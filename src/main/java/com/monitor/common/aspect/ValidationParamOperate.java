package com.monitor.common.aspect;

/**
 * @author lisuo
 * @since on 2018/9/29.
 */
public class ValidationParamOperate extends AspectHandler {
    @Override
    protected ValidationParamAspect factoryMethod() {
        return  new ValidationParamAspect();
    }
}
