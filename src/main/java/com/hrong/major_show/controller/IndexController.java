package com.hrong.major_show.controller;


import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author hrong
 **/
@Controller
public class IndexController {
	@RequestMapping("/")
	public String index(Model model, HttpServletResponse response) {
		model.addAttribute("name", "hrong");
		model.addAttribute("content", "test data");
		return "index";
	}
}