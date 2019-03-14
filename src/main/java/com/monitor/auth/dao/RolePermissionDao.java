package com.monitor.auth.dao;


import com.monitor.auth.entity.RolePermission;
import com.monitor.auth.vo.QueryRolePermissionOutVo;
import com.monitor.auth.vo.RolePermissionListOutVo;
import com.monitor.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 *
 * @author lisuo
 * @since 2018-09-29
 */
@Mapper
public interface RolePermissionDao extends BaseDao<RolePermission> {
    /**
     * 查询角色权限
     * @param roleId
     * @return
     */
    RolePermissionListOutVo queryRolePermission(Long roleId);

    /**
     * 查询角色ID的Set集合拥有的url权限
     * @param roleIds
     * @return
     */
	List<String> queryUserPermissionUrls(@Param("roleIds") Set<Long> roleIds);

    /**
     * 根据角色查询用户权限
     * @param roleIds
     * @return
     */
	List<QueryRolePermissionOutVo> queryRolePermission(@Param("roleIds")Set<Long> roleIds);

}