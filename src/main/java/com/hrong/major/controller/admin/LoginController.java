package com.hrong.major.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.constant.Constant;
import com.hrong.major.model.User;
import com.hrong.major.model.vo.UserVo;
import com.hrong.major.service.UserService;
import com.hrong.major.utils.CookieUtils;
import com.hrong.major.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author hrong
 **/
@Slf4j
@Controller
public class LoginController {
	@Resource
	private UserService userService;

	@PostMapping(value = "/admin/login")
	public String login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UserVo user, Model model) {
		User userDb = userService.getOne(new QueryWrapper<User>()
				.eq("account", user.getAccount())
				.eq("password", user.getPassword()));
		if (userDb == null) {
			model.addAttribute("msg", "你猜不出来的");
			return "admin/login/login";
		} else {
			String token = JwtUtils.getTokenByAccount(user.getAccount());
			log.info("用户{}的token为：{}", user.getAccount(), token);
			CookieUtils.setCookie(request, response, Constant.COOKIE, token, -1, true);
			return "admin/index";
		}
	}

	@GetMapping(value = "/admin/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UserVo user) {
		User currentUser = JwtUtils.getUserByRequest(request);
		log.info("用户{}退出成功", currentUser.getAccount());
		CookieUtils.deleteCookie(request, response, Constant.COOKIE);
		return "admin/login/login";
	}
}
