package com.monitor.auth.dao;


import com.monitor.auth.entity.UserRole;
import com.monitor.auth.vo.QueryUserRoleListOut;
import com.monitor.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * 用户-角色
 *
 * @author lisuo
 * @since 2018-09-28
 */
@Mapper
public interface UserRoleDao extends BaseDao<UserRole> {

	/**
	 * 查询用户角色
	 * @param username
	 * @return
	 */
	Set<String> queryUserRoleIdsByUserName(String username);

	/**
	 * 查询用户角色列表
	 * @param userId
	 * @return
	 */
	List<QueryUserRoleListOut> queryUserRoleList(Long userId);

	/**
	 * 根据用户id查询用户角色
	 * @param userIds
	 * @return
	 */
	List<UserRole> queryUserRoleByUserIds(Long[] userIds);


	/**
	 * 根据用户id批量删除用户
	 * @param userIds
	 */
	void deleteByUserIds(Long[] userIds);


	/**
	 * 根据用户id批量删除用户
	 * @param userId
	 */
	void deleteByUserId(Long userId);

}