package com.hrong.major.exception;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Map;


/**
 * 异常断言，提供简便的方式判断条件，并在条件不满足时抛出异常
 * 例如：Assert.assertNotNull(name, "name不能为空")
 * 含义：断定name不为空，当name为空时抛出提示信息为“name不能为空”的异常
 * 包含占位符的异常提示信息示例：{0}处抛出了异常，原因为：{1}
 *
 * @author hrong
 */
public class Assert {
	/**
	 * 创建异常
	 *
	 * @param msg  包含占位符的异常提示信息
	 * @param args 异常提示信息参数
	 * @return 抛出异常
	 */
	private static BaseException newException(String msg, Object... args) {
		return newException(MessageFormat.format(msg, args));
	}

	/**
	 * 创建异常
	 *
	 * @param msg 异常提示信息
	 * @return 抛出异常
	 */
	private static BaseException newException(String msg) {
		return new BaseException(msg);
	}

	/**
	 * 创建异常
	 *
	 * @param t 可抛出的异常
	 * @return 抛出异常
	 */
	private static BaseException newException(Throwable t) {
		return new BaseException(t);
	}


	/**
	 * 断言对象obj非空。如果对象obj为空，则抛出异常
	 *
	 * @param obj 待判断对象
	 * @param msg 异常提示信息
	 */
	public static void assertNotNull(Object obj, @NotNull String msg) {
		if (obj == null) {
			throw newException(msg);
		}
	}

	/**
	 * 断言对象obj非空。如果对象obj为空，则抛出异常
	 * 异常信息message支持传递参数方式，避免在判断之前进行字符串拼接操作
	 *
	 * @param obj  待判断对象
	 * @param msg  包含占位符的异常提示信息
	 * @param args message占位符对应的参数列表
	 */
	public static void assertNotNull(Object obj, @NotNull String msg, Object... args) {
		if (obj == null) {
			throw newException(msg, args);
		}
	}

	/**
	 * 断言字符串str不为空串（长度为0）。如果字符串str为空串，则抛出异常
	 *
	 * @param str 待判断字符串
	 * @param msg 异常提示信息
	 */
	public static void assertNotBlank(String str, @NotNull String msg) {
		if (StringUtils.isBlank(str)) {
			throw newException(msg);
		}
	}

	/**
	 * 断言字符串str不为空串（长度为0）。如果字符串str为空串，则抛出异常
	 * 异常信息message支持传递参数方式，避免在判断之前进行字符串拼接操作
	 *
	 * @param str  待判断字符串
	 * @param msg  包含占位符的异常提示信息
	 * @param args message占位符对应的参数列表
	 */
	public static void assertNotEmpty(String str, @NotNull String msg, Object... args) {
		if (StringUtils.isBlank(str)) {
			throw newException(msg, args);
		}
	}

	/**
	 * 断言数组arrays大小不为0。如果数组arrays大小不为0，则抛出异常
	 *
	 * @param arrays 待判断数组
	 * @param msg    异常提示信息
	 */
	public static void assertNotEmpty(Object[] arrays, @NotNull String msg) {
		if (arrays == null || arrays.length == 0) {
			throw newException(msg);
		}
	}

	/**
	 * 断言数组arrays大小不为0。如果数组arrays大小不为0，则抛出异常
	 * 异常信息message支持传递参数方式，避免在判断之前进行字符串拼接操作
	 *
	 * @param arrays 待判断数组
	 * @param msg    包含占位符的异常提示信息
	 * @param args   message占位符对应的参数列表
	 */
	public static void assertNotEmpty(Object[] arrays, @NotNull String msg, Object... args) {
		if (arrays == null || arrays.length == 0) {
			throw newException(msg, args);
		}
	}

	/**
	 * 断言集合c大小不为0。如果集合c大小不为0，则抛出异常
	 *
	 * @param c   待判断数组
	 * @param msg 异常提示信息
	 */
	public static void assertNotEmpty(Collection<?> c, @NotNull String msg) {
		if (c == null || c.isEmpty()) {
			throw newException(msg);
		}
	}

	/**
	 * 断言集合c大小不为0。如果集合c大小不为0，则抛出异常
	 *
	 * @param c    待判断数组
	 * @param msg  包含占位符的异常提示信息
	 * @param args message占位符对应的参数列表
	 */
	public static void assertNotEmpty(Collection<?> c, @NotNull String msg, Object... args) {
		if (c == null || c.isEmpty()) {
			throw newException(msg, args);
		}
	}

	/**
	 * 断言Map大小不为0。如果Map大小不为0，则抛出异常
	 *
	 * @param map 待判断Map
	 * @param msg 异常提示信息
	 */
	public static void assertNotEmpty(Map<?, ?> map, @NotNull String msg) {
		if (map == null || map.isEmpty()) {
			throw newException(msg);
		}
	}

	/**
	 * 断言Map大小不为0。如果Map大小不为0，则抛出异常
	 *
	 * @param map  待判断Map
	 * @param msg  包含占位符的异常提示信息
	 * @param args message占位符对应的参数列表
	 */
	public static void assertNotEmpty(Map<?, ?> map, @NotNull String msg, Object... args) {
		if (map == null || map.isEmpty()) {
			throw newException(msg, args);
		}
	}

