package com.baomidou.springwind.common.aspect;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.Token;
import com.baomidou.kisso.common.IpHelper;
import com.baomidou.springwind.common.HttpHelper;
import com.baomidou.springwind.entity.SysLog;
import com.baomidou.springwind.service.ISysLogService;

/**
 * 
 * <p>
 * 系统日志AOP
 * </p>
 * 
 * @author hubin
 *
 */
public class LogAspect {

	protected Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

	private static final String LOG_CONTENT = "[类名]:%s,[方法]:%s,[参数]:%s,[IP]:%s";

	@Autowired
	private ISysLogService sysLogService;


	/**
	 * 保存系统操作日志
	 *
	 * @param joinPoint 连接点
	 * @return 方法执行结果
	 * @throws Throwable 调用出错
	 */
	@Around(value = "@annotation(com.lyh.systemlog.annotation.Log)")
	public Object saveLog( ProceedingJoinPoint joinPoint ) throws Throwable {
		/**
		 * 解析Log注解
		 */
		String methodName = joinPoint.getSignature().getName();
		Method method = currentMethod(joinPoint, methodName);
		Log systemLogAnnotation = method.getAnnotation(Log.class);

		/**
		 * 日志入库  
		 */
		HttpServletRequest request = HttpHelper.getHttpServletRequest();
		SysLog sl = new SysLog();
		Token tk = SSOHelper.attrToken(request);
		if ( tk != null ) {
			sl.setUid(tk.getId());
		}
		sl.setContent(operateContent(joinPoint, methodName, request));
		LOGGER.debug(sl.getContent());
		sl.setOperation(systemLogAnnotation.value());
		sl.setCrtime(new Date());
		sysLogService.insert(sl);

		/**
		 * 方法执行
		 */
		return joinPoint.proceed();
	}


	/**
	 * 获取当前执行的方法
	 *
	 * @param joinPoint
	 * 					连接点
	 * @param methodName
	 * 					方法名称
	 * @return 方法
	 */
	private Method currentMethod( ProceedingJoinPoint joinPoint, String methodName ) {
		/**
		 * 获取目标类的所有方法，找到当前要执行的方法
		 */
		Method[] methods = joinPoint.getTarget().getClass().getMethods();
		Method resultMethod = null;
		for ( Method method : methods ) {
			if ( method.getName().equals(methodName) ) {
				resultMethod = method;
				break;
			}
		}
		return resultMethod;
	}


	/**
	 * 获取当前执行的方法
	 *
	 * @param joinPoint
	 * 					连接点
	 * @param methodName
	 * 					方法名称
	 * @return 操作内容
	 */
	@SuppressWarnings("unchecked")
	public String operateContent( ProceedingJoinPoint joinPoint, String methodName, HttpServletRequest request )
		throws Throwable {
		String className = joinPoint.getTarget().getClass().getName();
		Object[] params = joinPoint.getArgs();
		StringBuffer bf = new StringBuffer();
		if ( params != null && params.length > 0 ) {
			Enumeration<String> paraNames = request.getParameterNames();
			while ( paraNames.hasMoreElements() ) {
				String key = paraNames.nextElement();
				bf.append(key).append("=");
				bf.append(request.getParameter(key)).append("&");
			}
			if ( StringUtils.isBlank(bf.toString()) ) {
				bf.append(request.getQueryString());
			}
		}
		return String.format(LOG_CONTENT, className, methodName, bf.toString(), IpHelper.getIpAddr(request));
	}

}
