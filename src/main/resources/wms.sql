/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : wms

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-07-27 18:14:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES ('1', '苹果', 'Apple');
INSERT INTO `brand` VALUES ('2', '华为', 'HUAWEI');
INSERT INTO `brand` VALUES ('3', '小米', 'MI');
INSERT INTO `brand` VALUES ('4', '锤子', 'SMARTISAN');
INSERT INTO `brand` VALUES ('5', '360', '360');
INSERT INTO `brand` VALUES ('6', '菲尔可', 'FILCO');
INSERT INTO `brand` VALUES ('7', '西部数据', 'WD');
INSERT INTO `brand` VALUES ('8', '闪迪', 'SANDISK');
INSERT INTO `brand` VALUES ('9', '斯伯丁', 'SPALDING');

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('1', '叩丁狼', 'WOLFCODE', '123');
INSERT INTO `client` VALUES ('2', '小码哥iOS学院', 'XMG_IOS', '123');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '总经办', 'General Deparment');
INSERT INTO `department` VALUES ('2', '人力资源部', 'Human Resources Department');
INSERT INTO `department` VALUES ('3', '采购部', 'Order Department');
INSERT INTO `department` VALUES ('4', '仓储部', 'Warehousing Department');
INSERT INTO `department` VALUES ('5', '财务部', 'Finance Department');
INSERT INTO `department` VALUES ('19', 'AA', 'DEV');

-- ----------------------------
-- Table structure for depot
-- ----------------------------
DROP TABLE IF EXISTS `depot`;
CREATE TABLE `depot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of depot
-- ----------------------------
INSERT INTO `depot` VALUES ('1', '天河区1号仓', '天河区');
INSERT INTO `depot` VALUES ('2', '白云区2号仓', '白云区');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `admin` bit(1) DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'admin', 'C4CA4238A0B923820DCC509A6F75849B', 'admin@abc.com', '20', '', '1');
INSERT INTO `employee` VALUES ('2', '赵总', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '35', '\0', '1');
INSERT INTO `employee` VALUES ('3', '赵一明', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '25', '\0', '1');
INSERT INTO `employee` VALUES ('4', '钱总', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '35', '\0', '2');
INSERT INTO `employee` VALUES ('5', '钱二明', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '25', '\0', '2');
INSERT INTO `employee` VALUES ('6', '孙总', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '35', '\0', '3');
INSERT INTO `employee` VALUES ('7', '孙三明', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '25', '\0', '3');
INSERT INTO `employee` VALUES ('8', '李总', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '35', '\0', '4');
INSERT INTO `employee` VALUES ('9', '李四明', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '25', '\0', '4');
INSERT INTO `employee` VALUES ('10', '周总', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '35', '\0', '5');
INSERT INTO `employee` VALUES ('11', '周五明', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '25', '\0', '5');
INSERT INTO `employee` VALUES ('12', '吴总', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '35', '\0', '2');
INSERT INTO `employee` VALUES ('13', '吴六明', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '25', '\0', '5');
INSERT INTO `employee` VALUES ('14', '郑总', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '35', '\0', '7');
INSERT INTO `employee` VALUES ('15', '郑七明', 'C4CA4238A0B923820DCC509A6F75849B', 'xx@xx.com', '25', '\0', '2');

-- ----------------------------
-- Table structure for employee_role
-- ----------------------------
DROP TABLE IF EXISTS `employee_role`;
CREATE TABLE `employee_role` (
  `employee_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_role
-- ----------------------------
INSERT INTO `employee_role` VALUES ('4', '1');
INSERT INTO `employee_role` VALUES ('4', '2');
INSERT INTO `employee_role` VALUES ('4', '4');
INSERT INTO `employee_role` VALUES ('2', '1');

-- ----------------------------
-- Table structure for orderbill
-- ----------------------------
DROP TABLE IF EXISTS `orderbill`;
CREATE TABLE `orderbill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) DEFAULT NULL,
  `vdate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `total_amount` decimal(19,2) DEFAULT NULL,
  `total_number` decimal(19,2) DEFAULT NULL,
  `audit_time` datetime DEFAULT NULL,
  `input_time` datetime DEFAULT NULL,
  `input_user_id` bigint(20) DEFAULT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderbill
