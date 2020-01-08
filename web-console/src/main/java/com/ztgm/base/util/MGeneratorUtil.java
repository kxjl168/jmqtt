package com.ztgm.base.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ztgm.base.service.impl.KGenerateServiceImpl;
import com.ztgm.base.pojo.CtrollerMapperBean;

/**
 * mybatis 生成模板相关 MGeneratorUtil.java.
 * 
 * @author zj
 * @version 1.0.1 2019年1月7日
 * @revision zj 2019年1月7日
 * @since 1.0.1
 */
@Component
public class MGeneratorUtil {
	private static Logger log = LoggerFactory.getLogger(MGeneratorUtil.class);
	@Autowired
	private  FreeMarkUtil freeMarkUtil;

	// 模板文件目录
	@Value("${kauto.templatePath}")
	private String templatePath;


	/**
	 * 构建xml，调用mybatis generator 生成
	 * 
	 * @param cfgfileName
	 * @param data
	 * @throws Exception
	 * @author zj
	 * @date 2019年1月7日
	 */
	public void generatoMybatisPojoMapper(String cfgfileName, CtrollerMapperBean data) throws Exception {

		try {


			buildCfgXml(cfgfileName, data);

			// 先备份xml，重复生成xml内容会叠加
			String path = KGenerateServiceImpl.class.getResource("/").getPath();
			path += "../../src/main/resources/mappers/";
			TemplateFileUtil.BackUpFile(data.getModelClass() + "Mapper.xml", path);
			
			//清空xml,填入空xml，防止mybatis代码 xml解析异常
			TemplateFileUtil.SaveFile(data.getModelClass() + "Mapper.xml", path,"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + 
					"<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\r\n" + 
					"<mapper>\r\n" + 
					"  \r\n" + 
					"</mapper>");
			
			String pathmapper = KGenerateServiceImpl.class.getResource("/").getPath();
			pathmapper += "../../src/main/java/" + data.getBasepackageName().replace(".", "/") + File.separator + "dao";
			//清空dao内容，防止附加的内容重复
			TemplateFileUtil.SaveFile(data.getModelClass() + "Mapper.java", pathmapper,"");

			myBatisGenerate(cfgfileName);

		} catch (Exception e) {
			log.error("generatoMybatisPojoMapper error", e);
			throw e;
		}

	}

	/**
	 * 调用mybatis生成pojo mapper，
	 * 
	 * @param cfgfileName
	 *            generatorcfg.xml名称
	 * @author zj
	 * @date 2019年1月7日
	 */
	private static void myBatisGenerate(String cfgfileName) throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;// 如果已经生成过了是否进行覆盖

		String path = KGenerateServiceImpl.class.getResource("/").getPath();
		path += "generator/" + cfgfileName;
		File cfgFile = new File(path);

		ConfigurationParser cfgParser = new ConfigurationParser(warnings);// 配置文件解析器
		Configuration config = null;
		try {
			config = cfgParser.parseConfiguration(cfgFile);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLParserException e) {
			e.printStackTrace();
		}
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator generator = null;

		generator = new MyBatisGenerator(config, callback, warnings);

		generator.generate(null);

	}

	/**
	 * 根据参数生成mybatis genertor.xml配置文件
	 * 
	 * @param outputname
	 *            输出xml文件名称
	 * @param data
	 * @author zj
	 * @date 2019年1月7日
	 */
/*
	private static void buildCfgXml(String outputname, CtrollerMapperBean data) throws Exception {

		String change = "  \t<table tableName=\"" + data.getTableName() + "\" domainObjectName=\""
				+ GeneratorUtils.change(data.getModelClass())
				+ "\" enableCountByExample=\"false\" enableUpdateByExample=\"false\" enableDeleteByExample=\"false\" enableSelectByExample=\"false\" selectByExampleQueryId=\"false\"/>\n";

		Pattern pattern = Pattern.compile("\t<table tableName=", Pattern.CASE_INSENSITIVE); // 要匹配的字段内容，正则表达式
		Matcher matcher = pattern.matcher("");

		String path = KGenerateServiceImpl.class.getResource("/").getPath();
		path += "../../src/main/resources/generator/";
		BufferedReader reader = null;
		try {

			String cfgPath = path + "generatorConfig.xml";
			File fileTemplate = new File(cfgPath);
			// List<String> lines = Files.readAllLines(Paths.get(cfgPath)); // 读取文本文件

			StringBuffer sbf = new StringBuffer();

			FileReader fr = new FileReader(fileTemplate);
			reader = new BufferedReader(fr);
			String tempString = "";
			while ((tempString = reader.readLine()) != null) {

				matcher.reset(tempString);
				if (matcher.find()) { // 匹配正则表达式

					sbf.append(change);
				} else
					sbf.append(tempString);

			}

			String saveFileName = outputname;// "generatorConfig-temp.xml";
			TemplateFileUtil.SaveFile(saveFileName, path, sbf.toString());
		} catch (Exception e) {
			log.error("buildCfgXml error", e);
			throw e;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		}
		// return null;
	}
*/
	private  void buildCfgXml(String cfgfileName, CtrollerMapperBean data) throws Exception {
		String text =freeMarkUtil.getGeneratorContent(data);
		
		
		text=text.replaceAll("&","&amp;");
		
		String path = KGenerateServiceImpl.class.getResource("/").getPath();
		path += "generator/";
		TemplateFileUtil.SaveFile(cfgfileName, path, text);
	}

}
