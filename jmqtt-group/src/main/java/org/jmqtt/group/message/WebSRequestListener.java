package org.jmqtt.group.message;

import org.jmqtt.group.protocol.ClusterRemotingCommand;


/**
 * 接收集群指令，转给本节点web模块处理
 * WebSRequestListener.java.
 * 
 * @author zj
* @version 1.0.1 2020年2月13日
* @revision zj 2020年2月13日
* @since 1.0.1
 */
public interface WebSRequestListener {

	
	/**
	 * 规则引擎改变通知
	 * @param cmd
	 * @author zj
	 * @date 2020年2月13日
	 */
	void  EnginRuleChanged(ClusterRemotingCommand cmd);
	
	
	
	/**
	 * 物模型改变通知
	 * @param cmd
	 * @author zj
	 * @date 2020年2月13日
	 */
	void  ObjectModelChanged(ClusterRemotingCommand cmd);
	
	
	/**
	 * 设备影子改变通知
	 * @param cmd
	 * @author zj
	 * @date 2020年2月13日
	 */
	void  ObjectModelShadowChanged(ClusterRemotingCommand cmd);
	
	/**
	 * 设备影子已下发通知完成
	 * @param cmd
	 * @author zj
	 * @date 2020年2月13日
	 */
	void   ObjectModelShadowDownComplete(ClusterRemotingCommand cmd);
}
