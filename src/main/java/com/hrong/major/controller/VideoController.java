package com.hrong.major.controller;


import com.hrong.major.annotation.ClickLog;
import com.hrong.major.model.ClickType;
import com.hrong.major.model.vo.TopVideoAuthorVo;
import com.hrong.major.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hrong
 * @since 2019-08-17
 */
@Controller
public class VideoController {

	@Resource
	private VideoService videoService;

	@ClickLog(type = ClickType.top)
	@GetMapping(value = "/video")
	public String video(Model model){
		//展示视频贡献排行
		List<TopVideoAuthorVo> topTenVideoAuthor = videoService.findTopTenVideoAuthor();
		model.addAttribute("data", topTenVideoAuthor);
		return "top/top";
	}

	@GetMapping(value = "/videos")
	public Object getAllVideos(@RequestParam int page, @RequestParam int limit, @RequestParam String name, @RequestParam String author){
		int count = videoService.count();
		return null;
	}
}

