package org.jmqtt.group.protocol;

/**
 * cluster message response code
 * 100-~
 */
public class ClusterResponseCode {

    /**
     * response ok
     */
    public static final int RESPONSE_OK = 100;

    /**
     * response error code
     */
    public static final int ERROR_RESPONSE = 101;

    /**
     * request code not supported
     */
    public static final int REQUEST_CODE_NOT_SUPPORTED = 102;

    
    /**
	 * 请求类型转换
	 * 
	 * @param requestcode
	 * @return
	 * @author zj
	 * @date 2019年12月13日
	 */
	public static String getResponseType(int responsecode) {
		String requestTypeName = "NULL";
		switch (responsecode) {
		case ClusterResponseCode.RESPONSE_OK:
			requestTypeName = "RESPONSE_OK";
			break;
		case ClusterResponseCode.ERROR_RESPONSE:
			requestTypeName = "ERROR_RESPONSE";
			break;
		case ClusterResponseCode.REQUEST_CODE_NOT_SUPPORTED:
			requestTypeName = "REQUEST_CODE_NOT_SUPPORTED";
			break;
		
		default:
			break;
		}

		return requestTypeName;

	}
}
