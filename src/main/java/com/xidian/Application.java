package com.xidian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@MapperScan("com.xidian.mqttserver.mapper")
@ComponentScan(basePackages = "com.xidian.mqttserver")
public class Application extends SpringBootServletInitializer{
	
	/**
	 * 继承SpringBootServletInitializer，实现该方法才可以在tomcat中启动
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	
	 public static void main(String[] args) {
         SpringApplication.run(Application.class, args);
     }

}
