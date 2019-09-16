package com.hrong.analysis.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author hrong
 **/
public class PropertyUtil {

	private static String basePath = System.getProperty("user.dir");
	private static Properties properties;

	static {
		properties = new Properties();
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(basePath + "/conf.properties");
			properties.load(inputStream);
		} catch (IOException e) {
			inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream("conf.properties");
			try {
				properties.load(inputStream);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}
}
