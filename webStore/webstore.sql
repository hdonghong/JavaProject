/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : webstore

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2017-12-14 11:57:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `aid` bigint(20) NOT NULL AUTO_INCREMENT,
  `adminname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` bigint(20) NOT NULL AUTO_INCREMENT,
  `cname` varchar(20) DEFAULT NULL,
  `state` int(2) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '手机数码', '1');
INSERT INTO `category` VALUES ('2', '电脑办公', '1');
INSERT INTO `category` VALUES ('3', '家具家居', '1');
INSERT INTO `category` VALUES ('4', '鞋靴箱包', '1');
INSERT INTO `category` VALUES ('5', '图书音像', '1');
INSERT INTO `category` VALUES ('6', '男装女装', '1');
INSERT INTO `category` VALUES ('7', '测试添加分类', '0');
INSERT INTO `category` VALUES ('8', '添加', '0');
INSERT INTO `category` VALUES ('10', '母婴孕婴', '0');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `itemid` bigint(32) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `create_at` bigint(32) DEFAULT NULL,
  `update_at` bigint(32) DEFAULT NULL,
  `pid` bigint(32) DEFAULT NULL,
  `oid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  KEY `fk_0001` (`pid`),
  KEY `fk_0002` (`oid`),
  CONSTRAINT `FK3933auil4yrjm19ay5e6hqh5r` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`),
  CONSTRAINT `FKt46aaoxdwtns0rq58w0papv91` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('1', null, '2699', null, '1511787941000', null, '2', null);
