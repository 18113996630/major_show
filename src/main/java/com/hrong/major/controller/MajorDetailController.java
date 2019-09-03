package com.hrong.major.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.annotation.ClickLog;
import com.hrong.major.model.ClickType;
import com.hrong.major.model.DetailUpdate;
import com.hrong.major.model.Major;
import com.hrong.major.model.MajorDetail;
import com.hrong.major.model.Subject;
import com.hrong.major.model.vo.CommentVo;
import com.hrong.major.model.vo.MajorDetailWithVideoVo;
import com.hrong.major.service.CommentService;
import com.hrong.major.service.MajorDetailService;
import com.hrong.major.service.MajorService;
import com.hrong.major.service.SubjectService;
import com.hrong.major.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/major/info")
public class MajorDetailController {

	@Resource
	private MajorDetailService majorDetailService;
	@Resource
	private SubjectService subjectService;
	@Resource
	private MajorService majorService;
	@Resource
	private CommentService commentService;

	/**
	 * 根据专业id查询详情
	 */
	@ClickLog(type = ClickType.major)
	@GetMapping(value = "/{id}")
	public String getMajorInfoById(HttpServletRequest request, Model model, @PathVariable(value = "id") int id) {
		String ip = RequestUtils.getIp(request);
		//当前专业详情
		MajorDetail majorDetail = majorDetailService.getOne(new QueryWrapper<MajorDetail>().eq("major_id", id));

		//文字详情和视频信息
		MajorDetailWithVideoVo detail = majorDetailService.findDetailVoById(majorDetail.getId(), ip);
		//下一个专业的id
		Integer nextDetailId = majorDetailService.findNextMajorDetailIdByCurrentMajorDetailId(id);
		Major currentMajor = majorService.getById(id);
		//当前专业所属类别
		Subject currentSubject = subjectService.getById(currentMajor.getSubjectId());
		//当前专业附近的专业
		List<Major> majors = majorService.findAroundMajors(id);
		//当前专业详情的评论
		List<CommentVo> comments = commentService.findCommentsByDetailId(majorDetail.getId(), RequestUtils.getIp(request));
		log.info("ip:{}查看{}下的{}专业详情，详情id为：{}", ip, currentSubject.getName(), currentMajor.getName(), majorDetail.getId());

		model.addAttribute("detailVo", detail);
		model.addAttribute("nextId", nextDetailId);
		model.addAttribute("currentMajorId", id);
		//从详情查询subject下的majors
		model.addAttribute("currentSubject", currentSubject);
		//显示该专业前后几个专业
		model.addAttribute("majors", majors);
		//评论
		model.addAttribute("comments", comments);
		//评论数量
		model.addAttribute("commentsCount", comments.size());
		return "major/major_detail";
	}
	/**
	 * 根据专业id查询详情
	 */
	@ClickLog(type = ClickType.detail_update)
	@PostMapping
	public String detailUpdate(HttpServletRequest request, DetailUpdate detailUpdate) {
		System.out.println(detailUpdate);
		return null;
	}

}

