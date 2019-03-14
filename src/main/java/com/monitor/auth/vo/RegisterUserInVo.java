package com.monitor.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.validation.constraints.NotEmpty;

/**
 * @Description: 用户注册入参
 * @author lisuo
 * @date 2018/9/29 0029上午 2:07
 */
@ApiModel(description = "用户注册输入")
public class RegisterUserInVo {

    @ApiModelProperty("用户名")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty("用户姓名")
    private String name;

    @ApiModelProperty("用户密码")
    @NotEmpty(message = "用户密码不能为空")
    private String password;

    /*@ApiModelProperty("邮箱")
    private String email;*/


    @ApiModelProperty("用户手机")
    @NotEmpty(message = "用户手机号不能为空")
    private String phone;

    /*@ApiModelProperty("所属公司")
    private String company;*/

    @ApiModelProperty("用户角色")
    @NotEmpty(message = "用户角色不能为空")
    private Long type;


    @ApiModelProperty("用户拥有的项目id列表")
    @NotEmpty(message = "用户拥有的项目id列表不能为空")
    private Long[] projectIds;

    @ApiModelProperty("操作类型：0： 新增 1：更新")
    @NotEmpty(message = "操作类型不能为空")
    private Integer operateType;

    public String getUsername() {
        return username;
    }

    public RegisterUserInVo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getName() {
        return name;
    }

    public RegisterUserInVo setName(String name) {
        this.name = name;
        return this;
    }


    public String getPassword() {
        return password;
    }

    public RegisterUserInVo setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public RegisterUserInVo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long[] getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(Long[] projectIds) {
        this.projectIds = projectIds;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
