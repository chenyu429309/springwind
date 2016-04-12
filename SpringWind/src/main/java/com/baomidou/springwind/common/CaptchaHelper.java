package com.baomidou.springwind.common;

import java.awt.image.BufferedImageOp;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.kisso.common.captcha.background.LineNoiseBackgroundFactory;
import com.baomidou.kisso.common.captcha.filter.ConfigurableFilterFactory;
import com.baomidou.kisso.common.captcha.filter.library.AbstractImageOp;
import com.baomidou.kisso.common.captcha.filter.library.WobbleImageOp;
import com.baomidou.kisso.common.captcha.font.RandomFontFactory;
import com.baomidou.kisso.common.captcha.service.ConfigurableCaptchaService;
import com.baomidou.kisso.common.captcha.utils.encoder.EncoderHelper;

/**
 * 
 * 验证码辅助类
 * 
 * @author hubin
 *
 */
public class CaptchaHelper {

	private static final String SSO_CAPTCHA = "sso_captcha";


	/**
	 * <p>
	 * 判断验证码是否正确
	 * </p>
	 * @param request
	 * @param captcha
	 * 				验证码内容
	 */
	public static boolean captchaValid( HttpServletRequest request, String captcha ) {
		if ( captcha != null && captcha.equalsIgnoreCase((String) request.getSession().getAttribute(SSO_CAPTCHA)) ) {
			return true;
		}
		return false;
	}


	/**
	 * <p>
	 * 验证码图片，设置验证码内容至 session
	 * </p>
	 * @param request
	 * @param captcha
	 * 				验证码内容
	 */
	public static void outputImage( HttpServletRequest request, OutputStream out ) throws IOException {
		ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
		// 验证码宽高
		cs.setWidth(100);
		cs.setHeight(40);

		// 设置 6 位自适应验证码
		// AdaptiveRandomWordFactory arw = new AdaptiveRandomWordFactory();
		// arw.setMinLength(6);
		// arw.setMaxLength(6);
		// cs.setWordFactory(arw);

		// 字符大小设置
		RandomFontFactory rf = new RandomFontFactory();
		rf.setMinSize(27);
		rf.setMaxSize(30);
		cs.setFontFactory(rf);

		// 文本渲染
		// cs.setTextRenderer(new RandomYBestFitTextRenderer());

		// 设置一个单一颜色字体
		//cs.setColorFactory(new SingleColorFactory(new Color(11, 182, 114)));
		// cs.setFilterFactory(new
		// CurvesRippleFilterFactory(cs.getColorFactory()));

		// 图片滤镜设置
		ConfigurableFilterFactory filterFactory = new ConfigurableFilterFactory();
		List<BufferedImageOp> filters = new ArrayList<BufferedImageOp>();

		// 摆动干扰
		WobbleImageOp wio = new WobbleImageOp();
		wio.setEdgeMode(AbstractImageOp.EDGE_CLAMP);
		wio.setxAmplitude(2.0);
		wio.setyAmplitude(1.0);
		filters.add(wio);

		// 曲线干扰
		// CurvesImageOp cio = new CurvesImageOp();
		// cio.setColorFactory(new SingleColorFactory(new Color(59, 162, 9)));
		// cio.setEdgeMode(AbstractImageOp.EDGE_ZERO);
		// cio.setStrokeMax(0.3f);
		// cio.setStrokeMin(0.1f);
		// filters.add(cio);

		filterFactory.setFilters(filters);
		cs.setFilterFactory(filterFactory);

		// 椭圆形干扰背景
		// cs.setBackgroundFactory(new OvalNoiseBackgroundFactory(7));

		// 线形干扰背景
		cs.setBackgroundFactory(new LineNoiseBackgroundFactory(37));

		// 输出验证图片
		String captcha = EncoderHelper.getChallangeAndWriteImage(cs, "png", out);
		request.getSession().setAttribute(SSO_CAPTCHA, captcha);
	}
}
