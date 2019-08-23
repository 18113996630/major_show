package com.hrong.major.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hrong.major.constant.CacheConstant;
import com.hrong.major.model.Subject;
import com.hrong.major.model.vo.Result;
import com.hrong.major.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 学科分类 前端控制器
 * </p>
 *
 * @author hrong
 * @since 2019-08-13
 */
@Slf4j
@Controller
public class SubjectController {
	@Resource
	private SubjectService subjectService;

	/**
	 *
	 */
	@ResponseBody
	@RequestMapping("/subjects")
	public Map<String, Object> getAllSubjects() {
		Map<String, Object> res = new HashMap<>(2);
		List<Subject> subjects = CacheConstant.subjects;
		res.put("total", subjects.size());
		res.put("rows", subjects);
		return res;
	}

	@ResponseBody
	@GetMapping(value = "/subject/{id}")
	public Object findById(@PathVariable int id) {
		return Result.success(subjectService.getById(id));
	}

	@ResponseBody
	@PostMapping(value = "/subject")
	public Object saveOrUpdateSubject(@RequestBody Subject subject) {
		try {
			subjectService.saveOrUpdate(subject);
		} catch (Exception e) {
			log.error(e.getMessage());
			return Result.err(500, String.format("后台保存出错:%s", e.getMessage()));
		}
		return Result.success("保存成功");
	}

	@ResponseBody
	@DeleteMapping(value = "/subject/{id}")
	public Object deleteSubject(@PathVariable int id) {
		Subject subject = subjectService.getById(id);
		if (subject.getDeleted() != 1) {
			subjectService.update(subject, new UpdateWrapper<Subject>().eq("id", id).set("deleted", 1));
		}
		return Result.success("删除成功");
	}

	@ResponseBody
	@PostMapping(value = "/subject/{id}")
	public Object recoverySubject(@PathVariable int id) {
		Subject subject = subjectService.getById(id);
		if (subject.getDeleted() != 0) {
			subjectService.update(subject, new UpdateWrapper<Subject>().eq("id", id).set("deleted", 0));
		}
		return Result.success("恢复成功");
	}

}

