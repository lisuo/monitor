package com.monitor.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.List;

/**
 * @Description: TODO
 * @author lisuo
 * @date 2018/9/29 0029上午 10:37
 */
@ApiModel(description = "添加角色入参")
public class RoleAddInVo {

    @ApiModelProperty("角色描叙")
    private String roleDesc;
    @ApiModelProperty("角色名称")
    private String name;
    @ApiModelProperty("资源列表id")
    private List<Long> permissionId;

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(List<Long> permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
