/*
 * @(#)RuleActionController.java
 * @author: zhangJ
 * @Date: 2020-01-10 10:29:30
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
import com.ztgm.openplat.dao.RuleActionMapper;
import com.ztgm.openplat.pojo.RuleAction;
import com.ztgm.openplat.service.RuleActionService;

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
 * 规则动作管理 RuleActionController.java.
 * 
 * @author KAutoGenerator
 * @version 1.0.1 2020-01-10 10:29:30
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager/ruleaction")
public class RuleActionController {
	@Autowired
	private RuleActionService ruleactionService;


	@RequestMapping("/manager")
	public String manager(Model model) {

		return "/backend/page/ruleaction/index.ftl";
	}

	@RequestMapping("/ruleactionList")
	@ManagerActionLog(operateDescribe="查询规则动作",operateFuncType=FunLogType.Query,operateModelClassName=RuleActionMapper.class)
	@ResponseBody
	public String ruleactionList( RuleAction item, HttpServletRequest request,PageCondition pageCondition) {

		String rst = "";
		List<RuleAction> ruleactions = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		ruleactions = ruleactionService.selectRuleActionList(item);

		try {
			rst = PageUtil.packageTableData(page, ruleactions);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/delete")
	@ManagerActionLog(operateDescribe="删除规则动作",operateFuncType=FunLogType.Del,operateModelClassName=RuleActionMapper.class)
	@ResponseBody
	public Message delete( RuleAction item,HttpServletRequest request) {

		Message msg = new Message();
		
	
		
		int result = ruleactionService.deleteRuleAction(item);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/load")
	@ResponseBody
	public String load( @RequestParam String id,HttpServletRequest request) {
	
		RuleAction ruleactions = ruleactionService.selectRuleActionById(id);
		return JSONObject.fromObject(ruleactions).toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param ruleaction
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	@ManagerActionLog(operateDescribe="保存修改规则动作",operateFuncType=FunLogType.SaveOrUpdate,operateModelClassName=RuleActionMapper.class)
	@ResponseBody
	public String saveOrUpdate(RuleAction ruleaction) {

		JSONObject jsonObject = null;
		try {
			if (null == ruleaction.getId() || "".equals(ruleaction.getId())) {
				
				jsonObject = ruleactionService.saveRuleAction(ruleaction);

			} else {
				jsonObject = ruleactionService.updateRuleAction(ruleaction);
			

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}


    @RequestMapping("/selectruleaction")
    @ResponseBody
    public List<RuleAction> selectruleaction( RuleAction item) {
        return ruleactionService.selectRuleActionList(item);
    }


}