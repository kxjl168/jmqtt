/**
 * @(#)Manager.java  2018-11-14
 *
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ztgm.base.pojo;


import java.io.Serializable;
import java.util.Date;

import com.ztgm.base.util.PasswordUtil;

/**
 * 后台登陆人员
 * @author zj
 * @date 2018年5月29日
 *
 */

public class Manager implements Serializable {
   
    public static final String Role_ID_Sys="1";
    public static final String Role_ID_ProjOper="2";
    public static final String Role_ID_Supper="3";
    public static final String Role_ID_Common="4";
    
    private String id;

    private String username;

    private String password;

    private String telephone;

    private Integer telephoneVerified;

    private String token;

    private String time;

    private String verifyKey;

    private Date createDate;

    private String creater;

    private byte[] headImg;

    private String headImgUrl;

    private String  role;

    private String  nickname;
    

    private String  userRoleId;
    
    private String icon;
    
    private String oauthUserId;//oauthid
    
    //query
    private String userRole;
    
    private String key;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {

        return password;
    }

    /**
     *设置密码并对密码进行加密，加密使用 用户id 做盐所以必须先设置手机号码再设置密码值
     * @param password 密码
     */
    public void stPasswordAndEncrype(String password) {
        if(null!=password && null!=this.getTelephone()){
            this.password = PasswordUtil.encrypt(password,this.getUsername());
        }else{
            throw new RuntimeException("设置密码必须先设置用户登陆id");
        }
        //this.password = password == null ? null : password.trim();
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Integer getTelephoneVerified() {
        return telephoneVerified;
    }

    public void setTelephoneVerified(Integer telephoneVerified) {
        this.telephoneVerified = telephoneVerified;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getVerifyKey() {
        return verifyKey;
    }

    public void setVerifyKey(String verifyKey) {
        this.verifyKey = verifyKey == null ? null : verifyKey.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public byte[] getHeadImg() {
        return headImg;
    }

    public void setHeadImg(byte[] headImg) {
        this.headImg = headImg;
    }
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getOauthUserId() {
		return oauthUserId;
	}

	public void setOauthUserId(String oauthUserId) {
		this.oauthUserId = oauthUserId;
	}
}