/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100427
 Source Host           : localhost:3307
 Source Schema         : ticketbooking

 Target Server Type    : MySQL
 Target Server Version : 100427
 File Encoding         : 65001

 Date: 26/05/2023 08:32:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `full_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'ducnightcore12@gmail.com', 'duc', '$2a$10$6hms89pblgJ/UKbUnkFYxuswkoYpH7FQXvRvqPUhoWtsEnk8ikIxO', 'duc', NULL, NULL);
INSERT INTO `user` VALUES (2, 'huan@gmail.com', 'huan', '$2a$10$QlmPwnVOOLmMJPG/QWArBed7/TP.oTrEYNuLFfE/vHoIjAl8dH0w.', 'huan', NULL, NULL);
INSERT INTO `user` VALUES (3, 'voduyloc@gmail.com', 'loc', '$2a$10$3RXCie6v0zVvlcqWZcYxmODpBIfruAoyxtzPN.bFKn96CRehs02Mu', 'voduyloc', NULL, NULL);
INSERT INTO `user` VALUES (21, 'thicac@gmail.com', 'thicac', '$2a$10$2mESQgRXVDoWPJGEStu7x.KKcNr.6QLWLM2wGZcbmsvooM2W/Fc5G', 'thicac', NULL, NULL);
INSERT INTO `user` VALUES (22, 'zzzlolzzz24@gmail.com', 'voduyloc', '$2a$10$pi7ZFm43LzU0bw6CxWmMb.FoPHhzOTalleDFgH18RJMT3bZoEqmJC', 'duyloc', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
