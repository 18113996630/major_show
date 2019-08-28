package com.hrong.major.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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

}
