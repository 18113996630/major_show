package com.hrong.major.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.model.Log;
import com.hrong.major.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @Author hrong
 **/
@Slf4j
@Component
public class IpUtils {
	private static final String BAIDU_AK = "Kl6VANEIjV2QmmIILGMNYFrwnzprBufa";
	private static final String PROD_ENV = "prod";
	private static LogService service;
	private static boolean isProd = true;
	@Resource
	private LogService logService;
	@Value(value = "${spring.profiles.active}")
	private String env;


	@PostConstruct
	public void init(){
		service = logService;
		if (PROD_ENV.equalsIgnoreCase(env)) {
			log.info("当前环境为：生产环境，开启ip查询");
			isProd = true;
		}
	}

	public static String getCity(String ip){
		String address = "未知";
		List<Log> logs = service.list(new QueryWrapper<Log>().eq("ip", ip).ne("city", "").ne("city", "未知"));
		Log logDb = logs.size() == 0 ? null : logs.get(0);
		boolean isNeed = isProd && (logDb == null || logDb.getClass() == null || address.equalsIgnoreCase(logDb.getCity()));
		if (isNeed) {
			log.info("未在日志表中发现该ip，开始从第三方api获取city信息");
			boolean random = new Random().nextBoolean();
			if (random) {
				address = IpUtils.getCityByIpWithBaidu(ip);
				if (address == null) {
					address = IpUtils.getCityByIpWithTaobao(ip);
				}
				address = address == null ? "未知" : address;
			} else {
				address = IpUtils.getCityByIpWithTaobao(ip);
				if (address == null) {
					address = IpUtils.getCityByIpWithBaidu(ip);
				}
				address = address == null ? "未知" : address;
			}
		}else {
			address = Objects.requireNonNull(logDb).getCity();
		}
		log.info("获取结果:{}", address);
		return address;
	}

	/**
	 * 百度开放接口通过IP地址获取地理位置
	 *
	 * @return 返回结果 String
	 */
	private static String getCityByIpWithBaidu(String ip) {
		try {
			if (ip == null || "".equals(ip)) {
				return null;
			}
			String url = "https://api.map.baidu.com/location/ip" + "?ip=" + ip + "&ak=" + BAIDU_AK + "&coor=bd09ll";
			String content = httpGet(url);
			if (content != null) {
				return JSON.parseObject(content).getJSONObject("content").getString("address");
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
	/**
	 * 淘宝开放接口通过IP地址获取地理位置
	 *
	 * @return 返回结果 String
	 */
	private static String getCityByIpWithTaobao(String ip) {
		try {
			if (ip == null || "".equals(ip)) {
				return null;
			}
			String url = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
			String content = httpGet(url);
			if (content != null) {
				JSONObject dataObject = JSON.parseObject(content).getJSONObject("data");
				String region = dataObject.getString("region");
				String city = dataObject.getString("city");
				return region + " " + city;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	private static String httpGet(String url) {
		String content = null;
		CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);
		//设置超时时间，避免产生长时间的阻塞效果
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(4000).setConnectionRequestTimeout(2000)
				.setSocketTimeout(4000).build();
		httpGet.setConfig(requestConfig);
		httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36");
		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = closeableHttpClient.execute(httpGet);
			content = EntityUtils.toString(httpResponse.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (httpResponse != null) {
					httpResponse.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			closeableHttpClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}


}
