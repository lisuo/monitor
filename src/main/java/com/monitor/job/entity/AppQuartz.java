package com.monitor.job.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.monitor.base.BaseEntity;

/**
 * @Description:
 * @Author: lisuo
 * @Date: 2018/11/29:16:02
 */
@TableName("TB_APP_QUARTZ")
public class AppQuartz extends BaseEntity<AppQuartz> {
    //id  主键
    private Integer quartzId;

    //任务名称
    private String jobName;

    //任务类名
    private String jobClassName;

    //任务分组
    private String jobGroup;

    //任务开始时间
    private String startTime;

    //corn表达式
    private String cronExpression;

    //需要传递的参数
    private String invokeParam;

    public Integer getQuartzId() {
        return quartzId;
    }

    public void setQuartzId(Integer quartzId) {
        this.quartzId = quartzId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getInvokeParam() {
        return invokeParam;
    }

    public void setInvokeParam(String invokeParam) {
        this.invokeParam = invokeParam;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }
}
