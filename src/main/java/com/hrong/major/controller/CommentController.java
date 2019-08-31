package com.hrong.major.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.annotation.ClickLog;
import com.hrong.major.model.ClickType;
import com.hrong.major.model.Comment;
import com.hrong.major.model.CommentFeedback;
import com.hrong.major.model.vo.Result;
import com.hrong.major.service.CommentFeedbackService;
import com.hrong.major.service.CommentService;
import com.hrong.major.utils.RequestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hrong
 * @since 2019-08-27
 */
@Controller
public class CommentController {
	@Resource
	private CommentService commentService;
	@Resource
	private CommentFeedbackService feedbackService;

	@ResponseBody
	@PostMapping(value = "/comment")
	@ClickLog(type = ClickType.comment)
	public Object addComment(HttpServletRequest request,@RequestBody Comment comment) {
		String ip = RequestUtils.getIpAddress(request);
		int count = commentService.count(new QueryWrapper<Comment>().eq("major_detail_id", comment.getMajorDetailId()).eq("ip", ip));
		int maxCommentOfOneDetail = 3;
		if (count >= maxCommentOfOneDetail){
			return Result.err(500, "同一个专业提交的评论数不能超过三条哦");
		}
		//查看是否有相同内容评论
		int isSame = commentService.count(new QueryWrapper<Comment>().eq("major_detail_id", comment.getMajorDetailId()).eq("content", comment.getContent()));
		if (isSame != 0) {
			return Result.err(500, "已经有该评论啦~快去给你的知音点个赞~");
		}
		comment.setIp(ip);
		commentService.save(comment);
		return Result.success("提交成功");
	}

	@ResponseBody
	@PostMapping(value = "/comment/{id}")
	@ClickLog(type = ClickType.comment_up)
	public Object commentUp(HttpServletRequest request, @PathVariable int id) {
		String ip = RequestUtils.getIpAddress(request);
		int upCount = feedbackService.count(new QueryWrapper<CommentFeedback>().eq("comment_id", id).eq("ip", ip));
		if (upCount > 0) {
			return Result.err(500, "您已经点过赞啦~");
		}
		CommentFeedback feedback = CommentFeedback.builder().ip(ip).commentId(id).build();
		feedbackService.save(feedback);
		//更新点赞数量
		Comment comment = commentService.getById(id);
		comment.setUpCount(comment.getUpCount() + 1);
		commentService.updateById(comment);
		return Result.success("点赞成功");
	}
}

