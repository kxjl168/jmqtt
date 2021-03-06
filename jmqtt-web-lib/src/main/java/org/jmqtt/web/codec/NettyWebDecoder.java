package org.jmqtt.web.codec;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.jmqtt.common.log.LoggerName;

import org.jmqtt.common.helper.RemotingHelper;
import org.jmqtt.web.common.WebRemotingCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

public class NettyWebDecoder extends LengthFieldBasedFrameDecoder {

    private static final Logger log = LoggerFactory.getLogger(LoggerName.CLUSTER);

    private static final int FRAME_MAX_LENGTH = Integer.parseInt(System.getProperty("org.jmqtt.group.remoting.frameMaxLength","16777216"));

    public NettyWebDecoder(){
        super(FRAME_MAX_LENGTH,0,4,0,4);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = null;
        try{
            frame = (ByteBuf) super.decode(ctx,in);
            if(null == frame){
                return null;
            }
            ByteBuffer byteBuffer = frame.nioBuffer();
            return decode(byteBuffer);
        }catch(Exception ex){
            log.error("Decode exception,ex={}",ex);
            RemotingHelper.closeChannel(ctx.channel());
        }finally {
            if (null != frame){
                frame.release();
            }
        }
        return null;
    }

    private WebRemotingCommand decode(ByteBuffer byteBuffer){
        int allLen = byteBuffer.limit();

        // get header data
        int headerLength = byteBuffer.getInt();
        byte[] headerData = new byte[headerLength];
        byteBuffer.get(headerData);
        WebRemotingCommand cmd = JSONObject.parseObject(headerData,WebRemotingCommand.class);

        // get body data
        int bodyLength = allLen - headerLength - 4;
        byte[] bodyData = null;
        if(bodyLength > 0){
            bodyData = new byte[bodyLength];
            byteBuffer.get(bodyData);
        }

        // set body data
        cmd.setBody(bodyData);
        return cmd;
    }
}
