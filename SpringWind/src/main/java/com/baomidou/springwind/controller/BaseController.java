package com.baomidou.springwind.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.framework.controller.SuperController;
import com.baomidou.framework.mail.MailHelper;
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
	
	@Autowired
	protected MailHelper mailHelper;
	

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
	
	@Override
	protected <T> Page<T> getPage(int size) {
		int _size = size, _index = 1;
		if (request.getParameter("_size") != null) {
			_size = Integer.parseInt(request.getParameter("_size"));
		}
		if (request.getParameter("_index") != null) {
			int _offset = Integer.parseInt(request.getParameter("_index"));
			_index = _offset / _size + 1;
		}
		return new Page<T>(_index, _size);
	}
}
