package com.hrong.major.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrong.major.model.Major;
import com.hrong.major.model.vo.MajorVo;
import com.hrong.major.model.vo.MajorVoWithSubjectName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 专业 Mapper 接口
 * </p>
 *
 * @author hrong
 * @since 2019-08-13
 */
public interface MajorMapper extends BaseMapper<Major> {

	/**
	 * 根据当前major查询前后五条major
	 * @param id majorId
	 * @param subjectId 所属subjectId
	 * @return ids
	 */
	@Select(value = "select c.id from (" +
						"select a.id from (" +
							"SELECT `id` from `major` where `id`<#{id} and `subject_id`=#{subjectId} order by `order_number` desc limit 3) a " +
						"union " +
						"select * from (" +
							"SELECT `id` from `major` where `id`>=#{id} and `subject_id`=#{subjectId} order by `order_number` limit 4) b" +
					") c order by c.id")
	List<Integer> findAroundMajorIds(@Param(value = "id") Integer id, @Param(value = "subjectId") Integer subjectId);

	/**
	 * 根据专业名字模糊查询专业
	 * @param name 输入
	 * @return MajorVo
	 */
	@Select(value = "select t.*,s.* from major t left join subject s on t.subject_id = s.id where t.name like #{name} order by t.order_number")
	List<MajorVo> findMajorsByName(@Param(value = "name") String name);

	/**
	 * 根据专业名字和类别查询
	 * @param page 分页
	 * @param majorName 专业名字
	 * @param subjectId 种类id
	 * @return vo
	 */
	List<MajorVoWithSubjectName> findMajorsByNameAndSubjectName(Page page, @Param(value = "majorName") String majorName, @Param(value = "subjectId") Integer subjectId);


	/**
	 * 根据专业名字和类别查询数量
	 * @param majorName 专业名字
	 * @param subjectId 种类id
	 * @return vo
	 */
	int countMajorsByNameAndSubjectName(@Param(value = "majorName") String majorName, @Param(value = "subjectId") Integer subjectId);
}
