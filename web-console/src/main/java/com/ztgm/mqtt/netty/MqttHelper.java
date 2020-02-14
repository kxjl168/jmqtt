package com.ztgm.mqtt.netty;

import java.util.Date;
import java.util.List;

import org.jmqtt.common.bean.InvokeCallback;
import org.jmqtt.common.bean.ResponseFuture;
import org.jmqtt.common.helper.SerializeHelper;
import org.jmqtt.group.protocol.node.ServerNode;
import org.jmqtt.remoting.exception.RemotingConnectException;
import org.jmqtt.web.common.NettyWebResponseFuture;
import org.jmqtt.web.common.WebRemotingCommand;
import org.jmqtt.web.common.WebRequestCode;
import org.springframework.stereotype.Service;

import com.ztgm.base.util.DateUtil;
import com.ztgm.mqtt.pojo.MqttNode;


public interface MqttHelper {
	
	/**
	 * 获取mqtt节点信息
	 * @param ipport
	 * @return
	 * @author zj
	 * @date 2020年1月8日
	 */
	public List<MqttNode> getServerNodeList();
	
	
	/**
	 * 获取mqtt节点信息
	 * @param ipport
	 * @return
	 * @author zj
	 * @date 2020年1月8日
	 */
	public String getServerNodeConfig(String ipport);
	
	/**
	 * 获取mqtt节点topic列表
	 * @param ipport
	 * @return
	 * @author zj
	 * @date 2020年1月8日
	 */
	public List<String> getServerTopics(String ipport);
	
	
	
	/**
	 * 通知节点刷新rule
	 * @param ipport
	 * @param productKey
	 * @return
	 * @author zj
	 * @date 2020年1月8日
	 */
	public void notifyNodeToRefreshRules(String productKey);
	
	
	
	/**
	 * 通知节点刷新物模型数据
	 * @param ipport
	 * @param productKey
	 * @return
	 * @author zj
	 * @date 2020年1月8日
	 */
	public void notifyNodeToRefreshIotModel(String productKey);
	

}
