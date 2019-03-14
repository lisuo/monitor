package com.monitor.auth.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.monitor.auth.entity.User;
import com.monitor.auth.vo.QueryUserInfoOutVo;
import com.monitor.auth.vo.UserListInVo;
import com.monitor.auth.vo.UserListOutVo;
import com.monitor.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lisuo
 * @since 2018-09-06
 */
@Mapper
public interface UserDao extends BaseDao<User> {
    /**
     *
     * @param id
     * @return
     */
    User mySelect(Integer id);

    /**
     *
     * @param page
     * @param params
     * @return
     */
    List<UserListOutVo> selectUserListPage(Page page, UserListInVo params);

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

}