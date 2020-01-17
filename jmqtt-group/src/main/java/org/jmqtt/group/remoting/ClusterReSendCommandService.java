package org.jmqtt.group.remoting;

import io.netty.channel.Channel;

import org.jmqtt.common.bean.InvokeCallback;
import org.jmqtt.common.helper.RejectHandler;
import org.jmqtt.common.helper.ThreadFactoryImpl;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.group.ClusterRemotingClient;
import org.jmqtt.group.protocol.ClusterRemotingCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * failed cluster command retry queue
 */
public class ClusterReSendCommandService {

    private static final Logger log = LoggerFactory.getLogger(LoggerName.CLUSTER);
    private boolean stoped = false;
    private BlockingQueue<ResendCommand> resendCommandQueue = new LinkedBlockingQueue<>(10000);
    private ThreadPoolExecutor resendExecutor;
    // TODO 应该使用ClusterOutrAPI统一进行集群消息处理（集群客户端）
    //private AbstractNettyCluster nettyCluster;
    
    ClusterRemotingClient clusterRemotingClient;


    public ClusterReSendCommandService(ClusterRemotingClient clusterRemotingClient){
        this.clusterRemotingClient = clusterRemotingClient;
    }

    public void start() {
        this.resendExecutor = new ThreadPoolExecutor(4,
                4,
                60 * 1000,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(100000),
                new ThreadFactoryImpl("pollClusterResender"),
                new RejectHandler("pollClusterResenderRejectHandler", 100000));

        new Thread(new Runnable() {
            @Override
            public void run() {
                int waitTime = 1000;
                while (!stoped) {
                    try {
                        List<ResendCommand> resendCommandList = new ArrayList(100);
                        ResendCommand resendCommand;
                        for (int i = 0; i < 100; i++) {
                            if (i == 0) {
                                resendCommand = resendCommandQueue.poll(waitTime, TimeUnit.MILLISECONDS);
                            } else {
                                resendCommand = resendCommandQueue.poll();
                            }
                            if (Objects.nonNull(resendCommand)) {
                                resendCommandList.add(resendCommand);
                            } else {
                                break;
                            }
                        }
                        if (resendCommandList.size() > 0) {
                            ResendTask resendTask = new ResendTask(resendCommandList);
                            resendExecutor.submit(resendTask).get();
                        }
                    } catch (InterruptedException e) {
                        log.warn("poll wrong.");
                    } catch (ExecutionException e) {
                        log.warn("executed wrong.");
                    }
                }
                log.info("Shutdown cluster resend service success.");
            }
        }).start();
    }

    public void shutdown() {
        this.stoped = true;
        this.resendExecutor.shutdown();
    }

    /**
     * add failed command
     */
    public boolean appendMessage(String ipport, ClusterRemotingCommand command, long timeout, InvokeCallback invokeCallback) {
        ResendCommand resendCommand = new ResendCommand(ipport,command,timeout,invokeCallback);
        boolean isNotFull = resendCommandQueue.offer(resendCommand);
        if (!isNotFull) {
            log.warn("[ResendCommand] -> the buffer queue is full");
        }
        return isNotFull;
    }

    public boolean appendMessage(ResendCommand resendCommand) {
        boolean isNotFull = resendCommandQueue.offer(resendCommand);
        if (!isNotFull) {
            log.warn("[ResendCommand] -> the buffer queue is full");
        }
        return isNotFull;
    }


    /**
     * resend command bean
     */
    class ResendCommand{
        private String  ipport;
        private ClusterRemotingCommand command;
        private long timeout;
        private InvokeCallback invokeCallback;
        public ResendCommand(String ipport, ClusterRemotingCommand command, long timeout, InvokeCallback invokeCallback){
            this.ipport = ipport;
            this.command = command;
            this.timeout = timeout;
            this.invokeCallback = invokeCallback;
        }

        public String getIpport() {
            return ipport;
        }

        public long getTimeout() {
            return timeout;
        }

        public InvokeCallback getInvokeCallback() {
            return invokeCallback;
        }

        public ClusterRemotingCommand getCommand() {
            return command;
        }

        public void setIpport(String ipport){
            this.ipport = ipport;
        }

        public void setCommand(ClusterRemotingCommand command) {
            this.command = command;
        }

        public void setInvokeCallback(InvokeCallback invokeCallback) {
            this.invokeCallback = invokeCallback;
        }

        public void setTimeout(long timeout) {
            this.timeout = timeout;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public String toString() {
            return "ResendCommand{" +
                    "ipport='" + ipport + '\'' +
                    ", command='" + command + '\'' +
                    ", timeout=" + timeout + '\'' +
                    ", invokeCallback'" + invokeCallback +
                    '}';
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false ;
            else{
                if (obj instanceof ResendCommand){
                    ResendCommand c = (ResendCommand) obj;
                    if(c.ipport.equals(ipport)
                            && c.invokeCallback.equals(invokeCallback)
                            && c.timeout == timeout
                            && c.command.equals(command)){
                        return true ;
                    }
                }
            }
            return false ;
        }

    }


    /**
     * resend task
     */
    class ResendTask implements Runnable {

        private List<ResendCommand> resendCommandList;
        public ResendTask(List<ResendCommand> resendCommandList){
            this.resendCommandList = resendCommandList;
        }

        @Override
        public void run() {
            if (Objects.nonNull(resendCommandList)) {
                for(ResendCommand resendCommand : resendCommandList) {
                    try{
                    	clusterRemotingClient.invokeAsync(
                                resendCommand.getIpport(),
                                resendCommand.getCommand(),
                                resendCommand.getTimeout(),
                                resendCommand.getInvokeCallback()
                        );
                    } catch (Exception e){
                        // failed again, reenqueue.
                        appendMessage(resendCommand);
                    }
                }
            }
        }

    }

}
