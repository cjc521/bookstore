/*
 Navicat Premium Data Transfer

 Source Server         : helloworld
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : bookstore

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 22/10/2020 20:47:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `order_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_id` int(100) NOT NULL,
  `buynum` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`, `product_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('0050fd04-5396-440e-b021-cce066088672', 1, 9);
INSERT INTO `orderitem` VALUES ('03794855-85b1-41eb-9335-d7c561deb25a', 2, 1);
INSERT INTO `orderitem` VALUES ('03a0ca21-603f-4b41-ae37-e5d3b19a2d13', 4, 1);
INSERT INTO `orderitem` VALUES ('0bc41b05-4327-42eb-9183-085f4d4a5d0c', 2, 1);
INSERT INTO `orderitem` VALUES ('11da04ca-db90-4bd0-974a-66ccacfba17a', 1, 2);
INSERT INTO `orderitem` VALUES ('14200284-cab2-4cde-8e56-fa8be93853c1', 2, 1);
INSERT INTO `orderitem` VALUES ('14200284-cab2-4cde-8e56-fa8be93853c1', 4, 1);
INSERT INTO `orderitem` VALUES ('1485f3f8-22ee-4f0b-a1fa-58604d5202b3', 4, 1);
INSERT INTO `orderitem` VALUES ('169fa64c-91fb-4581-ac16-145f0d41d37c', 2, 2);
INSERT INTO `orderitem` VALUES ('182677bc-90a0-435e-a265-6021ab42bbdd', 4, 1);
INSERT INTO `orderitem` VALUES ('18e9427f-4a8f-4450-935d-d05ab2c3b192', 1, 1);
INSERT INTO `orderitem` VALUES ('19193d7f-d712-4d47-adc5-39a8f6756277', 7, 1);
INSERT INTO `orderitem` VALUES ('1ac6e814-978c-41eb-bb3c-cbb3462f8872', 1, 1);
INSERT INTO `orderitem` VALUES ('1da0e4dd-2aca-4b08-b74c-56e8f85381e1', 4, 1);
INSERT INTO `orderitem` VALUES ('297ceeb9-f1cf-40fd-8917-5c1dd8ec527c', 2, 1);
INSERT INTO `orderitem` VALUES ('32bc8933-2773-494c-a205-dd7745c7f4c5', 4, 1);
INSERT INTO `orderitem` VALUES ('3b93625f-279a-4711-ac20-4d012c2e8e34', 2, 1);
INSERT INTO `orderitem` VALUES ('3c34f035-3a40-4034-8a5b-eb99c717d7b1', 1, 1);
INSERT INTO `orderitem` VALUES ('3f1e2f56-569f-4e0a-a91f-8a64b9841864', 4, 1);
INSERT INTO `orderitem` VALUES ('4672355c-eae2-414e-b4f5-284a3cdd23a4', 2, 1);
INSERT INTO `orderitem` VALUES ('4672355c-eae2-414e-b4f5-284a3cdd23a4', 4, 1);
INSERT INTO `orderitem` VALUES ('4b845445-ed2e-419c-9ac5-7e6703b3460b', 2, 1);
INSERT INTO `orderitem` VALUES ('4b9459c7-7e20-4472-9408-9474f087a1dc', 1, 1);
INSERT INTO `orderitem` VALUES ('4bd7971d-b3ec-4dba-8d87-a846b28adfbc', 4, 1);
INSERT INTO `orderitem` VALUES ('57e56b15-0b21-488e-8d4d-cb4933fb8f4d', 4, 1);
INSERT INTO `orderitem` VALUES ('6144d142-5dd5-47ab-b7d0-bc426550c106', 4, 1);
INSERT INTO `orderitem` VALUES ('67447163-9b73-49e1-8ded-8f00d7f5b66d', 4, 1);
INSERT INTO `orderitem` VALUES ('6a6537e9-1edb-40f2-b594-ea67227f8097', 2, 1);
INSERT INTO `orderitem` VALUES ('6d4d71ea-1daf-4b6b-bde4-a92c611cc0c0', 2, 1);
INSERT INTO `orderitem` VALUES ('6f9a17aa-4691-4546-bda8-367054bb05d8', 4, 1);
INSERT INTO `orderitem` VALUES ('70e2d5bc-6de1-4b40-a74e-ed649d51fc4d', 1, 1);
INSERT INTO `orderitem` VALUES ('70e2d5bc-6de1-4b40-a74e-ed649d51fc4d', 2, 1);
INSERT INTO `orderitem` VALUES ('70e2d5bc-6de1-4b40-a74e-ed649d51fc4d', 4, 1);
INSERT INTO `orderitem` VALUES ('7401b76c-f639-4d80-9125-8bfe9703d6a0', 1, 1);
INSERT INTO `orderitem` VALUES ('745ee3fb-4025-4013-92c3-8e93c0ee7e4d', 2, 1);
INSERT INTO `orderitem` VALUES ('7dc1b43f-5dd9-4fda-b6fb-3146732a9087', 2, 1);
INSERT INTO `orderitem` VALUES ('7f9f1f21-9d51-4491-b02c-12dce8161597', 2, 1);
INSERT INTO `orderitem` VALUES ('8165e238-2499-4ecd-9c52-da8e6e0e37dc', 4, 1);
INSERT INTO `orderitem` VALUES ('8419778c-3577-4cba-b2ab-ce69ae8d5f09', 4, 1);
INSERT INTO `orderitem` VALUES ('8a111536-6a40-4cd0-8b14-506ca1f3b053', 4, 1);
INSERT INTO `orderitem` VALUES ('8d6efaf5-230a-49c5-ac02-bc89132fb33d', 4, 1);
INSERT INTO `orderitem` VALUES ('96f73bb1-9fef-4823-9299-837e6f0358ff', 4, 1);
INSERT INTO `orderitem` VALUES ('98720112-6253-4b0e-bf83-948a15655bce', 2, 1);
INSERT INTO `orderitem` VALUES ('9fd7b320-fa88-444b-89c3-30097fe1cc5d', 1, 1);
INSERT INTO `orderitem` VALUES ('a2dbd9ee-ab53-45e4-9993-0166f388f2ae', 4, 1);
INSERT INTO `orderitem` VALUES ('a8847c0c-5fec-4e36-9068-9a146ebd785e', 1, 1);
INSERT INTO `orderitem` VALUES ('b3528dd8-0cc0-4909-90c4-f0c36db5198b', 4, 1);
INSERT INTO `orderitem` VALUES ('b3c042e0-ad88-4e85-ac58-dcdc004bed13', 2, 1);
INSERT INTO `orderitem` VALUES ('c0b6cea9-5721-4f2d-9cc1-d1975a534af9', 4, 1);
INSERT INTO `orderitem` VALUES ('c295a1e5-acec-41cb-98d0-66b017c70dd5', 4, 2);
INSERT INTO `orderitem` VALUES ('d5f44fd8-533a-4742-9c35-fe67aedb839e', 1, 1);
INSERT INTO `orderitem` VALUES ('d5f44fd8-533a-4742-9c35-fe67aedb839e', 2, 1);
INSERT INTO `orderitem` VALUES ('e7404ef9-6066-48a2-a98a-91bd1e7de2da', 4, 1);
INSERT INTO `orderitem` VALUES ('e897dc90-0c29-4a5f-8d93-fdecebbfb147', 2, 5);
INSERT INTO `orderitem` VALUES ('e897dc90-0c29-4a5f-8d93-fdecebbfb147', 4, 3);
INSERT INTO `orderitem` VALUES ('eb84b23a-8947-4e38-b4e7-c0b01fce0ec7', 2, 1);
INSERT INTO `orderitem` VALUES ('f3caf483-9bb2-47a3-b4d0-604e7b9021a2', 13, 1);
INSERT INTO `orderitem` VALUES ('f3caf483-9bb2-47a3-b4d0-604e7b9021a2', 15, 1);
INSERT INTO `orderitem` VALUES ('f4330bc4-906c-4421-bf80-b2cace95c376', 2, 1);
INSERT INTO `orderitem` VALUES ('f4e6ca22-708b-4927-9c43-1c844a9eadf7', 6, 2);
INSERT INTO `orderitem` VALUES ('f5475010-ac42-4043-ac5b-030c01cc55b9', 4, 1);
INSERT INTO `orderitem` VALUES ('f752d6db-c9fc-49a9-b6eb-f223fe3acfad', 1, 1);
INSERT INTO `orderitem` VALUES ('fe23f0f5-6e58-4a26-8ad0-36036dfb9f85', 4, 1);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `money` double NULL DEFAULT NULL,
  `receiverAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiverName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiverPhone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payState` int(11) NULL DEFAULT NULL,
  `ordertime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('0050fd04-5396-440e-b021-cce066088672', 99, '', '9', '', 1, '2020-06-18 10:32:45', 4);
INSERT INTO `orders` VALUES ('03794855-85b1-41eb-9335-d7c561deb25a', 999, '1', '9', '1', 1, '2020-06-17 13:46:10', 4);
INSERT INTO `orders` VALUES ('03a0ca21-603f-4b41-ae37-e5d3b19a2d13', 999, '1', '1', '1', 1, '2020-06-18 10:37:49', 4);
INSERT INTO `orders` VALUES ('0bc41b05-4327-42eb-9183-085f4d4a5d0c', 0, '3', '3', '3', 1, '2020-06-18 10:44:46', 4);
INSERT INTO `orders` VALUES ('11da04ca-db90-4bd0-974a-66ccacfba17a', 198, '1', '1', '1', 1, '2020-06-18 10:51:50', 4);
INSERT INTO `orders` VALUES ('14200284-cab2-4cde-8e56-fa8be93853c1', 0, '', '', '', 1, '2020-06-18 10:58:14', 4);
INSERT INTO `orders` VALUES ('1485f3f8-22ee-4f0b-a1fa-58604d5202b3', 0, '', '', '', 0, '2020-06-16 17:25:25', 4);
INSERT INTO `orders` VALUES ('169fa64c-91fb-4581-ac16-145f0d41d37c', 1998, '3', '3', '3', 1, '2020-06-18 10:46:14', 4);
INSERT INTO `orders` VALUES ('182677bc-90a0-435e-a265-6021ab42bbdd', 999, '1', '1', '1', 1, '2020-06-18 10:56:17', 4);
INSERT INTO `orders` VALUES ('18e9427f-4a8f-4450-935d-d05ab2c3b192', 99, '', '', '', 0, '2020-06-17 08:39:47', 4);
INSERT INTO `orders` VALUES ('19193d7f-d712-4d47-adc5-39a8f6756277', 30, '4', '4', '4', 1, '2020-06-18 11:29:13', 4);
INSERT INTO `orders` VALUES ('1ac6e814-978c-41eb-bb3c-cbb3462f8872', 99, '新余学院', 'cjc', '110', 1, '2020-06-17 15:12:33', 8);
INSERT INTO `orders` VALUES ('1da0e4dd-2aca-4b08-b74c-56e8f85381e1', 999, '新余学院', 'cjc', '110', 1, '2020-06-17 15:37:22', 8);
INSERT INTO `orders` VALUES ('297ceeb9-f1cf-40fd-8917-5c1dd8ec527c', 0, '', '', '', 0, '2020-06-16 17:10:52', 4);
INSERT INTO `orders` VALUES ('32bc8933-2773-494c-a205-dd7745c7f4c5', 999, '1', '1', '1', 0, '2020-06-16 23:26:19', 4);
INSERT INTO `orders` VALUES ('3b93625f-279a-4711-ac20-4d012c2e8e34', 0, '2', '3', '3', 0, '2020-06-16 17:32:50', 4);
INSERT INTO `orders` VALUES ('3c34f035-3a40-4034-8a5b-eb99c717d7b1', 999, '', '', '', 1, '2020-06-19 09:56:21', 4);
INSERT INTO `orders` VALUES ('3f1e2f56-569f-4e0a-a91f-8a64b9841864', 999, '1', '1', '1', 0, '2020-06-16 21:21:31', 4);
INSERT INTO `orders` VALUES ('44f06482-7e34-43bd-92e3-3190ded8a161', 0, '', '', '', 1, '2020-06-18 09:45:10', 4);
INSERT INTO `orders` VALUES ('4672355c-eae2-414e-b4f5-284a3cdd23a4', 0, '', '', '', 0, '2020-06-16 12:15:55', 4);
INSERT INTO `orders` VALUES ('4b845445-ed2e-419c-9ac5-7e6703b3460b', 999, '新余学院', 'cjc', '110', 1, '2020-06-17 15:36:15', 8);
INSERT INTO `orders` VALUES ('4b9459c7-7e20-4472-9408-9474f087a1dc', 99, '4', '4', '4', 0, '2020-06-18 09:34:00', 4);
INSERT INTO `orders` VALUES ('4bd7971d-b3ec-4dba-8d87-a846b28adfbc', 999, '1', '1', '1', 0, '2020-06-16 21:19:31', 4);
INSERT INTO `orders` VALUES ('57e56b15-0b21-488e-8d4d-cb4933fb8f4d', 999, '', '', '', 1, '2020-06-18 23:30:45', 4);
INSERT INTO `orders` VALUES ('6144d142-5dd5-47ab-b7d0-bc426550c106', 999, '', '', '', 1, '2020-06-18 10:24:25', 4);
INSERT INTO `orders` VALUES ('653952b6-4024-4057-b456-62e54ee3e167', 0, '', '', '', 1, '2020-06-18 09:44:07', 4);
INSERT INTO `orders` VALUES ('67447163-9b73-49e1-8ded-8f00d7f5b66d', 999, '1', '1', '1', 0, '2020-06-16 21:23:03', 4);
INSERT INTO `orders` VALUES ('6a6537e9-1edb-40f2-b594-ea67227f8097', 999, '1', '1', '1', 0, '2020-06-16 22:32:55', 4);
INSERT INTO `orders` VALUES ('6d4d71ea-1daf-4b6b-bde4-a92c611cc0c0', 999, '1', '1', '1', 0, '2020-06-16 23:24:08', 4);
INSERT INTO `orders` VALUES ('6f9a17aa-4691-4546-bda8-367054bb05d8', 999, '1', '1', '1', 1, '2020-06-17 08:20:54', 4);
INSERT INTO `orders` VALUES ('70e2d5bc-6de1-4b40-a74e-ed649d51fc4d', 2097, '新余学院', 'cjc', '110', 1, '2020-06-17 15:01:38', 8);
INSERT INTO `orders` VALUES ('7401b76c-f639-4d80-9125-8bfe9703d6a0', 0, '1', '1', '1', 0, '2020-06-15 10:20:53', 4);
INSERT INTO `orders` VALUES ('745ee3fb-4025-4013-92c3-8e93c0ee7e4d', 999, '', '', '', 1, '2020-06-18 10:45:23', 4);
INSERT INTO `orders` VALUES ('7dc1b43f-5dd9-4fda-b6fb-3146732a9087', 999, '', '', '', 0, '2020-06-16 23:21:57', 4);
INSERT INTO `orders` VALUES ('7f9f1f21-9d51-4491-b02c-12dce8161597', 999, '4', '4', '4', 1, '2020-06-18 10:08:57', 4);
INSERT INTO `orders` VALUES ('8165e238-2499-4ecd-9c52-da8e6e0e37dc', 999, '', '', '', 0, '2020-06-17 09:21:14', 4);
INSERT INTO `orders` VALUES ('8419778c-3577-4cba-b2ab-ce69ae8d5f09', 999, '0', '0', '0', 1, '2020-06-17 08:28:29', 4);
INSERT INTO `orders` VALUES ('8a111536-6a40-4cd0-8b14-506ca1f3b053', 999, '1', '1', '1', 0, '2020-06-16 21:23:46', 4);
INSERT INTO `orders` VALUES ('8d6efaf5-230a-49c5-ac02-bc89132fb33d', 999, '1', '1', '1', 0, '2020-06-16 21:22:00', 4);
INSERT INTO `orders` VALUES ('921de2b7-b46d-48fc-b1ff-aeb8debb4a3c', 0, '', '', '', 1, '2020-06-18 09:47:27', 4);
INSERT INTO `orders` VALUES ('96f73bb1-9fef-4823-9299-837e6f0358ff', 999, '新余学院', 'cjc', '110', 1, '2020-06-17 15:17:59', 4);
INSERT INTO `orders` VALUES ('98720112-6253-4b0e-bf83-948a15655bce', 999, '', '', '', 1, '2020-06-18 10:22:14', 4);
INSERT INTO `orders` VALUES ('9fd7b320-fa88-444b-89c3-30097fe1cc5d', 999, '1', '1', '1', 1, '2020-06-19 10:31:07', 4);
INSERT INTO `orders` VALUES ('a2dbd9ee-ab53-45e4-9993-0166f388f2ae', 999, '1', '1', '1', 0, '2020-06-16 21:21:26', 4);
INSERT INTO `orders` VALUES ('a49d6c43-473c-4500-adbd-7ece8f3481af', 0, '', '', '', 1, '2020-06-18 09:46:39', 4);
INSERT INTO `orders` VALUES ('a8847c0c-5fec-4e36-9068-9a146ebd785e', 99, '4', '4', '4', 1, '2020-06-18 09:41:25', 4);
INSERT INTO `orders` VALUES ('b3528dd8-0cc0-4909-90c4-f0c36db5198b', 999, '', '', '', 0, '2020-06-17 09:21:22', 4);
INSERT INTO `orders` VALUES ('b3c042e0-ad88-4e85-ac58-dcdc004bed13', 999, '4', '4', '4', 1, '2020-06-18 10:09:37', 4);
INSERT INTO `orders` VALUES ('c0b6cea9-5721-4f2d-9cc1-d1975a534af9', 999, '1', '1', '1', 0, '2020-06-16 23:18:07', 4);
INSERT INTO `orders` VALUES ('c295a1e5-acec-41cb-98d0-66b017c70dd5', 1998, '新余学院', 'cjc', '110', 1, '2020-06-17 15:11:34', 8);
INSERT INTO `orders` VALUES ('d5f44fd8-533a-4742-9c35-fe67aedb839e', 1098, '4', '4', '110', 1, '2020-06-18 12:12:13', 4);
INSERT INTO `orders` VALUES ('e6dd85d8-7dc3-46dd-9a20-005824ac5cff', 0, '', '', '', 1, '2020-06-18 09:46:00', 4);
INSERT INTO `orders` VALUES ('e7404ef9-6066-48a2-a98a-91bd1e7de2da', 999, '2', '2', '2', 0, '2020-06-16 23:35:32', 4);
INSERT INTO `orders` VALUES ('e897dc90-0c29-4a5f-8d93-fdecebbfb147', 7992, '1', '1', '1', 0, '2020-06-16 23:14:57', 4);
INSERT INTO `orders` VALUES ('eb84b23a-8947-4e38-b4e7-c0b01fce0ec7', 999, '1', '1', '1', 0, '2020-06-16 22:27:45', 4);
INSERT INTO `orders` VALUES ('f3caf483-9bb2-47a3-b4d0-604e7b9021a2', 129, '4', '4', '4', 1, '2020-06-18 12:05:35', 4);
INSERT INTO `orders` VALUES ('f4330bc4-906c-4421-bf80-b2cace95c376', 999, '', '', '', 1, '2020-06-18 10:44:21', 4);
INSERT INTO `orders` VALUES ('f4e6ca22-708b-4927-9c43-1c844a9eadf7', 60, '4', '4', '4', 1, '2020-06-18 11:43:40', 4);
INSERT INTO `orders` VALUES ('f5475010-ac42-4043-ac5b-030c01cc55b9', 999, '1', '1', '1', 0, '2020-06-16 21:22:08', 4);
INSERT INTO `orders` VALUES ('f752d6db-c9fc-49a9-b6eb-f223fe3acfad', 99, '', '', '', 1, '2020-06-18 10:05:26', 4);
INSERT INTO `orders` VALUES ('fe23f0f5-6e58-4a26-8ad0-36036dfb9f85', 999, '1', '1', '1', 0, '2020-06-16 21:21:39', 4);

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double(10, 0) NULL DEFAULT NULL,
  `category` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pnum` int(11) NULL DEFAULT NULL,
  `imgurl` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES (1, '小黄书', 999, '文学', 97, 'booksimg\\\\1001.jpg', '好看');
INSERT INTO `products` VALUES (2, '金瓶梅', 999, '文学', 89, 'booksimg\\\\baidu_jgylogo3.gif', '非常好看');
INSERT INTO `products` VALUES (4, '水浒传', 999, '文学', 93, 'booksimg\\\\37.jpg', '好看');
INSERT INTO `products` VALUES (6, '时空穿行', 30, '科技', 7, 'booksimg\\\\101.jpg', '一本关于时空的书');
INSERT INTO `products` VALUES (7, '感悟', 30, '文学', 19, 'booksimg\\\\102.jpg', '感悟人生，感恩生活');
INSERT INTO `products` VALUES (8, '赢在领导力', 60, '生活', 10, 'booksimg\\\\103.jpg', '领导人生，赢在人生');
INSERT INTO `products` VALUES (9, '谁动了我的奶酪', 10, '经营', 60, 'booksimg\\\\104.jpg', '谁动了我的奶酪，我动你脑袋');
INSERT INTO `products` VALUES (10, '培育男孩', 30, '少儿', 200, 'booksimg\\\\105.jpg', '培育男孩不容易，相信你需要它的');
INSERT INTO `products` VALUES (11, '别做正常的傻瓜', 20, '励志', 1000, 'booksimg\\\\106.jpg', '别做正常的傻瓜');
INSERT INTO `products` VALUES (12, '学会宽容', 29, '生活', 50, 'booksimg\\\\107.jpg', '学会宽容，学会生活');
INSERT INTO `products` VALUES (13, 'Java 2 入门经典', 100, '计算机', 199, 'booksimg\\\\java2.jpg', '学习Java从这开始');
INSERT INTO `products` VALUES (14, '中国国家地理', 30, '原版', 600, 'booksimg\\\\ngcn.jpg', '认识中国地理之美');
INSERT INTO `products` VALUES (15, '.NET设计规范', 29, '计算机', 99, 'booksimg\\\\TS1.jpg', '学习前端的必学之书');
INSERT INTO `products` VALUES (16, '设计模式解析', 30, '计算机', 10, 'booksimg\\\\TS4.jpg', '了解设计模式，学会编写优质代码');
INSERT INTO `products` VALUES (17, '网管员必备宝典', 50, '计算机', 2, 'booksimg\\\\TS8.jpg', '网管员必备的一本书，相信你非常需要');
INSERT INTO `products` VALUES (18, '系统分析师教程', 60, '计算机', 80, 'booksimg\\\\TS9.jpg', '程序员进阶必备之书');
INSERT INTO `products` VALUES (19, '数据库原理及其应用原理', 100, '计算机', 90, 'booksimg\\\\TS10.jpg', '了解数据库，学会应用数据库');
INSERT INTO `products` VALUES (20, 'Linux', 30, '计算机', 100, 'booksimg\\\\TS14.jpg', '认识Linux系统，学习Linux操作，相信你会迷上它');
INSERT INTO `products` VALUES (21, '三国演义', 369, '文学', 125, 'booksimg\\\\9448607_145742365000_2.jpg', '非常好看');
INSERT INTO `products` VALUES (22, '红楼梦', 455, '文学', 52, 'booksimg\\\\1001.jpg', '非常好看');
INSERT INTO `products` VALUES (23, '隋唐演义', 258, '文学', 258, 'booksimg\\\\16pic_5741280_b.jpg', '好看');
INSERT INTO `products` VALUES (24, '李白传', 999, '文学', 258, 'booksimg\\\\37.jpg', '好看');
INSERT INTO `products` VALUES (27, '哈哈', 123412, '文学', 123, 'booksimg\\\\', 'qwreqwara');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PASSWORD` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduce` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `activeCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `role` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '普通用户',
  `registTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '1', '1', '男', NULL, '11111111111', NULL, '11111', 1, '管理员', '2020-06-12 10:55:09');
INSERT INTO `user` VALUES (2, '1', '123', '?', '1223', '123', '123', '4d517864-8752-46e0-b7e0-c3884c7a41d9', 0, '????', '2020-06-12 10:29:27');
INSERT INTO `user` VALUES (3, '3', '3', '?', '3', '3', '3', '6157471b-b059-4bae-8521-5e8c5438c6f4', 0, '????', '2020-06-12 10:33:16');
INSERT INTO `user` VALUES (4, '4', '123', '男', '4', '123', '4', '7c20241e-1cd0-4e8b-92ed-66316fa07f36', 1, '普通用户', '2020-06-18 12:10:43');
INSERT INTO `user` VALUES (5, '5', '5', '男', '5', '5', '5', '6ed30832-29aa-404e-a702-cf1eb75fee58', 0, '普通用户', '2020-06-12 10:48:16');
INSERT INTO `user` VALUES (6, '6', '6', '男', '6', '6', '6', '6539e8d8-c23c-4c8b-8336-9c1eebf00bf2', 0, '普通用户', '2020-06-12 11:02:55');
INSERT INTO `user` VALUES (7, '7', '7', '男', '7', '7', '7', 'ba15fa37-89ff-4f25-8c1f-c2c7997a3f9c', 1, '普通用户', '2020-06-12 11:58:19');
INSERT INTO `user` VALUES (8, 'cjc', '123', '男', '2012635056@qq.com', '110', '我变秃了，当时为什么没有变强', '01e1b31f-37a1-433f-8b59-33cac5604ced', 1, '普通用户', '2020-06-17 14:56:57');
INSERT INTO `user` VALUES (9, '9', '9', '男', '5056@qq.com', '120', '我很帅', '16a474d2-0539-4902-8966-bb5cfb35473e', 1, '普通用户', '2020-06-18 23:09:20');
INSERT INTO `user` VALUES (10, '123', '1', '男', '123', '123', '12', 'dbdf4c72-6335-4a03-86a7-18ed8bbd1242', 1, '普通用户', '2020-06-18 23:22:27');
INSERT INTO `user` VALUES (11, '123', '1', '男', '123', '123', '12', '24b61d26-7828-412b-8c05-e56e1c29d066', 1, '普通用户', '2020-06-18 23:24:43');
INSERT INTO `user` VALUES (12, '12313', '1', '男', 'qAWED', 'qAWEDASDF', '1231', '1f84e5aa-47b7-4f0e-bb2b-e2e3f5ad41ec', 1, '普通用户', '2020-06-18 23:27:24');
INSERT INTO `user` VALUES (13, '1234', '1', '男', '12321', '12234', '1324', 'cb74189a-f911-4fe1-a238-3b327034e932', 1, '普通用户', '2020-06-18 23:36:44');
INSERT INTO `user` VALUES (14, '1234', '', '男', '12321', '12234', '1324', '31ebaab4-d40a-402c-915e-b3f82faacf57', 1, '普通用户', '2020-06-18 23:36:50');
INSERT INTO `user` VALUES (15, '1123', '1', '男', '1234', '12', 'qwer', 'bb13a030-afa4-4e1e-8bc2-9c955e321beb', 1, '普通用户', '2020-06-18 23:38:03');
INSERT INTO `user` VALUES (16, '1123', '1', '男', '123', '123', '123', '3db06040-7ed8-4cb2-94d6-6bda9a8decee', 1, '普通用户', '2020-06-18 23:39:42');

SET FOREIGN_KEY_CHECKS = 1;
