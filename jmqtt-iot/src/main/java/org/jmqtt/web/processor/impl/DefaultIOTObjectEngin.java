package org.jmqtt.web.processor.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.http.impl.cookie.BasicClientCookie;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;
import org.jmqtt.common.bean.RuleType;
import org.jmqtt.common.bean.ZRule;
import org.jmqtt.common.bean.iot.IotObject;
import org.jmqtt.common.config.RuleConfig;
import org.jmqtt.common.constant.RuleConstants;
import org.jmqtt.common.helper.HttpSendPostNew;
import org.jmqtt.common.helper.RejectHandler;
import org.jmqtt.common.helper.SerializeHelper;
import org.jmqtt.common.helper.ThreadFactoryImpl;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.common.message.MessageDispatcher;
import org.jmqtt.common.subscribe.SubscriptionMatcher;
import org.jmqtt.group.ClusterMessageTransfer;
import org.jmqtt.remoting.session.ConnectManager;
import org.jmqtt.rule.common.RuleDest;
import org.jmqtt.rule.common.ZRuleCommand;
import org.jmqtt.rule.processor.RuleEngin;
import org.jmqtt.rule.processor.RuleResultProcessor;
import org.jmqtt.store.ObjectModelMessageStore;
import org.jmqtt.store.RuleMessageStore;
import org.jmqtt.web.processor.IotObjectEngin;
import org.jmqtt.web.processor.ObjectMessageParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.UUIDGenerator;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import com.google.gson.Gson;


import io.github.glytching.tranquil.Tranquil;
import io.netty.handler.codec.mqtt.MqttFixedHeader;

public class DefaultIOTObjectEngin implements IotObjectEngin {

	// DefaultRuleDespatcher defaultRuleDespatcher;
	private static ConcurrentHashMap<String, BlockingQueue<Message>> messageQueues = new ConcurrentHashMap<>();

	// private static final BlockingQueue<Message> messageQueue = new
	// LinkedBlockingQueue<>(100000);
	private boolean stoped = false;
	private static final Logger log = LoggerFactory.getLogger(LoggerName.RULE);

	private ObjectModelMessageStore iotObjectMessageStore;
	private ScheduledThreadPoolExecutor scheduleGetRuleExecutor;
	private final int FETCH_RULE_TIME_MILLIS = 5 * 60 * 1000;

	private RuleConfig ruleConfig;
	private ClusterMessageTransfer clusterMessageTransfer;

	private ObjectMessageParse objectMessageParse;

	@Override
	public boolean dealObjectMessage(Message message) {

		log.debug("[dealObjectMessage] " + message.toString());

		String username = ConnectManager.getInstance().getUserName(message.getClientId());
		if (username != null && username.contains("&")) {

			Rules rules = new Rules();

			String[] keys = username.split("&");
			if (keys.length == 2) {
				String deviceName = keys[0];

				BlockingQueue<Message> messageQueue = messageQueues.get(deviceName);
				if (messageQueue == null) {
					messageQueue = new LinkedBlockingQueue<>(100000);
					messageQueues.put(deviceName, messageQueue);

					// messageQueue = messageQueues.get(message.getGatewayid());
				}

				boolean isNotFull = messageQueue.offer(message);
				if (!isNotFull) {
					log.warn("[Message] -> the buffer queue is full");
				}
				return isNotFull;

			}
		}
		log.debug("No deviceId or ProuductKey ,Message is not device upload, do not deal");
		return false;

	}

	public DefaultIOTObjectEngin(int pollThreadNum, ObjectModelMessageStore iotObjectMessageStore,
			SubscriptionMatcher subscriptionMatcher, MessageDispatcher messageDispatcher,
			ExecutorService executorService, RuleConfig ruleConfig, ClusterMessageTransfer clusterMessageTransfer) {
		this.pollThreadNum = pollThreadNum;

		this.iotObjectMessageStore = iotObjectMessageStore;

		this.scheduleGetRuleExecutor = new ScheduledThreadPoolExecutor(1,
				new ThreadFactoryImpl("scheduleFetchRuleThread"));

		this.ruleConfig = ruleConfig;
		this.clusterMessageTransfer = clusterMessageTransfer;

		// this.objectMessageParse=
		// this.defaultRuleDespatcher = new DefaultRuleDespatcher(10,
		// subscriptionMatcher, messageDispatcher);

		// RuleResultProcessor mqttProcessor = new
		// DefaultMqttRuleProcessor(messageDispatcher, clusterMessageTransfer);
		// defaultRuleDespatcher.registerProcessor(RuleType.REPUBLISH, mqttProcessor,
		// executorService);
		// defaultRuleDespatcher.registerProcessor(RuleType.ERROR, mqttProcessor,
		// executorService);
	}

