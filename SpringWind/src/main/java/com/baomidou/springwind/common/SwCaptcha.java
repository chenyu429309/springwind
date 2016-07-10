package com.baomidou.springwind.common;

import java.awt.Color;
import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.framework.captcha.DefaultCaptcha;
import com.baomidou.framework.captcha.ICaptchaStore;
import com.baomidou.kisso.common.captcha.background.SingleColorBackgroundFactory;
import com.baomidou.kisso.common.captcha.color.SingleColorFactory;
import com.baomidou.kisso.common.captcha.filter.ConfigurableFilterFactory;
import com.baomidou.kisso.common.captcha.filter.library.AbstractImageOp;
import com.baomidou.kisso.common.captcha.filter.library.CurvesImageOp;
import com.baomidou.kisso.common.captcha.filter.library.WobbleImageOp;
import com.baomidou.kisso.common.captcha.filter.predefined.CurvesRippleFilterFactory;
import com.baomidou.kisso.common.captcha.service.ConfigurableCaptchaService;
import com.baomidou.kisso.common.captcha.text.renderer.RandomYBestFitTextRenderer;

/**
 * 
 * 验证码服务演示实例
 * 
 * @author hubin
 *
 */
public class SwCaptcha extends DefaultCaptcha {

	public static final String CAPTCHA_TOKEN = "ctoken";
	private static final String CAPTCHA_CACHE = "captchaCache";
	private static SwCaptcha ec = null;

	/**
	 * <p>
	 * 换掉验证码库， 继承 AbstractCaptcha 实现 writeImage 方法即可
	 * </p>
	 */
	public static SwCaptcha getInstance() {
		if (ec == null) {
			ec = new SwCaptcha();
			ec.setCaptchaService(getMyConfigurableCaptchaService());
			ec.setCaptchaStore(new ICaptchaStore() {

				@Override
				public String get(String ticket) {
					Object obj = EhcacheHelper.get(CAPTCHA_CACHE, ticket);
					if (obj != null) {
						return String.valueOf(obj);
					}
					return null;
				}

				@Override
				public boolean put(String ticket, String captcha) {
					EhcacheHelper.put(CAPTCHA_CACHE, ticket, captcha);
					return true;
				}

			});
		}
		return ec;
	}

	/**
	 * <p>
	 * 自定义图片验证码生成规则
	 * </p>
	 */
	public static ConfigurableCaptchaService getMyConfigurableCaptchaService() {
		ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
		// 验证码宽高
		cs.setWidth(200);
		cs.setHeight(70);

		// 设置 6 位自适应验证码
		// AdaptiveRandomWordFactory arw = new AdaptiveRandomWordFactory();
		// arw.setMinLength(6);
		// arw.setMaxLength(6);
		// cs.setWordFactory(arw);

		// 字符大小设置
		// RandomFontFactory rf = new RandomFontFactory();
		// rf.setMinSize(160);
		// rf.setMaxSize(120);
		// cs.setFontFactory(rf);

		// 文本渲染
		cs.setTextRenderer(new RandomYBestFitTextRenderer());

		// 设置一个单一颜色字体
		cs.setColorFactory(new SingleColorFactory(new Color(6, 111, 181)));
		cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));

		// 图片滤镜设置
		ConfigurableFilterFactory filterFactory = new ConfigurableFilterFactory();
		List<BufferedImageOp> filters = new ArrayList<BufferedImageOp>();

		// 摆动干扰
		WobbleImageOp wio = new WobbleImageOp();
		wio.setEdgeMode(AbstractImageOp.EDGE_CLAMP);
		wio.setxAmplitude(3.0);
		wio.setyAmplitude(1.0);
		filters.add(wio);

		// 曲线干扰
		CurvesImageOp cio = new CurvesImageOp();
		cio.setColorFactory(new SingleColorFactory(new Color(59, 162, 9)));
		cio.setEdgeMode(AbstractImageOp.EDGE_ZERO);
		cio.setStrokeMax(0.3f);
		cio.setStrokeMin(0.1f);
		filters.add(cio);

		filterFactory.setFilters(filters);
		cs.setFilterFactory(filterFactory);

		// 背景颜色
		cs.setBackgroundFactory(new SingleColorBackgroundFactory(new Color(250, 251, 253)));

		// 椭圆形干扰背景
		// cs.setBackgroundFactory(new OvalNoiseBackgroundFactory(7));

		// 线形干扰背景
		// cs.setBackgroundFactory(new LineNoiseBackgroundFactory(37));
		return cs;
	}
}
