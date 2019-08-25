package com.hrong.major.controller.admin;

import com.hrong.major.constant.CacheConstant;
import com.hrong.major.model.Log;
import com.hrong.major.model.User;
import com.hrong.major.service.LogService;
import com.hrong.major.service.MajorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author hrong
 **/
@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Resource
	private MajorService majorService;
	@Resource
	private LogService logService;

	@GetMapping
	public String adminIndex() {
		return "admin/index";
	}
	@GetMapping(value = "/index")
	public String index() {
		return "admin/index";
	}

	@GetMapping(value = "/login")
	public String login(Model model){
		model.addAttribute("user", new User());
		return "admin/login/login";
	}

	/**
	 * 专业种类表格
	 */
	@GetMapping(value = "/subject")
	public String subject() {
		return "admin/side/subject";
	}

	/**
	 * 专业表格
	 */
	@GetMapping(value = "/major")
	public String major(Model model){
		//用于下拉选项赋值
		model.addAttribute("subjects", CacheConstant.subjects);
		return "admin/side/major";
	}

	/**
	 * 视频表格
	 */
	@GetMapping(value = "/video")
	public String video(Model model){
		model.addAttribute("majors", majorService.list());
		return "admin/side/video";
	}

	/**
	 * 专业详情表格
	 */
	@GetMapping(value = "/major/detail")
	public String videoDetail(Model model){
		//用于下拉选项赋值
		model.addAttribute("subjects", CacheConstant.subjects);
		return "admin/side/major_detail";
	}

	/**
	 * 专业详情表格
	 */
	@GetMapping(value = "/log")
	public String log(Model model){
		List<Log> resources = logService.findResourceType();
		model.addAttribute("resources", resources);
		return "admin/side/log";
	}
}
