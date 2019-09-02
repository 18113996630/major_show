package com.hrong.major.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrong.major.model.Log;
import com.hrong.major.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
public class AdminLogController {

	@Resource
	private LogService logService;

	/**
	 * 表格初始化
	 */
	@ResponseBody
	@GetMapping("/logs")
	public Map<String, Object> getAllLogs(int pageNumber, int pageSize, String ip, String resource) {
		Map<String, Object> res = new HashMap<>(2);
		List<Log> logs = logService.findLogsByIpAndResource(new Page(pageNumber, pageSize), ip, resource);
		int total = logService.countLogsByIpAndResource(ip, resource);
		res.put("total", total);
		res.put("rows", logs);
		return res;
	}
}
