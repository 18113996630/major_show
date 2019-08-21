package com.hrong.major.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.model.VideoNeeds;
import com.hrong.major.model.vo.Result;
import com.hrong.major.service.VideoNeedsService;
import com.hrong.major.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hrong
 * @since 2019-08-21
 */
@Slf4j
@Controller
@RequestMapping("/video/need")
public class VideoNeedsController {
	@Resource
	private VideoNeedsService videoNeedsService;

	/**
	 * major视频申请
	 * @param id majorId
	 * @return 申请结果
	 */
	@ResponseBody
	@PostMapping(value = "/{id}")
	public Object needVideo(HttpServletRequest request, @PathVariable(value = "id")int id){
		String ipAddress = RequestUtils.getIpAddress(request);
		VideoNeeds videoNeeds = VideoNeeds.builder().ip(ipAddress).count(1).majorId(id).build();
		videoNeedsService.save(videoNeeds);
		//查询申请该major-video的人数
		int totalCount = videoNeedsService.count(new QueryWrapper<VideoNeeds>().eq("major_id", id));
		return Result.success(totalCount);
	}
}

