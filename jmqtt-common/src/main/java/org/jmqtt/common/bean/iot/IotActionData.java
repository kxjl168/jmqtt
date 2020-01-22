package org.jmqtt.common.bean.iot;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.Topic;

/**
 * 初步封装的动作数据 IotActionData.java.
 * 
 * @author zj
 * @version 1.0.1 2020年1月22日
 * @revision zj 2020年1月22日
 * @since 1.0.1
 */
public class IotActionData {
	IotActionType actionType;

	Topic topic;// 具体topic
	String actionTargeName;// 属性名称/方法名称/事件名称
	String actionData;// 属性值，
	Message oriMessage;

	
	boolean rst;// 处理结果

	public IotActionType getActionType() {
		return actionType;
	}

	public void setActionType(IotActionType actionType) {
		this.actionType = actionType;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public String getActionTargeName() {
		return actionTargeName;
	}

	public void setActionTargeName(String actionTargeName) {
		this.actionTargeName = actionTargeName;
	}

	public String getActionData() {
		return actionData;
	}

	public void setActionData(String actionData) {
		this.actionData = actionData;
	}

	public Message getOriMessage() {
		return oriMessage;
	}

	public void setOriMessage(Message oriMessage) {
		this.oriMessage = oriMessage;
	}

	public boolean isRst() {
		return rst;
	}

	public void setRst(boolean rst) {
		this.rst = rst;
	}
}
