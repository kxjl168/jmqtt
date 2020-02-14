package org.jmqtt.web.processor.impl;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;
import org.jmqtt.common.bean.iot.IotDevice;
import org.jmqtt.common.helper.SerializeHelper;
import org.jmqtt.common.message.MessageDispatcher;
import org.jmqtt.group.ClusterMessageTransfer;
import org.jmqtt.group.message.WebSRequestListener;
import org.jmqtt.group.protocol.ClusterRequestCode;
import org.jmqtt.remoting.session.ClientSession;
import org.jmqtt.remoting.session.ConnectManager;
import org.jmqtt.rule.common.ZRuleCommand;
import org.jmqtt.rule.processor.RuleEngin;
import org.jmqtt.rule.processor.RuleResultProcessor;
import org.jmqtt.store.ObjectModelMessageStore;
import org.jmqtt.web.common.WebHeaderConstant;
import org.jmqtt.web.common.WebRemotingCommand;
import org.jmqtt.web.common.WebResponseCode;
import org.jmqtt.web.helper.MsgFromType;
import org.jmqtt.web.helper.ShadowHelper;
import org.jmqtt.web.processor.WebRequestProcessor;

import com.alibaba.fastjson.JSONObject;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;

/**
 * web下发设备影子设备更改指令 DefaultModelShadowUpdateProcessor.java.
 * 
 * @author zj
 * @version 1.0.1 2020年2月14日
 * @revision zj 2020年2月14日
 * @since 1.0.1
 */
public class DefaultModelShadowUpdateProcessor implements WebRequestProcessor {

	ClusterMessageTransfer clusterMessageTransfer;
	MessageDispatcher messageDispatcher;
	WebSRequestListener webRequestListener;

	ObjectModelMessageStore iotModelStore;

	public DefaultModelShadowUpdateProcessor(ObjectModelMessageStore iotModelStore, MessageDispatcher messageDispatcher,
			ClusterMessageTransfer clusterMessageTransfer) {
		this.iotModelStore = iotModelStore;
		// this.webRequestListener=webRequestListener;
		this.clusterMessageTransfer = clusterMessageTransfer;
		this.messageDispatcher = messageDispatcher;
	}

	@Override
	public WebRemotingCommand processRequest(ChannelHandlerContext ctx, WebRemotingCommand cmd) {

		IotDevice deviceShadow = SerializeHelper.deserialize(cmd.getBody(), IotDevice.class);

		ShadowHelper.dealShadowChanged(MsgFromType.WEB, iotModelStore, deviceShadow, messageDispatcher,
				clusterMessageTransfer);

		cmd.setResponseCode(WebResponseCode.RESPONSE_OK);
		cmd.makeResponseType();

		return cmd;
	}

}
