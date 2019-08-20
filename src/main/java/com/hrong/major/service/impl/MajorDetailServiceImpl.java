package com.hrong.major.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrong.major.dao.MajorDetailMapper;
import com.hrong.major.dao.MajorMapper;
import com.hrong.major.dao.VideoMapper;
import com.hrong.major.model.Major;
import com.hrong.major.model.MajorDetail;
import com.hrong.major.model.Video;
import com.hrong.major.model.vo.MajorDetailWithVideoVo;
import com.hrong.major.service.MajorDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hrong
 * @since 2019-08-17
 */
@Slf4j
@Service
public class MajorDetailServiceImpl extends ServiceImpl<MajorDetailMapper, MajorDetail> implements MajorDetailService {

	@Resource
	private MajorDetailMapper majorDetailMapper;
	@Resource
	private VideoMapper videoMapper;
	@Resource
	private MajorMapper majorMapper;

	@Override
	public MajorDetailWithVideoVo findDetailVoById(Serializable id) {
		MajorDetail detail = majorDetailMapper.selectOne(new QueryWrapper<MajorDetail>().eq("major_id", id));
		if (detail == null) {
			return null;
		}
		List<Video> videos = videoMapper.selectList(new QueryWrapper<Video>().eq("major_detail_id", detail.getId()).orderByAsc("order_number"));
		return MajorDetailWithVideoVo.builder().detail(detail).videos(videos).build();
	}

	@Override
	public Integer findNextMajorDetailIdByCurrentMajorDetailId(Serializable id) {
		MajorDetail currentMajorDetail = majorDetailMapper.selectById(id);
		//当前major
		Major currentMajor = majorMapper.selectById(currentMajorDetail.getId());
		//根据order_number排序后处于当前major后面的major
		Major nextMajor = majorMapper.selectOne(new QueryWrapper<Major>()
				.select("id", "name")
				.eq("subject_id", currentMajor.getSubjectId())
				.gt("order_number", currentMajor.getOrderNumber())
				.orderByAsc("order_number")
				.last("limit 1")
		);
		if (nextMajor == null) {
			log.info("{}已经是该类别下的最后一个专业", currentMajor.getName());
			return 0;
		}
		log.info("{}下一个专业为：{}", currentMajor.getName(), nextMajor.getName());
		MajorDetail nextMajorDetail = majorDetailMapper.selectOne(new QueryWrapper<MajorDetail>().eq("major_id", nextMajor.getId()));
		log.info("是否发现该专业的明细：{}", nextMajorDetail == null);
		return nextMajorDetail == null ? 0 : nextMajorDetail.getId();
	}
}
