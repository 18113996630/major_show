package com.hrong.major.controller;

import com.hrong.major.model.Subject;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;

/**
 * @Author hrong
 **/
@ControllerAdvice
public class GlobalController {

	/**
	 * 使用方法:@ModelAttribute(name = "subjects")
	 * 被@ModelAttribute注释的方法会在此controller每个方法执行前被执行
	 */
	public List<Subject> index() {
		return null;
	}
}
