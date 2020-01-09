package com.ztgm.mqtt.netty;

import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztgm.base.util.DateUtil;
import com.ztgm.mqtt.pojo.MqttNode;

@Service
public class DefalutMqttHelper implements MqttHelper {

	@Autowired
	NettyWebRemotingClient nettyWebClient;

	String serverIPPORT = "192.168.100.41:29990";

	public DefalutMqttHelper() {
		// Config
		serverIPPORT = "192.168.100.126:39990";
	}

	public List<MqttNode> getServerNodeList() {

		final List<MqttNode> mqttnodes = new ArrayList<>();

		WebRemotingCommand wcmd = new WebRemotingCommand();
		wcmd.setCode(WebRequestCode.QUERY_NODES_STATUS);
		try {
			nettyWebClient.invoke(false, serverIPPORT, wcmd, 2000, new InvokeCallback() {

				@Override
				public void invokeComplete(ResponseFuture responseFuture) {

					NettyWebResponseFuture future = (NettyWebResponseFuture) responseFuture;
					byte[] data = future.getWebRemotingCommand().getBody();
					if (data != null) {
						List<ServerNode> nodes = SerializeHelper.deserializeList(data, ServerNode.class);
						for (ServerNode node : nodes) {
							MqttNode nd = new MqttNode();
							nd.setIpPort(node.getAddr());
							nd.setWebipPort(node.getWebaddr());
							nd.setName(node.getNodeName());
							nd.setStatus(node.isActive() ? "1" : "0");
							nd.setOnlineUsers(String.valueOf(node.getOnlinenums()));
							nd.setRefreshTime(DateUtil.getDateStr(new Date(node.getLastUpdateTime()), ""));
							mqttnodes.add(nd);
						}
					}

				}
			});
		} catch (RemotingConnectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return mqttnodes;
	}

	/**
	 * 通知节点刷新rule
	 * 
	 * @param ipport
	 * @param productKey
	 * @return
	 * @author zj
	 * @date 2020年1月8日
	 */
	public void notifyNodeToRefreshRules(String productKey) {
		WebRemotingCommand wcmd = new WebRemotingCommand();
		wcmd.setCode(WebRequestCode.SAVE_OR_UPDATE_RULE);

		wcmd.setBody(productKey.getBytes());
		try {
			nettyWebClient.invoke(true, serverIPPORT, wcmd, 2000, new InvokeCallback() {

				@Override
				public void invokeComplete(ResponseFuture responseFuture) {

					NettyWebResponseFuture future = (NettyWebResponseFuture) responseFuture;

				}
			});
		} catch (RemotingConnectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public String getServerNodeConfig(String ipport) {
		final WebRemotingCommand wcmd = new WebRemotingCommand();
		wcmd.setCode(WebRequestCode.QUERY_NODE_CONFIG);

		String config = "";

		try {
			nettyWebClient.invoke(false, ipport, wcmd, 2000, new InvokeCallback() {

				@Override
				public void invokeComplete(ResponseFuture responseFuture) {

					NettyWebResponseFuture future = (NettyWebResponseFuture) responseFuture;

					byte[] data = future.getWebRemotingCommand().getBody();
					if (data != null) {
						wcmd.setBody(data);
					}
				}
			});
		} catch (RemotingConnectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		config = SerializeHelper.deserialize(wcmd.getBody(), String.class);

		return config;
	}

	@Override
	public List<String> getServerTopics(String ipport) {
		final WebRemotingCommand wcmd = new WebRemotingCommand();
		wcmd.setCode(WebRequestCode.QUERY_NODE_TOPICS);

		try {
			nettyWebClient.invoke(false, ipport, wcmd, 2000, new InvokeCallback() {

				@Override
				public void invokeComplete(ResponseFuture responseFuture) {

					NettyWebResponseFuture future = (NettyWebResponseFuture) responseFuture;
					byte[] data = future.getWebRemotingCommand().getBody();
					if (data != null) {
						wcmd.setBody(data);
					}
				}
			});
		} catch (RemotingConnectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		List<String> topics = new ArrayList<>();
		topics = SerializeHelper.deserializeList(wcmd.getBody(), String.class);

		return topics;

	}

}
