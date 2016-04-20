package com.baomidou.springwind.controller;


import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.springwind.common.CaptchaHelper;

/**
 * <p>
 * 验证码服务
 * </p>
 * 
 * @author hubin
 * @Date 2016-01-06
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController extends BaseController {
	
	/**
	 * 生成图片
	 */
	@ResponseBody
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping("/image")
	public void image() {
		try {
			CaptchaHelper.outputImage(request, response.getOutputStream());
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

}