INSERT INTO `orderitem` VALUES ('4', null, '1499', null, '1511788422000', null, '3', null);
INSERT INTO `orderitem` VALUES ('7', null, '1499', null, '1511788740000', null, '3', null);
INSERT INTO `orderitem` VALUES ('9', '1', '4199', null, '1511843993000', '1512192726000', '33', '2c90edf96000ec3a016000ec98e50000');
INSERT INTO `orderitem` VALUES ('10', '1', '5398', '评价方未做出评价,系统默认好评。', null, '1512201397000', '2', '2c90edf9600109440160010ac5520001');
INSERT INTO `orderitem` VALUES ('11', '1', '1499', '宝贝收到了，很nICE', '1511845971000', '1512206104000', '3', '2c90edf9600109440160010ac5520001');
INSERT INTO `orderitem` VALUES ('12', '1', '4199', null, '1511859094000', '1512822258000', '33', null);
INSERT INTO `orderitem` VALUES ('13', '1', '1299', null, '1511859121000', '1512822254000', '32', null);
INSERT INTO `orderitem` VALUES ('14', '1', '6688', null, '1511859121000', '1512822254000', '32', null);
INSERT INTO `orderitem` VALUES ('15', '1', '6688', null, '1512128872000', '1512128872000', '32', '2c90edf96011e663016011e780170000');
INSERT INTO `orderitem` VALUES ('16', '1', '2699', null, '1512128872000', '1512128872000', '32', '2c90edf96011e663016011e780170000');
INSERT INTO `orderitem` VALUES ('17', '1', '2699', null, '1512129772000', '1512136320000', '2', null);
INSERT INTO `orderitem` VALUES ('18', '1', '2699', null, '1512141445000', '1512141445000', '2', '2c90edf960128450016012a75b3f0000');
INSERT INTO `orderitem` VALUES ('19', '20', '29980', null, '1512184322000', '1512184322000', '3', '40286e81601534550160153598a10000');
INSERT INTO `orderitem` VALUES ('20', '1', '1999', null, '1512186364000', '1512186364000', '4', '40286e8160154e2401601554c20c0000');
INSERT INTO `orderitem` VALUES ('21', '1', '10288', null, '1512191397000', '1512191397000', '39', '40286e8160159d92016015a18de10000');
INSERT INTO `orderitem` VALUES ('22', '1', '1499', '都荣耀10几了', '1512208295000', '1512208419000', '3', '40286e8160168d44016016a365df0000');
INSERT INTO `orderitem` VALUES ('23', '1', '4499', '经典得上不了QQ', '1512208295000', '1512208419000', '34', '40286e8160168d44016016a365df0000');
INSERT INTO `orderitem` VALUES ('24', '1', '6688', '评价方未做出评价，系统默认好评。', '1512208295000', '1512219329000', '32', '40286e8160168d44016016a365df0000');
INSERT INTO `orderitem` VALUES ('25', '1', '2299', '评价方未做出评价，系统默认好评。', '1512208325000', '1512219326000', '9', '40286e8160168d44016016a3dce50001');
INSERT INTO `orderitem` VALUES ('26', '1', '1499', '评价方未做出评价，系统默认好评。', '1512213086000', '1512213092000', '3', '40286e816016e36a016016ec81ad0000');
INSERT INTO `orderitem` VALUES ('27', '1', '1999', null, '1512213101000', '1512213101000', '4', '40286e816016e36a016016ecbcac0001');
INSERT INTO `orderitem` VALUES ('28', '2', '13198', '评价方未做出评价，系统默认好评。', '1512820889000', '1512820944000', '40', '2c90edf9603b221f01603b26d7c40000');
INSERT INTO `orderitem` VALUES ('29', '2', '3598', '评价方未做出评价，系统默认好评。', '1512820889000', '1512820944000', '12', '2c90edf9603b221f01603b26d7c40000');
INSERT INTO `orderitem` VALUES ('30', '20', '131980', '硬度还凑合吧，主要买了垫桌角，希望20台能用到下个月，不然就得换神舟试试了', '1512821226000', '1512821615000', '40', '2c90edf9603b2b8701603b2bfc7c0000');
INSERT INTO `orderitem` VALUES ('31', '1', '1499', '评价方未做出评价，系统默认好评。', '1512821395000', '1512821446000', '3', '2c90edf9603b2d5401603b2e900b0000');
INSERT INTO `orderitem` VALUES ('32', '1', '6688', null, '1512821879000', '1512821879000', '32', '2c90edf9603b2ef101603b35f4a90000');
INSERT INTO `orderitem` VALUES ('33', '1', '1799', null, '1512821885000', '1512821885000', '6', '2c90edf9603b2ef101603b3608810001');
INSERT INTO `orderitem` VALUES ('34', '1', '3299', '评价方未做出评价，系统默认好评。', '1512821889000', '1512821902000', '37', '2c90edf9603b2ef101603b3619d80002');
INSERT INTO `orderitem` VALUES ('35', '1', '4199', '评价方未做出评价，系统默认好评。', '1512831835000', '1512831850000', '33', '2c90edf9603bb7e001603bcddf390000');
INSERT INTO `orderitem` VALUES ('36', '11', '72589', '评价方未做出评价，系统默认好评。', '1512896832000', '1512896849000', '40', '2c90edf9603faca301603fada27b0000');
INSERT INTO `orderitem` VALUES ('37', '11', '36289', null, '1512896864000', '1512896864000', '37', '2c90edf9603faca301603fae20970001');
INSERT INTO `orderitem` VALUES ('38', '1', '1299', '宝贝收到了，完全超过预期，质量非常好，一定会向其他人推荐此宝贝。(十五字好评0.5/条，括号内容删掉)', '1512896877000', '1512897017000', '1', '2c90edf9603faca301603fae51df0002');
INSERT INTO `orderitem` VALUES ('39', '1', '4499', '宝贝收到了，完全超过预期，质量非常好，一定会向其他人推荐此宝贝。(十五字好评0.5/条，括号内容删掉)', '1512896877000', '1512897017000', '34', '2c90edf9603faca301603fae51df0002');
INSERT INTO `orderitem` VALUES ('40', '1', '6599', '评价方未做出评价，系统默认好评。', '1512905096000', '1512905102000', '40', '2c90edf9604029af0160402bbd0b0000');
INSERT INTO `orderitem` VALUES ('41', '1', '6688', '评价方未做出评价，系统默认好评。', '1512999401000', '1513033195000', '32', '2c90edf96045c8d0016045cab8d50000');
INSERT INTO `orderitem` VALUES ('42', '1', '1999', '评价方未做出评价，系统默认好评。', '1513033203000', '1513033262000', '4', '2c90edf96047ca7f016047ce80a20000');
INSERT INTO `orderitem` VALUES ('43', '1', '1499', '评价方未做出评价，系统默认好评。', '1513033473000', '1513033532000', '3', '2c90edf96047d20c016047d29ce10000');
INSERT INTO `orderitem` VALUES ('44', '1', '2299', '评价方未做出评价，系统默认好评。', '1513035664000', '1513035674000', '31', '2c90edf96047f3b3016047f40ae90000');
INSERT INTO `orderitem` VALUES ('45', '1', '1699', '评价方未做出评价，系统默认好评。', '1513035806000', '1513035812000', '5', '2c90edf96047f3b3016047f638bf0001');
INSERT INTO `orderitem` VALUES ('46', '1', '4199', '嗯，很好很强大，bababa~~', '1513036005000', '1513036274000', '33', '2c90edf96047f3b3016047f93f070002');
INSERT INTO `orderitem` VALUES ('47', '1', '1499', '评价方未做出评价，系统默认好评。', '1513036005000', '1513036019000', '3', '2c90edf96047f3b3016047f93f070002');
INSERT INTO `orderitem` VALUES ('48', '1', '999', 'emmmmmm。(十五字好评返现5块对吧？删除括号中的内容)', '1513041833000', '1513042025000', '107', '2c90edf960484814016048522fb80000');
INSERT INTO `orderitem` VALUES ('49', '1', '999', '评价方未做出评价，系统默认好评。', '1513041833000', '1513041852000', '66', '2c90edf960484814016048522fb80000');
INSERT INTO `orderitem` VALUES ('50', '2', '1998', '衣服不错，拿来当抹布用了，下次再来买，哈哈', '1513041833000', '1513042025000', '143', '2c90edf960484814016048522fb80000');
INSERT INTO `orderitem` VALUES ('51', '1', '999', '很好很强大- -，', '1513084817000', '1513084937000', '89', '2c90edf9604adbf501604ae211040000');
INSERT INTO `orderitem` VALUES ('52', '1', '999', '评价方未做出评价，系统默认好评。', '1513084817000', '1513084834000', '109', '2c90edf9604adbf501604ae211040000');
INSERT INTO `orderitem` VALUES ('53', '1', '999', '评价方未做出评价，系统默认好评。', '1513160935000', '1513160994000', '66', '2c90edf9604f6a9d01604f6b88dd0000');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `oid` varchar(32) NOT NULL,
  `total` double DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `create_at` bigint(32) DEFAULT NULL,
  `update_at` bigint(32) DEFAULT NULL,
  `uid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `FK58x4l9shxmkb7pismj4ilt7pj` (`uid`),
  CONSTRAINT `FK58x4l9shxmkb7pismj4ilt7pj` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('2c90edf96000ec3a016000ec98e50000', '4199', '0', null, null, null, '1511843993000', null, '5');
INSERT INTO `orders` VALUES ('2c90edf9600109440160010ac5520001', '1499', '4', '华农', 'dh', '123', '1511845971000', '1512206104000', '5');
INSERT INTO `orders` VALUES ('2c90edf96011e663016011e780170000', '9387', '0', null, null, null, '1512128872000', null, '5');
INSERT INTO `orders` VALUES ('2c90edf960128450016012a75b3f0000', '2699', '3', '华农', '122', '事实上', '1512141445000', '1512186046000', '5');
INSERT INTO `orders` VALUES ('2c90edf9603b221f01603b26d7c40000', '16796', '1', '广东省广州市五山华南农业大学泰山区22栋宿舍楼', 'phoebe', '123', '1512820889000', '1512820944000', '8');
INSERT INTO `orders` VALUES ('2c90edf9603b2b8701603b2bfc7c0000', '131980', '4', '广东省广州市五山华南农业大学泰山区22栋宿舍楼', 'phoebe', '123', '1512821226000', '1512821615000', '8');
INSERT INTO `orders` VALUES ('2c90edf9603b2d5401603b2e900b0000', '1499', '1', '广东省广州市五山华南农业大学泰山区22栋宿舍楼', '汤姆', '11123', '1512821395000', '1512821446000', '8');
INSERT INTO `orders` VALUES ('2c90edf9603b2ef101603b35f4a90000', '6688', '0', null, null, null, '1512821879000', null, '9');
INSERT INTO `orders` VALUES ('2c90edf9603b2ef101603b3608810001', '1799', '0', null, null, null, '1512821885000', null, '9');
INSERT INTO `orders` VALUES ('2c90edf9603b2ef101603b3619d80002', '3299', '1', '广东省广州市五山华南农业大学泰山区22栋宿舍楼', '华为', '123', '1512821889000', '1512821902000', '9');
INSERT INTO `orders` VALUES ('2c90edf9603bb7e001603bcddf390000', '4199', '1', '华农泰山区', 'hwd', '11123', '1512831835000', '1512831850000', '16');
INSERT INTO `orders` VALUES ('2c90edf9603faca301603fada27b0000', '72589', '1', '广东省广州市五山华南农业大学泰山区22栋宿舍楼', 'xld', '666', '1512896832000', '1512896849000', '23');
INSERT INTO `orders` VALUES ('2c90edf9603faca301603fae20970001', '36289', '0', null, null, null, '1512896864000', null, '23');
INSERT INTO `orders` VALUES ('2c90edf9603faca301603fae51df0002', '5798', '4', '广东省广州市五山华南农业大学泰山区22栋宿舍楼', 'dh', '123', '1512896877000', '1512897017000', '23');
INSERT INTO `orders` VALUES ('2c90edf9604029af0160402bbd0b0000', '6599', '2', '广东省广州市五山华南农业大学泰山区22栋宿舍楼', '华为', '123', '1512905096000', '1512905245000', '24');
INSERT INTO `orders` VALUES ('2c90edf96045c8d0016045cab8d50000', '6688', '1', '广东省广州市五山华南农业大学泰山区22栋宿舍楼', '汤姆', '123', '1512999401000', '1513033195000', '11');
INSERT INTO `orders` VALUES ('2c90edf96047ca7f016047ce80a20000', '1999', '1', null, null, null, '1513033203000', '1513033262000', '11');
INSERT INTO `orders` VALUES ('2c90edf96047d20c016047d29ce10000', '1499', '1', null, null, null, '1513033473000', '1513033532000', '11');
INSERT INTO `orders` VALUES ('2c90edf96047f3b3016047f40ae90000', '2299', '1', '广东省广州市五山华南农业大学泰山区22栋宿舍楼', '华为', '11123', '1513035664000', '1513035791000', '11');
INSERT INTO `orders` VALUES ('2c90edf96047f3b3016047f638bf0001', '1699', '1', '广东省广州市五山华南农业大学泰山区22栋宿舍楼', '汤姆', '123', '1513035806000', '1513035812000', '11');
INSERT INTO `orders` VALUES ('2c90edf96047f3b3016047f93f070002', '5698', '4', '华农泰山区', '杰克马', '1111', '1513036005000', '1513036274000', '25');
INSERT INTO `orders` VALUES ('2c90edf960484814016048522fb80000', '3996', '4', '广东省广州市五山华南农业大学泰山区22栋宿舍楼', '杰克', '11123', '1513041833000', '1513042025000', '11');
INSERT INTO `orders` VALUES ('2c90edf9604adbf501604ae211040000', '1998', '4', '广东省广州市五山华南农业大学泰山区22栋宿舍楼', '华为', '123', '1513084817000', '1513084937000', '27');
INSERT INTO `orders` VALUES ('2c90edf9604f6a9d01604f6b88dd0000', '999', '0', 's', 's', 's', '1513160935000', '1513161594000', '27');
INSERT INTO `orders` VALUES ('40286e81601534550160153598a10000', '29980', '1', '华农泰山区', '测试', '11123', '1512184322000', '1512184347000', '5');
INSERT INTO `orders` VALUES ('40286e8160154e2401601554c20c0000', '1999', '0', null, null, null, '1512186364000', null, '5');
INSERT INTO `orders` VALUES ('40286e8160159d92016015a18de10000', '10288', '1', '华农泰山区', '华为', '123', '1512191397000', '1512191404000', '5');
INSERT INTO `orders` VALUES ('40286e8160168d44016016a365df0000', '12686', '4', '华农泰山区', '杰克马', '123', '1512208295000', '1512208419000', '1');
INSERT INTO `orders` VALUES ('40286e8160168d44016016a3dce50001', '2299', '1', '华农泰山区', '杰克马', '11123', '1512208325000', '1512208332000', '1');
INSERT INTO `orders` VALUES ('40286e816016e36a016016ec81ad0000', '1499', '2', '华农', '华为', '11123', '1512213086000', '1512820650000', '1');
INSERT INTO `orders` VALUES ('40286e816016e36a016016ecbcac0001', '1999', '0', null, null, null, '1512213101000', null, '1');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `pid` bigint(32) NOT NULL AUTO_INCREMENT,
  `pname` varchar(50) DEFAULT NULL,
  `market_price` double DEFAULT NULL,
  `shop_price` double DEFAULT NULL,
  `pimage` varchar(200) DEFAULT NULL,
  `pdesc` varchar(255) DEFAULT NULL,
  `pcount` bigint(20) DEFAULT NULL,
  `state` int(1) DEFAULT NULL,
  `create_at` bigint(32) DEFAULT NULL,
  `update_at` bigint(32) DEFAULT NULL,
  `cid` bigint(32) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `sfk_0001` (`cid`),
  CONSTRAINT `FKcxmxpfdetdqdqm69d4cgbhotv` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '小米 4c 标准版', '1399', '1299', 'products/1/c_0001.jpg', '小米 4c 标准版 全网通 白色 移动联通电信4G手机 双卡双待', '13', '1', '1511601968007', '1512896885000', '1');
INSERT INTO `product` VALUES ('2', '中兴 AXON', '2899', '2699', 'products/1/c_0002.jpg', '中兴 AXON 天机 mini 压力屏版 B2015 华尔金 移动联通电信4G 双卡双待', '14', '1', '1511601968006', '1512192689000', '1');
INSERT INTO `product` VALUES ('3', '华为荣耀6', '1599', '1499', 'products/1/c_0003.jpg', '荣耀 6 (H60-L01) 3GB内存标准版 黑色 移动4G手机', '40', '1', '1511601968008', '1513036076000', '1');
INSERT INTO `product` VALUES ('4', '联想 P1', '2199', '1999', 'products/1/c_0004.jpg', '联想 P1 16G 伯爵金 移动联通4G手机充电5分钟，通话3小时！科技源于超越！品质源于沉淀！5000mAh大电池！高端商务佳配！', '13', '1', '1511601968009', '1513033262000', '1');
INSERT INTO `product` VALUES ('5', '摩托罗拉 moto x（x+1）', '1799', '1699', 'products/1/c_0005.jpg', '摩托罗拉 moto x（x+1）(XT1085) 32GB 天然竹 全网通4G手机11月11天！MOTO X震撼特惠来袭！1699元！带你玩转黑科技！天然材质，原生流畅系统！', '13', '1', '1511601968010', '1513035812000', '1');
INSERT INTO `product` VALUES ('6', '魅族 MX5 16GB 银黑色', '1899', '1799', 'products/1/c_0006.jpg', '魅族 MX5 16GB 银黑色 移动联通双4G手机 双卡双待送原厂钢化膜+保护壳+耳机！5.5英寸大屏幕，3G运行内存，2070万+500万像素摄像头！长期省才是真的省！', '1', '1', '1511601968040', '1512184514000', '1');
INSERT INTO `product` VALUES ('7', '三星 Galaxy On7', '1499', '1398', 'products/1/c_0007.jpg', '三星 Galaxy On7（G6000）昂小七 金色 全网通4G手机 双卡双待新品火爆抢购中！京东尊享千元良机！5.5英寸高清大屏！1300+500W像素！评价赢30元话费券！', '1', '1', '1511601968041', '1512184519000', '1');
INSERT INTO `product` VALUES ('8', 'NUU NU5', '1288', '1190', 'products/1/c_0008.jpg', 'NUU NU5 16GB 移动联通双4G智能手机 双卡双待 晒单有礼 晨光金香港品牌 2.5D弧度前后钢化玻璃 随机附赠手机套+钢化贴膜 晒单送移动电源+蓝牙耳机', '1', '1', '1511601968042', '1512184522000', '1');
INSERT INTO `product` VALUES ('9', '乐视（Letv）乐1pro（X800）', '2399', '2299', 'products/1/c_0009.jpg', '乐视（Letv）乐1pro（X800）64GB 金色 移动联通4G手机 双卡双待乐视生态UI+5.5英寸2K屏+高通8核处理器+4GB运行内存+64GB存储+1300万摄像头！', '2', '1', '1511601968043', '1512208332000', '1');
INSERT INTO `product` VALUES ('10', '华为 Ascend Mate7', '2699', '2599', 'products/1/c_0010.jpg', '华为 Ascend Mate7 月光银 移动4G手机 双卡双待双通6英寸高清大屏，纤薄机身，智能超八核，按压式指纹识别！!选择下方“移动老用户4G飞享合约”，无需换号，还有话费每月返还！', '1', '1', '1511601968044', '1512184528000', '1');
INSERT INTO `product` VALUES ('11', 'vivo X5Pro', '2399', '2298', 'products/1/c_0014.jpg', '移动联通双4G手机 3G运存版 极光白【购机送蓝牙耳机+蓝牙自拍杆】新升级3G运行内存·双2.5D弧面玻璃·眼球识别技术', '1', '1', '1511601968045', '1512184534000', '1');
INSERT INTO `product` VALUES ('12', '努比亚（nubia）My 布拉格', '1899', '1799', 'products/1/c_0013.jpg', '努比亚（nubia）My 布拉格 银白 移动联通4G手机 双卡双待【嗨11，下单立减100】金属机身，快速充电！布拉格相机全新体验！', '6', '1', '1511601968036', '1512820969000', '1');
INSERT INTO `product` VALUES ('13', '华为 麦芒4', '2599', '2499', 'products/1/c_0012.jpg', '华为 麦芒4 晨曦金 全网通版4G手机 双卡双待金属机身 2.5D弧面屏 指纹解锁 光学防抖', '2', '1', '1511601968037', '1512184503000', '1');
INSERT INTO `product` VALUES ('14', 'vivo X5M', '1899', '1799', 'products/1/c_0011.jpg', 'vivo X5M 移动4G手机 双卡双待 香槟金【购机送蓝牙耳机+蓝牙自拍杆】5.0英寸大屏显示·八核双卡双待·Hi-Fi移动KTV', '2', '1', '1511601968038', '1512184508000', '1');
INSERT INTO `product` VALUES ('15', 'Apple iPhone 6 (A1586)', '4399', '4288', 'products/1/c_0015.jpg', 'Apple iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机长期省才是真的省！点击购机送费版，月月送话费，月月享优惠，畅享4G网络，就在联通4G！', '3', '1', '1511601968033', '1512184485000', '1');
INSERT INTO `product` VALUES ('16', '华为 HUAWEI Mate S 臻享版', '4200', '4087', 'products/1/c_0016.jpg', '华为 HUAWEI Mate S 臻享版 手机 极昼金 移动联通双4G(高配)满星评价即返30元话费啦；买就送电源+清水套+创意手机支架；优雅弧屏，mate7升级版', '3', '1', '1511601968034', '1512184488000', '1');
INSERT INTO `product` VALUES ('17', '索尼(SONY) E6533 Z3+', '4099', '3999', 'products/1/c_0017.jpg', '索尼(SONY) E6533 Z3+ 双卡双4G手机 防水防尘 涧湖绿索尼z3专业防水 2070万像素 移动联通双4G', '3', '1', '1511601968035', '1512184492000', '1');
INSERT INTO `product` VALUES ('18', 'HTC One M9+', '3599', '3499', 'products/1/c_0018.jpg', 'HTC One M9+（M9pw） 金银汇 移动联通双4G手机5.2英寸，8核CPU，指纹识别，UltraPixel超像素前置相机+2000万/200万后置双镜头相机！降价特卖，惊喜不断！', '4', '1', '1511601968032', '1512184483000', '1');
INSERT INTO `product` VALUES ('19', 'HTC Desire 826d 32G 臻珠白', '1599', '1469', 'products/1/c_0020.jpg', '后置1300万+UltraPixel超像素前置摄像头+【双】前置扬声器+5.5英寸【1080p】大屏！', '1', '1', '1511601968046', '1512184537000', '1');
INSERT INTO `product` VALUES ('20', '小米 红米2A 增强版 白色', '649', '549', 'products/1/c_0019.jpg', '新增至2GB 内存+16GB容量！4G双卡双待，联芯 4 核 1.5GHz 处理器！', '5', '1', '1511601968031', '1512184480000', '1');
INSERT INTO `product` VALUES ('21', '魅族 魅蓝note2 16GB 白色', '1099', '999', 'products/1/c_0021.jpg', '现货速抢，抢完即止！5.5英寸1080P分辨率屏幕，64位八核1.3GHz处理器，1300万像素摄像头，双色温双闪光灯！', '6', '1', '1511601968029', '1512184473000', '1');
INSERT INTO `product` VALUES ('22', '三星 Galaxy S5 (G9008W) 闪耀白', '2099', '1999', 'products/1/c_0022.jpg', '5.1英寸全高清炫丽屏，2.5GHz四核处理器，1600万像素', '6', '1', '1511601968030', '1512184477000', '1');
INSERT INTO `product` VALUES ('23', 'sonim XP7700 4G手机', '1799', '1699', 'products/1/c_0023.jpg', '三防智能手机 移动/联通双4G 安全 黑黄色 双4G美国军工IP69 30天长待机 3米防水防摔 北斗', '7', '1', '1511601968028', '1512184470000', '1');
INSERT INTO `product` VALUES ('24', '努比亚（nubia）Z9精英版 金色', '3988', '3888', 'products/1/c_0024.jpg', '移动联通电信4G手机 双卡双待真正的无边框！金色尊贵版！4GB+64GB大内存！', '32', '1', '1511601968001', '1512184399000', '1');
INSERT INTO `product` VALUES ('25', 'Apple iPhone 6 Plus (A1524) 16GB 金色', '5188', '4988', 'products/1/c_0025.jpg', 'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力', '1', '1', '1511601968047', '1512184540000', '1');
INSERT INTO `product` VALUES ('26', 'Apple iPhone 6s (A1700) 64G 玫瑰金色', '6388', '6088', 'products/1/c_0026.jpg', 'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力', '21', '1', '1511601968004', '1512184404000', '1');
INSERT INTO `product` VALUES ('27', '三星 Galaxy Note5（N9200）32G版', '5588', '5388', 'products/1/c_0027.jpg', '旗舰机型！5.7英寸大屏，4+32G内存！不一样的SPen更优化的浮窗指令！赠无线充电板！', '1', '1', '1511601968048', '1512184545000', '1');
INSERT INTO `product` VALUES ('28', '三星 Galaxy S6 Edge+（G9280）32G版 铂光金', '5999', '5888', 'products/1/c_0028.jpg', '赠移动电源+自拍杆+三星OTG金属U盘+无线充电器+透明保护壳', '23', '1', '1511601968002', '1512184401000', '1');
INSERT INTO `product` VALUES ('29', 'LG G4（H818）陶瓷白 国际版', '3018', '2978', 'products/1/c_0029.jpg', '李敏镐代言，F1.8大光圈1600万后置摄像头，5.5英寸2K屏，3G+32G内存，LG年度旗舰机！', '12', '1', '1511601968011', '1512184424000', '1');
INSERT INTO `product` VALUES ('30', '微软(Microsoft) Lumia 640 LTE DS (RM-1113)', '1099', '999', 'products/1/c_0030.jpg', '微软首款双网双卡双4G手机，5.0英寸高清大屏，双网双卡双4G！', '12', '1', '1511601968012', '1512184426000', '1');
INSERT INTO `product` VALUES ('31', '宏碁（acer）ATC705-N50 台式电脑', '2399', '2299', 'products/2/c_0031.jpg', '爆款直降，满千减百，品质宏碁，特惠来袭，何必苦等11.11，早买早便宜！', '4', '1', '1511601968049', '1513039084000', '2');
INSERT INTO `product` VALUES ('32', 'Apple MacBook Air MJVE2CH/A 13.3英寸', '6788', '6688', 'products/2/c_0032.jpg', '宽屏笔记本电脑 128GB 闪存', '18', '1', '1511601968005', '1513039086000', '2');
INSERT INTO `product` VALUES ('33', '联想（ThinkPad） 轻薄系列E450C(20EH0001CD)', '4399', '4199', 'products/2/c_0033.jpg', '联想（ThinkPad） 轻薄系列E450C(20EH0001CD)14英寸笔记本电脑(i5-4210U 4G 500G 2G独显 Win8.1)', '16', '1', '1511601968013', '1513039088000', '2');
INSERT INTO `product` VALUES ('34', '联想（Lenovo）小新V3000经典版', '4599', '4499', 'products/2/c_0034.jpg', '14英寸超薄笔记本电脑（i7-5500U 4G 500G+8G SSHD 2G独显 全高清屏）黑色满1000減100，狂减！火力全开，横扫3天！', '14', '1', '1511601968014', '1513039089000', '2');
INSERT INTO `product` VALUES ('35', '华硕（ASUS）经典系列R557LI', '3799', '3699', 'products/2/c_0035.jpg', '15.6英寸笔记本电脑（i5-5200U 4G 7200转500G 2G独显 D刻 蓝牙 Win8.1 黑色）', '12', '1', '1511601968015', '1513039091000', '2');
INSERT INTO `product` VALUES ('36', '华硕（ASUS）X450J', '4599', '4399', 'products/2/c_0036.jpg', '14英寸笔记本电脑 （i5-4200H 4G 1TB GT940M 2G独显 蓝牙4.0 D刻 Win8.1 黑色）', '12', '1', '1511601968016', '1513039093000', '2');
INSERT INTO `product` VALUES ('37', '戴尔（DELL）灵越 飞匣3000系列', '3399', '3299', 'products/2/c_0037.jpg', ' Ins14C-4528B 14英寸笔记本（i5-5200U 4G 500G GT820M 2G独显 Win8）黑', '13', '1', '1511601968017', '1513039096000', '2');
INSERT INTO `product` VALUES ('38', '惠普(HP)WASD 暗影精灵', '5699', '5499', 'products/2/c_0038.jpg', '15.6英寸游戏笔记本电脑(i5-6300HQ 4G 1TB+128G SSD GTX950M 4G独显 Win10)', '12', '1', '1511601968018', '1513039097000', '2');
INSERT INTO `product` VALUES ('39', 'Apple 配备 Retina 显示屏的 MacBook', '11299', '10288', 'products/2/c_0039.jpg', 'Pro MF840CH/A 13.3英寸宽屏笔记本电脑 256GB 闪存', '3', '1', '1511601968039', '1513039099000', '2');
INSERT INTO `product` VALUES ('40', '机械革命（MECHREVO）MR X6S-M', '6799', '6599', 'products/2/c_0040.jpg', '15.6英寸游戏本(I7-4710MQ 8G 64GSSD+1T GTX960M 2G独显 IPS屏 WIN7)黑色的', '37', '1', '1511601968050', '1513039100000', '2');
INSERT INTO `product` VALUES ('41', '神舟（HASEE） 战神K660D-i7D2', '5699', '5499', 'products/2/c_0041.jpg', '15.6英寸游戏本(i7-4710MQ 8G 1TB GTX960M 2G独显 1080P)黑色', '12', '1', '1511601968019', '1513039102000', '2');
INSERT INTO `product` VALUES ('42', '微星（MSI）GE62 2QC-264XCN', '6199', '5999', 'products/2/c_0042.jpg', '15.6英寸游戏笔记本电脑（i5-4210H 8G 1T GTX960MG DDR5 2G 背光键盘）黑色', '12', '1', '1511601968020', '1513039105000', '2');
INSERT INTO `product` VALUES ('43', '雷神（ThundeRobot）G150S', '5699', '5499', 'products/2/c_0043.jpg', '15.6英寸游戏本 ( i7-4710MQ 4G 500G GTX950M 2G独显 包无亮点全高清屏) 金', '12', '1', '1511601968021', '1513039106000', '2');
INSERT INTO `product` VALUES ('44', '惠普（HP）轻薄系列 HP', '3199', '3099', 'products/2/c_0044.jpg', '15-r239TX 15.6英寸笔记本电脑（i5-5200U 4G 500G GT820M 2G独显 win8.1）金属灰', '12', '1', '1511601968022', '1513039110000', '2');
INSERT INTO `product` VALUES ('45', '未来人类（Terrans Force）T5', '10999', '9899', 'products/2/c_0045.jpg', '15.6英寸游戏本（i7-5700HQ 16G 120G固态+1TB GTX970M 3G GDDR5独显）黑', '23', '1', '1511601968003', '1513039113000', '2');
INSERT INTO `product` VALUES ('46', '戴尔（DELL）Vostro 3800-R6308 台式电脑', '3299', '3199', 'products/2/c_0046.jpg', '（i3-4170 4G 500G DVD 三年上门服务 Win7）黑', '12', '1', '1511601968023', '1513039115000', '2');
INSERT INTO `product` VALUES ('47', '联想（Lenovo）H3050 台式电脑', '5099', '4899', 'products/2/c_0047.jpg', '（i5-4460 4G 500G GT720 1G独显 DVD 千兆网卡 Win10）23英寸', '12', '1', '1511601968024', '1513039117000', '2');
INSERT INTO `product` VALUES ('48', 'Apple iPad mini 2 ME279CH/A', '2088', '1888', 'products/2/c_0048.jpg', '（配备 Retina 显示屏 7.9英寸 16G WLAN 机型 银色）', '12', '1', '1511601968025', '1513039120000', '2');
INSERT INTO `product` VALUES ('49', '小米（MI）7.9英寸平板', '1399', '1299', 'products/2/c_0049.jpg', 'WIFI 64GB（NVIDIA Tegra K1 2.2GHz 2G 64G 2048*1536视网膜屏 800W）白色', '12', '1', '1511601968026', '1513039122000', '2');
INSERT INTO `product` VALUES ('50', 'Apple iPad Air 2 MGLW2CH/A', '2399', '2299', 'products/2/c_0050.jpg', '（9.7英寸 16G WLAN 机型 银色）', '12', '1', '1511601968027', '1513039124000', '2');
INSERT INTO `product` VALUES ('63', '老大爷椅子', '999', '333', 'products/3/0003.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1512726921000', '1513040554000', '3');
INSERT INTO `product` VALUES ('64', '好用的家具', '999', '999', 'products/3/0001.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039284000', '1513041305000', '3');
INSERT INTO `product` VALUES ('65', '好用的家具', '999', '999', 'products/3/0002.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039289000', '1513041308000', '3');
INSERT INTO `product` VALUES ('66', '好用的家具', '999', '999', 'products/3/0004.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '6', '1', '1513039291000', '1513161627000', '3');
INSERT INTO `product` VALUES ('67', '好用的家具', '999', '999', 'products/3/0005.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039293000', '1513041310000', '3');
INSERT INTO `product` VALUES ('68', '好用的家具', '999', '999', 'products/3/0006.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039295000', '1513041310000', '3');
INSERT INTO `product` VALUES ('69', '好用的家具', '999', '999', 'products/3/0007.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039296000', '1513041311000', '3');
INSERT INTO `product` VALUES ('70', '好用的家具', '999', '999', 'products/3/0008.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039297000', '1513041311000', '3');
INSERT INTO `product` VALUES ('71', '好用的家具', '999', '999', 'products/3/0009.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039298000', '1513041312000', '3');
INSERT INTO `product` VALUES ('72', '好用的家具', '999', '999', 'products/3/0010.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039299000', '1513041313000', '3');
INSERT INTO `product` VALUES ('73', '好用的家具', '999', '999', 'products/3/0011.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039300000', '1513041314000', '3');
INSERT INTO `product` VALUES ('74', '好用的家具', '999', '999', 'products/3/0012.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039308000', '1513041314000', '3');
INSERT INTO `product` VALUES ('75', '好用的家具', '999', '999', 'products/3/0013.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039309000', '1513041315000', '3');
INSERT INTO `product` VALUES ('76', '好用的家具', '999', '999', 'products/3/0014.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039310000', '1513041316000', '3');
INSERT INTO `product` VALUES ('77', '好用的家具', '999', '999', 'products/3/0015.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039317000', '1513041317000', '3');
INSERT INTO `product` VALUES ('78', '好用的家具', '999', '999', 'products/3/0016.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039319000', '1513041317000', '3');
INSERT INTO `product` VALUES ('79', '好用的家具', '999', '999', 'products/3/0017.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039319000', '1513041318000', '3');
INSERT INTO `product` VALUES ('80', '好用的家具', '999', '999', 'products/3/0018.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039320000', '1513041319000', '3');
INSERT INTO `product` VALUES ('81', '好用的家具', '999', '999', 'products/3/0019.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039321000', '1513041319000', '3');
INSERT INTO `product` VALUES ('82', '好用的家具', '999', '999', 'products/3/0020.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039322000', '1513041322000', '3');
INSERT INTO `product` VALUES ('83', '好用的家具', '999', '999', 'products/3/0021.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039323000', '1513041323000', '3');
INSERT INTO `product` VALUES ('84', '好用的家具', '999', '999', 'products/3/0022.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039324000', '1513041325000', '3');
INSERT INTO `product` VALUES ('85', '好用的家具', '999', '999', 'products/3/0023.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039325000', '1513041327000', '3');
INSERT INTO `product` VALUES ('86', '好看的包包', '999', '999', 'products/4/0001.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039326000', '1513041367000', '4');
INSERT INTO `product` VALUES ('87', '好看的包包', '999', '999', 'products/4/0002.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039327000', '1513041367000', '4');
INSERT INTO `product` VALUES ('88', '好看的包包', '999', '999', 'products/4/0003.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039813000', '1513041368000', '4');
INSERT INTO `product` VALUES ('89', '好看的包包', '999', '999', 'products/4/0004.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '2', '1', '1513039817000', '1513084898000', '4');
INSERT INTO `product` VALUES ('90', '好看的包包', '999', '999', 'products/4/0005.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039833000', '1513041369000', '4');
INSERT INTO `product` VALUES ('91', '好看的包包', '999', '999', 'products/4/0006.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039834000', '1513041370000', '4');
INSERT INTO `product` VALUES ('92', '好看的包包', '999', '999', 'products/4/0007.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039835000', '1513041370000', '4');
INSERT INTO `product` VALUES ('93', '好看的包包', '999', '999', 'products/4/0008.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039836000', '1513041371000', '4');
INSERT INTO `product` VALUES ('94', '好看的包包', '999', '999', 'products/4/0009.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039836000', '1513041371000', '4');
INSERT INTO `product` VALUES ('95', '好看的包包', '999', '999', 'products/4/0010.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039837000', '1513041372000', '4');
INSERT INTO `product` VALUES ('96', '好看的包包', '999', '999', 'products/4/0011.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039838000', '1513041373000', '4');
INSERT INTO `product` VALUES ('97', '好看的包包', '999', '999', 'products/4/0012.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039838000', '1513041374000', '4');
INSERT INTO `product` VALUES ('98', '好看的包包', '999', '999', 'products/4/0013.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039839000', '1513041375000', '4');
INSERT INTO `product` VALUES ('99', '好看的包包', '999', '999', 'products/4/0014.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039840000', '1513041376000', '4');
INSERT INTO `product` VALUES ('100', '好看的包包', '999', '999', 'products/4/0015.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039841000', '1513041376000', '4');
INSERT INTO `product` VALUES ('101', '好看的包包', '999', '999', 'products/4/0016.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039848000', '1513041377000', '4');
INSERT INTO `product` VALUES ('102', '好看的包包', '999', '999', 'products/4/0017.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039853000', '1513041378000', '4');
INSERT INTO `product` VALUES ('103', '好看的包包', '999', '999', 'products/4/0018.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039854000', '1513041379000', '4');
INSERT INTO `product` VALUES ('104', '好看的包包', '999', '999', 'products/4/0019.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039856000', '1513041381000', '4');
INSERT INTO `product` VALUES ('105', '好看的包包', '999', '999', 'products/4/0020.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513039857000', '1513041382000', '4');
INSERT INTO `product` VALUES ('106', '好读的图书', '999', '999', 'products/5/0001.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040263000', '1513041425000', '5');
INSERT INTO `product` VALUES ('107', '好读的图书', '999', '999', 'products/5/0002.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '2', '1', '1513040293000', '1513041912000', '5');
INSERT INTO `product` VALUES ('108', '好读的图书', '999', '999', 'products/5/0003.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040294000', '1513041427000', '5');
INSERT INTO `product` VALUES ('109', '好读的图书', '999', '999', 'products/5/0004.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '2', '1', '1513040295000', '1513084898000', '5');
INSERT INTO `product` VALUES ('110', '好读的图书', '999', '999', 'products/5/0005.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040296000', '1513041428000', '5');
INSERT INTO `product` VALUES ('111', '好读的图书', '999', '999', 'products/5/0006.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040296000', '1513041429000', '5');
INSERT INTO `product` VALUES ('112', '好读的图书', '999', '999', 'products/5/0007.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040303000', '1513041429000', '5');
INSERT INTO `product` VALUES ('113', '好读的图书', '999', '999', 'products/5/0008.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040304000', '1513041430000', '5');
INSERT INTO `product` VALUES ('114', '好读的图书', '999', '999', 'products/5/0009.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040304000', '1513041436000', '5');
INSERT INTO `product` VALUES ('115', '好读的图书', '999', '999', 'products/5/0010.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040305000', '1513041437000', '5');
INSERT INTO `product` VALUES ('116', '好读的图书', '999', '999', 'products/5/0011.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040306000', '1513041437000', '5');
INSERT INTO `product` VALUES ('117', '好读的图书', '999', '999', 'products/5/0012.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040306000', '1513041438000', '5');
INSERT INTO `product` VALUES ('118', '好读的图书', '999', '999', 'products/5/0013.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040307000', '1513041439000', '5');
INSERT INTO `product` VALUES ('119', '好读的图书', '999', '999', 'products/5/0014.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040307000', '1513041440000', '5');
INSERT INTO `product` VALUES ('120', '好读的图书', '999', '999', 'products/5/0015.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040308000', '1513041441000', '5');
INSERT INTO `product` VALUES ('121', '好读的图书', '999', '999', 'products/5/0016.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040311000', '1513041441000', '5');
INSERT INTO `product` VALUES ('122', '好读的图书', '999', '999', 'products/5/0017.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040324000', '1513041442000', '5');
INSERT INTO `product` VALUES ('123', '好读的图书', '999', '999', 'products/5/0018.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040324000', '1513041442000', '5');
INSERT INTO `product` VALUES ('124', '好读的图书', '999', '999', 'products/5/0019.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040325000', '1513041445000', '5');
INSERT INTO `product` VALUES ('125', '好读的图书', '999', '999', 'products/5/0020.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040326000', '1513041445000', '5');
INSERT INTO `product` VALUES ('126', '好读的图书', '999', '999', 'products/5/0021.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040327000', '1513041450000', '5');
INSERT INTO `product` VALUES ('127', '好便宜的衣服', '999', '999', 'products/6/0001.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040903000', '1513041473000', '6');
INSERT INTO `product` VALUES ('128', '好便宜的衣服', '999', '999', 'products/6/0002.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040907000', '1513041474000', '6');
INSERT INTO `product` VALUES ('129', '好便宜的衣服', '999', '999', 'products/6/0003.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040908000', '1513041475000', '6');
INSERT INTO `product` VALUES ('130', '好便宜的衣服', '999', '999', 'products/6/0004.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040914000', '1513041475000', '6');
INSERT INTO `product` VALUES ('131', '好便宜的衣服', '999', '999', 'products/6/0005.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040915000', '1513041477000', '6');
INSERT INTO `product` VALUES ('132', '好便宜的衣服', '999', '999', 'products/6/0006.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040915000', '1513041478000', '6');
INSERT INTO `product` VALUES ('133', '好便宜的衣服', '999', '999', 'products/6/0007.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040916000', '1513041478000', '6');
INSERT INTO `product` VALUES ('134', '好便宜的衣服', '999', '999', 'products/6/0008.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040917000', '1513041479000', '6');
INSERT INTO `product` VALUES ('135', '好便宜的衣服', '999', '999', 'products/6/0009.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040917000', '1513041480000', '6');
INSERT INTO `product` VALUES ('136', '好便宜的衣服', '999', '999', 'products/6/0010.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040918000', '1513041480000', '6');
INSERT INTO `product` VALUES ('137', '好便宜的衣服', '999', '999', 'products/6/0011.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040922000', '1513041481000', '6');
INSERT INTO `product` VALUES ('138', '好便宜的衣服', '999', '999', 'products/6/0012.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040923000', '1513041481000', '6');
INSERT INTO `product` VALUES ('139', '好便宜的衣服', '999', '999', 'products/6/0013.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040924000', '1513041482000', '6');
INSERT INTO `product` VALUES ('140', '好便宜的衣服', '999', '999', 'products/6/0014.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040925000', '1513041487000', '6');
INSERT INTO `product` VALUES ('141', '好便宜的衣服', '999', '999', 'products/6/0015.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040926000', '1513041488000', '6');
INSERT INTO `product` VALUES ('142', '好便宜的衣服', '999', '999', 'products/6/0016.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '0', '1', '1513040927000', '1513041489000', '6');
INSERT INTO `product` VALUES ('143', '好便宜的衣服', '999', '999', 'products/6/0017.jpg', '特价特价，全场大甩卖，买了不亏，绝对舒服，火速抢购', '4', '1', '1513040928000', '1513041912000', '6');
INSERT INTO `product` VALUES ('145', '大宝', '999', '233', 'products/8/500.jpg', '测试', '0', '1', '1513124341000', '1513124759000', '8');
INSERT INTO `product` VALUES ('146', '大宝', '999', '233', 'products/6/500.jpg', '大宝', '0', '0', '1513138872000', '1513139219000', '6');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `state` int(1) DEFAULT NULL,
  `create_at` bigint(20) DEFAULT NULL,
  `update_at` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'jackey', '202cb962ac59075b964b07152d234b70', '杰克', '1229494771@qq.com', '1', '1511218727000', '1512285147000');
INSERT INTO `user` VALUES ('4', '2017-11-2123:44:09', '202cb962ac59075b964b07152d234b70', '汤姆', 'tom@store.com', '0', '1511279057000', '1512830411000');
INSERT INTO `user` VALUES ('5', 'tom', '202cb962ac59075b964b07152d234b70', '汤姆', '799108252@qq.com', '1', '1511341722000', '1511843993000');
INSERT INTO `user` VALUES ('6', 'Monica', '202cb962ac59075b964b07152d234b70', 'Monica E. Geller', 'tom@qq.com', '1', '1511342937000', '1512830797000');
INSERT INTO `user` VALUES ('7', 'joney', '202cb962ac59075b964b07152d234b70', '华为', '799108252@qq.com', '1', '1511414728000', '1511584193000');
INSERT INTO `user` VALUES ('8', 'Phoebe', '202cb962ac59075b964b07152d234b70', 'Phoebe Buffay', '1229494771@qq.com', '1', '1512820812000', '1512830710000');
INSERT INTO `user` VALUES ('9', 'Rachel', '202cb962ac59075b964b07152d234b70', 'Rachel Karen Green', '1229494771@qq.com', '1', '1512821850000', '1512830732000');
INSERT INTO `user` VALUES ('10', 'Joey', '202cb962ac59075b964b07152d234b70', 'Joey Francis Tribbia', '1229494771@qq.com', '1', '1512830523000', null);
INSERT INTO `user` VALUES ('11', 'Chandler', '202cb962ac59075b964b07152d234b70', 'Chandler Muriel Bing', '1229494771@qq.com', '1', '1512830545000', null);
INSERT INTO `user` VALUES ('12', 'Ross', '202cb962ac59075b964b07152d234b70', 'Ross Eustace Geller', '1229494771@qq.com', '1', '1512830571000', null);
INSERT INTO `user` VALUES ('13', 'Gunther', '202cb962ac59075b964b07152d234b70', 'Gunther', '1229494771@qq.com', '1', '1512830615000', null);
INSERT INTO `user` VALUES ('14', 'Janice', '202cb962ac59075b964b07152d234b70', 'Janice Litman', '1229494771@qq.com', '1', '1512830641000', null);
INSERT INTO `user` VALUES ('15', '伦纳德', '202cb962ac59075b964b07152d234b70', '伦纳德·霍夫斯塔特', '1229494771@qq.com', '1', '1512831152000', null);
INSERT INTO `user` VALUES ('16', '霍华德', '202cb962ac59075b964b07152d234b70', '霍华德·沃洛维兹', '1229494771@qq.com', '1', '1512831172000', null);
INSERT INTO `user` VALUES ('17', '拉杰什', '202cb962ac59075b964b07152d234b70', '拉杰什·库瑟帕里', '1229494771@qq.com', '1', '1512831190000', null);
INSERT INTO `user` VALUES ('18', '伯纳黛特', '202cb962ac59075b964b07152d234b70', '伯纳黛特·罗森考斯基', '1229494771@qq.com', '1', '1512831210000', null);
INSERT INTO `user` VALUES ('19', '艾米', '202cb962ac59075b964b07152d234b70', '艾米·法拉·福勒', '1229494771@qq.com', '1', '1512831232000', null);
INSERT INTO `user` VALUES ('20', '斯图尔特', '202cb962ac59075b964b07152d234b70', '斯图尔特·布鲁姆	', '1229494771@qq.com', '0', '1512831260000', '1512831550000');
INSERT INTO `user` VALUES ('21', '艾米丽', '202cb962ac59075b964b07152d234b70', '艾米丽·斯威妮', '1229494771@qq.com', '0', '1512831283000', '1512831341000');
INSERT INTO `user` VALUES ('22', '佩妮', '202cb962ac59075b964b07152d234b70', '佩妮', '1229494771@qq.com', '1', '1512831295000', null);
INSERT INTO `user` VALUES ('23', '谢耳朵', '202cb962ac59075b964b07152d234b70', '谢尔顿·库珀', '1229494771@qq.com', '1', '1512831310000', '1512831378000');
INSERT INTO `user` VALUES ('24', 'dh', '4297f44b13955235245b2497399d7a93', 'hdh', '1229494771@qq.com', '1', '1512905079000', '1512905495000');
INSERT INTO `user` VALUES ('25', 'chan', '4297f44b13955235245b2497399d7a93', 'Chandler', '1229494771@qq.com', '1', '1513035889000', '1513036461000');
INSERT INTO `user` VALUES ('26', '泓', '4297f44b13955235245b2497399d7a93', '华为手机', '1229494771@qq.com', '1', '1513076246000', '1513079099000');
INSERT INTO `user` VALUES ('27', '测试1', '202cb962ac59075b964b07152d234b70', '测试1号', '1229494771@qq.com', '1', '1513084681000', '1513084971000');
DROP TRIGGER IF EXISTS `tri_orderitem_create`;
DELIMITER ;;
CREATE TRIGGER `tri_orderitem_create` BEFORE INSERT ON `orderitem` FOR EACH ROW BEGIN
SET new.create_at = UNIX_TIMESTAMP(now())*1000;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `tri_orderitem_update`;
DELIMITER ;;
CREATE TRIGGER `tri_orderitem_update` BEFORE UPDATE ON `orderitem` FOR EACH ROW BEGIN
SET new.update_at = UNIX_TIMESTAMP(now())*1000;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `tri_orders_create`;
DELIMITER ;;
CREATE TRIGGER `tri_orders_create` BEFORE INSERT ON `orders` FOR EACH ROW BEGIN
SET new.create_at = UNIX_TIMESTAMP(now())*1000;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `tri_orders_update`;
DELIMITER ;;
CREATE TRIGGER `tri_orders_update` BEFORE UPDATE ON `orders` FOR EACH ROW BEGIN
SET new.update_at = UNIX_TIMESTAMP(now())*1000;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `tri_category_create`;
DELIMITER ;;
CREATE TRIGGER `tri_category_create` BEFORE INSERT ON `product` FOR EACH ROW BEGIN
SET new.create_at = UNIX_TIMESTAMP(now())*1000;
SET new.update_at = UNIX_TIMESTAMP(now())*1000;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `tri_category_update`;
DELIMITER ;;
CREATE TRIGGER `tri_category_update` BEFORE UPDATE ON `product` FOR EACH ROW BEGIN
SET new.update_at = UNIX_TIMESTAMP(now())*1000;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `tri_user_create`;
DELIMITER ;;
CREATE TRIGGER `tri_user_create` BEFORE INSERT ON `user` FOR EACH ROW BEGIN
SET new.create_at = UNIX_TIMESTAMP(now())*1000;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `tri_user_update`;
DELIMITER ;;
CREATE TRIGGER `tri_user_update` BEFORE UPDATE ON `user` FOR EACH ROW BEGIN
SET new.update_at = UNIX_TIMESTAMP(now())*1000;
END
;;
DELIMITER ;
