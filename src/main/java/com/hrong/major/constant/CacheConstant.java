package com.hrong.major.constant;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.model.Configuration;
import com.hrong.major.model.Subject;
import com.hrong.major.service.ConfigurationService;
import com.hrong.major.service.SubjectService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Author hrong
 **/
@Data
@Slf4j
@Component
public class CacheConstant {
	/**
	 * redis中subjects的key
	 */
	public static final String REDIS_SUBJECTS = "subjects";
	/**
	 * redis中配置数据的key
	 */
	public static final String REDIS_CONF = "conf";
	/**
	 * redis中top10的key
	 */
	public static final String REDIS_TOP = "top";
	@Resource
	private SubjectService subjectService;
	@Resource
	private ConfigurationService configurationService;
	@Resource
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 获取科目
	 *
	 * @return subjects
	 */
	public List<Subject> subjects() {
		List<Subject> subjects;
		String subjectsRedis = stringRedisTemplate.opsForValue().get(REDIS_SUBJECTS);
		if (StringUtils.isNotBlank(subjectsRedis)) {
			log.info("从redis中获取到subjects数据");
			subjects = JSONArray.parseArray(subjectsRedis, Subject.class);
		} else {
			subjects = subjectService.list(new QueryWrapper<Subject>().eq("deleted", 0).orderByAsc("order_number"));
			stringRedisTemplate.opsForValue().set(REDIS_SUBJECTS, JSONArray.toJSONString(subjects));
			log.info("redis中暂无subjects数据，将数据库中的数据存入redis");
		}
		return subjects;
	}

	/**
	 * 获取配置信息
	 *
	 * @return conf
	 */
	public Configuration conf() {
		Configuration conf;
		String confRedis = stringRedisTemplate.opsForValue().get(REDIS_CONF);
		if (StringUtils.isNotBlank(confRedis)) {
			conf = JSONObject.parseObject(confRedis, Configuration.class);
		} else {
			conf = configurationService.list().get(0);
			stringRedisTemplate.opsForValue().set(REDIS_CONF, JSONObject.toJSONString(conf));
			log.info("redis中暂无conf数据，将数据库中的数据存入redis");
		}
		return conf;
	}
}

