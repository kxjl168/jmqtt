package org.jmqtt.common.message;

import org.jmqtt.common.bean.Message;import org.jmqtt.common.bean.iot.IotObject;
import org.jmqtt.iot.processor.IotObjectEngin;

public interface MessageDispatcher {

    void start();

    void shutdown();

    boolean appendMessage(Message message);
    
    
    /**
     * 关联engin，转发下行日志
     * @param iotEngin
     * @author zj
     * @date 2020年2月13日
     */
    void setIotEngin(IotObjectEngin iotEngin);
  
}
