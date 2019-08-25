package com.hrong.major.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author hrong
 * @since 2019-08-17
 */
@Data
public class MajorDetailVoWithSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 专业名字
     */
    private String name;

    /**
     * 培养目标
     */
    private String description;

    /**
     * 核心课程
     */
    private String course;

    /**
     * 就业方向
     */
    private String job;

    /**
     * 能力要求
     */
    private String ability;
    /**
     * 专业外键
     */
    private Integer majorId;
    /**
     * 所属专业类别
     */
    private String subjectName;
    /**
     * 删除标志
     */
    private Integer deleted;
    /**
     * 编码
     */
    private String code;
    /**
     * 学制
     */
    private String year;
}
