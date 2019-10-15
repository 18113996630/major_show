package com.hrong.major.model.vo;

import lombok.Data;

/**
 * @Author hrong
 * @Description
 **/
@Data
public class MajorQuestionVo {
	private Integer id;
	/**
	 * question-url
	 */
	private String url;

	private String title;
	/**
	 * 问题描述
	 */
	private String description;

	/**
	 * 回答数量
	 */
	private Integer answerCount;
	/**
	 * 第一个回答（简略）
	 */
	private String firstAnswer;
}
