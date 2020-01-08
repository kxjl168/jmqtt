/**
 * @(#)ManagerActionLog.java  2018-11-14
 *
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.aopAspect;

import java.lang.annotation.Documented;  
import java.lang.annotation.ElementType;  
import java.lang.annotation.Inherited;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  

/**
 * 后台操作日志 
 * ManagerActionLog.java.
 * 
 * @author zj
* @version 1.0.1 2018年11月9日
* @revision zj 2018年11月9日
* @since 1.0.1
 */
@Target(ElementType.METHOD)  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
@Inherited
/**
 * 方法传递的核心map或者bean需要放到方法的第一位
 * ManagerActionLog.java.
 * 
 * @author zj
* @version 1.0.1 2018年12月15日
* @revision zj 2018年12月15日
* @since 1.0.1
 */
public @interface ManagerActionLog {  
	
	
	
	

	/** 
	 * 操作的model,对应的mappder的class
	 * @return 
	 */  
	Class operateModelClassName();
	
	/**
	 * 操作类型，
	 * @return
	 * @author zj
	 * @date 2018年12月15日
	 */
	FunLogType operateFuncType(); 
	
	/**
	 * 描述
	 * @return
	 * @author zj
	 * @date 2018年12月15日
	 */
	String operateDescribe(); 
	


}
