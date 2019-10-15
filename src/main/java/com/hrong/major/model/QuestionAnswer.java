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
public class QuestionAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 作者名字
     */
    private String authorName;

    /**
     * 作者主页
     */
    private String authorUrl;

    /**
     * 作者描述
     */
    private String authorDescription;

    /**
     * 点赞描述
     */
    private String upCount;

    /**
     * 发布时间
     */
    private String time;

    /**
     * 回答内容
     */
    private String content;

    /**
     * 排序号
     */
    private Integer orderNumber;

    /**
     * 删除标志，默认为0,1表示已删除
     */
    private Integer allow;

    /**
     * question外键
     */
    private Integer majorQuestionId;


}
