package org.jmqtt.iot.processor.impl.action;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.iot.IotActionData;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.iot.processor.ObjectMessageAction;
import org.jmqtt.remoting.session.ConnectManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解析失败的处理
 * DefaultFailAction.java.
 * 
 * @author zj
* @version 1.0.1 2020年2月10日
* @revision zj 2020年2月10日
* @since 1.0.1
 */
public class DefaultFailAction implements ObjectMessageAction {

	private static final Logger log = LoggerFactory.getLogger(LoggerName.IOTMODEL);

	@Override
	public IotActionData processRequest(IotActionData cmd) {
		Message msg = cmd.getOriMessage();
		
		// TODO 记录错误
		String username = ConnectManager.getInstance().getUserName(msg.getClientId());
		String[] keys = username.split("&");
	

		String deviceName = keys[0];
		String productKey = keys[1];
		
		log.debug(cmd.getTopic().getTopicName()+" 不在产品"+productKey+"的topic列表中!");
		
		return null;
	}

}
