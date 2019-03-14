package com.monitor.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.List;

/**
 * @Description: 用户添加角色入参
 * @author lisuo
 * @date 2018/9/29 0029上午 10:50
 */
@ApiModel(description = "用户添加角色入参")
public class AddUserRoleInVo {

    @ApiModelProperty(value = "用户id", required = true)
    private Long userId;

    @ApiModelProperty(value = "角色id列表")
    private List<Long> roleIds;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
