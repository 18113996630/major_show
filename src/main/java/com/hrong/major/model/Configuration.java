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
 * @since 2019-08-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Configuration implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * head-专业种类
     */
    private String headSubject;

    /**
     * head-专业
     */
    private String headMajor;

    /**
     * head-排行
     */
    private String headTop;

    /**
     * head-关于我
     */
    private String headAbout;

    /**
     * head-联系
     */
    private String headContact;

    /**
     * head-投稿
     */
    private String headContribute;

    /**
     * footer
     */
    private String footer;

    /**
     * 详情-专业描述
     */
    private String dDesc;
    private String dDescBtn;

    /**
     * 详情-课程
     */
    private String dCourse;
    private String dCourseBtn;

    /**
     * 详情-专业前景
     */
    private String dJob;
    private String dJobBtn;

    /**
     * 详情-文字视频分割
     */
    private String dSplitor;

    /**
     * 详情-视频时间
     */
    private String dVTime;

    /**
     * 详情-视频是否授权
     */
    private String dVAuth;

    /**
     * 详情-视频来源app
     */
    private String dVSource;

    /**
     * 详情-视频作者
     */
    private String dVAuthor;

    /**
     * 详情-视频怎么样
     */
    private String dVHow;

    /**
     * 详情-按钮-回到最上方
     */
    private String dBtnTop;

    /**
     * 详情-按钮-下个专业
     */
    private String dBtnNext;

    /**
     * 详情-按钮-其他专业
     */
    private String dBtnOther;

    /**
     * 详情-按钮-投稿
     */
    private String dBtnContribute;

    /**
     * 详情-按钮-提交评论
     */
    private String dBtnComment;

    /**
     * head-关于-标题
     */
    private String hATitle;

    /**
     * head-关于-p1
     */
    private String hAP1;

    /**
     * head-关于-c1
     */
    private String hAC1;

    /**
     * head-关于-p2
     */
    private String hAP2;

    /**
     * head-关于-c2
     */
    private String hAC2;

    /**
     * head-关于-按钮
     */
    private String hABtn;

    /**
     * head-联系我
     */
    private String hCContent;
    /**
     * 网站名字
     */
    private String webName;
    /**
     * 找视频按钮
     */
    private String dBtnFind;
    /**
     * 最后一个视频的提示
     */
    private String dBtnLast;

}
