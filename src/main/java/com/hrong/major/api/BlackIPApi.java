package com.hrong.major.api;

import com.alibaba.fastjson.JSONObject;
import com.hrong.major.model.Blacklist;
import com.hrong.major.model.vo.Result;
import com.hrong.major.service.BlacklistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.hrong.major.constant.Constant.REDIS_BLACK_IP;

/**
 * @Author hrong
 * @Description
 **/
@Slf4j
@RestController
public class BlackIPApi {
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	@Resource
	private BlacklistService blacklistService;

	@PostMapping(value = "/major/api/black_ip")
	public String addBlackIp(@RequestBody String blackIpInfo){
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			log.info("开始调用【新增黑名单】接口，请求数据：{}， 请求时间：{}", blackIpInfo, df.format(new Date()));
			Blacklist black = JSONObject.parseObject(blackIpInfo, Blacklist.class);
			log.info("json转换成功，开始入库");
			blacklistService.save(black);
			log.info("入库成功，开始保存至redis");
			stringRedisTemplate.opsForValue().set(REDIS_BLACK_IP+black.getIp(), black.getIp(), 180L, TimeUnit.SECONDS);
			log.info("保存至redis成功，过期时间：{}秒钟后", stringRedisTemplate.getExpire(REDIS_BLACK_IP+black.getIp(), TimeUnit.SECONDS));
			return JSONObject.toJSONString(Result.success("success"));
		} catch (Exception e) {
			log.error("【新增黑名单】接口调用失败，失败原因：{}", e.getMessage());
			return JSONObject.toJSONString(Result.err(500, e.getMessage()));
		}
	}
}
