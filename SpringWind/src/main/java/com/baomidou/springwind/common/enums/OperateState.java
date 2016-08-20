package com.baomidou.springwind.common.enums;

import com.baomidou.framework.common.IEnum;

/**
 * 操作状态
 */
public enum OperateState implements IEnum {
	DISABLE(0, "禁用"), ENABLE(1, "正常");

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
