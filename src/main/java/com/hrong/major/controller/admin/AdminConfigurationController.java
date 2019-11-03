package com.hrong.major.controller.admin;


import com.alibaba.fastjson.JSONObject;
import com.hrong.major.constant.CacheConstant;
import com.hrong.major.model.Configuration;
import com.hrong.major.service.ConfigurationService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hrong
 * @since 2019-08-29
 */
@Controller
@RequestMapping("/admin")
public class AdminConfigurationController {

	@Resource
	private ConfigurationService configurationService;
	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@PostMapping("/configuration")
	public Object updateConfiguration(@ModelAttribute Configuration configuration){
		configurationService.saveOrUpdate(configuration);
		Configuration conf = configurationService.list().get(0);
		stringRedisTemplate.opsForValue().setIfPresent(CacheConstant.REDIS_CONF, JSONObject.toJSONString(conf));
		return "admin/side/conf";
	}
}

