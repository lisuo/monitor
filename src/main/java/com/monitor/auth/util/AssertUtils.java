package com.monitor.auth.util;


import com.monitor.common.exception.BusinessException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @Description:
 * @Author: lisuo
 * @Date: 2018/10/9:12:24
 */
public abstract class AssertUtils {

    /**
     * 如果expression是true,就不抛出异常
     * @param expression
     * @param message 抛出异常的message
     */
    public static void isTrue(boolean expression, int code, String message) {
        if (!expression) {
            throw new BusinessException(code, message);
        }
    }

    /**
     * 如果expression是true,就不抛出异常,返回数据
     * @param expression
     * @param message 抛出异常的message
     */
    public static void isTrueToData(boolean expression, int code, String message,Object data) {
        if (!expression) {
            throw new BusinessException(code, message);
        }
    }
    /**
     * 如果expression是true,就不抛出异常
     * @param object
     * @param message
     */
    public static void isNull(Object object, int code, String message) {
        if (object != null) {
            throw new BusinessException(code, message);
        }
    }


    /**
     * 如果object不是null就不抛出异常
     * @param object
     * @param message
     */
    public static void notNull(Object object, int code, String message) {
        if (object == null) {
            throw new BusinessException(code, message);
        }
    }


    /**
     * text 是空的就抛出异常
     * @param text
     * @param message
     */
    public static void hasLength(String text, int code, String message) {
        if (!StringUtils.hasLength(text)) {
            throw new BusinessException(code, message);
        }
    }

    public static void hasText(String text, int code, String message) {
        if (!StringUtils.hasText(text)) {
            throw new BusinessException(code, message);
        }
    }

    public static void doesNotContain(String textToSearch, String substring, int code, String message) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) &&
                textToSearch.contains(substring)) {
            throw new BusinessException(code, message);
        }
    }

    public static void notEmpty(Object[] array, int code, String message) {
        if (ObjectUtils.isEmpty(array)) {
            throw new BusinessException(code, message);
        }
    }

    public static void noNullElements(Object[] array, int code, String message) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new BusinessException(code, message);
                }
            }
        }
    }

    public static void notEmpty(Collection<?> collection, int code, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BusinessException(code, message);
        }
    }

    public static void notEmpty(Map<?, ?> map, int code, String message) {
        if (CollectionUtils.isEmpty(map)) {
            throw new BusinessException(code, message);
        }
    }

    /**
     * 如果是null 或者是0就抛出异常
     * @param num
     * @param message
     */
    public static void notZero(Integer num, int code, String message) {
        isTrue(num != null && num != 0, code, message);
    }

    /**
     * 断言 a大于b, a,b任意一个等于null，或者a<=b就抛出异常
     * @param a
     * @param b
     * @param message
     */
    public static <A extends Comparable<B>, B extends Comparable<A>> void isGreater(A a, B b, int code, String message) {
        isTrue(a != null && b!= null && a.compareTo(b) > 0, code, message);
    }

    /**
     * 断言 a b相等 ,  a b不相等就抛出异常
     * @param a
     * @param b
     * @param message
     */
    public static void isEquals(Object a, Object b, int code, String message) {
        isTrue((a == null && b == null)||(a != null && a.equals(b)), code, message);
    }

    /**
     * 断言 a b不相等 ,  a b相等就抛出异常
     * @param a
     * @param b
     * @param message
     */
    public static void notEquals(Object a,Object b, int code, String message) {
        isTrue((a == null && b != null)||(a != null &&! a.equals(b)), code, message);
    }
}
