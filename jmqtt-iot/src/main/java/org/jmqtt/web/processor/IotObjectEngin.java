package org.jmqtt.web.processor;

import org.jmqtt.common.bean.Message;

/**
 * 物模型解析处理
 * IotEngin.java.
 * 
 * @author zj
* @version 1.0.1 2020年1月22日
* @revision zj 2020年1月22日
* @since 1.0.1
 */
public interface IotObjectEngin {

	/**
	 * 处理设备上报的报文，解析topic,模型，处理属性变更/事件上报,及api方法调用，api属性设置
	 * @param message
	 * @author zj
	 * @date 2020年1月22日
	 */
	boolean dealObjectMessage(Message message);
	

	
	/**
	 * 从远程服务器主动刷新特定产品模型数据 
	 * @return
	 * @author zj
	 * @date 2019年12月30日
	 */
	boolean refreshObjectModels(String productKey);

	
	void start();

	void shutdown();
}
