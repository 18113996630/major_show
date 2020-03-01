package com.hrong.major.aspect;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.hrong.major.exception.BaseException;
import com.hrong.major.model.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

/**
 * @Author hrong
 **/
@Slf4j
@Aspect
@Component
public class ControllerAspect {

	@Pointcut(value = "execution(public * com.hrong.major.controller.*.*(..))")
	public void controllerMethod() {
	}

	@Around("controllerMethod()")
	public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
		String method = pjp.getSignature().toShortString();
		log.info("进入方法：{}", pjp.getSignature());
		log.info("参数：[{}]", Arrays.toString(pjp.getArgs()));
		long startTime = System.currentTimeMillis();
		Object result;
		try {
			result = pjp.proceed();
			log.info("方法：{}处理结果：{}", method, JSONObject.toJSONString(result));
			log.info("方法：{}花费时间:{}", method, (System.currentTimeMillis() - startTime));
			log.info("**********************************************************************");
		} catch (Throwable e) {
			result = handlerException(pjp, e);
		}
		return result;
	}

	private Object handlerException(ProceedingJoinPoint pjp, Throwable e) {
		log.error("出现异常:", e);
		String msg;
		if (e instanceof JSONException) {
			msg = "json格式化出现异常：" + e.getMessage();
		} else if (e instanceof IOException) {
			msg = "io异常：" + e.getMessage();
		} else if (e instanceof BaseException) {
			//自定义异常信息
			msg = e.getMessage();
		} else if (e instanceof DataAccessException) {
			msg = "服务器异常！DB_ERROR";
		} else if (e instanceof IllegalArgumentException) {
			msg = "参数有误：" + e.getMessage();
		} else {
			msg = "服务器异常:" + e.getMessage();
		}
		return Result.err(500, msg);
	}
}
