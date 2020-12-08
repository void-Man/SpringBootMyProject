/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 08/12/2020 19:03:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_base_user
-- ----------------------------
DROP TABLE IF EXISTS `t_base_user`;
CREATE TABLE `t_base_user`  (
  `FId` int(11) NOT NULL AUTO_INCREMENT,
  `FName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '姓名',
  `FPassword` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `FAge` int(11) NOT NULL DEFAULT 0 COMMENT '年龄',
  `FOuterId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '外部ID',
  `FCreateTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `FUpdateTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `FDeleteTime` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `FCreator` int(11) NOT NULL DEFAULT 0 COMMENT '创建者ID',
  `FUpdater` int(11) NOT NULL DEFAULT 0 COMMENT '更新者ID',
  `FDeleter` int(11) NULL DEFAULT NULL COMMENT '删除者ID',
  `FIsDelete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除（1是；0否）',
  PRIMARY KEY (`FId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_base_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_ebuy_product
-- ----------------------------
DROP TABLE IF EXISTS `t_ebuy_product`;
CREATE TABLE `t_ebuy_product`  (
  `FId` int(11) NOT NULL AUTO_INCREMENT,
  `FName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `FPrice` decimal(20, 2) NOT NULL DEFAULT 0.00 COMMENT '商品价格（单位：分）',
  PRIMARY KEY (`FId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ebuy_product
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
