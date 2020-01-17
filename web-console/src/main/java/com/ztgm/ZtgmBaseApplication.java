package com.ztgm;

import java.util.TimeZone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass=true) 
@EnableAsync        //开启异步任务
@EnableTransactionManagement// 启注解事务管理
@MapperScan("com.ztgm.*.dao")//将项目中对应的mapper类的路径加进来就可以了
@PropertySource("classpath:project.properties")
@EnableRedisHttpSession  
@ImportResource(locations={"classpath:spring-session.xml"})
public class ZtgmBaseApplication {


    public static void main(String[] args) {
    	TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    	
    	
        SpringApplication.run(ZtgmBaseApplication.class, args);
    }

}
