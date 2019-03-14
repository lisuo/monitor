package com.monitor.common.aspect;

/**
 * @author lisuo
 * @since on 2018/9/29.
 */
public class RecordLogOperate extends AspectHandler {
    @Override
    protected RecordLogAspect factoryMethod() {
        return new RecordLogAspect();
    }
}
