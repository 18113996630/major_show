package com.hrong.major.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrong.major.annotation.ClickLog;
import com.hrong.major.model.ClickType;
import com.hrong.major.model.Major;
import com.hrong.major.model.Subject;
import com.hrong.major.service.MajorService;
import com.hrong.major.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 专业 前端控制器
 * </p>
 *
 * @author hrong
 * @since 2019-08-13
 */
@Slf4j
@Controller
public class MajorController {
	@Resource
	private MajorService majorService;
	@Resource
	private SubjectService subjectService;

	@ClickLog(type = ClickType.major)
	@GetMapping(value = "/majors/{subjectId}")
	public String getMajors(Model model, @PathVariable(value = "subjectId") int subjectId,
							@RequestParam(value = "page") int page,
							@RequestParam(value = "size") int size) {
		IPage<Major> majors = majorService.page(new Page<>(page, size), new QueryWrapper<Major>().eq("subject_id", subjectId).orderByAsc("order_number"));
		Subject currentSubject = subjectService.getById(subjectId);
		List<Subject> subjects = subjectService.list();

		model.addAttribute("pageNum", majors.getCurrent());
		model.addAttribute("pageSize", majors.getSize());
		model.addAttribute("totalPages", majors.getPages());
		model.addAttribute("majors", majors.getRecords());
		model.addAttribute("currentSubject", currentSubject);
		model.addAttribute("subjects", subjects);
		return "major/major";
	}


}

