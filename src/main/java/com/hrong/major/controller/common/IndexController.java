package com.hrong.major.controller.common;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.annotation.ClickLog;
import com.hrong.major.constant.CacheConstant;
import com.hrong.major.model.ClickType;
import com.hrong.major.model.Log;
import com.hrong.major.model.User;
import com.hrong.major.model.Video;
import com.hrong.major.model.vo.MessageVo;
import com.hrong.major.model.vo.SearchVo;
import com.hrong.major.service.LogService;
import com.hrong.major.service.MajorService;
import com.hrong.major.service.SearchService;
import com.hrong.major.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

import static com.hrong.major.constant.Constant.REDIS_BLACK_IP;

/**
 * @Author hrong
 **/
@Slf4j
@Controller
public class IndexController {

	@Resource
	private LogService logService;
	@Resource
	private MajorService majorService;
	@Resource
	private SearchService searchService;
	@Resource
	private CacheConstant cacheConstant;
	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@ClickLog(type = ClickType.subject)
	@GetMapping("/")
	public String index(Model model, HttpServletRequest request) {
		String ip = RequestUtils.getIp(request);
		log.info("ip:{}访问index页面", ip);
		int isFirstVisit = logService.count(new QueryWrapper<Log>().eq("ip", ip));
		if (isFirstVisit == 0) {
			return "about/about";
		}
		model.addAttribute("subjects", cacheConstant.subjects());
		model.addAttribute("searchVo", new SearchVo());
		model.addAttribute("searches", searchService.getPopularSearches());
		return "index";
	}

	@ClickLog(type = ClickType.about)
	@GetMapping("/about")
	public String about() {
		return "about/about";
	}

	@ClickLog(type = ClickType.contact)
	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("message", new MessageVo());
		return "contact/contact";
	}

	@ClickLog(type = ClickType.contribute)
	@GetMapping("/contribute")
	public String contribute(Model model) {
		model.addAttribute("majors", majorService.list());
		model.addAttribute("msg", null);
		model.addAttribute("video", new Video());
		return "contribute/contribute";
	}

	@ClickLog(type = ClickType.login)
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login/login";
	}

	@ClickLog(type = ClickType.register)
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register/register";
	}

	@ClickLog(type = ClickType.declare)
	@GetMapping("/declare")
	public String declare() {
		return "declare/declare";
	}

	@GetMapping("/black")
	public String black(HttpServletRequest request, Model model) {
		String ip = RequestUtils.getIp(request);
		Long expire = stringRedisTemplate.getExpire(REDIS_BLACK_IP + ip, TimeUnit.SECONDS);
		model.addAttribute("timeTolive", expire);
		return "error/black";
	}
	@GetMapping("/pay")
	public String pay(){
		return "pay/pay";
	}
}