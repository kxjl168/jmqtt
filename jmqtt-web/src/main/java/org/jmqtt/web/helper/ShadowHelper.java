package org.jmqtt.web.helper;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;
import org.jmqtt.common.bean.iot.IotDevice;
import org.jmqtt.common.helper.SerializeHelper;
import org.jmqtt.common.message.MessageDispatcher;
import org.jmqtt.group.ClusterMessageTransfer;
import org.jmqtt.group.protocol.ClusterRequestCode;
import org.jmqtt.remoting.session.ClientSession;
import org.jmqtt.remoting.session.ConnectManager;
import org.jmqtt.store.ObjectModelMessageStore;

public class ShadowHelper {

	/**
	 * web指令或者集群过来的指令 影子更新
	 * 
	 * @param iotModelStore
	 * @param deviceShadow
	 * @param messageDispatcher
	 * @param clusterMessageTransfer
	 * @author zj
	 * @date 2020年2月14日
	 */
	public static void dealShadowChanged(MsgFromType msgFrom, ObjectModelMessageStore iotModelStore,
			IotDevice deviceShadow, MessageDispatcher messageDispatcher,
			ClusterMessageTransfer clusterMessageTransfer) {

		iotModelStore.updateDeviceShadow(deviceShadow.getProductKey(), deviceShadow);

		ClientSession session = ConnectManager.getInstance()
				.getClientByUserName(deviceShadow.getDeviceName() + "&" + deviceShadow.getProductKey());
		// 检查设备是否上线
		if (session != null) {

			// 在线直接更新.
			// 下发

			// 回复影子更新消息 //TODO topic待确定
			String desttopic = "/sys/" + deviceShadow.getProductKey() + "/" + deviceShadow.getDeviceName()
					+ "/thing/model/up_raw_reply";

			Message trannewMsg = new Message();

			trannewMsg.putHeader(MessageHeader.TOPIC, desttopic);
			trannewMsg.putHeader(MessageHeader.QOS, 1);
			trannewMsg.putHeader(MessageHeader.DUP, false);

			trannewMsg.setPayload(deviceShadow.getDeviceShadow().getBytes());

			messageDispatcher.appendMessage(trannewMsg);

			// 更新影子状态
			iotModelStore.deviceShadowUpdateDone(deviceShadow.getProductKey(), deviceShadow.getDeviceName());

		} else {

			if (msgFrom == MsgFromType.WEB) {
				// 不在线则转发其他节点
				Message innermsg = new Message();
				innermsg.setPayload(SerializeHelper.serialize(deviceShadow));
				clusterMessageTransfer.dispatcherOtherMessage2Cluster(ClusterRequestCode.NOTICE_MODEL_SHADOW_CHANGED,
						innermsg);
			}
		}

	}
}
