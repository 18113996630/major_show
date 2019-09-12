package com.hrong.major.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.model.Search;
import com.hrong.major.dao.SearchMapper;
import com.hrong.major.service.SearchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hrong
 * @since 2019-09-12
 */
@Service
public class SearchServiceImpl extends ServiceImpl<SearchMapper, Search> implements SearchService {

	@Resource
	private SearchMapper searchMapper;

	@Override
	public List<Search> getPopularSearches() {
		return searchMapper.selectList(new QueryWrapper<Search>().orderByDesc("search_count").last("limit 5"));
	}
}
