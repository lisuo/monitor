package com.monitor.common.utils;

import com.baomidou.mybatisplus.toolkit.ArrayUtils;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.monitor.common.annotation.CSVField;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.ReflectionUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:
 * @Author: lisuo
 * @Date: 2018/12/20:16:26
 */
public final class CSVUtils {

    /** 日志对象 **/
    private static final Logger LOGGER = Logger.getLogger(CSVUtils.class);

    /** 临时文件**/
    private static final String TEMP_PATH = "temp.csv";

    /** 私有无参构造方法 **/
    private CSVUtils() { }

    /**
     * 生成CSV文件
     *
     * @param filePath 文件保存路径，例如：D:/temp/test.csv
     * @param beans 实体对象集合
     */
    public static <T> void createCSVFile(String filePath, List<T> beans) {
        CsvWriter writer = null;
        try {
            // 创建文件目录
            createDir(filePath);
            // 生成文件
            writer = new CsvWriter(filePath, ',', Charset.forName("GBK"));
            // 获取内容
            List<String[]> contents = getStringArrayFromBean(beans);
            // 写入内容
            for (String[] each : contents) {
                writer.writeRecord(each, true);
            }
        } catch (Exception e) {
            LOGGER.error("生成CSV文件失败", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * 导出CSV文件
     *
     * @param request 请求对象
     * @param response 响应对象
     * @param beans 实体对象集合
     */
    public static <T> void exportCSVFile(HttpServletRequest request, HttpServletResponse response, List<T> beans) {
        String path = request.getServletContext().getRealPath("/");
        try (FileInputStream input = new FileInputStream(createFile(path + TEMP_PATH))) {
            // 生成文件
            CsvWriter writer = new CsvWriter(path + TEMP_PATH, ',', Charset.forName("GBK"));
            // 获取内容
            List<String[]> contents = getStringArrayFromBean(beans);
            // 写入内容
            for (String[] each : contents) {
                writer.writeRecord(each, true);
            }
            writer.close();
            // 导出文件
            ServletOutputStream output = response.getOutputStream();
            // 以当前时间作为文件名
            String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            response.setContentType("application/csv;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Expose-Headers", "*");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".csv");
            int len = -1;
            byte[] b = new byte[1024 * 1024];
            while((len = input.read(b)) != -1) {
                output.write(b, 0, len);
            }
            output.close();
        } catch (Exception e) {
            LOGGER.error("导出CSV文件失败", e);
        } finally {
            deleteFile(path, TEMP_PATH);
        }
    }

    /**
     * 导出CSV文件
     *
     * @param request 请求对象
     * @param response 响应对象
     * @param beans 实体对象集合
     * @param fileName 文件名称 ,如：测试名称
     */
    public static <T> void exportCSVFile(HttpServletRequest request, HttpServletResponse response, List<T> beans, String fileName) {
        String path = request.getServletContext().getRealPath("/");
        try (FileInputStream input = new FileInputStream(createFile(path + TEMP_PATH))) {
            // 生成文件
            CsvWriter writer = new CsvWriter(path + TEMP_PATH, ',', Charset.forName("GBK"));
            // 获取内容
            List<String[]> contents = getStringArrayFromBean(beans);
            // 写入内容
            for (String[] each : contents) {
                writer.writeRecord(each, true);
            }
            writer.close();
            // 导出文件
            ServletOutputStream output = response.getOutputStream();
            response.setContentType("application/csv;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Expose-Headers", "*");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".csv");
            int len = -1;
            byte[] b = new byte[1024 * 1024];
            while((len = input.read(b)) != -1) {
                output.write(b, 0, len);
            }
            output.close();
        } catch (Exception e) {
            LOGGER.error("导出CSV文件失败", e);
        } finally {
            deleteFile(path, TEMP_PATH);
        }
    }

    /**
     * 导出CSV模板
     * @param request
     * @param response
     * @param clazz
     * @param <T>
     */
    public static <T> void exportCSVTemplate(HttpServletRequest request, HttpServletResponse response, Class<T> clazz) {
        // 以当前时间作为文件名
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        exportCSVTemplate(request, response, clazz, fileName);
    }

    /**
     * 导出CSV模板
     * @param request
     * @param response
     * @param clazz
     * @param fileName
     * @param <T>
     */
    public static <T> void exportCSVTemplate(HttpServletRequest request, HttpServletResponse response, Class<T> clazz, String fileName) {
        String path = request.getServletContext().getRealPath("/");
        try (FileInputStream input = new FileInputStream(createFile(path + TEMP_PATH))) {
            // 生成文件
            CsvWriter writer = new CsvWriter(path + TEMP_PATH, ',', Charset.forName("GBK"));
            // 筛选出标有注解的字段
            Field[] declaredFields = clazz.getDeclaredFields();
            List<Field> annoFields = new ArrayList<Field>();
            for (Field field : declaredFields) {
                CSVField anno = field.getAnnotation(CSVField.class);
                if (anno != null) {
                    annoFields.add(field);
                }
            }
            // 获取注解的值，即内容标题
            String[] title = new String[annoFields.size()];
            for (int i = 0; i < annoFields.size(); i++) {
                title[i] = annoFields.get(i).getAnnotation(CSVField.class).name();
            }
            // 写入内容
            if (ArrayUtils.isNotEmpty(title)) {
                writer.writeRecord(title);
            }
            writer.close();
            // 导出文件
            ServletOutputStream output = response.getOutputStream();
            response.setContentType("application/csv;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Expose-Headers", "*");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".csv");
            int len = -1;
            byte[] b = new byte[1024 * 1024];
            while((len = input.read(b)) != -1) {
                output.write(b, 0, len);
            }
            output.close();
        } catch (Exception e) {
            LOGGER.error("导出CSV模板失败", e);
        } finally {
            deleteFile(path, TEMP_PATH);
        }
    }

