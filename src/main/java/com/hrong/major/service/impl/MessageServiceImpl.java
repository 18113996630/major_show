package com.hrong.major.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrong.major.dao.MessageMapper;
import com.hrong.major.model.Message;
import com.hrong.major.model.vo.MessageVo;
import com.hrong.major.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hrong
 * @since 2019-09-01
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

	@Resource
	private MessageMapper messageMapper;
	@Override
	public List<MessageVo> getMessage(Page page, String status) {
		return messageMapper.getMessage(page, status);
	}

	@Override
	public int countMessage(String status) {
		return messageMapper.countMessage(status);
	}
}
