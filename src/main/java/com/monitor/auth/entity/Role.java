package com.monitor.auth.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.monitor.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 *
 * 用户角色
 * @author lisuo
 * @since 2018-09-28
 */
@TableName("t_role")
@ApiModel(description = "角色")
public class Role extends BaseEntity<Role> {

    /**
     * 角色描叙
     */
	@TableField("role_desc")
	@ApiModelProperty("角色描叙")
	private String roleDesc;
    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
	private String name;

	public String getRoleDesc() {
		return roleDesc;
	}

	public Role setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
		return this;
	}

	public String getName() {
		return name;
	}

	public Role setName(String name) {
		this.name = name;
		return this;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
