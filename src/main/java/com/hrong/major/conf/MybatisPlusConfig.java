package com.hrong.major.conf;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * @Author hrong
 **/
@EnableTransactionManagement
@Configuration
@MapperScan("com.hrong.major.dao")
public class MybatisPlusConfig {
	/**
	 * 分页插件
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

	/**
	 * 打印 sql
	 */
	@Bean
	@Profile({"dev"})
	public PerformanceInterceptor performanceInterceptor() {
		PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
		//格式化sql语句
		Properties properties = new Properties();
		properties.setProperty("format", "true");
		performanceInterceptor.setProperties(properties);
		return performanceInterceptor;
	}
}
