package com.hrong.major.filter;


import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 从springContext中获取所有ControllerMapping 并过滤出含有save的url，这些url的请求会经过TransferFilter
 *
 * @author hrong
 */
@Component
public class FilterUrlMapping {

	@Resource
	ApplicationContext applicationContext;

	private Set<String> allUrlMappings() {
		Set<String> result = new HashSet<>();
		RequestMappingHandlerMapping rmhp = applicationContext.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> map = rmhp.getHandlerMethods();

		for (RequestMappingInfo info : map.keySet()) {
			//getMatchingPatterns优化
			result.add(info.getPatternsCondition().toString().replace("[", "").replace("]", ""));
		}
		return result;
	}

	@Bean
	public FilterRegistrationBean filterRegistration() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		//添加过滤器
		filterRegistration.setFilter(new XSSFilter());
		Set<String> allSaveUrlPattern = allUrlMappings();
//emsBasePrivilegeDao.findAllSaveUrlPattern();
		if (CollectionUtils.isEmpty(allSaveUrlPattern)) {
			return filterRegistration;
		}

		String pattern = "/*.*save.*";//  save
		Set<String> urlPatterns = new LinkedHashSet();

		for (String saveUrlPattern : allSaveUrlPattern) {
			if (Pattern.matches(pattern, saveUrlPattern)) {
				urlPatterns.add(saveUrlPattern);
			}
		}
		filterRegistration.setUrlPatterns(urlPatterns);

		filterRegistration.setName("XSSFilter");//
		return filterRegistration;
	}


}