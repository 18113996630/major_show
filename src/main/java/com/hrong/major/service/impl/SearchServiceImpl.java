package com.hrong.major.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrong.major.dao.SearchMapper;
import com.hrong.major.model.Search;
import com.hrong.major.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Slf4j
@Service
public class SearchServiceImpl extends ServiceImpl<SearchMapper, Search> implements SearchService {

	@Resource
	private SearchMapper searchMapper;

	@Override
	public List<Search> getPopularSearches() {
		return searchMapper.selectList(new QueryWrapper<Search>().orderByDesc("search_count").last("limit 6"));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdateSearch(String name) {
		Search search = getOne(new QueryWrapper<Search>().eq("name", name));
		if (search == null) {
			search = new Search();
			search.setName(name);
			search.setSearchCount(1);
			log.info("首次出现该搜索词:{}", name);
		}else {
			Integer count = search.getSearchCount();
			search.setSearchCount(count + 1);
			log.info("搜索词：{}的搜索次数:{}", name, count);
		}
		saveOrUpdate(search);
	}
}
