package org.jmqtt.group.processor;

import org.apache.commons.lang3.StringUtils;
import org.jmqtt.common.bean.InvokeCallback;
import org.jmqtt.common.bean.ResponseFuture;
import org.jmqtt.common.config.ClusterConfig;
import org.jmqtt.common.config.WebConfig;
import org.jmqtt.common.helper.RemotingHelper;
import org.jmqtt.common.helper.SerializeHelper;
import org.jmqtt.common.helper.ThreadFactoryImpl;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.group.ClusterRemotingClient;
import org.jmqtt.group.common.ClusterNodeManager;
import org.jmqtt.group.common.ClusterResponseFuture;
import org.jmqtt.group.protocol.ClusterRemotingCommand;
import org.jmqtt.group.protocol.ClusterRequestCode;
import org.jmqtt.group.protocol.CommandConstant;
import org.jmqtt.group.protocol.node.ServerNode;
import org.jmqtt.remoting.session.ConnectManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 集群节点管理实际相关操作 定时扫描，发现，节点状态 ClusterOuterAPI.java.
 * 
 * @author zj
 * @version 1.0.1 2019年12月12日
 * @revision zj 2019年12月12日
 * @since 1.0.1
 */
public class ClusterOuterAPI {

	private static final Logger log = LoggerFactory.getLogger(LoggerName.CLUSTER);
	private ClusterConfig clusterConfig;
	private ClusterRemotingClient clusterRemotingClient;
	private ScheduledThreadPoolExecutor scheduleRegisterNode;
	private ScheduledThreadPoolExecutor scheduleScanNode;
	private long timeoutMillis;
	private static final int NODE_ACTIVE_TIME_MILLIS = 15000;
	
	private WebConfig webConfig;

	public ClusterOuterAPI(ClusterConfig clusterConfig, ClusterRemotingClient clusterRemotingClient,WebConfig webConfig) {
		this.clusterConfig = clusterConfig;
		this.clusterRemotingClient = clusterRemotingClient;
		this.scheduleRegisterNode = new ScheduledThreadPoolExecutor(1,
				new ThreadFactoryImpl("scheduleRegisterNodeThread"));
		this.scheduleScanNode = new ScheduledThreadPoolExecutor(1, new ThreadFactoryImpl("scheduleScanNodeThread"));
		this.timeoutMillis = clusterConfig.getTimeoutMills();
		this.webConfig=webConfig;
	}

