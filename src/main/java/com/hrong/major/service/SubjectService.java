package com.hrong.major.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrong.major.model.Subject;

/**
 * <p>
 * 学科分类 服务类
 * </p>
 *
 * @author hrong
 * @since 2019-08-13
 */
public interface SubjectService extends IService<Subject> {
	Boolean resetRedisSubjects();
}
