package com.hrong.major.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrong.major.dao.MajorDetailMapper;
import com.hrong.major.dao.VideoMapper;
import com.hrong.major.model.MajorDetail;
import com.hrong.major.model.Video;
import com.hrong.major.model.vo.Author;
import com.hrong.major.model.vo.Result;
import com.hrong.major.model.vo.TopVideoAuthorVo;
import com.hrong.major.model.vo.VideoVoWithMajorName;
import com.hrong.major.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	private MajorDetailMapper majorDetailMapper;
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

	@Override
	public List<Author> findAllAuthors(Page page, String upName, String isAuth) {
		return videoMapper.findAllAuthors(page, upName, isAuth);
	}

	@Override
	public int countAllAuthors(String upName, String isAuth) {
		return videoMapper.countAllAuthors(upName, isAuth);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result saveOrUpdateVideo(Video video) {
		try {
			if (video.getId() == null) {
				Integer majorDetailId = majorDetailMapper.selectOne(new QueryWrapper<MajorDetail>().select("id").eq("major_id", video.getMajorId())).getId();
				List<Video> videos = list(new QueryWrapper<Video>().eq("up_name", video.getUpName()));
				if (videos.size() != 0) {
					//将已有数据填充到当前数据
					Video dbVideo = videos.get(0);
					video.setUpId(dbVideo.getUpId());
					video.setUpPage(dbVideo.getUpPage());
					video.setUpFace(dbVideo.getUpFace());
					video.setFaceName(dbVideo.getFaceName());
				}else {
					video.setUpId(Integer.valueOf(video.getUpPage().split("com/")[1]));
				}
				video.setMajorDetailId(majorDetailId);
				saveOrUpdate(video);
			}else {
				//更新major_detail_id
				Integer majorId = video.getMajorId();
				Integer majorDetailId = majorDetailMapper.selectOne(new QueryWrapper<MajorDetail>().eq("major_id", majorId)).getId();
				video.setMajorDetailId(majorDetailId);
				saveOrUpdate(video);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return Result.err(500, String.format("后台保存出错:%s", e.getMessage()));
		}
		return Result.success("操作成功");
	}
}
