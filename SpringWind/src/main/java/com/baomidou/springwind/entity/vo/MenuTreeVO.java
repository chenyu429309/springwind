package com.baomidou.springwind.entity.vo;

import java.io.Serializable;

public class MenuTreeVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private Long id;

	/** 上级ID */
	private Long pId;

	/** 标题 */
	private String name;

	/** 树是否打开 */
	private boolean open;

	public MenuTreeVO() {

	}

	public MenuTreeVO(Long id, Long pId, String name, boolean open) {
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = open;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

}
