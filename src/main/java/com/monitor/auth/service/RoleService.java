package com.monitor.auth.service;


import com.baomidou.mybatisplus.service.IService;
import com.monitor.auth.entity.Role;
import com.monitor.auth.vo.RoleAddInVo;
import com.monitor.auth.vo.RoleListInVo;
import com.monitor.auth.vo.RoleUpdateInVo;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * @author lisuo
 * @since 2018-09-29
 */
public interface RoleService extends IService<Role> {

    /**
     * 更新角色
     * @param role
     */
    void update(RoleUpdateInVo role);

    /**
     * 新增角色
     * @param param
     */
    void addRole(RoleAddInVo param);

    /**
     * 删除角色
     * @param roleIds
     */
    void deleteRole(Long[] roleIds);

    /**
     * 查询角色列表
     * @param params
     * @return
     */
    Page<Role> selectRoleListPage(RoleListInVo params);
}
