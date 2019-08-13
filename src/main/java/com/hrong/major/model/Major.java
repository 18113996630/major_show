package com.hrong.major.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
	 * 专业描述
	 */
	private String description;

	/**
	 * 文章视频是否正确(正确)
	 */
	private Integer isRight;

	/**
	 * 文章视频是否正确(有误)
	 */
	private Integer isWrong;

	/**
	 * 详情url
	 */
	private String detailUrl;

	/**
	 * 排序序号
	 */
	private Integer orderNumber;

	/**
	 * 工作前景
	 */
	private String jobProspect;

	/**
	 * 课程
	 */
	private String course;

	/**
	 * 工作能力
	 */
	private String ability;

	/**
	 * 学科门类id
	 */
	private Integer subjectId;

	/**
	 * 删除标志
	 */
	@TableLogic(value = "0", delval = "1")
	@TableField(select = false)
	private Integer deleted;


}
