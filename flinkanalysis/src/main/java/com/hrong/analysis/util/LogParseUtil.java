package com.hrong.analysis.util;

import com.hrong.analysis.entity.Log;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author hrong
 * @Description
 **/
@Slf4j
public class LogParseUtil {
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss Z", Locale.ENGLISH);
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 解析nginx日志
	 * @param logString 原始日志
	 * @return log对象
	 */
	public static Log parseLog(String logString) {
		String[] datas = logString.split("\\|");
		String ip = datas[0];
		String user = datas[1];
		String time = datas[2];
		long logTime = 0L;
		String timeFormat = null;
		try {
			Date date = formatter.parse(time);
			logTime = date.getTime();
			timeFormat = format.format(date);
		} catch (ParseException e) {
			log.error("日志转换出错，输入：{} 错误：{}", logString, e.getMessage());
		}
		String requestMethod = datas[3];
		String status = datas[4];
		String bodySize = datas[5];
		String referer = datas[6];
		String userAgent = datas[7];
		String forwarded = datas[8];
		return new Log(ip, user, logTime, timeFormat, requestMethod, status, bodySize, referer, userAgent, forwarded);
	}

	/**
	 * 获取log中的专业详情id
	 * @param log log对象
	 * @return 专业详情id，若日志格式不正确则返回0
	 */
	public static int getRequestMajorId(Log log){
		String[] strings = log.getRequestMethod().split("/major/info/");
		if (strings.length == 2) {
			String id = strings[1].split("\\s")[0];
			return Integer.valueOf(id);
		}
		return 0;
	}
}
