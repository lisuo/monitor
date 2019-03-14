package com.monitor.common.utils;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author lisuo
 * @Title: lisuo
 * @ProjectName monitor
 * @Description: TODO
 * @date 2018/9/10 0010下午 11:56
 */
public class CopyUtils {

    /**
     * 用于不同类型List之间的拷贝，List中的对象有些属性可能不能的情况
     * @param input
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> copyListToList(List<T> input, Function<T, R> function){
        List<R> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(input)){
                input.stream().forEach(t ->{
                    R m = function.apply(t);
                    BeanUtils.copyProperties(t, m);
                    result.add(m);
                });
            }
        return result;
    }

    /**
     *
     * @param input
     * @param fieldName
     * @param <T>
     * @return
     */
    public static <T>Map<Object, T>convertListToMap(List<T> input, String fieldName){
        return input.stream().collect(Collectors.toMap(t ->{
            Method method = null;
            try {
                method = t.getClass().getDeclaredMethod("get" + fieldName.substring(0, 1).toUpperCase().concat(fieldName.substring(1)));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                return method.invoke(t);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }, Function.identity()));
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("jack", "henan"));
        students.add(new Student("bruce", "guangdong"));
        List<Teacher> teachers = copyListToList(students, t ->{
            Teacher teacher = new Teacher();
            teacher.setAddr(t.getAddress());
            return teacher;
        });
        System.out.println(teachers);
        System.out.println(convertListToMap(students, "name"));
    }
}

class Student{
    private String name;
    private String address;

    public Student(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }

}

class Teacher{
    private String name;
    private String addr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }

}