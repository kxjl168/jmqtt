/*
 * @(#)CommunityService.java
 * @author: zhangJ
 * @Date: 2018/12/11 09:02
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.service;



import net.sf.json.JSONObject;

import java.util.List;

import com.ztgm.base.pojo.SysPlat;



/**
 * 小区
 * CommunityService.java.
 * 
 * @author zj
* @version 1.0.1 2018年12月11日
* @revision zj 2018年12月11日
* @since 1.0.1
 */
public interface SysPlatService {


    /**
     * 新增
     */
    JSONObject saveSysPlat(SysPlat query);

    /**
     * 更新信息
     */
    JSONObject updateSysPlat(SysPlat query);


    List<SysPlat> selectSysPlatList(SysPlat query);

    int deleteSysPlat(SysPlat query);
    
    SysPlat selectSysPlatById(String id);




}
