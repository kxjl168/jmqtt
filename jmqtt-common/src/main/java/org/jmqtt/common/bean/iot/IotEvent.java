package org.jmqtt.common.bean.iot;

/**
 * 物模型事件
 * iotEvent.java.
 * 
 * @author zj
* @version 1.0.1 2020年1月22日
* @revision zj 2020年1月22日
* @since 1.0.1
 */
public class IotEvent {
	
	private String identifier;//名称
	
	private String name;//

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
	
	/*  "base": false,
      "baseEventId": 129,
      "contributorId": 9,
      "eventType": "FAULT",
      "featureState": "APPROVED",
      "flag": "基本",
      "gmtCreate": 1548732864000,
      "gmtModified": "2019-01-29 11:34:24",
      "id": 1238,
      "identifier": "Error",
      "name": "故障上报",
      "outputData": [
          {
              "argumentJSON": {
                  "identifier": "ErrorCode",
                  "dataSpecs": {
                      "unitType": 44,
                      "gmtModified": 1552619251000,
                      "unit": "",
                      "min": "0",
                      "isDeleted": 0,
                      "unitName": "无",
                      "max": "100",
                      "dataType": "INT",
                      "step": "1",
                      "id": 10025,
                      "gmtCreate": 1552619251000
                  },
                  "paraOrder": 0,
                  "dataType": "INT",
                  "childDataType": "INT",
                  "name": "故障代码",
                  "required": true,
                  "direction": "OUTPUT"
              },
              "baseFlag": true,
              "childDataType": "INT",
              "dataSpec": {
                  "$ref": "$.eventDTOList[0].outputData[0].argumentJSON.dataSpecs"
              },
              "direction": "PARAM_OUTPUT",
              "gmtCreate": 1548732864000,
              "gmtModified": 1554209275000,
              "id": 3646,
              "identifier": "ErrorCode",
              "isDeleted": 0,
              "name": "故障代码",
              "paraOrder": 0,
              "required": true
          }
      ],
      "required": true,
      "resourceType": "EVENT",
      "thingTemplateId": 1890,
      "version": "0",
      "versionId": 0*/
}
