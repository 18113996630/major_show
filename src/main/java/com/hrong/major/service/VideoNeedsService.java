package com.hrong.major.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hrong.major.model.VideoNeeds;
import com.hrong.major.model.vo.VideoNeedsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hrong
 * @since 2019-08-21
 */
public interface VideoNeedsService extends IService<VideoNeeds> {
	/**
	 * 分页查询有效的视频需求
	 * @param page 分页参数
	 * @return vos
	 */
	List<VideoNeedsVo> findVideoNeeds(Page page);
}
