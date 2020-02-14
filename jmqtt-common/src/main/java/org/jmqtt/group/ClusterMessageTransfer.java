package org.jmqtt.group;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.helper.SerializeHelper;
import org.jmqtt.group.protocol.ClusterRequestCode;


public interface ClusterMessageTransfer {
	
	/**
	 * 转发推送订阅回复消息数据到所有节点
	 * @param message
	 * @author zj
	 * @date 2020年1月17日
	 */
	public void dispatcherMessage2Cluster(Message message);
	
	
	/**
	 * 转发其他所有消息
	 * @param requestCode
	 * @param message
	 * @author zj
	 * @date 2020年2月13日
	 */
	public void dispatcherOtherMessage2Cluster(int ClusterRequestCode , Message message);


}
