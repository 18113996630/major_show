package com.hrong.major.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrong.major.model.Message;
import com.hrong.major.model.vo.MessageVo;
import com.hrong.major.model.vo.Result;
import com.hrong.major.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class AdminWebMessageController {

	@Resource
	private MessageService messageService;
	/**
	 * 表格初始化
	 */
	@ResponseBody
	@GetMapping("/contact/messages")
	public Map<String, Object> getAllVideos(int pageNumber, int pageSize, String status) {
		Map<String, Object> res = new HashMap<>(2);
		List<MessageVo> messages = messageService.getMessage(new Page(pageNumber, pageSize), status);
		int total = messageService.countMessage(status);
		res.put("total", total);
		res.put("rows", messages);
		return res;
	}
/**
	 * 表格初始化
	 */
	@ResponseBody
	@PostMapping("/contact/message/{id}")
	public Object dealerMessage(@PathVariable int id) {
		Message message = messageService.getById(id);
		int status = message.getStatus() == 1 ? 0 : 1;
		message.setStatus(status);
		messageService.saveOrUpdate(message);
		return Result.success("状态更改成功");
	}

}
