package org.jmqtt.group.protocol;

import org.jmqtt.common.log.LoggerName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 集群内部消息结构<br>
 * cluster remoting command 必须每次使用new 一个 <br>
 * opaque <AtomicInteger> 在初始化时默认自增 ClusterRemotingCommand.java.
 * 
 * @author zj
 * @version 1.0.1 2019年12月23日
 * @revision zj 2019年12月23日 备注
 * @since 1.0.1
 */
public class ClusterRemotingCommand {

	private static final Logger log = LoggerFactory.getLogger(LoggerName.CLUSTER);

	private static final AtomicInteger requestId = new AtomicInteger(0);

	/**
	 * cluster request code ,请求的业务类型(add zj)
	 */
	private int code;

	/**
	 * 返回的状态 zj add cluster response code
	 */
	private int responseCode = ClusterResponseCode.ERROR_RESPONSE;

	/**
	 * {@link MessageFlag}
	 */
	private int flag;

	/**
	 * zj 重发次数
	 */
	private int errorNum = 0;

	/**
	 * 0:request 1:response
	 */
	private int rpcType = 0;
	private int opaque = requestId.incrementAndGet();
	private HashMap<String, String> extField = new HashMap<>();
	private transient byte[] body;

	public ClusterRemotingCommand() {

		checkIntMax();
	}

	/**
	 * 
	 * AtomicInteger 最大超限重置
	 * @author zj
	 * @date 2019年12月24日
	 */
	private void checkIntMax() {

		if (opaque >= 2147483640) { //2147483640
			requestId.compareAndSet(opaque, 0);
		}

	}

	public ClusterRemotingCommand(int code) {
		this.code = code;

		checkIntMax();
	}

	public ClusterRemotingCommand(int code, byte[] body) {
		this.code = code;
		this.body = body;

		checkIntMax();
	}

	public void makeResponseType() {
		this.rpcType = 1;
	}

	public void putExtFiled(String key, String value) {
		this.extField.put(key, value);
	}

	public String getExtField(String key) {
		return this.extField.get(key);
	}

	public RemotingCommandType getType() {
		if (rpcType == 0) {
			return RemotingCommandType.REQUEST_COMMAND;
		}
		return RemotingCommandType.RESPONSE_COMMAND;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getOpaque() {
		return opaque;
	}

	public void setOpaque(int opaque) {
		this.opaque = opaque;
	}

	public HashMap<String, String> getExtField() {
		return extField;
	}

	public void setExtField(HashMap<String, String> extField) {
		this.extField = extField;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	public int getRpcType() {
		return rpcType;
	}

	public void setRpcType(int rpcType) {
		this.rpcType = rpcType;
	}

	@Override
	public String toString() {
		return "ClusterRemotingCommand{" + "requestId=" + requestId + ", code=" + code + ", flag=" + flag + ", rpcType="
				+ rpcType + ", opaque=" + opaque + ", extField=" + extField + ", responseCode=" + responseCode + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ClusterRemotingCommand cmd = (ClusterRemotingCommand) o;
		return code == cmd.code && responseCode == cmd.responseCode && flag == cmd.flag && rpcType == cmd.rpcType
				&& opaque == cmd.opaque && Objects.equals(extField, cmd.extField) && Arrays.equals(body, cmd.body);
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public int getErrorNum() {
		return errorNum;
	}

	public void setErrorNum(int errorNum) {
		this.errorNum = errorNum;
	}
}
