/*
 * @(#)MqttRuleController.java
 * @author: zhangJ
 * @Date: 2020-01-08 16:29:51
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.mqtt.controller.WebController;

import com.github.pagehelper.Page;
import com.ztgm.base.aopAspect.FunLogType;
import com.ztgm.base.aopAspect.ManagerActionLog;
import com.ztgm.base.aopAspect.NoNeedAuthorization;
import com.ztgm.base.base.PageCondition;
import com.ztgm.base.util.AppResult;
import com.ztgm.base.util.AppResultUtil;
import com.ztgm.base.util.Message;
import com.ztgm.base.util.PageUtil;
import com.ztgm.mqtt.dao.MqttRuleMapper;
import com.ztgm.mqtt.netty.MqttHelper;
import com.ztgm.mqtt.netty.NettyWebRemotingClient;
import com.ztgm.mqtt.pojo.MqttRule;
import com.ztgm.mqtt.service.MqttRuleService;

import net.sf.json.JSONObject;

import org.jmqtt.common.bean.ZRule;
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
 * mqtt规则表管理 MqttRuleController.java.
 * 
 * @author KAutoGenerator
 * @version 1.0.1 2020-01-08 16:29:51
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager/mqttrule")
public class MqttRuleController {
	@Autowired
	private MqttRuleService mqttruleService;
	@Autowired
	MqttHelper mqttHelper;

	@RequestMapping("/manager")
	public String manager(Model model) {

		return "/backend/page/mqttrule/index.ftl";
	}

	@RequestMapping("/interface/proRuleList")
	@NoNeedAuthorization
	@ResponseBody
	public AppResult proRuleList(MqttRule item, HttpServletRequest request, PageCondition pageCondition) {

		AppResult rst = AppResultUtil.fail();
		List<ZRule> rstrule = new ArrayList<>();

		List<MqttRule> mqttrules = mqttruleService.selectMqttRuleList(item);
		for (MqttRule mqttRule : mqttrules) {
			ZRule d = mqttRule.converToZRule();
			rstrule.add(d);
		}

		try {
			rst = AppResultUtil.success(rstrule);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/mqttruleList")
	@ManagerActionLog(operateDescribe = "查询mqtt规则表", operateFuncType = FunLogType.Query, operateModelClassName = MqttRuleMapper.class)
	@ResponseBody
	public String mqttruleList(MqttRule item, HttpServletRequest request, PageCondition pageCondition) {

		String rst = "";
		List<MqttRule> mqttrules = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		mqttrules = mqttruleService.selectMqttRuleList(item);

		try {
			rst = PageUtil.packageTableData(page, mqttrules);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/delete")
	@ManagerActionLog(operateDescribe = "删除mqtt规则表", operateFuncType = FunLogType.Del, operateModelClassName = MqttRuleMapper.class)
	@ResponseBody
	public Message delete(MqttRule item, HttpServletRequest request) {

		Message msg = new Message();

		MqttRule rule = mqttruleService.selectMqttRuleById(item.getId());

		int result = mqttruleService.deleteMqttRule(item);
		if (result == 1) {
			msg.setBol(true);
		}

		mqttHelper.notifyNodeToRefreshRules(rule.getProductKey());

		return msg;
	}

	@RequestMapping("/load")
	@ResponseBody
	public String load(@RequestParam String id, HttpServletRequest request) {

		MqttRule mqttrules = mqttruleService.selectMqttRuleById(id);
		return JSONObject.fromObject(mqttrules).toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param mqttrule
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	@ManagerActionLog(operateDescribe = "保存修改mqtt规则表", operateFuncType = FunLogType.SaveOrUpdate, operateModelClassName = MqttRuleMapper.class)
	@ResponseBody
	public String saveOrUpdate(MqttRule mqttrule) {

		JSONObject jsonObject = null;
		try {
			if (null == mqttrule.getId() || "".equals(mqttrule.getId())) {

				jsonObject = mqttruleService.saveMqttRule(mqttrule);

			} else {
				jsonObject = mqttruleService.updateMqttRule(mqttrule);

			}

			mqttHelper.notifyNodeToRefreshRules(mqttrule.getProductKey());

		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}

	@RequestMapping("/selectmqttrule")
	@ResponseBody
	public List<MqttRule> selectmqttrule(MqttRule item) {
		return mqttruleService.selectMqttRuleList(item);
	}

}