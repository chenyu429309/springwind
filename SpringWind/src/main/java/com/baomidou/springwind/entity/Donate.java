package com.baomidou.springwind.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 *
 * 捐赠表
 *
 */
public class Donate implements Serializable {

	@TableField(exist = false)
	protected static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId
	protected Long id;

	/** 捐赠者 */
	protected String donator;

	/** 类型： 0、wechat 1、alipay */
	protected Integer origin;

	/** 捐赠金额 */
	protected Integer amount;

	/**  */
	protected String message;

	/** 捐赠时间 */
	protected Date crTime;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
