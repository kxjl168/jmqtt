/**
 * @(#)SpringUtils.java  2018-11-14
 *
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * 
 * SpringUtils.java.
 * 
 * @author zj
* @version 1.0.1 2018年11月14日
* @revision zj 2018年11月14日
* @since 1.0.1
 */
@Component
public class SpringUtils implements ApplicationContextAware {

	private static ApplicationContext context;

	private SpringUtils() {}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		SpringUtils.context = context;
	}

	public static <T> T getBean(Class<T> clazz){
		if (clazz == null) return null;
		return context.getBean(clazz);
	}

	public static <T> T getBean(String beanName, Class<T> clazz) {
		if (null == beanName || "".equals(beanName.trim())) {
			return null;
		}
		if (clazz == null) return null;
		return (T) context.getBean(beanName, clazz);
	}

	public static ApplicationContext getContext(){
		if (context == null) return null;
		return context;
	}

	public static void publishEvent(ApplicationEvent event) {
		if (context == null) return;
		context.publishEvent(event);
	}

}
