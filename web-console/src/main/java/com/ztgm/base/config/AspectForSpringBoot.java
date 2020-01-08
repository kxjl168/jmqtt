/*
 * @(#)AspectForSpringBoot.java
 * @author: zhangJ
 * @Date: 2018/12/15 09:02
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.config;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.ztgm.base.aopAspect.FunLogType;
import com.ztgm.base.aopAspect.ManagerActionLog;
import com.ztgm.base.pojo.Manager;
import com.ztgm.base.pojo.SysOperLog;
import com.ztgm.base.service.SysLogService;
import com.ztgm.base.util.IPUtils;
import com.ztgm.base.util.SpringUtils;

/**
 * aop日志记录<br>
 * 方法传递的核心map或者bean需要放到方法的第一位<br>
 * AspectForSpringBoot.java.<br>
 * 
 * @author zj
* @version 1.0.1 2018年12月15日
* @revision zj 2018年12月15日
* @since 1.0.1
 */
@Aspect
@Component
@Configuration
public class AspectForSpringBoot {

	private final String POINT_CUT = "execution(* com.ztgm.*.controller..*.*(..))";

	@Pointcut(POINT_CUT)
	public void pointCut() {

	}

	@Before("pointCut() && @annotation(logAnnotation)")
	public void before(JoinPoint point, ManagerActionLog logAnnotation) {

		SaveActionLog(point,logAnnotation);
	}
	
	Logger logger=LoggerFactory.getLogger(AspectForSpringBoot.class);

	@Autowired
	private SysLogService operlogService;

	public void SaveActionLog(JoinPoint point, ManagerActionLog logAnnotation) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		HttpSession session = request.getSession();
		Object[] ars = point.getArgs();
		String userIp = "";
		userIp = IPUtils.getIpAddr(request);

		Gson gs = new Gson();
		String dataStr = "";
		String newData = "";

		String id = "";// id

		try {

			// Method md = mi.getMethod();
			// Class cls = md.getDeclaringClass();

			String operateFunName = point.getSignature().getDeclaringTypeName()+"."+point.getSignature().getName();// md.getName();
			// String operateClassName = cls.getName();

			// ManagerActionLog logAnnotation = md.getAnnotation(ManagerActionLog.class);

			Class mapperClass = logAnnotation.operateModelClassName();

			String operateModelClassNm = logAnnotation.operateModelClassName().getSimpleName(); // 业务功能
			FunLogType operateFuncType = logAnnotation.operateFuncType(); // 业务方法
			String operateFuncTypeNm = operateFuncType.name();
			String operateDescribe = logAnnotation.operateDescribe(); // 操作描述

			// 检查第一个参数，获取id或者bean对象.
			// 使用此注解，需要将核心请求参数作为方法的第一个参数

			if (operateFuncType == FunLogType.Del) {

				if (ars[0] instanceof Map)
					id = String.valueOf(((Map) ars[0]).get("id"));// 当参数类型为map情况下 处理方式
				else if (ars[0] instanceof HttpServletRequest) {
					id = String.valueOf(((HttpServletRequest) ars[0]).getParameter("id"));
				} else
					id = String.valueOf(ars[0].getClass().getMethod("getId").invoke(ars[0]));// 当参数类型为bean情况下

				// 调用Mapper的selectByPrimaryKey 获取数据
				Object data = mapperClass.getDeclaredMethod("selectByPrimaryKey",String.class).invoke(SpringUtils.getBean(mapperClass),id);

				dataStr = gs.toJson(data);

			} else if (operateFuncType == FunLogType.Update||operateFuncType == FunLogType.SaveOrUpdate) {// 要求调用的修改方法，被输出数据参数对象必须在第一位
				if (ars[0] instanceof Map) {
					id = String.valueOf(((Map) ars[0]).get("id"));// 当参数类型为map情况下 处理方式
					newData = gs.toJson(ars[0]);
				} else if (ars[0] instanceof HttpServletRequest) {
					id = String.valueOf(((HttpServletRequest) ars[0]).getParameter("id"));

					Map mdata = new HashMap<>();
					Map map = request.getParameterMap();
					Set keSet = map.entrySet();
					for (Iterator itr = keSet.iterator(); itr.hasNext();) {
						Map.Entry me = (Map.Entry) itr.next();
						Object ok = me.getKey();
						Object ov = me.getValue();
						mdata.put(ok, ov);
					}
					newData = gs.toJson(mdata);

				} else {
					id = String.valueOf(ars[0].getClass().getMethod("getId").invoke(ars[0]));// 当参数类型为bean情况下
					newData = gs.toJson(ars[0]);
				}
				
				Object data ="";
				if(id!=null&&!id.equals("null"))
				{
					 data = mapperClass.getDeclaredMethod("selectByPrimaryKey",String.class).invoke(SpringUtils.getBean(mapperClass),id);	
				}
				// 调用Mapper的selectByPrimaryKey 获取数据
				
				// 修改前数据.
				dataStr = gs.toJson(data);

				dataStr += "  --> " + newData;

			} else if (operateFuncType == FunLogType.Add) {
				newData = gs.toJson(ars[0]);

				dataStr = newData;
			} else {
				return ;

			}

			String opertype = operateFuncType.name();
			// 获取用户登录信息

			Map principal = (Map) request.getAttribute("principal");

			if (principal != null) {

				Manager user = (Manager) principal.get("user");
				String userId = user.getId();
				String userName = (String) principal.get("userName");
				String userLoginName = user.getTelephone();

				// 存储操作日志 ------差人员信息等
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				SysOperLog operLog = new SysOperLog();
				operLog.setId(uuid);
				operLog.setUserId(userId);

				operLog.setDisplayName(userName);
				operLog.setIp(userIp);
				operLog.setLoginName(userLoginName);

				operLog.setFunName(operateFunName);
				operLog.setOperType(operateFuncTypeNm);
				operLog.setBeanClassName(operateModelClassNm.replace("Mapper", ""));
				operLog.setOperDesc(operateDescribe);

				operLog.setData(dataStr);

				operlogService.saveSysOperLog(operLog);
			}
		} catch (Exception e) {
			logger.error("logerror",e);
		}
		//

	}

}
