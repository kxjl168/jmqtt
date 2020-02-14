package org.jmqtt.group.protocol;

/**
 * cluster message request code
 * 0-100
 */
public class ClusterRequestCode {

    /**
     * connect and get other node from the cluster
     */
    public static final int FETCH_NODES = 1;

    /**
     * notice there is a new client
     */
    public static final int NOTICE_NEW_CLIENT = 2;

    /**
     * send message to other jmqtt server
     */
    public static final int SEND_MESSAGE = 4;

    /**
     * transfer session -> subscribe
     */
    public static final int TRANSFER_SESSION = 5;

    /**
     * transfer offline message
     */
    public static final int TRANSFER_SESSION_MESSAGE = 6;
    
    
    //通知其他节点规则引擎规则变化
    public static final int NOTICE_RULE_CHAGNEND=7;
    
   //  通知其他节点物模型变化
    public static final int NOTICE_OBJ_MODEL_CHAGNEND=8;

    //  通知其他节点  web下发设备影子更新
    public static final int NOTICE_MODEL_SHADOW_CHANGED=9;

    //  通知其他节点  设备影子数据已下发更新完成
    public static final int NOTICE_MODEL_SHADOW_DOWN_UPDATED=10;

    

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
		case ClusterRequestCode.FETCH_NODES:
			requestTypeName = "FETCH_NODES";
			break;
		case ClusterRequestCode.NOTICE_NEW_CLIENT:
			requestTypeName = "NOTICE_NEW_CLIENT";
			break;
		case ClusterRequestCode.SEND_MESSAGE:
			requestTypeName = "SEND_MESSAGE";
			break;
		case ClusterRequestCode.TRANSFER_SESSION:
			requestTypeName = "TRANSFER_SESSION";
			break;
		case ClusterRequestCode.TRANSFER_SESSION_MESSAGE:
			requestTypeName = "TRANSFER_SESSION_MESSAGE";
			break;
			
		case ClusterRequestCode.NOTICE_RULE_CHAGNEND:
			requestTypeName = "NOTICE_RULE_CHAGNEND";
			break;
		case ClusterRequestCode.NOTICE_OBJ_MODEL_CHAGNEND:
			requestTypeName = "NOTICE_OBJ_MODEL_CHAGNEND";
			break;
		case ClusterRequestCode.NOTICE_MODEL_SHADOW_CHANGED:
			requestTypeName = "NOTICE_MODEL_SHADOW_CHANGED";
			break;
		case ClusterRequestCode.NOTICE_MODEL_SHADOW_DOWN_UPDATED:
			requestTypeName = "NOTICE_MODEL_SHADOW_DOWN_UPDATED";
			break;	
			
			
			
		default:
			break;
		}

		return requestTypeName;

	}
}
