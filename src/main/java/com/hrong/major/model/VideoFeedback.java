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
import java.time.LocalDateTime;

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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class VideoFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 视频id
     */
    @TableField("video_Id")
    private Integer videoId;

    /**
     * 点击量/点赞量/踩的量
     */
    private Integer count;

    /**
     * 统计类型(click、up、down)
     */
    private String type;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 请求时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime time;


}
