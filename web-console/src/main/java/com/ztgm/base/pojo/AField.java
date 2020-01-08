package com.ztgm.base.pojo;

import com.ztgm.base.util.TableNameUtil;

/**
 * 字段映射
 * AField.java.
 * 
 * @author zj
* @version 1.0.1 2019年1月4日
* @revision zj 2019年1月4日
* @since 1.0.1
 */
public class AField {

	
	private String id;// input id字段，js查询字段  对应pojo字段，驼峰模式，TableNameUtil.getAttrName 统一生成
	
	private String sqlcolname;// input id字段对应的数据库字段
	
	private String displayName;// 显示中文名称 eg: 提示中文
	
	private String colType;//timestamp  原始数据库字段
	
	private String type="input";//页面查询控件类型， input ,select
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public AField() {
		
	}
	
	public AField(String colname,String name) {
		
		this.displayName=name;
		this.sqlcolname=colname;
		this.id= TableNameUtil.getFirstLowCaseTFName(colname);//id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSqlcolname() {
		return sqlcolname;
	}

	public void setSqlcolname(String sqlcolname) {
		this.sqlcolname = sqlcolname;
		
		this.id= TableNameUtil.getFirstLowCaseTFName(sqlcolname);//id;
	}

	public String getColType() {
		return colType;
	}

	public void setColType(String colType) {
		this.colType = colType;
	}
}
