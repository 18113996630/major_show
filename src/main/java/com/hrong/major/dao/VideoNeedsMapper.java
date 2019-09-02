package com.hrong.major.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrong.major.model.VideoNeeds;
import com.hrong.major.model.vo.VideoNeedsVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hrong
 * @since 2019-08-21
 */
public interface VideoNeedsMapper extends BaseMapper<VideoNeeds> {

	/**
	 * 分页查询有效的视频需求
	 *
	 * @param page 分页参数
	 * @return vos
	 */
	@Select(value = "select t.*, m.name as majorName from video_needs t left join major_detail m on t.major_id=m.id where t.status=0 order by t.time desc")
	List<VideoNeedsVo> findVideoNeeds(@Param("page") Page page);

}
