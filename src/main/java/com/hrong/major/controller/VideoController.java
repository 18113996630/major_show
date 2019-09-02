package com.hrong.major.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.annotation.ClickLog;
import com.hrong.major.model.ClickType;
import com.hrong.major.model.MajorDetail;
import com.hrong.major.model.Video;
import com.hrong.major.model.vo.TopVideoAuthorVo;
import com.hrong.major.service.MajorDetailService;
import com.hrong.major.service.MajorService;
import com.hrong.major.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
@Slf4j
@Controller
public class VideoController {

	@Resource
	private VideoService videoService;
	@Resource
	private MajorDetailService majorDetailService;
	@Resource
	private MajorService majorService;

	@ClickLog(type = ClickType.top)
	@GetMapping(value = "/top")
	public String video(Model model){
		log.info("查看视频排行榜");
		//展示视频贡献排行
		List<TopVideoAuthorVo> topTenVideoAuthor = videoService.findTopTenVideoAuthor();
		model.addAttribute("data", topTenVideoAuthor);
		return "top/top";
	}

	@PostMapping(value = "/video/contribute")
	public Object uploadVideoInfo(Model model, @ModelAttribute Video video){
		log.info("投稿：{}", JSON.toJSONString(video));
		MajorDetail majorDetail = majorDetailService.getOne(new QueryWrapper<MajorDetail>().eq("major_id", video.getMajorId()));
		video.setMajorDetailId(majorDetail.getId());
		video.setDeleted(2);
		video.setIsAuth(1);
		videoService.save(video);
		model.addAttribute("majors", majorService.list());
		model.addAttribute("msg", "提交成功~我会尽快审核的~");
		model.addAttribute("video", new Video());
		return "contribute/contribute";
	}
}

