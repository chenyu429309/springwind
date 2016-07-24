package com.baomidou.springwind.common.enums;

import com.baomidou.framework.common.IEnum;

/**
 * 操作状态
 */
public enum OperateState implements IEnum {
	NORMAL(0, "正常"), DISABLE(1, "禁用");

	private final int key;
	private final String desc;

	OperateState(final int key, final String desc) {
		this.key = key;
		this.desc = desc;
	}

	@Override
	public int key() {
		return this.key;
	}

	@Override
	public String desc() {
		return this.desc;
	}

}
