package org.jmqtt.web.remoting;


import org.jmqtt.remoting.RemotingService;
import org.jmqtt.web.processor.WebRequestProcessor;

import java.util.concurrent.ExecutorService;

/**
 * receive request and handle some service
 */
public interface WebRemotingServer extends RemotingService {

    void registerWebProcessor(int code, WebRequestProcessor processor, ExecutorService executorService);
    
    void start() ;
}
