package com.monitor.auth.service;

import com.baomidou.mybatisplus.service.IService;
import com.monitor.auth.entity.UserRole;
import com.monitor.auth.vo.AddUserRoleInVo;
import com.monitor.auth.vo.QueryUserRoleListOut;

import java.util.List;
import java.util.Set;

/**
 * @author lisuo
 * @since 2018-09-29
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     *  根据用户名查询用户角色
     * @param username
     * @return
     */
    Set<String> queryUserRoleIdsByUserName(String username);
    /**
     *
     * @param username
     * @return
     */
    Set<Long> listUserRoleIds(String username);

    /**
     * 保存
     * @param param
     */
    void saveOrUpdate(AddUserRoleInVo param);

    /**
     * 查询用户角色
     *
     * @param userId
     * @return
     */
    List<QueryUserRoleListOut> queryRoleIdList(Long userId);

    /**
     * 删除角色
     *
     * @param roleIds
     * @return
     */
    int deleteBatch(Long[] roleIds);
}
