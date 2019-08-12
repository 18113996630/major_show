package com.hrong.major.service;

import com.hrong.major.model.SubjectCategory;

import java.util.List;

/**
 * @Name ISubjectCategoryService
 * @Date 2019/8/12 20:08
 * @Description
 **/
public interface ISubjectCategoryService {
	List<SubjectCategory> findSubjectCategories();
}
