package org.jmqtt.rule.common;

import org.jmqtt.common.bean.RuleType;

//规则具体执行动作
public class RuleDest {

	RuleType rtype;// 具体动作类型
	String configuration;// 动作具体配置jsonStr;

	public RuleDest() {
		// TODO Auto-generated constructor stub
	}

	public RuleDest(RuleType rtype, String config) {
		this.rtype = rtype;
		this.configuration = config;

	}

	public RuleType getRtype() {
		return rtype;
	}

	public void setRtype(RuleType rtype) {
		this.rtype = rtype;
	}

	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
}
