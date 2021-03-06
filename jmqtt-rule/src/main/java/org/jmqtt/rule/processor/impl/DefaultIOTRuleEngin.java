package org.jmqtt.rule.processor.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
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
import org.jmqtt.common.bean.msgdata.MsgActionType;
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
import org.jmqtt.iot.processor.IotObjectEngin;
import org.jmqtt.remoting.session.ConnectManager;
import org.jmqtt.rule.common.RuleDest;
import org.jmqtt.rule.common.ZRuleCommand;
import org.jmqtt.rule.processor.RuleEngin;
import org.jmqtt.rule.processor.RuleResultProcessor;
import org.jmqtt.store.RuleMessageStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.UUIDGenerator;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import com.google.gson.Gson;

import io.github.glytching.tranquil.Tranquil;
import io.netty.handler.codec.mqtt.MqttFixedHeader;

public class DefaultIOTRuleEngin implements RuleEngin {

	DefaultRuleDespatcher defaultRuleDespatcher;

	private static final BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>(100000);
	private boolean stoped = false;
	private static final Logger log = LoggerFactory.getLogger(LoggerName.RULE);

	private RuleMessageStore ruleMessageStore;
	private ScheduledThreadPoolExecutor scheduleGetRuleExecutor;
	private final int FETCH_RULE_TIME_MILLIS = 5 * 60 * 1000;

	private RuleConfig ruleConfig;
	private ClusterMessageTransfer clusterMessageTransfer;
	
	private IotObjectEngin iotEngin;

	@Override
	public boolean filter(Message message) {
		
		if(!ruleConfig.isRuleenable())
		{
			log.debug("rule [Message] not enable ,skip!");
			return false;
		}
		
		
		boolean isNotFull = messageQueue.offer(message);
		if (!isNotFull) {
			log.warn("[rule Message] -> the buffer queue is full");
		}
		return isNotFull;
	}

	public DefaultIOTRuleEngin(int pollThreadNum, RuleMessageStore ruleMessageStore,
			SubscriptionMatcher subscriptionMatcher, MessageDispatcher messageDispatcher,
			ExecutorService executorService, RuleConfig ruleConfig, ClusterMessageTransfer clusterMessageTransfer) {
		this.pollThreadNum = pollThreadNum;

		this.ruleMessageStore = ruleMessageStore;

		this.scheduleGetRuleExecutor = new ScheduledThreadPoolExecutor(1,
				new ThreadFactoryImpl("scheduleFetchRuleThread"));

		this.ruleConfig = ruleConfig;
		this.clusterMessageTransfer = clusterMessageTransfer;

		this.defaultRuleDespatcher = new DefaultRuleDespatcher(10, subscriptionMatcher, messageDispatcher);

		RuleResultProcessor mqttProcessor = new DefaultMqttRuleProcessor(messageDispatcher, clusterMessageTransfer);
		defaultRuleDespatcher.registerProcessor(RuleType.REPUBLISH, mqttProcessor, executorService);
		defaultRuleDespatcher.registerProcessor(RuleType.ERROR, mqttProcessor, executorService);
	}

	
	  /**
     * 关联engin，转发引擎过滤日志
     * @param iotEngin
     * @author zj
     * @date 2020年2月13日
     */
    public void setIotEngin(IotObjectEngin iotEngin) {
    	this.iotEngin=iotEngin;
    }
  

	
	private ThreadPoolExecutor pollThread;
	private int pollThreadNum;

