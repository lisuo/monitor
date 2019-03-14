package com.monitor.auth.vo;

/**
 * @author Administrator
 * @Title: lisuo
 * @ProjectName bootdo
 * @Description: TODO
 * @date 2018/9/28 0028下午 8:24
 */


import com.monitor.base.PageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Administrator
 * @Title: lisuo
 * @ProjectName monitor
 * @Description: TODO
 * @date 2018/9/8 0008下午 11:28
 */
@ApiModel(description = "用户列表查询入参")
public class UserListInVo extends PageVo {
    @ApiModelProperty(value ="用户名")
    private String name;
    @ApiModelProperty(value ="用户id")
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
