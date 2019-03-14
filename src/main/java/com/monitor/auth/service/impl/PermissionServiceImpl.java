package com.monitor.auth.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.monitor.auth.dao.PermissionDao;
import com.monitor.auth.dao.RolePermissionDao;
import com.monitor.auth.dao.UserRoleDao;
import com.monitor.auth.entity.Permission;
import com.monitor.auth.entity.UserRole;
import com.monitor.auth.service.PermissionService;
import com.monitor.auth.service.UserRoleService;
import com.monitor.auth.vo.QueryRolePermissionOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 *
 * @author lisuo
 * @since 2018-09-29
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission> implements PermissionService {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserRoleDao userRoleDao;

    private RolePermissionDao rolePermissionDao;

    @Override
    public List<QueryRolePermissionOutVo> queryUserPermission(Long userId) {
        //查询用户角色
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        List<UserRole> userRoles = userRoleDao.queryList(map);
        Set<Long> roleIds = new HashSet<>();

        userRoles.stream().forEach(ur ->{
            roleIds.add(ur.getRoleId());
        });

        //根据角色查询用户拥有的权限
        List<QueryRolePermissionOutVo> urls = rolePermissionDao.queryRolePermission(roleIds);
        return urls;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deletePermissions(Long[] permissionIds) {
        //删除权限
        //删除角色权限
        return null;
    }
}
