package com.monitor.auth.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.monitor.base.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 验证码
 * @author lisuo
 * @since 2018-09-28
 */
@TableName("t_kaptcha")
public class Kaptcha extends BaseEntity<Kaptcha> {

    /**
     * 正确的验证码
     */
	@TableField("right_code")
	private String rightCode;
    /**
     * 客户端ip
     */
	@TableField("address_ip")
	private String addressIp;


	public String getRightCode() {
		return rightCode;
	}

	public Kaptcha setRightCode(String rightCode) {
		this.rightCode = rightCode;
		return this;
	}

	public String getAddressIp() {
		return addressIp;
	}

	public Kaptcha setAddressIp(String addressIp) {
		this.addressIp = addressIp;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
