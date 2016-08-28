package com.baomidou.springwind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.Donate;
import com.baomidou.springwind.service.IDonateService;

/**
 * <p>
 * 捐赠管理相关操作
 * </p>
 *
 * @Author hubin
 * @Date 2016-08-20
 */
@Controller
@RequestMapping("/donate")
public class DonateController extends BaseController {

	@Autowired
	private IDonateService donateService;

	@Permission("2000")
	@RequestMapping("/list")
	public String list(Model model) {
		return "/donate/list";
	}

	/**
	 * 翻页
	 */
	@ResponseBody
	@Permission("2000")
	@RequestMapping("/jsonData")
	public String jsonData() {
		Page<Donate> page = getPage();
		return jsonPage(donateService.selectPage(page, null));
	}

	/**
	 * 添加
	 */
	@Permission("2000")
	@RequestMapping("/add")
	public String add(Model model) {

		return "/donate/edit";
	}

	/**
	 * 添加、编辑权限
	 */
	@Permission("2000")
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(Donate donate) {
		boolean rlt = false;
		if (donate != null) {
			if (donate.getId() != null) {
				rlt = donateService.updateSelectiveById(donate);
			} else {
				rlt = donateService.insertSelective(donate);
			}
		}
		return callbackSuccess(rlt);
	}

}
