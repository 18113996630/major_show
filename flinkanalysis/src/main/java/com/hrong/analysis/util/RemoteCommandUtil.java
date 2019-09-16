package com.hrong.analysis.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @Author hrong
 * @Description
 **/
public class RemoteCommandUtil {
	private static Logger log = LoggerFactory.getLogger(RemoteCommandUtil.class);
	public static void main(String[] args) throws InterruptedException {
		String ip = "192.168.0.50";
		String userName = "hadoop";
		String password = "hadoop";
		Connection connection = login(ip, userName, password);
		while (true) {
			String result = execute(connection, "tail -n 200 test.json");
			System.out.println(result);
			Thread.sleep(1000);
		}
	}
	/**
	 * 登录主机
	 *
	 * @return 登录成功返回true，否则返回false
	 */
	public static Connection login(String ip, String userName, String password) {

		boolean flg = false;
		Connection conn = null;
		try {
			conn = new Connection(ip);
			conn.connect();
			flg = conn.authenticateWithPassword(userName, password);
			if (flg) {
				log.info("=========登录成功=========");
				return conn;
			}
		} catch (IOException e) {
			log.error("=========登录失败=========" + e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 远程执行shll脚本或者命令
	 *
	 * @param cmd 即将执行的命令
	 * @return 命令执行完后返回的结果值
	 */
	public static String execute(Connection conn, String cmd) {
		String result = null;
		Session session = null;
		try {
			if (conn != null) {
				session = conn.openSession();
				session.execCommand(cmd);
				result = processStdout(session.getStdout());
				//如果为得到标准输出为空，说明脚本执行出错了
				if (StringUtils.isBlank(result)) {
					log.error("出错了");
					return null;
				}
			}
		} catch (IOException e) {
			log.info("执行命令失败,链接conn:" + conn + ",执行的命令：" + cmd + "  " + e.getMessage());
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	/**
	 * 解析脚本执行返回的结果集
	 *
	 * @param in      输入流对象
	 * @return 以纯文本的格式返回
	 */
	private static String processStdout(InputStream in) {
		InputStream stdout = new StreamGobbler(in);
		StringBuilder buffer = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(stdout, Charset.defaultCharset()));
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line).append("\n");
			}
		} catch (IOException e) {
			log.error("解析脚本出错：" + e.getMessage());
			e.printStackTrace();
		}
		return buffer.toString();
	}
}
