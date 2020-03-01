package com.hrong.major.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrong.major.model.Article;
import com.hrong.major.model.Label;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hrong
 * @since 2020-02-29
 */
public interface LabelService extends IService<Label> {
	/**
	 * 根据文章ID查找对应的labels
	 * @param articleId 文章ID
	 * @return labels
	 */
	List<Label> findLabelsByArticleId(Long articleId);

	/**
	 * 根据label找对应的文章
	 * @param name label-name
	 * @return article-list
	 */
	List<Article> findArticlesByLabelName(String name);
}
