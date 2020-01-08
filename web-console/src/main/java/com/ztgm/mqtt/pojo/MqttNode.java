package com.ztgm.mqtt.pojo;

public class MqttNode {
    /**
     * 
     */
    private String id;

    /**
     * 平台名称
     */
    private String name;

    /**
     * 平台根url
     */
    private String ipPort;

    /**
     * 上下线 1：上线，0 :离线
     */
    private String status;

    /**
     * 状态最后检查时间
     */
    private String refreshTime;

    /**
     * 在线用户数
     */
    private String onlineUsers;

    /**
     * 配置信息
     */
    private String configinfo;

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
     * 平台名称
     * @return name 平台名称
     */
    public String getName() {
        return name;
    }

    /**
     * 平台名称
     * @param name 平台名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 平台根url
     * @return ip_port 平台根url
     */
    public String getIpPort() {
        return ipPort;
    }

    /**
     * 平台根url
     * @param ipPort 平台根url
     */
    public void setIpPort(String ipPort) {
        this.ipPort = ipPort == null ? null : ipPort.trim();
    }

    /**
     * 上下线 1：上线，0 :离线
     * @return status 上下线 1：上线，0 :离线
     */
    public String getStatus() {
        return status;
    }

    /**
     * 上下线 1：上线，0 :离线
     * @param status 上下线 1：上线，0 :离线
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 状态最后检查时间
     * @return refresh_time 状态最后检查时间
     */
    public String getRefreshTime() {
        return refreshTime;
    }

    /**
     * 状态最后检查时间
     * @param refreshTime 状态最后检查时间
     */
    public void setRefreshTime(String refreshTime) {
        this.refreshTime = refreshTime == null ? null : refreshTime.trim();
    }

    /**
     * 在线用户数
     * @return online_users 在线用户数
     */
    public String getOnlineUsers() {
        return onlineUsers;
    }

    /**
     * 在线用户数
     * @param onlineUsers 在线用户数
     */
    public void setOnlineUsers(String onlineUsers) {
        this.onlineUsers = onlineUsers == null ? null : onlineUsers.trim();
    }

    /**
     * 配置信息
     * @return configinfo 配置信息
     */
    public String getConfiginfo() {
        return configinfo;
    }

    /**
     * 配置信息
     * @param configinfo 配置信息
     */
    public void setConfiginfo(String configinfo) {
        this.configinfo = configinfo == null ? null : configinfo.trim();
    }
}