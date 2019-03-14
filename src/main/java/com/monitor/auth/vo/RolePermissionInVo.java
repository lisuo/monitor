package com.monitor.auth.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Description: TODO
 * @author lisuo
 * @date 2018/9/29 0029下午 8:53
 */
@ApiModel(description = "角色权限")
public class RolePermissionInVo {
    @ApiModelProperty(value = "权限id", required = true)
    private Long permissionId;

    @ApiModelProperty("角色id")
    private Long roleId;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
