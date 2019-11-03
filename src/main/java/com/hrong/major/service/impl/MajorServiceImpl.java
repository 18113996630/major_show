package com.hrong.major.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrong.major.constant.CacheConstant;
import com.hrong.major.dao.MajorMapper;
import com.hrong.major.model.Major;
import com.hrong.major.model.Subject;
import com.hrong.major.model.vo.MajorVo;
import com.hrong.major.model.vo.MajorVoWithSubjectName;
import com.hrong.major.service.MajorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	@Resource
	private CacheConstant cacheConstant;

	@Override
	public List<Major> findAroundMajors(Integer id) {
		Major major = majorMapper.selectById(id);
		List<Integer> ids = majorMapper.findAroundMajorIds(id, major.getSubjectId());
		return majorMapper.selectList(new QueryWrapper<Major>().in("id", ids));
	}

	@Override
	public List<MajorVo> findMajorsByName(String name) {
		List<Major> majors = majorMapper.selectList(new QueryWrapper<Major>().like("name", name).last("order by order_number"));
		List<MajorVo> majorVos = new ArrayList<>(majors.size());
		List<Subject> subjects = cacheConstant.subjects();
		for (Major major : majors) {
			MajorVo majorVo = MajorVo.builder().major(major).build();
			Optional<Subject> first = subjects.stream().filter(item -> item.getId().equals(major.getSubjectId())).findFirst();
			first.ifPresent(majorVo::setSubject);
			majorVos.add(majorVo);
		}
		return majorVos;
	}

	@Override
	public List<MajorVoWithSubjectName> findMajorsByNameAndSubjectName(Page page, String majorName, Integer subjectId) {
		return majorMapper.findMajorsByNameAndSubjectName(page, majorName, subjectId);
	}

	@Override
	public int countMajorsByNameAndSubjectName(String majorName, Integer subjectId) {
		return majorMapper.countMajorsByNameAndSubjectName(majorName, subjectId);
	}
}
