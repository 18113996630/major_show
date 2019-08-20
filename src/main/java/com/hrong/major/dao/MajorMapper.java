package com.hrong.major.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hrong.major.model.Major;
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
							"SELECT `id` from `major` where `id`>=#{id} and `subject_id`=#{subjectId} order by `order_number` limit 3) b" +
					") c order by c.id")
	List<Integer> findAroundMajorIds(@Param(value = "id") Integer id, @Param(value = "subject_id") Integer subjectId);
}
