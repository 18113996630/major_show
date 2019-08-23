package com.hrong.major.controller.common;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.annotation.ClickLog;
import com.hrong.major.constant.CacheConstant;
import com.hrong.major.model.ClickType;
import com.hrong.major.model.Log;
import com.hrong.major.model.vo.SearchVo;
import com.hrong.major.service.LogService;
import com.hrong.major.utils.RequestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author hrong
 **/
@Controller
public class IndexController {

	@Resource
	private LogService logService;

	@ClickLog(type = ClickType.subject)
	@GetMapping("/")
	public String index(Model model, HttpServletRequest request) {
		int isFirstVisit = logService.count(new QueryWrapper<Log>().eq("ip", RequestUtils.getIpAddress(request)));
		if (isFirstVisit == 0) {
			return "about/about";
		}
		model.addAttribute("subjects", CacheConstant.subjects);
		model.addAttribute("searchVo", new SearchVo());
		return "index";
	}

	@ClickLog(type = ClickType.about)
	@GetMapping("/about")
	public String about() {
		return "about/about";
	}

	@ClickLog(type = ClickType.contact)
	@GetMapping("/contact")
	public String contact() {
		return "contact/contact";
	}

}