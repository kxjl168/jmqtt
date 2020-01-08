package com.ztgm.base.util;

import java.io.File;
import java.io.StringWriter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ztgm.base.service.impl.KGenerateServiceImpl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * 模板生成
 * FreeMarkUtil.java.
 * 
 * @author zj
* @version 1.0.1 2019年1月4日
* @revision zj 2019年1月4日
* @since 1.0.1
 */
@Component
public class FreeMarkUtil {

	// 模板文件目录
	@Value("${kauto.templatePath}")
	private String templatePath;

	
	/**
	 * @description 获取controller模板
	 */
	public String getControllerContent( Object data) throws Exception {

		return getContent("/controller/TemplateController.java",data);
	}

	/**
	 * @description 获取 Generator 模板
	 */
	public  String getGeneratorContent( Object data) throws Exception {

		return getContent("generatorConfig.xml",data);
	}
	
	/**
	 * @description 获取service 模板
	 */
	public String getServiceContent( Object data) throws Exception {

		return getContent("/service/TemplateService.java",data);
	}
	
	/**
	 * 基础service impl
	 * @param data
	 * @return
	 * @throws Exception
	 * @author zj
	 * @date 2019年1月7日
	 */
	public String getServiceImplContent( Object data) throws Exception {

		return getContent("/service/impl/TemplateServiceImpl.java",data);
	}
	
	
	/**
	 * dao 附加方法
	 * @param data
	 * @return
	 * @throws Exception
	 * @author zj
	 * @date 2019年1月7日
	 */
	public String getDaoPlusContent( Object data) throws Exception {

		return getContent("/dao/daoPlus.java",data);
	}
	
	/**
	 * mapper附加xml
	 * @param data
	 * @return
	 * @throws Exception
	 * @author zj
	 * @date 2019年1月7日
	 */
	public String getMapperPlusContent( Object data) throws Exception {

		return getContent("/dao/daoPlus.xml",data);
	}
	
	
	/**
	 * @description 获取主页模板
	 */
	public String getPageIndexContent( Object data) throws Exception {

		return getContent("/page/index.ftl",data);
	}
	
	
	/**
	 * @description 获取页面表单模板
	 */
	public String getPageFormContent( Object data) throws Exception {
		return getContent("/page/form.ftl",data);
	
	}
	
	
	/**
	 * @description 获取页面js模板
	 */
	public String getPageJSContent( Object data) throws Exception {

		return getContent("/page/template.js",data);
	}
	
	
	
	
	
	/**
	 * 使用数据渲染模板
	 * @param fileName
	 * @param data
	 * @return
	 * @throws Exception
	 * @author zj
	 * @date 2019年1月4日
	 */
	public String getContent(String fileName, Object data) throws Exception {

		String templateFileName = fileName;
		String templateFilePath = templatePath;
		
		
		
		//使用 classes path 下的模板
		templateFilePath = KGenerateServiceImpl.class.getResource("/").getPath();
		templateFilePath += "generator/";
		
		if (StringUtils.isEmpty(templatePath)) {
			throw new Exception("templatePath can not be empty!");
		}
		try {
			Configuration config = new Configuration(Configuration.VERSION_2_3_23);
			config.setDefaultEncoding("UTF-8");
			config.setDirectoryForTemplateLoading(new File(templateFilePath));
			config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			config.setLogTemplateExceptions(false);
			Template template = config.getTemplate(templateFileName);
			StringWriter writer = new StringWriter();
			template.process(data, writer);
			writer.flush();
			String html = writer.toString();
			return html;
		} catch (Exception ex) {
			throw new Exception("FreeMarkerUtil process fail", ex);
		}
	}
}
