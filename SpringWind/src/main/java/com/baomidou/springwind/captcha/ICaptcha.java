package com.baomidou.springwind.captcha;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 图片验证码接口
 * </p>
 * 
 * @author hubin
 * @Date 2016-05-15
 */
public interface ICaptcha {

	/**
	 * <p>
	 * 生成图片验证码
	 * </p>
	 * 
	 * @param request
	 * @param out
	 *            输出流
	 * @param ticket
	 *            验证码票据
	 * @return String 验证码内容
	 */
	void generate(HttpServletRequest request, OutputStream out, String ticket);

	/**
	 * <p>
	 * 判断验证码是否正确
	 * </p>
	 * 
	 * @param request
	 * @param ticket
	 *            验证码票据
	 * @param captcha
	 *            验证码内容
	 * @return boolean true 正确，false 错误
	 */
	boolean verification(HttpServletRequest request, String ticket, String captcha);

}
