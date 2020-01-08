package com.ztgm.mqtt.netty;

import org.jmqtt.common.bean.InvokeCallback;
import org.jmqtt.remoting.exception.RemotingConnectException;
import org.jmqtt.web.common.WebRemotingCommand;

public interface WebNettyClient {
	public void invoke(boolean isAsync,String addr, WebRemotingCommand command, long timeoutMills, InvokeCallback callback) throws Exception, RemotingConnectException ;
}
