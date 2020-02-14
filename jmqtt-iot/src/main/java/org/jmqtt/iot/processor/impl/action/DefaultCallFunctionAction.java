package org.jmqtt.iot.processor.impl.action;

import org.jmqtt.common.bean.iot.IotActionData;
import org.jmqtt.iot.processor.ObjectMessageAction;

public class DefaultCallFunctionAction implements ObjectMessageAction {

	@Override
	public IotActionData processRequest(IotActionData cmd) {
		// TODO 服务调用回调
		return null;
	}

}
