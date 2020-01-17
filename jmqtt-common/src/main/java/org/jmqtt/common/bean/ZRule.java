package org.jmqtt.common.bean;

import java.io.Serializable;



/**
 * 规则数据模型
 * ZRule.java.
 * 
 * @author zj
* @version 1.0.1 2019年12月27日
* @revision zj 2019年12月27日
* @since 1.0.1
 */
public class ZRule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1689916719459515458L;
	
	private String ruleactionid; //id
	private String ruleid;
	private String name;
	private String productKey;
	
	
	private String ruleDesc;
	private String select;
	private String topic;
	private String status;
	private String utcModified;
	private String where; // a>9
	
	/*规则动作类型。取值：
	REPUBLISH：转发到另一个topic。
	OTS：存储到表格存储。
	MNS：发送消息到消息服务。
	ONS：发送数据到消息队列。
	TSDB：存储到高性能时间序列数据库
	FC：发送数据到函数计算。
	DATAHUB：发送数据到DataHub中。
	RDS：存储数据到云数据库中*/
	private RuleType rtype;
	
	private String configuration;// 具体规则动作配置

	
	public ZRule() {
		
		
	}
	
	public ZRule(String productKey,String ruleaid,String ruleid,String desc,String topic,String select,String where,String config) {
		this.productKey=productKey;
		this.ruleactionid=ruleaid;
		this.name=ruleid+"name";
		this.ruleid=ruleid;
		this.ruleDesc=desc;
		this.topic=topic;
		this.select=select;
		this.where=where;
		this.configuration=config;
	}
	
	public String toString() {
		return "{productKey:"+productKey+
				" ruleactionid:"+ruleactionid+
				" name:"+name+
				" ruleid:"+ruleid+
				" ruleDesc:"+ruleDesc+
				" topic:"+topic+
				" select:"+select+
				
				" where:"+where+
				" configuration:"+configuration
				+"}";
	}
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	public String getRuleDesc() {
		return ruleDesc;
	}

	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUtcModified() {
		return utcModified;
	}

	public void setUtcModified(String utcModified) {
		this.utcModified = utcModified;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getRuleactionid() {
		return ruleactionid;
	}

	public void setRuleactionid(String ruleactionid) {
		this.ruleactionid = ruleactionid;
	}

	public String getRuleid() {
		return ruleid;
	}

	public void setRuleid(String ruleid) {
		this.ruleid = ruleid;
	}



	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public RuleType getRtype() {
		return rtype;
	}

	public void setRtype(RuleType rtype) {
		this.rtype = rtype;
	}


}