	/**
	 * 断言布尔值expression为false。如果布尔值expression为true，则抛出异常
	 *
	 * @param expression 待判断布尔变量
	 * @param msg        异常提示信息
	 */
	public static void assertIsFalse(boolean expression, @NotNull String msg) {
		if (expression) {
			throw newException(msg);
		}
	}

	/**
	 * 断言布尔值expression为false。如果布尔值expression为true，则抛出异常
	 *
	 * @param expression 待判断布尔变量
	 * @param msg        包含占位符的异常提示信息
	 * @param args       message占位符对应的参数列表
	 */
	public static void assertIsFalse(boolean expression, @NotNull String msg, Object... args) {
		if (expression) {
			throw newException(msg, args);
		}
	}

	/**
	 * 断言布尔值expression为true。如果布尔值expression为false，则抛出异常
	 *
	 * @param expression 待判断布尔变量
	 * @param msg        异常提示信息
	 */
	public static void assertIsTrue(boolean expression, @NotNull String msg) {
		if (!expression) {
			throw newException(msg);
		}
	}

	/**
	 * 断言布尔值expression为true。如果布尔值expression为false，则抛出异常
	 *
	 * @param expression 待判断布尔变量
	 * @param msg        包含占位符的异常提示信息
	 * @param args       message占位符对应的参数列表
	 */
	public static void assertIsTrue(boolean expression, @NotNull String msg, Object... args) {
		if (!expression) {
			throw newException(msg, args);
		}
	}

	/**
	 * 断言对象obj为null。如果对象obj不为null，则抛出异常
	 *
	 * @param obj 待判断对象
	 * @param msg 异常提示信息
	 */
	public static void assertIsNull(Object obj, @NotNull String msg) {
		if (obj != null) {
			throw newException(msg);
		}
	}

	/**
	 * 断言对象obj为null。如果对象obj不为null，则抛出异常
	 *
	 * @param obj  待判断布尔变量
	 * @param msg  包含占位符的异常提示信息
	 * @param args message占位符对应的参数列表
	 */
	public static void assertIsNull(Object obj, @NotNull String msg, Object... args) {
		if (obj != null) {
			throw newException(msg, args);
		}
	}

	/**
	 * 直接抛出异常
	 *
	 * @param msg 异常提示信息
	 */
	public static void assertFail(@NotNull String msg) {
		throw newException(msg);
	}

	/**
	 * 直接抛出异常
	 *
	 * @param msg  包含占位符的异常提示信息
	 * @param args message占位符对应的参数列表
	 */
	public static void assertFail(@NotNull String msg, Object... args) {
		throw newException(msg, args);
	}

	/**
	 * 直接抛出异常，并包含原异常信息
	 * 当捕获非运行时异常（非继承{@link RuntimeException}）时，并该异常进行业务描述时，
	 * 必须传递原始异常，作为新异常的cause
	 *
	 * @param t 原始异常
	 */
	public static void assertFail(Throwable t) {
		throw newException(t);
	}

	/**
	 * 直接抛出异常，并包含原异常信息
	 * 当捕获非运行时异常（非继承{@link RuntimeException}）时，并该异常进行业务描述时，
	 * 必须传递原始异常，作为新异常的cause
	 *
	 * @param t    原始异常
	 * @param msg  包含占位符的异常提示信息
	 * @param args message占位符对应的参数列表
	 */
	public static void assertFail(Throwable t, @NotNull String msg, Object... args) {
		throw newException(MessageFormat.format(msg, args), t);
	}

	/**
	 * 断言对象o1与对象o2相等，此处的相等指（o1.equals(o2)为true）。
	 * 如果两对象不相等，则抛出异常
	 *
	 * @param o1  待判断对象，若o1为null，也当作不相等处理
	 * @param o2  待判断对象
	 * @param msg 异常提示信息
	 */
	public static void assertEquals(Object o1, Object o2, @NotNull String msg) {
		if (o1 == o2) {
			return;
		}
		if (o1 == null) {
			throw newException(msg);
		}
		if (o1 instanceof String && o2 instanceof String) {
			if (!o1.toString().equalsIgnoreCase(o2.toString())){
				throw newException(msg);
			}
		}
		if (!o1.equals(o2)) {
			throw newException(msg);
		}
	}

	/**
	 * 断言对象o1与对象o2相等，此处的相等指（o1.equals(o2)为true）。
	 * 如果两对象不相等，则抛出异常
	 *
	 * @param o1   待判断对象，若o1为null，也当作不相等处理
	 * @param o2   待判断对象
	 * @param msg  异常提示信息，包含占位符
	 * @param args message占位符对应的参数列表
	 */
	public static void assertEquals(Object o1, Object o2, @NotNull String msg, Object... args) {
		if (o1 == o2) {
			return;
		}
		if (o1 == null) {
			throw newException(msg, args);
		}
		if (o1 instanceof String && o2 instanceof String) {
			if (!o1.toString().equalsIgnoreCase(o2.toString())){
				throw newException(msg);
			}
		}
		if (!o1.equals(o2)) {
			throw newException(msg, args);
		}
	}
}
