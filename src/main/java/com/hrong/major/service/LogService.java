package com.hrong.major.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hrong.major.model.Log;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hrong
 * @since 2019-08-15
 */
public interface LogService extends IService<Log> {

	/**
	 * 根据ip地址和访问资源进行查找
	 * @param page 分页
	 * @param ip ip地址
	 * @param resource 资源名
	 * @return logs
	 */
	List<Log> findLogsByIpAndResource(Page page, String ip, String resource);

	/**
	 * 根据ip地址和访问资源进行查找
	 * @param ip ip地址
	 * @param resource 资源名
	 * @return logs
	 */
	int countLogsByIpAndResource(String ip, String resource);

	/**
	 * 根据ip地址和访问资源进行查找
	 * @return logs
	 */
	List<Log> findResourceType();
}
