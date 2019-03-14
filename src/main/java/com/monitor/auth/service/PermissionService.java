package com.monitor.auth.service;

import com.baomidou.mybatisplus.service.IService;
import com.monitor.auth.entity.Permission;
import com.monitor.auth.vo.QueryRolePermissionOutVo;

import java.util.List;

/**
 *
 * @author lisuo
 * @since 2018-09-29
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 查询用户权限
     * @param userId
     * @return
     */
    List<QueryRolePermissionOutVo> queryUserPermission(Long userId);

    //List<QueryRolePermissionOutVo> queryRolePermission(List<Long> roleIds);

    /**
     * 删除权限
     * @param permissionIds
     * @return
     */
    Integer deletePermissions(Long[] permissionIds);
}
