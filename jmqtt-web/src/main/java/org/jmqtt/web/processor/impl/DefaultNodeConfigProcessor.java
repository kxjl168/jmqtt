package org.jmqtt.web.processor.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;
import org.jmqtt.common.bean.Subscription;
import org.jmqtt.common.config.BrokerConfig;
import org.jmqtt.common.config.ClusterConfig;
import org.jmqtt.common.config.NettyConfig;
import org.jmqtt.common.config.RuleConfig;
import org.jmqtt.common.config.StoreConfig;
import org.jmqtt.common.config.WebConfig;
import org.jmqtt.common.helper.MixAll;
import org.jmqtt.common.helper.SerializeHelper;
import org.jmqtt.common.message.MessageDispatcher;
import org.jmqtt.group.common.ClusterNodeManager;
import org.jmqtt.group.protocol.node.ServerNode;
import org.jmqtt.remoting.session.ConnectManager;
import org.jmqtt.rule.common.ZRuleCommand;
import org.jmqtt.rule.processor.RuleEngin;
import org.jmqtt.rule.processor.RuleResultProcessor;
import org.jmqtt.store.SubscriptionStore;
import org.jmqtt.web.common.WebRemotingCommand;
import org.jmqtt.web.common.WebResponseCode;
import org.jmqtt.web.processor.WebRequestProcessor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;

/**
 * 处理web服务器下发的查询配置指令
 * 
 * @author zj
 * @version 1.0.1 2020年1月6日
 * @revision zj 2020年1月6日
 * @since 1.0.1
 */
public class DefaultNodeConfigProcessor implements WebRequestProcessor {

	BrokerConfig brokerConfig;
	ClusterConfig clusterConfig;
	NettyConfig nettyConfig;
	RuleConfig ruleConfig;
	StoreConfig storeConfig;
	WebConfig webConfig;

	public DefaultNodeConfigProcessor(BrokerConfig brokerConfig, ClusterConfig clusterConfig, NettyConfig nettyConfig,
			RuleConfig ruleConfig, StoreConfig storeConfig, WebConfig webConfig) {

		this.brokerConfig = brokerConfig;
		this.clusterConfig = clusterConfig;
		this.nettyConfig = nettyConfig;
		this.ruleConfig = ruleConfig;
		this.storeConfig = storeConfig;
		this.webConfig = webConfig;
	}

	@Override
	public WebRemotingCommand processRequest(ChannelHandlerContext ctx, WebRemotingCommand cmd) {

		StringBuffer sb = new StringBuffer();
		
		sb.append("------基础信息----------------\r\n");
		MixAll.printPropertiesToString(sb, brokerConfig);
		sb.append("\r\n");
		sb.append("------集群信息----------------\r\n");
		MixAll.printPropertiesToString(sb, clusterConfig);
		sb.append("\r\n");
		sb.append("------netty信息----------------\r\n");
		MixAll.printPropertiesToString(sb, nettyConfig);
		sb.append("\r\n");
		sb.append("------rule规则引擎----------------\r\n");
		MixAll.printPropertiesToString(sb, ruleConfig);
		sb.append("\r\n");
		sb.append("------本地存储----------------\r\n");
		MixAll.printPropertiesToString(sb, storeConfig);
		sb.append("\r\n");
		sb.append("------web节点----------------\r\n");
		MixAll.printPropertiesToString(sb, webConfig);

		

		cmd.setBody(SerializeHelper.serialize(sb.toString()));
		cmd.setResponseCode(WebResponseCode.RESPONSE_OK);
		cmd.makeResponseType();

		return cmd;
	}

}
