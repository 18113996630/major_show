package com.hrong.major.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrong.major.model.Log;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hrong
 * @since 2019-08-15
 */
public interface LogMapper extends BaseMapper<Log> {


	/**
	 * 根据ip地址和访问资源进行查找
	 *
	 * @param page     分页
	 * @param ip       ip地址
	 * @param resource 资源名
	 * @return logs
	 */
	List<Log> findLogsByIpAndResource(@Param("page") Page page, @Param("ip") String ip, @Param("resource") String resource);

	/**
	 * 根据ip地址和访问资源进行查找
	 *
	 * @param ip       ip地址
	 * @param resource 资源名
	 * @return logs
	 */
	int countLogsByIpAndResource(@Param("ip") String ip, @Param("resource") String resource);

	/**
	 * 查询资源类型
	 * @return logs
	 */
	@Select(value = "select distinct resource_type as resourceType from log")
	List<Log> findResourceType();
}
