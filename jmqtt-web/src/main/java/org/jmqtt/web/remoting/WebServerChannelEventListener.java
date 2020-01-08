package org.jmqtt.web.remoting;

import io.netty.channel.Channel;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.remoting.netty.ChannelEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * solve cluster connect event
 */
public class WebServerChannelEventListener implements ChannelEventListener {

    private static final Logger log = LoggerFactory.getLogger(LoggerName.WEB);

    @Override
    public void onChannelConnect(String remoteAddr, Channel channel) {

    }

    @Override
    public void onChannelClose(String remoteAddr, Channel channel) {

    }

    @Override
    public void onChannelIdle(String remoteAddr, Channel channel) {

    }

    @Override
    public void onChannelException(String remoteAddr, Channel channel) {

    }
}
