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
 * @since 2019-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 视频title
     */
    private String title;

    /**
     * 介绍
     */
    private String intro;

    /**
     * 地址
     */
    private String url;

    /**
     * 时长
     */
    private String duration;

    /**
     * 发布时间
     */
    private String pubtime;

    /**
     * 封面url
     */
    private String cover;
    /**
     * 封面文件名
     */
    private String coverName;

    /**
     * 播放量
     */
    private Integer play;

    /**
     * 弹幕量
     */
    private Integer danmaku;

    /**
     * 回复数量
     */
    private Integer reply;

    /**
     * up id
     */
    private Integer upId;

    /**
     * up主页
     */
    private String upPage;

    /**
     * up名字
     */
    private String upName;

    /**
     * up头像地址
     */
    private String upFace;
    /**
     * up头像文件名称
     */
    private String faceName;

    /**
     * 是否授权
     */
    private Integer isAuth;

    /**
     * 专业id
     */
    private Integer majorId;
    /**
     * 专业id
     */
    private Integer majorDetailId;

    /**
     * 视频来源
     */
    private String sourceName;

    /**
     * 排序号
     */
    private Integer orderNumber;

    /**
     * 0为删除，1删除
     */
    private Integer deleted;


}
