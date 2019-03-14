package com.monitor.job;

import org.quartz.Job;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 定时任务常量
 * @author lisuo
 * @date 2018/11/29 0029下午 9:09
 */
public class JobConstants {

    private static String JOB_ONE = "jobOne";
    private static String NEW_JOB = "newJob";

    public static Map<String, Class<? extends Job>> JOB_MAP = new HashMap<>();
    static {
        JOB_MAP.put("jobOne", HelloJob.class);
        JOB_MAP.put("newJob", NewJob.class);
    }
}
