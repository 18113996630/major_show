package com.hrong.major.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author hrong
 * @since 2019-08-22
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 账号
	 */
	private String account;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 名字
	 */
	private String name;
	/**
	 * 联系方式
	 */
	private String contact;
	/**
	 * 所在城市
	 */
	private String city;

	private String ip;

}
