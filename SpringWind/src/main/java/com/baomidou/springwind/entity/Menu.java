package com.baomidou.springwind.entity;

/**
 * 权限表对应实体
 */
public class Menu {
	/** 主键 */
	private Long id;

	/** 标题 */
	private String text;

	/** 地址 */
	private String href;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}
