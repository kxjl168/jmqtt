package org.jmqtt.store.rocksdb;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.ZRule;
import org.jmqtt.common.helper.SerializeHelper;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.store.RuleMessageStore;

import org.jmqtt.store.rocksdb.db.RDB;
import org.jmqtt.store.rocksdb.db.RDBStorePrefix;
import org.rocksdb.ColumnFamilyHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.List;

public class RDBRuleMessageStore implements RuleMessageStore {

    private static final Logger log = LoggerFactory.getLogger(LoggerName.STORE);

    private RDB rdb;

    public RDBRuleMessageStore(RDB rdb){
        this.rdb = rdb;
    }

    @Override
  public List<ZRule> getRuleMessage(String productKey) {
        byte[] value = this.rdb.get(columnFamilyHandle(),key(productKey));
        if(value == null){
            log.warn("The will message is not exist,productKey = {}",productKey);
            return null;
        }
        return SerializeHelper.deserializeList(value,ZRule.class);
    }

    @Override
    public boolean hasRuleMessage(String productKey) {
        return this.rdb.get(columnFamilyHandle(),key(productKey)) != null;
    }

    @Override
    public void storeRuleMessage(String productKey, List<ZRule> message) {
        this.rdb.putSync(columnFamilyHandle(),key(productKey),SerializeHelper.serialize(message));
    }

    @Override
    public List<ZRule> removeRuleMessage(String productKey) {
        byte[] key = key(productKey);
        byte[] value = this.rdb.get(columnFamilyHandle(),key);
        if(value == null){
            log.warn("The rule message is not exist,cause = {}",productKey);
            return null;
        }
        this.rdb.delete(columnFamilyHandle(),key);
        return SerializeHelper.deserializeList(value,ZRule.class);
    }

    private byte[] key(String productKey){
        return (RDBStorePrefix.RULE_MESSAGE + productKey).getBytes(Charset.forName("UTF-8"));
    }


    private ColumnFamilyHandle columnFamilyHandle(){
        return this.rdb.getColumnFamilyHandle(RDBStorePrefix.RULE_MESSAGE);
    }
}
