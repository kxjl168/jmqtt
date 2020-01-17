/*
 * @(#)DateUtil.java
 * @author: chenenqiang
 * @Date: 2018/12/13 11:14
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/***
 * 
 * DateUtil.java.
 * 
 * @author zj
* @version 1.0.1 2019年1月4日
* @revision zj 2019年1月4日
* @since 1.0.1
 */
public class DateUtil {


    public static Date formatterDate(String dateTime) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = formatter.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    
    /**
     * 
     * @param date
     * @param timeType Calendar.DATE 时间粒度
     * @param nums
     * @return
     * @author zj
     * @date 2019年9月6日
     */
    public static Date addByNum(Date date,int timeType,int nums)
    {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(timeType, nums);
    	return calendar.getTime();
    }
    
    

	public static String defaultFormat = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 获取当前时间串
	 * 
	 * @param format
	 *            为空，则默认yyyy-MM-dd HH:mm:ss
	 * @return
	 * @author zj
	 * @date 2015-12-25 上午9:55:49 *
	 */
	public static String getNowStr(String format) {
		if (format == null || format.equals("")) {
			format = defaultFormat;
		}
		DateFormat dateFormat = new SimpleDateFormat(format);

		Date now = new Date();

		String DateStr = dateFormat.format(now);

		return DateStr;
	}

	
	public static String getDateStr(Date date,String format) {
		if (format == null || format.equals(""))
			format = defaultFormat;
		DateFormat dateFormat = new SimpleDateFormat(format);

		Date now = date;

		String DateStr = dateFormat.format(now);

		return DateStr;
	}
	
	/**
	 * 返回时间格式
	 * @param strdate
	 * @param format
	 * @return
	 * @date 2016-3-24
	 * @author zj
	 */
	public static Date getDate(String strdate, String format) {
		if (format == null || format.equals(""))
			format = defaultFormat;
		Date d=new Date();
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			
			d = dateFormat.parse(strdate);
		} catch (ParseException e) {
			Calendar c=Calendar.getInstance();
			c.set(2000, 1, 1);
			d=c.getTime();
		}
		return d;
	}


}
