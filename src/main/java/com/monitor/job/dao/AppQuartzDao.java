package com.monitor.job.dao;


import com.monitor.base.BaseDao;
import com.monitor.job.entity.AppQuartz;
import com.monitor.job.entity.JobAndTrigger;

import java.util.List;

/**
 * @Description:
 * @Author: lisuo
 * @Date: 2018/11/29:16:45
 */
public interface AppQuartzDao extends BaseDao<AppQuartz> {
    List<JobAndTrigger> queryJobAndTriggerDetails();
}
