package com.hrong.major.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hrong.major.model.Video;
import com.hrong.major.model.vo.TopVideoAuthorVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hrong
 * @since 2019-08-17
 */
public interface VideoMapper extends BaseMapper<Video> {

	/**
	 * 前十贡献视频者
	 * @return 视频作者信息
	 */
	@Select(value = "select * from vw_video_author_top order by count desc")
	List<TopVideoAuthorVo> findTopTenVideoAuthor();
}
