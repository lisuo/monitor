package com.monitor.auth.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.monitor.base.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 用户-角色关系
 * @author lisuo
 * @since 2018-09-29
 */
@TableName("t_user_role")
public class UserRole extends BaseEntity<UserRole> {

    /**
     * 用户id
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 角色id
     */
	@TableField("role_id")
	private Long roleId;

	public Long getUserId() {
		return userId;
	}

	public UserRole setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public Long getRoleId() {
		return roleId;
	}

	public UserRole setRoleId(Long roleId) {
		this.roleId = roleId;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
