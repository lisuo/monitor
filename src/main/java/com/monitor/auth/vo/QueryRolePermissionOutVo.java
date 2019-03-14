package com.monitor.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Description:
 * @Author: lisuo
 * @Date: 2018/10/13:13:53
 */
@ApiModel(description = "角色权限")
public class QueryRolePermissionOutVo {
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty("资源路径")
    private String uri;
    @ApiModelProperty(value = "资源类型 1、目录2、菜单、3按钮")
    private String type;
    @ApiModelProperty(value = "资源父id")
    private Long pid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
