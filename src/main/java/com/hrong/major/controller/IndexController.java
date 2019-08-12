package com.hrong.major.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.hrong.major.model.SubjectCategory;
import com.hrong.major.service.ISubjectCategoryService;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author hrong
 **/
@Controller
public class IndexController {

	@Resource
	private ISubjectCategoryService subjectCategoryService;

	@GetMapping("/")
	public String index(Model model, HttpServletResponse response) {
		List<SubjectCategory> subjectCategories = subjectCategoryService.findSubjectCategories();
		model.addAttribute("subjects", subjectCategories);
		return "index";
	}
}