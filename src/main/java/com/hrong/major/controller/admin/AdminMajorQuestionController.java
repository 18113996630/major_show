package com.hrong.major.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrong.major.model.MajorQuestion;
import com.hrong.major.model.QuestionAnswer;
import com.hrong.major.model.vo.Result;
import com.hrong.major.service.MajorQuestionService;
import com.hrong.major.service.QuestionAnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author hrong
 * @Description
 **/
@Slf4j
@RestController
@RequestMapping(value = "/admin")
public class AdminMajorQuestionController {
	@Resource
	private MajorQuestionService majorQuestionService;
	@Resource
	private QuestionAnswerService questionAnswerService;

	@GetMapping("/major/questions")
	public Map<String, Object> getAllVideos(int pageNumber, int pageSize, String majorName) {
		Map<String, Object> res = new HashMap<>(2);
		List<MajorQuestion> rows = majorQuestionService.findQuestionsPageData(new Page(pageNumber, pageSize), majorName);
		Integer total = majorQuestionService.countQuestionsPageData(majorName);
		res.put("total", total);
		res.put("rows", rows);
		return res;
	}
	@DeleteMapping("/major/question/{id}")
	public Result deleteMajorQuestion(@PathVariable int id){
		questionAnswerService.remove(new QueryWrapper<QuestionAnswer>().eq("major_question_id", id));
		majorQuestionService.removeById(id);
		return Result.success("删除成功");
	}


}
