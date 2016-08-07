package com.baomidou.springwind.entity.vo;

import java.util.List;

public class MenuTreeVO {

	private String id;

	/*
	 * 一级菜单欢迎地址
	 */
	private String homePage;

	private List<MenuVO> menu;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public List<MenuVO> getMenu() {
		return menu;
	}

	public void setMenu(List<MenuVO> menu) {
		this.menu = menu;
	}

}
