package com.monitor;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan(basePackages = {"com.monitor.*.dao"})
@ServletComponentScan
public class MonitorApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MonitorApplication.class, args);
		//context.addApplicationListener(new ProjectStartListener());
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MonitorApplication.class);
	}
}
