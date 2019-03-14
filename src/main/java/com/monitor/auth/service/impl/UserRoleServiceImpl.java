package com.monitor.auth.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.monitor.auth.dao.UserRoleDao;
import com.monitor.auth.entity.UserRole;
import com.monitor.auth.service.UserRoleService;
import com.monitor.auth.vo.AddUserRoleInVo;
import com.monitor.auth.vo.QueryUserRoleListOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author lisuo
 * @Date 2018-09-29
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	public Set<Long> listUserRoleIds(String username) {
		Set<String> roleIds = userRoleDao.queryUserRoleIdsByUserName(username);
		Set<Long> roleIdsLong = new HashSet<>();
		for (String roleId : roleIds){
			roleIdsLong.add(Long.parseLong(roleId));
		}
		return roleIdsLong;
	}

	@Override
	public void saveOrUpdate(AddUserRoleInVo param) {
		//先删除用户与角色关系
		List<Long> roleIdList = param.getRoleIds();
		Long userId = param.getUserId();
		Map<String, Object> map = new HashMap<>();
		map.put("user_id", userId);
		this.deleteByMap(map);
		if(CollectionUtils.isEmpty(roleIdList)){
			return ;
		}

		//保存用户与角色关系
		List<UserRole> list = new ArrayList<>(roleIdList.size());
		for(Long roleId : roleIdList){
			UserRole userRole = new UserRole();
			userRole.setUserId(userId);
			userRole.setRoleId(roleId);
			userRole.setCreateTime(LocalDateTime.now());
			list.add(userRole);
		}
		this.insertBatch(list);
	}

	@Override
	public List<QueryUserRoleListOut> queryRoleIdList(Long userId) {
		return userRoleDao.queryUserRoleList(userId);
	}

	@Override
	public int deleteBatch(Long[] roleIds) {
		return userRoleDao.deleteBatch(roleIds);
	}

	@Override
	public Set<String> queryUserRoleIdsByUserName(String username) {

		return userRoleDao.queryUserRoleIdsByUserName(username);
	}
}
