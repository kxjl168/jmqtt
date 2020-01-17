package com.ztgm.base.interceptor;

import org.apache.commons.httpclient.HttpState;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jmqtt.web.plat.SignConstants;
import org.jmqtt.web.plat.SignConstants.SignType;
import org.jmqtt.web.plat.SignUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ztgm.base.aopAspect.NoNeedAuthorization;
import com.ztgm.base.base.SysConst;
import com.ztgm.base.util.SpringUtils;
import com.ztgm.mqtt.pojo.Procompany;
import com.ztgm.mqtt.service.ProcompanyService;

import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class InterfaceTokenInterceptor implements HandlerInterceptor {

	private static Logger logger = LogManager.getLogger(InterfaceTokenInterceptor.class);

	TokeUtils tokeUtils;

	ProcompanyService procompanyService;

	public InterfaceTokenInterceptor() {
	
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		tokeUtils = (TokeUtils) SpringUtils.getBean(TokeUtils.class);
		procompanyService = (ProcompanyService) SpringUtils.getBean(ProcompanyService.class);

		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getServletContext());
		// UserServiceImpl userService = (UserServiceImpl) ctx.getBean("userService");

		// api keyid sign 检查
		Enumeration paras = request.getParameterNames();
		Map<String, String> datas = new HashMap<>();
		while (paras.hasMoreElements()) {

			String v = (String) paras.nextElement();
			String d = (String) request.getParameter(v);

			logger.debug("*" + v + ":" + request.getParameter(v));
			datas.put(v, d);

		}

		String appid = datas.get(SignConstants.FIELD_KEY);
		if (appid == null || appid.equals("")) {
			response.setStatus(HttpStatus.SC_UNAUTHORIZED);
			return false;
		}

		// 随机参数检查

		//

		// appid检查，key密钥获取
		Procompany company = procompanyService.selectProcompanyByAppId(appid);
		if (company == null) {
			response.setStatus(HttpStatus.SC_UNAUTHORIZED);
			return false;
		}
		String key = company.getSecret();

		String signType = datas.get(SignConstants.FIELD_SIGN_TYPE);
		SignType stype = SignType.MD5;
		if (signType == null || signType.equals("")) {

		} else {
			if (signType.equals(SignConstants.HMACSHA256))
				stype = SignType.HMACSHA256;

		}

		boolean isValida = SignUtil.isSignatureValid(datas, key, stype);
		if (!isValida) {
			response.setStatus(HttpStatus.SC_UNAUTHORIZED);
			return false;
		}
		// end

		// web端 token验证
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		if (method.getAnnotation(NoNeedAuthorization.class) != null) {
			// 如果验证token失败，但是方法注明了NoNeedAuthorization，正常请求
			// response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return true;
		}

		String token = request.getHeader(SysConst.AUTHORIZATION);
		if (token != null && !token.equals("")) {
			tokeUtils = (TokeUtils) SpringUtils.getBean(TokeUtils.class);

			boolean isExpire = tokeUtils.checkToken(token);
			if (isExpire) {
				response.setStatus(HttpStatus.SC_UNAUTHORIZED);
				return false;
			} else {

			}

		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {

	}
}
