package org.jmqtt.iot.processor;

import java.util.concurrent.ExecutorService;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.RuleType;
import org.jmqtt.common.bean.iot.IotActionType;
import org.jmqtt.common.bean.iot.IotObject;
import org.jmqtt.rule.processor.RuleResultProcessor;

/**
 * 特定产品模型属性，方法，事件解析
 * ObjectMessageParse.java.
 * 
 * @author zj
* @version 1.0.1 2020年1月22日
* @revision zj 2020年1月22日
* @since 1.0.1
 */
public interface ObjectMessageParse {

	/**
	 * 添加到处理队列
	 * @param iotObject
	 * @param message
	 * @author zj
	 * @date 2020年1月22日
	 */
	boolean addparse(IotObject iotObject,Message message);
	
	/**
	 * 注册具体设置/获取属性，调用方法，或者上报事件的具体处理程序
	 * @param aType
	 * @param processor
	 * @param executorService
	 * @author zj
	 * @date 2020年1月22日
	 */
	void registerActionProcessor(IotActionType aType, ObjectMessageAction processor,
			ExecutorService executorService);
	
	public void start();
	
	public void shutdown();
}
