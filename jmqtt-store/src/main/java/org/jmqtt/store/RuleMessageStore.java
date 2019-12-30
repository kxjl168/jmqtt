package org.jmqtt.store;

import java.util.List;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.ZRule;

/**
 * 规则数据存储
 * RuleMessageStore.java.
 * 
 * @author zj
* @version 1.0.1 2019年12月27日
* @revision zj 2019年12月27日
* @since 1.0.1
 */
public interface RuleMessageStore {

    List<ZRule> getRuleMessage(String productKey);

    boolean hasRuleMessage(String productKey);

    void storeRuleMessage(String productKey, List<ZRule> rules);

    List<ZRule> removeRuleMessage(String productKey);
}
