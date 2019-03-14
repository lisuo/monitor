package com.monitor.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Description:
 * @Author: lisuo
 * @Date: 2018/11/29:09:51
 */
public class NewJob implements BaseJob {

    private static Logger LOGGER = LoggerFactory.getLogger(NewJob.class);

    public NewJob() {
        System.out.println("NewJob初始化：--------------------------------------------------");
    }

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        System.out.println("执行new job---------------------------");

    }
}

