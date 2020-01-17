package com.ztgm.mqtt.pojo;

public class Procompany {
    /**
     * 
     */
    private String id;

    /**
     * 公司名称
     */
    private String name;

    /**
     * prokey
     */
    private String productKey;

    /**
     * secret
     */
    private String secret;

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
     * 公司名称
     * @return name 公司名称
     */
    public String getName() {
        return name;
    }

    /**
     * 公司名称
     * @param name 公司名称
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
     * secret
     * @return secret secret
     */
    public String getSecret() {
        return secret;
    }

    /**
     * secret
     * @param secret secret
     */
    public void setSecret(String secret) {
        this.secret = secret == null ? null : secret.trim();
    }
}