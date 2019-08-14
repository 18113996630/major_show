package com.hrong.major.utils;

import com.alibaba.fastjson.JSON;
import com.hrong.major.constant.Constant;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;

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

	public static String getInfoFromRequest(HttpServletRequest request) throws IOException {
		System.out.println("浏览器基本信息："+request.getHeader("user-agent"));
		System.out.println("客户端系统名称："+System.getProperty("os.name"));
		System.out.println("客户端系统版本："+System.getProperty("os.version"));
		System.out.println("客户端操作系统位数："+System.getProperty("os.arch"));
		System.out.println("HTTP协议版本："+request.getProtocol());
		System.out.println("请求编码格式："+request.getCharacterEncoding());
		System.out.println("Accept："+request.getHeader("Accept"));
		System.out.println("Accept-语言："+request.getHeader("Accept-Language"));
		System.out.println("Accept-编码："+request.getHeader("Accept-Encoding"));
		System.out.println("Connection："+request.getHeader("Connection"));
		System.out.println("Cookie："+request.getHeader("Cookie"));
		System.out.println("客户端发出请求时的完整URL"+request.getRequestURL());
		System.out.println("请求行中的资源名部分"+request.getRequestURI());
		System.out.println("请求行中的参数部分"+request.getRemoteAddr());
		System.out.println("客户机所使用的网络端口号"+request.getRemotePort());
		System.out.println("WEB服务器的IP地址"+request.getLocalAddr());
		System.out.println("WEB服务器的主机名"+request.getLocalName());
		System.out.println("客户机请求方式"+request.getMethod());
		System.out.println("请求的文件的路径"+request.getServerName());
		System.out.println("请求体的数据流"+request.getReader());
		BufferedReader br=request.getReader();
		String res = "";
		while ((res = br.readLine()) != null) {
			System.out.println("request body:" + res);
		}
		System.out.println("请求所使用的协议名称"+request.getProtocol());
		System.out.println("请求中所有参数的名字"+ JSON.toJSONString(request.getParameterNames()));
		Enumeration enumNames= request.getParameterNames();
		while (enumNames.hasMoreElements()) {
			String key = (String) enumNames.nextElement();
			System.out.println("参数名称："+key);
		}
		return null;
	}
}
