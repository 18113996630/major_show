package com.hrong.major.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author hrong
 **/
@Controller
public class AdminController {


	@GetMapping(value = "/admin")
	public String adminIndex(){
		return "admin/index";
	}

	@GetMapping(value = "/admin/subject")
	public String subject(){
		return "admin/side/subject";
	}

	@GetMapping(value = "/admin/major")
	public String major(){
		return "admin/side/major";
	}

	@GetMapping(value = "/admin/video")
	public String video(){
		return "admin/side/video";
	}
}
