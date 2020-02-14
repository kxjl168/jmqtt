package org.jmqtt.iot.processor.impl.action;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;
import org.jmqtt.common.bean.Topic;
import org.jmqtt.common.bean.iot.IotActionData;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.common.message.MessageDispatcher;
import org.jmqtt.group.ClusterMessageTransfer;
import org.jmqtt.iot.processor.ObjectMessageAction;
import org.jmqtt.remoting.session.ConnectManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 响应设备上报的设置属性topic操作
 * DefaultSetPropertyAction.java.
 * 
 * @author zj
* @version 1.0.1 2020年2月11日
* @revision zj 2020年2月11日
* @since 1.0.1
 */
public class DefaultSetPropertyAction implements ObjectMessageAction {

	private static final Logger log = LoggerFactory.getLogger(LoggerName.IOTMODEL);

	
	MessageDispatcher messageDispatcher;
	ClusterMessageTransfer clusterMessageTransfer;

	public DefaultSetPropertyAction(MessageDispatcher messageDispatcher,
			ClusterMessageTransfer clusterMessageTransfer) {
		this.messageDispatcher = messageDispatcher;
		this.clusterMessageTransfer = clusterMessageTransfer;
	}
	
	/**
	 * 
	 * eg:客户端发布  mosquitto_pub -t /sys/testKey/device1/thing/event/property/post -h 192.168.100.41 -p 21883 -m "{\"Brightness\":50}"  -u "device1&testKey" <br>
	 * 订阅回复  mosquitto_sub -t /sys/testKey/device1/thing/event/property/post_reply -h 192.168.100.41 -p 21883
	 * @param cmd
	 * @return
	 * @author zj
	 * @date 2020年2月11日
	 */
	@Override
	public IotActionData processRequest(IotActionData cmd) {
		// /sys/${productKey}/${deviceName}/thing/event/property/post


		Message msg = cmd.getOriMessage();
		// 消息的topic
		Topic msgtopic = new Topic(String.valueOf(msg.getHeader(MessageHeader.TOPIC)),
				Integer.parseInt(String.valueOf(msg.getHeader(MessageHeader.QOS))));

		String username = ConnectManager.getInstance().getUserName(msg.getClientId());
		String[] keys = username.split("&");

		String deviceName = keys[0];
		String productKey = keys[1];
		
		// 设置属性
		String jsondata = cmd.getActionData();// 解析属性数据

		// web调用,设置 设备影子信息，更新或者添加影子属性
		// TODO
		log.debug("set property action done!：" + jsondata);

		// 消息计数
		// TODO

		// 响应消息
		// /sys/${productKey}/${deviceName}/thing/event/property/post_reply

		//post 的需要回发消息 ，set的不需要
		if(msgtopic.getTopicName().endsWith("/thing/event/property/post"))
		{
			// 回复消息
			String desttopic = "/sys/" + productKey + "/" + deviceName + "/thing/event/property/post_reply";
			

			Message trannewMsg = new Message();

			trannewMsg.putHeader(MessageHeader.TOPIC, desttopic);
			trannewMsg.putHeader(MessageHeader.QOS, 1);
			trannewMsg.putHeader(MessageHeader.DUP, false);

			trannewMsg.setPayload(("post消息成功,当前值为:"+jsondata).getBytes());

			messageDispatcher.appendMessage(trannewMsg);
			clusterMessageTransfer.dispatcherMessage2Cluster(trannewMsg);
		}
		

		cmd.setRst(true);

		return cmd;
	}

}
