package org.jmqtt.group.remoting.codec;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.jmqtt.remoting.netty.ChannelEventListener;
import org.jmqtt.remoting.netty.NettyConnectHandler;
import org.jmqtt.remoting.netty.NettyEventExcutor;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultThreadFactory;

public class test2 {
	public static void main(String[] atgs) {

		NioEventLoopGroup ioGroup = new NioEventLoopGroup(4, new DefaultThreadFactory("test"));
		Class clazz = NioSocketChannel.class;

		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(ioGroup).channel(clazz)
				// .option(ChannelOption.SO_BACKLOG, clusterConfig.getGroupTcpBackLog())
				.option(ChannelOption.SO_SNDBUF, 65536).option(ChannelOption.SO_RCVBUF, 65536)
				.option(ChannelOption.SO_REUSEADDR, true).option(ChannelOption.SO_KEEPALIVE, true)
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000) // connect timeout
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel socketChannel) throws Exception {
						ChannelPipeline pipeline = socketChannel.pipeline();
						pipeline.addLast("mqttDecoder", new MqttDecoder()).addLast("mqttEncoder", MqttEncoder.INSTANCE)
								.addLast("connectManager",
										new NettyConnectHandler(new NettyEventExcutor(new ChannelEventListener() {

											@Override
											public void onChannelIdle(String remoteAddr, Channel channel) {
												// TODO Auto-generated method stub

											}

											@Override
											public void onChannelException(String remoteAddr, Channel channel) {
												// TODO Auto-generated method stub

											}

											@Override
											public void onChannelConnect(String remoteAddr, Channel channel) {
												System.out.println("connect");
											}

											@Override
											public void onChannelClose(String remoteAddr, Channel channel) {
												System.out.println("connect");

											}
										})));
						// .addLast("idleStateHandler", new
						// IdleStateHandler(MqttClientImpl.this.clientConfig.getTimeoutSeconds(),
						// MqttClientImpl.this.clientConfig.getTimeoutSeconds(), 0))
						// .addLast("mqttPingHandler", new
						// MqttPingHandler(MqttClientImpl.this.clientConfig.getTimeoutSeconds()));

						// .addLast("nettyConnectionManager", new
						// NettyConnectHandler(nettyEventExcutor))
						// .addLast("groupHandler", new NettyClientHandler());
					}
				});

		
		 bootstrap.localAddress("192.168.100.41",50000);
		
		SocketAddress remoteAddress = new InetSocketAddress("127.0.0.1", 21883);
		bootstrap.connect(remoteAddress).addListener(f -> {
			System.out.println(f.isSuccess());
			System.out.println(bootstrap.config().localAddress().toString());
		});

	}
}
