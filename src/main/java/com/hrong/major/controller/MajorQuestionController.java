package com.hrong.major.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.model.MajorQuestion;
import com.hrong.major.service.MajorQuestionService;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/question")
public class MajorQuestionController {

	@Resource
	private MajorQuestionService majorQuestionService;

	@GetMapping("/{id}")
	public List<MajorQuestion> getQuestionsByMajorId(@PathVariable int id){
		majorQuestionService.list(new QueryWrapper<MajorQuestion>().eq("major_id", id));
		return null;
	}
}

