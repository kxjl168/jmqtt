package org.jmqtt.common.bean.iot;

/**
 * 具体设备，名称，密钥，用于本地鉴权 IotDevice.java.
 * 
 * @author zj
 * @version 1.0.1 2020年2月11日
 * @revision zj 2020年2月11日
 * @since 1.0.1
 */
public class IotDevice {
	
	private String productKey;

	private String deviceid;
	private String deviceName;
	private String deviceSecret;
	
	private String deviceShadow;//设备影子信息
	
	private String deviceShadowUpdated;//true ,false

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceSecret() {
		return deviceSecret;
	}

	public void setDeviceSecret(String deviceSecret) {
		this.deviceSecret = deviceSecret;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getDeviceShadow() {
		return deviceShadow;
	}

	public void setDeviceShadow(String deviceShadow) {
		this.deviceShadow = deviceShadow;
	}

	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	public String getDeviceShadowUpdated() {
		return deviceShadowUpdated;
	}

	public void setDeviceShadowUpdated(String deviceShadowUpdated) {
		this.deviceShadowUpdated = deviceShadowUpdated;
	}
}
