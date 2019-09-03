package com.hrong.major.model;

/**
 * @author 1011486768
 */
public enum ClickType {
	/**
	 * 专业类别
	 */
	subject,
	/**
	 * 搜索查看专业
	 */
	majors,
	/**
	 * 专业详情
	 */
	major,
	/**
	 * 视频
	 */
	video,
	/**
	 * 踩视频
	 */
	video_down,
	/**
	 * 点赞视频
	 */
	video_up,
	/**
	 * 联系我
	 */
	contact,
	/**
	 * 关于
	 */
	about,
	/**
	 * 排行榜
	 */
	top,
	/**
	 * 视频需求
	 */
	video_need,
	/**
	 * 投稿
	 */
	contribute,
	/**
	 * 评论
	 */
	comment,
	/**
	 * 评论点赞
	 */
	comment_up,
	/**
	 * 登录
	 */
	login,
	/**
	 * 注册
	 */
	register,
	/**
	 * 详情修改
	 */
	detail_update,

	ClickType() {
	}
}
