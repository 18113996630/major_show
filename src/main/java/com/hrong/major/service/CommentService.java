package com.hrong.major.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrong.major.model.Comment;
import com.hrong.major.model.vo.CommentVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hrong
 * @since 2019-08-27
 */
public interface CommentService extends IService<Comment> {
	/**
	 * 根据详情页面id与ip查询当前页面评论
	 * @param detailId 详情页面id
	 * @param ip ip地址主要用于查询是否已点赞
	 * @return vos
	 */
	List<CommentVo> findCommentsByDetailId(int detailId, String ip);

}
