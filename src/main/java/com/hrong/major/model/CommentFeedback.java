package com.hrong.major.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
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
 * @since 2019-08-28
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CommentFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 评论id
     */
    private Integer commentId;

    /**
     * 点赞时间
     */
    @TableField(fill = FieldFill.INSERT)
    private String time;


}
