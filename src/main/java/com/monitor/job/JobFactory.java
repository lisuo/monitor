package com.monitor.job;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * @Description:创建job 实例工厂，解决spring注入问题，如果使用默认会导致spring的@Autowired 无法注入问题
 * @Author: lisuo
 * @Date: 2018/11/29:16:05
 */
@Component
public class JobFactory extends AdaptableJobFactory {
    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        //调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        //进行注入
        capableBeanFactory.autowireBean(jobInstance);
        System.out.println("jobInstance: -------------------" + jobInstance);
        return jobInstance;
    }

}
