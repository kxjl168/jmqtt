package com.ztgm.openplat.bean;

import java.io.Serializable;

/**
 * 通用数据字段
 * CommonData.java.
 * 
 * @author zj
* @version 1.0.1 2020年1月10日
* @revision zj 2020年1月10日
* @since 1.0.1
 */
public class CommonQueryData  {


	private String Version;// 是 API版本号，为日期形式：YYYY-MM-DD，最新版本为2018-01-20 。每个接口可以存在多个版本。
	private String AccessKeyId;// String 是 用户管理中心，即可创建和查看AccessKey。
	private String Signature;// String 是 签名结果串。
	private String SignatureMethod;// String 是 签名方式，目前支持HMAC-SHA1。
	private String Timestamp;// String 是 请求的时间戳。日期格式按照ISO8601标准表示，并需要使用UTC时间。格式为YYYY-MM-DDThh:mm:ssZ。
	// 例如，2016-01-04T12:00:00Z表示北京时间2016年01月04日20点0分0秒。
	private String SignatureVersion;// String 是 签名算法版本。目前版本是1.0。
	private String SignatureNonce;// String 是 唯一随机数。用于防止网络重放攻击。用户在不同请求中要使用不同的随机数值。
	

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public String getAccessKeyId() {
		return AccessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		AccessKeyId = accessKeyId;
	}

	public String getSignature() {
		return Signature;
	}

	public void setSignature(String signature) {
		Signature = signature;
	}

	public String getSignatureMethod() {
		return SignatureMethod;
	}

	public void setSignatureMethod(String signatureMethod) {
		SignatureMethod = signatureMethod;
	}

	public String getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(String timestamp) {
		Timestamp = timestamp;
	}

	public String getSignatureVersion() {
		return SignatureVersion;
	}

	public void setSignatureVersion(String signatureVersion) {
		SignatureVersion = signatureVersion;
	}

	public String getSignatureNonce() {
		return SignatureNonce;
	}

	public void setSignatureNonce(String signatureNonce) {
		SignatureNonce = signatureNonce;
	}

	
}
