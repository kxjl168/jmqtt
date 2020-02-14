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
import org.jmqtt.common.bean.msgdata.MsgActionType;
import org.jmqtt.common.bean.msgdata.MsgLog;

import org.jmqtt.common.helper.DateUtil;
import org.jmqtt.common.helper.Pair;
import org.jmqtt.common.helper.RejectHandler;
import org.jmqtt.common.helper.SerializeHelper;
import org.jmqtt.common.helper.ThreadFactoryImpl;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.group.common.ClusterNodeManager;
import org.jmqtt.iot.helper.TopicHelper;
import org.jmqtt.iot.processor.LogPipeLine;
import org.jmqtt.iot.processor.MsgLogProcessor;
import org.jmqtt.iot.processor.ObjectMessageAction;
import org.jmqtt.iot.processor.ObjectMessageParse;
import org.jmqtt.remoting.session.ConnectManager;
import org.jmqtt.rule.common.ZRuleCommand;
import org.jmqtt.rule.processor.RuleResultProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultMsgLogProcessor implements MsgLogProcessor {

	private static final Logger log = LoggerFactory.getLogger(LoggerName.IOTMODEL);
	private boolean stoped = false;
	private static final BlockingQueue<Pair<IotObject, Message>> messageQueue = new LinkedBlockingQueue<>(100000);

	//private Map<IotActionType, Pair<ObjectMessageAction, ExecutorService>> processorTable;

	private List<LogPipeLine> logPipeLines;
	
	private ThreadPoolExecutor pollThread;
	private int pollThreadNum;

	public DefaultMsgLogProcessor(int pollThreadNum) {
		//this.processorTable = new HashMap();
		
		this.pollThreadNum=pollThreadNum;
		
		logPipeLines=new ArrayList<>();
	}
	
	@Override
	public void pipeLine(LogPipeLine pipeline) {
		if(logPipeLines==null)
			logPipeLines=new ArrayList<>();
		logPipeLines.add(pipeline);	
	}
	

	@Override
	public boolean addlog(IotObject iotObject,Message message) {
		boolean isNotFull = messageQueue.offer(new Pair<IotObject, Message>(iotObject, message));
		if (!isNotFull) {
			log.warn("[addparse] -> the buffer queue is full");
		}
		return isNotFull;
	}

	@Override
	public void start() {
		this.pollThread = new ThreadPoolExecutor(pollThreadNum, pollThreadNum, 60 * 1000, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<>(100000), new ThreadFactoryImpl("iotMsgLogProcessor"),
				new RejectHandler("iotMsgLogProcessor", 100000));

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
	 * 处理上下行日志记录
	 * @param indata
	 * @author zj
	 * @date 2020年2月13日
	 */
	private void processLog(Pair<IotObject, Message> indata) {

	
		IotObject iotObj = indata.getObject1();
		Message msg = indata.getObject2();

		String clientId=msg.getClientId();
				
		String username = ConnectManager.getInstance().getUserName(clientId);
		String deviceName="";
		if (username != null && username.contains("&")) {

			String[] keys = username.split("&");
			if (keys.length == 2) {
				deviceName = keys[0];
			}
		}
			
		IotActionType atype=TopicHelper.ParesAType(iotObj, msg);
		
		
		MsgLog mlog=new MsgLog();
		
		
		mlog.setNodename(ClusterNodeManager.getInstance().getCurrentNode().getNodeName());
		
		mlog.setActionType(msg.getMsgType());
		mlog.setFeeType(atype);
		mlog.setClientId(clientId);
		mlog.setDateTime(DateUtil.getNowStr(""));
		mlog.setDeviceName(deviceName);
		mlog.setProductKey(iotObj.getProductKey());
		mlog.setPayload(msg.getPayload());
		
		
		//分发处理
		for (LogPipeLine logPipeLine : logPipeLines) {
			logPipeLine.dealLog(mlog);
		}

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

						processLog(rulerst);

					}
				} catch (Exception ex) {
					log.warn("msgLog processor failure,cause={}", ex);
				}
			}
		}

	}

	

}