	public void start() {
		String currentIp = clusterConfig.getCurrentNodeIp();
		if (StringUtils.isEmpty(currentIp)) {
			currentIp = RemotingHelper.getLocalAddr();
		}
		String addr = currentIp + ":" + clusterConfig.getGroupServerPort();
		ServerNode currentNode = new ServerNode(clusterConfig.getNodeName(), addr);

		// zj 自身激活
		currentNode.setActive(true);
		currentNode.setOnlinenums(0L);
		currentNode.setWebaddr( currentIp + ":" +webConfig.getWebServerPort());

		ClusterNodeManager.getInstance().setCurrentNode(currentNode);
		ClusterNodeManager.getInstance().putNewNode(currentNode);

		if (StringUtils.isEmpty(clusterConfig.getGroupNodes())) {
			log.info("there is no other nodes to connect");
		} else {
			this.scheduleRegisterNode.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					registerOwnAndFetchNode();
				}
			}, 10 * 1000, NODE_ACTIVE_TIME_MILLIS, TimeUnit.MILLISECONDS);
			this.scheduleScanNode.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					scanClusterNodes();
				}
			}, 15 * 1000, (int) (NODE_ACTIVE_TIME_MILLIS * 1.5), TimeUnit.MILLISECONDS);

		}
	}

	private void scanClusterNodes() {
		Set<ServerNode> nodes = ClusterNodeManager.getInstance().getAllNodes();

		ConnectManager.getInstance().printAllSession();
		for (ServerNode node : nodes) {

			long diff = System.currentTimeMillis() - node.getLastUpdateTime();
			if (diff < NODE_ACTIVE_TIME_MILLIS * 1.5) {
				continue;
			}
			node.setActive(false);
			ClusterNodeManager.getInstance().putNewNode(node);

			log.info(" *****nodeName={},nodeAddr={} change to not active****", node.getNodeName(), node.getAddr());
			if (diff > NODE_ACTIVE_TIME_MILLIS * 5) {
				ClusterNodeManager.getInstance().removeNode(node.getNodeName());
				log.info("remove not active node,nodeName={},nodeAddr={}", node.getNodeName(), node.getAddr());
			}
		}
		String nodelist = "";

		nodes = ClusterNodeManager.getInstance().getAllNodes();
		for (ServerNode node : nodes) {
			nodelist += node.getAddr() + "[" + node.getNodeName() + "],";
		}
		log.debug("【cluster current connect nodes are {} 】: {}", nodes.size(), nodelist);
	}

	/**
	 * 向其他节点注册自己，并获取其他节点返回的激活节点数据
	 * 
	 * @author zj
	 * @date 2019年12月23日
	 */
	private void registerOwnAndFetchNode() {

		ServerNode curNode = ClusterNodeManager.getInstance().getCurrentNode();
		// 本节点直接更新时间状态
		curNode.setLastUpdateTime(System.currentTimeMillis());
		curNode.setActive(true);// true?? zj 191212
		curNode.setOnlinenums(ConnectManager.getInstance().getOnlineNums());
		ServerNode prevNode = ClusterNodeManager.getInstance().putNewNode(curNode);

		// zj
		log.debug("===cluster FETCH_NODES ...====");

		Set<ServerNode> nodes = ClusterNodeManager.getInstance().getAllNodes();
		ServerNode currentNode = ClusterNodeManager.getInstance().getCurrentNode();

		if (nodes != null && nodes.size() > 0) {
			for (ServerNode remoteNode : nodes) {

				if (remoteNode.getAddr().equals(ClusterNodeManager.getInstance().getCurrentNode().getAddr())) {
					// 本节点直接更新时间状态

				} else {
					// 只向其他节点注册

					log.debug("===to cluster instatnce  nodes: {} ====", remoteNode.getAddr());
					ClusterRemotingCommand remotingCommand = new ClusterRemotingCommand(ClusterRequestCode.FETCH_NODES);
					remotingCommand.setBody(SerializeHelper.serialize(currentNode));
					// zj add
					HashMap<String, String> extDatas = new HashMap<>();
					extDatas.put(CommandConstant.TO_NODE_NAME, remoteNode.getNodeName());
					extDatas.put(CommandConstant.TO_NODE_ADDRESS, remoteNode.getAddr());
					remotingCommand.setExtField(extDatas);
					// end

					try {
						this.clusterRemotingClient.invokeAsync(remoteNode.getAddr(), remotingCommand, timeoutMillis,
								new FetchNodeCallback());
					} catch (Exception ex) {
						log.warn("register current node to other nodes failure,nodeName={},nodeAddr={},ex={}",
								remoteNode.getNodeName(), remoteNode.getAddr(), ex);
						ClusterNodeManager.getInstance().removeNode(remoteNode.getNodeName());
					}
				}
			}
			// return;
		}
		String[] nodesStr = clusterConfig.getGroupNodes().split(";");

		for (String nodeAddr : nodesStr) {

			boolean isFecth = false;
			for (ServerNode remoteNode : nodes) {
				if (remoteNode.getAddr().equals(nodeAddr)) {
					isFecth = true;
					break;
				}
			}
			// 只发送本地节点未发送过的
			if (isFecth)
				continue;

			if (nodeAddr.equals(ClusterNodeManager.getInstance().getCurrentNode().getAddr())) {
				// 本节点不做处理
			} else {

				log.debug("===to config nodes: {} ", nodeAddr);

				ClusterRemotingCommand remotingCommand = new ClusterRemotingCommand(ClusterRequestCode.FETCH_NODES);
				remotingCommand.setBody(SerializeHelper.serialize(currentNode));

				// zj add
				HashMap<String, String> extDatas = new HashMap<>();
				extDatas.put(CommandConstant.TO_NODE_NAME, nodeAddr);
				extDatas.put(CommandConstant.TO_NODE_ADDRESS, nodeAddr);
				remotingCommand.setExtField(extDatas);
				// end

				try {
					this.clusterRemotingClient.invokeAsync(nodeAddr, remotingCommand, timeoutMillis,
							new FetchNodeCallback());
				} catch (Exception ex) {
					log.warn("register current node to other nodes failure,nodeAddr={},ex={}", nodeAddr, ex);
				}
			}
		}

	}

	public void shutdown() {
		this.scheduleRegisterNode.shutdown();
		log.info("scheduleRegisterNodeThread shutdown");
		this.scheduleScanNode.shutdown();
		log.info("scheduleScanNode shutdown");
	}

	private class FetchNodeCallback implements InvokeCallback {
		@Override
		public void invokeComplete(ResponseFuture responseFuture) {
			ClusterRemotingCommand responseCommand = ((ClusterResponseFuture) responseFuture)
					.getClusterRemotingCommand();
			if (responseCommand == null) {
				log.warn("fetch nodes response command is null");
				return;
			}

			log.debug("FetchNode Request Callback :{}", responseCommand);

			ServerNode cunode = ClusterNodeManager.getInstance().getCurrentNode();

			byte[] body = responseCommand.getBody();
			List<ServerNode> nodeList = SerializeHelper.deserializeList(body, ServerNode.class);
			if (nodeList != null && nodeList.size() > 0) {
				for (ServerNode node : nodeList) {
					if (!node.equals(cunode)) {
						node.setLastUpdateTime(System.currentTimeMillis());
						node.setActive(true);// true?? zj 191212
						ServerNode prevNode = ClusterNodeManager.getInstance().putNewNode(node);
						/*
						 * if (prevNode != null) {
						 * log.debug("prev node is not null,nodeName:{},nodeAddr:{},nodeActive:{}",
						 * prevNode.getNodeName(), prevNode.getAddr(), prevNode.isActive()); }
						 */
					}
				}
			} else {
				log.warn("fetch nodes is nil");
			}
		}
	}
}
