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

	// productKey 设备鉴权的 deviceName&productKey
	private Map<String, String> clientUserNameCache = new ConcurrentHashMap<>();

	private Map<String, String> clientUserNameClinetIdCache = new ConcurrentHashMap<>();

	private static final ConnectManager INSTANCE = new ConnectManager();

	private ConnectManager() {
	};

	public static ConnectManager getInstance() {
		return INSTANCE;
	}

	/**
	 * 设备鉴权的 deviceName&productKey
	 * 
	 * @param clientId
	 * @return
	 * @author zj
	 * @date 2020年2月10日
	 */
	public String getUserName(String clientId) {
		return this.clientUserNameCache.get(clientId);
	}

	/**
	 * 根据设备username&productkey 反查clientId
	 * 
	 * @param userName
	 * @return
	 * @author zj
	 * @date 2020年2月14日
	 */
	public String getClientIdByUserName(String userName) {
		return this.clientUserNameClinetIdCache.get(userName);
	}

	/*
	 * 根据设备username&productkey  获取登陆session
	 * @param userName
	 * @return
	 * @author zj
	 * @date 2020年2月14日
	 */
	public ClientSession getClientByUserName(String userName) {
		String clientId = this.clientUserNameClinetIdCache.get(userName);
		return this.clientCache.get(clientId);
	}

	public String putUserName(String clientId, String userName) {
		if (userName != null) {
			String prevalue = this.clientUserNameCache.put(clientId, userName);

			clientUserNameClinetIdCache.put(userName, clientId);
			return prevalue;
		} else
			return "";
	}

	/**
	 * 当前节点总在线节点clinetIds
	 * 
	 * @return
	 * @author zj
	 * @date 2020年1月8日
	 */
	public Set<String> getOnlineClinetIds() {
		return clientCache.keySet();
	}

	/**
	 * 当前节点总在线数
	 * 
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
		ClientSession session = this.clientCache.remove(clientId);
		String userName = this.clientUserNameCache.remove(clientId);
		clientUserNameClinetIdCache.remove(userName);
		return session;
	}

	public void printAllSession() {
		log.debug("[[[all connection nums:{}]]]]", clientCache.keySet().size());

	}
}
