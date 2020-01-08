package com.ztgm.base.controller.WebController;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import com.google.gson.Gson;
import com.ztgm.base.util.sendpost.HttpSendPost;
import com.ztgm.base.pojo.Manager;
import com.ztgm.base.pojo.OAuthUserInfo;
import com.ztgm.base.pojo.OAuthUsernamePasswordToken;
import com.ztgm.base.service.ManagerService;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 前台登录 LoginController.java.
 * 
 * @author zj
 * @version 1.0.1 2018年11月9日
 * @revision zj 2018年11月9日
 * @since 1.0.1
 */
@Controller
@RequestMapping("/ologin")
public class OAuthLoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private Environment env;

	@Autowired
	private ManagerService managerService;

	private static String randpass = "111111";// 本地初始密码

	String oauthLoginUrl="";
	/**
	 * 返回oauth web登陆地址
	 * @return
	 * @author zj
	 * @date 2019年2月21日
	 */
	private String getOauthLoginUrl() {
		//String app_id="3";
		String app_id = env.getProperty("weboauth.appid");
		String app_secret = env.getProperty("weboauth.appsecret");
		String local_ologinUrl = URLEncoder.encode( env.getProperty("weboauth.localOAuthCallbackUrl"));//本地oauth回调
		//String app_secret="3";
	//	String access_tokenurl="http://127.0.0.1:8086/account-web-oauth/oauth2/access_token";
	//	String uinfo_url="http://127.0.0.1:8086/account-web-oauth/user/show";
		
		String weboauthurl = env.getProperty("weboauth.url");
	
		String oauthurl=weboauthurl+"/oauth2/authorize";
	
		
		//http://127.0.0.1:8086/account-web-oauth/oauth2/access_token?client_id=1&client_secret=1&grant_type=authorization_code&code=dddd&redirect_uri=http%3A%2F%2Fwww.ztgmwl.com.cn
		

		oauthLoginUrl= oauthurl+"?response_type=code&client_id="+app_id+"&redirect_uri="+local_ologinUrl+"&scope=phonenum,base,email"
				;
		
		return oauthLoginUrl;
	}
	
	@RequestMapping(value = "signIn")
	public ModelAndView signIn(ModelAndView model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		//给登陆页面oauth登陆按钮使用
				request.setAttribute("oauth_loginurl", getOauthLoginUrl());
		
		// code
		String code = request.getParameter("code");

		// session.setAttribute("truepassword",password);
		String ztgmwl_id = "";
		String loginPage = "/frontend/login/login.ftl";
		try {

			// String app_id="3";
			String app_id = env.getProperty("weboauth.appid");
			String app_secret = env.getProperty("weboauth.appsecret");
			// String app_secret="3";
			// String
			// access_tokenurl="http://127.0.0.1:8086/account-web-oauth/oauth2/access_token";
			// String uinfo_url="http://127.0.0.1:8086/account-web-oauth/user/show";

			String weboauthurl = env.getProperty("weboauth.url");

			String access_tokenurl = weboauthurl + "/oauth2/access_token";
			String uinfo_url = weboauthurl + "/user/show";

			// http://127.0.0.1:8086/account-web-oauth/oauth2/access_token?client_id=1&client_secret=1&grant_type=authorization_code&code=dddd&redirect_uri=http%3A%2F%2Fwww.ztgmwl.com.cn

			String access_codeurl = access_tokenurl + "?code=" + code + "&client_id=" + app_id + "&client_secret="
					+ app_secret + "&grant_type=authorization_code";

			System.out.println(access_codeurl);
			String jStr = HttpSendPost.sendHttpData(access_codeurl, "");

			JSONObject jsobj = new JSONObject(jStr);
			if (jsobj != null) {
				String error = jsobj.optString("error_code");

				if (error != null && !error.equals("")) {
					request.setAttribute("code", "10001");
					request.setAttribute("error", "oauth授权失败");

					String str = loginPage;

					return new ModelAndView(str);
				}

				String access_token = jsobj.optString("access_token");
				if (access_token != null && !access_token.equals("")) {
					String infourl = uinfo_url + "?access_token=" + access_token;

					jStr = HttpSendPost.sendHttpData(infourl, "");

					Gson gs = new Gson();
					OAuthUserInfo oinfo = gs.fromJson(jStr, OAuthUserInfo.class);

					if (oinfo != null) {
						ztgmwl_id = oinfo.getId();
						Manager u = new Manager();
						u.setOauthUserId(oinfo.getId());
						
						u.setTelephone(oinfo.getPhone());
						Manager user = managerService.selectManagerByOauthId(u);

						if (user == null) {
							// 注册
							oinfo.setPass(randpass); // 设置通用本地密码
							Manager mnew= managerService.registerUser(oinfo);
							if(mnew==null)
							{
								request.setAttribute("code", "10001");
								request.setAttribute("error", "oauth登陆失败,请先补充oauth用户信息电话号码！");
								String str = loginPage;

								return new ModelAndView(str); 
							}
						} else {
							// 更新
							oinfo.setPass(randpass); // 设置通用本地密码
							managerService.updateUser(oinfo);
						}
					}

				}

			}
		} catch (Exception e) {
			request.setAttribute("code", "10001");
			request.setAttribute("error", "oauth授权失败");

			String str = loginPage;

			return new ModelAndView(str);
		}

		if (!ztgmwl_id.equals("")) {

			Manager query = new Manager();
			query.setOauthUserId(ztgmwl_id);
			Manager userInquire = managerService.selectManagerByOauthId(query);

			Subject subject = SecurityUtils.getSubject();
			Map map = (Map) subject.getPrincipal();
			OAuthUsernamePasswordToken token = new OAuthUsernamePasswordToken(userInquire.getUsername(), randpass);

			// 获取用户直接登陆
			subject.login(token);

			// 登陆成功

			map = (Map) subject.getPrincipal();
			Manager user = (Manager) map.get("user");
			// 存入到session，
			request.getSession().setAttribute("user", user);

			String str = "redirect:/manager/admin/index";

			request.setAttribute("list", map.get("userMenus"));

			return new ModelAndView(str);
		} else {
			request.setAttribute("code", "10001");
			request.setAttribute("error", "oauth授权失败");

			String str = loginPage;

			return new ModelAndView(str);
		}

	}

	@RequestMapping(value = "logout")
	// @ActionLog(operateModelNm = "登录访问", operateFuncNm = "query", operateDescribe
	// = "访问登录页")
	public String logout(ModelAndView model, HttpServletRequest request, String username, String password) {
		// 先从HttpSession中删除掉user对象
		request.getSession().removeAttribute("user");
		// 帮助shiro框架进行会话管理操作
		Subject subject = SecurityUtils.getSubject();
		// 退出
		subject.logout();
		return "redirect:/uindex/preview/1";
	}

	/**
	 * 页面跳转
	 * 
	 * @param model
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "basic_table")
	public String basic_table(ModelAndView model, String username, String password) {

		return "module/main/basic_table";
	}

	@RequestMapping(value = "blank")
	public String blank(ModelAndView model, String username, String password) {

		return "module/main/blank";
	}

	@RequestMapping(value = "buttons")
	public String buttons(ModelAndView model, String username, String password, HttpServletRequest request,
			HttpServletResponse response) {

		return "module/main/userManager";
	}

	@RequestMapping(value = "organ")
	public String organ(ModelAndView model, String username, String password, HttpServletRequest request,
			HttpServletResponse response) {

		return "module/main/organ";
	}

	@RequestMapping(value = "calendar")
	public String calendar(ModelAndView model, String username, String password) {

		return "module/main/calendar";
	}

	@RequestMapping(value = "chartjs")
	public String chartjs(ModelAndView model, String username, String password) {

		return "module/main/chartjs";
	}

	@RequestMapping(value = "gallery")
	public String gallery(ModelAndView model, String username, String password) {

		return "module/main/gallery";
	}

	/*
	 *
	 * 菜单页面查询结果初始化 pagesize为页面显示的数据条数
	 */
	@RequestMapping(value = "general")
	public String general(ModelAndView model, String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		return "sys/menu/menuList";
	}

	@RequestMapping(value = "lock_screen")
	public String lock_screen(ModelAndView model, String username, String password) {

		return "module/main/lock_screen";
	}

	@RequestMapping(value = "morris")
	public String mirris(ModelAndView model, String username, String password) {

		return "module/main/morris";
	}

	@RequestMapping(value = "panels")
	public String panels(ModelAndView model, String username, String password, HttpServletRequest request,
			HttpServletResponse response) {

		return "module/main/roleManager";
	}

	@RequestMapping(value = "responsive_table")
	public String responsive_table(ModelAndView model, String username, String password) {

		return "module/main/responsive_table";
	}

	@RequestMapping(value = "todo_list")
	public String todo_list(ModelAndView model, String username, String password) {

		return "module/main/todo_list";
	}

	@RequestMapping(value = "form_component")
	public String form_component(ModelAndView model, String username, String password) {

		return "module/main/form_component";
	}

}
