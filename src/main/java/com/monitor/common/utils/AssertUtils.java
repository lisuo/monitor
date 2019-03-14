package com.monitor.common.utils;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.validation.BindingResult;

import java.util.Objects;

/**
 * @author Administrator
 * @Title: lisuo
 * @ProjectName monitor
 * @Description: TODO
 * @date 2018/9/8 0008上午 12:17
 */
public abstract class AssertUtils {


    /**
     * 如果条件为<code>true</code> throw {@link ResourceNotFoundException}
     *
     * @param condition : 断言条件
     * @param message   : 错误信息
     * @throws ResourceNotFoundException
     */
    public static void assertResourceNotFoundIsTrue ( boolean condition , String message ) throws
            ResourceNotFoundException {
        if ( condition ) {
            throw new ResourceNotFoundException( message );
        }

    }



}
