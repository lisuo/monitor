package com.monitor.auth.annotations;

import java.lang.annotation.*;

/**
 * @Description: 参数校验注解
 * @author lisuo
 * @date 2018/10/9 0009下午 11:26
 */
@Documented
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamValid {
    /**
     *
     * @return
     */
    String description() default "";
}
