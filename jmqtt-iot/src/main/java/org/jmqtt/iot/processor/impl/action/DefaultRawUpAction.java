package org.jmqtt.iot.processor.impl.action;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;
import org.jmqtt.common.bean.RuleType;
import org.jmqtt.common.bean.Topic;
import org.jmqtt.common.bean.iot.IotActionData;
import org.jmqtt.common.constant.RuleConstants;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.common.message.MessageDispatcher;
import org.jmqtt.group.ClusterMessageTransfer;
import org.jmqtt.iot.processor.ObjectMessageAction;
import org.jmqtt.remoting.session.ConnectManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 默认透传处理 DefaultRawUpAction.java.
 * 
 * @author zj
 * @version 1.0.1 2020年2月10日
 * @revision zj 2020年2月10日
 * @since 1.0.1
 */
public class DefaultRawUpAction implements ObjectMessageAction {

	private static final Logger log = LoggerFactory.getLogger(LoggerName.IOTMODEL);

	MessageDispatcher messageDispatcher;
	ClusterMessageTransfer clusterMessageTransfer;

	public DefaultRawUpAction(MessageDispatcher messageDispatcher, ClusterMessageTransfer clusterMessageTransfer) {
		this.messageDispatcher = messageDispatcher;
		this.clusterMessageTransfer = clusterMessageTransfer;
	}

	@Override
	public IotActionData processRequest(IotActionData cmd) {

		Message msg = cmd.getOriMessage();
		// 消息的topic
		Topic msgtopic = new Topic(String.valueOf(msg.getHeader(MessageHeader.TOPIC)),
				Integer.parseInt(String.valueOf(msg.getHeader(MessageHeader.QOS))));

		String username = ConnectManager.getInstance().getUserName(msg.getClientId());
		String[] keys = username.split("&");

		String deviceName = keys[0];
		String productKey = keys[1];

		// 透传设备上报的数据

		// TODO
		// web调用 url 获取返回
		// 转发

		// 数据
		String jsondata = cmd.getActionData();// 解析透传数据

		log.debug("消息透传成功!" + jsondata);

		// 回复消息
		String desttopic = "/sys/" + productKey + "/" + deviceName + "/thing/model/up_raw_reply";

		Message trannewMsg = new Message();

		trannewMsg.putHeader(MessageHeader.TOPIC, desttopic);
		trannewMsg.putHeader(MessageHeader.QOS, 1);
		trannewMsg.putHeader(MessageHeader.DUP, false);

		trannewMsg.setPayload("透传成功".getBytes());

		messageDispatcher.appendMessage(trannewMsg);
		clusterMessageTransfer.dispatcherMessage2Cluster(trannewMsg);

		return cmd;
	}

}
