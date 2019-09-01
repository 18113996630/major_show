package com.hrong.major.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrong.major.model.Message;
import com.hrong.major.model.vo.MessageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hrong
 * @since 2019-09-01
 */
public interface MessageMapper extends BaseMapper<Message> {

	/**
	 * 分页获取留言
	 *
	 * @param page   分页参数
	 * @param status 状态值
	 * @return vos
	 */
	List<MessageVo> getMessage(@Param("page") Page page, @Param("status") String status);

	/**
	 * 分页获取留言
	 *
	 * @param status 状态值
	 * @return vos
	 */
	int countMessage(@Param("status") String status);
}
