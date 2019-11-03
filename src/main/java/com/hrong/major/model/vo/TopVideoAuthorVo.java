package com.hrong.major.model.vo;

import lombok.Data;

/**
 * @Author hrong
 **/
@Data
public class TopVideoAuthorVo {
	private String upName;
	private Integer count;
	private String url;
	private String faceCover;

	public TopVideoAuthorVo() {
	}
}