-- ----------------------------
INSERT INTO `orderbill` VALUES ('1', '001', '2018-07-17 00:00:00', '1', '6000.00', '10.00', '2018-07-17 18:05:26', '2018-07-17 11:00:24', '1', '1', '2');
INSERT INTO `orderbill` VALUES ('2', '26171513613', '2018-07-18 00:00:00', '1', '4800.00', '14.00', '2018-07-18 19:35:34', '2018-07-18 19:34:23', '2', '2', '2');

-- ----------------------------
-- Table structure for orderbillitem
-- ----------------------------
DROP TABLE IF EXISTS `orderbillitem`;
CREATE TABLE `orderbillitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cost_price` decimal(19,2) DEFAULT NULL,
  `number` decimal(19,2) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `bill_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderbillitem
-- ----------------------------
INSERT INTO `orderbillitem` VALUES ('2', '600.00', '10.00', '6000.00', '', '6', '1');
INSERT INTO `orderbillitem` VALUES ('3', '200.00', '10.00', '2000.00', '', '1', '2');
INSERT INTO `orderbillitem` VALUES ('4', '600.00', '2.00', '1200.00', '', '5', '2');
INSERT INTO `orderbillitem` VALUES ('5', '800.00', '2.00', '1600.00', '', '3', '2');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `expression` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '部门删除', 'cn.wolfcode.wms.web.controller.DepartmentController:delete');
INSERT INTO `permission` VALUES ('2', '部门列表', 'cn.wolfcode.wms.web.controller.DepartmentController:list');
INSERT INTO `permission` VALUES ('3', '部门编辑', 'cn.wolfcode.wms.web.controller.DepartmentController:input');
INSERT INTO `permission` VALUES ('4', '部门保存或修改', 'cn.wolfcode.wms.web.controller.DepartmentController:saveOrUpdate');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  `cost_price` decimal(19,2) DEFAULT NULL,
  `sale_price` decimal(19,2) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `intro` varchar(255) DEFAULT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  `brand_name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1td6gorl25rsvufiiive2svlx` (`brand_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '360儿童手表', '001', '200.00', '400.00', '/upload/2427832c-f711-45cb-85d2-b3e022fec35f.jpg', '360儿童手表 巴迪龙儿童手表5 W563 360儿童卫士 智能彩屏电话手表 静谧蓝', '5', '360');
INSERT INTO `product` VALUES ('2', '360智能摄像机', '002', '300.00', '500.00', '/upload/91a15313-3ea5-4998-a285-b9eaae02d342.jpg', '360智能摄像机夜视版 D503 小水滴 家用网络摄像机 无线WiFi摄像头 手机远程监控 夜视版标', '5', '360');
INSERT INTO `product` VALUES ('3', '360N4S手机', '003', '800.00', '900.00', '/upload/b5bbd783-bdf4-4ec4-9e94-bd67f2013f80.jpg', '360n4s手机 流光金 全网通4G(4G RAM+32G ROM)标准版', '5', '360');
INSERT INTO `product` VALUES ('4', '锤子(smartisan) T2手机', '004', '2000.00', '2800.00', '/upload/47a539de-ace4-4ded-aa44-a38889e06c99.jpg', '锤子(smartisan) T2 4G手机 黑色 全网通(3G RAM+16G ROM)标配', '4', '锤子');
INSERT INTO `product` VALUES ('5', '锤子坚果手机', '005', '600.00', '800.00', '/upload/09241c32-6dcb-495a-a7e9-5e805afe8241.jpg', '锤子坚果手机4G手机 红色 全网通(2G RAM+32G ROM)标配', '4', '锤子');
INSERT INTO `product` VALUES ('6', '斐尔可（FILCO）机械键盘', '006', '600.00', '1000.00', '/upload/b131060d-6641-496d-b52c-f6a1dfab3c8b.jpg', '斐尔可（FILCO）「87圣手二代」机械键盘 黑色 茶轴 ', '6', '菲尔可');
INSERT INTO `product` VALUES ('7', '华为荣耀V8手机', '007', '2000.00', '2500.00', '/upload/3c35c681-f93b-4907-8ebe-059ef843edbf.jpg', '华为 荣耀 V8 4GB+32GB 铂光金 移动联通4G手机 双卡双待双通', '2', '华为');
INSERT INTO `product` VALUES ('8', '闪迪（SanDisk）至尊高速3.0U盘', '012', '80.00', '150.00', '/upload/a286c2f4-f2cc-4a83-bb53-b6c467849577.jpg', '闪迪（SanDisk）至尊高速（CZ48） 256GB USB3.0 U盘 读100MBs 写40MBs', '8', '闪迪');

