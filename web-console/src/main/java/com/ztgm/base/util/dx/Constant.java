/*
 * @(#)Constant.java
 * @author: chenenqiang
 * @Date: 2018/11/9 16:27
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.util.dx;

public class Constant {

    /**
     * 当你使用演示时，请替换IP和端口。
     */
    public static final String BASE_URL = "https://180.101.147.89:8743";

    /**
     * 当你使用演示时，请替换APIID和秘密。
     */
    public static final String APPID = "Y9X0ZkDZh2kNhoVmUqGnCf2qfJQa";
    public static final String SECRET = "eeN2pGYcTNqpJ4Y4DAbluR1jID8a";

    /**
     * IP和回调URL端口。
     */
    public static final String CALLBACK_BASE_URL = "http://58.213.74.62:7443/lock/notify";


    /**
     * 证书路径。
     */
    public static String SELFCERTPATH = "/outgoing.CertwithKey.pkcs12";
    public static String TRUSTCAPATH = "/ca.jks";

    /**
     * 证书密码。
     */
    public static String SELFCERTPWD = "IoM@1234";
    public static String TRUSTCAPWD = "Huawei@123";


    //*************************** 不需要修改下列常量 *********************************//

    /**
     * request header
     * 1. HEADER_APP_KEY
     * 2. HEADER_APP_AUTH
     */
    public static final String HEADER_APP_KEY = "app_key";
    public static final String HEADER_APP_AUTH = "Authorization";

    /**
     * Application Access Security:
     * 1. APP_AUTH
     * 2. REFRESH_TOKEN
     */
    public static final String APP_AUTH = BASE_URL + "/iocm/app/sec/v1.1.0/login";
    public static final String REFRESH_TOKEN = BASE_URL + "/iocm/app/sec/v1.1.0/refreshToken";


    /**
     * Device Management:
     * 1. REGISTER_DEVICE
     * 2. MODIFY_DEVICE_INFO
     * 3. QUERY_DEVICE_ACTIVATION_STATUS
     * 4. DELETE_DEVICE
     * 5. DISCOVER_INDIRECT_DEVICE
     * 6. REMOVE_INDIRECT_DEVICE
     */
    public static final String REGISTER_DEVICE = BASE_URL + "/iocm/app/reg/v1.1.0/devices";
    public static final String MODIFY_DEVICE_INFO = BASE_URL + "/iocm/app/dm/v1.4.0/devices";
    public static final String QUERY_DEVICE_ACTIVATION_STATUS = BASE_URL + "/iocm/app/reg/v1.1.0/devices";
    public static final String DELETE_DEVICE = BASE_URL + "/iocm/app/dm/v1.4.0/devices";
    public static final String DISCOVER_INDIRECT_DEVICE = BASE_URL + "/iocm/app/signaltrans/v1.1.0/devices/%s/services/%s/sendCommand";
    public static final String REMOVE_INDIRECT_DEVICE = BASE_URL + "/iocm/app/signaltrans/v1.1.0/devices/%s/services/%s/sendCommand";

    /**
     * Data Collection:
     * 1. QUERY_DEVICES
     * 2. QUERY_DEVICE_DATA
     * 3. QUERY_DEVICE_HISTORY_DATA
     * 4. QUERY_DEVICE_CAPABILITIES
     * 5. SUBSCRIBE_NOTIFYCATION
     */
    public static final String QUERY_DEVICES = BASE_URL + "/iocm/app/dm/v1.3.0/devices";
    public static final String QUERY_DEVICE_DATA = BASE_URL + "/iocm/app/dm/v1.3.0/devices";
    public static final String QUERY_DEVICE_HISTORY_DATA = BASE_URL + "/iocm/app/data/v1.1.0/deviceDataHistory";
    public static final String QUERY_DEVICE_CAPABILITIES = BASE_URL + "/iocm/app/data/v1.1.0/deviceCapabilities";
    public static final String SUBSCRIBE_NOTIFYCATION = BASE_URL + "/iocm/app/sub/v1.1.0/subscribe";


    /**
     * Signaling Delivery：
     * 1. POST_ASYN_CMD
     * 2. QUERY_DEVICE_CMD
     * 3. UPDATE_ASYN_COMMAND
     * 4. CREATE_DEVICECMD_CANCEL_TASK
     * 5. QUERY_DEVICECMD_CANCEL_TASK
     */
    public static final String POST_ASYN_CMD = BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommands";
    public static final String QUERY_DEVICE_CMD = BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommands";
    public static final String UPDATE_ASYN_COMMAND = BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommands/%s";
    public static final String CREATE_DEVICECMD_CANCEL_TASK = BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";
    public static final String QUERY_DEVICECMD_CANCEL_TASK = BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";


    /**
     * notify Type
     * serviceInfoChanged|deviceInfoChanged|LocationChanged|deviceDataChanged|deviceDatasChanged
     * deviceAdded|deviceDeleted|messageConfirm|commandRsp|deviceEvent|ruleEvent
     */
    public static final String DEVICE_ADDED = "deviceAdded";
    public static final String DEVICE_INFO_CHANGED = "deviceInfoChanged";
    public static final String DEVICE_DATA_CHANGED = "deviceDataChanged";
    public static final String DEVICE_DELETED = "deviceDeleted";
    public static final String MESSAGE_CONFIRM = "messageConfirm";
    public static final String SERVICE_INFO_CHANGED = "serviceInfoChanged";
    public static final String COMMAND_RSP = "commandRsp";
    public static final String DEVICE_EVENT = "deviceEvent";
    public static final String RULE_EVENT = "ruleEvent";
    public static final String DEVICE_DATAS_CHANGED = "deviceDatasChanged";

}
