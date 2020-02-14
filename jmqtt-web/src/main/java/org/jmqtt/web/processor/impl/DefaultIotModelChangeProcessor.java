package org.jmqtt.web.processor.impl;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;
import org.jmqtt.common.bean.iot.IotObject;
import org.jmqtt.common.helper.SerializeHelper;
import org.jmqtt.common.message.MessageDispatcher;
import org.jmqtt.group.ClusterMessageTransfer;
import org.jmqtt.iot.processor.IotObjectEngin;
import org.jmqtt.rule.common.ZRuleCommand;
import org.jmqtt.rule.processor.RuleEngin;
import org.jmqtt.rule.processor.RuleResultProcessor;
import org.jmqtt.web.common.WebRemotingCommand;
import org.jmqtt.web.common.WebResponseCode;
import org.jmqtt.web.processor.WebRequestProcessor;

import com.alibaba.fastjson.JSONObject;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;

/**
 * 处理web服务器下发的刷新规则数据指令
 * DefaultEnginRuleChangeProcessor.java.
 * 
 * @author zj
* @version 1.0.1 2020年1月6日
* @revision zj 2020年1月6日
* @since 1.0.1
 */
public class DefaultIotModelChangeProcessor implements WebRequestProcessor {

	IotObjectEngin iotEngin;
	
	ClusterMessageTransfer clusterMessageTransfer;


	public DefaultIotModelChangeProcessor(IotObjectEngin iotEngin) { //{,ClusterMessageTransfer clusterMessageTransfer) {
		this.iotEngin = iotEngin;
		//this.clusterMessageTransfer = clusterMessageTransfer;
	}

	@Override
	public WebRemotingCommand processRequest(ChannelHandlerContext ctx, WebRemotingCommand cmd) {

		String proKey=SerializeHelper.deserialize(cmd.getBody(),String.class);
		
		iotEngin.refreshObjectModels(proKey);
		
		cmd.setResponseCode(WebResponseCode.RESPONSE_OK);
		cmd.makeResponseType();
		
		
		//clusterMessageTransfer.dispatcherMessage2Cluster(message);
		
		return cmd;
	}

}
