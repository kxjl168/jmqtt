package org.jmqtt.web.remoting;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

import org.jmqtt.common.config.BrokerConfig;
import org.jmqtt.common.config.ClusterConfig;
import org.jmqtt.common.config.NettyConfig;
import org.jmqtt.common.config.RuleConfig;
import org.jmqtt.common.config.StoreConfig;
import org.jmqtt.common.config.WebConfig;
import org.jmqtt.common.helper.MixAll;
import org.jmqtt.common.helper.Pair;
import org.jmqtt.common.helper.RemotingHelper;
import org.jmqtt.common.helper.SerializeHelper;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.iot.processor.IotObjectEngin;
import org.jmqtt.remoting.exception.RemotingSendRequestException;
import org.jmqtt.web.common.WebHeaderConstant;
import org.jmqtt.web.common.WebRemotingCommand;
import org.jmqtt.web.common.WebRequestCode;
import org.jmqtt.web.common.WebResponseCode;
import org.jmqtt.rule.processor.RuleEngin;
import org.jmqtt.store.SubscriptionStore;
import org.jmqtt.web.processor.WebRequestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public abstract class AbstractNettyServer {

	private static final Logger log = LoggerFactory.getLogger(LoggerName.WEB);

	protected final Map<Integer /* code */, Pair<WebRequestProcessor, ExecutorService>> processorTable = new ConcurrentHashMap<>();

	//获取配置使用
	protected RuleEngin ruleEngin;
	protected BrokerConfig brokerConfig;
	protected NettyConfig nettyConfig;
	protected StoreConfig storeConfig;
	protected ClusterConfig clusterConfig;
	protected RuleConfig ruleConfig;
	protected WebConfig webConfig;
	protected SubscriptionStore subScriptionStore;
	
	protected IotObjectEngin iotEngin;

	/**
	 * Semaphore to limit maximum number of on-going asynchronous requests, which
	 * protects system memory footprint.
	 */
	private Semaphore semaphore;

	public AbstractNettyServer() {
		this.semaphore = new Semaphore(65536, true);
	}

	public AbstractNettyServer(int semaphore) {
		this.semaphore = new Semaphore(semaphore, true);
	}

	
	protected void processMessageReceived(ChannelHandlerContext ctx, WebRemotingCommand cmd) {
		if (cmd != null) {

			/*
			 * if (MessageFlag.COMPRESSED_FLAG == cmd.getFlag()) { byte[] body =
			 * cmd.getBody(); try { body = MixAll.uncompress(body); cmd.setBody(body); }
			 * catch (IOException e) { log.info("uncompress cluster message failure", e); }
			 * }
			 */
			switch (cmd.getType()) {
			case REQUEST_COMMAND:
				processRequest(ctx, cmd);
				break;
			case RESPONSE_COMMAND:
				// processResponse(ctx, cmd);
				break;
			default:
				break;
			}
		}
	}

	private void processRequest(ChannelHandlerContext ctx, WebRemotingCommand cmd) {
		final Pair<WebRequestProcessor, ExecutorService> pair = this.processorTable.get(cmd.getCode());
		final int opaque = cmd.getOpaque();
		final int code = cmd.getCode();
		String fromnode = cmd.getExtField(WebHeaderConstant.NODE_NAME);
		if (pair != null) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {

					log.debug("****web  request arrived ***  opaque :{},requestType:{}, fromNode:{} ", opaque,
							code + ":" + WebRequestCode. getRequestType(code), fromnode);

					final WebRemotingCommand responseCommand = pair.getObject1().processRequest(ctx, cmd);
					if (responseCommand != null) {

						responseCommand.setOpaque(opaque);
						responseCommand.makeResponseType();
						ctx.writeAndFlush(responseCommand).addListener(new ChannelFutureListener() {
							@Override
							public void operationComplete(ChannelFuture channelFuture) throws Exception {
								if (!channelFuture.isSuccess()) {
									log.warn("cluster transfer message failure,addr={}",
											RemotingHelper.getRemoteAddr(ctx.channel()));
								}
							}
						});
					}
				}
			};
			pair.getObject2().submit(runnable);
		} else {
			log.error("web request has no processor,code={}", cmd.getCode());
			WebRemotingCommand responseCommand = new WebRemotingCommand(WebResponseCode.REQUEST_CODE_NOT_SUPPORTED);
			responseCommand.setOpaque(opaque);
			responseCommand.makeResponseType();
			ctx.writeAndFlush(responseCommand);
		}
	}

	private void processResponse(ChannelHandlerContext ctx, WebRemotingCommand cmd) {
		/*
		 * final int opaque = cmd.getOpaque();
		 * 
		 * final int code = cmd.getCode();// zj modify final int rcode =
		 * cmd.getResponseCode();// zj modify // response failure , resend later if
		 * (rcode == ClusterResponseCode.RESPONSE_OK) { final ResponseFuture
		 * responseFuture = responseTable.get(opaque); if (responseFuture != null) {
		 * log.info("cluster receive response future, data {}.", cmd);
		 * responseFuture.setClusterRemotingCommand(cmd);
		 * responseFuture.executeCallback(); responseFuture.release(); } else {
		 * log.warn("cluster receive not exist response future, code={}, opaque={}.",
		 * code+":"+getRequestType(code), opaque); } } else {
		 * log.warn("receive response is error,response code={}",
		 * code+":"+getRequestType(code)); }
		 * 
		 * // zj add 清空原请求 ResponseFuture responseFuture = responseTable.get(opaque);//
		 * , responseFuture); if (responseFuture != null) {
		 * responseTable.remove(opaque); }
		 */
	}

	protected void registerProcessor(int requestCode, WebRequestProcessor processor, ExecutorService service) {
		this.processorTable.put(requestCode, new Pair(processor, service));
	}

}
