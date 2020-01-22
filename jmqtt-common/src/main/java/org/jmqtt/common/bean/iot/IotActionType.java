package org.jmqtt.common.bean.iot;

/**
 * 报文类型
 * IotActionType.java.
 * 
 * @author zj
* @version 1.0.1 2020年1月22日
* @revision zj 2020年1月22日
* @since 1.0.1
 */
public enum IotActionType {

	SetProperty,
	GetProperty,
	CallFunction,
	EventUpload,
	
	SynacModel,//设备上线，web端影子同步设置属性，内部再调用多次 SetProperty
}
