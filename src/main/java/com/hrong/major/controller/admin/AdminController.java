package com.hrong.major.controller.admin;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author hrong
 **/
@Controller
public class AdminController {


	@GetMapping(value = "/admin")
	public String adminIndex(){
		return "admin/index";
	}
	// 发送post请求，代替了RequestMapping（value="/user/login", method="post"）
	@PostMapping(value = "/admin/login")
	// 对登录请求判断request的参数值，并存放在map中
	public String login(@RequestParam("username") String username,
						@RequestParam("password") String password) {
		// 我们判断，如果登录名不是空，并且，密码是 123456 就登录成功（暂不涉及数据库）
		if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
			// 登录成功，就跳转到下一个页面
			return "dashboard";
		} else {
			// 登录失败，刷新本登录页
			return "login/login";
		}
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
