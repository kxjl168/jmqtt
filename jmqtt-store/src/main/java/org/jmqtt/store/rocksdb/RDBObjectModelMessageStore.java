package org.jmqtt.store.rocksdb;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.iot.IotObject;
import org.jmqtt.common.helper.SerializeHelper;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.store.ObjectModelMessageStore;


import org.jmqtt.store.rocksdb.db.RDB;
import org.jmqtt.store.rocksdb.db.RDBStorePrefix;
import org.rocksdb.ColumnFamilyHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.List;

public class RDBObjectModelMessageStore implements ObjectModelMessageStore {

    private static final Logger log = LoggerFactory.getLogger(LoggerName.STORE);

    private RDB rdb;

    public RDBObjectModelMessageStore(RDB rdb){
        this.rdb = rdb;
    }

    @Override
  public IotObject getIotObjectMessage(String productKey) {
        byte[] value = this.rdb.get(columnFamilyHandle(),key(productKey));
        if(value == null){
            log.warn("The will message is not exist,productKey = {}",productKey);
            return null;
        }
        return SerializeHelper.deserialize(value,IotObject.class);
    }

    @Override
    public boolean hasIotObjectMessage(String productKey) {
        return this.rdb.get(columnFamilyHandle(),key(productKey)) != null;
    }

    @Override
    public void storeIotObjectMessage(String productKey, IotObject message) {
        this.rdb.putSync(columnFamilyHandle(),key(productKey),SerializeHelper.serialize(message));
    }

    @Override
    public IotObject removeIotObjectMessage(String productKey) {
        byte[] key = key(productKey);
        byte[] value = this.rdb.get(columnFamilyHandle(),key);
        if(value == null){
            log.warn("The rule message is not exist,cause = {}",productKey);
            return null;
        }
        this.rdb.delete(columnFamilyHandle(),key);
        return SerializeHelper.deserialize(value,IotObject.class);
    }

    private byte[] key(String productKey){
        return (RDBStorePrefix.RULE_MESSAGE + productKey).getBytes(Charset.forName("UTF-8"));
    }


    private ColumnFamilyHandle columnFamilyHandle(){
        return this.rdb.getColumnFamilyHandle(RDBStorePrefix.IOTOBJECTMODEL_MESSAGE);
    }
}
