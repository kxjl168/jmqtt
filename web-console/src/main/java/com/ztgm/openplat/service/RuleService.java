/*
 * @(#)CommunityService.java
 * @author: zhangJ
 * @Date: 2018/12/11 09:02
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.openplat.service;



import net.sf.json.JSONObject;

import java.util.List;

import com.ztgm.base.util.AppResult;
import com.ztgm.openplat.pojo.Rule;

/**
 * 小区
 * CommunityService.java.
 * 
 * @author zj
* @version 1.0.1 2018年12月11日
* @revision zj 2018年12月11日
* @since 1.0.1
 */
public interface RuleService {


    /**
     * 新增
     */
    JSONObject saveRule(Rule query);

    /**
     * 更新信息
     */
    JSONObject updateRule(Rule query);


    List<Rule> selectRuleList(Rule query);

    int deleteRule(Rule query);
    
    Rule selectRuleById(String id);
    
    
    /**
     * 启动规则，通知jmqtt，然后更新本地状态
     * @param rule
     * @return
     * @author zj
     * @date 2020年1月10日
     */
    AppResult startRule(Rule rule);
    
    
    /**
     * 停止规则，通知jmqtt，然后跟新本地状态
     * @param rule
     * @return
     * @author zj
     * @date 2020年1月10日
     */
    AppResult stopRule(Rule rule);

    
    /**
     * 根据productKey通知jmqtt规则变化
     * @param productKey
     * @return
     * @author zj
     * @date 2020年1月10日
     */
    AppResult notifyMqttRuleChanged(String productKey);



}
