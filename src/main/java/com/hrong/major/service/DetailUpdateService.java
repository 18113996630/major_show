package com.hrong.major.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hrong.major.model.DetailUpdate;
import com.hrong.major.model.vo.DetailUpdateVo;
import com.hrong.major.model.vo.MajorDetailVoWithSubject;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hrong
 * @since 2019-09-03
 */
public interface DetailUpdateService extends IService<DetailUpdate> {

	/**
	 * 根据专业名字和状态查询
	 * @param page 分页
	 * @param majorDetailId 专业id
	 * @param status 状态
	 * @return vo
	 */
	List<DetailUpdateVo> findMajorsDetailUpdateByMajorNameAndStatus(Page page, String majorDetailId, String status);
	/**
	 * 根据专业名字和状态查询数量
	 * @param majorDetailId 专业id
	 * @param status 状态
	 * @return vo
	 */
	int countMajorsDetailUpdateByMajorNameAndStatus(String majorDetailId, String status);

	/**
	 * 根据专id查询
	 * @param id id
	 * @return vo
	 */
	DetailUpdateVo findMajorsDetailUpdateById(int id);

	/**
	 * 根据id同意某修改请求
	 * @param id id
	 * @return 修改结果
	 */
	Object agreeUpdate(int id);

	/**
	 * 添加修改信息
	 * @param detailUpdate bean
	 * @return 修改结果
	 */
	Object addUpdateInfo(String ip, DetailUpdate detailUpdate);
}
