
package com.ztgm.openplat.controller.AppController;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztgm.base.util.AppResult;
import com.ztgm.base.util.AppResultUtil;

import com.ztgm.openplat.bean.CommonQueryData;
import com.ztgm.openplat.bean.PageData;
import com.ztgm.openplat.pojo.Rule;
import com.ztgm.openplat.pojo.RuleAction;
import com.ztgm.openplat.service.RuleActionService;
import com.ztgm.openplat.service.RuleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * rule接口 ARuleController.java.
 * 
 * @author zj
 * @version 1.0.1 2020年1月10日
 * @revision zj 2020年1月10日
 * @since 1.0.1
 */
@Controller
@RequestMapping("/interface/rule")
public class ARuleController {

	private Logger logger = Logger.getLogger(ARuleController.class);

	@Autowired
	private RuleService ruleService;

	@Autowired
	private RuleActionService ruleActionService;

	/**
	 * 分页查询所有规则列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/ListRule")
	@ResponseBody
	public AppResult ListRule(CommonQueryData commonData, PageData pageData, HttpServletRequest request,
			HttpServletResponse response) {

		AppResult appResultDto = AppResultUtil.fail();

		try {// TODO 检查是否有权限能查全部

			// Data= ruleService.action();
			List<Rule> rules = new ArrayList<Rule>();

			appResultDto = AppResultUtil.success(rules);
		} catch (Exception e) {

			logger.error(e);

		}

		return appResultDto;
	}

	/**
	 * 查询指定规则下的所有规则动作列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/ListRuleActions")
	@ResponseBody
	public AppResult ListRuleActionsc(CommonQueryData commonData, PageData pageData, String RuleId,
			HttpServletRequest request, HttpServletResponse response) {

		AppResult appResultDto = AppResultUtil.fail();

		try {
			// TODO 检查是否有权限能查全部
			// Data= ruleActionService.action();

			List<RuleAction> actions = new ArrayList<RuleAction>();

			appResultDto = AppResultUtil.success(actions);
		} catch (Exception e) {

			logger.error(e);

		}

		return appResultDto;
	}

	/**
	 * 对指定Topic新建一个规则
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/CreateRule")
	@ResponseBody
	public AppResult CreateRule(CommonQueryData commonData, PageData pageData, Rule rule, String TopicType,
			String ProjectId, HttpServletRequest request, HttpServletResponse response) {

		AppResult appResultDto = AppResultUtil.fail();

		try {
			// TODO 检查是否有权限能加
			// Data= ruleService.action();

			Long RuleID = 0L;// TODO
			appResultDto = AppResultUtil.success(RuleID);
		} catch (Exception e) {

			logger.error(e);

		}

		return appResultDto;
	}

	/**
	 * 在指定的规则下创建一个规则动作，定义将处理后的Topic数据转发至物联网平台的其他Topic
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/CreateRuleAction")
	@ResponseBody
	public AppResult CreateRuleAction(CommonQueryData commonData, PageData pageData, RuleAction ruleAction,
			HttpServletRequest request, HttpServletResponse response) {

		AppResult appResultDto = AppResultUtil.fail();

		try {
			// TODO 检查是否有权限能加
			// Data= ruleActionService.action();

			Long RuleActionID = 0L;// TODO
			appResultDto = AppResultUtil.success(RuleActionID);
		} catch (Exception e) {

			logger.error(e);

		}

		return appResultDto;
	}

	/**
	 * 查询指定规则的详细信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/GetRule")
	@ResponseBody
	public AppResult GetRule(CommonQueryData commonData, PageData pageData, Long RuleId, HttpServletRequest request,
			HttpServletResponse response) {

		AppResult appResultDto = AppResultUtil.fail();

		try {
			// TODO 检查是否有权限能查
			// Data= ruleService.action();
			Rule rule = ruleService.selectRuleById(String.valueOf(RuleId));

			appResultDto = AppResultUtil.success(rule);
		} catch (Exception e) {

			logger.error(e);

		}

		return appResultDto;
	}

	/**
	 * 查询指定规则动作的详细信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/GetRuleAction")
	@ResponseBody
	public AppResult GetRuleAction(CommonQueryData commonData, PageData pageData, Long ActionId,
			HttpServletRequest request, HttpServletResponse response) {

		AppResult appResultDto = AppResultUtil.fail();

		try {

			// TODO 检查是否有权限能查

			// Data= ruleService.action();
			RuleAction ruleaction = ruleActionService.selectRuleActionById(String.valueOf(ActionId));

			appResultDto = AppResultUtil.success(ruleaction);
		} catch (Exception e) {

			logger.error(e);

		}

		return appResultDto;
	}

	/**
	 * 删除指定的规则
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/DeleteRule")
	@ResponseBody
	public AppResult DeleteRule(CommonQueryData commonData, PageData pageData, Long RuleId, HttpServletRequest request,
			HttpServletResponse response) {

		AppResult appResultDto = AppResultUtil.fail();

		try {
			// TODO 检查是否能删

			// Data= ruleService.action();
			Rule query = new Rule();
			query.setId(RuleId);
			ruleService.deleteRule(query);

			appResultDto = AppResultUtil.success();
		} catch (Exception e) {

			logger.error(e);

		}

		return appResultDto;
	}

	/**
	 * 删除指定的规则动作
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/DeleteRuleAction")
	@ResponseBody
	public AppResult DeleteRuleAction(CommonQueryData commonData, PageData pageData, Long ActionId,
			HttpServletRequest request, HttpServletResponse response) {

		AppResult appResultDto = AppResultUtil.fail();

		try {

			// TODO 检查是否能删

			// Data= ruleService.action();
			RuleAction query = new RuleAction();
			query.setId(ActionId);
			ruleActionService.deleteRuleAction(query);

			appResultDto = AppResultUtil.success();
		} catch (Exception e) {

			logger.error(e);

		}

		return appResultDto;
	}

	/**
	 * 修改指定的规则
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/UpdateRule")
	@ResponseBody
	public AppResult UpdateRule(CommonQueryData commonData, PageData pageData, Rule rule,
			HttpServletRequest request, HttpServletResponse response) {

		AppResult appResultDto = AppResultUtil.fail();

		try {

			// Data= ruleService.action();

			// TODO 检查rule修改是否符合要求

			ruleService.updateRule(rule);

			appResultDto = AppResultUtil.success();
		} catch (Exception e) {

			logger.error(e);

		}

		return appResultDto;
	}
	
	/**
	 * 修改指定的规则动作
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/UpdateRuleAction")
	@ResponseBody
	public AppResult UpdateRuleAction(CommonQueryData commonData, PageData pageData, RuleAction rule,
			HttpServletRequest request, HttpServletResponse response) {

		AppResult appResultDto = AppResultUtil.fail();

		try {

			// Data= ruleService.action();

			// TODO 检查rule修改是否符合要求

			ruleActionService.updateRuleAction(rule);

			appResultDto = AppResultUtil.success();
		} catch (Exception e) {

			logger.error(e);

		}

		return appResultDto;
	}
	
	

	/**
	 * 启用指定规则
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/StartRule")
	@ResponseBody
	public AppResult StartRule(CommonQueryData commonData, PageData pageData, Long RuleId,
			HttpServletRequest request, HttpServletResponse response) {

		AppResult appResultDto = AppResultUtil.fail();

		try {

			// Data= ruleService.action();

			// TODO 检查rule权限
			
			Rule rule=ruleService.selectRuleById(String.valueOf(RuleId));

			
			ruleService.startRule(rule);

			appResultDto = AppResultUtil.success();
		} catch (Exception e) {

			logger.error(e);

		}

		return appResultDto;
	}
	
	/**
	 * 停用规则
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/StopRule")
	@ResponseBody
	public AppResult StopRule(CommonQueryData commonData, PageData pageData, Long RuleId,
			HttpServletRequest request, HttpServletResponse response) {

		AppResult appResultDto = AppResultUtil.fail();

		try {

			// TODO 检查rule权限
			
			Rule rule=ruleService.selectRuleById(String.valueOf(RuleId));
			
			ruleService.stopRule(rule);


			appResultDto = AppResultUtil.success();
		} catch (Exception e) {

			logger.error(e);

		}

		return appResultDto;
	}


}
