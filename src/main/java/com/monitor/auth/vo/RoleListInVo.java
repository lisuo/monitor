package com.monitor.auth.vo;

import com.monitor.base.PageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Description: 查询角色列表入参
 * @Author: lisuo
 * @Date: 2018/12/1:09:59
 */
@ApiModel(description = "查询角色列表入参")
public class RoleListInVo extends PageVo {
    @ApiModelProperty("角色描叙")
    private String roleDesc;
    @ApiModelProperty("角色名称")
    private String name;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
