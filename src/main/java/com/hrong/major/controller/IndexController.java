package com.hrong.major.controller;


import com.hrong.major.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * @Author hrong
 **/
@Controller
public class IndexController {

	@Resource
	private SubjectService subjectService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("subjects", subjectService.list());
		return "index";
	}
}