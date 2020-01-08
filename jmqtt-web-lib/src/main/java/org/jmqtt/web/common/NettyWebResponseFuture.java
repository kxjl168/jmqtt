package org.jmqtt.web.common;

import io.netty.channel.Channel;

import org.jmqtt.common.bean.InvokeCallback;
import org.jmqtt.common.bean.ResponseFuture;
import org.jmqtt.common.bean.SemaphoreReleaseOnlyOnce;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * cache request until receive response or store response to the local storage
 */
public class NettyWebResponseFuture extends ResponseFuture {

	private volatile WebRemotingCommand webRemotingCommand;

	public NettyWebResponseFuture(Channel channel, int opaque, long timeoutMillis, InvokeCallback invokeCallback,
			SemaphoreReleaseOnlyOnce semaphoreReleaseOnlyOnce) {
		super(channel, opaque, timeoutMillis, invokeCallback, semaphoreReleaseOnlyOnce);

	}



	@Override
	public String toString() {
		return "ResponseFuture{" + "opaque=" + opaque + ", channel=" + channel + ", timeoutMillis=" + timeoutMillis
				+ ", beginTime=" + beginTime 
				+ ", WebRemotingCommand=" + webRemotingCommand 
				+ ", cause=" + cause
				+ ", invokeCallback=" + invokeCallback + ", callbackFlag=" + callbackFlag + '}';
	}



	public WebRemotingCommand getWebRemotingCommand() {
		return webRemotingCommand;
	}



	public void setWebRemotingCommand(WebRemotingCommand webRemotingCommand) {
		this.webRemotingCommand = webRemotingCommand;
	}
}
