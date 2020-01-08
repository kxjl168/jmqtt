package com.ztgm.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.ztgm.base.filter.FreemarkerFilter;
import com.ztgm.base.filter.JspFilter;
import com.ztgm.base.filter.WebSiteMeshFilter;

import java.util.ArrayList;
import java.util.concurrent.Executor;

@Configuration
@PropertySource("classpath:project.properties")
public class WebConfig extends WebMvcConfigurerAdapter {
	/**
	 * Freemarker 过滤器
	 *
	 * @return
	 */
	@Bean
	public FilterRegistrationBean freemarkerFilter() {
		FilterRegistrationBean filter = new FilterRegistrationBean();

		FreemarkerFilter freemarkerFilter = new FreemarkerFilter();
		filter.setFilter(freemarkerFilter);
		filter.setEnabled(true);
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("*.ftl");
		filter.setUrlPatterns(arrayList);
		return filter;
	}

/*	@Bean
	public ViewResolver getJspViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/templates");
		internalResourceViewResolver.setViewNames("*.jsp");
		//internalResourceViewResolver.setSuffix(".jsp");
		internalResourceViewResolver.setOrder(1);
		return internalResourceViewResolver;
	}

	@Bean
	public FreeMarkerViewResolver getFreeMarkerViewResolver() {
		FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
		freeMarkerViewResolver.setCache(true);
		freeMarkerViewResolver.setPrefix("/templates");
		//freeMarkerViewResolver.setSuffix(".html");
		freeMarkerViewResolver.setViewNames("*.ftl");
		freeMarkerViewResolver.setRequestContextAttribute("request");
		freeMarkerViewResolver.setOrder(0);
		freeMarkerViewResolver.setContentType("text/html;charset=UTF-8");

		return freeMarkerViewResolver;
	}*/

	@Bean
	public FilterRegistrationBean jspFilter() {
		FilterRegistrationBean filter = new FilterRegistrationBean();

		JspFilter jspFilter = new JspFilter();
		filter.setFilter(jspFilter);
		filter.setEnabled(true);
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("*.jsp");
		filter.setUrlPatterns(arrayList);
		return filter;
	}

	/**
	 * 装饰器
	 *
	 * @return
	 */
	@Bean
	public FilterRegistrationBean siteMeshFilter() {
		FilterRegistrationBean filter = new FilterRegistrationBean();

		WebSiteMeshFilter siteMeshFilter = new WebSiteMeshFilter();
		filter.setFilter(siteMeshFilter);
		filter.setEnabled(true);
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("/*");
		filter.setUrlPatterns(arrayList);
		return filter;
	}

	@Value("${pool.corePoolSize}")
	private Integer corePoolSize;

	@Value("${pool.maxPoolSize}")
	private Integer maxPoolSize;

	@Value("${pool.queueCapacity}")
	private Integer queueCapacity;

	@Value("${pool.keepAliveSeconds}")
	private Integer keepAliveSeconds;

	@Bean(name = "Async")
	public Executor customAsync() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		// 设置核心线程数
		threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
		// 设置最大线程数
		threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
		// 设置队列容量
		threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
		// 设置线程活跃时间（秒）
		threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
		// 设置默认线程名称
		threadPoolTaskExecutor.setThreadNamePrefix("Async-");
		threadPoolTaskExecutor.afterPropertiesSet();
		return threadPoolTaskExecutor;
	}

}
