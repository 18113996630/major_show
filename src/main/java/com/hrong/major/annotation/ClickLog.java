package com.hrong.major.annotation;

import com.hrong.major.model.ClickType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author huangrong
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClickLog {
	/**
	 * 点击类型
	 */
	ClickType type() default ClickType.major;
}
