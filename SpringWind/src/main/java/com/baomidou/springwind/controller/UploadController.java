package com.baomidou.springwind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;

/**
 * <p>
 * 上传服务
 * </p>
 * 
 * @author hubin
 * @Date 2016-04-13
 */
@Controller
@RequestMapping("/sys/upload")
public class UploadController extends BaseController {


	/**
	 * 上传文件
	 */
	@ResponseBody
	@Permission(action = Action.Skip)
	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public String file() {
		return "/upload/avatar";
	}

}
