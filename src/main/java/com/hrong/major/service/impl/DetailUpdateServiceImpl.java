package com.hrong.major.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrong.major.dao.DetailUpdateMapper;
import com.hrong.major.dao.MajorDetailMapper;
import com.hrong.major.dao.UserMapper;
import com.hrong.major.model.DetailUpdate;
import com.hrong.major.model.MajorDetail;
import com.hrong.major.model.User;
import com.hrong.major.model.vo.DetailUpdateVo;
import com.hrong.major.model.vo.Result;
import com.hrong.major.service.DetailUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hrong
 * @since 2019-09-03
 */
@Slf4j
@Service
public class DetailUpdateServiceImpl extends ServiceImpl<DetailUpdateMapper, DetailUpdate> implements DetailUpdateService {

	@Resource
	private DetailUpdateMapper detailUpdateMapper;
	@Resource
	private MajorDetailMapper majorDetailMapper;
	@Resource
	private UserMapper userMapper;

	@Override
	public List<DetailUpdateVo> findMajorsDetailUpdateByMajorNameAndStatus(Page page, String majorDetailId, String status) {
		return detailUpdateMapper.findMajorsDetailUpdateByMajorNameAndStatus(page, majorDetailId, status);
	}

	@Override
	public int countMajorsDetailUpdateByMajorNameAndStatus(String majorDetailId, String status) {
		return detailUpdateMapper.countMajorsDetailUpdateByMajorNameAndStatus(majorDetailId, status);
	}

	@Override
	public DetailUpdateVo findMajorsDetailUpdateById(int id) {
		return detailUpdateMapper.findMajorsDetailUpdateById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Object agreeUpdate(int id) {
		DetailUpdate detailUpdate = getById(id);
		String updateContent = detailUpdate.getContent();
		String type = detailUpdate.getType();
		MajorDetail majorDetail = majorDetailMapper.selectById(detailUpdate.getMajorDetailId());
		if (majorDetail != null) {
			if ("description".equalsIgnoreCase(type)) {
				majorDetail.setDescription(updateContent);
			} else if ("course".equalsIgnoreCase(type)) {
				majorDetail.setCourse(updateContent);
			} else if ("job".equalsIgnoreCase(type)) {
				majorDetail.setJob(updateContent);
			}
			majorDetailMapper.updateById(majorDetail);
			detailUpdate.setStatus(1);
			detailUpdateMapper.updateById(detailUpdate);
			log.info("审核通过修改意见: 修改{}专业的{}，修改结果：{}", majorDetail.getName(), type, updateContent);
			return Result.success("修改成功");
		}
		return Result.err(500, String.format("未找到专业详情信息，修改意见：%s", JSONObject.toJSONString(detailUpdate)));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Object addUpdateInfo(String ip, DetailUpdate detailUpdate) {
		Integer userId = userMapper.selectOne(new QueryWrapper<User>().eq("ip", ip)).getId();
		MajorDetail majorDetail = majorDetailMapper.selectById(detailUpdate.getMajorDetailId());
		String contentBefore = "";
		String type = detailUpdate.getType();
		if ("description".equalsIgnoreCase(type)) {
			contentBefore = majorDetail.getDescription();
		} else if ("course".equalsIgnoreCase(type)) {
			contentBefore = majorDetail.getCourse();
		} else if ("job".equalsIgnoreCase(type)) {
			contentBefore = majorDetail.getJob();
		}
		detailUpdate.setUserId(userId);
		detailUpdate.setContentBefore(contentBefore);
		save(detailUpdate);
		log.info("专业详情修改：{}", JSONObject.toJSONString(detailUpdate));
		return Result.success("感谢您的修改意见，我会尽快审核的");
	}
}
