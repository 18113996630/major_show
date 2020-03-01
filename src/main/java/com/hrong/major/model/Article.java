package com.hrong.major.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 * 
 * </p>
 *
 * @author hrong
 * @since 2020-02-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 链接
     */
    private String link;

    /**
     * 文章类别
     */
    private Integer typeId;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 发布时间
     */
    private Timestamp publishTime;

    /**
     * 来源
     */
    private String source;

    /**
     * 点击量
     */
    private Integer click;

    /**
     * 评论数量
     */
    private Integer commentCount;

    /**
     * 正文
     */
    private String content;
    /**
     * 正文预览
     */
    private String contentPreview;


}
