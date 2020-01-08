/**
 * @(#)ErrorCode.java  2018-11-14
 *
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.base;

/**
 * 通用错误
 * ErrorCode.java.
 * 
 * @author zj
* @version 1.0.1 2018年11月16日
* @revision zj 2018年11月16日
* @since 1.0.1
 */
public enum ZtgmErrorCode {

	/**
	 * 用户密码错误
	 */
	NameOrPassError("0001", "用户或密码错误"),
	
	/**
	 * 用户或密码为空
	 */
	NameOrPassNull("0002", "用户或密码为空"),
	/**
	 * 用户不存在
	 */
	NoNameExist("0003", "用户不存在"),
	/**
	 * 验证码错误
	 */
	ValideCodeError("0004", "验证码错误"),
	

	   
	
	
	
	NoClientID("1001", "请求参数缺失(client_id)不合法请求"),
	InvalidateClientIDorInvalidateCSecret("1002", "client_id或client_secret参数无效"),
	
	InvalidateAccessCode("2001","code参数无效"),
	NoClientSecrect("2002", "请求参数缺失(client_secret)不合法请求"),
	InvalidateGrantType("2003","请求类型错误"),
	UnSameRedirectUrl("2004","请求回调地址与注册回调地址不符"),
	UnSurpportedGrantTypee("2005","不支持的 GrantType"),
	
	InvalidateToken("3001","token已失效或错误"),
	

	
	UnKnowError("5001","发生未知异常"),
	;

	public String code;
	public String msg;

	private ZtgmErrorCode(String cd, String info) {
		code = cd;
		msg = info;
	}

	

}
