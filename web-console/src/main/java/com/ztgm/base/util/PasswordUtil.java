/**
 * @(#)PasswordUtil.java  2018-11-14
 *
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * shiro密码
 * PasswordUtil.java.
 * 
 * @author zj
* @version 1.0.1 2018年11月14日
* @revision zj 2018年11月14日
* @since 1.0.1
 */
public class PasswordUtil {

    public static String encrypt(String password,String telephone){
        //原始 密码
        String source = password;
        //盐
        String salt = telephone;
        //散列次数
        int hashIterations = 1;


        //构造方法中：
        //第一个参数：明文，原始密码
        //第二个参数：盐，通过使用随机数
        //第三个参数：散列的次数，比如散列两次，相当 于md5(md5(''))
        //Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);

        //String password_md5 =  md5Hash.toString();
        //System.out.println(password_md5);
        //第一个参数：散列算法
        SimpleHash simpleHash = new SimpleHash("md5", source, salt, hashIterations);
        //System.out.println(simpleHash.toString());

        return simpleHash.toString();
    }
    
    
    public static void main(String[] args) {
    	
    	 SimpleHash simpleHash =new SimpleHash("md5", "admin", "111111");
    	 System.out.println(simpleHash.toString());
    	
    	System.out.println(encrypt("111111", "admin"));
    }

}
