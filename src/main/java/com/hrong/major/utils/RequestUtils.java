package com.hrong.major.utils;

import com.hrong.major.constant.Constant;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author hrong
 **/
public class RequestUtils {
	/**
	 * 获取http请求的真实IP地址
	 */
	public static String getIPAddress(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || Constant.IP_HEAD_TYPE.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || Constant.IP_HEAD_TYPE.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || Constant.IP_HEAD_TYPE.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || Constant.IP_HEAD_TYPE.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || Constant.IP_HEAD_TYPE.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (Constant.IP_LOCAL.equals(ip) || Constant.IP_LOCAL_GATE.equals(ip)) {
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException unknownhostexception) {
				ip = "not recognized ip";
			}
		}
		return ip;
	}
}