-- ----------------------------
-- Table structure for productstock
-- ----------------------------
DROP TABLE IF EXISTS `productstock`;
CREATE TABLE `productstock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` decimal(19,2) DEFAULT NULL,
  `store_number` decimal(19,2) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `depot_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of productstock
-- ----------------------------
INSERT INTO `productstock` VALUES ('1', '2000.00', '3.00', '6000.00', '4', '1');
INSERT INTO `productstock` VALUES ('2', '200.00', '159.00', '31800.00', '1', '1');
INSERT INTO `productstock` VALUES ('3', '200.00', '6.00', '1200.00', '1', '2');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '人事管理', 'HR MGR');
INSERT INTO `role` VALUES ('2', '采购管理', 'ORDER MGR');
INSERT INTO `role` VALUES ('3', '仓储管理', 'WAREHOUSING MGR');
INSERT INTO `role` VALUES ('4', '行政部管理', 'Admin MGR');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1');
INSERT INTO `role_menu` VALUES ('1', '2');
INSERT INTO `role_menu` VALUES ('1', '3');
INSERT INTO `role_menu` VALUES ('1', '4');
INSERT INTO `role_menu` VALUES ('1', '5');
INSERT INTO `role_menu` VALUES ('1', '6');
INSERT INTO `role_menu` VALUES ('1', '7');
INSERT INTO `role_menu` VALUES ('1', '8');
INSERT INTO `role_menu` VALUES ('1', '9');
INSERT INTO `role_menu` VALUES ('1', '10');
INSERT INTO `role_menu` VALUES ('1', '11');
INSERT INTO `role_menu` VALUES ('1', '12');
INSERT INTO `role_menu` VALUES ('1', '13');
INSERT INTO `role_menu` VALUES ('1', '14');
INSERT INTO `role_menu` VALUES ('1', '15');
INSERT INTO `role_menu` VALUES ('1', '16');
INSERT INTO `role_menu` VALUES ('1', '17');
INSERT INTO `role_menu` VALUES ('1', '18');
INSERT INTO `role_menu` VALUES ('1', '19');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1');
INSERT INTO `role_permission` VALUES ('1', '2');
INSERT INTO `role_permission` VALUES ('1', '3');
INSERT INTO `role_permission` VALUES ('1', '4');

-- ----------------------------
-- Table structure for saleaccount
-- ----------------------------
DROP TABLE IF EXISTS `saleaccount`;
CREATE TABLE `saleaccount` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vdate` datetime DEFAULT NULL,
  `number` decimal(19,2) DEFAULT NULL,
  `cost_price` decimal(19,2) DEFAULT NULL,
  `cost_amount` decimal(19,2) DEFAULT NULL,
  `sale_price` decimal(19,2) DEFAULT NULL,
  `sale_amount` decimal(19,2) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `saleman_id` bigint(20) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of saleaccount
