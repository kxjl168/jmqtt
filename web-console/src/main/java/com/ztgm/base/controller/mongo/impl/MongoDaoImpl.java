/**
 * @(#)MongoDaoImpl.java  2018-11-14
 *
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.controller.mongo.impl;

import com.mongodb.*;
import com.ztgm.base.controller.mongo.MongoGeoDao;
import com.ztgm.base.controller.mongo.NormalImg2Mongo;
import com.ztgm.base.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author zj
 * @date 2018年9月6日
 *
 */
@Service("mongoGeoDao")
public class MongoDaoImpl implements MongoGeoDao {

	@Autowired
	private MongoTemplate mongoTemplate;
	private int distance = 5000;// Integer.valueOf(PropertiesUtil.getProperty("distance"));

	private DBCollection dbCollection = null;

	/**
	 * dbinfo
	 * @return
	 * @author zj
	 * @date 2019年2月11日
	 */
	public String getDbInfo() {
		String name = "";

		try {
			name = mongoTemplate.getDb().getName();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return name;
	}

	/**
	 * 普通通用图片
	 * 
	 * @param NormalImg2Mongo
	 * @author zj
	 * @date 2018年9月3日
	 */
	public void saveOrUpdateNormalImg(NormalImg2Mongo normalImg2Mongo) {
		Query query = new Query(Criteria.where("_id").is(normalImg2Mongo.getId()));
		Update update = new Update();
		update.set("id", normalImg2Mongo.getId());
		update.set("uId", normalImg2Mongo.getUId());

		if (normalImg2Mongo.getImg_file() != null) {
			update.set("img_file", normalImg2Mongo.getImg_file());
			update.set("img_file_uptime", DateUtil.getNowStr(""));
		}

		this.mongoTemplate.upsert(query, update, "account_normal_img");
	}

	/**
	 * 普通通用图片
	 * 
	 * @param NormalImg2Mongo
	 * @author zj
	 * @date 2018年9月3日
	 */
	public NormalImg2Mongo getNormalImg(String id) {
		try {
			Query query = new Query(Criteria.where("_id").is(id));
			NormalImg2Mongo car = mongoTemplate.findOne(query, NormalImg2Mongo.class, "account_normal_img");
			return car;
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 删除普通图片
	 * 
	 * @param id
	 * @author zj
	 * @date 2018年10月12日
	 */
	@Override
	public void deleteNormalImg(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		mongoTemplate.remove(query, NormalImg2Mongo.class, "account_normal_img");
	}

}
