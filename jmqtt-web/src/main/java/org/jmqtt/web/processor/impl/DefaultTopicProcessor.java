package org.jmqtt.web.processor.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;
import org.jmqtt.common.bean.Subscription;
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
 * 处理web服务器下发的topic查询指令 DefaultEnginRuleChangeProcessor.java.
 * 
 * @author zj
 * @version 1.0.1 2020年1月6日
 * @revision zj 2020年1月6日
 * @since 1.0.1
 */
public class DefaultTopicProcessor implements WebRequestProcessor {

	SubscriptionStore subscriptionStore;

	public DefaultTopicProcessor(SubscriptionStore subscriptionStore) {
		this.subscriptionStore = subscriptionStore;
	}

	@Override
	public WebRemotingCommand processRequest(ChannelHandlerContext ctx, WebRemotingCommand cmd) {

		Set<ServerNode> nodes = ClusterNodeManager.getInstance().getAllNodes();

		List<String> topics = new ArrayList<String>();

		Set<String> cids = ConnectManager.getInstance().getOnlineClinetIds();
		for (String cid : cids) {
			Collection<Subscription> subStore = subscriptionStore.getSubscriptions(cid);
			for (Subscription subscription : subStore) {
				if (!topics.contains(subscription.getTopic())) {
					topics.add(subscription.getTopic());
				}
			}
		}

		cmd.setBody(SerializeHelper.serialize(topics));
		cmd.setResponseCode(WebResponseCode.RESPONSE_OK);
		cmd.makeResponseType();

		return cmd;
	}

}