-- ----------------------------
INSERT INTO `saleaccount` VALUES ('29', '2016-07-11 00:00:00', '30.00', '300.00', '9000.00', '500.00', '15000.00', '2', '1', '1');
INSERT INTO `saleaccount` VALUES ('30', '2016-07-11 00:00:00', '30.00', '7000.00', '210000.00', '10000.00', '300000.00', '20', '1', '1');
INSERT INTO `saleaccount` VALUES ('31', '2016-07-11 00:00:00', '30.00', '350.00', '10500.00', '500.00', '15000.00', '14', '1', '1');
INSERT INTO `saleaccount` VALUES ('32', '2016-07-11 00:00:00', '30.00', '2000.00', '60000.00', '2800.00', '84000.00', '4', '1', '1');
INSERT INTO `saleaccount` VALUES ('33', '2016-07-11 00:00:00', '30.00', '2000.00', '60000.00', '2500.00', '75000.00', '7', '1', '1');
INSERT INTO `saleaccount` VALUES ('34', '2016-07-11 00:00:00', '30.00', '200.00', '6000.00', '350.00', '10500.00', '10', '1', '1');
INSERT INTO `saleaccount` VALUES ('35', '2016-07-11 00:00:00', '30.00', '500.00', '15000.00', '800.00', '24000.00', '13', '1', '1');
INSERT INTO `saleaccount` VALUES ('36', '2016-07-11 00:00:00', '30.00', '1500.00', '45000.00', '2000.00', '60000.00', '15', '1', '1');
INSERT INTO `saleaccount` VALUES ('37', '2016-07-11 00:00:00', '30.00', '80.00', '2400.00', '150.00', '4500.00', '12', '1', '1');
INSERT INTO `saleaccount` VALUES ('74', '2016-07-01 00:00:00', '30.00', '200.00', '6000.00', '400.00', '12000.00', '1', '1', '1');
INSERT INTO `saleaccount` VALUES ('75', '2016-07-01 00:00:00', '30.00', '2000.00', '60000.00', '2800.00', '84000.00', '4', '1', '1');
INSERT INTO `saleaccount` VALUES ('76', '2016-07-01 00:00:00', '30.00', '600.00', '18000.00', '1000.00', '30000.00', '6', '1', '1');
INSERT INTO `saleaccount` VALUES ('77', '2016-07-01 00:00:00', '30.00', '2000.00', '60000.00', '2500.00', '75000.00', '7', '1', '1');
INSERT INTO `saleaccount` VALUES ('78', '2016-07-01 00:00:00', '30.00', '2500.00', '75000.00', '3500.00', '105000.00', '19', '1', '1');
INSERT INTO `saleaccount` VALUES ('79', '2016-07-01 00:00:00', '30.00', '80.00', '2400.00', '150.00', '4500.00', '12', '1', '1');
INSERT INTO `saleaccount` VALUES ('80', '2016-07-01 00:00:00', '30.00', '800.00', '24000.00', '1200.00', '36000.00', '8', '1', '1');
INSERT INTO `saleaccount` VALUES ('81', '2016-07-01 00:00:00', '30.00', '200.00', '6000.00', '250.00', '7500.00', '16', '1', '1');
INSERT INTO `saleaccount` VALUES ('82', '2016-07-01 00:00:00', '30.00', '350.00', '10500.00', '500.00', '15000.00', '14', '1', '1');
INSERT INTO `saleaccount` VALUES ('83', '2016-07-15 00:00:00', '30.00', '7000.00', '210000.00', '10000.00', '300000.00', '20', '1', '2');
INSERT INTO `saleaccount` VALUES ('84', '2016-07-15 00:00:00', '30.00', '200.00', '6000.00', '400.00', '12000.00', '1', '1', '2');
INSERT INTO `saleaccount` VALUES ('85', '2016-07-15 00:00:00', '30.00', '2000.00', '60000.00', '2800.00', '84000.00', '4', '1', '2');
INSERT INTO `saleaccount` VALUES ('86', '2016-07-15 00:00:00', '30.00', '800.00', '24000.00', '1200.00', '36000.00', '8', '1', '2');
INSERT INTO `saleaccount` VALUES ('87', '2016-07-15 00:00:00', '30.00', '350.00', '10500.00', '500.00', '15000.00', '14', '1', '2');
INSERT INTO `saleaccount` VALUES ('88', '2016-07-15 00:00:00', '30.00', '200.00', '6000.00', '250.00', '7500.00', '16', '1', '2');
INSERT INTO `saleaccount` VALUES ('89', '2016-07-15 00:00:00', '30.00', '1500.00', '45000.00', '2000.00', '60000.00', '15', '1', '2');
INSERT INTO `saleaccount` VALUES ('90', '2016-07-15 00:00:00', '30.00', '5000.00', '150000.00', '6500.00', '195000.00', '9', '1', '2');
INSERT INTO `saleaccount` VALUES ('94', '2016-07-25 00:00:00', '20.00', '2000.00', '40000.00', '3500.00', '70000.00', '11', '9', '1');
INSERT INTO `saleaccount` VALUES ('95', '2016-07-25 00:00:00', '20.00', '200.00', '4000.00', '250.00', '5000.00', '16', '9', '1');
INSERT INTO `saleaccount` VALUES ('96', '2016-07-25 00:00:00', '20.00', '200.00', '4000.00', '400.00', '8000.00', '1', '9', '1');
INSERT INTO `saleaccount` VALUES ('97', '2016-07-25 00:00:00', '20.00', '800.00', '16000.00', '900.00', '18000.00', '3', '9', '1');
INSERT INTO `saleaccount` VALUES ('98', '2016-07-25 00:00:00', '30.00', '600.00', '18000.00', '800.00', '24000.00', '5', '9', '1');
INSERT INTO `saleaccount` VALUES ('99', '2016-07-25 00:00:00', '30.00', '600.00', '18000.00', '1000.00', '30000.00', '6', '9', '1');
INSERT INTO `saleaccount` VALUES ('100', '2016-07-25 00:00:00', '20.00', '80.00', '1600.00', '150.00', '3000.00', '12', '9', '1');
INSERT INTO `saleaccount` VALUES ('101', '2016-07-25 00:00:00', '20.00', '5000.00', '100000.00', '6500.00', '130000.00', '9', '9', '1');
INSERT INTO `saleaccount` VALUES ('102', '2017-08-31 14:19:40', '1.00', '200.00', '200.00', '400.00', '400.00', '1', '1', '1');
INSERT INTO `saleaccount` VALUES ('103', '2017-08-31 14:19:40', '1.00', '300.00', '300.00', '500.00', '500.00', '2', '1', '1');
INSERT INTO `saleaccount` VALUES ('104', '2018-07-18 00:00:00', '2.00', '200.00', '400.00', '400.00', '800.00', '1', '1', '1');
INSERT INTO `saleaccount` VALUES ('105', '2018-07-18 00:00:00', '2.00', '200.00', '400.00', '400.00', '800.00', '1', '1', '2');

