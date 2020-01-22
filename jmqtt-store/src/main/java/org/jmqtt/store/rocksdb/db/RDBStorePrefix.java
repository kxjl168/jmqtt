package org.jmqtt.store.rocksdb.db;

public interface RDBStorePrefix {

    String SESSION = "session:";

    String REC_FLOW_MESSAGE = "recFlowMessage:";

    String SEND_FLOW_MESSAGE = "sendFlowMessage:";

    String OFFLINE_MESSAGE = "offlineMessage:";

    String RETAIN_MESSAGE = "retainMessage:";

    String SUBSCRIPTION = "subscription:";

    String WILL_MESSAGE = "willMessage:";
    
    String RULE_MESSAGE = "ruleMessage:";
    
    //物模型数据
    String IOTOBJECTMODEL_MESSAGE = "iotModelMessage:";
}
