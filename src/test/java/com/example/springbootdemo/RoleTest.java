package com.example.monitor;


import com.alibaba.fastjson.JSON;
import com.monitor.MonitorApplication;
import com.monitor.auth.service.RoleService;
import com.monitor.auth.vo.RoleUpdateInVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: heguanyong
 * @Date: 2018/10/13 16:57
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MonitorApplication.class)
public class RoleTest {
    @Autowired
    private RoleService roleService;
    @Autowired
    private LscrmFunctionPrivilegeService lscrmFunctionPrivilegeService;
    @Autowired
    private LscrmFunctionPrivilegeDao lscrmFunctionPrivilegeDao;
    @Test
    public void updateRole(){
        RoleUpdateInVo inVo = new RoleUpdateInVo();
        inVo.setId(5L);
        inVo.setName("李董");
        inVo.setRoleDesc("李董事长");
        List<Long> longs = new ArrayList<>();
        Long one = 1l;
        Long two = 2l;
        longs.add(one);
        longs.add(two);
        inVo.setPermissionId(longs);
        roleService.update(inVo);
    }


    @Test
    public void test(){
        System.out.println(JSON.toJSONString(lscrmFunctionPrivilegeDao.readAllPrivileges(2)));
        //System.out.println(lscrmFunctionPrivilegeService.readAllPrivileges(2));
    }
}
