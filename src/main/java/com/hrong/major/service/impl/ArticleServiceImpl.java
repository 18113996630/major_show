package com.hrong.major.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrong.major.dao.ArticleMapper;
import com.hrong.major.dao.ArticleTypeMapper;
import com.hrong.major.model.Article;
import com.hrong.major.model.ArticleType;
import com.hrong.major.service.ArticleService;
import com.hrong.major.service.LabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hrong
 * @since 2020-02-29
 */
@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
	@Resource
	private ArticleTypeMapper articleTypeMapper;
	@Resource
	private LabelService labelService;
	@Resource
	private ArticleMapper articleMapper;

	@Override
	public List<Article> findArticlesByArticleTypeAndName(String type, String name) {
		ArticleType articleType = articleTypeMapper.selectOne(new QueryWrapper<ArticleType>().eq("name", type));
		QueryWrapper<Article> queryWrapper = new QueryWrapper<Article>()
				.eq("type_id", articleType.getId())
				.like("title", name)
				.orderByDesc("publish_time");
		return list(queryWrapper);
	}

	@Override
	public IPage<Article> findArticlesByArticleType(String type, int page, int size) {
		ArticleType articleType = articleTypeMapper.selectOne(new QueryWrapper<ArticleType>().eq("name", type));
		QueryWrapper<Article> queryWrapper = new QueryWrapper<Article>()
				.eq("type_id", articleType.getId())
				.orderByDesc("publish_time");
		return page(new Page<>(page, size), queryWrapper);
	}

	@Override
	public IPage<Article> findArticlesByArticleTypeAndLabel(String type, String label, int page, int size) {
		// 找到对应的articleType
		ArticleType articleType = articleTypeMapper.selectOne(new QueryWrapper<ArticleType>().eq("name", type));
		// 找到标签对应的article
		List<Article> articles = labelService.findArticlesByLabelName(label);
		// 根据type进行过滤
		List<Article> resultArticles = articles.stream().filter(article -> article.getTypeId().equals(articleType.getId())).collect(Collectors.toList());
		Page pageObject = new Page(page, size, resultArticles.size(), true);
		pageObject.setRecords(resultArticles);
		return pageObject;
	}

	@Override
	public Long getNextArticleId(Long current) {
		Article nextArticle = articleMapper.selectOne(new QueryWrapper<Article>().select("id").gt("id", current).orderByAsc("id").last("limit 1"));
		return nextArticle == null ? 0L : nextArticle.getId();
	}
}
