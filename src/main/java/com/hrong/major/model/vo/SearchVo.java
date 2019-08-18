package com.hrong.major.model.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


/**
 * @Author hrong
 * 专业查询vo
 **/
@Data
public class SearchVo {
	@NotBlank(message = "搜索条件不能为空哦~")
	@Length(max = 30, message ="专业名太长啦~")
	private String name;
}
