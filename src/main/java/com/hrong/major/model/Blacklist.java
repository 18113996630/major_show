package com.hrong.major.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author hrong
 * @since 2019-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Blacklist implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 非法账号id
     */
    private Integer userId;
	/**
	 * 非法ip
	 */
	private String ip;

    /**
     * 检查时间
     */
    private String checkTime;

    /**
     * 检测详情
     */
    private String detail;

    /**
     * 状态(1：拉入黑名单 0：取消黑名单)
     */
    private Integer status;


}
