package com.hrong.major.model.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author hrong
 * @since 2019-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DetailUpdateVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 修改内容
	 */
	private String content;
	/**
	 * 原内容
	 */
	private String contentBefore;

	/**
	 * 修改类型
	 */
	private String type;

	/**
	 * 修改原因
	 */
	private String reason;

	/**
	 * 专业名字
	 *
	 */
	private String majorName;
	/**
	 * 用户id
	 */
	private String ip;
	private String city;

	/**
	 * 提交时间
	 */
	private String time;

	/**
	 * 状态(0-待处理 1-已通过 -1-未通过)
	 */
	private Integer status;



}
