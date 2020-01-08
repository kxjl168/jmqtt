package com.ztgm.base.shiro;


import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import com.ztgm.base.pojo.OAuthUsernamePasswordToken;
import com.ztgm.base.util.PasswordUtil;




/**
 * 密码比较器
 * 
 * @author Administrator
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

	/**
	 * 自己来编写密码比较方法
	 * 
	 * token 从JSP页面传过来的用户名和密码 info 从数据库中查询到密码
	 */
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		
		try {
			//兼容oauth登陆
			// oauth过来的直接认证通过
			OAuthUsernamePasswordToken otoken = (OAuthUsernamePasswordToken) token;
			if (otoken != null)
				return true;
		} catch (Exception e) {

		}
		
		// 获取到JSP页面的用户名和密码
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		// 提供了方法
		String username = upToken.getUsername();
		String password = new String(upToken.getPassword());
		// password没有加密
		// 对象password加密
		//String formPwd =password;//页面已经计算md5
		String formPwd = PasswordUtil. encrypt(password, username);
		// 获取到数据库中的密码
		String dbPwd = (String) info.getCredentials();
		// 对密码进行对比
		return super.equals(formPwd, dbPwd);
	}

}
