package com.baomidou.springwind.entity;

/**
 * 权限表对应实体
 */
public class Menu {
	/** 主键 */
	private Long tid;

	/** 菜单ID */
	private String id;

	/** 标题 */
	private String text;

	/** 地址 */
	private String href;
	/** 菜单是否可关闭 0、否 1、是 */
	private boolean closeable;

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public boolean isCloseable() {
		return closeable;
	}

	public void setCloseable(boolean closeable) {
		this.closeable = closeable;
	}

}
