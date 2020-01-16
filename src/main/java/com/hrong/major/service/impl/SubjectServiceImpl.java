package com.hrong.major.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrong.major.constant.CacheConstant;
import com.hrong.major.dao.SubjectMapper;
import com.hrong.major.model.Subject;
import com.hrong.major.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 学科分类 服务实现类
 * </p>
 *
 * @author hrong
 * @since 2019-08-13
 */
@Slf4j
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService, Serializable {
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	@Override
	public Boolean resetRedisSubjects() {
		log.info("数据库中subjects已变更，开始更新redis中的数据");
		List<Subject> subjects = list(new QueryWrapper<Subject>().eq("deleted", 0).orderByAsc("order_number"));
		Boolean setResult = stringRedisTemplate.opsForValue().setIfPresent(CacheConstant.REDIS_SUBJECTS, JSONArray.toJSONString(subjects));
		log.info("更新结果：{}", setResult);
		return setResult;
	}
}
