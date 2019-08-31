package com.hrong.major.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrong.major.dao.VideoNeedsMapper;
import com.hrong.major.model.VideoNeeds;
import com.hrong.major.model.vo.VideoNeedsVo;
import com.hrong.major.service.VideoNeedsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hrong
 * @since 2019-08-21
 */
@Service
public class VideoNeedsServiceImpl extends ServiceImpl<VideoNeedsMapper, VideoNeeds> implements VideoNeedsService {

	@Resource
	private VideoNeedsMapper mapper;
	@Override
	public List<VideoNeedsVo> findVideoNeeds(Page page) {
		return mapper.findVideoNeeds(page);
	}
}
