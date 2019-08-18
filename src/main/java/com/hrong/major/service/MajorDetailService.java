package com.hrong.major.service;
import	java.io.Serializable;

import com.hrong.major.model.MajorDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hrong.major.model.vo.MajorDetailWithVideoVo;

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
	 * 根据id获取专业详情、包含视频数据
	 * @param id 指定专业id
	 * @return vo
	 */
	MajorDetailWithVideoVo findDetailVoById(Serializable id);

	/**
	 * 根据当前专业明细获取排序后的下一个专业详情id
	 * @param id 当前专业详情的id
	 * @return nextDetailId
	 */
	Integer findNextMajorDetailIdByCurrentMajorDetailId(Serializable id);
}
