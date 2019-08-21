package com.hrong.major.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrong.major.model.Major;
import com.hrong.major.model.vo.MajorVo;

import java.util.List;

/**
 * <p>
 * 专业 服务类
 * </p>
 *
 * @author hrong
 * @since 2019-08-13
 */
public interface MajorService extends IService<Major> {
	/**
	 * 找到根据排序号排序后当前专业的前后五个专业
	 * @param id 当前专业的id
	 * @return 专业list
	 */
	List<Major> findAroundMajors(Integer id);

	/**
	 * 模糊查询majors
	 * @param name 输入的name
	 * @return majorVo
	 */
	List<MajorVo> findMajorsByName(String name);
}
