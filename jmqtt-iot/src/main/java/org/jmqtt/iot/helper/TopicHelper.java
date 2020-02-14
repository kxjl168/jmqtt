package org.jmqtt.iot.helper;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;
import org.jmqtt.common.bean.Topic;
import org.jmqtt.common.bean.iot.IotActionType;
import org.jmqtt.common.bean.iot.IotObject;
import org.jmqtt.common.bean.iot.IotTopic;
import org.jmqtt.remoting.session.ConnectManager;

/**
 * 设备topic辅助类 TopicHelper.java.
 * 
 * @author zj
 * @version 1.0.1 2020年2月10日
 * @revision zj 2020年2月10日
 * @since 1.0.1
 */
public class TopicHelper {

	/**
	 * 根据物模型topic定义，解析出上报/下发报文的业务类型
	 * 
	 * @param iotObj
	 * @param message
	 * @return
	 * @author zj
	 * @date 2020年2月10日
	 */
	public static IotActionType ParesAType(IotObject iotObj, Message msg) {
		IotActionType atype = IotActionType.Fail;

		// 消息的topic
		Topic msgtopic = new Topic(String.valueOf(msg.getHeader(MessageHeader.TOPIC)),
				Integer.parseInt(String.valueOf(msg.getHeader(MessageHeader.QOS))));

		String username = ConnectManager.getInstance().getUserName(msg.getClientId());
		String[] keys = username.split("&");
		if (keys.length != 2)
			return atype;

		String deviceName = keys[0];
		String productKey = keys[1];

		// /sys/${productKey}/${deviceName}/thing/event/property/post
		if (msgtopic.getTopicName().equals("/sys/" + productKey + "/" + deviceName + "/thing/event/property/post")) {
			atype = IotActionType.PostProperty; // 属性上报
			return atype;
		}
		if (msgtopic.getTopicName().equals("/sys/" + productKey + "/" + deviceName + "/thing/event/property/post_reply")) {
			atype = IotActionType.PostPropertyReply; // 属性上报
			return atype;
		}
	
		if (msgtopic.getTopicName().equals("/sys/" + productKey + "/" + deviceName + "/thing/service/property/set")) {
			atype = IotActionType.SetProperty; // 属性设置
			return atype;
		}
		
		
		if (msgtopic.getTopicName().equals("/sys/" + productKey + "/" + deviceName + "/thing/model/up_raw")) {
			atype = IotActionType.RAW_UP; // 透传
			return atype;
		}
		
		if (msgtopic.getTopicName().equals("/sys/" + productKey + "/" + deviceName + "/thing/model/down_raw")) {
			atype = IotActionType.RAW_DOWN; // 透传
			return atype;
		}

		boolean isInModelTopics = false;// 检查是否在物模型定义的topic列表中

		// 模型topics
		for (IotTopic iotTopic : iotObj.getTopics()) {

			String modelTopic = iotTopic.getTopicName().replace("${productKey}", productKey).replace("${deviceName}",
					deviceName);

			// 服务调用
			if (modelTopic.contains("${tsl.service.identifer}")) {

				for (int i = 0; i < iotObj.getServiceDTOList().size(); i++) {
					String destTopic = modelTopic.replace("${tsl.service.identifer}",
							iotObj.getServiceDTOList().get(i).getIdentifier());
					if (destTopic.equals(msgtopic.getTopicName())) {
						isInModelTopics = true;
						break;
					}
				}

				if (isInModelTopics) {
					atype = IotActionType.CallFunction_REPLY; // 服务方法调用
					return atype;
				}

			}

			// 事件上报
			if (modelTopic.contains("${tsl.event.identifer}")) {
				for (int i = 0; i < iotObj.getEventDTOList().size(); i++) {
					String destTopic = modelTopic.replace("${tsl.service.identifer}",
							iotObj.getEventDTOList().get(i).getIdentifier());
					if (destTopic.equals(msgtopic.getTopicName())) {
						isInModelTopics = true;
						break;
					}
				}

				if (isInModelTopics) {
					atype = IotActionType.EventUpload; // 事件调用
					return atype;
				}

			}

			if (modelTopic.equals(msgtopic.getTopicName())) {
				isInModelTopics = true;
				break;
			}

		}

		if (!isInModelTopics)
			return atype;

		atype = IotActionType.SelfDefine; // 其他自定义topic

		return atype;
	}
}
