package com.monitor.auth.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.monitor.base.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 角色-权限
 * @author lisuo
 * @since 2018-09-29
 */
@TableName("t_role_permission")
public class RolePermission extends BaseEntity<RolePermission> {


    /**
     * 角色id
     */
	@TableField("role_id")
	private Long roleId;
    /**
     * 资源id
     */
	@TableField("permission_id")
	private Long permissionId;



	public Long getRoleId() {
		return roleId;
	}

	public RolePermission setRoleId(Long roleId) {
		this.roleId = roleId;
		return this;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public RolePermission setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
