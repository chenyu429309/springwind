package com.baomidou.springwind.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;

/**
 * <p>
 * 公共基础实体类
 * </p>
 * 
 * @author hubin
 */
@SuppressWarnings("serial")
public class BaseEntity implements Serializable {

	/** 主键 */
	@TableId
	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
