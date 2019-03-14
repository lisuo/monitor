package com.example.monitor;


import com.monitor.MonitorApplication;
import com.monitor.job.JobUtil;
import com.monitor.job.entity.AppQuartz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @author lisuo
 * @date 2018/11/30 0030上午 12:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MonitorApplication.class)
public class JobTest {
    @Autowired
    private JobUtil jobUtil;

    @Test
    public void test(){
        AppQuartz appQuartz = new AppQuartz();
        appQuartz.setJobName("newJob");
        appQuartz.setJobClassName("newJob");
        appQuartz.setJobGroup("newJob");
        appQuartz.setCronExpression("*/1 * * * * ?");
        try {
            jobUtil.addJob(appQuartz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
