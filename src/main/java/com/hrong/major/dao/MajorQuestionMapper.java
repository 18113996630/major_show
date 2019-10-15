package com.hrong.major.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrong.major.model.MajorQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hrong
 * @since 2019-10-11
 */
public interface MajorQuestionMapper extends BaseMapper<MajorQuestion> {
	/**
	 * 分页显示数据
	 *
	 * @param page      分页参数
	 * @param majorName 专业名字
	 * @return datas
	 */
	List<MajorQuestion> findQuestionsPageData(@Param("page") Page page, @Param("majorName") String majorName);

	/**
	 * 分页数据计数
	 * @param majorName 专业名字
	 * @return datas
	 */
	Integer countQuestionsPageData(@Param("majorName") String majorName);
}
