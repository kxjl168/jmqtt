package com.ztgm.base.mybatisMain;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

public class MybatisGenerator {

	public static void main(String[] args) {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;// 如果已经生成过了是否进行覆盖
		//String genCfg = "/generator/generatorConfig.xml";// 配置文件的路径:默认放到src下面
		
		
		String genCfg = "/generator/mybatisgenerator-temp.xml";// 配置文件的路径:默认放到src下面
		InputStream fs = MybatisGenerator.class.getResourceAsStream(genCfg);

		// String file =
		// "F:\\kxjl\\code\\LegendShop3.0.6黄金版源代码\\LegendShop3.0.6黄金版源代码\\legendshop_demo\\src\\resource\\generatorConfig.xml";
		// String file = url.getFile();
		// File configFile = new File(file);

		// File configFile =File.
		
		ConfigurationParser cfgParser = new ConfigurationParser(warnings);// 配置文件解析器
		Configuration config = null;
		try {
			config = cfgParser.parseConfiguration(fs);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLParserException e) {
			e.printStackTrace();
		}
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator generator = null;
		try {
			generator = new MyBatisGenerator(config, callback, warnings);
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
		try {
			generator.generate(null);
			System.out.println("mybatis 代码生成成功。。。");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
