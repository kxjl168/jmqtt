package org.jmqtt.common.bean;

public enum RuleType {
	REPUBLISH, // ：转发到另一个topic。
	OTS, // ：存储到表格存储。
	MNS, // ：发送消息到消息服务。
	ONS, // ：发送数据到消息队列。
	TSDB, // ：存储到高性能时间序列数据库
	FC, // ：发送数据到函数计算。
	DATAHUB, // ：发送数据到DataHub中。
	RDS,// ：存储数据到云数据库中
	NULL;

	public static RuleType Parse(String type)
	{
		for (RuleType rtype : RuleType.values()) {
			if(rtype.toString().equals(type))
				return rtype;
		}
		return NULL;
	}
}
