package org.jmqtt.broker;

import io.netty.handler.codec.mqtt.MqttMessageType;
import org.jmqtt.broker.acl.ConnectPermission;
import org.jmqtt.broker.acl.PubSubPermission;
import org.jmqtt.broker.acl.impl.DefaultConnectPermission;
import org.jmqtt.broker.acl.impl.DefaultPubSubPermission;
import org.jmqtt.broker.client.ClientLifeCycleHookService;
import org.jmqtt.broker.dispatcher.DefaultDispatcherMessage;
import org.jmqtt.broker.processor.*;
import org.jmqtt.broker.recover.ReSendMessageService;
import org.jmqtt.broker.subscribe.DefaultSubscriptionTreeMatcher;
import org.jmqtt.common.subscribe.SubscriptionMatcher;
import org.jmqtt.common.config.BrokerConfig;
import org.jmqtt.common.config.ClusterConfig;
import org.jmqtt.common.config.IotConfig;
import org.jmqtt.common.config.NettyConfig;
import org.jmqtt.common.config.RuleConfig;
import org.jmqtt.common.config.StoreConfig;
import org.jmqtt.common.config.WebConfig;
import org.jmqtt.common.helper.MixAll;
import org.jmqtt.common.helper.RejectHandler;
import org.jmqtt.common.helper.ThreadFactoryImpl;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.common.message.MessageDispatcher;
import org.jmqtt.group.ClusterRemotingClient;
import org.jmqtt.group.ClusterRemotingServer;
import org.jmqtt.group.MessageTransfer;
import org.jmqtt.group.message.WebSRequestListener;
import org.jmqtt.broker.dispatcher.DefaultMessageTransfer;
import org.jmqtt.broker.dispatcher.InnerMessageTransfer;
import org.jmqtt.group.processor.ClusterOuterAPI;
import org.jmqtt.group.processor.ClusterRequestProcessor;
import org.jmqtt.group.processor.FetchNodeProcessor;
import org.jmqtt.group.processor.WebSRequestProcessor;
import org.jmqtt.group.protocol.ClusterRequestCode;
import org.jmqtt.group.remoting.NettyClusterRemotingClient;
import org.jmqtt.group.remoting.NettyClusterRemotingServer;
import org.jmqtt.iot.acl.IOTConnectPermission;
import org.jmqtt.iot.processor.IotObjectEngin;
import org.jmqtt.iot.processor.impl.DefaultIOTObjectEngin;
import org.jmqtt.remoting.netty.ChannelEventListener;
import org.jmqtt.remoting.netty.NettyRemotingServer;
import org.jmqtt.remoting.netty.RequestProcessor;
import org.jmqtt.rule.processor.RuleEngin;
import org.jmqtt.rule.processor.impl.DefaultIOTRuleEngin;
import org.jmqtt.store.*;
import org.jmqtt.store.memory.DefaultMqttStore;
import org.jmqtt.store.rocksdb.RDBMqttStore;
import org.jmqtt.web.listener.impl.DefaultWebSRequestListenerImpl;

