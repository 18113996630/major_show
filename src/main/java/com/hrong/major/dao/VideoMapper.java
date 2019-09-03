package com.hrong.major.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrong.major.model.Video;
import com.hrong.major.model.vo.Author;
import com.hrong.major.model.vo.TopVideoAuthorVo;
import com.hrong.major.model.vo.VideoVoWithMajorName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hrong
 * @since 2019-08-17
 */
public interface VideoMapper extends BaseMapper<Video> {

	/**
	 * 前十贡献视频者
	 *
	 * @return 视频作者信息
	 */
	@Select(value = "select * from vw_video_author_top order by count desc")
	List<TopVideoAuthorVo> findTopTenVideoAuthor();

	/**
	 * 视频分页查询方法
	 *
	 * @param page      分页参数
	 * @param videoName 视频名字
	 * @param majorName 专业名字
	 * @param upName    up-name
	 * @param isAuth    是否授权
	 * @return vos
	 */
	@Select(value = "SELECT t.*, m.NAME AS majorName FROM video t " +
			"LEFT JOIN major_detail d ON t.major_detail_id = d.id " +
			"LEFT JOIN major m ON d.major_id = m.id " +
			"WHERE t.title LIKE #{videoName} " +
			"and t.up_name like #{upName} " +
			"and t.is_auth like #{isAuth} " +
			"and m.name like #{majorName}")
	List<VideoVoWithMajorName> findVideosByNameAndSubjectName(@Param("page") Page page, @Param("videoName") String videoName,
															  @Param("majorName") String majorName,
															  @Param("upName") String upName,
															  @Param("isAuth") String isAuth);

	/**
	 * 视频分页count
	 *
	 * @param videoName 视频名字
	 * @param majorName 专业名字
	 * @param upName    up-name
	 * @param isAuth    是否授权
	 * @return vos
	 */
	@Select(value = "SELECT count(1) FROM video t LEFT JOIN major_detail d ON t.major_detail_id = d.id LEFT JOIN major m ON d.major_id = m.id WHERE t.title LIKE #{videoName} and t.up_name like #{upName} and t.is_auth like #{isAuth} and m.name like #{majorName}")
	int countVideosByNameAndSubjectName(@Param("videoName") String videoName,
										@Param("majorName") String majorName,
										@Param("upName") String upName,
										@Param("isAuth") String isAuth);

	/**
	 * 查找up
	 *
	 * @param page   分页参数
	 * @param upName up名字
	 * @param isAuth 是否授权
	 * @return ups
	 */
	List<Author> findAllAuthors(@Param("page") Page page, @Param("upName") String upName, @Param("isAuth") String isAuth);

	/**
	 * up计数
	 *
	 * @param upName up名字
	 * @param isAuth 是否授权
	 * @return ups
	 */
	int countAllAuthors(@Param("upName") String upName, @Param("isAuth") String isAuth);

}
