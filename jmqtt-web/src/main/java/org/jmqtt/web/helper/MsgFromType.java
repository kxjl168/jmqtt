package org.jmqtt.web.helper;

/**
 * 消息来源
 * MsgFromType.java.
 * 
 * @author zj
* @version 1.0.1 2020年2月14日
* @revision zj 2020年2月14日
* @since 1.0.1
 */
public enum MsgFromType {
 
	WEB, //web直接下发的
	CLUSTER //内部集群转发的
}
