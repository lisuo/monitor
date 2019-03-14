package com.monitor.auth.annotations;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: lisuo
 * @Date: 2018/12/13:17:21
 */
@Documented
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserInfoChange {
    /**
     *
     * @return
     */
    String description() default "";
}
