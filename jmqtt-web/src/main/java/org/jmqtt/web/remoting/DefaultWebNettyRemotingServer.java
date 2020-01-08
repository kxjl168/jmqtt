package org.jmqtt.web.remoting;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import org.jmqtt.common.config.BrokerConfig;
import org.jmqtt.common.config.ClusterConfig;
import org.jmqtt.common.config.NettyConfig;
import org.jmqtt.common.config.StoreConfig;
import org.jmqtt.common.config.WebConfig;
import org.jmqtt.common.helper.ThreadFactoryImpl;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.remoting.netty.NettyConnectHandler;
import org.jmqtt.remoting.netty.NettyEventExcutor;
import org.jmqtt.web.common.WebRemotingCommand;
import org.jmqtt.web.common.WebRequestCode;
import org.jmqtt.rule.processor.RuleEngin;
import org.jmqtt.web.processor.WebRequestProcessor;
import org.jmqtt.web.processor.impl.DefaultEnginRuleChangeProcessor;
import org.jmqtt.web.processor.impl.DefaultGroupProcessor;
import org.jmqtt.web.codec.NettyWebDecoder;
import org.jmqtt.web.codec.NettyWebEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * web netty服务端,提供给web服务端使用，单向接受web后台指令，<br>
 * 查询节点信息，在线，通知节点刷新规则，变化等等<br>
 * 修改节点配置信息，重启节点等等 TODO<br>
 * WebNettyRemotingServer.java.
 * 
 * @author zj
 * @version 1.0.1 2020年1月6日
 * @revision zj 2020年1月6日
 * @since 1.0.1
 */
public class DefaultWebNettyRemotingServer extends AbstractNettyServer implements WebRemotingServer {

	private static final Logger log = LoggerFactory.getLogger(LoggerName.WEB);

	private WebConfig webConfig;
	private EventLoopGroup selectorGroup;
	private EventLoopGroup ioGroup;
	private Class<? extends ServerChannel> clazz;
	private NettyEventExcutor nettyEventExcutor;
	private ServerBootstrap serverBootstrap;
	// private ScheduledExecutorService schudure = new
	// ScheduledThreadPoolExecutor(1,new
	// ThreadFactoryImpl("ScanResponseTableThread"));

	public DefaultWebNettyRemotingServer(RuleEngin ruleEngin, WebConfig wConfig, BrokerConfig brokerConfig,
			NettyConfig nettyConfig, StoreConfig storeConfig, ClusterConfig clusterConfig,
			ExecutorService executorService) {
		this.webConfig = wConfig;
		// if(!clusterConfig.isGroupUseEpoll()){
		selectorGroup = new NioEventLoopGroup(webConfig.getGroupSelectorThreadNum(),
				new ThreadFactoryImpl("GroupSelectorEventGroup"));
		ioGroup = new NioEventLoopGroup(webConfig.getGroupIoThreadNum(), new ThreadFactoryImpl("GroupIOEventGroup"));
		clazz = NioServerSocketChannel.class;
		/*
		 * }else{ selectorGroup = new
		 * EpollEventLoopGroup(clusterConfig.getGroupSelectorThreadNum(), new
		 * ThreadFactoryImpl("GroupSelectorEventGroup")); ioGroup = new
		 * EpollEventLoopGroup(clusterConfig.getGroupIoThreadNum(), new
		 * ThreadFactoryImpl("GroupIOEventGroup")); clazz =
		 * EpollServerSocketChannel.class; }
		 */
		this.nettyEventExcutor = new NettyEventExcutor(new WebServerChannelEventListener());
		this.serverBootstrap = new ServerBootstrap();

		this.brokerConfig = brokerConfig;
		this.nettyConfig = nettyConfig;
		this.storeConfig = storeConfig;
		this.clusterConfig = clusterConfig;
		this.ruleEngin = ruleEngin;

		registerDefaultProcessor(executorService);
	}

	/**
	 * 注册默认处理逻辑
	 * @param executorService
	 * @author zj
	 * @date 2020年1月6日
	 */
	private void registerDefaultProcessor(ExecutorService executorService) {
		DefaultEnginRuleChangeProcessor ruleProcessor = new DefaultEnginRuleChangeProcessor(this.ruleEngin);
		registerWebProcessor(WebRequestCode.SAVE_OR_UPDATE_RULE, ruleProcessor, executorService);
		
		DefaultGroupProcessor groupProcessor = new DefaultGroupProcessor();
		registerWebProcessor(WebRequestCode.QUERY_NODE_STATUS, groupProcessor, executorService);
	}

	@Override
	public void start() {
		this.serverBootstrap.group(selectorGroup, ioGroup).channel(clazz)
				.option(ChannelOption.SO_BACKLOG, webConfig.getGroupTcpBackLog())
				.childOption(ChannelOption.TCP_NODELAY, webConfig.isGroupTcpNoDelay())
				.childOption(ChannelOption.SO_SNDBUF, webConfig.getGroupTcpSndBuf())
				.option(ChannelOption.SO_RCVBUF, webConfig.getGroupTcpRcvBuf())
				.option(ChannelOption.SO_REUSEADDR, webConfig.isGroupTcpReuseAddr())
				.childOption(ChannelOption.SO_KEEPALIVE, webConfig.isGroupTcpKeepAlive())
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel socketChannel) throws Exception {
						ChannelPipeline pipeline = socketChannel.pipeline();
						pipeline.addLast("groupIdleStateHandler", new IdleStateHandler(0, 0, 60))
								.addLast("webDecoder", new NettyWebDecoder())
								.addLast("webEncoder", new NettyWebEncoder())
								.addLast("nettyConnectionManager", new NettyConnectHandler(nettyEventExcutor))
								.addLast("groupServerHandler", new NettyServerHandler());
					}
				});
		if (webConfig.isGroupPooledByteBufAllocatorEnable()) {
			this.serverBootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
		}
		// this.resendService.start();
		try {
			ChannelFuture future = this.serverBootstrap.bind(webConfig.getWebServerPort()).sync();
			future.addListener(
					(ChannelFutureListener) futured -> log.info("***** web netty server start complete!** "));
			log.info("Start web server success,port = {}", webConfig.getWebServerPort());
		} catch (InterruptedException ex) {
			log.error("Start web server failure.cause={}", ex);
		}

		/*
		 * schudure.scheduleAtFixedRate(new Runnable() {
		 * 
		 * @Override public void run() {
		 * NettyClusterRemotingServer.this.scanResponseTable(); } },3000,1000,
		 * TimeUnit.MILLISECONDS);
		 */
	}

	@Override
	public void shutdown() {
		if (selectorGroup != null) {
			selectorGroup.shutdownGracefully();
		}
		if (ioGroup != null) {
			ioGroup.shutdownGracefully();
		}
		// this.schudure.shutdown();
		// this.resendService.shutdown();
		log.info("shutdown web server success");
	}

	public void registerWebProcessor(int code, WebRequestProcessor processor, ExecutorService executorService) {
		registerProcessor(code, processor, executorService);
	}

	private class NettyServerHandler extends SimpleChannelInboundHandler<WebRemotingCommand> {

		@Override
		protected void channelRead0(ChannelHandlerContext channelHandlerContext, WebRemotingCommand webRemotingCommand)
				throws Exception {
			processMessageReceived(channelHandlerContext, webRemotingCommand);
		}
	}

}
