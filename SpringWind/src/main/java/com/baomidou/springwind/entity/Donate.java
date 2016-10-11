package com.baomidou.springwind.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableField;

/**
 *
 * 捐赠表
 *
 */
public class Donate extends BaseEntity {

	@TableField(exist = false)
	protected static final long serialVersionUID = 1L;

	/** 捐赠者 */
	protected String donator;

	/** 类型： 0、wechat 1、alipay */
	protected Integer origin;

	/** 捐赠金额 */
	protected Integer amount;

	/** 留言 */
	protected String message;

	/** 捐赠时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date crTime;

	public String getDonator() {
		return this.donator;
	}

	public void setDonator(String donator) {
		this.donator = donator;
	}

	public Integer getOrigin() {
		return this.origin;
	}

	public void setOrigin(Integer origin) {
		this.origin = origin;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCrTime() {
		return this.crTime;
	}

	public void setCrTime(Date crTime) {
		this.crTime = crTime;
	}

}
