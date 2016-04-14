package com.baomidou.springwind.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.framework.controller.SuperController;
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

}
