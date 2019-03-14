package com.monitor.job.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.monitor.job.dao.AppQuartzDao;
import com.monitor.job.entity.AppQuartz;
import com.monitor.job.service.AppQuartzService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: lisuo
 * @Date: 2018/11/29:16:39
 */
@Service
public class AppQuartzServiceImpl extends ServiceImpl<AppQuartzDao, AppQuartz> implements AppQuartzService {
    @Override
    public int insertAppQuartzSer(AppQuartz appQuartz) {
        return 0;
    }

    @Override
    public List<AppQuartz> selectAppQuartzByIdSer(Integer quartzId) {
        return null;
    }

    @Override
    public Integer deleteAppQuartzByIdSer(Integer quartzId) {
        return null;
    }

    @Override
    public Integer updateAppQuartzSer(AppQuartz appQuartz) {
        return null;
    }
}
