package com.baomidou.springwind.entity.vo;

import java.util.List;

import com.baomidou.springwind.entity.Menu;

public class MenuVO {

	private String text;

	private List<Menu> items;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Menu> getItems() {
		return items;
	}

	public void setItems(List<Menu> items) {
		this.items = items;
	}

}
