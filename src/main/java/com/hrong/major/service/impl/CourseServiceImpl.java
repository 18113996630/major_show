package com.hrong.major.service.impl;

import com.hrong.major.model.Course;
import com.hrong.major.dao.CourseMapper;
import com.hrong.major.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hrong
 * @since 2019-08-13
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

}
