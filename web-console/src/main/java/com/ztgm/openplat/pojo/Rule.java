package com.ztgm.openplat.pojo;

public class Rule {
    /**
     * 
     */
    private Long id;

    /**
     * 创建用户id
     */
    private Long createUserId;

    /**
     * 该规则的数据类型，取值：JSON和BINARY
     */
    private String dataType;

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
    private String selectData;

    /**
     * 短topic
     */
    private String shortTopic;

    /**
     * 规则运行状态
     */
    private String status;

    /**
     * topic
     */
    private String topic;

    /**
     * 创建时间
     */
    private String utcModified;

    /**
     * 修改时间
     */
    private String utcCreated;

    /**
     * where
     */
    private String whereData;

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
     * 创建用户id
     * @return CreateUserId 创建用户id
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建用户id
     * @param createUserId 创建用户id
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 该规则的数据类型，取值：JSON和BINARY
     * @return DataType 该规则的数据类型，取值：JSON和BINARY
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * 该规则的数据类型，取值：JSON和BINARY
     * @param dataType 该规则的数据类型，取值：JSON和BINARY
     */
    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    /**
     * 规则名称
     * @return Name 规则名称
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
     * @return ProductKey prokey
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
     * @return RuleDesc 规则描述
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
     * @return SelectData select
     */
    public String getSelectData() {
        return selectData;
    }

    /**
     * select
     * @param selectData select
     */
    public void setSelectData(String selectData) {
        this.selectData = selectData == null ? null : selectData.trim();
    }

    /**
     * 短topic
     * @return ShortTopic 短topic
     */
    public String getShortTopic() {
        return shortTopic;
    }

    /**
     * 短topic
     * @param shortTopic 短topic
     */
    public void setShortTopic(String shortTopic) {
        this.shortTopic = shortTopic == null ? null : shortTopic.trim();
    }

    /**
     * 规则运行状态
     * @return Status 规则运行状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 规则运行状态
     * @param status 规则运行状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * topic
     * @return Topic topic
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
     * @return UtcModified 创建时间
     */
    public String getUtcModified() {
        return utcModified;
    }

    /**
     * 创建时间
     * @param utcModified 创建时间
     */
    public void setUtcModified(String utcModified) {
        this.utcModified = utcModified == null ? null : utcModified.trim();
    }

    /**
     * 修改时间
     * @return UtcCreated 修改时间
     */
    public String getUtcCreated() {
        return utcCreated;
    }

    /**
     * 修改时间
     * @param utcCreated 修改时间
     */
    public void setUtcCreated(String utcCreated) {
        this.utcCreated = utcCreated == null ? null : utcCreated.trim();
    }

    /**
     * where
     * @return WhereData where
     */
    public String getWhereData() {
        return whereData;
    }

    /**
     * where
     * @param whereData where
     */
    public void setWhereData(String whereData) {
        this.whereData = whereData == null ? null : whereData.trim();
    }
}