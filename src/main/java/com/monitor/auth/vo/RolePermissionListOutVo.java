package com.monitor.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Description: TODO
 * @author lisuo
 * @date 2018/9/30 0030上午 2:02
 */
@ApiModel(description = "角色权限列表")
public class RolePermissionListOutVo {
    @ApiModelProperty(value = "权限id", required = true)
    private Long permissionId;

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("角色名")
    private Long roleName;

    @ApiModelProperty("资源名称")
    private String permissionName;

    @ApiModelProperty("资源路径")
    private String uri;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("上级")
    private Long pid;

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

    public Long getRoleName() {
        return roleName;
    }

    public void setRoleName(Long roleName) {
        this.roleName = roleName;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
