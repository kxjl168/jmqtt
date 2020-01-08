package com.ztgm.base.pojo;

import org.apache.shiro.authc.UsernamePasswordToken;

public class OAuthUsernamePasswordToken extends UsernamePasswordToken {

	public OAuthUsernamePasswordToken(String id,String pass)
	{
		super(id, pass);
	}
	
}
