package com.hrong.major.interceptor;

import com.hrong.major.constant.Constant;
import com.hrong.major.model.vo.UserVo;
import com.hrong.major.utils.CookieUtils;
import com.hrong.major.utils.JwtUtils;
import com.hrong.major.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

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
						String user = JwtUtils.getUserByToken(token).getAccount();
						if (StringUtils.isNotBlank(user)) {
							return true;
						}
					} catch (Exception e) {
						response.sendRedirect(request.getContextPath() + "/admin/login");
						return false;
					}
				}
				log.info("IP:{}请求{}被拦截，即将重定向到登录页面", RequestUtils.getIpAddress(request), url);
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
		if (modelAndView != null && modelAndView.getViewName() != null) {
			String view = modelAndView.getViewName();
			if (view.contains(Constant.LOGIN_REQUEST)) {
				modelAndView.addObject("user", new UserVo());
			}
		}
	}
}
