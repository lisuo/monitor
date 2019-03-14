package com.monitor.job;

import com.monitor.common.Result;
import com.monitor.job.dao.AppQuartzDao;
import com.monitor.job.entity.AppQuartz;
import com.monitor.job.entity.JobAndTrigger;
import com.monitor.job.service.AppQuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 定时任务controller
 * @Author: lisuo
 * @Date: 2018/11/29:16:38
 */
@RestController("jobController")
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobUtil jobUtil;
    @Autowired
    private AppQuartzService appQuartzService;
    @Autowired
    private AppQuartzDao appQuartzDao;


    @RequestMapping(value = "/queryJob", method = RequestMethod.GET)
    public Result<List<JobAndTrigger>> queryJob(int pageNum, int pageSize) throws Exception {
        List<JobAndTrigger> jobs = appQuartzDao.queryJobAndTriggerDetails();
        return new Result(200, "查询任务成功！", jobs);
    }
    /**
     * 添加一个job
     * @param appQuartz
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addJob", method = RequestMethod.POST)
    public Result addJob(@RequestBody AppQuartz appQuartz) throws Exception {
        appQuartzService.insertAppQuartzSer(appQuartz);
        String result = jobUtil.addJob(appQuartz);
        return new Result(200, "新增任务成功！");
    }

    /**
     * 暂停job
     * @param quartzIds
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/pauseJob", method = RequestMethod.POST)
    public Result pauseJob(@RequestBody Integer[] quartzIds) throws Exception {
        AppQuartz appQuartz = null;
        if(quartzIds.length > 0){
            for(Integer quartzId : quartzIds) {
                appQuartz = appQuartzService.selectAppQuartzByIdSer(quartzId).get(0);
                jobUtil.pauseJob(appQuartz.getJobName(), appQuartz.getJobGroup());
            }
            return new Result("200","success pauseJob");
        }else {
            return new Result("404","fail pauseJob");
        }
    }

    /**
     * 恢复job
     * @param quartzIds
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/resumeJob", method=RequestMethod.POST)
    public Result resumeJob(@RequestBody Integer[] quartzIds) throws Exception {
        AppQuartz appQuartz = null;
        if(quartzIds.length > 0) {
            for(Integer quartzId : quartzIds) {
                appQuartz = appQuartzService.selectAppQuartzByIdSer(quartzId).get(0);
                jobUtil.resumeJob(appQuartz.getJobName(), appQuartz.getJobGroup());
            }
            return new Result("200","success resumeJob");
        }else {
            return new Result("404","fail resumeJob");
        }
    }


    /**
     *  删除job
     * @param quartzIds
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/deletJob",method=RequestMethod.POST)
    public Result deletJob(@RequestBody Integer[] quartzIds) throws Exception {
        AppQuartz appQuartz = null;
        for(Integer quartzId:quartzIds) {
            appQuartz = appQuartzService.selectAppQuartzByIdSer(quartzId).get(0);
            String ret = jobUtil.deleteJob(appQuartz);
            if("success".equals(ret)) {
                appQuartzService.deleteAppQuartzByIdSer(quartzId);
            }
        }
        return new Result("200","success deleteJob");
    }

    /**
     * 修改
     * @param appQuartz
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateJob", method = RequestMethod.POST)
    public Result modifyJob(@RequestBody AppQuartz appQuartz) throws Exception {
        String ret = jobUtil.modifyJob(appQuartz);
        if("success".equals(ret)) {
            appQuartzService.updateAppQuartzSer(appQuartz);
            return new Result(200,"success updateJob", ret);
        }else {
            return new Result(404,"fail updateJob", ret);
        }
    }


    /**
     * 暂停所有
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/pauseAll",method = RequestMethod.GET)
    public Result pauseAllJob() throws Exception {
        jobUtil.pauseAllJob();
        return new Result("200","success pauseAll");
    }


    /**
     * 恢复所有
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/resumeAll", method = RequestMethod.GET)
    public Result resumeAllJob() throws Exception {
        jobUtil.resumeAllJob();
        return new Result("200","success repauseAll");
    }

}
