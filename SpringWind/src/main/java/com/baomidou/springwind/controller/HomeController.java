package com.baomidou.springwind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 首页
 * </p>
 * 
 * @author hubin
 * @Date 2016-04-13
 */
@Controller
public class HomeController extends BaseController {

	/**
	 * 首页
	 */
	@RequestMapping("/index")
	public String index( Model model ) {
		
		return "/index";
	}
}
