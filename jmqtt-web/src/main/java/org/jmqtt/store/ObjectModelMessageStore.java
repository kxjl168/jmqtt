package org.jmqtt.store;

import java.util.List;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.ZRule;
import org.jmqtt.common.bean.iot.IotDevice;
import org.jmqtt.common.bean.iot.IotObject;

/**
 * 基础物模型数据存储 ObjectModelMessageStore.java.
 * 
 * @author zj
 * @version 1.0.1 2020年1月22日
 * @revision zj 2020年1月22日
 * @since 1.0.1
 */
public interface ObjectModelMessageStore {

	IotObject getIotObjectMessage(String productKey);

	boolean hasIotObjectMessage(String productKey);

	void storeIotObjectMessage(String productKey, IotObject models);

	IotObject removeIotObjectMessage(String productKey);
	
	
	/**
	 *  更新设备物模型存储
	 * @param productKey
	 * @param device
	 * @return
	 * @author zj
	 * @date 2020年2月14日
	 */
	boolean updateDeviceShadow(String productKey,IotDevice device);
	
	/**
	 *  更新设备影子状态已通知到设备
	 * @param productKey
	 * @param device
	 * @return
	 * @author zj
	 * @date 2020年2月14日
	 */
	boolean deviceShadowUpdateDone(String productKey,String deviceName);
}
