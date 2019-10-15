package com.hrong.major.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hrong.major.model.MajorQuestion;
import com.hrong.major.model.vo.MajorQuestionVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hrong
 * @since 2019-10-11
 */
public interface MajorQuestionService extends IService<MajorQuestion> {
	/**
	 * 根据专业id查找专业相关的问题
	 * @param id 专业id
	 * @return vos
	 */
	List<MajorQuestionVo> findQuestionsByMajorId(int id);

	/**
	 * 根据问题id获取下一个问题的id
	 * @param id 当前问题的id
	 * @return 下一个问题的id
	 */
	Integer findNextMajorQuestion(int id);
	/**
	 * 分页显示数据
	 * @param page 分页参数
	 * @param majorName 专业名字
	 * @return datas
	 */
	List<MajorQuestion> findQuestionsPageData(Page page, String majorName);
	/**
	 * 分页数据计数
	 * @param majorName 专业名字
	 * @return datas
	 */
	Integer countQuestionsPageData(String majorName);
}
