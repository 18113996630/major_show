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
}
