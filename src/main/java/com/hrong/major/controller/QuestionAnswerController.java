package com.hrong.major.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.model.MajorQuestion;
import com.hrong.major.model.QuestionAnswer;
import com.hrong.major.service.MajorQuestionService;
import com.hrong.major.service.QuestionAnswerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hrong
 * @since 2019-10-11
 */
@Controller
@RequestMapping("/major/question")
public class QuestionAnswerController {

	@Resource
	private MajorQuestionService majorQuestionService;
	@Resource
	private QuestionAnswerService questionAnswerService;

	/**
	 * 根据questionId获取问题详情及回答
	 * @param model model
	 * @param id questionId
	 * @return 页面所需数据
	 */
	@GetMapping("/{id}")
	public String getQuestionAnswerByQuestionId(Model model, @PathVariable int id) {
		MajorQuestion question = majorQuestionService.getById(id);
		Integer nextId = majorQuestionService.findNextMajorQuestion(id);
		List<QuestionAnswer> answers = questionAnswerService.list(new QueryWrapper<QuestionAnswer>()
				.eq("major_question_id", id)
				.eq("allow", 1)
				.orderByDesc("order_number"));
		model.addAttribute("question", question);
		model.addAttribute("answers", answers);
		model.addAttribute("nextId", nextId);
		model.addAttribute("title", question.getTitle());
		return "major/question/major_question.html";
	}
}

