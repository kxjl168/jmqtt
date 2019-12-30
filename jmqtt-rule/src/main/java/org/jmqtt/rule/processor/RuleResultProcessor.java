package org.jmqtt.rule.processor;


import org.jmqtt.rule.common.ZRuleCommand;


import io.netty.channel.ChannelHandlerContext;

/**
 * 具体数据处理实现<br>
 * 转发至mqtt/数据库等
 * RuleResultProcessor.java.
 * 
 * @author zj
* @version 1.0.1 2019年12月27日
* @revision zj 2019年12月27日
* @since 1.0.1
 */
public interface RuleResultProcessor {

	ZRuleCommand processRequest( ZRuleCommand cmd);

}
