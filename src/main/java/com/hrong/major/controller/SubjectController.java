package com.hrong.major.controller;

import com.hrong.major.model.vo.Result;
import com.hrong.major.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author hrong
 **/
@Slf4j
@Controller
public class SubjectController {

	@Resource
	private SubjectService subjectService;

	@ResponseBody
	@GetMapping(value = "/subject/{id}")
	public Object findById(@PathVariable int id) {
		return Result.success(subjectService.getById(id));
	}
}
