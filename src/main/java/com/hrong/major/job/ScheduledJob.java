package com.hrong.major.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.model.Log;
import com.hrong.major.service.LogService;
import com.hrong.major.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

/**
 * @Author hrong
 **/
@Slf4j
//@Component
public class ScheduledJob {
	@Resource
	private LogService logService;
	/**
	 * 更新日志表中city信息不准备的数据
	 */
//	@Scheduled
	public void updateCityInfo(){
		List<Log> logs = logService.list(new QueryWrapper<Log>().eq("city", "未知").or().eq("city", ""));
		HashSet<Log> hashSet = new HashSet<>(logs);
		log.info("发现{}条数据city信息需要更新，开始更新...", logs.size());
		for (Log log : logs) {
			String city = IpUtils.getCity(log.getIp());
			log.setAddress(city);
		}
		logService.saveOrUpdateBatch(logs);
	}
}
