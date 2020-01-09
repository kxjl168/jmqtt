package com.ztgm.mqtt.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import org.jmqtt.common.bean.InvokeCallback;
import org.jmqtt.common.bean.ResponseFuture;
import org.jmqtt.common.bean.SemaphoreReleaseOnlyOnce;
import org.jmqtt.common.helper.Pair;
import org.jmqtt.common.helper.RemotingHelper;
import org.jmqtt.remoting.exception.RemotingConnectException;
import org.jmqtt.remoting.exception.RemotingSendRequestException;
import org.jmqtt.remoting.netty.NettyConnectHandler;
import org.jmqtt.remoting.netty.NettyEventExcutor;

import org.jmqtt.web.codec.NettyWebDecoder;
import org.jmqtt.web.codec.NettyWebEncoder;
import org.jmqtt.web.common.NettyWebResponseFuture;
import org.jmqtt.web.common.WebHeaderConstant;
import org.jmqtt.web.common.WebRemotingCommand;
import org.jmqtt.web.common.WebRequestCode;
import org.jmqtt.web.common.WebResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * web netty客户端，用于与mqtt节点 通信。 NettyClusterRemotingClient.java.
 * 
 * @author zj
 * @version 1.0.1 2019年12月12日
 * @revision zj 2019年12月12日
 * @since 1.0.1
 */
@Component
public class NettyWebRemotingClient implements WebNettyClient {

	private static final Logger log = LoggerFactory.getLogger(NettyWebRemotingClient.class);

	// private ClusterConfig clusterConfig;
	private EventLoopGroup ioGroup;
	private Class<? extends SocketChannel> clazz;
	private NettyEventExcutor nettyEventExcutor;
	private Bootstrap bootstrap;
	private Lock lockChannelTable = new ReentrantLock();
	// private ScheduledExecutorService schudure = new
	// ScheduledThreadPoolExecutor(1,new
	// ThreadFactoryImpl("ScanResponseTableThread"));
	private final int LOCK_CHANNEL_TIMEOUT = 3000;

	private final int CHANNEL_CONNETCT_TIMEOUT = 1000;
	private final ConcurrentMap<String, Channel> channelTable = new ConcurrentHashMap<>();

	private Semaphore semaphore;

	private final Map<Integer /* opaque */, ResponseFuture> responseTable = new ConcurrentHashMap<>();

	public NettyWebRemotingClient() {
		/*
		 * this.clusterConfig = clusterConfig; if(!clusterConfig.isGroupUseEpoll()){
		 */
		ioGroup = new NioEventLoopGroup(4, new ThreadFactoryImpl("GroupClientWorkEventGroup"));
		clazz = NioSocketChannel.class;
		/*
		 * }else{ ioGroup = new EpollEventLoopGroup(clusterConfig.getGroupIoThreadNum(),
		 * new ThreadFactoryImpl("GroupClientWorkEventGroup")); clazz =
		 * EpollSocketChannel.class; }
		 */
		this.nettyEventExcutor = new NettyEventExcutor(new WebServerChannelEventListener());
		this.bootstrap = new Bootstrap();

		this.semaphore = new Semaphore(65536, true);
		
		start();
	}

	// @Override
	public void start() {
		this.bootstrap.group(ioGroup).channel(clazz)

				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000) // connect timeout

