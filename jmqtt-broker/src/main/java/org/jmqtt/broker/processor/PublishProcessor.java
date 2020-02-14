package org.jmqtt.broker.processor;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttPubAckMessage;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.FutureListener;

import org.jmqtt.broker.BrokerController;
import org.jmqtt.broker.acl.PubSubPermission;
import org.jmqtt.store.FlowMessageStore;
import org.jmqtt.remoting.session.ClientSession;
import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.iot.processor.IotObjectEngin;
import org.jmqtt.remoting.netty.RequestProcessor;
import org.jmqtt.remoting.session.ConnectManager;
import org.jmqtt.remoting.util.MessageUtil;
import org.jmqtt.remoting.util.NettyUtil;
import org.jmqtt.rule.processor.RuleEngin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class PublishProcessor extends AbstractMessageProcessor implements RequestProcessor {
	private Logger log = LoggerFactory.getLogger(LoggerName.MESSAGE_TRACE);

	private FlowMessageStore flowMessageStore;

	private PubSubPermission pubSubPermission;

	private RuleEngin ruleEngin;
	
	private IotObjectEngin iotEngin;

	public PublishProcessor(BrokerController controller) {
		super(controller.getMessageDispatcher(), controller.getRetainMessageStore(),
				controller.getInnerMessageTransfer());
		this.flowMessageStore = controller.getFlowMessageStore();
		this.pubSubPermission = controller.getPubSubPermission();

		this.ruleEngin = controller.getRuleEngin();
		this.iotEngin=controller.getIotEngin();
	}

	@Override
	public void processRequest(ChannelHandlerContext ctx, MqttMessage mqttMessage) {
		try {
			MqttPublishMessage publishMessage = (MqttPublishMessage) mqttMessage;
			MqttQoS qos = publishMessage.fixedHeader().qosLevel();
			Message innerMsg = new Message();
			String clientId = NettyUtil.getClientId(ctx.channel());
			ClientSession clientSession = ConnectManager.getInstance().getClient(clientId);
			String topic = publishMessage.variableHeader().topicName();
			if (!this.pubSubPermission.publishVerfy(clientId, topic)) {
				log.warn("[PubMessage] permission is not allowed");
				clientSession.getCtx().close();
				return;
			}
			innerMsg.setPayload(MessageUtil.readBytesFromByteBuf(((MqttPublishMessage) mqttMessage).payload()));
			innerMsg.setClientId(clientId);
			innerMsg.setType(Message.Type.valueOf(mqttMessage.fixedHeader().messageType().value()));
			Map<String, Object> headers = new HashMap<>();
			headers.put(MessageHeader.TOPIC, publishMessage.variableHeader().topicName());
			headers.put(MessageHeader.QOS, publishMessage.fixedHeader().qosLevel().value());
			headers.put(MessageHeader.RETAIN, publishMessage.fixedHeader().isRetain());
			headers.put(MessageHeader.DUP, publishMessage.fixedHeader().isDup());

			innerMsg.setHeaders(headers);
			innerMsg.setMsgId(publishMessage.variableHeader().packetId());
			switch (qos) {
			case AT_MOST_ONCE:
				processMessage(innerMsg);
				break;
			case AT_LEAST_ONCE:
				processQos1(ctx, innerMsg);
				break;
			case EXACTLY_ONCE:
				processQos2(ctx, innerMsg);
				break;
			default:
				log.warn("[PubMessage] -> Wrong mqtt message,clientId={}", clientId);
			}

			processRule(ctx, innerMsg);
			processIotModel(ctx,innerMsg);

		} catch (Throwable tr) {
			log.warn("[PubMessage] -> Solve mqtt pub message exception:{}", tr);
		} finally {
			ReferenceCountUtil.release(mqttMessage.payload());
		}
	}

	/**
	 * 处理规则引擎数据过滤转发等
	 * 
	 * @param ctx
	 * @param innerMsg
	 * @author zj
	 * @date 2019年12月27日
	 */
	private void processRule(ChannelHandlerContext ctx, Message innerMsg) {
		log.info("[PubMessage] -> Process processRule message,clientId={}", innerMsg.getClientId());

		
		ruleEngin.filter(innerMsg);

	}
	
	/**
	 * 处理IOT物模型数据
	 * @param ctx
	 * @param innerMsg
	 * @author zj
	 * @date 2020年2月10日
	 */
	private void processIotModel(ChannelHandlerContext ctx, Message innerMsg) {
		log.info("[PubMessage] -> Process processIotModel message,clientId={}", innerMsg.getClientId());

		iotEngin.dealObjectMessage(innerMsg);

	}
	

	private void processQos2(ChannelHandlerContext ctx, Message innerMsg) {
		log.info("[PubMessage] -> Process qos2 message,clientId={}", innerMsg.getClientId());
		boolean flag = flowMessageStore.cacheRecMsg(innerMsg.getClientId(), innerMsg);
		if (!flag) {
			log.warn("[PubMessage] -> cache qos2 pub message failure,clientId={}", innerMsg.getClientId());
		}
		MqttMessage pubRecMessage = MessageUtil.getPubRecMessage(innerMsg.getMsgId());
		ctx.writeAndFlush(pubRecMessage);
	}

	private void processQos1(ChannelHandlerContext ctx, Message innerMsg) {
		//

		log.info("[PubMessage] -> Process qos1 message,clientId={}", innerMsg.getClientId());
		MqttPubAckMessage pubAckMessage = MessageUtil.getPubAckMessage(innerMsg.getMsgId());
		ctx.writeAndFlush(pubAckMessage);

		processMessage(innerMsg);

	}

}
