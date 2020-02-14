package org.jmqtt.iot.acl;

import org.jmqtt.broker.acl.ConnectPermission;
import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;
import org.jmqtt.common.bean.iot.IotDevice;
import org.jmqtt.common.bean.iot.IotObject;
import org.jmqtt.common.config.IotConfig;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.store.ObjectModelMessageStore;
import org.jmqtt.web.plat.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 物模型 连接校验 hmacsha256 IOTConnectPermission.java.
 * 
 * @author zj
 * @version 1.0.1 2020年2月11日
 * @revision zj 2020年2月11日
 * @since 1.0.1
 */
public class IOTConnectPermission implements ConnectPermission {

	private static final Logger log = LoggerFactory.getLogger(LoggerName.IOTMODEL);

	private ObjectModelMessageStore iotObjectMessageStore;
	private IotConfig iotConfig;

	public IOTConnectPermission(ObjectModelMessageStore objectModelMessageStore, IotConfig iotConfig) {
		this.iotObjectMessageStore = objectModelMessageStore;
		this.iotConfig = iotConfig;
	}

	@Override
	public boolean clientIdVerfy(String clientId) {
		return true;
	}

	@Override
	public boolean onBlacklist(String remoteAddr, String clientId) {
		return false;
	}

	/**
	 * iot签名检查 eg:<br>
	 * mosquitto_pub -t /sys/testKey/device1/thing/service/property/set -h
	 * 192.168.100.41 -p 21883 -m "{\"Brightness\":50}" -u "device1&testKey" -V
	 * mqttv311 -i
	 * "testclient1|securemode=3,signmethod=hmacsha256,timestamp=22334123|" -P
	 * BCCE73216BCC9016C5887DC497B56948DE46D3A28CC023FA49D7C2F6F081CD45<br>
	 * 使用clientId传递出23字符的长字符，需要指定mqtt协议版本为 v3.1.1
	 * 
	 * @param oriclientId
	 * @param username
	 * @param password
	 * @return
	 * @author zj
	 * @date 2020年2月11日
	 */
	@Override
	public boolean authentication(String oriclientId, String username, byte[] password) {

		// 不启用，直接通过
		if (!iotConfig.isIotauthenable())
			return true;

		// testclient1|securemode=3,signmethod=hmacsha256,timestamp=22334123|
		// device1&testKey
		// hmacsha256("123","clientIdtestclient1deviceNamedevice1productKeytestKeytimestamp22334123")
		// BCCE73216BCC9016C5887DC497B56948DE46D3A28CC023FA49D7C2F6F081CD45

		// clientid
		// //clientId+"|securemode=3,signmethod=hmacsha256,timestamp=132323232|"
		// pass // sign_hmac(deviceSecret,content)
		
		
		
		if(username==null||username.equals(""))
			return true;
		
		
		
		String[] keys = username.split("&");
		if (keys.length == 2) {
			String productKey = keys[1];
			String deviceName = keys[0];

			IotObject iotObj = iotObjectMessageStore.getIotObjectMessage(productKey);

			String[] clientstrs = oriclientId.split(",");
			if (clientstrs == null || clientstrs.length != 3) {
				log.error("clientId参数错误:" + oriclientId);
				return false;
			}

			String deviceSecret = "";// 设备密钥，默认空
			IotDevice deviceShadow = null;
			if (!deviceName.equals("")) {
				if (iotObj.getDevices() != null) {
					for (IotDevice device : iotObj.getDevices()) {
						if (device.getDeviceName().equals(deviceName)) {
							deviceSecret = device.getDeviceSecret();
							deviceShadow = device;
							break;
						}
					}
				}
			}

			boolean signStatus = false;

			String realclientId = clientstrs[0].substring(0, clientstrs[0].indexOf("|"));
			String timestamp = clientstrs[2].substring(clientstrs[2].indexOf("=") + 1, clientstrs[2].indexOf("|"));

			String oridata = "clientId" + realclientId + "deviceName" + deviceName + "productKey" + productKey
					+ "timestamp" + timestamp;
			try {
				// 检查签名是否正确
				String signData = SignUtil.HMACSHA256(oridata, deviceSecret);
				if (signData.equals(new String(password, "utf-8")))
					signStatus = true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// hmacsha1("secret","clientId12345deviceNamedeviceproductKeypktimestamp789").toHexString()

			if (!signStatus) {
				log.error("clientId:" + oriclientId + "/username:" + username + "签名校验错误");
				return false;// 签名错误
			}
			// 鉴权完成

			if (deviceName.equals("")) {
				// 无设备名称，自动注册 TODO

				// 请求http web api接口注册，返回设备的devicename,deviceSecret

				// 通过特定topic下发deviceName,deviceSecret等实现
			}

					if (deviceShadow != null) {
				String shadowJson = deviceShadow.getDeviceShadow();
				
				boolean isNeedUpdate=false;
				
				// TODO 检查影子设备数据，是否下发数据更新

				//topic发送数据
				//post 的需要回发消息 ，set的不需要
				if(isNeedUpdate)
				{
					// 回复消息
					String desttopic = "/sys/" + productKey + "/" + deviceName + "/thing/event/property/xxxxx";
					

					Message trannewMsg = new Message();

					trannewMsg.putHeader(MessageHeader.TOPIC, desttopic);
					trannewMsg.putHeader(MessageHeader.QOS, 1);
					trannewMsg.putHeader(MessageHeader.DUP, false);

					/*trannewMsg.setPayload(("post消息成功,当前值为:"+jsondata).getBytes());

					messageDispatcher.appendMessage(trannewMsg);
					clusterMessageTransfer.dispatcherMessage2Cluster(trannewMsg);*/
				}
			}

			return true;
		} else {
			log.error("连接中用户名不包含设备名称及产品key: username:" + username);
			return false;
		}
	}

	@Override
	public boolean verfyHeartbeatTime(String clientId, int time) {
		return true;
	}
}
