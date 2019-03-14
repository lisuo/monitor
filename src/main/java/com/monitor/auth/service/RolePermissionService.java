package com.monitor.auth.service;

import com.baomidou.mybatisplus.service.IService;
import com.monitor.auth.entity.RolePermission;
import com.monitor.auth.vo.RolePermissionListOutVo;
import java.util.List;
import java.util.Set;

/**
 *
 * @author lisuo
 * @since 2018-09-29
 */
public interface RolePermissionService extends IService<RolePermission> {

    /**
     * 查询角色权限
     * @param roleId
     * @return
     */
    RolePermissionListOutVo queryRolePermission(Long roleId);

    /**
     * 按用户拥有的角色ID集合查询用户拥有的权限
     * @param roleIds
     * @return
     */
	List<String> queryUserPermissionUrls(Set<Long> roleIds);

    /**
     * 根据用户名查询角色权限
     * @param username
     * @return
     */
	List<String> queryUserPermissionUrlsByUserName(String username);
}
