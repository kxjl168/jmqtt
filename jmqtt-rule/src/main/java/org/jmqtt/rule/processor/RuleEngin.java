package org.jmqtt.rule.processor;

import org.jeasy.rules.api.Rule;
import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.ZRule;
import org.jmqtt.iot.processor.IotObjectEngin;

/**
 * 规则引擎，启动后及开始接受mqtt消息过滤处理。<br>
 * 
 * 同时负责刷新规则数据
 * 
 * @author zj
 * @version 1.0.1 2019年12月27日
 * @revision zj 2019年12月27日
 * @since 1.0.1
 */
public interface RuleEngin {


	/**
	 * 规则引擎过滤数据，过滤所有mqtt pub消息，如果成功，生成ZRuleCommand数据交给RuleDispatcher下一步处理
	 * @param message
	 * @return
	 * @author zj
	 * @date 2019年12月27日
	 */
	boolean filter(Message message);
	
	/**
	 * 从远程服务器主动刷新规则数据
	 * @return
	 * @author zj
	 * @date 2019年12月30日
	 */
	boolean refreshRules(String productKey);
	
	
	  /**
     * 关联engin，转发引擎过滤日志
     * @param iotEngin
     * @author zj
     * @date 2020年2月13日
     */
    void setIotEngin(IotObjectEngin iotEngin);
  


	
	void start();

	void shutdown();
}
