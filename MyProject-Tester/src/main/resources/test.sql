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

 Date: 06/12/2020 22:12:17
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
  PRIMARY KEY (`FId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_base_user
-- ----------------------------
INSERT INTO `t_base_user` VALUES (6, '李四', '123456', 23, 'habuma-20');
INSERT INTO `t_base_user` VALUES (7, '李四', '123456', 23, 'habuma-20');

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
