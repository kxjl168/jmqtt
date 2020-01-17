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
     * 该规则动作得类型的数据类型，取值：REPUBLISH：转发到另一个topic。
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
     * 该规则动作得类型的数据类型，取值：REPUBLISH：转发到另一个topic。
     * @return Type 该规则动作得类型的数据类型，取值：REPUBLISH：转发到另一个topic。
     */
    public String getType() {
        return type;
    }

    /**
     * 该规则动作得类型的数据类型，取值：REPUBLISH：转发到另一个topic。
     * @param type 该规则动作得类型的数据类型，取值：REPUBLISH：转发到另一个topic。
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