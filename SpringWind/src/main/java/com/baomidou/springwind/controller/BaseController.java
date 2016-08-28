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
	protected MailHelper mailHelper;

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
		jo.put("data", page.getRecords());
		jo.put("itemsCount", page.getTotal());
		return toJson(jo);
	}

	@Override
	protected <T> Page<T> getPage(int size) {
		int _size = size, _index = 1;
		if (request.getParameter("limit") != null) {
			_size = Integer.parseInt(request.getParameter("limit"));
		}
		if (request.getParameter("start") != null) {
			int _offset = Integer.parseInt(request.getParameter("start"));
			_index = _offset / _size + 1;
		}
		return new Page<T>(_index, _size);
	}

	protected String callbackResult(boolean reslut) {
		if (reslut) {
			return callbackSuccess(null);
		}
		return callbackFail(null);
	}

}
