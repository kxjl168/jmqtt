package org.jmqtt.iot.processor.impl.action;

import org.jmqtt.common.bean.iot.IotActionData;
import org.jmqtt.iot.processor.ObjectMessageAction;

public class DefaultTriggerEventAction implements ObjectMessageAction {

	@Override
	public IotActionData processRequest(IotActionData cmd) {
		// TODO 设备上报事件
		return null;
	}

}