	private ThreadPoolExecutor pollThread;
	private int pollThreadNum;

	public boolean refreshObjectModels(String productKey) {
		// HTTP

		if (!ruleConfig.isRuleenable())
			return false;

		List<IotObject> iotModels = new ArrayList<>();

		String token = "";
		String url = this.ruleConfig.getRuleweburl();

		JSONObject jobj = new JSONObject();
		jobj.put("productKey", productKey);
		String str = jobj.toString();

		List<BasicClientCookie> cks = new ArrayList<BasicClientCookie>();
		HashMap<String, String> headerstrs = new HashMap<>();
		headerstrs.put("User-Token", "8add95975fc743acb89f4d325717cfeb");
		try {
			String rst = HttpSendPostNew.sendHttpJSONDataNoSSL(true, token, false, url, str, headerstrs, cks);
			if (rst != null && !rst.equals("")) {
				JSONObject jsonobj = JSONObject.parseObject(rst);
				if (jsonobj.getString("code").equals("100")) {
					iotModels = JSONObject.parseArray(jsonobj.getString("data"), IotObject.class);
				}
			}
		} catch (Exception e) {
			log.debug("objectModel Engin model list Refresh Error:" + e.getMessage());
			return false;
		}

		// /manager/mqttrule

		if (iotModels == null || iotModels.size() == 0)
			return false;
		else {
			log.debug("productKey:{} objectModel refreshed!!!! size:{}, data:{}", productKey, iotModels.size(),
					iotModels.toString());
		}

		/*
		 * JSONObject jconfig = new JSONObject(); jconfig.put("desttopic", "cool");
		 * ZRule r = new ZRule("testproduct", "1", "1", "test rule", "testtopic",
		 * " data > 4", jconfig.toString()); rules.add(r);
		 * 
		 * productRules.put("testproduct", rules);
		 */

		for (IotObject iotObject : iotModels) {

			// 刷新rule
			iotObjectMessageStore.removeIotObjectMessage(iotObject.getProductKey());

			iotObjectMessageStore.storeIotObjectMessage(iotObject.getProductKey(), iotObject);
		}

		return true;

	}

	@Override
	public void start() {

		this.scheduleGetRuleExecutor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				refreshObjectModels("");
			}
		}, 5 * 1000, FETCH_RULE_TIME_MILLIS, TimeUnit.MILLISECONDS);

		// defaultRuleDespatcher.start();

		this.pollThread = new ThreadPoolExecutor(pollThreadNum, pollThreadNum, 60 * 1000, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<>(100000), new ThreadFactoryImpl("iotModelFilter"),
				new RejectHandler("iotModelFilter", 100000));

		new Thread(new Runnable() {
			@Override
			public void run() {
				int waitTime = 1000;
				while (!stoped) {
					try {

						Enumeration<String> deviceIds = messageQueues.keys();

						while (deviceIds.hasMoreElements()) {
							String deviceId = deviceIds.nextElement();

							BlockingQueue<Message> messageQueue = messageQueues.get(deviceId);
							
							List<Message> messageList = new ArrayList(32);
							
							
							Message message;
							for (int i = 0; i < 32; i++) {
								if (i == 0) {
									message = messageQueue.poll(waitTime, TimeUnit.MILLISECONDS);
								} else {
									message = messageQueue.poll();
								}
								if (Objects.nonNull(message)) {
									messageList.add(message);
									messageQueues.remove(deviceId);//设备从队列中移除
								} else {
									break;
								}
							}
							if (messageList.size() > 0) {
								AsyncDispatcher dispatcher = new AsyncDispatcher(messageList);
								pollThread.submit(dispatcher).get();
							}
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

	class AsyncDispatcher implements Runnable {

		private List<Message> messages;

		AsyncDispatcher(List<Message> messages) {
			this.messages = messages;
		}

		@Override
		public void run() {

			if (!ruleConfig.isRuleenable())
				return;

			if (Objects.nonNull(messages)) {
				try {
					for (Message message : messages) {

						// mqttUsername: deviceName+"&"+productKey
						String username = ConnectManager.getInstance().getUserName(message.getClientId());
						if (username != null && username.contains("&")) {

							Rules rules = new Rules();

							String[] keys = username.split("&");
							if (keys.length == 2) {
								String productKey = keys[1];

								IotObject oriRule = iotObjectMessageStore.getIotObjectMessage(productKey);
								if (oriRule != null) {

									if (objectMessageParse != null)
										objectMessageParse.addparse(oriRule, message);
								}
							}
						}

					}
				} catch (Exception ex) {
					log.warn("Dispatcher message failure,cause={}", ex);
				}
			}
		}

	}

	@Override
	public void shutdown() {

		this.stoped = true;
		this.pollThread.shutdown();
		// defaultRuleDespatcher.shutdown();
	}

}
