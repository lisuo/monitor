package com.monitor.auth.dao;


import com.monitor.auth.entity.Permission;
import com.monitor.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
  * InnoDB free: 8192 kB Mapper 接口
 * </p>
 *
 * @author lisuo
 * @since 2018-09-29
 */
@Mapper
public interface PermissionDao extends BaseDao<Permission> {

}