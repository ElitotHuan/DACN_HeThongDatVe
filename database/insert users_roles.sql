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

 Date: 26/05/2023 08:33:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for users_roles
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles`  (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  INDEX `FKj6m8fwv7oqv74fcehir1a9ffy`(`role_id` ASC) USING BTREE,
  INDEX `FKgd3iendaoyh04b95ykqise6qh`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users_roles
-- ----------------------------
INSERT INTO `users_roles` VALUES (1, 2);
INSERT INTO `users_roles` VALUES (2, 1);
INSERT INTO `users_roles` VALUES (3, 1);
INSERT INTO `users_roles` VALUES (21, 2);
INSERT INTO `users_roles` VALUES (22, 2);

SET FOREIGN_KEY_CHECKS = 1;
