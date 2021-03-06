package org.jmqtt.store;



public abstract class AbstractMqttStore {

    protected FlowMessageStore flowMessageStore;
    protected WillMessageStore willMessageStore;
    protected RetainMessageStore retainMessageStore;
    protected OfflineMessageStore offlineMessageStore;
    protected SubscriptionStore subscriptionStore;
    protected SessionStore sessionStore;
    
    protected RuleMessageStore ruleMessageStore;
    
    protected ObjectModelMessageStore iotObjectModelMessageStore;

    public abstract void init() throws Exception;

    public abstract void shutdown();

    public FlowMessageStore getFlowMessageStore() {
        return flowMessageStore;
    }

    public OfflineMessageStore getOfflineMessageStore() {
        return offlineMessageStore;
    }

    public RetainMessageStore getRetainMessageStore() {
        return retainMessageStore;
    }

    public SessionStore getSessionStore() {
        return sessionStore;
    }

    public SubscriptionStore getSubscriptionStore() {
        return subscriptionStore;
    }

    public WillMessageStore getWillMessageStore() {
        return willMessageStore;
    }
    
    public RuleMessageStore getRuleMessageStore() {
        return ruleMessageStore;
    }

	public ObjectModelMessageStore getIotObjectModelMessageStore() {
		return iotObjectModelMessageStore;
	}



}
