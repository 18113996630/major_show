package com.hrong.major.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrong.major.dao.VideoFeedbackMapper;
import com.hrong.major.model.Video;
import com.hrong.major.model.VideoFeedback;
import com.hrong.major.service.VideoFeedbackService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hrong
 * @since 2019-08-21
 */
@Service
public class VideoFeedbackServiceImpl extends ServiceImpl<VideoFeedbackMapper, VideoFeedback> implements VideoFeedbackService {

	@Override
	public List<VideoFeedback> findFeedbackConditionByIp(String ip, List<Video> videos) {
		return null;
	}
}
