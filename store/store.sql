/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : store28

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2017-09-15 06:48:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` varchar(32) NOT NULL,
  `cname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '手机数码');
INSERT INTO `category` VALUES ('172934bd636d485c98fd2d3d9cccd409', '运动户外');
INSERT INTO `category` VALUES ('2', '电脑办公');
INSERT INTO `category` VALUES ('3', '家具家居');
INSERT INTO `category` VALUES ('4', '鞋靴箱包');
INSERT INTO `category` VALUES ('5', '图书音像');
INSERT INTO `category` VALUES ('6', '母婴孕婴');
INSERT INTO `category` VALUES ('afdba41a139b4320a74904485bdb7719', '汽车用品');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `itemid` varchar(32) NOT NULL,
  `count` int(11) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `pid` varchar(32) DEFAULT NULL,
  `oid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  KEY `fk_0001` (`pid`),
  KEY `fk_0002` (`oid`),
  CONSTRAINT `fk_0001` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`),
  CONSTRAINT `fk_0002` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('0546AD49639D4E26B2E8577770AAC1EE', '1', '5499', '41', '6BAEEB6A99AB4674BECAB5F0C562F1BF');
INSERT INTO `orderitem` VALUES ('07A0ECB6A6604EEDB0A34DF3B6A5258F', '1', '4499', '34', '067B612EFBDA479A9AEBCE07F4B77EC2');
INSERT INTO `orderitem` VALUES ('0B4BA766AE294AAD94F175E0802E7AD2', '1', '2499', '13', '77D7D3DF9F7E49EE932C2DF4D99901DD');
INSERT INTO `orderitem` VALUES ('0CE71003DD1342E2941A65A834803943', '1', '2599', '10', '05B612CCE3974E2BA6CD784894D6C043');
INSERT INTO `orderitem` VALUES ('0D9F45C5D6E441C98FBE816B0EB2C829', '11', '46189', '33', 'B3E803CDF5FB4EAEAA7D54090A44FC2A');
INSERT INTO `orderitem` VALUES ('0DA2855541B54E0D8425435D5AB119DF', '1', '1299', '1', '9901026540314643A05FE957C0AF7B33');
INSERT INTO `orderitem` VALUES ('0DE0D37C4D1A44B6A77EFC6D4D99D04C', '1', '4199', '33', 'D5E4F08DDB06456E9A5DBABAAA2ED61E');
INSERT INTO `orderitem` VALUES ('181C53AA93F64C4A87D4D1CB5289EA60', '1', '1469', '19', '5308FE56104145BB84C94A4F7FDFB5EB');
INSERT INTO `orderitem` VALUES ('1ADDEC85C6AF447DB78C8541CBF4EB60', '1', '3699', '35', 'AF2F0DF545BE4103968169276E8A18A8');
INSERT INTO `orderitem` VALUES ('1AFB6E3847824EBEB72FD7EA6D74BB5D', '1', '3299', '37', 'E0412FF15AC842FB9A599146CF073F67');
INSERT INTO `orderitem` VALUES ('26D19D5079F94F91934E9F760AD3DF5E', '1', '1799', '12', '9901026540314643A05FE957C0AF7B33');
INSERT INTO `orderitem` VALUES ('2A5A9D374A7C46ABBDA7E6DD3823080F', '1', '6688', '32', '9904B85B382F45AD849E072094A04796');
INSERT INTO `orderitem` VALUES ('3B9247BD2B7E493A931BACBA9AC24437', '1', '6688', '32', 'AF2F0DF545BE4103968169276E8A18A8');
INSERT INTO `orderitem` VALUES ('3F62AFC895034811A588D83E5F395E32', '1', '3299', '37', 'C5A3A9EA549640B89344C6488FBACFF2');
INSERT INTO `orderitem` VALUES ('43E65EF9806C4D9D976A439A60D2757A', '1', '4499', '34', 'E2BE135DA7914E4492DAEA1C94E059A6');
INSERT INTO `orderitem` VALUES ('4EB02BD14F78402190793114B8DD395A', '1', '6688', '32', 'CB7FB36F26DE46E1BBEFFD3BEAB4E437');
INSERT INTO `orderitem` VALUES ('52E0CE5E25C64B8A8FC1BB9AD64A293E', '12', '31188', '10', 'CB7FB36F26DE46E1BBEFFD3BEAB4E437');
INSERT INTO `orderitem` VALUES ('5429273AACDF4CB39819238C95D4792E', '1', '3999', '17', '7D5966A545FE47B1B7365BA54DCEB730');
INSERT INTO `orderitem` VALUES ('61499ABE454345699F3909643440A6A2', '2', '4598', '31', 'D5E4F08DDB06456E9A5DBABAAA2ED61E');
INSERT INTO `orderitem` VALUES ('6C7366B986E4406CB88A2A19689685D0', '1', '10288', '39', 'C5A3A9EA549640B89344C6488FBACFF2');
INSERT INTO `orderitem` VALUES ('77C36AAF403A4476B5D4E83F6F2AA42B', '12', '71988', '42', 'CCB8AAE096D54A27A947A3ED702E9D18');
INSERT INTO `orderitem` VALUES ('7F2E53E81F59461EB5D6D7617D3CF848', '1', '4288', '15', 'FD2845BFE175476D9C18A989FFAA58A3');
INSERT INTO `orderitem` VALUES ('813A8871402B4733B2188747369F727A', '2', '2598', '1', 'CCB8AAE096D54A27A947A3ED702E9D18');
INSERT INTO `orderitem` VALUES ('841A2396B33E45E78ACD64187A9C6D5E', '1', '2599', '10', 'D5E4F08DDB06456E9A5DBABAAA2ED61E');
INSERT INTO `orderitem` VALUES ('88E67A07B4B548F0B231CF4A1403F696', '1', '2599', '10', '9901026540314643A05FE957C0AF7B33');
INSERT INTO `orderitem` VALUES ('AF0E96043BD4430699637DDFD4656008', '1', '5999', '42', 'AF2F0DF545BE4103968169276E8A18A8');
INSERT INTO `orderitem` VALUES ('B8DF84C4E15A43AC8064CFEC7A339D69', '1', '4499', '34', '980F9B1397774A129D1E849210BD966F');
INSERT INTO `orderitem` VALUES ('BE326D8F2B1F46A880FA839E5281AEF5', '1', '2298', '11', 'A6F150893F4C4B9DBAACA118ED219EC0');
INSERT INTO `orderitem` VALUES ('C017DB3BEEC94D348AEF9EAC43A9F236', '12', '15588', '1', 'D7CAD3A419EE4C35967AA65F36D07A89');
INSERT INTO `orderitem` VALUES ('DB95AB573A604015AB76D0DF5B289AD8', '1', '4199', '33', '79B9C821A2FD4AFD8E0210928795CB88');
INSERT INTO `orderitem` VALUES ('ECE724430DEA446D90D800369E352935', '1', '2599', '10', 'F6DB7F5EF843478E9AC926E23434AF9D');
INSERT INTO `orderitem` VALUES ('F672977DDFD347FC890A1E05A8E090CA', '1', '2699', '2', 'CCB8AAE096D54A27A947A3ED702E9D18');
INSERT INTO `orderitem` VALUES ('FD6301FA35934E25A5BB9A48A3D234EA', '12', '31188', '10', 'C8296A2A6EF04CE6BDA74B6EE76D2C6C');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `oid` varchar(32) NOT NULL,
  `ordertime` datetime DEFAULT NULL,
  `total` double DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `uid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('05B612CCE3974E2BA6CD784894D6C043', '2017-08-16 21:25:57', '2599', '0', null, null, null, '1D05BBA754B04122A9D484E52BFD0CF9');
INSERT INTO `orders` VALUES ('067B612EFBDA479A9AEBCE07F4B77EC2', '2017-08-16 21:31:04', '4499', '0', null, null, null, '1D05BBA754B04122A9D484E52BFD0CF9');
INSERT INTO `orders` VALUES ('5308FE56104145BB84C94A4F7FDFB5EB', '2017-08-16 21:31:10', '1469', '0', '广东地区', '大王', '123123123', '1D05BBA754B04122A9D484E52BFD0CF9');
INSERT INTO `orders` VALUES ('6BAEEB6A99AB4674BECAB5F0C562F1BF', '2017-08-16 21:26:02', '5499', '2', null, null, null, '1D05BBA754B04122A9D484E52BFD0CF9');
INSERT INTO `orders` VALUES ('77D7D3DF9F7E49EE932C2DF4D99901DD', '2017-08-16 21:29:11', '2499', '2', null, null, null, '1D05BBA754B04122A9D484E52BFD0CF9');
INSERT INTO `orders` VALUES ('79B9C821A2FD4AFD8E0210928795CB88', '2017-08-16 21:28:59', '4199', '2', null, null, null, '1D05BBA754B04122A9D484E52BFD0CF9');
INSERT INTO `orders` VALUES ('7D5966A545FE47B1B7365BA54DCEB730', '2017-08-16 21:29:07', '3999', '2', null, null, null, '1D05BBA754B04122A9D484E52BFD0CF9');
INSERT INTO `orders` VALUES ('980F9B1397774A129D1E849210BD966F', '2017-08-16 21:28:40', '4499', '0', null, null, null, '1D05BBA754B04122A9D484E52BFD0CF9');
INSERT INTO `orders` VALUES ('9901026540314643A05FE957C0AF7B33', '2017-08-16 21:28:36', '5697', '3', null, null, null, '1D05BBA754B04122A9D484E52BFD0CF9');
INSERT INTO `orders` VALUES ('9904B85B382F45AD849E072094A04796', '2017-08-16 21:28:51', '6688', '3', null, null, null, '1D05BBA754B04122A9D484E52BFD0CF9');
INSERT INTO `orders` VALUES ('A6F150893F4C4B9DBAACA118ED219EC0', '2017-08-16 21:25:48', '2298', '2', null, null, null, '1D05BBA754B04122A9D484E52BFD0CF9');
INSERT INTO `orders` VALUES ('AF2F0DF545BE4103968169276E8A18A8', '2017-08-20 22:17:43', '16386', '0', 'hong', 'hong', '135', 'D33FCEB271CD40DF91A161F8A22A5782');
INSERT INTO `orders` VALUES ('B3E803CDF5FB4EAEAA7D54090A44FC2A', '2017-08-14 14:29:42', '46189', '0', null, null, null, '1D05BBA754B04122A9D484E52BFD0CF9');
INSERT INTO `orders` VALUES ('C5A3A9EA549640B89344C6488FBACFF2', '2017-08-16 21:26:16', '13587', '0', null, null, null, '1D05BBA754B04122A9D484E52BFD0CF9');
INSERT INTO `orders` VALUES ('C8296A2A6EF04CE6BDA74B6EE76D2C6C', '2017-08-14 14:29:06', '31188', '3', null, null, null, '1D05BBA754B04122A9D484E52BFD0CF9');
INSERT INTO `orders` VALUES ('CB7FB36F26DE46E1BBEFFD3BEAB4E437', '2017-08-16 19:43:28', '37876', '3', null, null, null, '1D05BBA754B04122A9D484E52BFD0CF9');
INSERT INTO `orders` VALUES ('CCB8AAE096D54A27A947A3ED702E9D18', '2017-08-16 20:17:12', '77285', '3', null, null, null, '1D05BBA754B04122A9D484E52BFD0CF9');
INSERT INTO `orders` VALUES ('D5E4F08DDB06456E9A5DBABAAA2ED61E', '2017-08-14 00:00:00', '11396', '0', null, null, null, 'D33FCEB271CD40DF91A161F8A22A5782');
INSERT INTO `orders` VALUES ('D7CAD3A419EE4C35967AA65F36D07A89', '2017-08-16 21:26:07', '15588', '0', null, null, null, '1D05BBA754B04122A9D484E52BFD0CF9');
INSERT INTO `orders` VALUES ('E0412FF15AC842FB9A599146CF073F67', '2017-08-16 21:31:16', '3299', '0', '广东地区', '大王', '123123123', '1D05BBA754B04122A9D484E52BFD0CF9');
INSERT INTO `orders` VALUES ('E2BE135DA7914E4492DAEA1C94E059A6', '2017-08-16 21:25:53', '4499', '0', null, null, null, '1D05BBA754B04122A9D484E52BFD0CF9');
INSERT INTO `orders` VALUES ('F6DB7F5EF843478E9AC926E23434AF9D', '2017-08-16 21:26:21', '2599', '0', null, null, null, '1D05BBA754B04122A9D484E52BFD0CF9');
INSERT INTO `orders` VALUES ('FD2845BFE175476D9C18A989FFAA58A3', '2017-08-16 21:29:03', '4288', '0', null, null, null, '1D05BBA754B04122A9D484E52BFD0CF9');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `pid` varchar(32) NOT NULL,
  `pname` varchar(50) DEFAULT NULL,
  `market_price` double DEFAULT NULL,
  `shop_price` double DEFAULT NULL,
  `pimage` varchar(200) DEFAULT NULL,
  `pdate` date DEFAULT NULL,
  `is_hot` int(11) DEFAULT NULL,
  `pdesc` varchar(255) DEFAULT NULL,
  `pflag` int(11) DEFAULT NULL,
  `cid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `sfk_0001` (`cid`),
  CONSTRAINT `sfk_0001` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '小米 4c 标准版', '1399', '1299', 'products/1/c_0001.jpg', '2015-11-02', '1', '小米 4c 标准版 全网通 白色 移动联通电信4G手机 双卡双待', '0', '1');
