package com.ztgm.base.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ztgm.base.pojo.Manager;

@Controller
public class Redirecter {
	@RequestMapping("/")
	public String redirect(HttpServletRequest request) {

		// User userInquire = userService.queryPasswordByUsername(username);

		Map principal = (Map) request.getAttribute("principal");
		if(principal==null)
			return "redirect:/login.action";
		String loginUserId = String.valueOf(principal.get("userId"));
		if (loginUserId != null) {
			return "redirect:/manager/admin/index";
		} else {
			return "redirect:/login.action";
		}

	}
}
