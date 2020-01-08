/*
 * @(#)MqttNodeController.java
 * @author: zhangJ
 * @Date: 2020-01-06 13:51:43
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.mqtt.controller.WebController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.Page;
import com.ztgm.base.aopAspect.FunLogType;
import com.ztgm.base.aopAspect.ManagerActionLog;
import com.ztgm.base.base.PageCondition;
import com.ztgm.base.util.DateUtil;
import com.ztgm.base.util.Message;
import com.ztgm.base.util.PageUtil;
import com.ztgm.mqtt.dao.MqttNodeMapper;
import com.ztgm.mqtt.netty.MqttHelper;
import com.ztgm.mqtt.netty.NettyWebRemotingClient;
import com.ztgm.mqtt.pojo.MqttNode;
import com.ztgm.mqtt.service.MqttNodeService;

import net.sf.json.JSONObject;

import org.jmqtt.common.bean.InvokeCallback;
import org.jmqtt.common.bean.ResponseFuture;
import org.jmqtt.common.helper.SerializeHelper;

import org.jmqtt.group.protocol.node.ServerNode;
import org.jmqtt.remoting.exception.RemotingConnectException;
import org.jmqtt.web.common.NettyWebResponseFuture;
import org.jmqtt.web.common.WebRemotingCommand;
import org.jmqtt.web.common.WebRequestCode;
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
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * mqtt节点信息表管理 MqttNodeController.java.
 * 
 * @author KAutoGenerator
 * @version 1.0.1 2020-01-06 13:51:43
 * @since 1.0.0
 */
@Controller
@RequestMapping("/manager/mqttnode")
public class MqttNodeController {
	@Autowired
	private MqttNodeService mqttnodeService;

	@Autowired
	NettyWebRemotingClient nettyWebClient;

	@RequestMapping("/manager")
	public String manager(Model model) {

		return "/backend/page/mqttnode/index.ftl";
	}

	@Autowired
	MqttHelper mqttHelper;

	@RequestMapping("/mqttnodeList")
	@ManagerActionLog(operateDescribe = "查询mqtt节点信息表", operateFuncType = FunLogType.Query, operateModelClassName = MqttNodeMapper.class)
	@ResponseBody
	public String mqttnodeList(MqttNode item, HttpServletRequest request, PageCondition pageCondition) {

		String rst = "";
		List<MqttNode> mqttnodes = new ArrayList<>();

		Page page = PageUtil.getPage(pageCondition);
		// mqttnodes = mqttnodeService.selectMqttNodeList(item);

		mqttnodes = mqttHelper.getServerNodeList();

		page = new Page<>();
		page.setTotal(mqttnodes.size());

		try {
			rst = PageUtil.packageTableData(page, mqttnodes);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return rst;
	}

	@RequestMapping("/delete")
	@ManagerActionLog(operateDescribe = "删除mqtt节点信息表", operateFuncType = FunLogType.Del, operateModelClassName = MqttNodeMapper.class)
	@ResponseBody
	public Message delete(MqttNode item, HttpServletRequest request) {

		Message msg = new Message();

		int result = mqttnodeService.deleteMqttNode(item);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/load")
	@ResponseBody
	public String load(@RequestParam String id, HttpServletRequest request) {

		MqttNode mqttnodes = mqttnodeService.selectMqttNodeById(id);
		return JSONObject.fromObject(mqttnodes).toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param mqttnode
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	@ManagerActionLog(operateDescribe = "保存修改mqtt节点信息表", operateFuncType = FunLogType.SaveOrUpdate, operateModelClassName = MqttNodeMapper.class)
	@ResponseBody
	public String saveOrUpdate(MqttNode mqttnode) {

		JSONObject jsonObject = null;
		try {
			if (null == mqttnode.getId() || "".equals(mqttnode.getId())) {

				jsonObject = mqttnodeService.saveMqttNode(mqttnode);

			} else {
				jsonObject = mqttnodeService.updateMqttNode(mqttnode);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert jsonObject != null;
		return jsonObject.toString();
	}

	@RequestMapping("/selectmqttnode")
	@ResponseBody
	public List<MqttNode> selectmqttnode(MqttNode item) {
		return mqttnodeService.selectMqttNodeList(item);
	}

}