/**
 * @(#)BaseModel.java  2018-11-27
 *
 * Copyright (C),2017-2018, ZHONGTONGGUOMAI TECHNOLOGY NANJING
 * Co.,Ltd. All Rights Reserved.
 * GMWL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ztgm.base.pojo.base;

/**
 * ibatis 分页查询参数
 * 
 * @date 2016-3-7
 * @author zj
 *
 */
public class BaseModel {
	/*
	 * 
	 * @date 2016-3-7
	 * 
	 * @author zj
	 */
	protected int pageCount = 10;// 每页显示的记录数
	protected int page = 1;// 第几页

	protected int start = 0;// limit 查询开始数

	protected int totleRecord = 0;// 总记录数

	// 排序字段
	private String sortName;
	private String sortOrder;

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
		start = (page - 1) * pageCount;
	}

	public int getTotleRecord() {
		return totleRecord;
	}

	public void setTotleRecord(int totleRecord) {
		this.totleRecord = totleRecord;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;

		start = (page - 1) * pageCount;
	}

}
