package com.hrong.major.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 在联系界面的留言版使用
 * </p>
 *
 * @author hrong
 * @since 2019-09-01
 */
@Data
public class MessageVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    /**
     * 留言内容
     */
    private String content;
    /**
     * 联系方式
     */
    private String contact;
    private String ip;
    private String time;
    private String city;
    private String status;
}
