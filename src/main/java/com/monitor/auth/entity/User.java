package com.monitor.auth.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.monitor.base.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 用户
 * @author lisuo
 * @since 2018-09-28
 */
@TableName("t_user")
public class User extends BaseEntity<User> {

    /**
     * 用户名
     */
	private String username;
    /**
     * 用户姓名
     */
	private String name;

	/**
	 * 用户所拥有的项目
	 */
	private String projectNames;
    /**
     * 账号是否禁用： 0 ：未禁用， 1： 禁用
     */
	private String enable;

	/**
	 * 用户盐值
	 */
	private String salt;
    /**
     * 密码
     */
	private String password;
	/**
	 * 密码明文
	 */
	private String ps;
    /**
     * 邮箱
     */
	private String email;

    /**
     * 手机号
     */
	private String phone;
    /**
     * 所属企业
     */
	private String company;
    /**
     * 描叙
     */
	private String desc;

	/**
	 * 用户类型
	 */
	private Long type;

	public String getUsername() {
		return username;
	}

	public User setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getName() {
		return name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

	public String getEnable() {
		return enable;
	}

	public User setEnable(String enable) {
		this.enable = enable;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public User setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getCompany() {
		return company;
	}

	public User setCompany(String company) {
		this.company = company;
		return this;
	}

	public String getDesc() {
		return desc;
	}

	public User setDesc(String desc) {
		this.desc = desc;
		return this;
	}

	public String getSalt() {
		return salt;
	}

	public String getProjectNames() {
		return projectNames;
	}

	public void setProjectNames(String projectNames) {
		this.projectNames = projectNames;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
