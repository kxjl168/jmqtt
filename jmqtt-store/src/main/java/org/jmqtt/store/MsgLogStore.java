package org.jmqtt.store;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.msgdata.MsgLog;

import java.util.Collection;
/**
 * 消息日志，计费，日志上报查询
 * MsgLogStore.java.
 * 
 * @author zj
* @version 1.0.1 2020年2月12日
* @revision zj 2020年2月12日
* @since 1.0.1
 */
public interface MsgLogStore {

    void clearMsgLog(String dayDate);

 
    /**
     * 本地存储计费消息日志 日期为到秒的格式
     * @param dateDate  2020-01-01 12:01:02  
     * @param message
     * @return
     * @author zj
     * @date 2020年2月12日
     */
    boolean saveMsgLog(String dateDate, MsgLog message);

    /**
     * 获取指定日的所有日志数据 ,日期为过滤日期，可以为到天，到小时， 
     * @param date
     * @return
     * @author zj
     * @date 2020年2月12日
     */
    Collection<MsgLog> getAllMsgLog(String dateDate); 


}
