package com.hrong.major.service.impl;

import com.hrong.major.dao.SubjectCategoryMapper;
import com.hrong.major.model.SubjectCategory;
import com.hrong.major.service.ISubjectCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author hrong
 **/
@Service
public class SubjectCategoryServiceImpl implements ISubjectCategoryService {

	@Resource
	private SubjectCategoryMapper subjectCategoryMapper;

	@Override
	public List<SubjectCategory> findSubjectCategories() {
		return subjectCategoryMapper.selectCategories();
	}
}
