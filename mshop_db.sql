/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : mshop_db

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 13/05/2024 13:57:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `encryption` tinyint NULL DEFAULT 0 COMMENT '加密方式0无加密1MD5',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '账号创建时间',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '资料更新时间',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `auth_type` tinyint NULL DEFAULT NULL COMMENT '权限类型 0普通用户1管理员',
  `black_list` tinyint NULL DEFAULT NULL COMMENT '是否黑名单用户,无法登录 0否 1是',
  `roles` json NULL COMMENT '角色列表',
  `hide_user` tinyint NULL DEFAULT NULL COMMENT '是否隐藏用户，隐藏用户可登录，在用户列表不可见 0非 1是',
  `encryption_key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '加密KEY，当密码为MD5时存储盐值',
  `assets` json NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `admin_user_mobile_index`(`mobile` ASC) USING BTREE,
  INDEX `admin_user_username_index`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES (1, 'root', '15830315442', 'liujun@liuujun.com', '123456', 0, '2024-05-08 10:57:28', '2024-05-08 10:57:28', '超级管理员', 1, 0, NULL, 1, NULL, NULL);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `route_path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '页面路径',
  `route_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '页面路由标识',
  `route_title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '页面描述',
  `component_path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '页面组件路径',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父级ID',
  `sort_id` int NULL DEFAULT 0 COMMENT '排序ID',
  `del_flag` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  `route_type` tinyint NULL DEFAULT NULL COMMENT '类型1页面2标识',
  `icon` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `hide` tinyint NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '/setting', 'setting', '系统设置', NULL, 0, 1, 0, NULL, 'Icon-Setting', 0);
INSERT INTO `permission` VALUES (2, '/setting/permissionAdmin', 'permissionAdmin', '权限管理', '/src/views/auth/PermissionView.vue', 1, 0, 0, NULL, 'Icon-Operation', 0);
INSERT INTO `permission` VALUES (3, '/setting/userAdmin', 'userAdmin', '用户管理', '/src/views/auth/AdminUserView.vue', 1, 0, 0, NULL, 'Icon-User', 0);
INSERT INTO `permission` VALUES (4, '/setting/roleAdmin', 'roleAdmin', '角色管理', '/src/views/auth/RoleView.vue', 1, 0, 0, NULL, 'Icon-Coordinate', 0);
INSERT INTO `permission` VALUES (5, '/dashboard', 'dashboard', '首页', '/src/views/dashboard/index.vue', 0, 0, 0, NULL, 'Icon-DataAnalysis', 0);
INSERT INTO `permission` VALUES (6, '/operate', 'operate', '运营中心', NULL, 0, 1, 0, NULL, 'Icon-DataAnalysis', 0);
INSERT INTO `permission` VALUES (7, '/operate/commodify', 'commodity', '商品管理', '/src/views/commodity/CommodityList.vue', 6, 0, 0, NULL, 'Icon-ShoppingBag', 0);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `permissions` json NULL,
  `del_flag` tinyint NULL DEFAULT 0,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
