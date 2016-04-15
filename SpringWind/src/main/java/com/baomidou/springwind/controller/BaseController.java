package com.baomidou.springwind.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.framework.controller.SuperController;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.service.IUserService;

/**
 * <p>
 * 基础控制器
 * </p>
 * 
 * @author hubin
 * @Date 2016-04-13
 */
public class BaseController extends SuperController {

	@Autowired
	protected IUserService userService;

	/**
	 * <p>
	 * 转换为 bootstrap-table 需要的分页格式 JSON
	 * </p>
	 * 
	 * @param page
	 *            分页对象
	 * @return
	 */
	protected String jsonPage(Page<?> page) {
		JSONObject jo = new JSONObject();
		jo.put("total", page.getTotal());
		jo.put("rows", page.getRecords());
		return toJson(jo);
	}
}