    /**
     * 读取CSV文件内容
     *
     * @param filePath 文件存放的路径，如：D:/csv/xxx.csv
     * @param bean 类类型
     * @return List<T>
     */
    public static <T> List<T> readCSVFile(String filePath, Class<T> bean) {
        List<String[]> dataList = new ArrayList<String[]>();
        CsvReader reader = null;
        try {
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            reader = new CsvReader(filePath, ',', Charset.forName("GBK"));
            if (reader != null) {
                // 跳过表头，如果需要表头的话，这句可以忽略
                //reader.readHeaders();
                // 逐行读入除表头的数据
                while (reader.readRecord()) {
                    dataList.add(reader.getValues());
                }
                if (!dataList.isEmpty()) {
                    // 数组转对象
                    return getBeanFromStringArray(dataList, bean);
                }
            }
        } catch (Exception e) {
            LOGGER.error("读取CSV文件失败", e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return Collections.emptyList();
    }

    /**
     * 删除该目录下所有文件
     *
     * @param filePath 文件目录路径，如：d:/test
     */
    public static boolean deleteFiles(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File f : files) {
                    if (f.isFile() && f.delete()) {
                        LOGGER.info("删除" + f.getName() + "文件成功");
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 删除单个文件
     *
     * @param filePath 文件目录路径，如：d:/test
     * @param fileName 文件名称，如：110.csv
     */
    public static boolean deleteFile(String filePath, String fileName) {
        File file = new File(filePath);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File f : files) {
                    if (f.isFile() && f.getName().equals(fileName)) {
                        return f.delete();
                    }
                }
            }
        }
        return false;
    }

    /**
     * 泛型实体转换为数组
     *
     * @param beans 实体类集合
     * @return List<String[]>
     */
    private static <T> List<String[]> getStringArrayFromBean(List<T> beans) {
        List<String[]> result = new ArrayList<String[]>();
        Class<? extends Object> cls = beans.get(0).getClass();
        Field[] declaredFields = cls.getDeclaredFields();
        List<Field> annoFields = new ArrayList<Field>();
        // 筛选出标有注解的字段
        for (Field field : declaredFields) {
            CSVField anno = field.getAnnotation(CSVField.class);
            if (anno != null) {
                annoFields.add(field);
            }
        }
        // 获取注解的值，即内容标题
        String[] title = new String[annoFields.size()];
        for (int i = 0; i < annoFields.size(); i++) {
            title[i] = annoFields.get(i).getAnnotation(CSVField.class).name();
        }
        result.add(title);
        try {
            // 获取内容
            for (T t : beans) {
                String[] item = new String[annoFields.size()];
                int index = 0;
                for (Field field : annoFields) {
                    Class<?> valType = field.getType();
                    String fieldName = field.getName();
                    String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method method = ReflectionUtils.findMethod(t.getClass(), methodName);
                    if (method != null) {
                        Object value = ReflectionUtils.invokeMethod(method, t);
                        item[index] = setValues(value, valType);
                    }
                    index ++;
                }
                result.add(item);
            }
        } catch (Exception e) {
            LOGGER.info("实体对象转数组失败", e);
        }
        return result;
    }

    /**
     * 数组转为对象集合
     *
     * @param dataList 集合数据
     * @param bean 类类型
     * @return List<T>
     */
    private static <T> List<T> getBeanFromStringArray(List<String[]> dataList, Class<T> bean) {
        List<T> list = new ArrayList<>();
        List<Map<String, String>> titles = getTitles(dataList);
        Map<String, Field> fields = getFields(bean);
        try {
            for (Map<String, String> map : titles) {
                T t = bean.newInstance();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (fields.containsKey(entry.getKey())) {
                        Field field = fields.get(entry.getKey());
                        Class<?> valType = field.getType();
                        Object value = getType(entry.getValue(), valType);
                        String fieldName = field.getName();
                        String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        Method method = ReflectionUtils.findMethod(bean, methodName, valType);
                        if (method != null) {
                            ReflectionUtils.invokeMethod(method, t, value);
                        }
                    }
                }
                list.add(t);
            }
        } catch (Exception e) {
            LOGGER.error("创建实体失败", e);
        }
        return list;
    }

