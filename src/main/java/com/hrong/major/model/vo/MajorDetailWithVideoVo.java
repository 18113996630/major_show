package com.hrong.major.model.vo;

import com.hrong.major.model.MajorDetail;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author hrong
 * 专业详情vo（包含视频信息）
 **/
@Data
@Builder
public class MajorDetailWithVideoVo {
	private MajorDetail detail;
	/**
	 * 详情中的视频
	 */
	private List<VideoVo> videos;
}
