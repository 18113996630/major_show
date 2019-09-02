package com.hrong.major.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrong.major.model.Major;
import com.hrong.major.model.vo.MajorVoWithSubjectName;
import com.hrong.major.model.vo.Result;
import com.hrong.major.service.MajorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 * @Author hrong
 **/
@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class AdminMajorController{

	@Resource
	private MajorService majorService;
	/**
	 * 表格初始化
	 */
	@ResponseBody
	@GetMapping("/majors")
	public Map<String, Object> getAllMajors(int pageNumber, int pageSize, String majorName, String subjectId) {
		Integer subjectIdInt = StringUtils.isEmpty(subjectId) ? null : Integer.parseInt(subjectId);
		Map<String, Object> res = new HashMap<>(2);
		List<MajorVoWithSubjectName> majors = majorService.findMajorsByNameAndSubjectName(new Page(pageNumber, pageSize), majorName, subjectIdInt);
		int total = majorService.countMajorsByNameAndSubjectName(majorName, subjectIdInt);
		res.put("total", total);
		res.put("rows", majors);
		return res;
	}

	@ResponseBody
	@GetMapping(value = "/major/{id}")
	public Object findById(@PathVariable int id) {
		return Result.success(majorService.getById(id));
	}

	@ResponseBody
	@PostMapping(value = "/major")
	public Object saveOrUpdateSubject(@RequestBody Major major) {
		try {
			majorService.saveOrUpdate(major);
		} catch (Exception e) {
			log.error(e.getMessage());
			return Result.err(500, String.format("后台保存出错:%s", e.getMessage()));
		}
		return Result.success("保存成功");
	}

	@ResponseBody
	@DeleteMapping(value = "/major/{id}")
	public Object deleteSubject(@PathVariable int id) {
		Major major = majorService.getById(id);
		if (major.getDeleted() != 1) {
			majorService.update(major, new UpdateWrapper<Major>().eq("id", id).set("deleted", 1));
		}
		return Result.success("删除成功");
	}

	@ResponseBody
	@PostMapping(value = "/major/{id}")
	public Object recoverySubject(@PathVariable int id) {
		Major major = majorService.getById(id);
		if (major.getDeleted() != 0) {
			majorService.update(major, new UpdateWrapper<Major>().eq("id", id).set("deleted", 0));
		}
		return Result.success("恢复成功");
	}

	/**
	 * 根据专业类别id获取专业名字di
	 */
	@GetMapping(value = "/majors/{id}")
	public Object getMajorsBySubjectId(@PathVariable("id") int id) {
		return majorService.list(new QueryWrapper<Major>().select("id", "name").eq("subject_id", id));
	}
}
