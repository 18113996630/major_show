package com.hrong.major.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrong.major.dao.LogMapper;
import com.hrong.major.model.Log;
import com.hrong.major.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hrong
 * @since 2019-08-15
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

	@Resource
	private LogMapper logMapper;

	@Override
	public List<Log> findLogsByIpAndResource(Page page, String ip, String resource) {
		return logMapper.findLogsByIpAndResource(page, ip, resource);
	}

	@Override
	public int countLogsByIpAndResource(String ip, String resource) {
		return logMapper.countLogsByIpAndResource(ip, resource);
	}

	@Override
	public List<Log> findResourceType() {
		return logMapper.findResourceType();
	}
}
