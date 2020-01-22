package org.jmqtt.web.processor;

import org.jmqtt.common.bean.iot.IotActionData;
import org.jmqtt.rule.common.ZRuleCommand;

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
