/*
 * @(#)ByteUtil.java
 * @author: chenenqiang
 * @Date: 2018/11/29 15:24
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.util.yd;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: chenenqiang
 * @createDate: 2018/11/29 15:24
 * @description:
 */
public class ByteUtil {


    public static String addZero(String time) {
        if (time.length() < 2) {
            return "0" + time;
        } else {
            return time;
        }
    }


    /**
     * 字符串转换成为16进制
     */
    public static String strToHexStr(String s) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str.append(s4);
        }
        return str.toString();
    }

    /**
     * 16进制直接转换成为字符串
     */
    public static String hexStrToStr(String s) {
        if (s == null || "".equals(s)) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, StandardCharsets.UTF_8);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    /**
     * 数字转换成为16进制
     */
    public static String intToHexStr(Integer integer) {
        String str;
        str = Integer.toHexString(integer);
        return str;
    }

    /**
     * 16进制转换成为数字
     */
    public static Integer hexStrToInt(String str) {
        Integer integer;
        integer = Integer.parseInt(str, 16);
        return integer;
    }


    /**
     * 16进制转换成为Date
     */
    public static Date hexStrToDate(String time) {
        Date date = null;
        int cnt = hexStrToInt(time.substring(0, 2));
        int year = hexStrToInt(time.substring(2, 4));
        int year1 = hexStrToInt(time.substring(4, 6));
        int month = hexStrToInt(time.substring(6, 8));
        int day = hexStrToInt(time.substring(8, 10));
        int hour = hexStrToInt(time.substring(10, 12));
        int minute = hexStrToInt(time.substring(12, 14));
        int second = hexStrToInt(time.substring(14, 16));
        time = year1 + "" + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
