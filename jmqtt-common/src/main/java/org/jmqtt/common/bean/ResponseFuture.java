package org.jmqtt.common.bean;


import java.util.concurrent.atomic.AtomicBoolean;

import io.netty.channel.Channel;

public abstract class ResponseFuture {

	protected Channel channel;
	protected final int opaque;
	protected final long timeoutMillis;
	protected final long beginTime = System.currentTimeMillis();
	protected volatile Throwable cause;
	protected final InvokeCallback invokeCallback;
	protected volatile boolean sendRequestOK = false;
    
  
	protected final SemaphoreReleaseOnlyOnce semaphoreReleaseOnlyOnce;
    /**
     * callbackFlag is true show that callback has invoked. no longer to excute it
     */
	protected AtomicBoolean callbackFlag = new AtomicBoolean(false);

    public ResponseFuture(Channel channel,int opaque,long timeoutMillis,InvokeCallback invokeCallback,SemaphoreReleaseOnlyOnce semaphoreReleaseOnlyOnce){
        this.channel = channel;
        this.opaque = opaque;
        this.timeoutMillis = timeoutMillis;
        this.invokeCallback = invokeCallback;
        this.semaphoreReleaseOnlyOnce = semaphoreReleaseOnlyOnce;
    }

    public void executeCallback(){
        if(invokeCallback != null){
            if(callbackFlag.compareAndSet(false,true)){
                this.invokeCallback.invokeComplete(this);
            }
        }
    }

    public void release(){
        if(this.semaphoreReleaseOnlyOnce != null){
            this.semaphoreReleaseOnlyOnce.release();
        }
    }

    public int getOpaque() {
        return opaque;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public long getTimeoutMillis() {
        return timeoutMillis;
    }

    public long getBeginTime() {
        return beginTime;
    }

 

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public InvokeCallback getInvokeCallback() {
        return invokeCallback;
    }

    public AtomicBoolean getCallbackFlag() {
        return callbackFlag;
    }

    public void setCallbackFlag(AtomicBoolean callbackFlag) {
        this.callbackFlag = callbackFlag;
    }

    public boolean isSendRequestOK() {
        return sendRequestOK;
    }

    public void setSendRequestOK(boolean sendRequestOK) {
        this.sendRequestOK = sendRequestOK;
    }

    @Override
    public String toString() {
        return "ResponseFuture{" +
                "opaque=" + opaque +
                ", channel=" + channel +
                ", timeoutMillis=" + timeoutMillis +
                ", beginTime=" + beginTime +
              //  ", clusterRemotingCommand=" + clusterRemotingCommand +
                ", cause=" + cause +
                ", invokeCallback=" + invokeCallback +
                ", callbackFlag=" + callbackFlag +
                '}';
    }
}
