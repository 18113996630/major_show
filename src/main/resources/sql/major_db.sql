/*
 Navicat Premium Data Transfer

 Source Server         : aliyun
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 39.106.190.74:3306
 Source Schema         : major_db

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 15/09/2019 14:12:58
*/
CREATE DATABASE `major_db` DEFAULT CHARACTER SET utf8;

USE `major_db`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '回复内容',
  `major_detail_id` int(11) NULL DEFAULT NULL COMMENT '详情页面id',
  `time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复时间',
  `up_count` int(10) NULL DEFAULT 0 COMMENT '点赞次数',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment_feedback
-- ----------------------------
DROP TABLE IF EXISTS `comment_feedback`;
CREATE TABLE `comment_feedback`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `comment_id` int(10) NOT NULL COMMENT '评论id',
  `time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '点赞时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for configuration
-- ----------------------------
DROP TABLE IF EXISTS `configuration`;
CREATE TABLE `configuration`  (
  `id` int(1) NOT NULL AUTO_INCREMENT,
  `web_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网站名字',
  `head_subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'head-专业种类',
  `head_major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'head-专业',
  `head_top` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'head-排行',
  `head_about` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'head-关于我',
  `head_contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'head-联系',
  `head_contribute` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'head-投稿',
  `footer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'footer',
  `d_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情-专业描述',
  `d_desc_btn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情-专业描述-按钮',
  `d_course` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情-课程',
  `d_course_btn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情-课程-按钮',
  `d_job` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情-专业前景',
  `d_job_btn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情-专业前景按钮',
  `d_splitor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情-文字视频分割',
  `d_v_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情-视频时间',
  `d_v_auth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情-视频是否授权',
  `d_v_source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情-视频来源app',
  `d_v_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情-视频作者',
  `d_v_how` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情-视频怎么样',
  `d_btn_find` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情-按钮-找视频',
  `d_btn_top` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情-按钮-回到最上方',
  `d_btn_last` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情-按钮-最后一个视频',
  `d_btn_next` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情-按钮-下个专业',
  `d_btn_other` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情-按钮-其他专业',
  `d_btn_contribute` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情-按钮-投稿',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言提示',
  `d_btn_comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情-按钮-提交评论',
  `h_a_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'head-关于-标题',
  `h_a_p1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'head-关于-p1',
  `h_a_c1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'head-关于-c1',
  `h_a_p2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'head-关于-p2',
  `h_a_c2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'head-关于-c2',
  `h_a_btn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'head-关于-按钮',
  `h_c_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'head-联系我',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for detail_update
-- ----------------------------
DROP TABLE IF EXISTS `detail_update`;
CREATE TABLE `detail_update`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '修改内容',
  `content_before` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '原内容',
  `reason` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '修改原因',
  `major_detail_id` int(10) NULL DEFAULT NULL COMMENT '专业详情id',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '用户id',
  `time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提交时间',
  `status` int(1) NULL DEFAULT 0 COMMENT '状态(0-待处理 1-已通过 -1-未通过)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `resource_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `request_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `browser` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `system` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `system_version` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `system_bit` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `http_version` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `encoding` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cookie` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uri` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `client_port` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `method` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `params` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  `execute_time` int(15) NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6852 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业名字',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业代码',
  `detail_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情url',
  `order_number` int(2) NULL DEFAULT NULL COMMENT '排序号',
  `subject_id` int(10) NULL DEFAULT NULL COMMENT '学科门类id',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 361 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '专业' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for major_detail
-- ----------------------------
DROP TABLE IF EXISTS `major_detail`;
CREATE TABLE `major_detail`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业名字',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '培养目标',
  `course` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '核心课程',
  `job` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '就业方向',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业编码',
  `year` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学制',
  `major_id` int(10) NULL DEFAULT NULL COMMENT '专业外键',
  `ability` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '能力要求',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 358 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言时间',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '留言内容',
  `ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言者ip',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '用户id',
  `status` int(1) NULL DEFAULT 0 COMMENT '是否查看',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for search
-- ----------------------------
DROP TABLE IF EXISTS `search`;
CREATE TABLE `search`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '搜索内容',
  `search_count` int(10) NULL DEFAULT NULL COMMENT '搜索次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学科名字',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学科代码',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学科描述',
  `icon_uri` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标路径',
  `order_number` int(2) NULL DEFAULT NULL COMMENT '显示排序号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学科分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `contact` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `city` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `ip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 370 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频title',
  `intro` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '介绍',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `duration` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '时长',
  `pubtime` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `cover_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面文件名',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面url',
  `play` int(5) NULL DEFAULT NULL COMMENT '播放量',
  `danmaku` int(5) NULL DEFAULT NULL COMMENT '弹幕量',
  `reply` int(5) NULL DEFAULT NULL COMMENT '回复数量',
  `up_id` int(10) NULL DEFAULT NULL COMMENT 'up id',
  `up_contact` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者联系方式',
  `up_page` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'up主页',
  `up_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'up名字',
  `face_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像文件名',
  `up_face` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'up头像地址',
  `is_auth` int(1) NULL DEFAULT 0 COMMENT '是否授权',
  `major_detail_id` int(10) NULL DEFAULT NULL COMMENT '专业详情外键',
  `major_id` int(10) NULL DEFAULT NULL COMMENT '专业id',
  `source_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '哔哩哔哩' COMMENT '视频来源',
  `order_number` int(5) NULL DEFAULT NULL COMMENT '排序号',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '0为删除，1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 345 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for video_feedback
-- ----------------------------
DROP TABLE IF EXISTS `video_feedback`;
CREATE TABLE `video_feedback`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `video_Id` int(10) NULL DEFAULT NULL COMMENT '视频id',
  `count` int(10) NULL DEFAULT NULL COMMENT '点击量/点赞量/踩的量',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '统计类型(click、up、down)',
  `ip` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for video_needs
-- ----------------------------
DROP TABLE IF EXISTS `video_needs`;
CREATE TABLE `video_needs`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `major_id` int(10) NULL DEFAULT NULL COMMENT '专业id',
  `count` int(10) NULL DEFAULT 1 COMMENT '需求数量',
  `ip` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip',
  `time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求时间',
  `status` int(1) NULL DEFAULT 0 COMMENT '需求状态',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for vw_authed_up
-- ----------------------------
DROP VIEW IF EXISTS `vw_authed_up`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `vw_authed_up` AS select distinct `t`.`up_name` AS `up_name`,`t`.`up_id` AS `up_id`,`t`.`up_page` AS `up_page` from `video` `t` where (`t`.`is_auth` = 1);

-- ----------------------------
-- View structure for vw_video_author_top
-- ----------------------------
DROP VIEW IF EXISTS `vw_video_author_top`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%%` SQL SECURITY DEFINER VIEW `vw_video_author_top` AS select `a`.`upName` AS `upName`,`a`.`count` AS `count`,`b`.`up_page` AS `url`,`b`.`face_name` AS `faceCover` from (((select `t`.`up_name` AS `upName`,count(1) AS `count` from `major_db`.`video` `t` group by `t`.`up_name` order by count(`t`.`up_name`) desc limit 10)) `a` left join (select distinct `v`.`up_name` AS `up_name`,`v`.`up_page` AS `up_page`,`v`.`face_name` AS `face_name` from `major_db`.`video` `v`) `b` on((`a`.`upName` = `b`.`up_name`)));

-- ----------------------------
-- View structure for vw_video_up
-- ----------------------------
DROP VIEW IF EXISTS `vw_video_up`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `vw_video_up` AS select `t1`.`upName` AS `upName`,`t1`.`is_auth` AS `isAuth`,`t1`.`url` AS `url`,`t2`.`count` AS `count`,`t2`.`titles` AS `titles` from (((select distinct `v`.`up_name` AS `upName`,`v`.`is_auth` AS `is_auth`,`v`.`up_page` AS `url` from `major_db`.`video` `v` order by `v`.`is_auth` desc)) `t1` left join (select `t`.`up_name` AS `up_name`,count(`t`.`up_name`) AS `count`,group_concat(`t`.`title` separator ',') AS `titles` from `major_db`.`video` `t` group by `t`.`up_name`) `t2` on((`t1`.`upName` = `t2`.`up_name`)));

SET FOREIGN_KEY_CHECKS = 1;
