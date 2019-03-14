package com.monitor.auth.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.monitor.auth.dao.RoleDao;
import com.monitor.auth.dao.UserDao;
import com.monitor.auth.dao.UserRoleDao;
import com.monitor.auth.entity.User;
import com.monitor.auth.entity.UserRole;
import com.monitor.auth.service.RolePermissionService;
import com.monitor.auth.service.UserRoleService;
import com.monitor.auth.service.UserService;
import com.monitor.auth.util.AssertUtils;
import com.monitor.auth.util.ShiroUtils;
import com.monitor.auth.vo.QueryUserInfoOutVo;
import com.monitor.auth.vo.RegisterUserInVo;
import com.monitor.auth.vo.UserListInVo;
import com.monitor.auth.vo.UserListOutVo;
import com.monitor.common.Constant;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author lisuo
 * @since 2018-09-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private RoleDao roleDao;


	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private RolePermissionService rolePermissionService;


	@Autowired
	private CacheManager cacheManager;
	@Override
	public Page<UserListOutVo> selectUserListPage(UserListInVo params) {
		// 当前页，总条数 构造 page 对象
		Page<UserListOutVo> page = new Page<>(params.getPage(), params.getLimit());
		List<UserListOutVo> users = userDao.selectUserListPage(page, params);
		page.setRecords(users);
		return page;
	}

	@Override
	public User findByUserName(String username) {
		return userDao.findByUserName(username);
	}

	@Override
	public List<String> getPermission(String username) {
		Cache userCache = cacheManager.getCache(username);

		List<String> permissions = (List)userCache.get(Constant.AUTH_USER_PERMISSIONS + username);
		if (Objects.isNull(permissions)){
			//用户拥有的角色集合
			Set<Long> roleIds = (Set)userCache.get(Constant.AUTH_USER_ROLES + username);
			if (Objects.isNull(userCache.get(Constant.AUTH_USER_ROLES + username))){
				roleIds = userRoleService.listUserRoleIds(username);
				userCache.put(Constant.AUTH_USER_ROLES + username, roleIds);
			}
			permissions = rolePermissionService.queryUserPermissionUrls(roleIds);
			userCache.put(Constant.AUTH_USER_PERMISSIONS + username, permissions);
		}
		return permissions;
	}
	
    @Override
    public QueryUserInfoOutVo queryUserInfo(Long id) {
        return userDao.queryUserInfo(id);
    }

    @Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteUsers(Long[] userIds) {
		//删除用户
		userDao.deleteBatch(userIds);
		//删除用户角色
		List<UserRole> userRoles = userRoleDao.queryUserRoleByUserIds(userIds);
		if (!CollectionUtils.isEmpty(userRoles)){
			userRoleDao.deleteBatchIds(userRoles.stream().map(UserRole :: getId).collect(Collectors.toList()));
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void addUser(RegisterUserInVo registerUserInVo) {


		//新增用户
		User user = new User();
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setSalt(salt);
		BeanUtils.copyProperties(registerUserInVo, user);
		user.setPassword(ShiroUtils.sha256(registerUserInVo.getPassword(), user.getSalt()));
		user.setPs(registerUserInVo.getPassword());
		if (registerUserInVo.getOperateType().equals(Constant.ZERO)){
			//查询用户是否存在
			AssertUtils.isNull(findByUserName(registerUserInVo.getUsername()), -1, "用户名已经存在！");
			user.setCreateTime(LocalDateTime.now());
			userDao.insert(user);
		}else{
			user.setId(findByUserName(registerUserInVo.getUsername()).getId());
			userDao.updateById(user);
		}

		//创建角色
		/*Role role = new Role();
		role.setName(UserRoleEnum.getNameByType(user.getType()));
		role.setCreateTime(LocalDateTime.now());
		roleDao.insert(role);*/

		//为用户添加角色
		Long[] userIds = {user.getId()};
		List<UserRole> userRoles = userRoleDao.queryUserRoleByUserIds(userIds);
		if (!CollectionUtils.isEmpty(userRoles)){
			userRoleDao.deleteById(userRoles.get(0));
		}
		UserRole userRole = new UserRole();
		userRole.setUserId(user.getId());
		userRole.setRoleId(user.getType());
		userRole.setCreateTime(LocalDateTime.now());
		userRoleDao.insert(userRole);



	}
}
