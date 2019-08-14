package com.hrong.major.aspect;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hrong.major.annotation.ClickLog;
import com.hrong.major.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Author hrong
 **/
@Slf4j
@Aspect
@Component
public class UserLogAspect {

	@Pointcut(value = "@annotation(com.hrong.major.annotation.ClickLog)")
	public void pointcut() {

	}

	@Around("pointcut()")
	public String aroundController(ProceedingJoinPoint  joinPoint) throws Throwable{
		try {
			HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
			String ipAddress = RequestUtils.getIPAddress(request);
			String requestHeader = request.getHeader("user-agent");
			String systemName = System.getProperty("os.name");
			String systemVersion = System.getProperty("os.version");
			String systemBit = System.getProperty("os.arch");
			String httpVersion = request.getProtocol();
			String encoding = request.getCharacterEncoding();
			String cookie = request.getHeader("Cookie");
			String url = String.valueOf(request.getRequestURL());
			String uri = request.getRequestURI();
			int clientPort = request.getRemotePort();
			String method = request.getMethod();


			String infoFromRequest = RequestUtils.getInfoFromRequest(request);
			Object[] args = joinPoint.getArgs();
			System.out.println("参数为："+ JSONArray.toJSONString(args));
			Object target = joinPoint.getTarget();
			String kind = joinPoint.getKind();
			Signature signature = joinPoint.getSignature();
			SourceLocation location = joinPoint.getSourceLocation();
			JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();
			String signatureName = signature.getName();
			Object aThis = joinPoint.getThis();
			String methodName = joinPoint.getSignature().getName();
			if (signature instanceof MethodSignature){
				MethodSignature methodSignature = (MethodSignature)signature;
				ClickLog annotation = methodSignature.getMethod().getAnnotation(ClickLog.class);
				String value = annotation.type().name();
				System.out.println("value值为："+value);
			}
			log.info("请求开始: methodName = {}, request = {}", methodName, request);
			long startTime = System.currentTimeMillis();
			Object response = joinPoint.proceed();
			long endTime = System.currentTimeMillis();
			long executeTime = endTime - startTime;
			log.info("请求结束: methodName = {}, result = {}, 执行时间: time = {}ms", methodName, response, executeTime);
			return response.toString();
		}catch (Exception e){

		}finally {

		}
		return null;


	}
}
