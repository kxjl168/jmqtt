package org.jmqtt.group;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.helper.SerializeHelper;


public interface ClusterMessageTransfer {
	
	/**
	 * 转发推送消息数据到所有节点
	 * @param message
	 * @author zj
	 * @date 2020年1月17日
	 */
	public void dispatcherMessage2Cluster(Message message);

}
