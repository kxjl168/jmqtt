package com.ztgm.base.base;

/**
 * 一些常量
 */
public class SysConst {

	/**
	 * 存储当前登录用户id的字段名
	 */
	public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

	/**
	 * 存储当前登录用户其他信息字段
	 */
	public static final String CURRENT_USER = "CURRENT_USER";

	/**
	 * 存储当前登录用户id的字段名
	 */
	public static final String APPID = "APP_ID";

	/**
	 * token有效期（小时）
	 */
	public static final int TOKEN_EXPIRES_HOUR = 72;

	/**
	 * token有效期（小时）
	 */
	public static final int TOKEN_EXPIRES_DAY = 7;

	/**
	 * 存放Authorization的header字段
	 */
	public static final String AUTHORIZATION = "authorization";


	public static final Integer OPERATION_TIME = 5;	//一些更新操作动作默认缓存的秒数,用于防止重复提交
	
	/**
	 * 排序方式
	 * @author Administrator
	 *
	 */
	public enum SortBy{
		ASC,DESC;
	}
}
