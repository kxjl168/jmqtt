package org.jmqtt.common.bean.iot;

/**
 * 物模型业务 报文类型
 * IotActionType.java.
 * 
 * @author zj
* @version 1.0.1 2020年1月22日
* @revision zj 2020年1月22日
* @since 1.0.1
 */
public enum IotActionType {
	
	OTA_INFO_PUB,//ota上报升级 设备上报固件升级信息
	OTA_UPGRAGE_SUB,//固件升级信息下行
	OTA_PROGRESS_PUB,//设备固件升级进度上报
	OTA_REQUEST_PUB,//设备主动拉取固件升级信息
	
	NTP_TIME_PUB,
	NTP_TIME_SUB,//时间ntp
	
	SHADOW_UPDATE_PUB, //设备影子
	SHADOW_UPDATE_SUB,
	

	PostProperty, //上报属性
	PostPropertyReply, //上报回发属性
	
	SetProperty, //设置属性
	GetProperty, //获取属性
	
	CallFunction,//web服务调用
	CallFunction_REPLY,//设备服务调用返回
	EventUpload, //事件上报
	
	//SynacModel,//设备上线，web端影子同步设置属性，内部再调用多次 SetProperty
	
	RAW_UP,//透传
	RWA_UP_REPLY,
	
	RAW_DOWN,
	
	SelfDefine,//自定义
	
	Fail,//未解析出匹配的topic动作或者解析异常
}
