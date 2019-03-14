package com.monitor.common.annotation;


import java.lang.annotation.*;

/**
 * 在Controller方法上加入改注解会自动记录日志
 * @author : lisuo
 * @date : 2018/09/29
 */
@Target( { ElementType.METHOD } )
@Retention( RetentionPolicy.RUNTIME )
@Documented
public @interface Log {
	/**
	 * 描述.
	 */
	String description() default "";

}
