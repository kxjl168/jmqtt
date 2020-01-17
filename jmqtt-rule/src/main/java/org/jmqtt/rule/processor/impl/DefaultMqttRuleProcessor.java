package org.jmqtt.rule.processor.impl;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;
import org.jmqtt.common.constant.RuleConstants;
import org.jmqtt.common.message.MessageDispatcher;
import org.jmqtt.group.ClusterMessageTransfer;
import org.jmqtt.rule.common.ZRuleCommand;
import org.jmqtt.rule.processor.RuleResultProcessor;

import com.alibaba.fastjson.JSONObject;

import io.netty.handler.codec.mqtt.MqttMessage;

/**
 * mqtt 规则转发处理器
 * DefaultMqttRuleProcessor.java.
 * 
 * @author zj
* @version 1.0.1 2019年12月30日
* @revision zj 2019年12月30日
* @since 1.0.1
 */
public class DefaultMqttRuleProcessor implements RuleResultProcessor {

	MessageDispatcher messageDispatcher;
	ClusterMessageTransfer clusterMessageTransfer;

	public DefaultMqttRuleProcessor(MessageDispatcher messageDispatcher,ClusterMessageTransfer clusterMessageTransfer) {
		this.messageDispatcher = messageDispatcher;
		this.clusterMessageTransfer=clusterMessageTransfer;
	}

	@Override
	public ZRuleCommand processRequest(ZRuleCommand rulerst) {
		Message orimessage = rulerst.getOriMessage();
		String oldmsg="";
		if(orimessage!=null&&orimessage.getPayload()!=null)
		 oldmsg=new String(orimessage.getPayload());
		
		Message trannewMsg=new Message();
		//TODO 根据 where select config等构造新的mqtt消息
		
		String config= rulerst.getConfiguration();
		JSONObject jconfig=JSONObject.parseObject(config);
		String desttopic=jconfig.getString(RuleConstants.JSON_DEST_TOPIC);
		
		trannewMsg.putHeader(MessageHeader.TOPIC,desttopic);
		trannewMsg.putHeader(MessageHeader.QOS,1);
		trannewMsg.putHeader(MessageHeader.DUP,false);
		
		trannewMsg.setPayload(("我是ruleEngin转发的消息,原始消息:"+oldmsg+" select:"+rulerst.getSelect()+" where:"+rulerst.getWhere() +" destData:"+rulerst.getProcessMsg()).getBytes());
		
		
		messageDispatcher.appendMessage(trannewMsg);
		clusterMessageTransfer.dispatcherMessage2Cluster(trannewMsg);		
		
		return rulerst;

	}

}
