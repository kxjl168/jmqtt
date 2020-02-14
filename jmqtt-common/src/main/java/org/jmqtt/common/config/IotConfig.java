package org.jmqtt.common.config;

/**
 * 物模型相关配置 IotConfig.java.
 * 
 * @author zj
 * @version 1.0.1 2020年2月10日
 * @revision zj 2020年2月10日
 * @since 1.0.1
 */
public class IotConfig {

	private String iotmodelweburl = ""; //物模型配置获取web地址
	private boolean iotenable = true; //是否启用物模型过滤
	
	private boolean iotauthenable = false; //是否启用设备deviceName deviceSecret校验

	public String getIotmodelweburl() {
		return iotmodelweburl;
	}

	public void setIotmodelweburl(String iotmodelweburl) {
		this.iotmodelweburl = iotmodelweburl;
	}

	public boolean isIotenable() {
		return iotenable;
	}

	public void setIotenable(boolean iotenable) {
		this.iotenable = iotenable;
	}

	public boolean isIotauthenable() {
		return iotauthenable;
	}

	public void setIotauthenable(boolean iotauthenable) {
		this.iotauthenable = iotauthenable;
	}

}
