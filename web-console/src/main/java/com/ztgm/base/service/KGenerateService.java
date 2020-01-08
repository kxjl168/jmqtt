/*
 * @(#)RoomService.java
 * @author: zhangJ
 * @Date: 2018/12/11 09:02
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.service;


import net.sf.json.JSONObject;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ztgm.base.pojo.CtrollerMapperBean;
import com.ztgm.base.pojo.SysOperLog;
import com.ztgm.base.util.Message;


/**
 * 生成controller,service
 * GenerateService.java.
 * 
 * @author zj
* @version 1.0.1 2019年1月4日
* @revision zj 2019年1月4日
* @since 1.0.1
 */
public interface KGenerateService {



   /**
    * 生成默认菜单并分配给admin用户
    * @param item
    * @return
    * @author zj
    * @date 2019年1月9日
    */
    Message generateMenuAndAsgin(HttpServletRequest request,CtrollerMapperBean item);
	
    /**
     * 复制生成controller模板
     * @param item
     * @return
     * @author zj
     * @date 2019年1月4日
     */
    Message generateController(CtrollerMapperBean item);
    
    /**
     * 复制生成页面
     * @param item
     * @return
     * @author zj
     * @date 2019年1月4日
     */
    Message generatePageAndJs(CtrollerMapperBean item);
    
    /**
     * 复制生成controller模板
     * @param item
     * @return
     * @author zj
     * @date 2019年1月4日
     */
    Message generateService(CtrollerMapperBean item);
    
    /**
     * 自定义修改 mybatis generator生成基础的pojo,bean， <br>
     * 增加 基础条件查询等   List<LockCommunity> selectList(LockCommunity item);
     * @param item
     * @return
     * @author zj
     * @date 2019年1月4日
     */
    Message UpdatePojoDao(CtrollerMapperBean item);
    

    /**
     * mybatis generator生成基础pojo,bean
     * @param item
     * @return
     * @author zj
     * @date 2019年1月4日
     */
    Message generatePojoDao(CtrollerMapperBean item);


    
    

}
