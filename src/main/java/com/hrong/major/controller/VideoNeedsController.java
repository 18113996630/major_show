package com.hrong.major.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.annotation.ClickLog;
import com.hrong.major.model.ClickType;
import com.hrong.major.model.MajorDetail;
import com.hrong.major.model.VideoNeeds;
import com.hrong.major.model.vo.Result;
import com.hrong.major.service.MajorDetailService;
import com.hrong.major.service.VideoNeedsService;
import com.hrong.major.utils.IpUtils;
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
	@Resource
	private MajorDetailService majorDetailService;
	/**
	 * major视频申请
	 * @param id majorId
	 * @return 申请结果
	 */
	@ResponseBody
	@ClickLog(type = ClickType.video_need)
	@PostMapping(value = "/{id}")
	public Object needVideo(HttpServletRequest request, @PathVariable(value = "id")int id){
		String ip = RequestUtils.getIp(request);
		MajorDetail detail = majorDetailService.getById(id);
		log.info("ip:{}想看{}专业的视频", ip, detail.getName());
		String address = IpUtils.getCity(ip);
		VideoNeeds videoNeeds = VideoNeeds.builder().ip(ip).address(address).count(1).majorId(detail.getId()).build();
		videoNeedsService.save(videoNeeds);
		//查询申请该major-video的人数
		int totalCount = videoNeedsService.count(new QueryWrapper<VideoNeeds>().eq("major_id", id));
		log.info("id为{}的专业，共有{}个人想看", id, totalCount);
		return Result.success(totalCount);
	}
}

