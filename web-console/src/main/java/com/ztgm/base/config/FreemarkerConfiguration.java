package com.ztgm.base.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * ftl模板 ${ctx} 前缀变量注入
 * FreemarkerConfiguration.java.
 * 
 * @author zj
* @version 1.0.1 2018年12月20日
* @revision zj 2018年12月20日
* @since 1.0.1
 */
@Configuration
@PropertySource("classpath:project.properties")
public class FreemarkerConfiguration extends FreeMarkerAutoConfiguration.FreeMarkerWebConfiguration {

	/**
	 * project.properties变量
	 */
    @Value("${context-path}")
    private String myProp;

    @Override
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = super.freeMarkerConfigurer();

        Map<String, Object> sharedVariables = new HashMap<>();
        sharedVariables.put("ctx", myProp);
        configurer.setFreemarkerVariables(sharedVariables);

        return configurer;
    }
}