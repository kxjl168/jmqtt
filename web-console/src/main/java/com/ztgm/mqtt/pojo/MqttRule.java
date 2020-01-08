package com.ztgm.mqtt.pojo;

import org.jmqtt.common.bean.ZRule;

public class MqttRule {
    /**
     * 
     */
    private String id;

    /**
     * 规则名称
     */
    private String name;

    /**
     * prokey
     */
    private String productKey;

    /**
     * 规则描述
     */
    private String ruleDesc;

    /**
     * select
     */
    private String selectdata;

    /**
     * topic
     */
    private String topic;

    /**
     * 创建时间
     */
    private String CREATE_DATE;

    /**
     * 修改时间
     */
    private String utcModified;

    /**
     * where
     */
    private String wheredata;

    /**
     * 规则动作类型。取值： 	REPUBLISH：转发到另一个topic。 	OTS：存储到表格存储。 	MNS：发送消息到消息服务。 	ONS：发送数据到消息队列。 	TSDB：存储到高性能时间序列数据库 	FC：发送数据到函数计算。 	DATAHUB：发送数据到DataHub中。 	RDS：存储数据到云数据库中
     */
    private String rtype;

    /**
     * 配置json目标动作信息
     */
    private String configuration;
    
    public ZRule converToZRule() {
    	ZRule zr=new ZRule(productKey, id, id, ruleDesc, topic, wheredata, configuration);
    	return zr;
    }

    /**
     * 
     * @return id 
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 规则名称
     * @return name 规则名称
     */
    public String getName() {
        return name;
    }

    /**
     * 规则名称
     * @param name 规则名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * prokey
     * @return productKey prokey
     */
    public String getProductKey() {
        return productKey;
    }

    /**
     * prokey
     * @param productKey prokey
     */
    public void setProductKey(String productKey) {
        this.productKey = productKey == null ? null : productKey.trim();
    }

    /**
     * 规则描述
     * @return ruleDesc 规则描述
     */
    public String getRuleDesc() {
        return ruleDesc;
    }

    /**
     * 规则描述
     * @param ruleDesc 规则描述
     */
    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc == null ? null : ruleDesc.trim();
    }

    /**
     * select
     * @return selectdata select
     */
    public String getSelectdata() {
        return selectdata;
    }

    /**
     * select
     * @param selectdata select
     */
    public void setSelectdata(String selectdata) {
        this.selectdata = selectdata == null ? null : selectdata.trim();
    }

    /**
     * topic
     * @return topic topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * topic
     * @param topic topic
     */
    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    /**
     * 创建时间
     * @return CREATE_DATE 创建时间
     */
    public String getCREATE_DATE() {
        return CREATE_DATE;
    }

    /**
     * 创建时间
     * @param CREATE_DATE 创建时间
     */
    public void setCREATE_DATE(String CREATE_DATE) {
        this.CREATE_DATE = CREATE_DATE == null ? null : CREATE_DATE.trim();
    }

    /**
     * 修改时间
     * @return utcModified 修改时间
     */
    public String getUtcModified() {
        return utcModified;
    }

    /**
     * 修改时间
     * @param utcModified 修改时间
     */
    public void setUtcModified(String utcModified) {
        this.utcModified = utcModified == null ? null : utcModified.trim();
    }

    /**
     * where
     * @return wheredata where
     */
    public String getWheredata() {
        return wheredata;
    }

    /**
     * where
     * @param wheredata where
     */
    public void setWheredata(String wheredata) {
        this.wheredata = wheredata == null ? null : wheredata.trim();
    }

    /**
     * 规则动作类型。取值： 	REPUBLISH：转发到另一个topic。 	OTS：存储到表格存储。 	MNS：发送消息到消息服务。 	ONS：发送数据到消息队列。 	TSDB：存储到高性能时间序列数据库 	FC：发送数据到函数计算。 	DATAHUB：发送数据到DataHub中。 	RDS：存储数据到云数据库中
     * @return rtype 规则动作类型。取值： 	REPUBLISH：转发到另一个topic。 	OTS：存储到表格存储。 	MNS：发送消息到消息服务。 	ONS：发送数据到消息队列。 	TSDB：存储到高性能时间序列数据库 	FC：发送数据到函数计算。 	DATAHUB：发送数据到DataHub中。 	RDS：存储数据到云数据库中
     */
    public String getRtype() {
        return rtype;
    }

    /**
     * 规则动作类型。取值： 	REPUBLISH：转发到另一个topic。 	OTS：存储到表格存储。 	MNS：发送消息到消息服务。 	ONS：发送数据到消息队列。 	TSDB：存储到高性能时间序列数据库 	FC：发送数据到函数计算。 	DATAHUB：发送数据到DataHub中。 	RDS：存储数据到云数据库中
     * @param rtype 规则动作类型。取值： 	REPUBLISH：转发到另一个topic。 	OTS：存储到表格存储。 	MNS：发送消息到消息服务。 	ONS：发送数据到消息队列。 	TSDB：存储到高性能时间序列数据库 	FC：发送数据到函数计算。 	DATAHUB：发送数据到DataHub中。 	RDS：存储数据到云数据库中
     */
    public void setRtype(String rtype) {
        this.rtype = rtype == null ? null : rtype.trim();
    }

    /**
     * 配置json目标动作信息
     * @return configuration 配置json目标动作信息
     */
    public String getConfiguration() {
        return configuration;
    }

    /**
     * 配置json目标动作信息
     * @param configuration 配置json目标动作信息
     */
    public void setConfiguration(String configuration) {
        this.configuration = configuration == null ? null : configuration.trim();
    }
}