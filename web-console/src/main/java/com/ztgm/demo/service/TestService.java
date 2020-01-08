/*
 * @(#)CommunityService.java
 * @author: zhangJ
 * @Date: 2018/12/11 09:02
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.demo.service;



import net.sf.json.JSONObject;

import java.util.List;

import com.ztgm.demo.pojo.Test;

/**
 * 小区
 * CommunityService.java.
 * 
 * @author zj
* @version 1.0.1 2018年12月11日
* @revision zj 2018年12月11日
* @since 1.0.1
 */
public interface TestService {


    /**
     * 新增
     */
    JSONObject saveTest(Test query);

    /**
     * 更新信息
     */
    JSONObject updateTest(Test query);


    List<Test> selectTestList(Test query);

    int deleteTest(Test query);
    
    Test selectTestById(String id);




}
