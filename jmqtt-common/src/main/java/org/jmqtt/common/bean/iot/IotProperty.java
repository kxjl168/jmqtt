package org.jmqtt.common.bean.iot;

/**
 * 物模型属性
 * iotProperty.java.
 * 
 * @author zj
* @version 1.0.1 2020年1月22日
* @revision zj 2020年1月22日
* @since 1.0.1
 */
public class IotProperty {

	private String accessMode;// READ_WRITE
	
	private String identifier;//
	
	private String name;
	
	private String unit;
	/*
	
	 "accessMode": "READ_WRITE",
     "base": false,
     "basePropertyId": 291,
     "contributorId": 9,
     "dataType": "INT",
     "featureState": "APPROVED",
     "flag": "基本",
     "gmtCreate": 1548732864000,
     "gmtModified": "2019-04-02 20:46:12",
     "id": 9178,
     "identifier": "Brightness",
     "max": "100",
     "min": "0",
     "name": "明暗度",
     "required": false,
     "resourceType": "PROPERTY",
     "step": "1",
     "thingTemplateId": 1890,
     "unit": "%",
     "unitName": "百分比",
     "unitType": 36,
     "version": "0",
     "versionId": 0*/

	public String getAccessMode() {
		return accessMode;
	}

	public void setAccessMode(String accessMode) {
		this.accessMode = accessMode;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
