package org.jmqtt.group.protocol.node;

import java.io.Serializable;

/**
 * save jmqtt server node data
 */
public class ServerNode {

	/**
	 * 
	 */

	private String nodeName;
	/**
	 * ip:port,集群ipport
	 */
	private String addr;
	
	
	/**
	 * ip:port,web监听 ipport
	 */
	private String webaddr;
	
	private  long lastUpdateTime;
	private  boolean active;
	
	private Long onlinenums;

	public ServerNode() {
	}
	
	public ServerNode(String nodeName, String addr) {
		this.nodeName = nodeName;
		this.addr = addr;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * 节点检查，是否是同一个节点
	 * 
	 * @param obj
	 * @return
	 * @author zj
	 * @date 2019年12月13日
	 */
	public boolean equals(ServerNode node) {
		if (node.getAddr().equals(this.getAddr()))
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "ServerNode{" + "nodeName='" + nodeName + '\'' + ", addr='" + addr + '\'' + ", lastUpdateTime="
				+ lastUpdateTime + ", active=" + active + '}';
	}

	public Long getOnlinenums() {
		return onlinenums;
	}

	public void setOnlinenums(Long onlinenums) {
		this.onlinenums = onlinenums;
	}

	public String getWebaddr() {
		return webaddr;
	}

	public void setWebaddr(String webaddr) {
		this.webaddr = webaddr;
	}
}
