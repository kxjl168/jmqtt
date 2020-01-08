package com.ztgm.base.controller.WebController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manager")
public class AdminController {

    //登录后  首页
    @RequestMapping("/admin/index")
    public String admin(Map<String, Object> map, HttpServletRequest request) {
        Map principal = (Map) request.getAttribute("principal");
        
        if(principal==null)
        	return "redirect:/login/signIn";
        
        String userId = (String) principal.get("userId");
  
        
  
        boolean isAdmin = false;
        List<Map> roleList = (List<Map>) principal.get("roles");
        for (Map role : roleList) {
            if (role.get("sys_role_id").equals("1")) {   // 系统管理员
                isAdmin = true;
                break;
            }
        }

        return "/backend/admin/welcome(new).ftl";
    }

   


}
