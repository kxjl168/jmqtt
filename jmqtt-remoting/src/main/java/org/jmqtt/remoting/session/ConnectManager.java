package org.jmqtt.remoting.session;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.jmqtt.common.log.LoggerName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectManager {

	private static final Logger log = LoggerFactory.getLogger(LoggerName.REMOTING);

	private Map<String, ClientSession> clientCache = new ConcurrentHashMap<>();

	// productKey
	private Map<String, String> clientUserNameCache = new ConcurrentHashMap<>();

	private static final ConnectManager INSTANCE = new ConnectManager();

	private ConnectManager() {
	};

	public static ConnectManager getInstance() {
		return INSTANCE;
	}

	public String getUserName(String clientId) {
		return this.clientUserNameCache.get(clientId);
	}

	public String putUserName(String clientId, String userName) {
		if (userName != null)
			return this.clientUserNameCache.put(clientId, userName);
		else
			return "";
	}
	
	/**
	 * 当前节点总在线节点clinetIds
	 * @return
	 * @author zj
	 * @date 2020年1月8日
	 */
	public Set<String> getOnlineClinetIds() {
		return clientCache.keySet();
	}
	
	/**
	 * 当前节点总在线数
	 * @return
	 * @author zj
	 * @date 2020年1月8日
	 */
	public Long getOnlineNums() {
		return Long.valueOf(clientCache.keySet().size());
	}

	public ClientSession getClient(String clientId) {
		return this.clientCache.get(clientId);
	}

	public ClientSession putClient(String clientId, ClientSession clientSession) {
		return this.clientCache.put(clientId, clientSession);
	}

	public boolean containClient(String clientId) {
		return this.clientCache.containsKey(clientId);
	}

	public ClientSession removeClient(String clientId) {
		return this.clientCache.remove(clientId);
	}

	public void printAllSession() {
		log.info("[[[all connection nums:{}]]]]", clientCache.keySet().size());
	}
}
