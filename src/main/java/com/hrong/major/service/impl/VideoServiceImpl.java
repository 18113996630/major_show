package com.hrong.major.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrong.major.dao.VideoMapper;
import com.hrong.major.model.Video;
import com.hrong.major.model.vo.TopVideoAuthorVo;
import com.hrong.major.model.vo.VideoVoWithMajorName;
import com.hrong.major.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hrong
 * @since 2019-08-17
 */
@Slf4j
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

	@Resource
	private VideoMapper videoMapper;

	@Override
	public List<TopVideoAuthorVo> findTopTenVideoAuthor() {
		return videoMapper.findTopTenVideoAuthor();
	}

	@Override
	public List<VideoVoWithMajorName> findVideosByNameAndSubjectName(Page page, String videoName, String majorName, String upName, String isAuth) {
		List<VideoVoWithMajorName> res = videoMapper.findVideosByNameAndSubjectName(page, videoName, majorName, upName, isAuth);
		return res;
	}

	@Override
	public int countVideosByNameAndSubjectName(String videoName, String majorName, String upName, String isAuth) {
		return videoMapper.countVideosByNameAndSubjectName(videoName, majorName, upName, isAuth);
	}
}
