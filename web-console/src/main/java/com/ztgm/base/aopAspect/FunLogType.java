/*
 * @(#)FunLogType.java
 * @author: zhangJ
 * @Date: 2018/12/11 09:02
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.aopAspect;

/**
 * 日志类型
 * FunLogType.java.
 * 
 * @author zj
* @version 1.0.1 2018年12月15日
* @revision zj 2018年12月15日
* @since 1.0.1
 */
public enum FunLogType {

	Add("add","新增"),Update("update","修改"),SaveOrUpdate("saveOrUpdate","保存或者修改"),Del("del","删除"),Query("query","查询");
	
	
	private String name;
	private String desc;
	private FunLogType(String name,String desc)
	{
		this.name=name;
		this.desc=desc;
	}
}
