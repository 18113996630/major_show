package com.hrong.major.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrong.major.dao.ArticleMapper;
import com.hrong.major.dao.LabelMapper;
import com.hrong.major.dao.RelationArticleLabelMapper;
import com.hrong.major.model.Article;
import com.hrong.major.model.Label;
import com.hrong.major.model.RelationArticleLabel;
import com.hrong.major.service.LabelService;
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
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {

	@Resource
	private RelationArticleLabelMapper relationArticleLabelMapper;
	@Resource
	private LabelMapper labelMapper;
	@Resource
	private ArticleMapper articleMapper;

	@Override
	public List<Label> findLabelsByArticleId(Long articleId) {
		List<RelationArticleLabel> relation = relationArticleLabelMapper.selectList(new QueryWrapper<RelationArticleLabel>().eq("article_id", articleId));
		List<Long> labelIdList = relation.stream().map(RelationArticleLabel::getLabelId).collect(Collectors.toList());
		return labelMapper.selectList(new QueryWrapper<Label>().in("id", labelIdList));
	}

	@Override
	public List<Article> findArticlesByLabelName(String name) {
		Label label = labelMapper.selectOne(new QueryWrapper<Label>().eq("name", name));
		List<RelationArticleLabel> relation = relationArticleLabelMapper.selectList(new QueryWrapper<RelationArticleLabel>().eq("label_id", label.getId()));
		List<Long> articleIdList = relation.stream().map(RelationArticleLabel::getArticleId).collect(Collectors.toList());
		return articleMapper.selectList(new QueryWrapper<Article>().in("id", articleIdList));
	}
}
