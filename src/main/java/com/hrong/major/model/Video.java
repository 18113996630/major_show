package com.hrong.major.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author hrong
 * @since 2019-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Video implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 视频标题
	 */
	private String title;

	/**
	 * 视频作者
	 */
	private String author;

	/**
	 * 视频来源
	 */
	private String sourceApp;

	/**
	 * 视频发布日期
	 */
	private LocalDateTime publishTime;

	/**
	 * 视频地址
	 */
	private String videoUrl;

	/**
	 * 是否取得授权
	 */
	private Integer isAuth;

	/**
	 * 排序号
	 */
	private Integer orderNumber;

	/**
	 * 专业名字
	 */
	private String category;

	/**
	 * 专业id
	 */
	private Integer majorId;

	/**
	 * 学科id
	 */
	private Integer subjectId;

	/**
	 * 删除标志
	 */
	@TableLogic(value = "0", delval = "1")
	@TableField(select = false)
	private Integer deleted;
}
