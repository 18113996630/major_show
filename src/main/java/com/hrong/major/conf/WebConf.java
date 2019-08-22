package com.hrong.major.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author hrong
 **/
@Slf4j
@Configuration
public class WebConf implements WebMvcConfigurer {
	@Value("${cover.path}")
	private String coverPath;
	@Value("${face.path}")
	private String facePath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info("添加静态封面资源路径:{}", coverPath);
		log.info("添加静态头像资源路径:{}", facePath);
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		//访问路径：http://localhost:8081/cover/1c8e2b974c543582117948b670375434d8001abd.jpg
		//addResourceHandler填:/cover/**，addResourceLocations填本地路径：file:E:/workspace/java/cover/
		registry.addResourceHandler("/cover/**").addResourceLocations(coverPath);
		registry.addResourceHandler("/face/**").addResourceLocations(facePath);
	}
}
