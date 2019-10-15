package com.hrong.major.model;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2019-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MajorQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * question-url
     */
    private String url;

    private String majorName;

    /**
     * 专业id
     */
    private Integer majorId;

    private String title;

    /**
     * 问题描述
     */
    private String description;

    /**
     * 回答数量
     */
    private Integer answerCount;
}
