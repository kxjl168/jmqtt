package org.jmqtt.web.processor.impl;

import java.util.List;
import java.util.Set;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;
import org.jmqtt.common.helper.SerializeHelper;
import org.jmqtt.common.message.MessageDispatcher;
import org.jmqtt.group.common.ClusterNodeManager;
import org.jmqtt.group.protocol.node.ServerNode;
import org.jmqtt.rule.common.ZRuleCommand;
import org.jmqtt.rule.processor.RuleEngin;
import org.jmqtt.rule.processor.RuleResultProcessor;
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
 * 处理web服务器下发的查询所有节点状态指令
 * DefaultEnginRuleChangeProcessor.java.
 * 
 * @author zj
* @version 1.0.1 2020年1月6日
* @revision zj 2020年1月6日
* @since 1.0.1
 */
public class DefaultGroupProcessor implements WebRequestProcessor {

/*	RuleEngin ruleEngin;

	public DefaultGroupProcessor(RuleEngin ruleEngin) {
		this.ruleEngin = ruleEngin;
	}*/

	@Override
	public WebRemotingCommand processRequest(ChannelHandlerContext ctx, WebRemotingCommand cmd) {

		
		Set<ServerNode> nodes= ClusterNodeManager.getInstance().getAllNodes();
		
/*		String tstr=JSONObject.toJSONString(nodes,SerializerFeature.WriteNonStringValueAsString);
		byte[] d2=tstr.getBytes();
		
	
		byte[] d= SerializeHelper.serialize(nodes);
		
		JSON.parseArray(new String(d),ServerNode.class);
		
		
		JSONObject.parseArray(new String(d));
	
		List<ServerNode> nds= SerializeHelper.deserializeList(d, ServerNode.class);
		*/
		cmd.setBody(SerializeHelper.serialize(nodes));
		cmd.setResponseCode(WebResponseCode.RESPONSE_OK);
		cmd.makeResponseType();
		
		return cmd;
	}

}