	/**
	 * 生成easyRule规则，解析本地rule相关数据
	 * 
	 * @param zrule
	 * @return
	 * @author zj
	 * @date 2019年12月30日
	 */
	private Rule TransFormToEasyRule(ZRule zrule) {

		// zrule.getTopic()

		String ruleName = zrule.getName();
		if (ruleName == null || ruleName.equals(""))
			ruleName = "test";
		// create rules
		Rule rule = new RuleBuilder().name(ruleName).description(zrule.getRuleDesc()).when(fact -> {

			// mqtt json数据 输入
			Message msg = fact.get("msg");

			String msgTopic = (String) msg.getHeader(MessageHeader.TOPIC);
			byte[] msgData = (byte[]) msg.getPayload();

			String jsonStr = "";
			try {
				jsonStr = new String(msgData, "utf-8");
				SerializeHelper.deserialize(msgData, String.class);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 通过Gson 格式化json str,引号之类的问题
			Gson gs = new Gson();
			try {

				JSONObject json = gs.fromJson(jsonStr, JSONObject.class);
				if (json == null)
					return false;

				// 基于Tranquil ANTLR4的sql语法解析，及json数据解析匹配过滤
				Date startDate = new Date();
				boolean isExist = Tranquil.parse(json).exists(zrule.getWhere());
				Date endDate = new Date();

				log.debug("Rule[Tranquil-ANTLR4] Paring using time {} ms ,with data :{} where:{}  ",
						endDate.getTime() - startDate.getTime(), json, zrule.getWhere());
				if (!isExist)
					return false;
				else
					return true;

			} catch (Exception e) {

				// TODO 记录规则引擎错误信息
				log.debug("rule 过滤错误:" + e.getMessage());

				transforError(zrule, msg, e.getMessage());

				return false;
			}
			/*
			 * // rule // zrule.getWhere().sp // TODO where 解析 String field = "data"; String
			 * action = ">"; int fieldData = 5;
			 * 
			 * int realdata = json.getIntValue(field);
			 * 
			 * if (msgTopic.equals(zrule.getTopic())) { if (action.equals(">")) { if
			 * (realdata > fieldData) return true; else return false;
			 * 
			 * } else if (action.equals("<")) { if (realdata < fieldData) return true; else
			 * return false; }
			 * 
			 * return true;
			 * 
			 * } else return false;
			 */

		}).then(fact -> {

			// TODO 匹配规则计数

			// mqtt json数据 输入
			Message msg = fact.get("msg");

			byte[] msgData = (byte[]) msg.getPayload();

			String jsonStr = "";
			try {
				jsonStr = new String(msgData, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 通过Gson 格式化json str,引号之类的问题
			Gson gs = new Gson();
			JSONObject json = gs.fromJson(jsonStr, JSONObject.class);
			// 基于ANTLR4的sql语法解析，及json数据解析匹配过滤出目标地需要的数据
			String jsonReturn = Tranquil.parse(json).read(zrule.getSelect(), zrule.getWhere());

			List<RuleDest> types = parseRuleType(zrule.getConfiguration());
			for (RuleDest rtype : types) {
				
				//正常过滤不转发错误数据
				if (rtype.getRtype() == RuleType.ERROR) 
					continue;
				// 生成目标处理数据
				ZRuleCommand rcommand = new ZRuleCommand();
				rcommand.setConfiguration(rtype.getConfiguration());
				rcommand.setRtype(rtype.getRtype());
				rcommand.setOriMessage(msg);
				rcommand.setProcessMsg(jsonReturn);
				rcommand.setWhere(zrule.getWhere());
				rcommand.setSelect(zrule.getSelect());

				defaultRuleDespatcher.appendMessage(rcommand);
			}
			
			
			//通过iotengin，记录
			msg.setMsgType(MsgActionType.Rule);
            iotEngin.saveMsgLog(msg.getClientId(), msg);
		

		}).build();

		return rule;
	}

	/**
	 * 规则引擎解析错误转发，如果规则引擎配置了错误转发
	 * 
	 * @param zrule
	 * @param orimsg
	 * @param errormsg
	 * @author zj
	 * @date 2020年1月17日
	 */
	private void transforError(ZRule zrule, Message orimsg, String errormsg) {
		List<RuleDest> types = parseRuleType(zrule.getConfiguration());
		for (RuleDest rtype : types) {

			if (rtype.getRtype() == RuleType.ERROR) {
				// 生成目标处理数据
				ZRuleCommand rcommand = new ZRuleCommand();
				rcommand.setConfiguration(rtype.getConfiguration());
				rcommand.setRtype(rtype.getRtype());
				rcommand.setOriMessage(orimsg);
				rcommand.setProcessMsg(errormsg);
				rcommand.setWhere(zrule.getWhere());
				rcommand.setSelect(zrule.getSelect());

				defaultRuleDespatcher.appendMessage(rcommand);

				break;
			}

		}
	}

	/**
	 * 根据configuration中的json 数组中 ruleType字段数据 获取转发动作
	 * 
	 * @param configuration
	 * @return
	 * @author zj
	 * @date 2020年1月17日
	 */
	private List<RuleDest> parseRuleType(String configuration) {
		List<RuleDest> types = new ArrayList<>();

		// 通过Gson 格式化json str,引号之类的问题
		Gson gs = new Gson();
		JSONArray jsonarray = gs.fromJson(configuration, JSONArray.class);
		if (jsonarray != null) {
			for (int i = 0; i < jsonarray.size(); i++) {
				JSONObject configDest = jsonarray.getJSONObject(i);
				String type = configDest.getString(RuleConstants.JSON_RULE_TYPE);
				if (type != null) {
					RuleType destType = RuleType.Parse(type);
					RuleDest rDest = new RuleDest(destType, configDest.toJSONString());
					types.add(rDest);
				}
			}
		}

		return types;
	}

	public boolean refreshRules(String productKey) {
		// HTTP
		
		
		if(!ruleConfig.isRuleenable())
			return false;
		

		List<ZRule> rules = new ArrayList<>();

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
					rules = JSONObject.parseArray(jsonobj.getString("data"), ZRule.class);
				}
			}
		} catch (Exception e) {
			log.debug("Rule Engin rule list Refresh Error:" + e.getMessage());
			return false;
		}

		// /manager/mqttrule

		if (rules == null || rules.size() == 0)
			return false;
		else {
			log.debug("productKey:{} rule refreshed!!!! size:{}, data:{}", productKey, rules.size(), rules.toString());
		}

		Map<String, List<ZRule>> productRules = new HashMap<>();

		/*
		 * JSONObject jconfig = new JSONObject(); jconfig.put("desttopic", "cool");
		 * ZRule r = new ZRule("testproduct", "1", "1", "test rule", "testtopic",
		 * " data > 4", jconfig.toString()); rules.add(r);
		 * 
		 * productRules.put("testproduct", rules);
		 */

		// 每次获取一个productKey下的rules
		productRules.put(rules.get(0).getProductKey(), rules);

		// 检查有变化的rule

		for (String pkey : productRules.keySet()) {

			// 刷新rule
			ruleMessageStore.removeRuleMessage(pkey);

			ruleMessageStore.storeRuleMessage(pkey, productRules.get(pkey));
		}

		return true;

	}

