package com.hrong.major.model.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hrong
 * @since 2019-08-21
 */
@Data
@Builder
public class VideoNeedsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 专业id
     */
    private String majorName;

    /**
     * 需求数量
     */
    private Integer count;

    /**
     * ip
     */
    private String ip;

    /**
     *
     * 请求时间
     */
    private String time;
    private String address;
}