				//.option(ChannelOption.SO_BACKLOG, 10240)
				.option(ChannelOption.SO_KEEPALIVE, true)
				.option(ChannelOption.TCP_NODELAY, true).option(ChannelOption.SO_SNDBUF, 64 * 1024)
				.option(ChannelOption.SO_RCVBUF, 64 * 1024)

				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel socketChannel) throws Exception {
						ChannelPipeline pipeline = socketChannel.pipeline();
						pipeline.addLast("groupIdleStateHandler", new IdleStateHandler(0, 0, 60))
								.addLast("groupEncoder", new NettyWebEncoder())
								.addLast("groupDecoder", new NettyWebDecoder())
								.addLast("nettyConnectionManager", new NettyConnectHandler(nettyEventExcutor))
								.addLast("groupHandler", new NettyClientHandler());
					}
				});
		// if (clusterConfig.isGroupPooledByteBufAllocatorEnable()) {
		bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
		// }
		// this.resendService.start();
		log.info("init cluster client success");

	}

	// @Override
	public void shutdown() {
		if (ioGroup != null) {
			ioGroup.shutdownGracefully();
		}
		// this.schudure.shutdown();
		// this.resendService.shutdown();
		log.info("shutdown cluster client success");
	}

	 @Override
	public void invoke(boolean isAsync,String addr, WebRemotingCommand command, long timeoutMills, InvokeCallback callback)
			throws Exception, RemotingConnectException {
		final Channel channel = getOrCreateChannel(addr);
		if (channel != null) {
			byte[] body = command.getBody();
			try {
				/*
				 * if (body.length > clusterConfig.getCompressMaxSize()) { body =
				 * MixAll.compress(body, 5); command.setBody(body);
				 * command.setFlag(MessageFlag.COMPRESSED_FLAG); }
				 */

				// zj add

				command.putExtFiled(WebHeaderConstant.NODE_ADDRESS, "web1");

				this.invokeAsyncImpl(isAsync,channel, command, timeoutMills, callback);
			} catch (Exception ex) {
				throw new RemotingSendRequestException(ex.getMessage());
			}
		} else {
			log.warn("connect this addr failure,addr={}", addr);
			throw new RemotingConnectException(addr);
		}
	}

	

	private void invokeAsyncImpl(boolean isAsync,final Channel channel, final WebRemotingCommand command, final long timeout,
			InvokeCallback invokeCallback) throws RemotingSendRequestException {
		final int opaque = command.getOpaque();
		try {
			SemaphoreReleaseOnlyOnce semaphoreReleaseOnlyOnce = new SemaphoreReleaseOnlyOnce(semaphore);
			NettyWebResponseFuture responseFuture = new NettyWebResponseFuture(channel, opaque, timeout, invokeCallback,
					semaphoreReleaseOnlyOnce);
			responseFuture.setWebRemotingCommand(command);
			boolean tryAquired = semaphore.tryAcquire(timeout, TimeUnit.MILLISECONDS);
			if (tryAquired) {
				// zj 备注，记录请求id，异步回调根据id找原请求
				responseTable.put(opaque, responseFuture);
				final String remotingAddr = command.getExtField(WebHeaderConstant.TO_NODE_ADDRESS);
				// RemotingHelper.getRemoteAddr(channel);

				// zj
				log.debug("****web inner request begin***  opaque :{}, dest:{},requestType:{},chnnel:{}",
						String.valueOf(opaque), remotingAddr,
						command.getCode() + ":" + WebRequestCode.getRequestType(command.getCode()), channel.toString());

				ChannelFuture cfuture= channel.writeAndFlush(command);
				cfuture.addListener(new ChannelFutureListener() {
					@Override
					public void operationComplete(ChannelFuture channelFuture) throws Exception {
						if (channelFuture.isSuccess()) {
							responseFuture.setSendRequestOK(true);
							return;
						}
						// requestFail(opaque, command);
						log.warn("【node error】 inner send a request command to channel <{}> failed.", remotingAddr);
					}
				});
				
				if(!isAsync&&cfuture!=null)
				 cfuture.await(50);
				
				
			} else {
				// requestFail(opaque, command);
				log.warn("Async invoke aquire semaphore failure,waiting threadNums:{},semaphoreAsyncValue:{}",
						semaphore.getQueueLength(), semaphore.availablePermits());
			}
		} catch (Exception ex) {
			log.info("send request failure", ex);
			throw new RemotingSendRequestException("send request failure");
		}
	}

	private Channel getOrCreateChannel(String addr) {
		Channel channel = this.channelTable.get(addr);
		if (channel != null && channel.isActive()) {
			return channel;
		}
		return createChannel(addr);
	}

	/**
	 * 连接目标节点，并等待连接成功
	 * 
	 * @param addr
	 * @return
	 * @author zj
	 * @date 2019年12月24日 备注
	 */
	private Channel createChannel(String addr) {
		// new connect

		Channel channelrst = null;
		try {
			if (lockChannelTable.tryLock(LOCK_CHANNEL_TIMEOUT, TimeUnit.MILLISECONDS)) {
				Channel channel = this.channelTable.get(addr);
				if (channel != null) {
					channel.close();
					this.channelTable.remove(addr);
				}
				ChannelFuture channelFuture = this.bootstrap.connect(RemotingHelper.string2SocketAddress(addr));
				Channel newChannel = channelFuture.channel();
				this.channelTable.put(addr, newChannel);

				channelrst = newChannel;
				// zj 等待
				channelFuture.get(CHANNEL_CONNETCT_TIMEOUT, TimeUnit.MILLISECONDS);// 等待连接完成

			} else {
				log.warn("try lock channel table to cache channel failure,addr:{}", addr);
			}
		} catch (InterruptedException e) {
			log.warn("create new channel failure,remote address={},ex={}", addr, e);
		} catch (ExecutionException e) {
			log.warn("create new channel execution error,remote address={},ex={}", addr, e);
		} catch (TimeoutException e) {
			log.warn(" 【create new channel timeout】 ,remote address={},ex={}", addr, e.getMessage());
		} finally {
			lockChannelTable.unlock();
		}
		return channelrst;
	}

	private void processMessageReceived(ChannelHandlerContext ctx, WebRemotingCommand cmd) {

		final int opaque = cmd.getOpaque();

		final int code = cmd.getCode();// zj modify
		final int rcode = cmd.getResponseCode();// zj modify // response failure , resend later
		if (rcode == WebResponseCode.RESPONSE_OK) {
			final NettyWebResponseFuture responseFuture = (NettyWebResponseFuture) responseTable.get(opaque);
			if (responseFuture != null) {
				log.info("web receive response future, data {}.", cmd);
				responseFuture.setWebRemotingCommand(cmd);
				responseFuture.executeCallback();
				responseFuture.release();
			} else {
				log.warn("web receive not exist response future, code={}, opaque={}.",
						code + ":" + WebRequestCode.getRequestType(code), opaque);
			}
		} else {
			log.warn("receive response is error,response code={}", code + ":" + WebRequestCode.getRequestType(code));
		}

		// zj add 清空原请求
		ResponseFuture responseFuture = responseTable.get(opaque);//
		// , responseFuture);
		if (responseFuture != null) {
			responseTable.remove(opaque);
		}

	}

	private class NettyClientHandler extends SimpleChannelInboundHandler<WebRemotingCommand> {
		@Override
		protected void channelRead0(ChannelHandlerContext channelHandlerContext,
				WebRemotingCommand clusterRemotingCommand) throws Exception {
			processMessageReceived(channelHandlerContext, clusterRemotingCommand);
		}
	}

}
