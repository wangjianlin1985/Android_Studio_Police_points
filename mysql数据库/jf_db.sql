/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : jf_db

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2017-12-25 20:58:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) default NULL,
  `Purview` int(11) default NULL,
  `LastLoginIP` varchar(20) default NULL,
  `LastLoginTime` varchar(40) default NULL,
  `LastLogoutTime` varchar(40) default NULL,
  `LoginTimes` int(11) default NULL,
  `RndPassword` varchar(40) default NULL,
  `QX` varchar(10) default NULL,
  `BigClassID` varchar(10) default NULL,
  `BigClassTypes` varchar(10) default NULL,
  `mc` varchar(30) default NULL,
  `zxb` bit(1) default NULL,
  `scb` bit(1) default NULL,
  `dyz` bit(1) default NULL,
  `jyb` bit(1) default NULL,
  `gly` bit(1) default NULL,
  `gjzxb` bit(1) default NULL,
  `ysz` bit(1) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('a', 'a', null, null, null, null, null, null, null, null, null, '管理员', null, null, null, null, null, null, null);
INSERT INTO `admin` VALUES ('bgs', '123', null, null, null, null, null, null, null, null, null, '办公室', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `yy_jftj`
-- ----------------------------
DROP TABLE IF EXISTS `yy_jftj`;
CREATE TABLE `yy_jftj` (
  `id` int(11) NOT NULL auto_increment,
  `jftj` varchar(120) default NULL,
  `fs` float default NULL,
  `typeid` int(11) default NULL,
  `mtypeid` int(11) default NULL,
  `bz` longtext,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1119 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yy_jftj
-- ----------------------------
INSERT INTO `yy_jftj` VALUES ('1116', '所在单位管辖区每季度刑事案件发案每下降一个百分点加0.5分', '0.5', '1', '1', '');
INSERT INTO `yy_jftj` VALUES ('1117', '所在单位管辖区每季度每发生一起恶性案件扣2分', '-2', '1', '1', '杀人案件、聚众斗殴致人轻伤以上案件、涉枪涉爆案件、强奸学生案件');
INSERT INTO `yy_jftj` VALUES ('1118', '所在单位管辖区每季度侵财类案件每上升一个百分点扣0.5分', '-0.5', '1', '1', '');

-- ----------------------------
-- Table structure for `yy_zxy`
-- ----------------------------
DROP TABLE IF EXISTS `yy_zxy`;
CREATE TABLE `yy_zxy` (
  `id` int(11) NOT NULL auto_increment,
  `bid` int(11) default NULL,
  `bname` varchar(20) default NULL,
  `btypes` int(11) default NULL,
  `jid` int(11) default NULL,
  `jfjd` varchar(20) default NULL,
  `jdsdate` datetime default NULL,
  `jdedate` datetime default NULL,
  `xsfajf` float default NULL,
  `hmzfjf` float default NULL,
  `cpfkjf` float default NULL,
  `dwzsjf` float default NULL,
  `hjf` float default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yy_zxy
-- ----------------------------
INSERT INTO `yy_zxy` VALUES ('1', '28', '办公室', '2', '1116', '2017年第一季度', '2017-01-01 00:00:00', '2017-03-31 00:00:00', '2.5', '3.2', '1.8', '2.4', '20');
INSERT INTO `yy_zxy` VALUES ('2', '29', '北门派出所', '1', '1126', '2017年第一季度', '2017-01-01 00:00:00', '2017-03-31 00:00:00', '25', '30', '15', '22', '92');

-- ----------------------------
-- Table structure for `yy_zxyjf`
-- ----------------------------
DROP TABLE IF EXISTS `yy_zxyjf`;
CREATE TABLE `yy_zxyjf` (
  `id` int(11) NOT NULL auto_increment,
  `bid` int(11) default NULL,
  `bname` varchar(30) default NULL,
  `btypes` int(11) default NULL,
  `sid` varchar(20) default NULL,
  `sname` varchar(20) default NULL,
  `jid` int(11) default NULL,
  `jftj` varchar(100) default NULL,
  `jfjd` varchar(40) default NULL,
  `jfdate` datetime default NULL,
  `jdsdate` datetime default NULL,
  `fs` float default NULL,
  `sl` int(11) default NULL,
  `xsfajf` float default NULL,
  `hmzfjf` float default NULL,
  `cpfkjf` float default NULL,
  `dwzsjf` float default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yy_zxyjf
-- ----------------------------
INSERT INTO `yy_zxyjf` VALUES ('1', '28', '办公室', '2', '165', '孙云', '1126', '所在单位管辖区每季度刑事案件发案每下降一个百分点加0.5分', '2017年第一季度', '2017-01-03 00:00:00', '2017-01-01 00:00:00', '0.5', '2', '2', '3', '4', '10');
INSERT INTO `yy_zxyjf` VALUES ('2', '28', '办公室', '2', '166', '吴玮玮', '1126', '所在单位管辖区每季度刑事案件发案每下降一个百分点加0.5分', '2017年第一季度', '2017-01-26 00:00:00', '2017-01-01 00:00:00', '0.5', '1', '0.2', '0.3', '0.5', '1.2');

-- ----------------------------
-- Table structure for `yy_zxymj`
-- ----------------------------
DROP TABLE IF EXISTS `yy_zxymj`;
CREATE TABLE `yy_zxymj` (
  `id` int(11) NOT NULL auto_increment,
  `bid` int(11) default NULL,
  `bname` varchar(20) default NULL,
  `sid` int(11) default NULL,
  `sname` varchar(20) default NULL,
  `btypes` int(11) default NULL,
  `jid` int(11) default NULL,
  `jfjd` varchar(20) default NULL,
  `jdsdate` datetime default NULL,
  `jdedate` datetime default NULL,
  `xsfajf` float default NULL,
  `hmzfjf` float default NULL,
  `cpfkjf` float default NULL,
  `dwzsjf` float default NULL,
  `hjf` float default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yy_zxymj
-- ----------------------------
INSERT INTO `yy_zxymj` VALUES ('1', '28', '办公室', '165', '孙云', '2', '1116', '2017年第一季度', '2017-01-01 00:00:00', '2017-03-31 00:00:00', '2.5', '2.3', '3.2', '4.2', '28');
INSERT INTO `yy_zxymj` VALUES ('2', '29', '北门派出所', '172', '李毅锋', '1', '1126', '2017年第一季度', '2017-01-01 00:00:00', '2017-03-31 00:00:00', '0', '50', '50', '0', '100');

-- ----------------------------
-- Table structure for `yy_zxymjnd`
-- ----------------------------
DROP TABLE IF EXISTS `yy_zxymjnd`;
CREATE TABLE `yy_zxymjnd` (
  `id` int(11) NOT NULL auto_increment,
  `bid` int(11) default NULL,
  `bname` varchar(30) default NULL,
  `sid` int(11) default NULL,
  `sname` varchar(20) default NULL,
  `btypes` int(11) default NULL,
  `jfjd` varchar(20) default NULL,
  `xsfajf` float default NULL,
  `hmzfjf` float default NULL,
  `cpfkjf` float default NULL,
  `dwzsjf` float default NULL,
  `hjf` float default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yy_zxymjnd
-- ----------------------------
INSERT INTO `yy_zxymjnd` VALUES ('1', '28', '办公室', '165', '孙云', '2', '2017年', '2.3', '3.2', '4.2', '5.2', '22');
INSERT INTO `yy_zxymjnd` VALUES ('2', '29', '北门派出所', '172', '李毅锋', '1', '2017', '0', '0', '50', '50', '100');

-- ----------------------------
-- Table structure for `yy_zxynd`
-- ----------------------------
DROP TABLE IF EXISTS `yy_zxynd`;
CREATE TABLE `yy_zxynd` (
  `id` int(11) NOT NULL auto_increment,
  `bid` int(11) default NULL,
  `bname` varchar(20) default NULL,
  `btypes` int(11) default NULL,
  `jfjd` varchar(20) default NULL,
  `xsfajf` float default NULL,
  `hmzfjf` float default NULL,
  `cpfkjf` float default NULL,
  `dwzsjf` float default NULL,
  `hjf` float default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yy_zxynd
-- ----------------------------
INSERT INTO `yy_zxynd` VALUES ('1', '28', '办公室', '2', '2017年', '2.5', '3.2', '4.2', '5.2', '18');
INSERT INTO `yy_zxynd` VALUES ('2', '29', '北门派出所', '1', '2017', '0', '40', '20', '38', '98');
