package org.jmqtt.broker.processor;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;
import org.jmqtt.store.FlowMessageStore;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.remoting.netty.RequestProcessor;
import org.jmqtt.remoting.util.MessageUtil;
import org.jmqtt.remoting.util.NettyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PubCompProcessor implements RequestProcessor {

    private Logger log = LoggerFactory.getLogger(LoggerName.MESSAGE_TRACE);
    private FlowMessageStore flowMessageStore;

    public PubCompProcessor(FlowMessageStore flowMessageStore){
        this.flowMessageStore = flowMessageStore;
    }

    /**
     * PUBCOMP – 发布完成（QoS 2，第三步）
     * @param ctx
     * @param mqttMessage
     * @author zj
     * @date 2019年12月16日
     */
    @Override
    public void processRequest(ChannelHandlerContext ctx, MqttMessage mqttMessage) {
        String clientId = NettyUtil.getClientId(ctx.channel());
        int messageId = MessageUtil.getMessageId(mqttMessage);
        boolean isContain = flowMessageStore.releaseSendMsg(clientId,messageId);
        log.debug("[PubComp] -> Recieve PubCom and remove the flow message,clientId={},msgId={}",clientId,messageId);
        if(!isContain){
            log.warn("[PubComp] -> The message is not in Flow cache,clientId={},msgId={}",clientId,messageId);
        }
    }
}
