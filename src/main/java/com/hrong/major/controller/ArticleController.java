package com.hrong.major.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hrong.major.controller.common.BaseController;
import com.hrong.major.model.Article;
import com.hrong.major.model.ArticleType;
import com.hrong.major.model.Label;
import com.hrong.major.model.vo.SearchVo;
import com.hrong.major.service.ArticleService;
import com.hrong.major.service.ArticleTypeService;
import com.hrong.major.service.LabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hrong
 * @since 2020-02-29
 */
@Slf4j
@Controller
public class ArticleController extends BaseController<Article> {

	@Resource
	private ArticleService articleService;
	@Resource
	private LabelService labelService;
	@Resource
	private ArticleTypeService articleTypeService;


	/**
	 * 根据文章类型获取文章列表并进行跳转
	 *
	 * @return 文章页面
	 */
	@GetMapping(value = "/articles/{type}")
	public String listArticles(Model model, @PathVariable(value = "type") String type,
							   @RequestParam(value = "page") int page,
							   @RequestParam(value = "size") int size) {
		IPage<Article> articles = articleService.findArticlesByArticleType(type, page, size);
		packagePageResult(model, articles);
		model.addAttribute("articles", articles.getRecords());
		model.addAttribute("type", type);
		SearchVo searchVo = new SearchVo();
		searchVo.setType(type);
		model.addAttribute("searchVo", searchVo);
		return "article/article";
	}

	/**
	 * 根据文章类型和文章名字(模糊搜索)获取文章列表并进行跳转
	 *
	 * @return 文章页面
	 */
	@PostMapping(value = "/articles/search/{type}")
	public String searchArticles(Model model, @ModelAttribute SearchVo searchVo) {
		List<Article> articles = articleService.findArticlesByArticleTypeAndName(searchVo.getType(), searchVo.getName());
		model.addAttribute("articles", articles);
		model.addAttribute("type", searchVo.getType());
		model.addAttribute("searchVo", new SearchVo());
		return "article/article";
	}

	/**
	 * 根据文章类型和label进行搜索
	 *
	 * @return 文章页面
	 */
	@GetMapping(value = "/articles/{type}/label")
	public String queryArticles(Model model, @PathVariable(value = "type") String type,
								@RequestParam(value = "label") String label,
								@RequestParam(value = "page") int page,
								@RequestParam(value = "size") int size) {
		IPage<Article> articles = articleService.findArticlesByArticleTypeAndLabel(type, label, page, size);
		packagePageResult(model, articles);
		model.addAttribute("articles", articles.getRecords());
		model.addAttribute("type", type);
		model.addAttribute("searchVo", new SearchVo());
		return "article/article";
	}


	/**
	 * 根据文章ID获取文章详情
	 *
	 * @return 文章详情页面
	 */
	@GetMapping(value = "/article/{id}")
	public String listArticles(Model model, @PathVariable(value = "id") int id) {
		Article article = articleService.getOne(new QueryWrapper<Article>().eq("id", id));
		List<Label> labels = labelService.findLabelsByArticleId(article.getId());
		ArticleType articleType = articleTypeService.getOne(new QueryWrapper<ArticleType>().eq("id", article.getTypeId()));
		Long nextArticleId = articleService.getNextArticleId((long) id);
		model.addAttribute("article", article);
		model.addAttribute("labels", labels);
		model.addAttribute("nextId", nextArticleId);
		model.addAttribute("type", articleType.getName());
		return "article/detail";
	}
}

