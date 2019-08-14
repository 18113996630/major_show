package com.hrong.major.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.model.Major;
import com.hrong.major.model.Subject;
import com.hrong.major.service.MajorService;
import com.hrong.major.service.SubjectService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 专业 前端控制器
 * </p>
 *
 * @author hrong
 * @since 2019-08-13
 */
@Controller
public class MajorController {
	@Resource
	private MajorService majorService;
	@Resource
	private SubjectService subjectService;

	@GetMapping(value = "/majors/{subjectId}")
	public String findMajorsBySubject(Model model, @PathVariable(value = "subjectId") int subjectId){
		QueryWrapper<Major> wrapper = new QueryWrapper<Major>().eq("subject_id", subjectId);
		List<Major> majors = majorService.list(wrapper);
		List<Subject> subjects = subjectService.list();
		model.addAttribute("majors", majors);
		model.addAttribute("subjects", subjects);
		return "major/major";
	}
}

