package org.jmqtt.common.bean.iot;

import java.util.List;

/**
 * 基础物模型 iotObject.java.
 * 
 * @author zj
 * @version 1.0.1 2020年1月22日
 * @revision zj 2020年1月22日
 * @since 1.0.1
 */
public abstract class IotObject {
	
	private String productKey;

	private String name;// 名称

	private String objectType;// 模型类型

	private String version;// 版本

	private List<IotProperty> properties;

	private List<IotFunction> functions;

	private List<IotEvent> events;
	
	private List<IotTopic> topics;

	public List<IotProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<IotProperty> properties) {
		this.properties = properties;
	}

	public List<IotFunction> getFunctions() {
		return functions;
	}

	public void setFunctions(List<IotFunction> functions) {
		this.functions = functions;
	}

	public List<IotEvent> getEvents() {
		return events;
	}

	public void setEvents(List<IotEvent> events) {
		this.events = events;
	}

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

}
