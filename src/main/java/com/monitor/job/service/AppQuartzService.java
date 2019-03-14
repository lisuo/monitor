package com.monitor.job.service;



import com.baomidou.mybatisplus.service.IService;
import com.monitor.job.entity.AppQuartz;

import java.util.List;

/**
 * @Description:
 * @Author: lisuo
 * @Date: 2018/11/29:16:39
 */
public interface AppQuartzService extends IService<AppQuartz> {

    int insertAppQuartzSer(AppQuartz appQuartz);

    List<AppQuartz> selectAppQuartzByIdSer(Integer quartzId);

    Integer deleteAppQuartzByIdSer(Integer quartzId);

    Integer updateAppQuartzSer(AppQuartz appQuartz);
}
