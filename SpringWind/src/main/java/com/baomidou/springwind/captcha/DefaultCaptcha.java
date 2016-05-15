package com.baomidou.springwind.captcha;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.kisso.common.captcha.service.AbstractCaptchaService;
import com.baomidou.kisso.common.captcha.service.ConfigurableCaptchaService;
import com.baomidou.kisso.common.captcha.utils.encoder.EncoderHelper;

/**
 * <p>
 * 默认图片验证码实现
 * </p>
 * 
 * @author hubin
 * @Date 2016-05-15
 */
public class DefaultCaptcha extends AbstractCaptcha {

	/*
	 * 抽象验证码服务
	 */
	private AbstractCaptchaService captchaService;

	/*
	 * 图片格式，默认 png
	 */
	private String format = "png";

	@Override
	public String writeImage(HttpServletRequest request, OutputStream out) {
		if (captchaService == null) {
			captchaService = new ConfigurableCaptchaService();
		}
		try {
			return EncoderHelper.getChallangeAndWriteImage(captchaService, format, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public AbstractCaptchaService getCaptchaService() {
		return captchaService;
	}

	public void setCaptchaService(AbstractCaptchaService captchaService) {
		this.captchaService = captchaService;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
