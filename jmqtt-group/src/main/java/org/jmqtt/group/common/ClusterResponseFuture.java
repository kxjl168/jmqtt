package org.jmqtt.group.common;

import io.netty.channel.Channel;

import org.jmqtt.common.bean.InvokeCallback;
import org.jmqtt.common.bean.ResponseFuture;
import org.jmqtt.common.bean.SemaphoreReleaseOnlyOnce;
import org.jmqtt.group.protocol.ClusterRemotingCommand;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * cache request until receive response or store response to the local storage
 */
public class ClusterResponseFuture extends ResponseFuture {

	private volatile ClusterRemotingCommand clusterRemotingCommand;

	public ClusterResponseFuture(Channel channel, int opaque, long timeoutMillis, InvokeCallback invokeCallback,
			SemaphoreReleaseOnlyOnce semaphoreReleaseOnlyOnce) {
		super(channel, opaque, timeoutMillis, invokeCallback, semaphoreReleaseOnlyOnce);

	}

	public ClusterRemotingCommand getClusterRemotingCommand() {
		return clusterRemotingCommand;
	}

	public void setClusterRemotingCommand(ClusterRemotingCommand clusterRemotingCommand) {
		this.clusterRemotingCommand = clusterRemotingCommand;
	}

	@Override
	public String toString() {
		return "ResponseFuture{" + "opaque=" + opaque + ", channel=" + channel + ", timeoutMillis=" + timeoutMillis
				+ ", beginTime=" + beginTime + ", clusterRemotingCommand=" + clusterRemotingCommand + ", cause=" + cause
				+ ", invokeCallback=" + invokeCallback + ", callbackFlag=" + callbackFlag + '}';
	}
}
