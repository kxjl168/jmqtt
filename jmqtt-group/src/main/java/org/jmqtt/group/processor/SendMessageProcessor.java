package org.jmqtt.group.processor;

import io.netty.channel.ChannelHandlerContext;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.group.message.MessageListener;
import org.jmqtt.group.message.ReceiveMessageStatus;
import org.jmqtt.group.protocol.ClusterRemotingCommand;
import org.jmqtt.group.protocol.ClusterResponseCode;
import org.jmqtt.group.protocol.CommandConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理集群中节点上线/消息转发等
 * SendMessageProcessor.java.
 * 
 * @author zj
* @version 1.0.1 2019年12月13日
* @revision zj 2019年12月13日
* @since 1.0.1
 */
public class SendMessageProcessor implements ClusterRequestProcessor{

    private static final Logger log = LoggerFactory.getLogger(LoggerName.MESSAGE_TRACE);
    private MessageListener messageListener;

    public SendMessageProcessor(MessageListener messageListener){
        this.messageListener = messageListener;
    }

    @Override
    public ClusterRemotingCommand processRequest(ChannelHandlerContext ctx, ClusterRemotingCommand cmd) {
       
    	String from=cmd.getExtField(CommandConstant.NODE_NAME);
    	log.debug("***ClusterServer**** SendMessageProcess fromnode:{} ",from);
    	
    	ReceiveMessageStatus result = this.messageListener.receive(cmd);
        ClusterRemotingCommand response = null;
        
    	response=new ClusterRemotingCommand(cmd.getCode());
    	//zj
    	//传回传入的id
    	response.setOpaque(cmd.getOpaque());
    	
    	
        if(result == ReceiveMessageStatus.OK){
            //response = new ClusterRemotingCommand(ClusterResponseCode.RESPONSE_OK);
        	
        
        	response.setResponseCode(ClusterResponseCode.RESPONSE_OK);
        } else {
            //response = new ClusterRemotingCommand(ClusterResponseCode.ERROR_RESPONSE);
        	
        	response.setResponseCode(ClusterResponseCode.ERROR_RESPONSE);
            
        }
        return response;
    }
}
