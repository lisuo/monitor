package com.monitor.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Auther: heguanyong
 * @Date: 2018/10/13 15:22
 * @Description:
 */
@ApiModel(description = "角色修改传入参数")
public class RoleUpdateInVo {
    @ApiModelProperty("主键id")
    private Long id;
    @ApiModelProperty("角色名称")
    private String name;
    @ApiModelProperty("角色描叙")
    private String roleDesc;
    @ApiModelProperty("资源列表id")
    private List<Long> permissionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Long> getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(List<Long> permissionId) {
        this.permissionId = permissionId;
    }
}