    /**
     * 数组标题与值的对应关系
     *
     * @param dataList 集合数据
     * @return
     */
    private static <T> List<Map<String, String>> getTitles(List<String[]> dataList) {
        List<Map<String, String>> list = new ArrayList<>();
        String[] titles = dataList.get(0);
        dataList.remove(0);
        for (String[] values : dataList) {
            Map<String, String> titleMap = new HashMap<>();
            for (int i = 0; i < values.length; i++) {
                titleMap.put(titles[i], values[i]);
            }
            list.add(titleMap);
        }
        return list;
    }

    /**
     * 注解名称与字段属性的对应关系
     *
     * @param clazz 实体对象类类型
     * @param <T> 泛型类型
     * @return Map<String,Field>
     */
    private static <T> Map<String, Field> getFields(Class<T> clazz) {
        Map<String, Field> annoMap = new HashMap<>();
        Field[] fileds = clazz.getDeclaredFields();
        for (Field filed : fileds) {
            CSVField anno = filed.getAnnotation(CSVField.class);
            if (anno != null) {
                // 获取name属性值
                if (StringUtils.isNotBlank(anno.name())) {
                    annoMap.put(anno.name(), filed);
                }
            }
        }
        return annoMap;
    }

    /**
     * 转换值
     *
     * @param value 属性值
     * @param valType 属性类型
     * @return
     */
    private static String setValues(Object value, Class<?> valType) {
        if (value == null) {
            return "";
        } else if (valType == Date.class) {
            // 默认日期类型格式：yyyy-MM-dd HH:mm:ss
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(((Date) value).getTime());

        } else { // 字符串
            return String.valueOf(value);
        }
    }

    /**
     * 转换成实体属性对应的类型
     *
     * @param value 每一格的数值
     * @param valType 实体属性类型
     * @return Object 转换为对应类型以obj返回
     */
    private static <T> Object getType(String value, Class<T> valType) {
        try {
            if (valType == Date.class) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return sdf.parse(value);
            } else if (valType == Double.class) {
                return Double.parseDouble(value);
            } else if (valType == BigDecimal.class) {
                return new BigDecimal(value);
            } else if (valType == Integer.class) {
                return Integer.parseInt(value);
            } else if (valType == Long.class) {
                return Long.parseLong(value);
            } else if (valType == Boolean.class) {
                return Boolean.parseBoolean(value);
            }
        } catch (Exception e) {
            LOGGER.error("类型转换异常", e);
        }
        return value;
    }

    /**
     * 创建文件目录
     *
     * @param filePath 文件路径，例如：temp/test.csv
     */
    private static void createDir(String filePath) {
        try {
            File file = new File(filePath.substring(0, filePath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            LOGGER.error("创建文件目录失败", e);
        }
    }

    /**
     * 创建文件
     *
     * @param filePath 文件路径，例如：temp/test.csv
     * @return File
     */
    private static File createFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            return file;
        } catch (Exception e) {
            LOGGER.error("创建文件失败", e);
        }
        return null;
    }

}
