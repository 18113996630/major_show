package com.hrong.major.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hrong.major.model.Video;
import com.hrong.major.model.vo.Author;
import com.hrong.major.model.vo.TopVideoAuthorVo;
import com.hrong.major.model.vo.VideoVoWithMajorName;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hrong
 * @since 2019-08-17
 */
public interface VideoService extends IService<Video> {
	/**
	 * 视频前十贡献者
	 * @return vos
	 */
	List<TopVideoAuthorVo> findTopTenVideoAuthor();

	List<VideoVoWithMajorName> findVideosByNameAndSubjectName(Page page, String videoName, String majorName, String upName, String isAuth);
	int countVideosByNameAndSubjectName(String videoName, String majorName, String upName, String isAuth);

	List<Author> findAllAuthors(Page page, String upName);
	int countAllAuthors(String upName);
}
