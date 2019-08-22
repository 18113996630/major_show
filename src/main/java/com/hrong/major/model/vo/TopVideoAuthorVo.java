package com.hrong.major.model.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @Author hrong
 **/
@Builder
@Data
public class TopVideoAuthorVo {
	private String upName;
	private Integer count;
	private String url;
	private String faceCover;
}
