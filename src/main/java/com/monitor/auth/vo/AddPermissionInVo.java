package com.monitor.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Description: 增加权限入参
 * @author lisuo
 * @date 2018/9/29 0029下午 8:24
 */
@ApiModel("增加权限")
public class AddPermissionInVo {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty(value = "uri", required = true)
    private String uri;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("父id")
    private Long pid;

    @ApiModelProperty("排序")
    private String sort;

    @ApiModelProperty("权限值")
    private String value;

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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