-- ----------------------------
-- Table structure for stockincomebill
-- ----------------------------
DROP TABLE IF EXISTS `stockincomebill`;
CREATE TABLE `stockincomebill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) DEFAULT NULL,
  `vdate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `total_amount` decimal(19,2) DEFAULT NULL,
  `total_number` decimal(19,2) DEFAULT NULL,
  `audit_time` datetime DEFAULT NULL,
  `input_time` datetime DEFAULT NULL,
  `input_user_id` bigint(20) DEFAULT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  `depot_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stockincomebill
-- ----------------------------
INSERT INTO `stockincomebill` VALUES ('1', '001', '2018-07-11 00:00:00', '1', '300.00', '1.00', '2018-07-17 01:18:10', '2018-07-17 00:45:47', '1', '1', '1');
INSERT INTO `stockincomebill` VALUES ('3', '11', '2018-07-04 00:00:00', '1', '4000.00', '2.00', '2018-07-17 10:54:13', '2018-07-17 10:52:25', '1', '1', '1');
INSERT INTO `stockincomebill` VALUES ('4', '123', '2018-07-17 00:00:00', '1', '2000.00', '1.00', '2018-07-17 10:57:22', '2018-07-17 10:57:16', '1', '1', '1');
INSERT INTO `stockincomebill` VALUES ('5', '14283', '2018-07-17 00:00:00', '1', '20000.00', '100.00', '2018-07-17 11:02:01', '2018-07-17 11:01:41', '1', '1', '1');
INSERT INTO `stockincomebill` VALUES ('6', '162223', '2018-07-17 00:00:00', '1', '2600.00', '13.00', '2018-07-17 11:03:49', '2018-07-17 11:03:21', '1', '1', '2');
INSERT INTO `stockincomebill` VALUES ('7', '12131', '2018-07-17 00:00:00', '1', '2600.00', '13.00', '2018-07-17 11:05:18', '2018-07-17 11:05:11', '1', '1', '1');
INSERT INTO `stockincomebill` VALUES ('8', '005', '2018-07-11 00:00:00', '1', '400.00', '2.00', '2018-07-18 08:55:15', '2018-07-18 08:55:11', '1', '1', '2');
INSERT INTO `stockincomebill` VALUES ('9', '2323', '2018-07-11 00:00:00', '1', '10000.00', '50.00', '2018-07-18 08:56:54', '2018-07-18 08:56:48', '1', '1', '1');
INSERT INTO `stockincomebill` VALUES ('10', '2341242', '2018-07-18 00:00:00', '1', '1000.00', '5.00', '2018-07-18 09:01:06', '2018-07-18 09:00:39', '1', '1', '2');

-- ----------------------------
-- Table structure for stockincomebillitem
-- ----------------------------
DROP TABLE IF EXISTS `stockincomebillitem`;
CREATE TABLE `stockincomebillitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cost_price` decimal(19,2) DEFAULT NULL,
  `number` decimal(19,2) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `bill_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stockincomebillitem
