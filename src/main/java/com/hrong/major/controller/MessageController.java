package com.hrong.major.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrong.major.model.Message;
import com.hrong.major.model.User;
import com.hrong.major.model.vo.MessageVo;
import com.hrong.major.service.MessageService;
import com.hrong.major.service.UserService;
import com.hrong.major.utils.IpUtils;
import com.hrong.major.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hrong
 * @since 2019-09-01
 */
@Slf4j
@Controller
public class MessageController {

	@Resource
	private UserService userService;
	@Resource
	private MessageService messageService;

	@PostMapping("/contact/message")
	public Object addMessage(HttpServletRequest request, Model model, @ModelAttribute MessageVo messageVo){
		String ip = RequestUtils.getIp(request);
		log.info("ip:{}提交留言：{}", ip, messageVo.getContact());
		String city = IpUtils.getCity(ip);
		User userDb = userService.getOne(new QueryWrapper<User>().eq("contact", messageVo.getContact()));
		if (userDb == null) {
			userDb = new User();
			userDb.setCity(city);
			userDb.setIp(ip);
			userDb.setContact(messageVo.getContact());
			userService.save(userDb);
			log.info("用户保存成功，userId为：{}，开始保存留言信息",userDb.getId());
		}
		Message message = Message.builder().ip(ip).content(messageVo.getContent()).userId(userDb.getId()).build();
		messageService.save(message);
		log.info("留言保存成功");
		model.addAttribute("msg", "留言成功，我会尽快看的~");
		return "contact/contact";
	}
}

