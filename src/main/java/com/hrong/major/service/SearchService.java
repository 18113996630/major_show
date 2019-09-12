package com.hrong.major.service;

import com.hrong.major.model.Search;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
