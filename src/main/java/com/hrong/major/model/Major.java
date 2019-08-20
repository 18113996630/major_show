package com.hrong.major.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 专业
 * </p>
 *
 * @author hrong
 * @since 2019-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Major implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 专业名字
	 */
	private String name;

	/**
	 * 专业代码
	 */
	private String code;

	/**
	 * 详情url
	 */
	private String detailUrl;

	/**
	 * 排序序号
	 */
	private Integer orderNumber;

	/**
	 * 学科门类id
	 */
	private Integer subjectId;

	/**
	 * 删除标志
	 */
	private Integer deleted;
}
