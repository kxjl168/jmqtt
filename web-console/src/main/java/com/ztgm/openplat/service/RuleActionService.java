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

import com.ztgm.openplat.pojo.RuleAction;

/**
 * 小区
 * CommunityService.java.
 * 
 * @author zj
* @version 1.0.1 2018年12月11日
* @revision zj 2018年12月11日
* @since 1.0.1
 */
public interface RuleActionService {


    /**
     * 新增
     */
    JSONObject saveRuleAction(RuleAction query);

    /**
     * 更新信息
     */
    JSONObject updateRuleAction(RuleAction query);


    List<RuleAction> selectRuleActionList(RuleAction query);

    int deleteRuleAction(RuleAction query);
    
    RuleAction selectRuleActionById(String id);




}
