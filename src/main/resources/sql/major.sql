/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : major

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 12/08/2019 07:58:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名字',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '课程描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for major_detail
-- ----------------------------
DROP TABLE IF EXISTS `major_detail`;
CREATE TABLE `major_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业名字',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业代码',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '专业描述',
  `is_right` int(10) NULL DEFAULT NULL COMMENT '文章视频是否正确(正确)',
  `is_wrong` int(10) NULL DEFAULT NULL COMMENT '文章视频是否正确(有误)',
  `detail_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情url',
  `order` int(2) NULL DEFAULT NULL COMMENT '排序序号',
  `job_prospect` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `course` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `ability` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `subject_id` int(10) NULL DEFAULT NULL COMMENT '学科门类id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 359 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '专业' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of major_detail
-- ----------------------------
INSERT INTO `major_detail` VALUES (1, '哲学', '10101', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 1);
INSERT INTO `major_detail` VALUES (2, '逻辑学', '10102', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, 1);
INSERT INTO `major_detail` VALUES (3, '宗教学', '010103K', NULL, NULL, NULL, NULL, 3, NULL, NULL, NULL, 1);
INSERT INTO `major_detail` VALUES (4, '经济学', '20101', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 2);
INSERT INTO `major_detail` VALUES (5, '经济统计学', '20102', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, 2);
INSERT INTO `major_detail` VALUES (6, '财政学', '020201K', NULL, NULL, NULL, NULL, 3, NULL, NULL, NULL, 2);
INSERT INTO `major_detail` VALUES (7, '税收学', '20202', NULL, NULL, NULL, NULL, 4, NULL, NULL, NULL, 2);
INSERT INTO `major_detail` VALUES (8, '金融学', '020301K', NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, 2);
INSERT INTO `major_detail` VALUES (9, '金融工程', '20302', NULL, NULL, NULL, NULL, 6, NULL, NULL, NULL, 2);
INSERT INTO `major_detail` VALUES (10, '保险学', '20303', NULL, NULL, NULL, NULL, 7, NULL, NULL, NULL, 2);
INSERT INTO `major_detail` VALUES (11, '投资学', '20304', NULL, NULL, NULL, NULL, 8, NULL, NULL, NULL, 2);
INSERT INTO `major_detail` VALUES (12, '国际经济与贸易', '20401', NULL, NULL, NULL, NULL, 9, NULL, NULL, NULL, 2);
INSERT INTO `major_detail` VALUES (13, '贸易经济', '20402', NULL, NULL, NULL, NULL, 10, NULL, NULL, NULL, 2);
INSERT INTO `major_detail` VALUES (14, '法学', '030101K', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 3);
INSERT INTO `major_detail` VALUES (15, '政治学与行政学', '30201', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, 3);
INSERT INTO `major_detail` VALUES (16, '国际政治', '30202', NULL, NULL, NULL, NULL, 3, NULL, NULL, NULL, 3);
INSERT INTO `major_detail` VALUES (17, '外交学', '30203', NULL, NULL, NULL, NULL, 4, NULL, NULL, NULL, 3);
INSERT INTO `major_detail` VALUES (18, '社会学', '30301', NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, 3);
INSERT INTO `major_detail` VALUES (19, '社会工作', '30302', NULL, NULL, NULL, NULL, 6, NULL, NULL, NULL, 3);
INSERT INTO `major_detail` VALUES (20, '民族学', '30401', NULL, NULL, NULL, NULL, 7, NULL, NULL, NULL, 3);
INSERT INTO `major_detail` VALUES (21, '科学社会主义', '30501', NULL, NULL, NULL, NULL, 8, NULL, NULL, NULL, 3);
INSERT INTO `major_detail` VALUES (22, '中国共产党历史', '30502', NULL, NULL, NULL, NULL, 9, NULL, NULL, NULL, 3);
INSERT INTO `major_detail` VALUES (23, '思想政治教育', '30503', NULL, NULL, NULL, NULL, 10, NULL, NULL, NULL, 3);
INSERT INTO `major_detail` VALUES (24, '治安学', '030601K', NULL, NULL, NULL, NULL, 11, NULL, NULL, NULL, 3);
INSERT INTO `major_detail` VALUES (25, '侦查学', '030602K', NULL, NULL, NULL, NULL, 12, NULL, NULL, NULL, 3);
INSERT INTO `major_detail` VALUES (26, '边防管理', '030603K', NULL, NULL, NULL, NULL, 13, NULL, NULL, NULL, 3);
INSERT INTO `major_detail` VALUES (27, '教育学', '40101', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 4);
INSERT INTO `major_detail` VALUES (28, '科学教育', '40102', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, 4);
INSERT INTO `major_detail` VALUES (29, '人文教育', '40103', NULL, NULL, NULL, NULL, 3, NULL, NULL, NULL, 4);
INSERT INTO `major_detail` VALUES (30, '教育技术学（注：可授教育学或理学或工学学士学位）', '40104', NULL, NULL, NULL, NULL, 4, NULL, NULL, NULL, 4);
INSERT INTO `major_detail` VALUES (31, '艺术教育（注：可授教育学或艺术学学士学位）', '40105', NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, 4);
INSERT INTO `major_detail` VALUES (32, '学前教育', '40106', NULL, NULL, NULL, NULL, 6, NULL, NULL, NULL, 4);
INSERT INTO `major_detail` VALUES (33, '小学教育', '40107', NULL, NULL, NULL, NULL, 7, NULL, NULL, NULL, 4);
INSERT INTO `major_detail` VALUES (34, '特殊教育', '40108', NULL, NULL, NULL, NULL, 8, NULL, NULL, NULL, 4);
INSERT INTO `major_detail` VALUES (35, '体育教育', '40201', NULL, NULL, NULL, NULL, 9, NULL, NULL, NULL, 4);
INSERT INTO `major_detail` VALUES (36, '运动训练', '040202K', NULL, NULL, NULL, NULL, 10, NULL, NULL, NULL, 4);
INSERT INTO `major_detail` VALUES (37, '社会体育指导与管理', '40203', NULL, NULL, NULL, NULL, 11, NULL, NULL, NULL, 4);
INSERT INTO `major_detail` VALUES (38, '武术与民族传统体育', '040204K', NULL, NULL, NULL, NULL, 12, NULL, NULL, NULL, 4);
INSERT INTO `major_detail` VALUES (39, '运动人体科学', '40205', NULL, NULL, NULL, NULL, 13, NULL, NULL, NULL, 4);
INSERT INTO `major_detail` VALUES (40, '汉语言文学', '50101', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (41, '汉语言', '50102', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (42, '汉语国际教育', '50103', NULL, NULL, NULL, NULL, 3, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (43, '中国少数民族语言文学', '50104', NULL, NULL, NULL, NULL, 4, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (44, '古典文献学', '50105', NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (45, '英语', '50201', NULL, NULL, NULL, NULL, 6, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (46, '俄语', '50202', NULL, NULL, NULL, NULL, 7, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (47, '德语', '50203', NULL, NULL, NULL, NULL, 8, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (48, '法语', '50204', NULL, NULL, NULL, NULL, 9, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (49, '西班牙语', '50205', NULL, NULL, NULL, NULL, 10, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (50, '阿拉伯语', '50206', NULL, NULL, NULL, NULL, 11, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (51, '日语', '50207', NULL, NULL, NULL, NULL, 12, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (52, '波斯语', '50208', NULL, NULL, NULL, NULL, 13, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (53, '朝鲜语', '50209', NULL, NULL, NULL, NULL, 14, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (54, '菲律宾语', '50210', NULL, NULL, NULL, NULL, 15, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (55, '梵语巴利语', '50211', NULL, NULL, NULL, NULL, 16, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (56, '印度尼西亚语', '50212', NULL, NULL, NULL, NULL, 17, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (57, '印地语', '50213', NULL, NULL, NULL, NULL, 18, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (58, '柬埔寨语', '50214', NULL, NULL, NULL, NULL, 19, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (59, '老挝语', '50215', NULL, NULL, NULL, NULL, 20, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (60, '缅甸语', '50216', NULL, NULL, NULL, NULL, 21, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (61, '马来语', '50217', NULL, NULL, NULL, NULL, 22, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (62, '蒙古语', '50218', NULL, NULL, NULL, NULL, 23, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (63, '僧伽罗语', '50219', NULL, NULL, NULL, NULL, 24, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (64, '泰语', '50220', NULL, NULL, NULL, NULL, 25, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (65, '乌尔都语', '50221', NULL, NULL, NULL, NULL, 26, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (66, '希伯来语', '50222', NULL, NULL, NULL, NULL, 27, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (67, '越南语', '50223', NULL, NULL, NULL, NULL, 28, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (68, '豪萨语', '50224', NULL, NULL, NULL, NULL, 29, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (69, '斯瓦希里语', '50225', NULL, NULL, NULL, NULL, 30, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (70, '阿尔巴尼亚语', '50226', NULL, NULL, NULL, NULL, 31, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (71, '保加利亚语', '50227', NULL, NULL, NULL, NULL, 32, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (72, '波兰语', '50228', NULL, NULL, NULL, NULL, 33, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (73, '捷克语', '50229', NULL, NULL, NULL, NULL, 34, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (74, '斯洛伐克语', '50230', NULL, NULL, NULL, NULL, 35, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (75, '罗马尼亚语', '50231', NULL, NULL, NULL, NULL, 36, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (76, '葡萄牙语', '50232', NULL, NULL, NULL, NULL, 37, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (77, '瑞典语', '50233', NULL, NULL, NULL, NULL, 38, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (78, '塞尔维亚语', '50234', NULL, NULL, NULL, NULL, 39, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (79, '土耳其语', '50235', NULL, NULL, NULL, NULL, 40, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (80, '希腊语', '50236', NULL, NULL, NULL, NULL, 41, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (81, '匈牙利语', '50237', NULL, NULL, NULL, NULL, 42, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (82, '意大利语', '50238', NULL, NULL, NULL, NULL, 43, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (83, '泰米尔语', '50239', NULL, NULL, NULL, NULL, 44, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (84, '普什图语', '50240', NULL, NULL, NULL, NULL, 45, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (85, '世界语', '50241', NULL, NULL, NULL, NULL, 46, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (86, '孟加拉语', '50242', NULL, NULL, NULL, NULL, 47, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (87, '尼泊尔语', '50243', NULL, NULL, NULL, NULL, 48, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (88, '克罗地亚语', '50244', NULL, NULL, NULL, NULL, 49, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (89, '荷兰语', '50245', NULL, NULL, NULL, NULL, 50, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (90, '芬兰语', '50246', NULL, NULL, NULL, NULL, 51, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (91, '乌克兰语', '50247', NULL, NULL, NULL, NULL, 52, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (92, '挪威语', '50248', NULL, NULL, NULL, NULL, 53, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (93, '丹麦语', '50249', NULL, NULL, NULL, NULL, 54, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (94, '冰岛语', '50250', NULL, NULL, NULL, NULL, 55, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (95, '爱尔兰语', '50251', NULL, NULL, NULL, NULL, 56, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (96, '拉脱维亚语', '50252', NULL, NULL, NULL, NULL, 57, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (97, '立陶宛语', '50253', NULL, NULL, NULL, NULL, 58, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (98, '斯洛文尼亚语', '50254', NULL, NULL, NULL, NULL, 59, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (99, '爱沙尼亚语', '50255', NULL, NULL, NULL, NULL, 60, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (100, '马耳他语', '50256', NULL, NULL, NULL, NULL, 61, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (101, '哈萨克语', '050257', NULL, NULL, NULL, NULL, 62, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (102, '乌兹别克语', '050258', NULL, NULL, NULL, NULL, 63, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (103, '祖鲁语', '050259', NULL, NULL, NULL, NULL, 64, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (104, '拉丁语', '050260', NULL, NULL, NULL, NULL, 65, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (105, '翻译', '50261', NULL, NULL, NULL, NULL, 66, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (106, '商务英语', '50262', NULL, NULL, NULL, NULL, 67, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (107, '新闻学', '50301', NULL, NULL, NULL, NULL, 68, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (108, '广播电视学', '50302', NULL, NULL, NULL, NULL, 69, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (109, '广告学', '50303', NULL, NULL, NULL, NULL, 70, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (110, '传播学', '50304', NULL, NULL, NULL, NULL, 71, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (111, '编辑出版学', '50305', NULL, NULL, NULL, NULL, 72, NULL, NULL, NULL, 5);
INSERT INTO `major_detail` VALUES (113, '历史学', '60101', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 6);
INSERT INTO `major_detail` VALUES (114, '世界史', '60102', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, 6);
INSERT INTO `major_detail` VALUES (115, '考古学', '60103', NULL, NULL, NULL, NULL, 3, NULL, NULL, NULL, 6);
INSERT INTO `major_detail` VALUES (116, '文物与博物馆学', '60104', NULL, NULL, NULL, NULL, 4, NULL, NULL, NULL, 6);
INSERT INTO `major_detail` VALUES (117, '数学与应用数学', '70101', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (118, '信息与计算科学', '70102', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (119, '物理学', '70201', NULL, NULL, NULL, NULL, 3, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (120, '应用物理学', '70202', NULL, NULL, NULL, NULL, 4, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (121, '核物理', '70203', NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (122, '化学', '70301', NULL, NULL, NULL, NULL, 6, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (123, '应用化学（注：可授理学或工学学士学位）', '70302', NULL, NULL, NULL, NULL, 7, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (124, '天文学', '70401', NULL, NULL, NULL, NULL, 8, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (125, '地理科学', '70501', NULL, NULL, NULL, NULL, 9, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (126, '自然地理与资源环境（注：可授理学或管理学学士学位）', '70502', NULL, NULL, NULL, NULL, 10, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (127, '人文地理与城乡规划（注：可授理学或管理学学士学位）', '70503', NULL, NULL, NULL, NULL, 11, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (128, '地理信息科学', '70504', NULL, NULL, NULL, NULL, 12, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (129, '大气科学', '70601', NULL, NULL, NULL, NULL, 13, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (130, '应用气象学', '70602', NULL, NULL, NULL, NULL, 14, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (131, '海洋科学', '70701', NULL, NULL, NULL, NULL, 15, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (132, '海洋技术(注：可授理学或工学学士学位)', '70702', NULL, NULL, NULL, NULL, 16, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (133, '地球物理学', '70801', NULL, NULL, NULL, NULL, 17, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (134, '空间科学与技术（注：可授理学或工学学士学位）', '70802', NULL, NULL, NULL, NULL, 18, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (135, '地质学', '70901', NULL, NULL, NULL, NULL, 19, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (136, '地球化学', '70902', NULL, NULL, NULL, NULL, 20, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (137, '生物科学', '71001', NULL, NULL, NULL, NULL, 21, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (138, '生物技术（注：可授理学或工学学士学位）', '71002', NULL, NULL, NULL, NULL, 22, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (139, '生物信息学（注：可授理学或工学学士学位）', '71003', NULL, NULL, NULL, NULL, 23, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (140, '生态学', '71004', NULL, NULL, NULL, NULL, 24, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (141, '心理学（注：可授理学或教育学学士学位）', '71101', NULL, NULL, NULL, NULL, 25, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (142, '应用心理学（注：可授理学或教育学学士学位）', '71102', NULL, NULL, NULL, NULL, 26, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (143, '统计学', '71201', NULL, NULL, NULL, NULL, 27, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (144, '应用统计学', '71202', NULL, NULL, NULL, NULL, 28, NULL, NULL, NULL, 7);
INSERT INTO `major_detail` VALUES (145, '理论与应用力学（注：可授工学或理学学士学位）', '80101', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (146, '工程力学', '80102', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (147, '机械工程', '80201', NULL, NULL, NULL, NULL, 3, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (148, '机械设计制造及其自动化', '80202', NULL, NULL, NULL, NULL, 4, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (149, '材料成型及控制工程', '80203', NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (150, '机械电子工程', '80204', NULL, NULL, NULL, NULL, 6, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (151, '工业设计', '80205', NULL, NULL, NULL, NULL, 7, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (152, '过程装备与控制工程', '80206', NULL, NULL, NULL, NULL, 8, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (153, '车辆工程', '80207', NULL, NULL, NULL, NULL, 9, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (154, '汽车服务工程', '80208', NULL, NULL, NULL, NULL, 10, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (155, '测控技术与仪器', '80301', NULL, NULL, NULL, NULL, 11, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (156, '材料科学与工程', '80401', NULL, NULL, NULL, NULL, 12, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (157, '材料物理（注：可授工学或理学学士学位）', '80402', NULL, NULL, NULL, NULL, 13, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (158, '材料化学（注：可授工学或理学学士学位）', '80403', NULL, NULL, NULL, NULL, 14, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (159, '冶金工程', '80404', NULL, NULL, NULL, NULL, 15, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (160, '金属材料工程', '80405', NULL, NULL, NULL, NULL, 16, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (161, '无机非金属材料工程', '80406', NULL, NULL, NULL, NULL, 17, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (162, '高分子材料与工程', '80407', NULL, NULL, NULL, NULL, 18, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (163, '复合材料与工程', '80408', NULL, NULL, NULL, NULL, 19, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (164, '能源与动力工程', '80501', NULL, NULL, NULL, NULL, 20, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (165, '电气工程及其自动化', '80601', NULL, NULL, NULL, NULL, 21, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (166, '智能电网信息工程', '080602T', NULL, NULL, NULL, NULL, 22, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (167, '光源与照明', '080603T', NULL, NULL, NULL, NULL, 23, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (168, '电气工程与智能控制', '080604T', NULL, NULL, NULL, NULL, 24, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (169, '电子信息工程（注：可授工学或理学学士学位）', '80701', NULL, NULL, NULL, NULL, 25, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (170, '电子科学与技术（注：可授工学或理学学士学位）', '80702', NULL, NULL, NULL, NULL, 26, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (171, '通信工程', '80703', NULL, NULL, NULL, NULL, 27, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (172, '微电子科学与工程（注：可授工学或理学学士学位）', '80704', NULL, NULL, NULL, NULL, 28, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (173, '光电信息科学与工程（注：可授工学或理学学士学位）', '080705', NULL, NULL, NULL, NULL, 29, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (174, '信息工程', '80706', NULL, NULL, NULL, NULL, 30, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (175, '自动化', '80801', NULL, NULL, NULL, NULL, 31, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (176, '计算机科学与技术（注：可授工学或理学学士学位）', '80901', NULL, NULL, NULL, NULL, 32, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (177, '软件工程', '80902', NULL, NULL, NULL, NULL, 33, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (178, '网络工程', '80903', NULL, NULL, NULL, NULL, 34, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (179, '信息安全（注：可授工学或理学或管理学学士学位）', '080904K', NULL, NULL, NULL, NULL, 35, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (180, '物联网工程', '80905', NULL, NULL, NULL, NULL, 36, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (181, '数字媒体技术', '80906', NULL, NULL, NULL, NULL, 37, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (182, '土木工程', '81001', NULL, NULL, NULL, NULL, 38, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (183, '建筑环境与能源应用工程', '81002', NULL, NULL, NULL, NULL, 39, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (184, '给排水科学与工程', '81003', NULL, NULL, NULL, NULL, 40, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (185, '建筑电气与智能化', '81004', NULL, NULL, NULL, NULL, 41, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (186, '水利水电工程', '81101', NULL, NULL, NULL, NULL, 42, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (187, '水文与水资源工程', '81102', NULL, NULL, NULL, NULL, 43, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (188, '港口航道与海岸工程', '81103', NULL, NULL, NULL, NULL, 44, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (189, '测绘工程', '81201', NULL, NULL, NULL, NULL, 45, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (190, '遥感科学与技术', '81202', NULL, NULL, NULL, NULL, 46, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (191, '化学工程与工艺', '81301', NULL, NULL, NULL, NULL, 47, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (192, '制药工程', '81302', NULL, NULL, NULL, NULL, 48, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (193, '地质工程', '81401', NULL, NULL, NULL, NULL, 49, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (194, '勘查技术与工程', '81402', NULL, NULL, NULL, NULL, 50, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (195, '资源勘查工程', '81403', NULL, NULL, NULL, NULL, 51, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (196, '采矿工程', '81501', NULL, NULL, NULL, NULL, 52, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (197, '石油工程', '81502', NULL, NULL, NULL, NULL, 53, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (198, '矿物加工工程', '81503', NULL, NULL, NULL, NULL, 54, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (199, '油气储运工程', '81504', NULL, NULL, NULL, NULL, 55, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (200, '纺织工程', '81601', NULL, NULL, NULL, NULL, 56, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (201, '服装设计与工程（注：可授工学或艺术学学士学位）', '81602', NULL, NULL, NULL, NULL, 57, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (202, '轻化工程', '81701', NULL, NULL, NULL, NULL, 58, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (203, '包装工程', '81702', NULL, NULL, NULL, NULL, 59, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (204, '印刷工程', '81703', NULL, NULL, NULL, NULL, 60, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (205, '交通运输', '81801', NULL, NULL, NULL, NULL, 61, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (206, '交通工程', '81802', NULL, NULL, NULL, NULL, 62, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (207, '航海技术', '081803K', NULL, NULL, NULL, NULL, 63, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (208, '轮机工程', '081804K', NULL, NULL, NULL, NULL, 64, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (209, '飞行技术', '081805K', NULL, NULL, NULL, NULL, 65, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (210, '船舶与海洋工程', '81901', NULL, NULL, NULL, NULL, 66, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (211, '航空航天工程', '82001', NULL, NULL, NULL, NULL, 67, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (212, '飞行器设计与工程', '82002', NULL, NULL, NULL, NULL, 68, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (213, '飞行器制造工程', '82003', NULL, NULL, NULL, NULL, 69, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (214, '飞行器动力工程', '82004', NULL, NULL, NULL, NULL, 70, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (215, '飞行器环境与生命保障工程', '82005', NULL, NULL, NULL, NULL, 71, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (216, '武器系统与工程', '82101', NULL, NULL, NULL, NULL, 72, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (217, '武器发射工程', '82102', NULL, NULL, NULL, NULL, 73, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (218, '探测制导与控制技术', '82103', NULL, NULL, NULL, NULL, 74, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (219, '弹药工程与爆炸技术', '82104', NULL, NULL, NULL, NULL, 75, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (220, '特种能源技术与工程', '82105', NULL, NULL, NULL, NULL, 76, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (221, '装甲车辆工程', '82106', NULL, NULL, NULL, NULL, 77, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (222, '信息对抗技术', '82107', NULL, NULL, NULL, NULL, 78, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (223, '核工程与核技术', '82201', NULL, NULL, NULL, NULL, 79, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (224, '辐射防护与核安全', '82202', NULL, NULL, NULL, NULL, 80, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (225, '工程物理', '82203', NULL, NULL, NULL, NULL, 81, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (226, '核化工与核燃料工程', '82204', NULL, NULL, NULL, NULL, 82, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (227, '农业工程', '82301', NULL, NULL, NULL, NULL, 83, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (228, '农业机械化及其自动化', '82302', NULL, NULL, NULL, NULL, 84, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (229, '农业电气化', '82303', NULL, NULL, NULL, NULL, 85, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (230, '农业建筑环境与能源工程', '82304', NULL, NULL, NULL, NULL, 86, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (231, '农业水利工程', '82305', NULL, NULL, NULL, NULL, 87, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (232, '森林工程', '82401', NULL, NULL, NULL, NULL, 88, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (233, '木材科学与工程', '82402', NULL, NULL, NULL, NULL, 89, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (234, '林产化工', '82403', NULL, NULL, NULL, NULL, 90, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (235, '环境科学与工程', '82501', NULL, NULL, NULL, NULL, 91, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (236, '环境工程', '82502', NULL, NULL, NULL, NULL, 92, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (237, '环境科学（注：可授工学或理学学士学位）', '82503', NULL, NULL, NULL, NULL, 93, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (238, '环境生态工程', '82504', NULL, NULL, NULL, NULL, 94, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (239, '生物医学工程（注：可授工学或理学学士学位）', '82601', NULL, NULL, NULL, NULL, 95, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (240, '食品科学与工程（注：可授工学或农学学士学位）', '82701', NULL, NULL, NULL, NULL, 96, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (241, '食品质量与安全', '82702', NULL, NULL, NULL, NULL, 97, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (242, '粮食工程', '82703', NULL, NULL, NULL, NULL, 98, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (243, '乳品工程', '82704', NULL, NULL, NULL, NULL, 99, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (244, '酿酒工程', '82705', NULL, NULL, NULL, NULL, 100, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (245, '建筑学', '82801', NULL, NULL, NULL, NULL, 101, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (246, '城乡规划', '82802', NULL, NULL, NULL, NULL, 102, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (247, '风景园林（注：可授工学或艺术学学士学位）', '82803', NULL, NULL, NULL, NULL, 103, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (248, '安全工程', '82901', NULL, NULL, NULL, NULL, 104, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (249, '生物工程', '83001', NULL, NULL, NULL, NULL, 105, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (250, '刑事科学技术', '083101K', NULL, NULL, NULL, NULL, 106, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (251, '消防工程', '083102K', NULL, NULL, NULL, NULL, 107, NULL, NULL, NULL, 8);
INSERT INTO `major_detail` VALUES (253, '农学', '90101', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 9);
INSERT INTO `major_detail` VALUES (254, '园艺', '90102', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, 9);
INSERT INTO `major_detail` VALUES (255, '植物保护', '90103', NULL, NULL, NULL, NULL, 3, NULL, NULL, NULL, 9);
INSERT INTO `major_detail` VALUES (256, '植物科学与技术', '90104', NULL, NULL, NULL, NULL, 4, NULL, NULL, NULL, 9);
INSERT INTO `major_detail` VALUES (257, '种子科学与工程', '90105', NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, 9);
INSERT INTO `major_detail` VALUES (258, '设施农业科学与工程（注：可授农学或工学学士学位）', '90106', NULL, NULL, NULL, NULL, 6, NULL, NULL, NULL, 9);
INSERT INTO `major_detail` VALUES (259, '农业资源与环境', '90201', NULL, NULL, NULL, NULL, 7, NULL, NULL, NULL, 9);
INSERT INTO `major_detail` VALUES (260, '野生动物与自然保护区管理', '90202', NULL, NULL, NULL, NULL, 8, NULL, NULL, NULL, 9);
INSERT INTO `major_detail` VALUES (261, '水土保持与荒漠化防治', '90203', NULL, NULL, NULL, NULL, 9, NULL, NULL, NULL, 9);
INSERT INTO `major_detail` VALUES (262, '动物科学', '90301', NULL, NULL, NULL, NULL, 10, NULL, NULL, NULL, 9);
INSERT INTO `major_detail` VALUES (263, '动物医学', '90401', NULL, NULL, NULL, NULL, 11, NULL, NULL, NULL, 9);
INSERT INTO `major_detail` VALUES (264, '动物药学', '90402', NULL, NULL, NULL, NULL, 12, NULL, NULL, NULL, 9);
INSERT INTO `major_detail` VALUES (265, '林学', '90501', NULL, NULL, NULL, NULL, 13, NULL, NULL, NULL, 9);
INSERT INTO `major_detail` VALUES (266, '园林', '90502', NULL, NULL, NULL, NULL, 14, NULL, NULL, NULL, 9);
INSERT INTO `major_detail` VALUES (267, '森林保护', '90503', NULL, NULL, NULL, NULL, 15, NULL, NULL, NULL, 9);
INSERT INTO `major_detail` VALUES (268, '水产养殖学', '90601', NULL, NULL, NULL, NULL, 16, NULL, NULL, NULL, 9);
INSERT INTO `major_detail` VALUES (269, '海洋渔业科学与技术', '90602', NULL, NULL, NULL, NULL, 17, NULL, NULL, NULL, 9);
INSERT INTO `major_detail` VALUES (270, '草业科学', '90701', NULL, NULL, NULL, NULL, 18, NULL, NULL, NULL, 9);
INSERT INTO `major_detail` VALUES (271, '基础医学', '100101K', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (272, '临床医学', '100201K', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (273, '口腔医学', '100301K', NULL, NULL, NULL, NULL, 3, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (274, '预防医学', '100401K', NULL, NULL, NULL, NULL, 4, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (275, '食品卫生与营养学（注：授予理学学士学位）', '100402', NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (276, '中医学', '100501K', NULL, NULL, NULL, NULL, 6, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (277, '针灸推拿学', '100502K', NULL, NULL, NULL, NULL, 7, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (278, '藏医学', '100503K', NULL, NULL, NULL, NULL, 8, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (279, '蒙医学', '100504K', NULL, NULL, NULL, NULL, 9, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (280, '维医学', '100505K', NULL, NULL, NULL, NULL, 10, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (281, '壮医学', '100506K', NULL, NULL, NULL, NULL, 11, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (282, '哈医学', '100507K', NULL, NULL, NULL, NULL, 12, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (283, '中西医临床医学', '100601K', NULL, NULL, NULL, NULL, 13, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (284, '药学（注：授予理学学士学位）', '100701', NULL, NULL, NULL, NULL, 14, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (285, '药物制剂（注：授予理学学士学位）', '100702', NULL, NULL, NULL, NULL, 15, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (286, '中药学（注：授予理学学士学位）', '100801', NULL, NULL, NULL, NULL, 16, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (287, '中药资源与开发（注：授予理学学士学位）', '100802', NULL, NULL, NULL, NULL, 17, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (288, '法医学', '100901K', NULL, NULL, NULL, NULL, 18, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (289, '医学检验技术（注：授予理学学士学位）', '101001', NULL, NULL, NULL, NULL, 19, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (290, '医学实验技术（注：授予理学学士学位）', '101002', NULL, NULL, NULL, NULL, 20, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (291, '医学影像技术（注：授予理学学士学位）', '101003', NULL, NULL, NULL, NULL, 21, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (292, '眼视光学（注：授予理学学士学位）', '101004', NULL, NULL, NULL, NULL, 22, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (293, '康复治疗学（注：授予理学学士学位）', '101005', NULL, NULL, NULL, NULL, 23, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (294, '口腔医学技术（注：授予理学学士学位）', '101006', NULL, NULL, NULL, NULL, 24, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (295, '卫生检验与检疫（注：授予理学学士学位）', '101007', NULL, NULL, NULL, NULL, 25, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (296, '护理学（注：授予理学学士学位）', '101101', NULL, NULL, NULL, NULL, 26, NULL, NULL, NULL, 10);
INSERT INTO `major_detail` VALUES (298, '管理科学（注：可授管理学或理学学士学位）', '120101', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (299, '信息管理与信息系统（注：可授管理学或工学学士学位）', '120102', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (300, '工程管理（注：可授管理学或工学学士学位）', '120103', NULL, NULL, NULL, NULL, 3, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (301, '房地产开发与管理', '120104', NULL, NULL, NULL, NULL, 4, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (302, '工程造价（注：可授管理学或工学学士学位）', '120105', NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (303, '工商管理', '120201K', NULL, NULL, NULL, NULL, 6, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (304, '市场营销', '120202', NULL, NULL, NULL, NULL, 7, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (305, '会计学', '120203K', NULL, NULL, NULL, NULL, 8, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (306, '财务管理', '120204', NULL, NULL, NULL, NULL, 9, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (307, '国际商务', '120205', NULL, NULL, NULL, NULL, 10, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (308, '人力资源管理', '120206', NULL, NULL, NULL, NULL, 11, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (309, '审计学', '120207', NULL, NULL, NULL, NULL, 12, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (310, '资产评估', '120208', NULL, NULL, NULL, NULL, 13, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (311, '物业管理', '120209', NULL, NULL, NULL, NULL, 14, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (312, '文化产业管理（注：可授管理学或艺术学学士学位）', '120210', NULL, NULL, NULL, NULL, 15, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (313, '农林经济管理', '120301', NULL, NULL, NULL, NULL, 16, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (314, '农村区域发展（注：可授管理学或农学学士学位）', '120302', NULL, NULL, NULL, NULL, 17, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (315, '公共事业管理', '120401', NULL, NULL, NULL, NULL, 18, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (316, '行政管理', '120402', NULL, NULL, NULL, NULL, 19, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (317, '劳动与社会保障', '120403', NULL, NULL, NULL, NULL, 20, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (318, '土地资源管理（注：可授管理学或工学学士学位）', '120404', NULL, NULL, NULL, NULL, 21, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (319, '城市管理', '120405', NULL, NULL, NULL, NULL, 22, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (320, '图书馆学', '120501', NULL, NULL, NULL, NULL, 23, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (321, '档案学', '120502', NULL, NULL, NULL, NULL, 24, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (322, '信息资源管理', '120503', NULL, NULL, NULL, NULL, 25, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (323, '物流管理', '120601', NULL, NULL, NULL, NULL, 26, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (324, '物流工程（注：可授管理学或工学学士学位）', '120602', NULL, NULL, NULL, NULL, 27, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (325, '工业工程（注：可授管理学或工学学士学位）', '120701', NULL, NULL, NULL, NULL, 28, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (326, '电子商务（注：可授管理学或经济学或工学学士学位）', '120801', NULL, NULL, NULL, NULL, 29, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (327, '旅游管理', '120901K', NULL, NULL, NULL, NULL, 30, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (328, '酒店管理', '120902', NULL, NULL, NULL, NULL, 31, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (329, '会展经济与管理', '120903', NULL, NULL, NULL, NULL, 32, NULL, NULL, NULL, 11);
INSERT INTO `major_detail` VALUES (330, '艺术史论', '130101', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (331, '音乐表演', '130201', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (332, '音乐学', '130202', NULL, NULL, NULL, NULL, 3, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (333, '作曲与作曲技术理论', '130203', NULL, NULL, NULL, NULL, 4, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (334, '舞蹈表演', '130204', NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (335, '舞蹈学', '130205', NULL, NULL, NULL, NULL, 6, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (336, '舞蹈编导', '130206', NULL, NULL, NULL, NULL, 7, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (337, '表演', '130301', NULL, NULL, NULL, NULL, 8, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (338, '戏剧学', '130302', NULL, NULL, NULL, NULL, 9, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (339, '电影学', '130303', NULL, NULL, NULL, NULL, 10, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (340, '戏剧影视文学', '130304', NULL, NULL, NULL, NULL, 11, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (341, '广播电视编导', '130305', NULL, NULL, NULL, NULL, 12, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (342, '戏剧影视导演', '130306', NULL, NULL, NULL, NULL, 13, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (343, '戏剧影视美术设计', '130307', NULL, NULL, NULL, NULL, 14, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (344, '录音艺术', '130308', NULL, NULL, NULL, NULL, 15, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (345, '播音与主持艺术', '130309', NULL, NULL, NULL, NULL, 16, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (346, '动画', '130310', NULL, NULL, NULL, NULL, 17, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (347, '美术学', '130401', NULL, NULL, NULL, NULL, 18, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (348, '绘画', '130402', NULL, NULL, NULL, NULL, 19, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (349, '雕塑', '130403', NULL, NULL, NULL, NULL, 20, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (350, '摄影', '130404', NULL, NULL, NULL, NULL, 21, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (351, '艺术设计学', '130501', NULL, NULL, NULL, NULL, 22, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (352, '视觉传达设计', '130502', NULL, NULL, NULL, NULL, 23, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (353, '环境设计', '130503', NULL, NULL, NULL, NULL, 24, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (354, '产品设计', '130504', NULL, NULL, NULL, NULL, 25, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (355, '服装与服饰设计', '130505', NULL, NULL, NULL, NULL, 26, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (356, '公共艺术', '130506', NULL, NULL, NULL, NULL, 27, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (357, '工艺美术', '130507', NULL, NULL, NULL, NULL, 28, NULL, NULL, NULL, 12);
INSERT INTO `major_detail` VALUES (358, '数字媒体艺术', '130508', NULL, NULL, NULL, NULL, 29, NULL, NULL, NULL, 12);

-- ----------------------------
-- Table structure for relation_major_course
-- ----------------------------
DROP TABLE IF EXISTS `relation_major_course`;
CREATE TABLE `relation_major_course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `major_id` int(11) NULL DEFAULT NULL,
  `course_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for subject_category
-- ----------------------------
DROP TABLE IF EXISTS `subject_category`;
CREATE TABLE `subject_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学科名字',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学科代码',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学科描述',
  `icon_uri` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标路径',
  `order` int(2) NULL DEFAULT NULL COMMENT '显示排序号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学科分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject_category
-- ----------------------------
INSERT INTO `subject_category` VALUES (1, '哲学', '01', NULL, NULL, 1);
INSERT INTO `subject_category` VALUES (2, '经济学', '02', NULL, NULL, 2);
INSERT INTO `subject_category` VALUES (3, '法学', '03', NULL, NULL, 3);
INSERT INTO `subject_category` VALUES (4, '教育学', '04', NULL, NULL, 4);
INSERT INTO `subject_category` VALUES (5, '文学', '05', NULL, NULL, 5);
INSERT INTO `subject_category` VALUES (6, '历史学', '06', NULL, NULL, 6);
INSERT INTO `subject_category` VALUES (7, '理学', '07', NULL, NULL, 7);
INSERT INTO `subject_category` VALUES (8, '工学', '08', NULL, NULL, 8);
INSERT INTO `subject_category` VALUES (9, '农学', '09', NULL, NULL, 9);
INSERT INTO `subject_category` VALUES (10, '医学', '10', NULL, NULL, 10);
INSERT INTO `subject_category` VALUES (11, '管理学', '12', NULL, NULL, 11);
INSERT INTO `subject_category` VALUES (12, '艺术学', '13', NULL, NULL, 12);

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `video_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频地址',
  `is_auth` int(1) NULL DEFAULT NULL COMMENT '是否取得授权',
  `order` int(2) NULL DEFAULT NULL COMMENT '排序号',
  `course_id` int(10) NULL DEFAULT NULL COMMENT '课程id',
  `major_id` int(10) NULL DEFAULT NULL COMMENT '学科id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
