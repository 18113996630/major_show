package com.hrong.major.model.vo;

import lombok.Data;

/**
 * @Author hrong
 **/
@Data
public class MajorVoWithSubjectName {
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
	private String subjectName;

	/**
	 * 删除标志
	 */
	private Integer deleted;
}
