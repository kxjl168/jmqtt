package org.jmqtt.web.listener.impl;

import org.jmqtt.common.bean.iot.IotDevice;
import org.jmqtt.common.helper.SerializeHelper;
import org.jmqtt.common.message.MessageDispatcher;
import org.jmqtt.group.ClusterMessageTransfer;
import org.jmqtt.group.message.WebSRequestListener;
import org.jmqtt.group.protocol.ClusterRemotingCommand;
import org.jmqtt.iot.processor.IotObjectEngin;
import org.jmqtt.rule.processor.RuleEngin;
import org.jmqtt.store.ObjectModelMessageStore;
import org.jmqtt.web.common.WebRemotingCommand;
import org.jmqtt.web.helper.MsgFromType;
import org.jmqtt.web.helper.ShadowHelper;

/**
 *  集群节点转发的web指令 WebSRequestListener.java.
 * 
 * @author zj
 * @version 1.0.1 2020年2月13日
 * @revision zj 2020年2月13日
 * @since 1.0.1
 */
public class DefaultWebSRequestListenerImpl implements WebSRequestListener {

	IotObjectEngin iotEngin;
	RuleEngin ruleEngin;

	ObjectModelMessageStore iotModelStore;
	ClusterMessageTransfer clusterMessageTransfer;
	MessageDispatcher messageDispatcher;

	public DefaultWebSRequestListenerImpl(IotObjectEngin iotEngin, RuleEngin ruleEngin,
			ObjectModelMessageStore iotModelStore, ClusterMessageTransfer clusterMessageTransfer,
			MessageDispatcher messageDispatcher) {
		this.iotEngin = iotEngin;
		this.ruleEngin = ruleEngin;

		this.iotModelStore = iotModelStore;
		this.clusterMessageTransfer = clusterMessageTransfer;
		this.messageDispatcher = messageDispatcher;
	}

	/**
	 * 规则引擎改变通知
	 * 
	 * @param cmd
	 * @author zj
	 * @date 2020年2月13日
	 */
	public void EnginRuleChanged(ClusterRemotingCommand cmd) {
		String proKey = SerializeHelper.deserialize(cmd.getBody(), String.class);

		ruleEngin.refreshRules(proKey);
	}

	/**
	 * 物模型改变通知
	 * 
	 * @param cmd
	 * @author zj
	 * @date 2020年2月13日
	 */
	public void ObjectModelChanged(ClusterRemotingCommand cmd) {
		String proKey = SerializeHelper.deserialize(cmd.getBody(), String.class);

		iotEngin.refreshObjectModels(proKey);
	}

	@Override
	public void ObjectModelShadowChanged(ClusterRemotingCommand cmd) {
		IotDevice deviceShadow = SerializeHelper.deserialize(cmd.getBody(), IotDevice.class);

		ShadowHelper.dealShadowChanged(MsgFromType.CLUSTER, iotModelStore, deviceShadow, messageDispatcher,
				clusterMessageTransfer);
	}

	@Override
	public void ObjectModelShadowDownComplete(ClusterRemotingCommand cmd) {
		IotDevice deviceShadow = SerializeHelper.deserialize(cmd.getBody(), IotDevice.class);

		// 更新影子状态
		iotModelStore.deviceShadowUpdateDone(deviceShadow.getProductKey(), deviceShadow.getDeviceName());

	}
}
