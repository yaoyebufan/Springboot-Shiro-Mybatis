/*
Navicat MySQL Data Transfer

Source Server         : sunyue
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2021-02-02 21:51:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for test_user
-- ----------------------------
DROP TABLE IF EXISTS `test_user`;
CREATE TABLE `test_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(120) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `perms` varchar(120) DEFAULT NULL,
  `role` varchar(120) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of test_user
-- ----------------------------
INSERT INTO `test_user` VALUES ('4', 'admin', '4867df2e009d0096c4cd8d9be8cc104c', 'manage', 'admin', 'GQR2m1N1o3nSLjtOzMITRQ==');
INSERT INTO `test_user` VALUES ('5', 'user', '636502f40cf197dd2f4b19f56f475b24', '', '', 'Kxw3HZiFmgnlUu8fmjMY7Q==');
INSERT INTO `test_user` VALUES ('6', 'user1', '43f3133aa7e0ef9cf8373521dff8d8e8', 'manage', null, 'J8fn4HpauvNOrlUaRl/Spg==');
INSERT INTO `test_user` VALUES ('7', '1', '1', 'manage', null, null);
