package org.jmqtt.common.bean.msgdata;

/**
 * 消息上下行
 * MsgActionType.java.
 * 
 * @author zj
* @version 1.0.1 2020年2月12日
* @revision zj 2020年2月12日
* @since 1.0.1
 */
public enum MsgActionType {

	UP,
	Rule,//上行数据经过规则引擎流转
	Down
}
