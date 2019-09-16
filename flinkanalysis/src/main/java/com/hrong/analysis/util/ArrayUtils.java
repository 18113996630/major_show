package com.hrong.analysis.util;

import com.hrong.analysis.entity.Log;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @Author hrong
 * @Description
 **/
public class ArrayUtils {
	/**
	 * 判断一个arrayList里面的数字是否是连续的
	 *
	 * @param list 参数list
	 * @return 判断结果
	 */
	public static boolean isContinuousArray(ArrayList<Integer> list) {
		list.sort(Comparator.comparingInt(n -> n));
		if (list.size() <= 1){
			return false;
		}
		for (int i = 0; i < list.size() - 2; i++) {
			if (list.get(i + 1) - list.get(i) != 1) {
				return false;
			}
		}
		return true;
	}

	public static Log getLog(int i) {
		Log log = new Log();
		String method = "GET /major/info/" + i + " HTTP/1.1";
		log.setIp("182.148.24.100");
		log.setUser(i+"");
		log.setRequestMethod(method);
		log.setTime(1568550987000L + i * 3000);
		return log;
	}
}
