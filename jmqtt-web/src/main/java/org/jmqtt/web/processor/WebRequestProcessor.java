package org.jmqtt.web.processor;

import org.jmqtt.web.common.WebRemotingCommand;

import io.netty.channel.ChannelHandlerContext;



public interface WebRequestProcessor {

	WebRemotingCommand processRequest(ChannelHandlerContext ctx, WebRemotingCommand cmd);

}
