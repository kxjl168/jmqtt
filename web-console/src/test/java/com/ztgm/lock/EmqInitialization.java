/*
 * @(#)EmqInitialization.java
 * @author: chenenqiang
 * @Date: 2018/10/25 15:46
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.lock;

import com.alibaba.fastjson.JSONObject;
import com.ztgm.base.util.UUIDUtil;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
//import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Emq 初始化
 *
 * @author chenenqiang
 */
@Slf4j
@Component
@EnableScheduling
@EnableAsync
@PropertySource("classpath:project.properties")
public class EmqInitialization {

	// @Value("${achieve.emqtt.url}")
	private String achivevEmqtt;

	private static MqttClient client;

	public static EmqInitialization e = new EmqInitialization();
	static {
		e.init();
	}

	public static void main(String[] args) {
		// EmqInitialization e=new EmqInitialization();
		//// e.init();
		String topic = "x";
		String content = "12312333333333";
		e.emqSend(topic, content);

	
	}

	@Async("AsyncPool")
	@Scheduled(cron = "0 0 0/1 * * ? ")
	public void emqTiming() {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("定时检查Emq状态");
			// log.error(ExceptionUntil.getMessage(e));
		}
	}

	public MqttConnectOptions getOptions() {
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(true);
		options.setUserName("admin");
		options.setPassword("admin".toCharArray());
		options.setConnectionTimeout(10);
		options.setKeepAliveInterval(100);
		return options;
	}

	@PostConstruct
	public void init() {
		/*
		 * SendPostResponse sendPostResponse = new SendPostResponse(); try {
		 * sendPostResponse = httpSendPost.doPost(achivevEmqtt, ""); } catch (Exception
		 * e) { log.error("emq地址获取错误"); e.printStackTrace(); }
		 */
		// String result = sendPostResponse.getData();
		/*
		 * String emqIp = String.valueOf(new JSONObject(result).get("serverIp")); String
		 * emqPort = String.valueOf(new JSONObject(result).get("serverPort"));
		 */
		String emqIp = "192.168.100.41"; // JSONObject.parseObject(result).getString("serverIp");
		String emqPort = "21883";// JSONObject.parseObject(result).getString("serverPort");
		String emqUrl = "tcp://" + emqIp + ":" + emqPort;
		String clientId = "System/Netty：" + UUIDUtil.getUUID();
		try {
			// 防止重复创建MQTTClient实例
			if (client == null) {
				client = new MqttClient(emqUrl, clientId, new MemoryPersistence());
				client.setCallback(new MqttCallback() {

					@Override
					public void messageArrived(String topic, MqttMessage message) throws Exception {
						// TODO Auto-generated method stub

					}

					@Override
					public void deliveryComplete(IMqttDeliveryToken token) {
						// TODO Auto-generated method stub

					}

					@Override
					public void connectionLost(Throwable cause) {
						// TODO Auto-generated method stub

					}
				});
			}
			// 判断拦截状态，这里注意一下，如果没有这个判断，是非常坑的
			if (!client.isConnected()) {
				client.connect(getOptions());
			} else {
				// 这里的逻辑是如果连接成功就重新连接
				client.disconnect();
				client.connect(getOptions());
			}
		} catch (MqttException me) {
			log.error("jmqtt初始化错误");
			log.error("ip:" + emqIp + "/port:" + emqPort);
			me.printStackTrace();
			// log.error(ExceptionUntil.getMessage(me));
		}
	}

	void emqSend(String topic, String content) {
		try {
			int qos = 2;
			MqttMessage message;
			message = new MqttMessage(content.getBytes(StandardCharsets.UTF_8));
			message.setQos(qos);
			message.setRetained(false);
			client.publish(topic, message);
			log.debug("Topic: " + topic + ", Content: " + content);
			
			client.disconnect();
			
		} catch (MqttException me) {
			init();
			log.error("emq发送信息错误");
			me.printStackTrace();
			// log.error(ExceptionUntil.getMessage(me));
		}
	}
}
