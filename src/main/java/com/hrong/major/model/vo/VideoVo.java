package com.hrong.major.model.vo;

import com.hrong.major.model.Video;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author hrong
 **/
@Data
@Builder
public class VideoVo implements Serializable {
	private Video video;
	/**
	 * 是否点赞
	 */
	private Integer isUpped;
	/**
	 * 是否踩
	 */
	private Integer isDowned;
	/**
	 * 点赞量
	 */
	private int upCount;
	/**
	 * 踩量
	 */
	private int downCount;
	/**
	 * 点击量
	 */
	private int clickCount;
}
