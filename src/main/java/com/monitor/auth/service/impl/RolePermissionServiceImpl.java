package com.monitor.auth.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.monitor.auth.dao.RolePermissionDao;
import com.monitor.auth.entity.RolePermission;
import com.monitor.auth.service.RolePermissionService;
import com.monitor.auth.service.UserRoleService;
import com.monitor.auth.vo.RolePermissionListOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 *
 * @author lisuo
 * @since 2018-09-29
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionDao, RolePermission> implements RolePermissionService {
    @Autowired
    private RolePermissionDao rolePermissiondao;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public RolePermissionListOutVo queryRolePermission(Long roleId) {
        return null;
    }


	@Override
	public List<String> queryUserPermissionUrls(Set<Long> roleIds) {
		List<String> urls = rolePermissiondao.queryUserPermissionUrls(roleIds);
		return urls;
	}

    @Override
    public List<String> queryUserPermissionUrlsByUserName(String username) {
        Set<Long> roleIds = userRoleService.listUserRoleIds(username);
        if (CollectionUtils.isEmpty(roleIds)){
            return null;
        }
        return queryUserPermissionUrls(roleIds);
    }
}
