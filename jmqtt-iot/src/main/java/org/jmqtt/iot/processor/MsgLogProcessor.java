package org.jmqtt.iot.processor;

import java.util.concurrent.ExecutorService;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.RuleType;
import org.jmqtt.common.bean.iot.IotActionType;
import org.jmqtt.common.bean.iot.IotObject;
import org.jmqtt.common.bean.msgdata.MsgActionType;
import org.jmqtt.rule.processor.RuleResultProcessor;


/**
 * 节点日志处理，上报web
 * MsgLogProcessor.java.
 * 
 * @author zj
* @version 1.0.1 2020年2月13日
* @revision zj 2020年2月13日
* @since 1.0.1
 */
public interface MsgLogProcessor {

	/**
	 * 添加日志至处理队列
	 * @param iotObject
	 * @param message
	 * @param clientId
	 * @param atype
	 * @return
	 * @author zj
	 * @date 2020年2月13日
	 */
	boolean addlog(IotObject iotObject,Message message);
	
	
	
	/**
	 * 注册日志处理程序
	 * @param pipeline
	 * @author zj
	 * @date 2020年2月13日
	 */
	void pipeLine(LogPipeLine pipeline);
	
	
	/**
	 * 启动内部队列处理， 发送或者存储
	 * 
	 * @author zj
	 * @date 2020年2月13日
	 */
	public void start();
	
	public void shutdown();
}