	@Override
	public void start() {

		this.scheduleGetRuleExecutor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				refreshRules("");
			}
		}, 5 * 1000, FETCH_RULE_TIME_MILLIS, TimeUnit.MILLISECONDS);

		defaultRuleDespatcher.start();

		this.pollThread = new ThreadPoolExecutor(pollThreadNum, pollThreadNum, 60 * 1000, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<>(100000), new ThreadFactoryImpl("ruleFilter"),
				new RejectHandler("ruleFilter", 100000));

		new Thread(new Runnable() {
			@Override
			public void run() {
				int waitTime = 1000;
				while (!stoped) {
					try {
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

	class AsyncDispatcher implements Runnable {

		private List<Message> messages;

		AsyncDispatcher(List<Message> messages) {
			this.messages = messages;
		}

		@Override
		public void run() {
			
			if(!ruleConfig.isRuleenable())
				return ;
			
			if (Objects.nonNull(messages)) {
				try {
					for (Message rulerst : messages) {

						// mqttUsername: deviceName+"&"+productKey
						String username = ConnectManager.getInstance().getUserName(rulerst.getClientId());
						if (username != null && username.contains("&")) {

							Rules rules = new Rules();

							String[] keys = username.split("&");
							if (keys.length == 2) {
								String productKey = keys[1];

								List<ZRule> oriRules = ruleMessageStore.getRuleMessage(productKey);
								if (oriRules != null && oriRules.size() > 0) {
									for (ZRule zRule2 : oriRules) {
										Rule r = TransFormToEasyRule(zRule2);
										rules.register(r);
									}

									// create a default rules engine and fire rules on known facts
									RulesEngine rulesEngine = new DefaultRulesEngine();

									Facts facts = new Facts();
									facts.put("msg", rulerst);

									rulesEngine.fire(rules, facts);
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
		defaultRuleDespatcher.shutdown();
	}

}
