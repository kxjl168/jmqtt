package org.jmqtt.rule.processor;


import java.util.concurrent.ExecutorService;

import org.jmqtt.common.bean.RuleType;
import org.jmqtt.rule.common.ZRuleCommand;


/**
 * 规则转发处理，处理所有符合规则的mqtt消息生成的数据
 * RuleDispatcher.java.
 * 
 * @author zj
* @version 1.0.1 2019年12月27日
* @revision zj 2019年12月27日
* @since 1.0.1
 */
public interface RuleDispatcher {

	/**
	 * 注册具体处理数据的处理器
	 * @param rType
	 * @param processor
	 * @param executorService
	 * @author zj
	 * @date 2019年12月30日
	 */
	void registerProcessor(RuleType rType, RuleResultProcessor processor,
			ExecutorService executorService);
	
    void start();

    void shutdown();

    
    /**
     * 添加待处理指令至内部队列
     * @param message
     * @return
     * @author zj
     * @date 2019年12月30日
     */
    boolean appendMessage(ZRuleCommand message);

}
