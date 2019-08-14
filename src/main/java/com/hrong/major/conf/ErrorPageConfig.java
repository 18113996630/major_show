package com.hrong.major.conf;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @Author hrong
 **/
@Configuration
public class ErrorPageConfig {
	@Bean
	public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
		return (factory -> {
			ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
			ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");
			factory.addErrorPages(errorPage404, errorPage500);
		});
	}
}
