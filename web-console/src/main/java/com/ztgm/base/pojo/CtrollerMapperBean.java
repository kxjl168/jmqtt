package com.ztgm.base.pojo;

import java.util.ArrayList;
import java.util.List;

import com.ztgm.base.util.DateUtil;
import com.ztgm.base.util.TableNameUtil;

/**
 * ctroller/service 生成模板 配置参数
 * 
 * @author zj
 * @version 1.0.1 2019年1月4日
 * @revision zj 2019年1月4日
 * @since 1.0.1
 */
public class CtrollerMapperBean {

	private String author = "KAutoGenerator";

	// pojo ,dao
	private String generatedate = DateUtil.getNowStr("");

	private String generatorFileName;// 生成的mybatis generatorcfg.xml 文件名称

	private String tableName;// 原始表名称

	private String idType = "2";// 主键id的字段类型：默认varchar, 1:int ,2:varchar;

	private String useActualColumnNames = "true";// true针对表字段为驼峰模式,false 针对表字段为下划线分割
	
	private String tableNameStartFromSecondUnderLine = "true";// true针对表名称, 生成的pojo从第二个下划线开始  t_xx_yy ==> XxYy，
	
	private String daosubfold="";//生成的dao是否指定子目录，比如 dao.normal/ dao.plus等

	private String jarPath;// jar包路径

	// controller,service
	private String basepackageName;// 生成的controller 基础包名 eg: com.ztgm.demo

	private String modelClass; // pojo名称 eg: Community

	private String ctrollerModelMapping;// mapping路径 ， eg: community, modelClass首字母小写

	private String logName;// pojo中文名称 eg: 小区 ，页面title等公用

	// 页面字段
	private String pageType;// 页面类型 jsp,ftl

	private String pagetitle;// 页面title eg: 系统权限，不带管理二字. 基本与logName相同

	private List<AField> queryFields = new ArrayList<>(); // 页面查询条件 暂时只有一个条件，名称

	private List<AField> colFields = new ArrayList<>(); // 页面table显示的字段

	public String getJarPath() {
		return jarPath;
	}

	public void setJarPath(String jarPath) {
		this.jarPath = jarPath;
	}

	public String getBasepackageName() {
		return basepackageName;
	}

	public void setBasepackageName(String basepackageName) {
		this.basepackageName = basepackageName;
	}

	public String getModelClass() {
		return modelClass;
	}

	public void setModelClass(String modelClass) {
		this.modelClass = modelClass;
	}

	public String getCtrollerModelMapping() {
		return ctrollerModelMapping;
	}

	public void setCtrollerModelMapping(String ctrollerModelMapping) {
		this.ctrollerModelMapping = ctrollerModelMapping;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getGeneratedate() {
		return generatedate;
	}

	public void setGeneratedate(String generatedate) {
		this.generatedate = generatedate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGeneratorFileName() {
		return generatorFileName;
	}

	public void setGeneratorFileName(String generatorFileName) {
		this.generatorFileName = generatorFileName;
	}

	public String getTableName() {
		return tableName;
	}

	/**
	 * 基础配置，表名称，modelname,requestMapping,均通过数据库表名称来确定
	 * 
	 * @param tableName
	 * @author zj
	 * @date 2019年1月10日
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;

		// 设置符合规则的Pojo及cotroller mapping名称
		//从第一个下划线后开始表名称
		if(tableNameStartFromSecondUnderLine.equals("true"))
			this.modelClass = TableNameUtil.getTFName(tableName.substring(tableName.indexOf("_")+1, tableName.length()));
		else
		this.modelClass = TableNameUtil.getTFName(tableName);
		this.ctrollerModelMapping = TableNameUtil.getCtrollerMappingName(tableName);
		

	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getPagetitle() {
		return pagetitle;
	}

	public void setPagetitle(String pagetitle) {
		this.pagetitle = pagetitle;
	}

	public List<AField> getQueryFields() {
		return queryFields;
	}

	public void setQueryFields(List<AField> queryFields) {
		this.queryFields = queryFields;
	}

	public List<AField> getColFields() {
		return colFields;
	}

	public void setColFields(List<AField> colFields) {
		this.colFields = colFields;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getUseActualColumnNames() {
		return useActualColumnNames;
	}

	public void setUseActualColumnNames(String useActualColumnNames) {
		this.useActualColumnNames = useActualColumnNames;
	}

	public String getTableNameStartFromSecondUnderLine() {
		return tableNameStartFromSecondUnderLine;
	}

	public void setTableNameStartFromSecondUnderLine(String tableNameStartFromSecondUnderLine) {
		this.tableNameStartFromSecondUnderLine = tableNameStartFromSecondUnderLine;
	}

	public String getDaosubfold() {
		return daosubfold;
	}

	public void setDaosubfold(String daosubfold) {
		this.daosubfold = daosubfold;
	}

}
