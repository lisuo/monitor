package com.example.monitor;


import com.monitor.MonitorApplication;
import com.monitor.auth.dao.RoleDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lisuo
 * @Description: TODO
 * @date 2018/12/10 0010下午 11:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MonitorApplication.class)
public class ProjectTest {
    @Autowired
    private DictDao dictDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ProjectService projectService;

    @Test
    public void testDict(){
        DictEntity dictEntity = new DictEntity();
        dictEntity.setParentId(1L);
        System.out.println(dictDao.selectDictList(dictEntity));
    }

    @Test
    public void delette(){
        roleDao.deleteById(8L);
    }


    @Test
    public void queryProjectList(){
        ProjectListInVo params = new ProjectListInVo();
        System.out.println("****************************" + projectService.selectListPage(params).getRecords());
    }
}
