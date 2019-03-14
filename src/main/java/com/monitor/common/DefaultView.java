package com.monitor.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Auther: heguanyong
 * @Date: 2018/10/8 17:07
 * @Description:
 */
@Configuration
public class DefaultView  extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //替换成修改后url访问的路径
         registry.addResourceHandler("/**")
                 .addResourceLocations("classpath:/META-INF/resources/")
                 .addResourceLocations("classpath:/static/")
                 .addResourceLocations("classpath:/templates/")
                 .addResourceLocations("classpath:/");
    }

}
