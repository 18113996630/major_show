package com.hrong.major.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author hrong
 * @since 2019-08-15
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Log implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 资源类别
	 */
	private String resourceType;

	/**
	 * 请求地址
	 */
	private String requestAddress;

	/**
	 * ip
	 */
	private String ip;

	/**
	 * 浏览器Agent
	 */
	private String browser;

	/**
	 * 系统名称
	 */
	private String system;

	/**
	 * 系统版本
	 */
	private String systemVersion;

	/**
	 * 操作系统位数
	 */
	private String systemBit;

	/**
	 * HTTP协议版本
	 */
	private String httpVersion;

	/**
	 * 请求编码格式
	 */
	private String encoding;

	/**
	 * Cookie
	 */
	private String cookie;

	/**
	 * 请求url
	 */
	private String url;

	/**
	 * uri资源
	 */
	private String uri;

	/**
	 * 客户端端口
	 */
	private String clientPort;

	/**
	 * 请求方式
	 */
	private String method;

	/**
	 * 参数名字
	 */
	private String params;

	/**
	 * 请求时间
	 */
	private LocalDateTime time;

	/**
	 * 花费的时间
	 */
	private Integer executeTime;


}
