package org.jmqtt.iot.processor;

import org.jmqtt.common.bean.iot.IotActionData;
import org.jmqtt.rule.common.ZRuleCommand;

/**
 * 具体处理设备上报的topic
 * ObjectMessageAction.java.
 * 
 * @author zj
* @version 1.0.1 2020年2月11日
* @revision zj 2020年2月11日
* @since 1.0.1
 */
public interface ObjectMessageAction {

	
	/**
	 * 具体处理
	 * @param cmd
	 * @return
	 * @author zj
	 * @date 2020年1月22日
	 */
	IotActionData processRequest( IotActionData cmd);
	
}
