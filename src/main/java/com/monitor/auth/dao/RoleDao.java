package com.monitor.auth.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.monitor.auth.entity.Role;
import com.monitor.auth.vo.RoleListInVo;
import com.monitor.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色
 * @author lisuo
 * @since 2018-09-28
 */
@Mapper
public interface RoleDao extends BaseDao<Role> {

    /**
     * 根据用户id查询用户角色
     * @param userId 用户id
     * @return
     */
    List<Role> queryRoleByUserId(Long userId);


    /**
     * 分页查询角色列表
     * @param page
     * @param params
     * @return
     */
    List<Role> selectRoleListPage(Page page, RoleListInVo params);

}