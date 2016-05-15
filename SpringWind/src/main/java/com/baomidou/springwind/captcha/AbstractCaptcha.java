package com.baomidou.springwind.captcha;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 抽象图片验证码接口
 * </p>
 * 
 * @author hubin
 * @Date 2016-05-15
 */
public abstract class AbstractCaptcha implements ICaptcha {

	/*
	 * 图片验证码票据存储接口
	 */
	private ICaptchaStore captchaStore;

	/*
	 * 是否忽略验证内容大小写，默认 true
	 */
	private boolean ignoreCase = true;

	protected AbstractCaptcha() {
		/* 保护 */
	}

	public AbstractCaptcha(ICaptchaStore captchaStore) {
		this.captchaStore = captchaStore;
	}

	/**
	 * <p>
	 * 生成图片验证码
	 * </p>
	 * 
	 * @param request
	 * @param out
	 *            输出流
	 * @return String 验证码内容
	 */
	public abstract String writeImage(HttpServletRequest request, OutputStream out);

	@Override
	public void generate(HttpServletRequest request, OutputStream out, String ticket) {
		String captcha = writeImage(request, out);
		if (captcha != null) {
			captchaStore.put(ticket, captcha);
		}
	}

	@Override
	public boolean verification(HttpServletRequest request, String ticket, String captcha) {
		String tc = captchaStore.get(ticket);
		if (tc != null) {
			if (ignoreCase) {
				return tc.equalsIgnoreCase(captcha);
			} else {
				return tc.equals(captcha);
			}
		}
		return false;
	}

	public ICaptchaStore getCaptchaStore() {
		return captchaStore;
	}

	public void setCaptchaStore(ICaptchaStore captchaStore) {
		this.captchaStore = captchaStore;
	}

	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

}
