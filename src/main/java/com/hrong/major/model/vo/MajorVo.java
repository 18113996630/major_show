package com.hrong.major.model.vo;

import com.hrong.major.model.Major;
import com.hrong.major.model.Subject;
import lombok.Builder;
import lombok.Data;

/**
 * @Author hrong
 **/
@Data
@Builder
public class MajorVo {
	private Major major;
	private Subject subject;
}
