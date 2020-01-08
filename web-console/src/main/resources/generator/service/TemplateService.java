/*
 * @(#)CommunityService.java
 * @author: zhangJ
 * @Date: 2018/12/11 09:02
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package ${basepackageName}.service;



import net.sf.json.JSONObject;

import java.util.List;

import ${basepackageName}.pojo.${modelClass};

/**
 * 小区
 * CommunityService.java.
 * 
 * @author zj
* @version 1.0.1 2018年12月11日
* @revision zj 2018年12月11日
* @since 1.0.1
 */
public interface ${modelClass}Service {


    /**
     * 新增
     */
    JSONObject save${modelClass}(${modelClass} query);

    /**
     * 更新信息
     */
    JSONObject update${modelClass}(${modelClass} query);


    List<${modelClass}> select${modelClass}List(${modelClass} query);

    int delete${modelClass}(${modelClass} query);
    
    ${modelClass} select${modelClass}ById(String id);




}
