package org.jmqtt.web.common;

/**
 * web端通信报文类型 web message request code 0-100
 * 
 * WebRequestCode.java.
 * 
 * @author zj
 * @version 1.0.1 2020年1月6日
 * @revision zj 2020年1月6日
 * @since 1.0.1
 */
public class WebRequestCode {

	/**
	 * WEB端保存更新规则数据
	 */
	public static final int SAVE_OR_UPDATE_RULE = 1;

	/**
	 * web端查询所有节点状态
	 */
	public static final int QUERY_NODES_STATUS = 2;

	/**
	 * web端查询单个指定节点配置
	 */
	public static final int QUERY_NODE_CONFIG = 4;

	/**
	 * web端查询单个节点上的topic信息
	 */
	public static final int QUERY_NODE_TOPICS = 5;
	
	/**
	 * WEB端保存更新物模型，修改属性，方法，事件，topic等
	 */
	public static final int SAVE_OR_UPDATE_IOT_MODEL = 6;

	
	/**
	 * WEB端保存更新物模型影子数据
	 */
	public static final int SAVE_OR_UPDATE_IOT_SHADOW = 7;
	
	

	/**
	 * 请求类型转换
	 * 
	 * @param requestcode
	 * @return
	 * @author zj
	 * @date 2019年12月13日
	 */
	public static String getRequestType(int requestcode) {
		String requestTypeName = "NULL";
		switch (requestcode) {
		case WebRequestCode.SAVE_OR_UPDATE_RULE:
			requestTypeName = "SAVE_OR_UPDATE_RULE";
			break;
		case WebRequestCode.QUERY_NODES_STATUS:
			requestTypeName = "QUERY_NODES_STATUS";
			break;
		case WebRequestCode.QUERY_NODE_TOPICS:
			requestTypeName = "QUERY_NODE_TOPICS";
			break;
		case WebRequestCode.QUERY_NODE_CONFIG:
			requestTypeName = "QUERY_NODE_CONFIG";
			break;
		case WebRequestCode.SAVE_OR_UPDATE_IOT_MODEL:
			requestTypeName = "SAVE_OR_UPDATE_IOT_MODEL";
			break;
		case WebRequestCode.SAVE_OR_UPDATE_IOT_SHADOW:
			requestTypeName = "SAVE_OR_UPDATE_IOT_SHADOW";
			break;	
			
			

		default:
			break;
		}

		return requestTypeName;

	}

}
