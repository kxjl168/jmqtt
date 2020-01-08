package org.jmqtt.web.common;

/**
 * web端通信报文类型
 * web message request code
 * 0-100
 * 
 * WebRequestCode.java.
 * 
 * @author zj
* @version 1.0.1 2020年1月6日
* @revision zj 2020年1月6日
* @since 1.0.1
 */
public class WebRequestCode {

    /**
     * WEB端保存更新规则数据
     */
    public static final int SAVE_OR_UPDATE_RULE = 1;

    /**
     * web端查询本节点状态
     */
    public static final int QUERY_NODE_STATUS = 2;

    /**
     * web端查询本节点配置
     */
    public static final int QUERY_NODE_CONFIG = 4;

    /**
     * web端查询本节点在线情况
     */
    public static final int QUERY_ONLINE_DATA = 5;

    /**
     * web端查询topic数据
     */
    public static final int QUERY_TOPICS = 6;

}
