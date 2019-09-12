package com.hrong.major.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author hrong
 **/
@Slf4j
public class XSSFilter implements Filter {

	/**
	 * 是否过滤富文本内容
	 */
	private static boolean IS_INCLUDE_RICH_TEXT = false;

	private List<String> excludes = new ArrayList<>();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,ServletException {
		if(log.isDebugEnabled()){
			log.debug("xss filter is open");
		}

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if(handleExcludeURL(req, resp)){
			filterChain.doFilter(request, response);
			return;
		}

		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request,IS_INCLUDE_RICH_TEXT);
		filterChain.doFilter(xssRequest, response);
	}

	private boolean handleExcludeURL(HttpServletRequest request, HttpServletResponse response) {

		if (excludes == null || excludes.isEmpty()) {
			return false;
		}

		String url = request.getServletPath();
		for (String pattern : excludes) {
			Pattern p = Pattern.compile("^" + pattern);
			Matcher m = p.matcher(url);
			if (m.find()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void init(FilterConfig filterConfig) {
		if(log.isDebugEnabled()){
			log.debug("xss filter init~~~~~~~~~~~~");
		}
		String isIncludeRichText = filterConfig.getInitParameter("isIncludeRichText");
		if(StringUtils.isNotBlank(isIncludeRichText)){
			IS_INCLUDE_RICH_TEXT = BooleanUtils.toBoolean(isIncludeRichText);
		}

		String temp = filterConfig.getInitParameter("excludes");
		if (temp != null) {
			String[] url = temp.split(",");
			for (int i = 0; i < url.length; i++) {
				excludes.add(url[i]);
			}
		}
	}

	@Override
	public void destroy() {}
}
