package com.hrong.major.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrong.major.model.Search;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hrong
 * @since 2019-09-12
 */
public interface SearchService extends IService<Search> {
	/**
	 * 获取常见搜索
	 * @return datas
	 */
	List<Search> getPopularSearches();

	/**
	 * 保存或新增search对象
	 * @param name 查询词
	 */
	void saveOrUpdateSearch(String name);
}
