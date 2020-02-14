package org.jmqtt.iot.processor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;
import org.jmqtt.common.bean.RuleType;
import org.jmqtt.common.bean.Topic;
import org.jmqtt.common.bean.iot.IotActionData;
import org.jmqtt.common.bean.iot.IotActionType;
import org.jmqtt.common.bean.iot.IotObject;
import org.jmqtt.common.bean.iot.IotTopic;
import org.jmqtt.common.helper.Pair;
import org.jmqtt.common.helper.RejectHandler;
import org.jmqtt.common.helper.SerializeHelper;
import org.jmqtt.common.helper.ThreadFactoryImpl;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.iot.helper.TopicHelper;
import org.jmqtt.iot.processor.ObjectMessageAction;
import org.jmqtt.iot.processor.ObjectMessageParse;
import org.jmqtt.rule.common.ZRuleCommand;
import org.jmqtt.rule.processor.RuleResultProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultMessageParse implements ObjectMessageParse {

	private static final Logger log = LoggerFactory.getLogger(LoggerName.IOTMODEL);
	private boolean stoped = false;
	private static final BlockingQueue<Pair<IotObject, Message>> messageQueue = new LinkedBlockingQueue<>(100000);

	private Map<IotActionType, Pair<ObjectMessageAction, ExecutorService>> processorTable;

	private ThreadPoolExecutor pollThread;
	private int pollThreadNum;

	public DefaultMessageParse(int pollThreadNum) {
		this.processorTable = new HashMap();
		
		this.pollThreadNum=pollThreadNum;
	}

	@Override
	public boolean addparse(IotObject iotObject, Message message) {
		boolean isNotFull = messageQueue.offer(new Pair<IotObject, Message>(iotObject, message));
		if (!isNotFull) {
			log.warn("[addparse] -> the buffer queue is full");
		}
		return isNotFull;
	}

	@Override
	public void start() {
		this.pollThread = new ThreadPoolExecutor(pollThreadNum, pollThreadNum, 60 * 1000, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<>(100000), new ThreadFactoryImpl("iotModelParse"),
				new RejectHandler("iotModelParse", 100000));

		new Thread(new Runnable() {
			@Override
			public void run() {
				int waitTime = 1000;
				while (!stoped) {
					try {
						List<Pair<IotObject, Message>> messageList = new ArrayList(32);
						Pair<IotObject, Message> message;
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
	public void shutdown() {
		this.stoped = true;
		this.pollThread.shutdown();
	}

	/**
	 * 根据具体物模型及收到的消息，对比消息topic及物模型，生成具体的目标处理预处理数据
	 * 
	 * @param indata
	 * @return
	 * @author zj
	 * @date 2020年1月22日
	 */
	private IotActionData parseData(Pair<IotObject, Message> indata) {
		IotActionData data = new IotActionData();

		IotObject iotObj = indata.getObject1();
		Message msg = indata.getObject2();

		Topic topic = new Topic(String.valueOf(msg.getHeader(MessageHeader.TOPIC)), Integer.parseInt(String.valueOf(msg.getHeader(MessageHeader.QOS))));
		
	
		String jsonData= SerializeHelper.deserialize(msg.getPayload(),String.class);
		
		IotActionType atype=TopicHelper.ParesAType(iotObj, msg);
		
		data.setActionType(atype);
		data.setActionData(jsonData);
		//具体targetName在 jsonData中，实际具体的actionType解析时处理
		//data.setActionTargeName(actionTargeName);
		data.setTopic(topic);
		data.setOriMessage(msg);

		return data;
	}

	class AsyncDispatcher implements Runnable {

		private List<Pair<IotObject, Message>> messages;

		AsyncDispatcher(List<Pair<IotObject, Message>> messages) {
			this.messages = messages;
		}

		@Override
		public void run() {
			if (Objects.nonNull(messages)) {
				try {
					for (Pair<IotObject, Message> rulerst : messages) {

						IotActionData data = parseData(rulerst);

						if (processorTable.get(data.getActionType()) != null) {

							Runnable runnable = new Runnable() {
								@Override
								public void run() {
									processorTable.get(data.getActionType()).getObject1().processRequest(data);
								}
							};

							processorTable.get(data.getActionType()).getObject2().submit(runnable);
						} else {
							log.warn("ObjectMesageParse中 {} 类型消息没有处理程序!", data.getActionType().toString());
						}

					}
				} catch (Exception ex) {
					log.warn("ObjectMesageParse message failure,cause={}", ex);
				}
			}
		}

	}

	public void registerActionProcessor(IotActionType aType, ObjectMessageAction processor,
			ExecutorService executorService) {
		this.processorTable.put(aType, new Pair<>(processor, executorService));
	}

}
