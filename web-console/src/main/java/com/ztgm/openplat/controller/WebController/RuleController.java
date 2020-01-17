/*
 * @(#)RuleController.java
 * @author: zhangJ
 * @Date: 2020-01-10 10:29:09
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.openplat.controller.WebController;


import com.github.pagehelper.Page;
import com.ztgm.base.aopAspect.FunLogType;
import com.ztgm.base.aopAspect.ManagerActionLog;
import com.ztgm.base.base.PageCondition;
import com.ztgm.base.util.Message;
import com.ztgm.base.util.PageUtil;
import com.ztgm.openplat.dao.RuleMapper;
import com.ztgm.openplat.pojo.Rule;
import com.ztgm.openplat.service.RuleService;

import net.sf.json.JSONObject;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 规则管理 RuleController.java.
 * 
 * @author KAutoGenerator
 * @version 1.0.1 2020-01-10 10:29:09
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager/rule")
public class RuleController {
	@Autowired
	private RuleService ruleService;


	@RequestMapping("/manager")
	public String manager(Model model) {

		return "/backend/page/rule/index.ftl";
	}

	@RequestMapping("/ruleList")
	@ManagerActionLog(operateDescribe="查询规则",operateFuncType=FunLogType.Query,operateModelClassName=RuleMapper.class)
	@ResponseBody
	public String ruleList( Rule item, HttpServletRequest request,PageCondition pageCondition) {

		String rst = "";
		List<Rule> rules = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		rules = ruleService.selectRuleList(item);

		try {
			rst = PageUtil.packageTableData(page, rules);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/delete")
	@ManagerActionLog(operateDescribe="删除规则",operateFuncType=FunLogType.Del,operateModelClassName=RuleMapper.class)
	@ResponseBody
	public Message delete( Rule item,HttpServletRequest request) {

		Message msg = new Message();
		
	
		
		int result = ruleService.deleteRule(item);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/load")
	@ResponseBody
	public String load( @RequestParam String id,HttpServletRequest request) {
	
		Rule rules = ruleService.selectRuleById(id);
		return JSONObject.fromObject(rules).toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param rule
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	@ManagerActionLog(operateDescribe="保存修改规则",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=RuleMapper.class)
	@ResponseBody
	public String saveOrUpdate(Rule rule) {

		JSONObject jsonObject = null;
		try {
			if (null == rule.getId() || "".equals(rule.getId())) {
				
				jsonObject = ruleService.saveRule(rule);

			} else {
				jsonObject = ruleService.updateRule(rule);
			

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}


    @RequestMapping("/selectrule")
    @ResponseBody
    public List<Rule> selectrule( Rule item) {
        return ruleService.selectRuleList(item);
    }


}