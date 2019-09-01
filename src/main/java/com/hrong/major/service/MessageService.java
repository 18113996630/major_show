package com.hrong.major.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hrong.major.model.Message;
import com.hrong.major.model.vo.MessageVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hrong
 * @since 2019-09-01
 */
public interface MessageService extends IService<Message> {
	/**
	 * 分页获取留言
	 *
	 * @param page   分页参数
	 * @param status 状态值
	 * @return vos
	 */
	List<MessageVo> getMessage(Page page, String status);

	/**
	 * 分页获取留言
	 *
	 * @param status 状态值
	 * @return vos
	 */
	int countMessage(String status);
}
