package com.monitor.auth.service;



import com.baomidou.mybatisplus.service.IService;
import com.monitor.auth.entity.User;
import com.monitor.auth.vo.QueryUserInfoOutVo;
import com.monitor.auth.vo.RegisterUserInVo;
import com.monitor.auth.vo.UserListInVo;
import com.monitor.auth.vo.UserListOutVo;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;


/**
 *
 * @author lisuo
 * @since 2018-09-06
 */
public interface UserService extends IService<User> {

    /**
     * 查询用户列表
     * @param params
     * @return
     */
    Page<UserListOutVo> selectUserListPage(UserListInVo params);

    /**
     * 查询用户信息
     * @param id
     * @return
     */
    QueryUserInfoOutVo queryUserInfo(Long id);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * 查询用户权限
     * @param username
     */
	List<String> getPermission(String username);

    /**
     * 删除用户
     * @param ids
     */
	void deleteUsers(Long[] ids);

    /**
     * 新增用户
     * @param registerUserInVo
     */
	void addUser(RegisterUserInVo registerUserInVo);
}
