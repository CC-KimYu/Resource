/*
Navicat MySQL Data Transfer

Source Server         : 172.16.73.89
Source Server Version : 50096
Source Host           : 172.16.73.89:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2018-05-27 15:00:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for fruit
-- ----------------------------
DROP TABLE IF EXISTS `fruit`;
CREATE TABLE `fruit` (
  `fruit_name` varchar(255) character set utf8 NOT NULL,
  `fruit_id` varchar(255) NOT NULL,
  `fruit_price` varchar(255) character set utf8 NOT NULL,
  PRIMARY KEY  (`fruit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of fruit
-- ----------------------------
INSERT INTO `fruit` VALUES ('苹果', '001', '5');
INSERT INTO `fruit` VALUES ('香蕉', '002', '3');
INSERT INTO `fruit` VALUES ('梨子', '003', '3.5');
INSERT INTO `fruit` VALUES ('樱桃', '004', '20');
INSERT INTO `fruit` VALUES ('菠萝', '005', '8');
INSERT INTO `fruit` VALUES ('哈密瓜', '006', '10');
INSERT INTO `fruit` VALUES ('西瓜', '007', '12');
INSERT INTO `fruit` VALUES ('蜜桃', '008', '11');
