package com.hrong.major.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.model.Major;
import com.hrong.major.dao.MajorMapper;
import com.hrong.major.service.MajorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 专业 服务实现类
 * </p>
 *
 * @author hrong
 * @since 2019-08-13
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService {

	@Resource
	private MajorMapper majorMapper;
	@Override
	public List<Major> findAroundMajors(Integer id) {
		Major major = majorMapper.selectById(id);
		List<Integer> ids = majorMapper.findAroundMajorIds(id, major.getSubjectId());
		return majorMapper.selectList(new QueryWrapper<Major>().in("id", ids));
	}
}
