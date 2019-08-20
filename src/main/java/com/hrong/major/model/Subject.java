package com.hrong.major.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 学科分类
 * </p>
 *
 * @author hrong
 * @since 2019-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Subject implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 学科名字
	 */
	private String name;

	/**
	 * 学科代码
	 */
	private String code;

	/**
	 * 学科描述
	 */
	private String description;

	/**
	 * 图标路径
	 */
	private String iconUri;

	/**
	 * 显示排序号
	 */
	private Integer orderNumber;

	/**
	 * 删除标志
	 */
	private Integer deleted;


}
