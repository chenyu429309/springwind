package com.baomidou.springwind.entity.vo;

import java.util.List;

public class MenuTreeVO {

	private Long id;

	private List<MenuVO> menu;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<MenuVO> getMenu() {
		return menu;
	}

	public void setMenu(List<MenuVO> menu) {
		this.menu = menu;
	}

}
