/**
 * @(#)MongoGeoDao.java  2018-11-14
 *
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.controller.mongo;



import com.mongodb.DBObject;


import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * Created by Think on 2018/3/16.
 */
public interface MongoGeoDao {

	
	
	/**
	 * dbinfo
	 * @return
	 * @author zj
	 * @date 2019年2月11日
	 */
	public String getDbInfo();
	
    /**
     * 删除普通图片
     * @param id
     * @author zj
     * @date 2018年10月12日
     */
    public void deleteNormalImg(String id) ;
	
	
	
    /**
     * 普通通用图片
     * @param NormalImg2Mongo
     * @author zj
     * @date 2018年9月3日
     */
    public void saveOrUpdateNormalImg(NormalImg2Mongo  normalImg2Mongo);

    
   
    /**
     * 普通通用图片
     * @param NormalImg2Mongo
     * @author zj
     * @date 2018年9月3日
     */
    public NormalImg2Mongo getNormalImg(String id);
    
    
	
	
}
