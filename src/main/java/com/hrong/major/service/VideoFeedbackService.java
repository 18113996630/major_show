package com.hrong.major.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrong.major.model.Video;
import com.hrong.major.model.VideoFeedback;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hrong
 * @since 2019-08-21
 */
public interface VideoFeedbackService extends IService<VideoFeedback> {
	/**
	 * 根据ip地址与详情页的视频list查找当前用户的点赞、踩情况
	 * @param ip ip地址
	 * @param videos 详情页videos
	 * @return 点赞、踩统计
	 */
	List<VideoFeedback> findFeedbackConditionByIp(String ip, List<Video> videos);
}
