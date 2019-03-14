package com.monitor.job.dao;


import com.monitor.job.entity.JobAndTrigger;
import java.util.List;

/**
 * @Description:
 * @Author: lisuo
 * @Date: 2018/11/29:10:18
 */
public interface JobAndTriggerDao {
	public List<JobAndTrigger> getJobAndTriggerDetails();
}
