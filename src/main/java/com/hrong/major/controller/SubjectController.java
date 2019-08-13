package com.hrong.major.controller;


import com.hrong.major.service.SubjectService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * <p>
 * 学科分类 前端控制器
 * </p>
 *
 * @author hrong
 * @since 2019-08-13
 */
@Controller
@RequestMapping("/subject")
public class SubjectController {
	@Resource
	private SubjectService subjectService;


}

