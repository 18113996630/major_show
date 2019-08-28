package com.hrong.major.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrong.major.dao.CommentFeedbackMapper;
import com.hrong.major.dao.CommentMapper;
import com.hrong.major.model.Comment;
import com.hrong.major.model.CommentFeedback;
import com.hrong.major.model.vo.CommentVo;
import com.hrong.major.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hrong
 * @since 2019-08-27
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

	@Resource
	private CommentFeedbackMapper feedbackMapper;

	@Override
	public List<CommentVo> findCommentsByDetailId(int detailId, String ip) {
		List<Comment> comments = list(new QueryWrapper<Comment>().eq("major_detail_id", detailId).orderByDesc("up_count"));
		List<CommentVo> results = new ArrayList<>(comments.size());
		for (Comment comment : comments) {
			CommentVo commentVo = new CommentVo();
			Integer isUp = feedbackMapper.selectCount(new QueryWrapper<CommentFeedback>().eq("comment_id", comment.getId()).eq("ip", ip));
			BeanUtils.copyProperties(comment, commentVo);
			commentVo.setIsUp(isUp);
			results.add(commentVo);
		}
		return results;
	}
}
