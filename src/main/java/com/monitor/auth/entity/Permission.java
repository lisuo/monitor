package com.monitor.auth.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.monitor.base.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 权限
 * @author lisuo
 * @since 2018-09-29
 */
@TableName("t_permission")
public class Permission extends BaseEntity<Permission> {

    /**
     * 资源名称
     */
	private String name;
    /**
     * 资源路径
     */
	private String uri;
    /**
     * 类型(1、目录2、菜单、3按钮)
     */
	private String type;
    /**
     * 父id
     */
	private Long pid;
	private String sort;
    /**
     * 权限值
     */
	private String value;

	public String getName() {
		return name;
	}

	public Permission setName(String name) {
		this.name = name;
		return this;
	}

	public String getUri() {
		return uri;
	}

	public Permission setUri(String uri) {
		this.uri = uri;
		return this;
	}

	public String getType() {
		return type;
	}

	public Permission setType(String type) {
		this.type = type;
		return this;
	}

	public Long getPid() {
		return pid;
	}

	public Permission setPid(Long pid) {
		this.pid = pid;
		return this;
	}

	public String getSort() {
		return sort;
	}

	public Permission setSort(String sort) {
		this.sort = sort;
		return this;
	}

	public String getValue() {
		return value;
	}

	public Permission setValue(String value) {
		this.value = value;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