import org.jmqtt.web.remoting.DefaultWebNettyRemotingServer;
import org.jmqtt.web.remoting.WebRemotingServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BrokerController {

    private static final Logger log = LoggerFactory.getLogger(LoggerName.BROKER);

    private BrokerConfig brokerConfig;
    private NettyConfig nettyConfig;
    private StoreConfig storeConfig;
    private ClusterConfig clusterConfig;
    private WebConfig webConfig;
    private RuleConfig ruleConfig;
    private IotConfig iotConfig;
    
    
    private ExecutorService connectExecutor;
    private ExecutorService pubExecutor;
    private ExecutorService subExecutor;
    private ExecutorService pingExecutor;
    private LinkedBlockingQueue connectQueue;
    private LinkedBlockingQueue pubQueue;
    private LinkedBlockingQueue subQueue;
    private LinkedBlockingQueue pingQueue;
    private ChannelEventListener channelEventListener;
    private NettyRemotingServer remotingServer;
    private MessageDispatcher messageDispatcher;
    private FlowMessageStore flowMessageStore;
    private SubscriptionMatcher subscriptionMatcher;
    private WillMessageStore willMessageStore;
    
    private RuleMessageStore ruleMessageStore;
    private ObjectModelMessageStore iotObjectModelMessageStore;
    
    
    private RetainMessageStore retainMessageStore;
    private OfflineMessageStore offlineMessageStore;
    private SubscriptionStore subscriptionStore;
    private SessionStore sessionStore;
    private AbstractMqttStore abstractMqttStore;
    private ConnectPermission connectPermission;
    private PubSubPermission pubSubPermission;
    private ReSendMessageService reSendMessageService;
    /**
     * cluster message transfer innerMessageTransfer is pluginable
     */
    private ClusterRemotingClient clusterClient;
    private ClusterRemotingServer clusterServer;
    private ClusterOuterAPI clusterOuterAPI;
    private InnerMessageTransfer innerMessageTransfer;
    private ExecutorService clusterService;
    
    private RuleEngin ruleEngin;
    private ExecutorService ruleExecutorService;
    
    private WebRemotingServer webRemotingServer;
    private ExecutorService webExecutorService;
    
    private IotObjectEngin iotEngin;
    private ExecutorService iotExecutorService;
    
    private WebSRequestListener websrequestListener;

    public BrokerController(BrokerConfig brokerConfig, NettyConfig nettyConfig,
    		StoreConfig storeConfig, ClusterConfig clusterConfig
    		,WebConfig webConfig,RuleConfig ruleConfig,IotConfig iotConfig) {
        this.brokerConfig = brokerConfig;
        this.nettyConfig = nettyConfig;
        this.storeConfig = storeConfig;
        this.clusterConfig = clusterConfig;
        this.webConfig=webConfig;
        this.ruleConfig=ruleConfig;
        this.iotConfig=iotConfig;

        this.connectQueue = new LinkedBlockingQueue(100000);
        this.pubQueue = new LinkedBlockingQueue(100000);
        this.subQueue = new LinkedBlockingQueue(100000);
        this.pingQueue = new LinkedBlockingQueue(10000);

        {//store pluggable
            switch (storeConfig.getStoreType()) {
                case 1:
                    this.abstractMqttStore = new RDBMqttStore(storeConfig);
                    break;
                default:
                    this.abstractMqttStore = new DefaultMqttStore();
                    break;
            }
            try {
                this.abstractMqttStore.init();
            } catch (Exception e) {
                System.out.println("Init Store failure,exception=" + e);
                e.printStackTrace();
            }
            this.flowMessageStore = this.abstractMqttStore.getFlowMessageStore();
            this.willMessageStore = this.abstractMqttStore.getWillMessageStore();
            this.retainMessageStore = this.abstractMqttStore.getRetainMessageStore();
            this.offlineMessageStore = this.abstractMqttStore.getOfflineMessageStore();
            this.subscriptionStore = this.abstractMqttStore.getSubscriptionStore();
            this.sessionStore = this.abstractMqttStore.getSessionStore();
            this.ruleMessageStore=this.abstractMqttStore.getRuleMessageStore();
            
            this.iotObjectModelMessageStore=this.abstractMqttStore.getIotObjectModelMessageStore();
        }

        {// permission pluggable
           // this.connectPermission = new DefaultConnectPermission();
            this.pubSubPermission = new DefaultPubSubPermission();
            
            //iot鉴权 zj
            this.connectPermission=new IOTConnectPermission(iotObjectModelMessageStore, iotConfig);
        }

        this.subscriptionMatcher = new DefaultSubscriptionTreeMatcher();
        this.messageDispatcher = new DefaultDispatcherMessage(brokerConfig.getPollThreadNum(), subscriptionMatcher, flowMessageStore, offlineMessageStore);

        this.channelEventListener = new ClientLifeCycleHookService(willMessageStore, messageDispatcher);
        this.remotingServer = new NettyRemotingServer(brokerConfig, nettyConfig, channelEventListener);
        this.reSendMessageService = new ReSendMessageService(offlineMessageStore, flowMessageStore);

        int coreThreadNum = Runtime.getRuntime().availableProcessors();
        this.connectExecutor = new ThreadPoolExecutor(coreThreadNum * 2,
                coreThreadNum * 2,
                60000,
                TimeUnit.MILLISECONDS,
                connectQueue,
                new ThreadFactoryImpl("ConnectThread"),
                new RejectHandler("connect", 100000));
        this.pubExecutor = new ThreadPoolExecutor(coreThreadNum * 2,
                coreThreadNum * 2,
                60000,
                TimeUnit.MILLISECONDS,
                pubQueue,
                new ThreadFactoryImpl("PubThread"),
                new RejectHandler("pub", 100000));
        this.subExecutor = new ThreadPoolExecutor(coreThreadNum * 2,
                coreThreadNum * 2,
                60000,
                TimeUnit.MILLISECONDS,
                subQueue,
                new ThreadFactoryImpl("SubThread"),
                new RejectHandler("sub", 100000));
        this.pingExecutor = new ThreadPoolExecutor(coreThreadNum,
                coreThreadNum,
                60000,
                TimeUnit.MILLISECONDS,
                pingQueue,
                new ThreadFactoryImpl("PingThread"),
                new RejectHandler("heartbeat", 100000));

        /* cluster  */
        this.clusterClient = new NettyClusterRemotingClient(clusterConfig);
        this.clusterServer = new NettyClusterRemotingServer(clusterConfig,this.clusterClient);
        {
            // message transfer is pluginAble
            MessageTransfer messageTransfer = new DefaultMessageTransfer(this.clusterClient,this.clusterServer);
            this.innerMessageTransfer = new InnerMessageTransfer(this,messageTransfer);
        }
        this.clusterOuterAPI = new ClusterOuterAPI(clusterConfig, clusterClient,webConfig);
        this.clusterService = new ThreadPoolExecutor(coreThreadNum * 2,
                coreThreadNum * 2,
                60000,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10000),
                new ThreadFactoryImpl("ClusterThread"),
                new RejectHandler("sub", 100000));
        
    
        this.webExecutorService = new ThreadPoolExecutor(coreThreadNum * 2,
                coreThreadNum * 2,
                60000,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10000),
                new ThreadFactoryImpl("WebThread"),
                new RejectHandler("sub", 100000));
        
        
        this.iotExecutorService = new ThreadPoolExecutor(coreThreadNum * 2,
                coreThreadNum * 2,
                60000,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10000),
                new ThreadFactoryImpl("IotThread"),
                new RejectHandler("sub", 100000));
        
        //iot zj
        this.iotEngin=new DefaultIOTObjectEngin(coreThreadNum * 2, iotObjectModelMessageStore, subscriptionMatcher, messageDispatcher, iotExecutorService, iotConfig, innerMessageTransfer);
        //日志下行
        this.messageDispatcher.setIotEngin(this.iotEngin);
        
        this.ruleExecutorService = new ThreadPoolExecutor(coreThreadNum * 2,
                coreThreadNum * 2,
                60000,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10000),
                new ThreadFactoryImpl("RuleThread"),
                new RejectHandler("sub", 100000));
        //zj
        this.ruleEngin=new DefaultIOTRuleEngin(coreThreadNum * 2, ruleMessageStore, subscriptionMatcher, messageDispatcher, ruleExecutorService,ruleConfig,innerMessageTransfer);
        this.ruleEngin.setIotEngin(this.iotEngin);
        
        
         websrequestListener=new DefaultWebSRequestListenerImpl(iotEngin,ruleEngin,iotObjectModelMessageStore,innerMessageTransfer,messageDispatcher);
        
        //zj
        this.webRemotingServer=new DefaultWebNettyRemotingServer(ruleEngin,iotEngin, webConfig, brokerConfig, nettyConfig, storeConfig, clusterConfig, 
        	ruleConfig,subscriptionStore,	 webExecutorService,websrequestListener,innerMessageTransfer);
        
        
      
    }


    public void start() {

        MixAll.printProperties(log, brokerConfig);
        MixAll.printProperties(log, nettyConfig);
        MixAll.printProperties(log, storeConfig);
        MixAll.printProperties(log, clusterConfig);
        MixAll.printProperties(log, ruleConfig);
        MixAll.printProperties(log, iotConfig);
        
        
        

        {//init and register mqtt remoting processor
            RequestProcessor connectProcessor = new ConnectProcessor(this);
            RequestProcessor disconnectProcessor = new DisconnectProcessor(this);
            RequestProcessor pingProcessor = new PingProcessor();
            RequestProcessor publishProcessor = new PublishProcessor(this);
            RequestProcessor pubRelProcessor = new PubRelProcessor(this);
            RequestProcessor subscribeProcessor = new SubscribeProcessor(this);
            RequestProcessor unSubscribeProcessor = new UnSubscribeProcessor(subscriptionMatcher, subscriptionStore);
            RequestProcessor pubRecProcessor = new PubRecProcessor(flowMessageStore);
            RequestProcessor pubAckProcessor = new PubAckProcessor(flowMessageStore);
            RequestProcessor pubCompProcessor = new PubCompProcessor(flowMessageStore);

        /*    
         * https://mcxiaoke.gitbooks.io/mqtt-cn/content/mqtt/0306-PUBREL.html
            3.0 Contents – MQTT控制报文
            3.1 CONNECT – 连接服务端
            3.2 CONNACK – 确认连接请求
            3.3 PUBLISH – 发布消息
            3.4 PUBACK –发布确认
            3.5 PUBREC – 发布收到（QoS 2，第一步）
            3.6 PUBREL – 发布释放（QoS 2，第二步）
            3.7 PUBCOMP – 发布完成（QoS 2，第三步）
            3.8 SUBSCRIBE - 订阅主题
            3.9 SUBACK – 订阅确认
            3.10 UNSUBSCRIBE –取消订阅
            3.11 UNSUBACK – 取消订阅确认
            3.12 PINGREQ – 心跳请求
            3.13 PINGRESP – 心跳响应
            3.14 DISCONNECT –断开连接
*/            
            this.remotingServer.registerProcessor(MqttMessageType.CONNECT, connectProcessor, connectExecutor);
            this.remotingServer.registerProcessor(MqttMessageType.DISCONNECT, disconnectProcessor, connectExecutor);
            this.remotingServer.registerProcessor(MqttMessageType.PINGREQ, pingProcessor, pingExecutor);
            this.remotingServer.registerProcessor(MqttMessageType.PUBLISH, publishProcessor, pubExecutor);
            this.remotingServer.registerProcessor(MqttMessageType.PUBACK, pubAckProcessor, pubExecutor);
            this.remotingServer.registerProcessor(MqttMessageType.PUBREL, pubRelProcessor, pubExecutor);
            this.remotingServer.registerProcessor(MqttMessageType.SUBSCRIBE, subscribeProcessor, subExecutor);
            this.remotingServer.registerProcessor(MqttMessageType.UNSUBSCRIBE, unSubscribeProcessor, subExecutor);
            this.remotingServer.registerProcessor(MqttMessageType.PUBREC, pubRecProcessor, subExecutor);
            this.remotingServer.registerProcessor(MqttMessageType.PUBCOMP, pubCompProcessor, subExecutor);
        }

        {//init and register cluster processor
            ClusterRequestProcessor fetchNodeProcessor = new FetchNodeProcessor();
            this.clusterServer.registerClusterProcessor(ClusterRequestCode.FETCH_NODES,fetchNodeProcessor,clusterService);
            
           
            //zj web-cluster
            ClusterRequestProcessor webrequestProcessor=new WebSRequestProcessor(websrequestListener);
            this.clusterServer.registerClusterProcessor(ClusterRequestCode.NOTICE_RULE_CHAGNEND,webrequestProcessor,clusterService);
            this.clusterServer.registerClusterProcessor(ClusterRequestCode.NOTICE_OBJ_MODEL_CHAGNEND,webrequestProcessor,clusterService);
            
        }
        this.innerMessageTransfer.init();
        this.clusterClient.start();
        this.clusterServer.start();
        this.clusterOuterAPI.start();
        this.messageDispatcher.start();
        this.reSendMessageService.start();
        this.remotingServer.start();
        
        this.ruleEngin.start();
        this.webRemotingServer.start();
        this.iotEngin.start();
        log.info("JMqtt Server start success and version = {}", brokerConfig.getVersion());
    }

    public void shutdown() {
        this.remotingServer.shutdown();
        this.clusterOuterAPI.shutdown();
        this.clusterClient.shutdown();
        this.clusterServer.shutdown();
        this.connectExecutor.shutdown();
        this.pubExecutor.shutdown();
        this.subExecutor.shutdown();
        this.pingExecutor.shutdown();
        this.messageDispatcher.shutdown();
        this.reSendMessageService.shutdown();
        this.abstractMqttStore.shutdown();
        
        this.ruleEngin.shutdown();
        this.webRemotingServer.shutdown();
        this.iotEngin.shutdown();
    }

    public BrokerConfig getBrokerConfig() {
        return brokerConfig;
    }

    public NettyConfig getNettyConfig() {
        return nettyConfig;
    }

    public ExecutorService getConnectExecutor() {
        return connectExecutor;
    }

    public ExecutorService getPubExecutor() {
        return pubExecutor;
    }

    public ExecutorService getSubExecutor() {
        return subExecutor;
    }

    public ExecutorService getPingExecutor() {
        return pingExecutor;
    }

    public LinkedBlockingQueue getConnectQueue() {
        return connectQueue;
    }

    public LinkedBlockingQueue getPubQueue() {
        return pubQueue;
    }

    public LinkedBlockingQueue getSubQueue() {
        return subQueue;
    }

    public LinkedBlockingQueue getPingQueue() {
        return pingQueue;
    }

    public NettyRemotingServer getRemotingServer() {
        return remotingServer;
    }

    public MessageDispatcher getMessageDispatcher() {
        return messageDispatcher;
    }

    public FlowMessageStore getFlowMessageStore() {
        return flowMessageStore;
    }

    public SubscriptionMatcher getSubscriptionMatcher() {
        return subscriptionMatcher;
    }

    public WillMessageStore getWillMessageStore() {
        return willMessageStore;
    }

    public RetainMessageStore getRetainMessageStore() {
        return retainMessageStore;
    }

    public OfflineMessageStore getOfflineMessageStore() {
        return offlineMessageStore;
    }

    public SubscriptionStore getSubscriptionStore() {
        return subscriptionStore;
    }

    public SessionStore getSessionStore() {
        return sessionStore;
    }

    public ConnectPermission getConnectPermission() {
        return connectPermission;
    }

    public PubSubPermission getPubSubPermission() {
        return pubSubPermission;
    }

    public ReSendMessageService getReSendMessageService() {
        return reSendMessageService;
    }

    public StoreConfig getStoreConfig() {
        return storeConfig;
    }

    public ClusterConfig getClusterConfig() {
        return clusterConfig;
    }

    public ChannelEventListener getChannelEventListener() {
        return channelEventListener;
    }

    public AbstractMqttStore getAbstractMqttStore() {
        return abstractMqttStore;
    }

    public ClusterRemotingClient getClusterClient() {
        return clusterClient;
    }

    public ClusterRemotingServer getClusterServer() {
        return clusterServer;
    }

    public ClusterOuterAPI getClusterOuterAPI() {
        return clusterOuterAPI;
    }

    public InnerMessageTransfer getInnerMessageTransfer() {
        return innerMessageTransfer;
    }

    public ExecutorService getClusterService() {
        return clusterService;
    }


	public RuleEngin getRuleEngin() {
		return ruleEngin;
	}


	public void setRuleEngin(RuleEngin ruleEngin) {
		this.ruleEngin = ruleEngin;
	}


	public IotObjectEngin getIotEngin() {
		return iotEngin;
	}


	public void setIotEngin(IotObjectEngin iotEngin) {
		this.iotEngin = iotEngin;
	}
}