-- ----------------------------
INSERT INTO `stockincomebillitem` VALUES ('9', '300.00', '1.00', '300.00', '360智能摄像机', '2', '1');
INSERT INTO `stockincomebillitem` VALUES ('12', '2000.00', '2.00', '4000.00', '锤子(smartisan) T2手机', '4', '3');
INSERT INTO `stockincomebillitem` VALUES ('13', '2000.00', '1.00', '2000.00', '', '4', '4');
INSERT INTO `stockincomebillitem` VALUES ('14', '200.00', '100.00', '20000.00', '', '1', '5');
INSERT INTO `stockincomebillitem` VALUES ('15', '200.00', '13.00', '2600.00', '', '1', '6');
INSERT INTO `stockincomebillitem` VALUES ('16', '200.00', '13.00', '2600.00', '', '1', '7');
INSERT INTO `stockincomebillitem` VALUES ('17', '200.00', '2.00', '400.00', '', '1', '8');
INSERT INTO `stockincomebillitem` VALUES ('18', '200.00', '50.00', '10000.00', '', '1', '9');
INSERT INTO `stockincomebillitem` VALUES ('19', '200.00', '5.00', '1000.00', '', '1', '10');

-- ----------------------------
-- Table structure for stockoutcomebill
-- ----------------------------
DROP TABLE IF EXISTS `stockoutcomebill`;
CREATE TABLE `stockoutcomebill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) DEFAULT NULL,
  `vdate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `total_amount` decimal(19,2) DEFAULT NULL,
  `total_number` decimal(19,2) DEFAULT NULL,
  `audit_time` datetime DEFAULT NULL,
  `input_time` datetime DEFAULT NULL,
  `input_user_id` bigint(20) DEFAULT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  `depot_id` bigint(20) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stockoutcomebill
-- ----------------------------
INSERT INTO `stockoutcomebill` VALUES ('1', '001', '2018-07-17 00:00:00', '1', '1200.00', '3.00', '2018-07-17 22:29:51', '2018-07-17 18:19:58', '1', '1', '2', '2');
INSERT INTO `stockoutcomebill` VALUES ('2', '002', '2018-07-15 00:00:00', '1', '800.00', '2.00', '2018-07-17 22:31:24', '2018-07-17 22:31:04', '1', '1', '2', '1');
INSERT INTO `stockoutcomebill` VALUES ('3', '003', '2018-07-11 00:00:00', '1', '3600.00', '9.00', '2018-07-18 12:56:46', '2018-07-17 22:33:46', '1', '1', '2', '1');
INSERT INTO `stockoutcomebill` VALUES ('4', '003', '2018-07-18 00:00:00', '1', '800.00', '2.00', '2018-07-19 17:29:19', '2018-07-18 11:12:48', '1', null, '1', '1');
INSERT INTO `stockoutcomebill` VALUES ('5', '002317', '2018-07-18 00:00:00', '1', '800.00', '2.00', '2018-07-19 18:08:48', '2018-07-19 18:08:09', '1', '1', '1', '2');

