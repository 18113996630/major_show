package com.hrong.major.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hrong.major.model.Video;
import com.hrong.major.model.vo.Author;
import com.hrong.major.model.vo.Result;
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

	/**
	 * 根据视频名字、专业名字、up名字、是否授权查询
	 * @param page 分页参数
	 * @param videoName 视频名字
	 * @param majorName 专业名字
	 * @param upName up名字
	 * @param isAuth 是否授权
	 * @return 视频信息
	 */
	List<VideoVoWithMajorName> findVideosByNameAndSubjectName(Page page, String videoName, String majorName, String upName, String isAuth);
	/**
	 * 根据视频名字、专业名字、up名字、是否授权计数
	 * @param videoName 视频名字
	 * @param majorName 专业名字
	 * @param upName up名字
	 * @param isAuth 是否授权
	 * @return 视频信息
	 */
	int countVideosByNameAndSubjectName(String videoName, String majorName, String upName, String isAuth);
	/**
	 * 查找up
	 * @param page 分页参数
	 * @param upName up名字
	 * @param isAuth 是否授权
	 * @return ups
	 */
	List<Author> findAllAuthors(Page page, String upName, String isAuth);
	/**
	 * up计数
	 * @param upName up名字
	 * @param isAuth 是否授权
	 * @return ups
	 */
	int countAllAuthors(String upName, String isAuth);

	/**
	 * 更新或者保存视频信息
	 * @param video 视频
	 * @return 操作结果
	 */
	Result saveOrUpdateVideo(Video video);
}
