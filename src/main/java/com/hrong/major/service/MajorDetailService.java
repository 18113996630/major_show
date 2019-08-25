package com.hrong.major.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hrong.major.model.MajorDetail;
import com.hrong.major.model.vo.MajorDetailVoWithSubject;
import com.hrong.major.model.vo.MajorDetailWithVideoVo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hrong
 * @since 2019-08-17
 */
public interface MajorDetailService extends IService<MajorDetail> {
	/**
	 * 根据id获取专业详情、包含视频数据、点赞、踩、观看情况
	 * @param id 指定专业id
	 * @param ip ip地址
	 * @return vo
	 */
	MajorDetailWithVideoVo findDetailVoById(Serializable id, String ip);

	/**
	 * 根据当前专业明细获取排序后的下一个专业详情id
	 * @param id 当前专业详情的id
	 * @return nextDetailId
	 */
	Integer findNextMajorDetailIdByCurrentMajorDetailId(Serializable id);

	/**
	 * 根据专业名字和类别查询
	 * @param page 分页
	 * @param majorName 专业名字
	 * @param subjectName 种类
	 * @return vo
	 */
	List<MajorDetailVoWithSubject> findMajorsDetailByNameAndSubjectName(Page page, String majorName, String subjectName);
	/**
	 * 根据专业名字和类别查询数量
	 * @param majorName 专业名字
	 * @param subjectName 种类
	 * @return vo
	 */
	int countMajorsDetailByNameAndSubjectName(String majorName, String subjectName);
}