-- ----------------------------
-- Table structure for stockoutcomebillitem
-- ----------------------------
DROP TABLE IF EXISTS `stockoutcomebillitem`;
CREATE TABLE `stockoutcomebillitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sale_price` decimal(19,2) DEFAULT NULL,
  `number` decimal(19,2) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `bill_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stockoutcomebillitem
-- ----------------------------
INSERT INTO `stockoutcomebillitem` VALUES ('2', '400.00', '3.00', '1200.00', '360儿童手表', '1', '1');
INSERT INTO `stockoutcomebillitem` VALUES ('3', '400.00', '2.00', '800.00', '360儿童手表', '1', '2');
INSERT INTO `stockoutcomebillitem` VALUES ('6', '400.00', '9.00', '3600.00', '', '1', '3');
INSERT INTO `stockoutcomebillitem` VALUES ('9', '400.00', '2.00', '800.00', '360智能摄像机', '1', '4');
INSERT INTO `stockoutcomebillitem` VALUES ('10', '400.00', '2.00', '800.00', '', '1', '5');

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES ('1', '北京XX公司', '123', '北京');
INSERT INTO `supplier` VALUES ('2', '上海XX公司', '456', '上海');
INSERT INTO `supplier` VALUES ('3', '广州XX公司', '789', '广州');

-- ----------------------------
-- Table structure for systemmenu
-- ----------------------------
DROP TABLE IF EXISTS `systemmenu`;
CREATE TABLE `systemmenu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `systemmenu_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `systemmenu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemmenu
-- ----------------------------
INSERT INTO `systemmenu` VALUES ('1', '系统模块', '', 'system', null);
INSERT INTO `systemmenu` VALUES ('2', '业务模块', '', 'business', null);
INSERT INTO `systemmenu` VALUES ('3', '报表模块', '', 'charts', null);
INSERT INTO `systemmenu` VALUES ('4', '部门管理', 'department/list', '', '1');
INSERT INTO `systemmenu` VALUES ('5', '员工管理', 'employee/list', '', '1');
INSERT INTO `systemmenu` VALUES ('6', '权限管理', 'permission/list', '', '1');
INSERT INTO `systemmenu` VALUES ('7', '角色管理', 'role/list', '', '1');
INSERT INTO `systemmenu` VALUES ('8', '系统菜单管理', 'systemmenu/list', '', '1');
INSERT INTO `systemmenu` VALUES ('9', '品牌管理', 'brand/list', '', '2');
INSERT INTO `systemmenu` VALUES ('10', '供应商管理', 'supplier/list', '', '2');
INSERT INTO `systemmenu` VALUES ('11', '商品管理', 'product/list', '', '2');
INSERT INTO `systemmenu` VALUES ('12', '仓库管理', 'depot/list', '', '2');
INSERT INTO `systemmenu` VALUES ('13', '客户管理', 'client/list', '', '2');
INSERT INTO `systemmenu` VALUES ('14', '采购订单管理', 'orderBill/list', '', '2');
INSERT INTO `systemmenu` VALUES ('15', '采购入库单管理', 'stockincomeBill/list', '', '2');
INSERT INTO `systemmenu` VALUES ('16', '销售出库单管理', 'stockoutcomeBill/list', '', '2');
INSERT INTO `systemmenu` VALUES ('17', '即时库存报表', 'productStock/list', '', '3');
INSERT INTO `systemmenu` VALUES ('18', '订货报表', 'chart/order', '', '3');
INSERT INTO `systemmenu` VALUES ('19', '销售报表', 'chart/sale', '', '3');
