/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : major_db

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 19/08/2019 14:49:36
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
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复内容',
  `detail_id` int(11) NULL DEFAULT NULL COMMENT '详情页面id',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '回复时间',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父级评论',
  `to_id` int(11) NULL DEFAULT NULL COMMENT '目标评论',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for detail
-- ----------------------------
DROP TABLE IF EXISTS `detail`;
CREATE TABLE `detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `course` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `job` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `ability` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1602 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `resource_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `request_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `browser` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `system` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `system_version` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 786 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业名字',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业代码',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '专业描述',
  `is_right` int(10) NULL DEFAULT NULL COMMENT '文章视频是否正确(正确)',
  `is_wrong` int(10) NULL DEFAULT NULL COMMENT '文章视频是否正确(有误)',
  `detail_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情url',
  `order_number` int(2) NULL DEFAULT NULL,
  `job_prospect` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '工作前景',
  `course` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '课程',
  `ability` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '工作能力',
  `subject_id` int(10) NULL DEFAULT NULL COMMENT '学科门类id',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 360 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '专业' ROW_FORMAT = Dynamic;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 512 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for major_detail_bak
-- ----------------------------
DROP TABLE IF EXISTS `major_detail_bak`;
CREATE TABLE `major_detail_bak`  (
  `id` int(10) NOT NULL DEFAULT 0,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业名字',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '培养目标',
  `course` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '核心课程',
  `job` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '就业方向',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业编码',
  `year` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学制',
  `major_id` int(10) NULL DEFAULT NULL COMMENT '专业外键',
  `ability` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '能力要求'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for search
-- ----------------------------
DROP TABLE IF EXISTS `search`;
CREATE TABLE `search`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `content` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '搜索内容',
  `search_count` int(10) NULL DEFAULT NULL COMMENT '搜索次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 339 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for vw_authed_up
-- ----------------------------
DROP VIEW IF EXISTS `vw_authed_up`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `vw_authed_up` AS select distinct `t`.`up_name` AS `up_name`,`t`.`up_id` AS `up_id`,`t`.`up_page` AS `up_page` from `video` `t` where (`t`.`is_auth` = 1);

-- ----------------------------
-- View structure for vw_video_up
-- ----------------------------
DROP VIEW IF EXISTS `vw_video_up`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `vw_video_up` AS select distinct `t`.`up_name` AS `up_name`,`t`.`up_id` AS `up_id`,`t`.`up_page` AS `up_page` from `video` `t`;

SET FOREIGN_KEY_CHECKS = 1;
