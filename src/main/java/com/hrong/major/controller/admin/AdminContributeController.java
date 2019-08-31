package com.hrong.major.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.model.Video;
import com.hrong.major.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author hrong
 **/
@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class AdminContributeController {

	@Resource
	private VideoService videoService;

	/**
	 * 表格初始化
	 */
	@ResponseBody
	@GetMapping("/contribute/video")
	public Map<String, Object> getVideos() {
		Map<String, Object> res = new HashMap<>(2);
		List<Video> videos = videoService.list(new QueryWrapper<Video>().eq("deleted", 2));
		res.put("total", videos.size());
		res.put("rows", videos);
		return res;
	}
}
