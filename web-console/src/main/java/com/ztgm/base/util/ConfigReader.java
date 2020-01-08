package com.ztgm.base.util;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件数据放入静态配置中
 * @author zj
 * @date 2018年6月28日
 *
 */
@Component
@PropertySource("classpath:project.properties")
public class ConfigReader {

	private final Logger logger = LoggerFactory.getLogger(ConfigReader.class);

	public static String httppath;

	@Autowired
	public void setHttppath(@Value("${HTTP_PATH}") String path) {
		httppath = path;
	}
}