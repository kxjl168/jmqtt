package com.ztgm.base.util;

/**
 * 
 * TableNameUtil.java. 表名称 pojo名称转化类<br>
 * 要求表名称及字段一律使用小写字符，下划线分割的命名方式. 生成的Pojo使用驼峰模式
 * 
 * @author zj
 * @version 1.0.1 2019年1月8日
 * @revision zj 2019年1月8日
 * @since 1.0.1
 */
public class TableNameUtil {

	/**
	 * 根据表名称返回符合规则的Pojo名称 ， 以下划线为分割，首字母大写驼峰
	 * 
	 * @param tableName
	 * @return
	 * @author zj
	 * @date 2019年1月8日
	 */
	public static String getTFName(String tableName) {
		String[] names = tableName.split("_");
		String newName="";
		for (int i = 0; i < names.length; i++) {
			if(!names[i].trim().equals(""))
			{
				newName+= names[i].substring(0,1).toUpperCase()+names[i].substring(1, names[i].length());
			}
		}
		return newName;
	}
	
	/**
	 * 根据表名称返回符合规则的requestMapping名称,<br>
	 * 
	 * requestmapping,
	 * js
	 * 目录均与此同
	 * 
	 * 
	 * @param tableName
	 * @return
	 * @author zj
	 * @date 2019年1月8日
	 */
	public static String getCtrollerMappingName(String tableName) {
		return  tableName.replace("_","").toLowerCase();
		
	}

	

	/**
	 * 根据表名称返回符合规则的Pojo名称   以下划线为分割， 首字母小写驼峰
	 * 
	 * @param tableName
	 * @return
	 * @author zj
	 * @date 2019年1月8日
	 */
	public static String getFirstLowCaseTFName(String attrName) {
		String[] names = attrName.split("_");
		String newName="";
		for (int i = 0; i < names.length; i++) {
			if(!names[i].trim().equals(""))
			{
				if(!newName.equals("")) //后续大写
				newName+= names[i].substring(0,1).toUpperCase()+names[i].substring(1, names[i].length());
				else //第一个小写
					newName+= names[i].substring(0,1).toLowerCase()+names[i].substring(1, names[i].length());
			}
		}
		return newName;
	}
}
