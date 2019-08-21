package com.hrong.major.controller;


import com.alibaba.fastjson.JSON;
import com.hrong.major.annotation.ClickLog;
import com.hrong.major.model.ClickType;
import com.hrong.major.model.VideoFeedback;
import com.hrong.major.model.vo.Result;
import com.hrong.major.service.VideoFeedbackService;
import com.hrong.major.utils.RequestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hrong
 * @since 2019-08-21
 */
@Controller
@RequestMapping("/video/back")
public class VideoFeedbackController {
	@Resource
	private VideoFeedbackService service;

	@ResponseBody
	@ClickLog(type = ClickType.video)
	@PostMapping(value = "/{id}")
	public Object feedBackVideo(HttpServletRequest request, @PathVariable(value = "id") int id, String type) {
		VideoFeedback feedback = VideoFeedback.builder().videoId(id).type(type).count(1).ip(RequestUtils.getIpAddress(request)).build();
		service.save(feedback);
		return JSON.toJSONString(Result.success("成功"));
	}
}

