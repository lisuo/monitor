package com.monitor.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @Description: 定时任务测试类
 * @Author: lisuo
 * @Date: 2018/11/29:09:48
 */
public class HelloJob implements BaseJob {

    private static Logger LOGGER = LoggerFactory.getLogger(HelloJob.class);

    public HelloJob() {
        System.out.println("helloJob初始化：--------------------------------------------------");
    }

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        System.out.println("执行hello job----------------------");

    }
}

