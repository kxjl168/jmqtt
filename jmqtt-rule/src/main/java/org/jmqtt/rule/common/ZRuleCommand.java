package org.jmqtt.rule.common;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.RuleType;

import io.netty.handler.codec.mqtt.MqttMessage;

/**
 * 待处理消息数据 ZRuleCommand.java.
 * 
 * @author zj
 * @version 1.0.1 2019年12月27日
 * @revision zj 2019年12月27日
 * @since 1.0.1
 */
public class ZRuleCommand {

	private Boolean satisfied; // 符合规则

	private String ruleactionid;// 符合的规则id
	/*
	 * 规则动作类型。取值： REPUBLISH：转发到另一个topic。 OTS：存储到表格存储。 MNS：发送消息到消息服务。 ONS：发送数据到消息队列。
	 * TSDB：存储到高性能时间序列数据库 FC：发送数据到函数计算。 DATAHUB：发送数据到DataHub中。 RDS：存储数据到云数据库中
	 */
	private RuleType rtype;

	private String select;
	private String topic;

	private String where; // a>9

	private String configuration;// 具体规则动作配置

	private Message oriMessage;// 原始pub数据

	private Boolean processRst;// 处理结果
	private String processMsg;// 处理结果

	public Boolean getSatisfied() {
		return satisfied;
	}

	public void setSatisfied(Boolean satisfied) {
		this.satisfied = satisfied;
	}

	public String getRuleactionid() {
		return ruleactionid;
	}

	public void setRuleactionid(String ruleactionid) {
		this.ruleactionid = ruleactionid;
	}

	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	public Message getOriMessage() {
		return oriMessage;
	}

	public void setOriMessage(Message oriMessage) {
		this.oriMessage = oriMessage;
	}

	public Boolean getProcessRst() {
		return processRst;
	}

	public void setProcessRst(Boolean processRst) {
		this.processRst = processRst;
	}

	public String getProcessMsg() {
		return processMsg;
	}

	public void setProcessMsg(String processMsg) {
		this.processMsg = processMsg;
	}

	public RuleType getRtype() {
		return rtype;
	}

	public void setRtype(RuleType rtype) {
		this.rtype = rtype;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}
}
