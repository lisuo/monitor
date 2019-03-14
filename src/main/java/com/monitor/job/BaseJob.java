package com.monitor.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Description: 定时任务基础类
 * @Author: lisuo
 * @Date: 2018/11/29:09:46
 */
public interface BaseJob extends Job{
    public void execute(JobExecutionContext context) throws JobExecutionException;
}