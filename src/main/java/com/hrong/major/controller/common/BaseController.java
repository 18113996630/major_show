package com.hrong.major.controller.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.ui.Model;

/**
 * @Author hrong
 **/
public class BaseController<T> {
	public void packagePageResult(Model model, IPage<T> page){
		model.addAttribute("pageNum", page.getCurrent());
		model.addAttribute("pageSize", page.getSize());
		model.addAttribute("totalPages", page.getPages());
	}
}
