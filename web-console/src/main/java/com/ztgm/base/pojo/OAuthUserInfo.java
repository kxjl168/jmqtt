/**
 * @(#)User.java  2018-11-14
 *
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.pojo;

import java.io.Serializable;




import lombok.Data;

/**
 * 
 * User.java. Oauth返回的前台用户信息
 * 
 * @author zj
 * @version 1.0.1 2018年11月8日
 * @revision zj 2018年11月8日
 * @since 1.0.1
 */

@Data
public class OAuthUserInfo     implements Serializable {
	
	
	//基础信息
	private String id;// varchar(64) NOT NULL COMMENT '主键',
	private String nickname;// varchar(100) DEFAULT NULL COMMENT '昵称',
	private String sexuality;// int(1) DEFAULT NULL COMMENT '性别(0:女 1:男)',
	
	private String headImg;// varchar(64) DEFAULT NULL COMMENT '头像相对路径，使用monodb存储base64\r\n 数据类似
	
	
	//需要权限的信息
	private String idCardNo;// varchar(64) DEFAULT NULL COMMENT '认证字段，唯一',
	private String phone;// varchar(20) DEFAULT NULL COMMENT '手机号码，认证字段，唯一',
	private String email;// varchar(128) DEFAULT NULL COMMENT '认证字段，唯一',
	private String name;// varchar(100) DEFAULT NULL COMMENT '真实姓名',
	
	private String idCardBack;// varchar(255) DEFAULT NULL COMMENT '身份证正面图片url\r\n 使用monodb存储base64\r\n 数据类似

	private String idCardFront;// varchar(255) DEFAULT NULL COMMENT '身份证正面图片url\r\n 使用monodb存储base64\r\n 数据类似
								// /getImg/user/idcard_front/id}\r\n 组合前缀',

	
	private String pass;

}