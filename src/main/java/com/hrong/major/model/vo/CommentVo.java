package com.hrong.major.model.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hrong
 * @since 2019-08-27
 */
@Data
public class CommentVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 详情页面id
     */
    private Integer majorDetailId;

    /**
     * 回复时间
     */
    @TableField(fill = FieldFill.INSERT)
    private String time;

    /**
     * 点赞次数
     */
    private Integer upCount;
    /**
     * IP
     */
    private String ip;
    private int isUp;
}
