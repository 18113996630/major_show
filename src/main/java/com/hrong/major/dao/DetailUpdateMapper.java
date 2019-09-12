package com.hrong.major.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrong.major.model.DetailUpdate;
import com.hrong.major.model.vo.DetailUpdateVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hrong
 * @since 2019-09-03
 */
public interface DetailUpdateMapper extends BaseMapper<DetailUpdate> {

	/**
	 * 根据专业名字和状态查询
	 *
	 * @param page      分页
	 * @param majorDetailId 专业id
	 * @param status    状态
	 * @return vo
	 */
	List<DetailUpdateVo> findMajorsDetailUpdateByMajorNameAndStatus(@Param("page") Page page, @Param("majorDetailId") String majorDetailId, @Param("status") String status);

	/**
	 * 根据专业名字和状态查询数量
	 *
	 * @param majorDetailId 专业id
	 * @param status    状态
	 * @return vo
	 */
	int countMajorsDetailUpdateByMajorNameAndStatus(@Param("majorDetailId") String majorDetailId, @Param("status") String status);

	/**
	 * 根据专id查询
	 *
	 * @param id id
	 * @return vo
	 */
	DetailUpdateVo findMajorsDetailUpdateById(@Param("id") int id);
}
