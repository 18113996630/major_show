package com.hrong.major.interceptor;

import com.hrong.major.constant.Constant;
import com.hrong.major.utils.CookieUtils;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Base64Utils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.hrong.major.constant.Constant.KEY;
import static com.hrong.major.constant.Constant.REQUEST_TYPE;

/**
 * @Author hrong
 **/
@Slf4j
@Configuration
public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			String requestUri = request.getRequestURI();
			if (requestUri.contains(Constant.ADMIN_REQUEST_PREFIX)) {
				String url = handlerMethod.getBeanType().getName() + "." + handlerMethod.getMethod().getName();
				//解密jwt
				String token = CookieUtils.getToken(request);
				if (token != null) {
					try {
						String user = Jwts.parser()
								.setSigningKey(Base64Utils.encodeToString(KEY.getBytes()))
								.requireAudience("audience")
								.requireIssuer("issuer")
								.parseClaimsJws(token.replace("Bearer ", ""))
								.getBody()
								.getSubject();
						if (StringUtils.isNotBlank(user)) {
							return true;
						}
					} catch (Exception e) {
						e.printStackTrace();
						response.sendRedirect(request.getContextPath() + "/admin/login");
						return false;
					}
				}
				log.info(url + "被拦截，即将重定向到登录页面");
				response.setStatus(403);
				//ajax请求
				String requestType = request.getHeader("X-Requested-With");
				if (REQUEST_TYPE.equals(requestType)) {
					response.setContentType("text/html;charset=gb2312");
					PrintWriter out = response.getWriter();
					out.println("地址:" + url + "无权限访问，请登录后重试");
				} else {
					response.sendRedirect(request.getContextPath() + "/admin/login");
				}
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String requestUri = request.getRequestURI();
		System.out.println(response.toString());
//		if (requestUri.contains())
	}
}
