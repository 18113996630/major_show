package com.hrong.major.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrong.major.model.MajorDetail;
import com.hrong.major.model.vo.MajorDetailVoWithSubject;
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
public interface MajorDetailMapper extends BaseMapper<MajorDetail> {

	/**
	 * 根据专业名字和类别查询
	 * @param page        分页
	 * @param majorName   专业名字
	 * @param subjectName 种类
	 * @return vo
	 */
	@Select(value = "select t.*, s.name as subjectName from major_detail t " +
											"left join major m on t.major_id=m.id " +
											"left join subject s on m.subject_id=s.id " +
										"where t.name like #{majorName} " +
											"and s.name like #{subjectName}")
	List<MajorDetailVoWithSubject> findMajorsDetailByNameAndSubjectName(@Param("page") Page page,
																		@Param("majorName") String majorName,
																		@Param("subjectName") String subjectName);

	/**
	 * 根据专业名字和类别查询数量
	 *
	 * @param majorName   专业名字
	 * @param subjectName 种类
	 * @return vo
	 */
	@Select(value = "select count(1) from major_detail t " +
										"left join major m on t.major_id=m.id " +
										"left join subject s on m.subject_id=s.id " +
									"where t.name like #{majorName} " +
										"and s.name like #{subjectName}")
	int countMajorsDetailByNameAndSubjectName(@Param("majorName") String majorName, @Param("subjectName") String subjectName);
}
