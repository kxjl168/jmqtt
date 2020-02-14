package org.jmqtt.group.processor;

import io.netty.channel.ChannelHandlerContext;

import org.jmqtt.common.helper.RemotingHelper;
import org.jmqtt.common.helper.SerializeHelper;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.group.common.ClusterNodeManager;
import org.jmqtt.group.message.WebSRequestListener;
import org.jmqtt.group.protocol.ClusterRemotingCommand;
import org.jmqtt.group.protocol.ClusterRequestCode;
import org.jmqtt.group.protocol.ClusterResponseCode;
import org.jmqtt.group.protocol.CommandConstant;
import org.jmqtt.group.protocol.node.ServerNode;
import org.jmqtt.remoting.session.ConnectManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * 集群模块转发 web通信模块 指令 WebSRequestProcessor.java.
 * 
 * @author zj
 * @version 1.0.1 2020年2月13日
 * @revision zj 2020年2月13日
 * @since 1.0.1
 */
public class WebSRequestProcessor implements ClusterRequestProcessor {

	private static final Logger log = LoggerFactory.getLogger(LoggerName.CLUSTER);

	WebSRequestListener webrequestListener;

	public WebSRequestProcessor(WebSRequestListener webrequestListener) {
		this.webrequestListener = webrequestListener;
	}

	@Override
	public ClusterRemotingCommand processRequest(ChannelHandlerContext ctx, ClusterRemotingCommand cmd) {

		// WebRemotingCommand wcmd = new WebRemotingCommand();
		// wcmd.setBody(cmd.getBody());

		log.info("***ClusterServer**** WebSRequestProcessor  request processed: opaque :{} cmd.code:{},type:{}",
				cmd.getOpaque(), cmd.getCode(), ClusterRequestCode.getRequestType(cmd.getCode()));

		try {

			switch (cmd.getCode()) {
			case ClusterRequestCode.NOTICE_RULE_CHAGNEND:
				// wcmd.setCode(WebRequestCode.SAVE_OR_UPDATE_RULE);
				webrequestListener.EnginRuleChanged(cmd);
				break;
			case ClusterRequestCode.NOTICE_OBJ_MODEL_CHAGNEND:
				// wcmd.setCode(WebRequestCode.SAVE_OR_UPDATE_IOT_MODEL);
				webrequestListener.ObjectModelChanged(cmd);
				break;
				
			case ClusterRequestCode.NOTICE_MODEL_SHADOW_CHANGED:
				// wcmd.setCode(WebRequestCode.SAVE_OR_UPDATE_IOT_MODEL);
				webrequestListener.ObjectModelShadowChanged(cmd);
				break;
				
			case ClusterRequestCode.NOTICE_MODEL_SHADOW_DOWN_UPDATED:
				// wcmd.setCode(WebRequestCode.SAVE_OR_UPDATE_IOT_MODEL);
				webrequestListener.ObjectModelShadowDownComplete(cmd);
				break;
				

			default:
				break;
			}
		} catch (Exception e) {
			log.error("[WebSRequestProcessor]" + e.getMessage());
		}

		cmd.setResponseCode(ClusterResponseCode.RESPONSE_OK);
		return cmd;

	}
}
