package org.jmqtt.remoting.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledHeapByteBuf;
import io.netty.handler.codec.mqtt.*;
import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;

import java.util.List;

/**
 * transfer message from Message and MqttMessage
 */
public class MessageUtil {

    public static byte[] readBytesFromByteBuf(ByteBuf byteBuf){
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        return bytes;
    }


    public static MqttUnsubAckMessage getUnSubAckMessage(int messageId){
        MqttFixedHeader fixedHeader = new MqttFixedHeader(MqttMessageType.UNSUBACK,false,MqttQoS.AT_MOST_ONCE,false,0);
        MqttMessageIdVariableHeader idVariableHeader = MqttMessageIdVariableHeader.from(messageId);
        return new MqttUnsubAckMessage(fixedHeader,idVariableHeader);
    }

    public static int getMessageId(MqttMessage mqttMessage){
        MqttMessageIdVariableHeader idVariableHeader = (MqttMessageIdVariableHeader) mqttMessage.variableHeader();
        return idVariableHeader.messageId();
    }

    public static int getMinQos(int qos1,int qos2){
        if(qos1 < qos2){
            return qos1;
        }
        return qos2;
    }

    public static MqttMessage getPubRelMessage(int messageId){
        MqttFixedHeader fixedHeader = new MqttFixedHeader(MqttMessageType.PUBREL,false,MqttQoS.AT_MOST_ONCE,false,0);
        MqttMessageIdVariableHeader idVariableHeader = MqttMessageIdVariableHeader.from(messageId);
        return new MqttMessage(fixedHeader,idVariableHeader);
    }

    public static MqttPublishMessage getPubMessage(Message message,boolean dup,int qos,int messageId){
        MqttFixedHeader fixedHeader = new MqttFixedHeader(MqttMessageType.PUBLISH,dup,MqttQoS.valueOf(qos),false,0);
        MqttPublishVariableHeader publishVariableHeader = new MqttPublishVariableHeader((String) message.getHeader(MessageHeader.TOPIC),messageId);
        ByteBuf heapBuf;
        if(message.getPayload() == null){
            heapBuf = Unpooled.EMPTY_BUFFER;
        }else{
            heapBuf = Unpooled.wrappedBuffer((byte[])message.getPayload());
        }
        return new MqttPublishMessage(fixedHeader,publishVariableHeader,heapBuf);
    }

    public static MqttMessage getSubAckMessage(int messageId, List<Integer> qos){
        MqttFixedHeader fixedHeader = new MqttFixedHeader(MqttMessageType.SUBACK,false,MqttQoS.AT_MOST_ONCE,false,0);
        MqttMessageIdVariableHeader idVariableHeader = MqttMessageIdVariableHeader.from(messageId);
        MqttSubAckPayload subAckPayload = new MqttSubAckPayload(qos);
        return new MqttSubAckMessage(fixedHeader,idVariableHeader,subAckPayload);
    }

    public static MqttMessage getPingRespMessage(){
        MqttFixedHeader fixedHeader = new MqttFixedHeader(MqttMessageType.PINGRESP,false,MqttQoS.AT_MOST_ONCE,false,0);
        MqttMessage mqttMessage = new MqttMessage(fixedHeader);
        return mqttMessage;
    }

    
    /* "MQTT_CONNECT"=>1,//请求连接
    "MQTT_CONNACK"=>2,//请求应答
    "MQTT_PUBLISH"=>3,//发布消息
    "MQTT_PUBACK"=>4,//发布应答
    "MQTT_PUBREC"=>5,//发布已接收，保证传递1
    "MQTT_PUBREL"=>6,//发布释放，保证传递2
    "MQTT_PUBCOMP"=>7,//发布完成，保证传递3  
    "MQTT_SUBSCRIBE"=>8,//订阅请求
    "MQTT_SUBACK"=>9,//订阅应答
    "MQTT_UNSUBSCRIBE"=>10,//取消订阅
    "MQTT_UNSUBACK"=>11,//取消订阅应答
    "MQTT_PINGREQ"=>12,//ping请求
    "MQTT_PINGRESP"=>13,//ping响应
    "MQTT_DISCONNECT"=>14//断开连接
    */
    
    public static MqttMessage getPubComMessage(int messageId){
        MqttFixedHeader fixedHeader = new MqttFixedHeader(MqttMessageType.PUBCOMP,false,MqttQoS.AT_MOST_ONCE,false,0);
        MqttMessage mqttMessage = new MqttMessage(fixedHeader,MqttMessageIdVariableHeader.from(messageId));
        return mqttMessage;
    }

    /**
     * qos 2响应
     * @param messageId
     * @return
     * @author zj
     * @date 2019年12月16日 备注
     */
    public static MqttMessage getPubRecMessage(int messageId){
        MqttFixedHeader fixedHeader = new MqttFixedHeader(MqttMessageType.PUBREC,false,MqttQoS.AT_MOST_ONCE,false,0);
        MqttMessage mqttMessage = new MqttMessage(fixedHeader,MqttMessageIdVariableHeader.from(messageId));
        return mqttMessage;
    }

    /**
     * qos 1响应
     * @param messageId
     * @return
     * @author zj
     * @date 2019年12月16日
     */
    public static MqttPubAckMessage getPubAckMessage(int messageId){
        MqttFixedHeader fixedHeader = new MqttFixedHeader(MqttMessageType.PUBACK,false,MqttQoS.AT_LEAST_ONCE,false,0);
        MqttMessageIdVariableHeader idVariableHeader = MqttMessageIdVariableHeader.from(messageId);
        return new MqttPubAckMessage(fixedHeader,idVariableHeader);
    }

    public static MqttConnAckMessage getConnectAckMessage(MqttConnectReturnCode returnCode,boolean sessionPresent){
        MqttFixedHeader fixedHeader = new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE, false, 0);
        MqttConnAckVariableHeader variableHeade = new MqttConnAckVariableHeader(returnCode,sessionPresent);
        return new MqttConnAckMessage(fixedHeader,variableHeade);
    }
}
