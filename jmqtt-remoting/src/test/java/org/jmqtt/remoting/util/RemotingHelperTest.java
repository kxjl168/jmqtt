package org.jmqtt.remoting.util;

import org.junit.Test;

import static org.junit.Assert.*;

import org.jmqtt.common.helper.RemotingHelper;

public class RemotingHelperTest {

    @Test
    public void getLocalAddr() {
        String currentIp = RemotingHelper.getLocalAddr();
        assert currentIp != null;
    }
}