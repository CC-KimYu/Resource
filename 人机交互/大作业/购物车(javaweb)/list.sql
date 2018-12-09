/*
Navicat MySQL Data Transfer

Source Server         : 172.16.73.89
Source Server Version : 50096
Source Host           : 172.16.73.89:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2018-05-27 15:00:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for list
-- ----------------------------
DROP TABLE IF EXISTS `list`;
CREATE TABLE `list` (
  `account` varchar(255) NOT NULL,
  `fruit_id` varchar(255) NOT NULL,
  `fruit_number` int(11) NOT NULL,
  PRIMARY KEY  (`account`,`fruit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of list
-- ----------------------------
INSERT INTO `list` VALUES ('1111', '001', '7');
INSERT INTO `list` VALUES ('1111', '002', '1');
INSERT INTO `list` VALUES ('1111', '003', '1');
INSERT INTO `list` VALUES ('1111', '004', '1');
INSERT INTO `list` VALUES ('1111', '005', '1');
INSERT INTO `list` VALUES ('1111', '006', '1');
INSERT INTO `list` VALUES ('1111', '007', '1');
INSERT INTO `list` VALUES ('2222', '001', '10');
