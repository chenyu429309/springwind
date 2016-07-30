package com.baomidou.springwind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;

/**
 * <p>
 * 图表演示（ http://echarts.baidu.com/echarts2/ ）
 * </p>
 * 
 * @author hubin
 * @Date 2016-05-11
 */
@Controller
@RequestMapping("/echarts")
public class EchartsController extends BaseController {

	/**
	 * 折线图
	 */
	@Permission(action = Action.Skip)
	@RequestMapping("/line")
	public String line(Model model) {
		return "/echarts/line";
	}
	
	/**
	 * 散列图
	 */
	@Permission(action = Action.Skip)
	@RequestMapping("/scatter")
	public String scatter(Model model) {
		return "/echarts/scatter";
	}
	
	/**
	 * 散列图
	 */
	@Permission(action = Action.Skip)
	@RequestMapping("/radar")
	public String radar(Model model) {
		return "/echarts/radar";
	}

}
