package org.jmqtt.store.rocksdb;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.msgdata.MsgLog;
import org.jmqtt.common.helper.SerializeHelper;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.store.MsgLogStore;
import org.jmqtt.store.OfflineMessageStore;
import org.jmqtt.store.rocksdb.db.RDB;
import org.jmqtt.store.rocksdb.db.RDBStorePrefix;
import org.rocksdb.ColumnFamilyHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 消息详细日志
 * RDBMsgLogStore.java.
 * 
 * @author zj
* @version 1.0.1 2020年2月12日
* @revision zj 2020年2月12日
* @since 1.0.1
 */
public class RDBMsgLogStore implements MsgLogStore {

    private static final Logger log = LoggerFactory.getLogger(LoggerName.STORE);
    private RDB rdb;

    public RDBMsgLogStore(RDB rdb){
        this.rdb  = rdb;
    }

    @Override
    public void clearMsgLog(String dateDate) {
        this.rdb.deleteByPrefix(columnFamilyHandle(),keyPrefix(dateDate));
    }

 
    @Override
    public boolean saveMsgLog(String dateDate, MsgLog message) {
        try{
            this.rdb.putSync(columnFamilyHandle(),key(dateDate,message.getDeviceName()), SerializeHelper.serialize(message));
            return true;
        }catch (Exception ex){
            log.warn("saveMsgLog failure,cause={}",ex);
        }
        return false;
    }

    @Override
    public Collection<MsgLog> getAllMsgLog(String dayDate) {
        Collection<byte[]> values = this.rdb.getByPrefix(columnFamilyHandle(),keyPrefix(dayDate));
        Collection<MsgLog> msglogs = new ArrayList<>(values.size());
        for(byte[] value : values){
        	msglogs.add(SerializeHelper.deserialize(value,MsgLog.class));
        }
        return msglogs;
    }

    private byte[] keyPrefix(String dayDate){
        return (RDBStorePrefix.MSG_FEE_LOG + dayDate).getBytes(Charset.forName("UTF-8"));
    }

    private byte[] key(String dayDate,String deviceName){
        return (RDBStorePrefix.MSG_FEE_LOG + dayDate + deviceName).getBytes(Charset.forName("UTF-8"));
    }


    private ColumnFamilyHandle columnFamilyHandle(){
        return this.rdb.getColumnFamilyHandle(RDBStorePrefix.MSG_FEE_LOG);
    }
}
