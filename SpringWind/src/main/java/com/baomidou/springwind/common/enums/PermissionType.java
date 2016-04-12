package com.baomidou.springwind.common.enums;

public enum PermissionType {
	MENU(0, "菜单"), OPERATION(1, "功能");

	private final int value;
	private final String desc;

	PermissionType(final int value, final String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}

}
