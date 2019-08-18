package com.hrong.major.controller;


import com.hrong.major.model.MajorDetail;
import com.hrong.major.model.Subject;
import com.hrong.major.model.vo.MajorDetailWithVideoVo;
import com.hrong.major.service.MajorDetailService;
import com.hrong.major.service.MajorService;
import com.hrong.major.service.SubjectService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hrong
 * @since 2019-08-17
 */
@Controller
@RequestMapping("/major/info")
public class MajorDetailController {

	@Resource
	private MajorDetailService majorDetailService;
	@Resource
	private SubjectService subjectService;
	@Resource
	private MajorService majorService;

	/**
	 * 根据专业id查询详情
	 */
	@GetMapping(value = "/{id}")
	public String getMajorInfoById(Model model, @PathVariable(value = "id") int id) {
		MajorDetailWithVideoVo detail = majorDetailService.findDetailVoById(id);
		Integer nextDetailId = majorDetailService.findNextMajorDetailIdByCurrentMajorDetailId(id);
		Subject currentSubject = subjectService.getById(majorService.getById(detail.getDetail().getMajorId()).getSubjectId());
		model.addAttribute("detailVo", detail);
		model.addAttribute("nextId", nextDetailId);
		model.addAttribute("currentSubject", currentSubject);
		return "major/major_detail";
	}
}

