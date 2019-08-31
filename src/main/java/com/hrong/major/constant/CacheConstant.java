package com.hrong.major.constant;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.model.Configuration;
import com.hrong.major.model.Subject;
import com.hrong.major.service.ConfigurationService;
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
	public static Configuration conf;
	private static SubjectService subjectServiceStatic;
	private static ConfigurationService configurationServiceStatic;
	@Resource
	private SubjectService subjectService;
	@Resource
	private ConfigurationService configurationService;

	@PostConstruct
	public void init() {
		subjectServiceStatic = subjectService;
		configurationServiceStatic = configurationService;
		subjects = subjectServiceStatic.list(new QueryWrapper<Subject>().orderByAsc("order_number"));
		conf = configurationServiceStatic.list().get(0);
	}
}
