package org.jmqtt.common.bean.iot;

import java.util.ArrayList;
import java.util.List;

import org.jmqtt.common.helper.SerializeHelper;

/**
 * 基础产品物模型 iotObject.java.
 * 
 * @author zj
 * @version 1.0.1 2020年1月22日
 * @revision zj 2020年1月22日
 * @since 1.0.1
 */
public  class IotObject {
	
	private String productKey;
	
	private String autoRegister;//自动注册 true,false

	private String name;// 名称
	
	private String identifier;//名称
	

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	private String objectType;// 模型类型

	private String version;// 版本

	private List<IotProperty> propertyDTOList;

	private List<IotFunction> serviceDTOList;

	private List<IotEvent> eventDTOList;
	
	private List<IotTopic> topics;

	private List<IotDevice> devices;//特定产品下的所有设备。
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<IotTopic> getTopics() {
		return topics;
	}

	public void setTopics(List<IotTopic> topics) {
		this.topics = topics;
	}

	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	public String getAutoRegister() {
		return autoRegister;
	}

	public void setAutoRegister(String autoRegister) {
		this.autoRegister = autoRegister;
	}
	
	
	public static void main(String [] args)
	{
		
		IotObject testLight=new IotObject();
	
		testLight.autoRegister="true";
		testLight.name="灯";
		testLight.identifier="light";
		testLight.objectType="Ligth";
		testLight.productKey="testKey";
		
		
		List<IotProperty> pros=new ArrayList<IotProperty>();
		IotProperty plight=new IotProperty();
		plight.setIdentifier("Brightness");
		pros.add(plight);
		
		
		List<IotFunction> funcs=new ArrayList<IotFunction>();
		IotFunction pfun=new IotFunction();
		pfun.setIdentifier("ToggleLightSwitch");
		funcs.add(pfun);
		
		
		List<IotEvent> events=new ArrayList<IotEvent>();
		IotEvent event=new IotEvent();
		event.setIdentifier("Error");
		events.add(event);
		
		
		List<IotTopic> topics=new ArrayList<IotTopic>();
		IotTopic topic=new IotTopic();
		topic.setTopicName("/sys/${productKey}/${deviceName}/thing/event/property/post");
		topics.add(topic);
		
		testLight.serviceDTOList=funcs;
		testLight.eventDTOList=events;
		testLight.topics=topics;
		testLight.propertyDTOList=pros;
		
		
		String lightStr=new String( SerializeHelper.serialize(testLight));
		System.out.println(lightStr);
		
		
	
	}

	public List<IotProperty> getPropertyDTOList() {
		return propertyDTOList;
	}

	public void setPropertyDTOList(List<IotProperty> propertyDTOList) {
		this.propertyDTOList = propertyDTOList;
	}

	public List<IotFunction> getServiceDTOList() {
		return serviceDTOList;
	}

	public void setServiceDTOList(List<IotFunction> serviceDTOList) {
		this.serviceDTOList = serviceDTOList;
	}

	public List<IotEvent> getEventDTOList() {
		return eventDTOList;
	}

	public void setEventDTOList(List<IotEvent> eventDTOList) {
		this.eventDTOList = eventDTOList;
	}

	public List<IotDevice> getDevices() {
		return devices;
	}

	public void setDevices(List<IotDevice> devices) {
		this.devices = devices;
	}
	

}
