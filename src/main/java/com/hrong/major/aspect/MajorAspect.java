package com.hrong.major.aspect;


import com.alibaba.fastjson.JSON;
import com.hrong.major.annotation.ClickLog;
import com.hrong.major.model.ClickType;
import com.hrong.major.model.Log;
import com.hrong.major.service.LogService;
import com.hrong.major.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author hrong
 **/
@Slf4j
@Aspect
@Component
public class MajorAspect {
	/**
	 * 当前线程小于core时，创建新线程执行任务
	 * 当前线程等于core且queue未满时，将任务放进queue，不创建新的线程
	 * 当前线程等于core且queue已满时，创建新的线程
	 * 当前线程等于max且queue已满时，执行拒绝策略
	 * <p>
	 * 每秒任务数假设处于100-200范围
	 * 保存一次平均时间在100ms
	 * corePoolSize = tasks * taskCount = 10-20 可取15
	 * <p>
	 * maxPoolSize = 20
	 * <p>
	 * queueSize = maxTask-core*(每秒处理的task数量) = 200 - 15 * (1/0.1) = 50
	 */
	private static ThreadPoolExecutor executor = new ThreadPoolExecutor(16,
			20,
			60L,
			TimeUnit.SECONDS,
			new LinkedBlockingQueue<>(40), Thread::new, new Reject());


	@Resource
	private LogService logService;

	@Pointcut(value = "@annotation(com.hrong.major.annotation.ClickLog)")
	public void pointcut() {
	}


	@Around("pointcut()")
	public String aroundController(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
			String ipAddress = RequestUtils.getIpAddress(request);
			String userAgent = request.getHeader("user-agent");
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
			String params = JSON.toJSONString(request.getParameterMap());
			Signature signature = joinPoint.getSignature();
			String resourceType = null;
			if (signature instanceof MethodSignature) {
				MethodSignature methodSignature = (MethodSignature) signature;
				ClickLog annotation = methodSignature.getMethod().getAnnotation(ClickLog.class);
				if (ClickType.video.equals(annotation.type())) {
					resourceType = request.getParameter("type");
				} else {
					resourceType = annotation.type().name();
				}
			}
			String methodName = signature.getName();
			Object aThis = joinPoint.getThis();
			String requestAddress = aThis.toString() + methodName;
			long startTime = System.currentTimeMillis();
			Object response = joinPoint.proceed();
			long endTime = System.currentTimeMillis();
			long executeTime = endTime - startTime;
			Log logInfo = new Log(null, resourceType, requestAddress, ipAddress, userAgent, systemName, systemVersion, systemBit, httpVersion, encoding, cookie, url, uri, String.valueOf(clientPort), method, params, LocalDateTime.now(), Math.toIntExact(executeTime));
			executor.execute(() -> logService.save(logInfo));
			return response == null ? null : response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("出现异常:{}", e.getMessage());
			Object response = joinPoint.proceed();
			return response == null ? null : response.toString();
		}
	}

	static class Reject implements RejectedExecutionHandler {
		private ThreadPoolExecutor rejectExecutor = new ThreadPoolExecutor(5,
				10,
				60L,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(20), (ThreadFactory) Thread::new);

		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			log.warn("【当前并发较高，超过200/s,使用备用线程保存日志数据，请优化线程池参数】");
			rejectExecutor.execute(r);
		}
	}

	private Date getExpiration() {
		Long time = 24 * 60 * 60 * 1000L;
		return new Date(System.currentTimeMillis() + time);
	}
}
