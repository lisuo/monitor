package com.monitor.auth.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.monitor.auth.dao.RoleDao;
import com.monitor.auth.dao.RolePermissionDao;
import com.monitor.auth.entity.Role;
import com.monitor.auth.entity.RolePermission;
import com.monitor.auth.service.RolePermissionService;
import com.monitor.auth.service.RoleService;
import com.monitor.auth.vo.RoleAddInVo;
import com.monitor.auth.vo.RoleListInVo;
import com.monitor.auth.vo.RoleUpdateInVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lisuo
 * @since 2018-09-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {
    @Autowired
    private  RoleDao roleDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;
    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RoleUpdateInVo role) {
        Role r = new Role();
        r.setCreateTime(LocalDateTime.now());
        r.setId(role.getId());
        r.setName(role.getName());
        r.setRoleDesc(role.getRoleDesc());
        roleDao.updateById(r);
        EntityWrapper<RolePermission> wrapper = new EntityWrapper<>();
        wrapper.eq("role_id", role.getId());
        //将改角色的权限先全部删除 然后在做插入处理
        rolePermissionDao.delete(wrapper);
        insertRolePermission(role.getId(), role.getPermissionId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRole(RoleAddInVo param) {
        Role role = new Role();
        role.setName(role.getName());
        role.setRoleDesc(role.getRoleDesc());
        role.setCreateTime(LocalDateTime.now());
        roleDao.insert(role);
        insertRolePermission(role.getId(), param.getPermissionId());
    }


    /**
     * 插入角色对应的资源权限
     * @param roleId
     * @param permissionIds
     */
    private void insertRolePermission(Long roleId,List<Long> permissionIds){
        List<RolePermission> list = new ArrayList<>();
        for (Long menuId : permissionIds) {
            RolePermission rolePermission  = new RolePermission();
            rolePermission.setCreateTime(LocalDateTime.now());
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(menuId);
            list.add(rolePermission);
        }
        if (!CollectionUtils.isEmpty(list)) {
            rolePermissionService.insertBatch(list);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRole(Long[] roleIds) {
        //删除角色
        //删除角色对应的资源
    }

    @Override
    public Page<Role> selectRoleListPage(RoleListInVo params) {
        Page<Role> page = new Page<>(params.getPage(), params.getLimit());
        List<Role> roles = roleDao.selectRoleListPage(page, params);
        page.setRecords(roles);
        return page;
    }
}
