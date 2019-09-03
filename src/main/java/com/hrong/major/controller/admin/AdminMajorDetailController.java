package com.hrong.major.controller.admin;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrong.major.model.MajorDetail;
import com.hrong.major.model.vo.MajorDetailVoWithSubject;
import com.hrong.major.model.vo.Result;
import com.hrong.major.service.MajorDetailService;
import com.hrong.major.service.MajorService;
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
 * @Author hrong
 **/
@Slf4j
@Controller
@RequestMapping(value = "/admin/major")
public class AdminMajorDetailController {

	@Resource
	private MajorService majorService;
	@Resource
	private MajorDetailService detailDetailService;
	/**
	 * 表格初始化
	 */
	@ResponseBody
	@GetMapping("/details")
	public Map<String, Object> getAllMajors(int pageNumber, int pageSize, String majorName, String subjectName) {
		Map<String, Object> res = new HashMap<>(2);
		List<MajorDetailVoWithSubject> details = detailDetailService.findMajorsDetailByNameAndSubjectName(new Page(pageNumber, pageSize), majorName, subjectName);
		int total = detailDetailService.countMajorsDetailByNameAndSubjectName(majorName, subjectName);
		res.put("total", total);
		res.put("rows", details);
		return res;
	}

	@ResponseBody
	@GetMapping(value = "/detail/{id}")
	public Object findById(@PathVariable int id) {
		return Result.success(detailDetailService.getById(id));
	}

	@ResponseBody
	@PostMapping(value = "/detail")
	public Object saveOrUpdateSubject(@RequestBody MajorDetail detail) {
		try {
			Integer majorId = detail.getMajorId();
			String majorName = majorService.getById(majorId).getName();
			detail.setName(majorName);
			detailDetailService.saveOrUpdate(detail);
		} catch (Exception e) {
			log.error(e.getMessage());
			return Result.err(500, String.format("后台保存出错:%s", e.getMessage()));
		}
		return Result.success("保存成功");
	}

	@ResponseBody
	@DeleteMapping(value = "/detail/{id}")
	public Object deleteSubject(@PathVariable int id) {
		MajorDetail detail = detailDetailService.getById(id);
		if (detail.getDeleted() != 1) {
			detailDetailService.update(detail, new UpdateWrapper<MajorDetail>().eq("id", id).set("deleted", 1));
		}
		return Result.success("删除成功");
	}

	@ResponseBody
	@PostMapping(value = "/detail/{id}")
	public Object recoverySubject(@PathVariable int id) {
		MajorDetail detail = detailDetailService.getById(id);
		if (detail.getDeleted() != 0) {
			detailDetailService.update(detail, new UpdateWrapper<MajorDetail>().eq("id", id).set("deleted", 0));
		}
		return Result.success("恢复成功");
	}
}
