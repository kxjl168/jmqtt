package com.ztgm.openplat.pojo;

public class RuleAction {
    /**
     * 
     */
    private Long id;

    /**
     * rule id
     */
    private Long ruleId;

    /**
     * 该规则动作得类型的数据类型，取值：REPUBLISH：转发到另一个topic。 	// OTS：存储到表格存储。 	// MNS：发送消息到消息服务。 	// ONS：发送数据到消息队列。 	// TSDB：存储到高性能时间序列数据库 	// FC：发送数据到函数计算。 	// DATAHUB：发送数据到DataHub中。 	// RDS：存储数据到云数据库中。
     */
    private String type;

    /**
     * 规则动作配置
     */
    private String configuration;

    /**
     * String 该规则动作是否为转发错误操作数据的转发动作，即转发流转到其他云产品失败且重试失败的数据 ,true/false
     */
    private String errorActionFlag;

    /**
     * 
     * @return Id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * rule id
     * @return RuleId rule id
     */
    public Long getRuleId() {
        return ruleId;
    }

    /**
     * rule id
     * @param ruleId rule id
     */
    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    /**
     * 该规则动作得类型的数据类型，取值：REPUBLISH：转发到另一个topic。 	// OTS：存储到表格存储。 	// MNS：发送消息到消息服务。 	// ONS：发送数据到消息队列。 	// TSDB：存储到高性能时间序列数据库 	// FC：发送数据到函数计算。 	// DATAHUB：发送数据到DataHub中。 	// RDS：存储数据到云数据库中。
     * @return Type 该规则动作得类型的数据类型，取值：REPUBLISH：转发到另一个topic。 	// OTS：存储到表格存储。 	// MNS：发送消息到消息服务。 	// ONS：发送数据到消息队列。 	// TSDB：存储到高性能时间序列数据库 	// FC：发送数据到函数计算。 	// DATAHUB：发送数据到DataHub中。 	// RDS：存储数据到云数据库中。
     */
    public String getType() {
        return type;
    }

    /**
     * 该规则动作得类型的数据类型，取值：REPUBLISH：转发到另一个topic。 	// OTS：存储到表格存储。 	// MNS：发送消息到消息服务。 	// ONS：发送数据到消息队列。 	// TSDB：存储到高性能时间序列数据库 	// FC：发送数据到函数计算。 	// DATAHUB：发送数据到DataHub中。 	// RDS：存储数据到云数据库中。
     * @param type 该规则动作得类型的数据类型，取值：REPUBLISH：转发到另一个topic。 	// OTS：存储到表格存储。 	// MNS：发送消息到消息服务。 	// ONS：发送数据到消息队列。 	// TSDB：存储到高性能时间序列数据库 	// FC：发送数据到函数计算。 	// DATAHUB：发送数据到DataHub中。 	// RDS：存储数据到云数据库中。
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 规则动作配置
     * @return Configuration 规则动作配置
     */
    public String getConfiguration() {
        return configuration;
    }

    /**
     * 规则动作配置
     * @param configuration 规则动作配置
     */
    public void setConfiguration(String configuration) {
        this.configuration = configuration == null ? null : configuration.trim();
    }

    /**
     * String 该规则动作是否为转发错误操作数据的转发动作，即转发流转到其他云产品失败且重试失败的数据 ,true/false
     * @return ErrorActionFlag String 该规则动作是否为转发错误操作数据的转发动作，即转发流转到其他云产品失败且重试失败的数据 ,true/false
     */
    public String getErrorActionFlag() {
        return errorActionFlag;
    }

    /**
     * String 该规则动作是否为转发错误操作数据的转发动作，即转发流转到其他云产品失败且重试失败的数据 ,true/false
     * @param errorActionFlag String 该规则动作是否为转发错误操作数据的转发动作，即转发流转到其他云产品失败且重试失败的数据 ,true/false
     */
    public void setErrorActionFlag(String errorActionFlag) {
        this.errorActionFlag = errorActionFlag == null ? null : errorActionFlag.trim();
    }
}