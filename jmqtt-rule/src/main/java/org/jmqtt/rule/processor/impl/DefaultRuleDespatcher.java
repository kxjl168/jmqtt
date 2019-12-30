package org.jmqtt.rule.processor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;
import org.jmqtt.common.bean.RuleType;
import org.jmqtt.common.bean.Subscription;
import org.jmqtt.common.helper.Pair;
import org.jmqtt.common.helper.RejectHandler;
import org.jmqtt.common.helper.ThreadFactoryImpl;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.common.message.MessageDispatcher;
import org.jmqtt.common.subscribe.SubscriptionMatcher;
import org.jmqtt.remoting.netty.RequestProcessor;
import org.jmqtt.remoting.session.ClientSession;
import org.jmqtt.remoting.session.ConnectManager;
import org.jmqtt.remoting.util.MessageUtil;
import org.jmqtt.remoting.util.NettyUtil;
import org.jmqtt.rule.common.ZRuleCommand;
import org.jmqtt.rule.processor.RuleDispatcher;
import org.jmqtt.rule.processor.RuleResultProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttPublishMessage;


public class DefaultRuleDespatcher implements RuleDispatcher {

	private static final Logger log = LoggerFactory.getLogger(LoggerName.RULE);
	private boolean stoped = false;
	private static final BlockingQueue<ZRuleCommand> messageQueue = new LinkedBlockingQueue<>(100000);

	private Map<RuleType, Pair<RuleResultProcessor, ExecutorService>> processorTable;

	private ThreadPoolExecutor pollThread;
	private int pollThreadNum;
	
	private MessageDispatcher messageDispatcher;

	/*
	 * private FlowMessageStore flowMessageStore; private OfflineMessageStore
	 * offlineMessageStore;
	 */

	public DefaultRuleDespatcher(int pollThreadNum,
			SubscriptionMatcher subscriptionMatcher,MessageDispatcher messageDispatcher)/*
													 * , FlowMessageStore flowMessageStore, OfflineMessageStore
													 * offlineMessageStore) {
													 */
	{
		this.pollThreadNum = pollThreadNum;
	
		// this.flowMessageStore = flowMessageStore;
		// this.offlineMessageStore = offlineMessageStore;

		this.processorTable = new HashMap();
		this.messageDispatcher=messageDispatcher;
		
		//registerProcessor(RuleType.REPUBLISH, messageDispatcher, executorService);
	}

	public void registerProcessor(RuleType rType, RuleResultProcessor processor, ExecutorService executorService) {
		this.processorTable.put(rType, new Pair<>(processor, executorService));
	}

	@Override
	public void start() {
		this.pollThread = new ThreadPoolExecutor(pollThreadNum, pollThreadNum, 60 * 1000, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<>(100000), new ThreadFactoryImpl("ruleDespatcher"),
				new RejectHandler("ruleMessage", 100000));

		new Thread(new Runnable() {
			@Override
			public void run() {
				int waitTime = 1000;
				while (!stoped) {
					try {
						List<ZRuleCommand> messageList = new ArrayList(32);
						ZRuleCommand message;
						for (int i = 0; i < 32; i++) {
							if (i == 0) {
								message = messageQueue.poll(waitTime, TimeUnit.MILLISECONDS);
							} else {
								message = messageQueue.poll();
							}
							if (Objects.nonNull(message)) {
								messageList.add(message);
							} else {
								break;
							}
						}
						if (messageList.size() > 0) {
							AsyncDispatcher dispatcher = new AsyncDispatcher(messageList);
							pollThread.submit(dispatcher).get();
						}
					} catch (InterruptedException e) {
						log.warn("poll message wrong.");
					} catch (ExecutionException e) {
						log.warn("AsyncDispatcher get() wrong.");
					}
				}
			}
		}).start();
	}

	@Override
	public boolean appendMessage(ZRuleCommand message) {
		boolean isNotFull = messageQueue.offer(message);
		if (!isNotFull) {
			log.warn("[PubMessage] -> the buffer queue is full");
		}
		return isNotFull;
	}

	@Override
	public void shutdown() {
		this.stoped = true;
		this.pollThread.shutdown();
	}

	;

	class AsyncDispatcher implements Runnable {

		private List<ZRuleCommand> messages;

		AsyncDispatcher(List<ZRuleCommand> messages) {
			this.messages = messages;
		}

		@Override
		public void run() {
			if (Objects.nonNull(messages)) {
				try {
					for (ZRuleCommand rulerst : messages) {

						RuleType ruleType = rulerst.getRtype();

						Runnable runnable = new Runnable() {
							@Override
							public void run() {
								processorTable.get(ruleType).getObject1().processRequest(rulerst);
							}
						};

						processorTable.get(ruleType).getObject2().submit(runnable);


					}
				} catch (Exception ex) {
					log.warn("Dispatcher message failure,cause={}", ex);
				}
			}
		}

	}
}
