package org.jmqtt.common.bean.msgdata;

import org.jmqtt.common.bean.iot.IotActionType;

/**
 * 消息日志记录， 详情日志 MsgLog.java.
 * 
 * @author zj
 * @version 1.0.1 2020年2月12日
 * @revision zj 2020年2月12日
 * @since 1.0.1
 */
public class MsgLog {

	private String nodename;//发送，接收消息的节点名称
	
	private String productKey;
	private String deviceName;
	private String clientId;

	private String dateTime; // 2020-01-01 11:12:11
	
	private MsgActionType actionType;// 上下行

	private IotActionType feeType;// 物模型业务类型  计费类型

	private byte[] payload; // 消息数据

	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public MsgActionType getActionType() {
		return actionType;
	}

	public void setActionType(MsgActionType actionType) {
		this.actionType = actionType;
	}

	public IotActionType getFeeType() {
		return feeType;
	}

	public void setFeeType(IotActionType feeType) {
		this.feeType = feeType;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

}