INSERT INTO `product` VALUES ('10', '华为 Ascend Mate7', '2699', '2599', 'products/1/c_0010.jpg', '2015-11-02', '1', '华为 Ascend Mate7 月光银 移动4G手机 双卡双待双通6英寸高清大屏，纤薄机身，智能超八核，按压式指纹识别！!选择下方“移动老用户4G飞享合约”，无需换号，还有话费每月返还！', '0', '1');
INSERT INTO `product` VALUES ('11', 'vivo X5Pro', '2399', '2298', 'products/1/c_0014.jpg', '2015-11-02', '1', '移动联通双4G手机 3G运存版 极光白【购机送蓝牙耳机+蓝牙自拍杆】新升级3G运行内存·双2.5D弧面玻璃·眼球识别技术', '0', '1');
INSERT INTO `product` VALUES ('12', '努比亚（nubia）My 布拉格', '1899', '1799', 'products/1/c_0013.jpg', '2015-11-02', '0', '努比亚（nubia）My 布拉格 银白 移动联通4G手机 双卡双待【嗨11，下单立减100】金属机身，快速充电！布拉格相机全新体验！', '0', '1');
INSERT INTO `product` VALUES ('13', '华为 麦芒4', '2599', '2499', 'products/1/c_0012.jpg', '2015-11-02', '1', '华为 麦芒4 晨曦金 全网通版4G手机 双卡双待金属机身 2.5D弧面屏 指纹解锁 光学防抖', '0', '1');
INSERT INTO `product` VALUES ('14', 'vivo X5M', '1899', '1799', 'products/1/c_0011.jpg', '2015-11-02', '0', 'vivo X5M 移动4G手机 双卡双待 香槟金【购机送蓝牙耳机+蓝牙自拍杆】5.0英寸大屏显示·八核双卡双待·Hi-Fi移动KTV', '0', '1');
INSERT INTO `product` VALUES ('15', 'Apple iPhone 6 (A1586)', '4399', '4288', 'products/1/c_0015.jpg', '2015-11-02', '1', 'Apple iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机长期省才是真的省！点击购机送费版，月月送话费，月月享优惠，畅享4G网络，就在联通4G！', '0', '1');
INSERT INTO `product` VALUES ('16', '华为 HUAWEI Mate S 臻享版', '4200', '4087', 'products/1/c_0016.jpg', '2015-11-03', '0', '华为 HUAWEI Mate S 臻享版 手机 极昼金 移动联通双4G(高配)满星评价即返30元话费啦；买就送电源+清水套+创意手机支架；优雅弧屏，mate7升级版', '0', '1');
INSERT INTO `product` VALUES ('17', '索尼(SONY) E6533 Z3+', '4099', '3999', 'products/1/c_0017.jpg', '2015-11-02', '0', '索尼(SONY) E6533 Z3+ 双卡双4G手机 防水防尘 涧湖绿索尼z3专业防水 2070万像素 移动联通双4G', '0', '1');
INSERT INTO `product` VALUES ('18', 'HTC One M9+', '3599', '3499', 'products/1/c_0018.jpg', '2015-11-02', '0', 'HTC One M9+（M9pw） 金银汇 移动联通双4G手机5.2英寸，8核CPU，指纹识别，UltraPixel超像素前置相机+2000万/200万后置双镜头相机！降价特卖，惊喜不断！', '0', '1');
INSERT INTO `product` VALUES ('19', 'HTC Desire 826d 32G 臻珠白', '1599', '1469', 'products/1/c_0020.jpg', '2015-11-02', '1', '后置1300万+UltraPixel超像素前置摄像头+【双】前置扬声器+5.5英寸【1080p】大屏！', '0', '1');
INSERT INTO `product` VALUES ('2', '中兴 AXON', '2899', '2699', 'products/1/c_0002.jpg', '2015-11-05', '1', '中兴 AXON 天机 mini 压力屏版 B2015 华尔金 移动联通电信4G 双卡双待', '0', '1');
INSERT INTO `product` VALUES ('20', '小米 红米2A 增强版 白色', '649', '549', 'products/1/c_0019.jpg', '2015-11-02', '0', '新增至2GB 内存+16GB容量！4G双卡双待，联芯 4 核 1.5GHz 处理器！', '0', '1');
INSERT INTO `product` VALUES ('21', '魅族 魅蓝note2 16GB 白色', '1099', '999', 'products/1/c_0021.jpg', '2015-11-02', '0', '现货速抢，抢完即止！5.5英寸1080P分辨率屏幕，64位八核1.3GHz处理器，1300万像素摄像头，双色温双闪光灯！', '0', '1');
INSERT INTO `product` VALUES ('22', '三星 Galaxy S5 (G9008W) 闪耀白', '2099', '1999', 'products/1/c_0022.jpg', '2015-11-02', '1', '5.1英寸全高清炫丽屏，2.5GHz四核处理器，1600万像素', '0', '1');
INSERT INTO `product` VALUES ('23', 'sonim XP7700 4G手机', '1799', '1699', 'products/1/c_0023.jpg', '2015-11-09', '1', '三防智能手机 移动/联通双4G 安全 黑黄色 双4G美国军工IP69 30天长待机 3米防水防摔 北斗', '0', '1');
INSERT INTO `product` VALUES ('24', '努比亚（nubia）Z9精英版 金色', '3988', '3888', 'products/1/c_0024.jpg', '2015-11-02', '1', '移动联通电信4G手机 双卡双待真正的无边框！金色尊贵版！4GB+64GB大内存！', '0', '1');
INSERT INTO `product` VALUES ('25', 'Apple iPhone 6 Plus (A1524) 16GB 金色', '5188', '4988', 'products/1/c_0025.jpg', '2015-11-02', '0', 'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力', '0', '1');
INSERT INTO `product` VALUES ('26', 'Apple iPhone 6s (A1700) 64G 玫瑰金色', '6388', '6088', 'products/1/c_0026.jpg', '2015-11-02', '0', 'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力', '0', '1');
INSERT INTO `product` VALUES ('27', '三星 Galaxy Note5（N9200）32G版', '5588', '5388', 'products/1/c_0027.jpg', '2015-11-02', '0', '旗舰机型！5.7英寸大屏，4+32G内存！不一样的SPen更优化的浮窗指令！赠无线充电板！', '0', '1');
INSERT INTO `product` VALUES ('28', '三星 Galaxy S6 Edge+（G9280）32G版 铂光金', '5999', '5888', 'products/1/c_0028.jpg', '2015-11-02', '0', '赠移动电源+自拍杆+三星OTG金属U盘+无线充电器+透明保护壳', '0', '1');
INSERT INTO `product` VALUES ('29', 'LG G4（H818）陶瓷白 国际版', '3018', '2978', 'products/1/c_0029.jpg', '2015-11-02', '0', '李敏镐代言，F1.8大光圈1600万后置摄像头，5.5英寸2K屏，3G+32G内存，LG年度旗舰机！', '0', '1');
INSERT INTO `product` VALUES ('3', '华为荣耀6', '1599', '1499', 'products/1/c_0003.jpg', '2015-11-02', '0', '荣耀 6 (H60-L01) 3GB内存标准版 黑色 移动4G手机', '0', '1');
INSERT INTO `product` VALUES ('30', '微软(Microsoft) Lumia 640 LTE DS (RM-1113)', '1099', '999', 'products/1/c_0030.jpg', '2015-11-02', '0', '微软首款双网双卡双4G手机，5.0英寸高清大屏，双网双卡双4G！', '0', '1');
INSERT INTO `product` VALUES ('31', '宏碁（acer）ATC705-N50 台式电脑', '2399', '2299', 'products/1/c_0031.jpg', '2015-11-02', '0', '爆款直降，满千减百，品质宏碁，特惠来袭，何必苦等11.11，早买早便宜！', '0', '2');
INSERT INTO `product` VALUES ('32', 'Apple MacBook Air MJVE2CH/A 13.3英寸', '6788', '6688', 'products/1/c_0032.jpg', '2015-11-02', '0', '宽屏笔记本电脑 128GB 闪存', '0', '2');
INSERT INTO `product` VALUES ('33', '联想（ThinkPad） 轻薄系列E450C(20EH0001CD)', '4399', '4199', 'products/1/c_0033.jpg', '2015-11-02', '0', '联想（ThinkPad） 轻薄系列E450C(20EH0001CD)14英寸笔记本电脑(i5-4210U 4G 500G 2G独显 Win8.1)', '0', '2');
INSERT INTO `product` VALUES ('34', '联想（Lenovo）小新V3000经典版', '4599', '4499', 'products/1/c_0034.jpg', '2015-11-02', '0', '14英寸超薄笔记本电脑（i7-5500U 4G 500G+8G SSHD 2G独显 全高清屏）黑色满1000減100，狂减！火力全开，横扫3天！', '0', '2');
INSERT INTO `product` VALUES ('35', '华硕（ASUS）经典系列R557LI', '3799', '3699', 'products/1/c_0035.jpg', '2015-11-02', '0', '15.6英寸笔记本电脑（i5-5200U 4G 7200转500G 2G独显 D刻 蓝牙 Win8.1 黑色）', '0', '2');
INSERT INTO `product` VALUES ('36', '华硕（ASUS）X450J', '4599', '4399', 'products/1/c_0036.jpg', '2015-11-02', '0', '14英寸笔记本电脑 （i5-4200H 4G 1TB GT940M 2G独显 蓝牙4.0 D刻 Win8.1 黑色）', '0', '2');
INSERT INTO `product` VALUES ('37', '戴尔（DELL）灵越 飞匣3000系列', '3399', '3299', 'products/1/c_0037.jpg', '2015-11-03', '0', ' Ins14C-4528B 14英寸笔记本（i5-5200U 4G 500G GT820M 2G独显 Win8）黑', '0', '2');
INSERT INTO `product` VALUES ('38', '惠普(HP)WASD 暗影精灵', '5699', '5499', 'products/1/c_0038.jpg', '2015-11-02', '0', '15.6英寸游戏笔记本电脑(i5-6300HQ 4G 1TB+128G SSD GTX950M 4G独显 Win10)', '0', '2');
INSERT INTO `product` VALUES ('39', 'Apple 配备 Retina 显示屏的 MacBook', '11299', '10288', 'products/1/c_0039.jpg', '2015-11-02', '0', 'Pro MF840CH/A 13.3英寸宽屏笔记本电脑 256GB 闪存', '0', '2');
INSERT INTO `product` VALUES ('4', '联想 P1', '2199', '1999', 'products/1/c_0004.jpg', '2015-11-02', '0', '联想 P1 16G 伯爵金 移动联通4G手机充电5分钟，通话3小时！科技源于超越！品质源于沉淀！5000mAh大电池！高端商务佳配！', '0', '1');
INSERT INTO `product` VALUES ('40', '机械革命（MECHREVO）MR X6S-M', '6799', '6599', 'products/1/c_0040.jpg', '2015-11-02', '0', '15.6英寸游戏本(I7-4710MQ 8G 64GSSD+1T GTX960M 2G独显 IPS屏 WIN7)黑色', '0', '2');
INSERT INTO `product` VALUES ('41', '神舟（HASEE） 战神K660D-i7D2', '5699', '5499', 'products/1/c_0041.jpg', '2015-11-02', '0', '15.6英寸游戏本(i7-4710MQ 8G 1TB GTX960M 2G独显 1080P)黑色', '0', '2');
INSERT INTO `product` VALUES ('42', '微星（MSI）GE62 2QC-264XCN', '6199', '5999', 'products/1/c_0042.jpg', '2015-11-02', '0', '15.6英寸游戏笔记本电脑（i5-4210H 8G 1T GTX960MG DDR5 2G 背光键盘）黑色', '0', '2');
INSERT INTO `product` VALUES ('43', '雷神（ThundeRobot）G150S', '5699', '5499', 'products/1/c_0043.jpg', '2015-11-02', '0', '15.6英寸游戏本 ( i7-4710MQ 4G 500G GTX950M 2G独显 包无亮点全高清屏) 金', '0', '2');
INSERT INTO `product` VALUES ('44', '惠普（HP）轻薄系列 HP', '3199', '3099', 'products/1/c_0044.jpg', '2015-11-02', '0', '15-r239TX 15.6英寸笔记本电脑（i5-5200U 4G 500G GT820M 2G独显 win8.1）金属灰', '0', '2');
INSERT INTO `product` VALUES ('45', '未来人类（Terrans Force）T5', '10999', '9899', 'products/1/c_0045.jpg', '2015-11-02', '0', '15.6英寸游戏本（i7-5700HQ 16G 120G固态+1TB GTX970M 3G GDDR5独显）黑', '0', '2');
INSERT INTO `product` VALUES ('46', '戴尔（DELL）Vostro 3800-R6308 台式电脑', '3299', '3199', 'products/1/c_0046.jpg', '2015-11-02', '0', '（i3-4170 4G 500G DVD 三年上门服务 Win7）黑', '0', '2');
INSERT INTO `product` VALUES ('47', '联想（Lenovo）H3050 台式电脑', '5099', '4899', 'products/1/c_0047.jpg', '2015-11-11', '0', '（i5-4460 4G 500G GT720 1G独显 DVD 千兆网卡 Win10）23英寸', '0', '2');
INSERT INTO `product` VALUES ('48', 'Apple iPad mini 2 ME279CH/A', '2088', '1888', 'products/1/c_0048.jpg', '2015-11-02', '0', '（配备 Retina 显示屏 7.9英寸 16G WLAN 机型 银色）', '0', '2');
INSERT INTO `product` VALUES ('49', '小米（MI）7.9英寸平板', '1399', '1299', 'products/1/c_0049.jpg', '2015-11-02', '0', 'WIFI 64GB（NVIDIA Tegra K1 2.2GHz 2G 64G 2048*1536视网膜屏 800W）白色', '0', '2');
INSERT INTO `product` VALUES ('5', '摩托罗拉 moto x（x+1）', '1799', '1699', 'products/1/c_0005.jpg', '2015-11-01', '0', '摩托罗拉 moto x（x+1）(XT1085) 32GB 天然竹 全网通4G手机11月11天！MOTO X震撼特惠来袭！1699元！带你玩转黑科技！天然材质，原生流畅系统！', '0', '1');
INSERT INTO `product` VALUES ('50', 'Apple iPad Air 2 MGLW2CH/A', '2399', '2299', 'products/1/c_0050.jpg', '2015-11-12', '0', '（9.7英寸 16G WLAN 机型 银色）', '0', '2');
INSERT INTO `product` VALUES ('6', '魅族 MX5 16GB 银黑色', '1899', '1799', 'products/1/c_0006.jpg', '2015-11-02', '0', '魅族 MX5 16GB 银黑色 移动联通双4G手机 双卡双待送原厂钢化膜+保护壳+耳机！5.5英寸大屏幕，3G运行内存，2070万+500万像素摄像头！长期省才是真的省！', '0', '1');
INSERT INTO `product` VALUES ('7', '三星 Galaxy On7', '1499', '1398', 'products/1/c_0007.jpg', '2015-11-14', '0', '三星 Galaxy On7（G6000）昂小七 金色 全网通4G手机 双卡双待新品火爆抢购中！京东尊享千元良机！5.5英寸高清大屏！1300+500W像素！评价赢30元话费券！', '0', '1');
INSERT INTO `product` VALUES ('8', 'NUU NU5', '1288', '1190', 'products/1/c_0008.jpg', '2015-11-02', '0', 'NUU NU5 16GB 移动联通双4G智能手机 双卡双待 晒单有礼 晨光金香港品牌 2.5D弧度前后钢化玻璃 随机附赠手机套+钢化贴膜 晒单送移动电源+蓝牙耳机', '0', '1');
INSERT INTO `product` VALUES ('9', '乐视（Letv）乐1pro（X800）', '2399', '2299', 'products/1/c_0009.jpg', '2015-11-02', '0', '乐视（Letv）乐1pro（X800）64GB 金色 移动联通4G手机 双卡双待乐视生态UI+5.5英寸2K屏+高通8核处理器+4GB运行内存+64GB存储+1300万摄像头！', '0', '1');
INSERT INTO `product` VALUES ('D93F0629C66B4ED58D12AF5A95BE6CC8', '风水宝地', '999999', '999998', 'products/1/C0D4CED724854E65A702F6E9A99EA58A.jpg', '2017-08-20', '1', '养老', '0', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` varchar(32) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1D05BBA754B04122A9D484E52BFD0CF9', 'jack', '202cb962ac59075b964b07152d234b70', '杰克', 'jack@store.com', null, '2017-08-12', '0', '1', null);
INSERT INTO `user` VALUES ('D33FCEB271CD40DF91A161F8A22A5782', 'tom', '202cb962ac59075b964b07152d234b70', '汤姆', 'tom@store.com', null, '2017-08-13', '1', '1', null);
