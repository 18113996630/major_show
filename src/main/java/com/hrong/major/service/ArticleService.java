package com.hrong.major.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hrong.major.model.Article;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hrong
 * @since 2020-02-29
 */
public interface ArticleService extends IService<Article> {
	/**
	 * 根据article 类别和名字查找对应的articles
	 *
	 * @param type 类别
	 * @param name 文章名字
	 * @return list
	 */
	List<Article> findArticlesByArticleTypeAndName(String type, String name);

	/**
	 * 根据article 类别查找对应的articles
	 *
	 * @param type 类别
	 * @return list
	 */
	IPage<Article> findArticlesByArticleType(String type, int page, int size);

	/**
	 * 根据article 类别和label查找对应的articles
	 *
	 * @param type  类别
	 * @param label 文章标签
	 * @return list
	 */
	IPage<Article> findArticlesByArticleTypeAndLabel(String type, String label, int page, int size);

	/**
	 * 根据当前id获取下一篇文章的ID
	 * @param current 当前文章ID
	 * @return 下一篇文章的ID
	 */
	Long getNextArticleId(Long current);
}
