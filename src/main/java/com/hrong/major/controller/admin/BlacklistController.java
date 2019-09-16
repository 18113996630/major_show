package com.hrong.major.controller.admin;


import com.hrong.major.model.Blacklist;
import com.hrong.major.model.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hrong
 * @since 2019-09-16
 */
@Controller
@RequestMapping("/blacklist")
public class BlacklistController {

	@PostMapping(value = "/ip")
	public Result saveBlackIpToDb(String ip, String detail, String checkTime){

		return null;
	}
}

