package com.monitor.common.utils;

import org.apache.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:
 * @Author: lisuo
 * @Date: 2018/12/19:11:14
 */
public class CsvExportUtil {

    private static Logger logger = Logger.getLogger(CsvExportUtil.class);

    public static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";// 默认日期格式

    /**
     * 生成为CVS文件
     * @param exportData
     * @param map
     * @param fileName
     * @param response
     * @throws UnsupportedEncodingException
     */
    @SuppressWarnings("rawtypes")
    public static void createCSVFile(List exportData, LinkedHashMap map, String fileName,HttpServletResponse response) throws UnsupportedEncodingException {
        // BufferedWriter
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        OutputStreamWriter csvFileOutputStream=null;
        // Writer out=new OutputStreamWriter(
        try {
            csvFileOutputStream = new OutputStreamWriter(out,"UTF-8");
            for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
                java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
                csvFileOutputStream.write((String) propertyEntry.getValue() != null
                        ? new String(((String) propertyEntry.getValue()).getBytes("UTF-8"), "UTF-8")
                        : "");
                if (propertyIterator.hasNext()) {
                    csvFileOutputStream.write(",");
                }
            }
            csvFileOutputStream.write("\r\n");
            // 写入文件内容,
            // ============ //第一种格式：Arraylist<实体类>填充实体类的基本信息==================
            for (int j = 0; exportData != null && !exportData.isEmpty() && j < exportData.size(); j++) {
                Object t = exportData.get(j);
                Class clazz = t.getClass();
                String[] contents = new String[map.size()];
                int i = 0;
                for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
                    java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
                    String filedName = (String) propertyEntry.getKey() != null ?
                            // new String(((String) propertyEntry.getKey()).getBytes("GBK"), "GBK") : "";
                            // new String(((String) propertyEntry.getKey()).getBytes("UTF-8"),
                            // "iso-8859-1"): "";
                            (String) propertyEntry.getKey() : "";
                    Method method = clazz.getMethod(toUpperCaseFirstOne(filedName));
                    method.setAccessible(true);
                    Object obj = method.invoke(t);
                    boolean isEmpty = method.invoke(t) == null;
                    if (method.getReturnType().getName().equals("java.util.Date") && !isEmpty) {
                        String time = new SimpleDateFormat(DEFAULT_DATE_PATTERN).format(obj);
                        obj = time;
                    }
                    String str = String.valueOf(obj);

                    if (str == null || str.equals("null")) {
                        str = "";
                    }
                    str = csvHandlerStr(str);
                    // contents[i] =new String(str.getBytes("GBK"), "GBK") ;
                    // contents[i] =new String(str.getBytes("UTF-8"));
                    contents[i] = str;
                    i++;
                }
                for (int n = 0; n < contents.length; n++) {
                    // 将生成的单元格添加到工作表中
                    csvFileOutputStream.write(contents[n]);
                    csvFileOutputStream.write(",");

                }
                csvFileOutputStream.write("\r\n");
            }
            out.write("\ufeff".getBytes());
            csvFileOutputStream.flush();
            ServletOutputStream servletOut=response.getOutputStream();
            servletOut.write(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            //return null;
        } finally {
            try {
                csvFileOutputStream.close();
                out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    /**
     * 将第一个字母转换为大写字母并和get拼合成方法
     *
     * @param origin
     * @return
     */
    private static String toUpperCaseFirstOne(String origin) {
        StringBuffer sb = new StringBuffer(origin);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        sb.insert(0, "get");
        return sb.toString();
    }

    /**
     * 导出csv格式文件
     *
     * @param title
     *            文件名称
     * @param headMap
     *            列名
     * @param ja
     *            数据源
     * @param response
     *            输出response
     */
    public static void cvsExport(String title, Map<String, String> headMap, List ja, HttpServletResponse response) {
        List<String> keyList = new ArrayList<String>(headMap.keySet());
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        for (String key : keyList) {
            linkedHashMap.put(key, headMap.get(key));
        }
        try {
            response.setContentType("application/csv;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Expose-Headers", "*");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(title + ".csv", "UTF-8"));
            createCSVFile(ja, linkedHashMap, title, response);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("下载出错", e);
        }

    }

    /**
     * 方法名称: csvHandlerStr</br>
     * 方法描述: 处理包含逗号，或者双引号的字段</br>
     * 方法参数: @param forecastName 方法参数: @return </br>
     * 返回类型: String</br>
     * 抛出异常:</br>
     */
    private static String csvHandlerStr(String str) {
        // csv格式如果有逗号，整体用双引号括起来；如果里面还有双引号就替换成两个双引号，这样导出来的格式就不会有问题了
        String tempDescription = str;
        // 如果有逗号
        if (str.contains(",")) {
            // 如果还有双引号，先将双引号转义，避免两边加了双引号后转义错误
            if (str.contains("\"")) {
                tempDescription = str.replace("\"", "\"\"");
            }
            // 在将逗号转义
            tempDescription = "\"" + tempDescription + "\"";
        }
        return tempDescription;
    }

}