package org.jmqtt.iot.processor;

import org.jmqtt.common.bean.msgdata.MsgLog;

/**
 * 日志处理实现
 * LogPipeLine.java.
 * 
 * @author zj
* @version 1.0.1 2020年2月13日
* @revision zj 2020年2月13日
* @since 1.0.1
 */
public interface LogPipeLine {

	/**
	 * 具体日志处理实现
	 * @param log
	 * @author zj
	 * @date 2020年2月13日
	 */
	void dealLog(MsgLog log);
}
