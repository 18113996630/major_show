package com.hrong.major.constant;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.model.Subject;
import com.hrong.major.service.SubjectService;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Author hrong
 **/
@Data
@Component
public class CacheConstant {
	public static List<Subject> subjects;
	private static SubjectService service;
	@Resource
	private SubjectService subjectService;

	@PostConstruct
	public void init() {
		service = subjectService;
		subjects = service.list(new QueryWrapper<Subject>().orderByAsc("order_number"));
	}
}
