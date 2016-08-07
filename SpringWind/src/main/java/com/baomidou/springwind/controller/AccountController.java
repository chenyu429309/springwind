package com.baomidou.springwind.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.framework.controller.SuperController;
import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.kisso.common.encrypt.SaltEncoder;
import com.baomidou.kisso.common.util.RandomUtil;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import com.baomidou.springwind.common.SwCaptcha;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.service.IUserService;

/**
 * <p>
 * 账户相关操作
 * </p>
 * 
 * @author hubin
 * @Date 2016-04-13
 */
@Controller
@RequestMapping("/account")
public class AccountController extends SuperController {

	@Autowired
	protected IUserService userService;

	/**
	 * 登录
	 */
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping("/login")
	public String login(Model model) {
		if (SSOHelper.getToken(request) != null) {
			// 已经登录
			return redirectTo("/index.html");
		}
		if (isPost()) {
			String errorMsg = "用户名或密码错误";
			/**
			 * 过滤 XSS SQL 注入
			 */
			WafRequestWrapper wr = new WafRequestWrapper(request);
			String ctoken = wr.getParameter("ctoken");
			String captcha = wr.getParameter("captcha");
			if (StringUtils.isNotBlank(ctoken) && StringUtils.isNotBlank(captcha)
					&& SwCaptcha.getInstance().verification(wr, ctoken, captcha)) {
				String loginName = wr.getParameter("loginName");
				String password = wr.getParameter("password");
				/**
				 * <p>
				 * 用户存在，签名合法，登录成功 <br>
				 * 进入后台
				 * </p>
				 */
				User user = userService.selectByLoginName(loginName);
				if (user != null && SaltEncoder.md5SaltValid(loginName, user.getPassword(), password)) {
					SSOToken st = new SSOToken(request);
					st.setId(user.getId());
					st.setData(loginName);

					// 记住密码，设置 cookie 时长 1 周 = 604800 秒
					String rememberMe = wr.getParameter("rememberMe");
					if ("on".equals(rememberMe)) {
						request.setAttribute(SSOConfig.SSO_COOKIE_MAXAGE, 604800);
					}

					SSOHelper.setSSOCookie(request, response, st, false);
					return redirectTo("/index.html");
				}
			} else {
				errorMsg = "验证码错误";
			}
			model.addAttribute("errorMsg", errorMsg);
		}
		model.addAttribute(SwCaptcha.CAPTCHA_TOKEN, RandomUtil.get32UUID());
		return "/login";
	}

	/**
	 * 退出
	 */
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping("/logout")
	public String logout(Model model) {
		SSOHelper.clearLogin(request, response);
		return redirectTo("/account/login.html");
	}

}
