package org.jmqtt.iot.acl;

import org.jmqtt.broker.acl.PubSubPermission;

public class IOTPubSubPermission implements PubSubPermission {

    @Override
    public boolean publishVerfy(String clientId, String topic) {
        return true;
    }

    @Override
    public boolean subscribeVerfy(String clientId, String topic) {
        return true;
    }
}
