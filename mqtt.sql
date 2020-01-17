/*
Navicat MySQL Data Transfer

Source Server         : 192.168.100.126-kaifa-test
Source Server Version : 50728
Source Host           : 192.168.100.126:3306
Source Database       : mqtt

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-01-17 15:32:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mall_delivery_address
-- ----------------------------
DROP TABLE IF EXISTS `mall_delivery_address`;
CREATE TABLE `mall_delivery_address` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户id',
  `receiver` varchar(100) DEFAULT NULL COMMENT '收货人',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机',
  `address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `is_default` int(1) DEFAULT NULL COMMENT '是否默认地址(0:否  1:是)',
  `state` int(10) DEFAULT '1' COMMENT '1正常 0删除  逻辑删除标识',
  `province_city` varchar(50) DEFAULT NULL COMMENT '省市区县',
  `testDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '测试时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_Reference_20` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收货地址';

-- ----------------------------
-- Records of mall_delivery_address
-- ----------------------------
INSERT INTO `mall_delivery_address` VALUES ('71ef32951dfd4d86bc2f1a518128d637', '111', '222', '12313', '1231', '123', '1231', '123123', null);

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `ID` varchar(64) NOT NULL,
  `USERNAME` varchar(32) DEFAULT NULL COMMENT '用户账号',
  `PASSWORD` varchar(32) DEFAULT NULL COMMENT '用户密码',
  `TELEPHONE` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `TELEPHONE_VERIFIED` int(11) DEFAULT NULL COMMENT '手机号码是否验证',
  `NICKNAME` varchar(45) DEFAULT NULL,
  `HEAD_IMG` blob COMMENT '头像',
  `TOKEN` varchar(36) DEFAULT NULL COMMENT '用户访问令牌',
  `TIME` varchar(36) DEFAULT NULL COMMENT '令牌创建时间',
  `VERIFY_KEY` varchar(36) DEFAULT NULL COMMENT '验证密钥',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATER` varchar(36) DEFAULT NULL COMMENT '创建人',
  `headImgUrl` varchar(256) DEFAULT NULL COMMENT '头像 url 同步统一认证img地址',
  `oauthUserId` varchar(64) DEFAULT NULL COMMENT '统一认证系统的用户id',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员';

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('06f39f5a0f3a488593508ac0e375fb27', '13915915326', '22632253717655f9c886f78c02b77f27', '13915915326', null, '123', null, null, null, null, '2019-04-10 06:46:08', '0', null, 'a5ca4c8f6b874d2e9f4fcadb1c5c2cf8');
INSERT INTO `manager` VALUES ('0c58bd35ce8f477d8ff3904078d6c384', '13357816991', '83d1773fe6072de4cd5b29359034ff80', '13357816991', null, '13357816991', null, null, null, null, '2019-03-25 21:04:26', '0', null, '895551b178fa4aaaa192d4375a883176');
INSERT INTO `manager` VALUES ('0f3b5b1db2364a46af996ddb78a06dc4', '13951908976', '99efededeaddb1916b8c4bfa8f141ee5', '13951908976', null, '风中过客', null, null, null, null, '2019-03-22 21:23:45', '0', 'http://ztgmwl.com:7501/public/pic/getImg/19bb7a6e80aa43e7864c729859e1ba51', '7213c453e5f84358a6661f061a694e64');
INSERT INTO `manager` VALUES ('1', 'admin', 'efd9d1b8bfb00e8e3647990f7d74d1d8', '13915925326', null, 'admin', null, null, null, null, '2018-03-29 19:06:45', '0', '84922a1fbe462e69555fec17155be168', 'b368d648dd6249eaaa64758cb3af6b3a');
INSERT INTO `manager` VALUES ('1dc35a5a837d4574ab290e53181cb224', '13913834423', '6419825e9f85d601cd4c1ab5922d7ee2', '13913834423', null, '13913834423', null, null, null, null, '2019-04-19 03:03:53', '0', null, '45bd90cc69a143f28352f7196e1f89af');
INSERT INTO `manager` VALUES ('22673aa2f7944c7f8512536dfe369e46', '18652995345', 'cab84b813d61afb22760031a009d0e81', '18652995345', null, '刘露', null, null, null, null, '2019-04-02 11:33:07', '0', 'http://ztgmwl.com:9004/public/pic/getImg/5ba6a6e5c62846b0aae6ff9663e43777', 'd790de0c1bcf488e8c0e45ee80841427');
INSERT INTO `manager` VALUES ('26c8a6208f5e4151822704e3c7fad5a6', '13851888888', '08792ce8770c244f71f722d49d02e7ff', '13851888888', null, '13851888888', null, null, null, null, '2019-04-17 07:40:03', '0', null, 'baec0378bb2d42168cff3741530684d5');
INSERT INTO `manager` VALUES ('2d11942c1c1c435ea8370430682f80f2', '13815429448', '5eb5bce6e2016b9a9326a04f1962962b', '13815429448', null, 'testtttt', null, null, null, null, '2019-04-18 02:00:24', '0', 'http://ztgmwl.com:9004/public/pic/getImg/47552d1158bf4b9e9b00300dd64edeba', '16ca96113cc449edb6ceb1a4733cbc19');
INSERT INTO `manager` VALUES ('2f58ed37dcaa4dbabcf75110353c4c84', '15515515515', 'c6aa0562f08935c4705935692d75a3c7', '15515515515', null, '15515515515', null, null, null, null, '2019-04-01 22:28:22', '0', null, '7f538cdb7ed24c4593f6ab89a5ea08e8');
INSERT INTO `manager` VALUES ('36c4764f9ef04e8c8e68ebe58c8d298b', '18600000000', '6f2ea5c39fbc561371b7b6d714be09c4', '18600000000', null, '18600000000', null, null, null, null, '2019-04-18 01:44:58', '0', null, 'ac61383614b241ecb46efc4cb7fbc14a');
INSERT INTO `manager` VALUES ('37831e31048e48c495a461d094588bba', '13925925326', '563f7edd9fdf92db33f20f95ba83c9e8', '13925925326', null, '12', null, null, null, null, '2019-04-10 06:47:21', '0', 'http://ztgmwl.com:9004/public/pic/getImg/c8b71e291a7240aea8c3fbe234e6abc6', 'f533e679d53447b18e19db6557251afa');
INSERT INTO `manager` VALUES ('460388c9449c4e5087fa662b8be6b73d', '18626953535', 'af1312e2063a511d5ab10dbff643b702', '18626953535', null, '18626953535', null, null, null, null, '2019-04-02 17:54:41', '0', null, '112');
INSERT INTO `manager` VALUES ('50c060d828474da589b020fa5c4a0f48', '15261850266', 'd24edc41b7929e10282e00e5303f8381', '15261850266', null, '15261850266', null, null, null, null, '2019-04-18 01:40:26', '0', null, 'fb4b5b218c254ee1af7af88feeb54b11');
INSERT INTO `manager` VALUES ('51a2662fdc7f48d1b5c1ec4ca6f6a386', '17770048312', '65aeb1746405b18078e5491765dab76d', '17770048312', null, 'sr', null, null, null, null, '2019-03-21 19:20:08', '0', null, '7d817c55a8f142dc8690c238df4df4b4');
INSERT INTO `manager` VALUES ('5340d6457145449d868bf4909bbe8b0c', '18803000000', '97b51d6f8446c0a88b763e19ce032d79', '18803000000', null, '18800000000', null, null, null, null, '2019-04-18 02:26:03', '0', null, '');
INSERT INTO `manager` VALUES ('560bf3666d8d49fd864568af3787940e', '18800000000', '97b51d6f8446c0a88b763e19ce032d79', '18800000000', null, '丹丹8', null, null, null, null, '2019-04-18 02:29:01', '0', null, '9c85e30c95ed4ce39d3275ee7efea175');
INSERT INTO `manager` VALUES ('5aa695b170b14c399a6fc7413884f452', '17700000000', '0f3ad8c1f37f11907fc1f6bebd56cfb7', '17700000000', null, '177', null, null, null, null, '2019-04-18 02:23:05', '0', null, '');
INSERT INTO `manager` VALUES ('5b8a8399993349a1a9deeda4b41b26db', '18014847852', '6d07470d9cc5e45c4658615380e982ae', '18014847852', null, '18014847852', null, null, null, null, '2019-04-04 13:18:23', '0', null, '463e690e14df41f7ad3cd745335e636b');
INSERT INTO `manager` VALUES ('5b994c9add924e6b9d2aa863b5b7a897', '13815429444', '348ada38e455357703d2cc2c829080ba', '13815429444', null, 't2', null, null, null, null, '2019-04-18 01:53:59', '0', 'http://ztgmwl.com:9004/public/pic/getImg/1a684b9011164d52afdac6418260c906', '6a7318960a5b47d98408f13e691732d3');
INSERT INTO `manager` VALUES ('68e3d96c92cf42b684b758f59aefd82b', '18051871910', '902a6b9a855b1dd6d9446c36d01fb3f9', '18051871910', null, 'LZQ', null, null, null, null, '2019-03-19 18:26:51', '0', null, '8814b6ce3b244d6e9c5fcf4b8765da9e');
INSERT INTO `manager` VALUES ('69b37e4531444b69bfd6954072175128', '18892621682', '176972b8e3c2849dde4f30f7b39e7ad9', '18892621682', null, 'gao11', null, null, null, null, '2019-04-10 07:58:07', '0', 'http://ztgmwl.com:9004/public/pic/getImg/3af82ff72b954fca9b2dd134bc5466d3', 'cbac31bff7e049d6bef9a7ebf70db13a');
INSERT INTO `manager` VALUES ('6aaa4f31a17c49c5b36719be9bae0791', '15800000000', '1e35d73d47df8303c659a0d0fd1aa72d', '15800000000', null, '15800000000', null, null, null, null, '2019-04-17 07:50:16', '0', null, '6a4cf69c1f3946709b934c5704b9db03');
INSERT INTO `manager` VALUES ('72a7d271c35f4bdd965b36f9db1d5ba5', '18652935361', '95efe487fdf21def47f5f5016fb356eb', '18652935361', null, '18652935361', null, null, null, null, '2019-03-25 20:09:15', '0', null, '47759ce088bf41e9829a487d52e682db');
INSERT INTO `manager` VALUES ('7751d24f117b4cf3ba9f4158475fea79', '13611580921', '8c46c8dd3a876926cb39b3d81554ce6e', '13611580921', null, '主账号', null, null, null, null, '2019-04-02 14:12:00', '0', 'http://ztgmwl.com:9002/public/pic/getImg/bf68ce82b5234cacbe3ec8a77ec21184', '27c265d44cda4445b1d3cf8bec1ff19b');
INSERT INTO `manager` VALUES ('817c34b2fa524034b713deb28a2e554c', '18892621681', '34b8045255b86986bca1aca24493aa44', '18892621681', null, '天吾1321', null, null, null, null, '2019-04-10 10:26:36', '0', 'http://ztgmwl.com:9004/public/pic/getImg/798a211fac5242c4b7a131aae63e48f9', '26b86ede1b9a4028b26967b1a112dd98');
INSERT INTO `manager` VALUES ('94335f848a03464aa2c5199f7fba835d', '13016966628', '51d13bba16034136d4b882112c2925d8', '13016966628', null, 'test', null, null, null, null, '2019-04-18 08:38:52', '0', null, '8b9e4c84ee2d41e9ab435e4c84b0f0d5');
INSERT INTO `manager` VALUES ('9972f14a0c6240168f2f6dcb84ed6449', '13333333333', '4316ccf974e24b7ff1321cbabb2f7198', '13333333333', null, '&amp;&lt;', null, null, null, null, '2019-04-11 03:29:20', '0', null, null);
INSERT INTO `manager` VALUES ('999554a5634048fbaaf2608b8362cdde', '18013318430', '6cda14af932e13d4e037612794e58acc', '18013318430', null, '18013318430', null, null, null, null, '2019-03-27 21:20:46', '0', null, '8e6a7565d4dd4bb0b09a14cf44e2eb5c');
INSERT INTO `manager` VALUES ('a6c6b0a3c0c64b959f974091c3d6e412', '18021505025', 'bd7beba0fafa1313c0b451a4120910ef', '18021505025', null, '18021505025', null, null, null, null, '2019-04-04 15:16:13', '0', 'http://ztgmwl.com:9002/public/pic/getImg/daa04d10488149b2afdcb247b43cdae0', 'f5941676caa74c6b94be9846572f0343');
INSERT INTO `manager` VALUES ('ab02cf6b107a4901a72f7cb7bf985b81', '15850508531', 'c50b960282afa5b162a85a09d34b8230', '15850508531', null, '丹丹认证', null, null, null, null, '2019-04-02 15:23:01', '0', 'http://ztgmwl.com:9002/public/pic/getImg/e89d1aab29d142fc8b1b1fac8ddcb674', 'b8c33209d8a2436aa1e9849411418a98');
INSERT INTO `manager` VALUES ('aba023eb2cdc41de9f4adf9b13584153', '13675184987', '4541dcf48fb4491b76f87742785d62b4', '13675184987', null, '13675184987', null, null, null, null, '2019-04-11 06:54:38', '0', 'http://ztgmwl.com:9004/public/pic/getImg/a54caea6ac4d44d1acf0518586092d13', '70597f9131764658b2fb00043f0210db');
INSERT INTO `manager` VALUES ('ac4d2f026e51499b9ccef471025047d3', '13057579236', '3e53294b29edaddcd9945f1fb3897bd5', '13057579236', null, 'even', null, null, null, null, '2019-03-27 00:09:04', '0', null, '93971025d0e84028ab3e263e051dd077');
INSERT INTO `manager` VALUES ('bb6aac433e574501ac8824cb6655e19b', '18652916101', '1a46c7fd4a06ba5b1b4966bba7c5c6ce', '18652916101', null, '186', null, null, null, null, '2019-04-16 08:46:46', '0', null, 'bdffcbf5b25b4344abb2afec7be20064');
INSERT INTO `manager` VALUES ('c96e8a6ad6664fbc94fe870ec9ec153d', '13815429446', 'a28f31f2bb7cf9f148bfd8b1d92d42e4', '13815429446', null, 'zj', null, null, null, null, '2019-04-15 01:20:41', '0', null, '12428c29d6464c589a390064f4ba9727');
INSERT INTO `manager` VALUES ('d241476016e94f108a4dd73967587c18', '13915429446', '8bc547f5e7373f9fa985b6bba835bbaa', '13915429446', null, '13915429446', null, null, null, null, '2019-04-18 02:01:27', '0', null, '');
INSERT INTO `manager` VALUES ('e5f077d44edf4cababc30890393edf45', '13900000000', '448856e26e882d10818329660da69437', '13900000000', null, '中通国脉', null, null, null, null, '2019-03-12 19:15:49', '0', null, '7dc1733fd2664e0dafcc7080198d9c1c');
INSERT INTO `manager` VALUES ('edd5a822b6744d1d8ec8688e4f9769e8', '18888888889', '5fa49c6799254105538e0dd797b6b081', '18888888889', null, '18888888889', null, null, null, null, '2019-04-02 19:12:36', '0', null, '113');
INSERT INTO `manager` VALUES ('ef90bbb019484a69a71efe837972a122', '13913834420', '0a4a536cfa0f0e49adf641b38addae18', '13913834420', null, '333', null, null, null, null, '2019-01-21 19:42:06', '0', 'b3dbfcac09575e3f8f3129458e4c1f33', null);
INSERT INTO `manager` VALUES ('fe7a72df634549918554a6f2c47e356b', '13815429443', '7e7c0f897a5f9dd985f1fb4ccf5737c5', '13815429443', null, '测试喵', null, null, null, null, '2019-02-22 22:48:07', '0', 'http://ztgmwl.com:7501/public/pic/getImg/ff6405b27d0649988169836e9333834b', '1');

-- ----------------------------
-- Table structure for mqtt_node
-- ----------------------------
DROP TABLE IF EXISTS `mqtt_node`;
CREATE TABLE `mqtt_node` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '平台名称',
  `ip_port` varchar(255) DEFAULT NULL COMMENT '平台根url',
  `status` varchar(1) DEFAULT '1' COMMENT '上下线 1：上线，0 :离线',
  `refresh_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '状态最后检查时间',
  `online_users` varchar(255) DEFAULT '0' COMMENT '在线用户数',
  `configinfo` varchar(5000) DEFAULT '' COMMENT '配置信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='mqtt节点信息表';

-- ----------------------------
-- Records of mqtt_node
-- ----------------------------

-- ----------------------------
-- Table structure for mqtt_rule
-- ----------------------------
DROP TABLE IF EXISTS `mqtt_rule`;
CREATE TABLE `mqtt_rule` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '规则名称',
  `productKey` varchar(255) DEFAULT NULL COMMENT 'prokey',
  `ruleDesc` varchar(500) DEFAULT '' COMMENT '规则描述',
  `selectdata` varchar(500) DEFAULT ' * ' COMMENT 'select',
  `topic` varchar(255) DEFAULT '0' COMMENT 'topic',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `utcModified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `wheredata` varchar(255) DEFAULT '0' COMMENT 'where',
  `rtype` varchar(10) DEFAULT 'REPUBLISH' COMMENT '规则动作类型。取值：\r\n	REPUBLISH：转发到另一个topic。\r\n	OTS：存储到表格存储。\r\n	MNS：发送消息到消息服务。\r\n	ONS：发送数据到消息队列。\r\n	TSDB：存储到高性能时间序列数据库\r\n	FC：发送数据到函数计算。\r\n	DATAHUB：发送数据到DataHub中。\r\n	RDS：存储数据到云数据库中',
  `configuration` varchar(5000) DEFAULT '' COMMENT '配置json目标动作信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='mqtt规则表';

-- ----------------------------
-- Records of mqtt_rule
-- ----------------------------
INSERT INTO `mqtt_rule` VALUES ('d8d58428ff474c269318ef97b9306cd8', 'test', 'testkey', 'test', 'a.id as id', '/test/126test', '2020-01-08 16:34:22', '2020-01-08 16:34:22', 'a.id > 10 or b <5', 'REPUBLISH', '[{\"ruleType\":\"REPUBLISH\",\"desttopic\":\"cool\",\"ip\":\"192.168.100.126\"},{\"ruleType\":\"REPUBLISH\",\"desttopic\":\"/test/otherTopic\",\"ip\":\"192.168.100.126\"}]');

-- ----------------------------
-- Table structure for procompany
-- ----------------------------
DROP TABLE IF EXISTS `procompany`;
CREATE TABLE `procompany` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `productKey` varchar(255) DEFAULT NULL COMMENT 'prokey',
  `secret` varchar(500) DEFAULT '' COMMENT 'secret',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司表';

-- ----------------------------
-- Records of procompany
-- ----------------------------

-- ----------------------------
-- Table structure for rule
-- ----------------------------
DROP TABLE IF EXISTS `rule`;
CREATE TABLE `rule` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `CreateUserId` int(11) NOT NULL COMMENT '创建用户id',
  `DataType` varchar(50) NOT NULL DEFAULT 'JSON' COMMENT '该规则的数据类型，取值：JSON和BINARY',
  `Name` varchar(500) NOT NULL COMMENT '规则名称',
  `ProductKey` varchar(255) NOT NULL COMMENT 'prokey',
  `RuleDesc` varchar(500) DEFAULT '' COMMENT '规则描述',
  `SelectData` varchar(500) DEFAULT ' * ' COMMENT 'select',
  `ShortTopic` varchar(255) NOT NULL COMMENT '短topic',
  `Status` varchar(255) DEFAULT 'STOP' COMMENT '规则运行状态',
  `Topic` varchar(255) NOT NULL COMMENT 'topic',
  `UtcModified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UtcCreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `WhereData` varchar(255) DEFAULT '0' COMMENT 'where',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='mqtt规则表';

-- ----------------------------
-- Records of rule
-- ----------------------------

-- ----------------------------
-- Table structure for rule_action
-- ----------------------------
DROP TABLE IF EXISTS `rule_action`;
CREATE TABLE `rule_action` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `RuleId` int(11) NOT NULL COMMENT 'rule id',
  `Type` varchar(50) NOT NULL COMMENT '该规则动作得类型的数据类型，取值：REPUBLISH：转发到另一个topic。\r\n	// OTS：存储到表格存储。\r\n	// MNS：发送消息到消息服务。\r\n	// ONS：发送数据到消息队列。\r\n	// TSDB：存储到高性能时间序列数据库\r\n	// FC：发送数据到函数计算。\r\n	// DATAHUB：发送数据到DataHub中。\r\n	// RDS：存储数据到云数据库中。',
  `Configuration` varchar(3000) NOT NULL COMMENT '规则动作配置',
  `ErrorActionFlag` varchar(255) NOT NULL COMMENT 'String 该规则动作是否为转发错误操作数据的转发动作，即转发流转到其他云产品失败且重试失败的数据 ,true/false',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='mqtt规则动作表';

-- ----------------------------
-- Records of rule_action
-- ----------------------------

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `old_name` varchar(100) DEFAULT NULL COMMENT '文件原始名称',
  `save_name` varchar(150) DEFAULT NULL COMMENT '文件存储名称',
  `full_path` varchar(400) DEFAULT NULL COMMENT '文件本地存储全路径',
  `http_relative_path` varchar(500) DEFAULT NULL COMMENT '文件访问相对路径  (不带http://xxxx/)',
  `http_down_url` varchar(500) DEFAULT NULL COMMENT '文件下载路径.action-计算下载次数(不带http://xxxx/)',
  `file_size` int(11) DEFAULT NULL,
  `save_date` varchar(50) DEFAULT NULL,
  `file_md5` varchar(100) DEFAULT NULL,
  `down_nums` int(11) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件表';

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES ('839b4ab54f474fa38455454c8c80043b', '2019-01-21 19:41:41', 'faviconqq.png', 'b95ab097-6c62-4e25-901e-3d3f99ad6777.png', 'F:\\IMG\\upload\\apk/201901/b95ab097-6c62-4e25-901e-3d3f99ad6777.png', 'apk/201901/b95ab097-6c62-4e25-901e-3d3f99ad6777.png', '/FileSvr/downFile.action?m5=dc8fd2fbf4190207937db3767a9cdeb8', '2691', '2019-01-21 11:41:41', 'dc8fd2fbf4190207937db3767a9cdeb8', '1');
INSERT INTO `sys_file` VALUES ('8e229878495544949192b63f81ebb590', '2019-01-21 19:23:51', 'cropped-p-logo-100x100.png', 'd53bb21b-f163-4c98-8028-2f67a2b58cd1.png', 'F:\\IMG\\upload\\apk/201901/d53bb21b-f163-4c98-8028-2f67a2b58cd1.png', 'apk/201901/d53bb21b-f163-4c98-8028-2f67a2b58cd1.png', '/FileSvr/downFile.action?m5=d25d7ad7fd88446f3cab3bd7bf502d0d', '5737', '2019-01-21 11:23:51', 'd25d7ad7fd88446f3cab3bd7bf502d0d', '5');
INSERT INTO `sys_file` VALUES ('a37d441dc2b84a2a81c05375dc7086af', '2019-01-21 19:31:04', 'ff.png', 'c6dedf8b-e42a-45d3-85ff-9502dfc1707a.png', 'F:\\IMG\\upload\\apk/201901/c6dedf8b-e42a-45d3-85ff-9502dfc1707a.png', 'apk/201901/c6dedf8b-e42a-45d3-85ff-9502dfc1707a.png', '/FileSvr/downFile.action?m5=b3dbfcac09575e3f8f3129458e4c1f33', '39158', '2019-01-21 11:31:05', 'b3dbfcac09575e3f8f3129458e4c1f33', '2');
INSERT INTO `sys_file` VALUES ('a709431563384e4c83fe6f16963fadc7', '2019-01-21 19:39:29', 'head (3).png', 'd0043ef2-15b0-46ea-867d-eadd5429f377.png', 'F:\\IMG\\upload\\apk/201901/d0043ef2-15b0-46ea-867d-eadd5429f377.png', 'apk/201901/d0043ef2-15b0-46ea-867d-eadd5429f377.png', '/FileSvr/downFile.action?m5=1afece67766f660e7a500a02a573065b', '2738', '2019-01-21 11:39:30', '1afece67766f660e7a500a02a573065b', '1');
INSERT INTO `sys_file` VALUES ('f1613754f8f04348ae163a8ab5538e1d', '2019-01-21 19:23:46', 'cropped-尚磊头像-1-32x32.png', 'a1e69df6-b5c9-4ca9-af8f-f2f87abcb632.png', 'F:\\IMG\\upload\\apk/201901/a1e69df6-b5c9-4ca9-af8f-f2f87abcb632.png', 'apk/201901/a1e69df6-b5c9-4ca9-af8f-f2f87abcb632.png', '/FileSvr/downFile.action?m5=340f54b20b7c40770359173cb809bff6', '2247', '2019-01-21 11:23:46', '340f54b20b7c40770359173cb809bff6', '1');
INSERT INTO `sys_file` VALUES ('f7887ffd90b04ebc90d7b396203f4c36', '2019-01-21 18:49:18', 'd304382c-7aa6-4168-8731-ddadebbfc500.jpg', 'e6832b25-4c33-46c8-80a1-4e3e61e4b6d3.jpg', 'F:\\IMG\\upload\\apk/201901/e6832b25-4c33-46c8-80a1-4e3e61e4b6d3.jpg', 'apk/201901/e6832b25-4c33-46c8-80a1-4e3e61e4b6d3.jpg', '/FileSvr/downFile.action?m5=fff1e3459ae591983138ead774b7f0cb', '17667', '2019-01-21 10:49:18', 'fff1e3459ae591983138ead774b7f0cb', '4');

-- ----------------------------
-- Table structure for sys_operlog
-- ----------------------------
DROP TABLE IF EXISTS `sys_operlog`;
CREATE TABLE `sys_operlog` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `user_id` varchar(64) DEFAULT NULL COMMENT '登录用户ID',
  `login_name` varchar(50) DEFAULT NULL COMMENT '登录名',
  `display_name` varchar(50) DEFAULT NULL COMMENT '显示名',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `oper_type` varchar(20) DEFAULT NULL COMMENT '操作类型',
  `oper_desc` varchar(2000) DEFAULT NULL COMMENT '操作描述',
  `fun_name` varchar(200) DEFAULT NULL COMMENT '方法名：中文描述',
  `bean_class_name` varchar(200) DEFAULT NULL COMMENT 'bean class名称',
  `oper_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `delete_flag` int(11) DEFAULT '0' COMMENT '删除标记\r\n                        1 删除\r\n                        0 未删除',
  `data` text COMMENT '操作数据',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_sys_id` (`user_id`),
  CONSTRAINT `FK_Reference_sys_id` FOREIGN KEY (`user_id`) REFERENCES `manager` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统操作日志表';

-- ----------------------------
-- Records of sys_operlog
-- ----------------------------
INSERT INTO `sys_operlog` VALUES ('0066b45ef8c74784bbea8f982581a0aa', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-08 16:35:31', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"testtopic\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"testtopic\",\"wheredata\":\"a \\u003e 10\"}');
INSERT INTO `sys_operlog` VALUES ('0425c76627ec469f97def51718e5f30b', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-17 15:11:44', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"a.id\",\"topic\":\"/test/126test\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a.id \\u003e 10 or b \\u003c5\",\"rtype\":\"REPUBLISH\",\"configuration\":\"[{\\\"ruleType\\\":\\\"REPUBLISH\\\",\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"},{\\\"ruleType\\\":\\\"REPUBLISH\\\",\\\"desttopic\\\":\\\"/test/otherTopic\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}]\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"a.id as id\",\"topic\":\"/test/126test\",\"wheredata\":\"a.id \\u003e 10 or b \\u003c5\",\"configuration\":\"[{\\\"ruleType\\\":\\\"REPUBLISH\\\",\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"},{\\\"ruleType\\\":\\\"REPUBLISH\\\",\\\"desttopic\\\":\\\"/test/otherTopic\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}]\"}');
INSERT INTO `sys_operlog` VALUES ('0dd3305d7dfe407aad5de241c9db1b97', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-08 16:56:02', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"testtopic\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"wheredata\":\"a \\u003e 10\"}');
INSERT INTO `sys_operlog` VALUES ('15ad465ea9564f64bcdc94115892839b', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-14 16:58:50', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\"}\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"wheredata\":\"a \\u003e 10\"}');
INSERT INTO `sys_operlog` VALUES ('1ca04f0e37214b119ef1fed434da1d6a', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-17 15:02:54', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"a.*\",\"topic\":\"/test/126test\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a.id \\u003e 10 or b \\u003c5\",\"rtype\":\"REPUBLISH\",\"configuration\":\"[{\\\"ruleType\\\":\\\"REPUBLISH\\\",\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"},{\\\"ruleType\\\":\\\"REPUBLISH\\\",\\\"desttopic\\\":\\\"/test/otherTopic\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}]\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"a\",\"topic\":\"/test/126test\",\"wheredata\":\"a.id \\u003e 10 or b \\u003c5\",\"configuration\":\"[{\\\"ruleType\\\":\\\"REPUBLISH\\\",\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"},{\\\"ruleType\\\":\\\"REPUBLISH\\\",\\\"desttopic\\\":\\\"/test/otherTopic\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}]\"}');
INSERT INTO `sys_operlog` VALUES ('23fc59bfb51949788b6084eb6babe04e', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-14 17:03:04', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\"}\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test1\",\"wheredata\":\"a \\u003e 10\"}');
INSERT INTO `sys_operlog` VALUES ('255b83c654fb4dbd88a937b0c5907257', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-08 16:34:22', '0', 'null  --> {\"id\":\"\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"testtopic\",\"wheredata\":\"a \\u003e 10\"}');
INSERT INTO `sys_operlog` VALUES ('27f1d6f4edba4c39b3d337c5f076ed3d', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-17 13:40:57', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"wheredata\":\"a \\u003e 10\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}\"}');
INSERT INTO `sys_operlog` VALUES ('2d74ebc86051423f80898cd2d1611c5f', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-14 16:42:44', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\"}\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"wheredata\":\"a \\u003e 10\"}');
INSERT INTO `sys_operlog` VALUES ('3bb3cc043cdd4c0d89d46d462bb2f2f6', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-17 14:46:24', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a.id \\u003e 10 or b \\u003c5\",\"rtype\":\"REPUBLISH\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"wheredata\":\"a.id \\u003e 10 or b \\u003c5\",\"configuration\":\"{\\\"ruleType\\\":\\\"REPUBLISH\\\",\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}\"}');
INSERT INTO `sys_operlog` VALUES ('428d8015bfef43b0bdc8b70e11199ac9', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-14 17:08:31', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test121\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\"}\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"wheredata\":\"a \\u003e 10\"}');
INSERT INTO `sys_operlog` VALUES ('4bf0e7da8cbf4fd2bb436e483c38faf6', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-14 16:31:25', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"wheredata\":\"a \\u003e 10\"}');
INSERT INTO `sys_operlog` VALUES ('58ce249b9fa64643b2886a8549a20425', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-17 14:22:19', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10 or b \\u003c5\",\"rtype\":\"REPUBLISH\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"wheredata\":\"a.id \\u003e 10 or b \\u003c5\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}\"}');
INSERT INTO `sys_operlog` VALUES ('5cbdd6d130fd4d95b02fea0b042dc368', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-15 14:32:15', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\"}\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"wheredata\":\"a \\u003e 10\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}\"}');
INSERT INTO `sys_operlog` VALUES ('61d245ddcde94c8999e89f7e3f79fdd6', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-14 16:48:15', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\"}\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"wheredata\":\"a \\u003e 10\"}');
INSERT INTO `sys_operlog` VALUES ('63c0c8546de44c5ba9c9820ee5a311d1', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-14 17:05:48', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126tes\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\"}\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"wheredata\":\"a \\u003e 10\"}');
INSERT INTO `sys_operlog` VALUES ('6f1ce7a9a0bf45d39f5aa68cd9224d7a', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-14 17:04:48', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126tes\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\"}\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126tes\",\"wheredata\":\"a \\u003e 10\"}');
INSERT INTO `sys_operlog` VALUES ('75ab20ddf82b4c7e9c75575f4e359df1', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-17 15:11:17', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"a\",\"topic\":\"/test/126test\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a.id \\u003e 10 or b \\u003c5\",\"rtype\":\"REPUBLISH\",\"configuration\":\"[{\\\"ruleType\\\":\\\"REPUBLISH\\\",\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"},{\\\"ruleType\\\":\\\"REPUBLISH\\\",\\\"desttopic\\\":\\\"/test/otherTopic\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}]\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"a.id\",\"topic\":\"/test/126test\",\"wheredata\":\"a.id \\u003e 10 or b \\u003c5\",\"configuration\":\"[{\\\"ruleType\\\":\\\"REPUBLISH\\\",\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"},{\\\"ruleType\\\":\\\"REPUBLISH\\\",\\\"desttopic\\\":\\\"/test/otherTopic\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}]\"}');
INSERT INTO `sys_operlog` VALUES ('7e3c5e12a6af45f2922981fb0e3ec553', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-14 16:37:18', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"wheredata\":\"a \\u003e 10\"}');
INSERT INTO `sys_operlog` VALUES ('7ee284ad27624a5ba5b5544112850809', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-14 16:36:40', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"wheredata\":\"a \\u003e 10\"}');
INSERT INTO `sys_operlog` VALUES ('8f0dcc0f98524a5e9037e61625c6f865', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-17 14:21:09', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"wheredata\":\"a \\u003e 10 or b \\u003c5\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}\"}');
INSERT INTO `sys_operlog` VALUES ('9f429eea33f54aae817658265173f4f9', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-08 16:42:52', '0', 'null  --> {\"id\":\"\",\"name\":\"test2\",\"productKey\":\"testkey\",\"ruleDesc\":\"test22222\",\"selectdata\":\"*\",\"topic\":\"testtopic\",\"wheredata\":\"a \\u003c 5\"}');
INSERT INTO `sys_operlog` VALUES ('aca62e1056ac4cd5abe3a682d178b55b', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-14 16:46:47', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\"}\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"wheredata\":\"a \\u003e 10\"}');
INSERT INTO `sys_operlog` VALUES ('ce945f142ea24525a49c00523d5ce92f', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-17 14:46:54', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a.id \\u003e 10 or b \\u003c5\",\"rtype\":\"REPUBLISH\",\"configuration\":\"{\\\"ruleType\\\":\\\"REPUBLISH\\\",\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"wheredata\":\"a.id \\u003e 10 or b \\u003c5\",\"configuration\":\"[{\\\"ruleType\\\":\\\"REPUBLISH\\\",\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"},{\\\"ruleType\\\":\\\"REPUBLISH\\\",\\\"desttopic\\\":\\\"/test/otherTopic\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}]\"}');
INSERT INTO `sys_operlog` VALUES ('d0cdb4a3f4e143f382baa441a50a4eca', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-14 17:03:47', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test1\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\"}\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126tes\",\"wheredata\":\"a \\u003e 10\"}');
INSERT INTO `sys_operlog` VALUES ('d40913a921b342b18a3cb950fa17b979', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-08 16:41:49', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"testtopic\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"testtopic\",\"wheredata\":\"a \\u003e 10\"}');
INSERT INTO `sys_operlog` VALUES ('e2e5a94d384840859ae417d65c26fc3a', '1', '13915925326', 'admin', '127.0.0.1', 'Del', '删除mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.delete', 'MqttRule', '2020-01-08 16:43:28', '0', '{\"id\":\"1860b48161244facbea5eea38328df2e\",\"name\":\"test2\",\"productKey\":\"testkey\",\"ruleDesc\":\"test22222\",\"selectdata\":\"*\",\"topic\":\"testtopic\",\"CREATE_DATE\":\"2020-01-08 16:42:52.0\",\"utcModified\":\"2020-01-08 16:42:52.0\",\"wheredata\":\"a \\u003c 5\",\"rtype\":\"REPUBLISH\",\"configuration\":\"\"}');
INSERT INTO `sys_operlog` VALUES ('f113c03c544d4fd999558477c1916cae', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-08 16:35:05', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"testtopic\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"testtopic\",\"wheredata\":\"a \\u003e 10\"}');
INSERT INTO `sys_operlog` VALUES ('f36a42ffeb1f42d9944a7998c1714f27', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-17 13:45:30', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"wheredata\":\"a \\u003e 10\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}\"}');
INSERT INTO `sys_operlog` VALUES ('f3933d17d6564281a43f28b12662d39d', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-14 17:07:04', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a \\u003e 10\",\"rtype\":\"REPUBLISH\",\"configuration\":\"{\\\"desttopic\\\":\\\"cool\\\"}\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test121\",\"wheredata\":\"a \\u003e 10\"}');
INSERT INTO `sys_operlog` VALUES ('fc9239c2630a4888a85087dd81a778d9', '1', '13915925326', 'admin', '127.0.0.1', 'SaveOrUpdate', '保存修改mqtt规则表', 'com.ztgm.mqtt.controller.WebController.MqttRuleController.saveOrUpdate', 'MqttRule', '2020-01-17 15:01:11', '0', '{\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"*\",\"topic\":\"/test/126test\",\"CREATE_DATE\":\"2020-01-08 16:34:22.0\",\"utcModified\":\"2020-01-08 16:34:22.0\",\"wheredata\":\"a.id \\u003e 10 or b \\u003c5\",\"rtype\":\"REPUBLISH\",\"configuration\":\"[{\\\"ruleType\\\":\\\"REPUBLISH\\\",\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"},{\\\"ruleType\\\":\\\"REPUBLISH\\\",\\\"desttopic\\\":\\\"/test/otherTopic\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}]\"}  --> {\"id\":\"d8d58428ff474c269318ef97b9306cd8\",\"name\":\"test\",\"productKey\":\"testkey\",\"ruleDesc\":\"test\",\"selectdata\":\"a.*\",\"topic\":\"/test/126test\",\"wheredata\":\"a.id \\u003e 10 or b \\u003c5\",\"configuration\":\"[{\\\"ruleType\\\":\\\"REPUBLISH\\\",\\\"desttopic\\\":\\\"cool\\\",\\\"ip\\\":\\\"192.168.100.126\\\"},{\\\"ruleType\\\":\\\"REPUBLISH\\\",\\\"desttopic\\\":\\\"/test/otherTopic\\\",\\\"ip\\\":\\\"192.168.100.126\\\"}]\"}');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `name` varchar(128) NOT NULL COMMENT '资源名称',
  `type` varchar(32) NOT NULL COMMENT '权限类型：1，一级菜单；  2，二级菜单； 3，二级菜单功能下的按钮',
  `url` varchar(128) DEFAULT NULL COMMENT '访问url地址',
  `percode` varchar(128) DEFAULT NULL COMMENT '权限代码字符串',
  `parentid` varchar(128) DEFAULT NULL COMMENT '父结点id',
  `parentids` varchar(128) DEFAULT NULL COMMENT '父结点id列表串',
  `sortstring` varchar(128) DEFAULT NULL COMMENT '排序号',
  `available` char(1) DEFAULT '1' COMMENT '是否可用,1：可用，0不可用',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标或者icon class',
  `plat_id` varchar(64) DEFAULT NULL COMMENT '所属平台id',
  `testDate` timestamp NULL DEFAULT NULL COMMENT '测试时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限-菜单-按钮';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('&amp;&lt;&gt;&quot;&#39;', '&amp;&lt;&gt;&quot;&#39;', '2', '&amp;amp;&amp;lt;&amp;gt;&amp;quot;&amp;#39;', '&amp;amp;&amp;lt;&amp;gt;&amp;quot;&amp;#39;', '0', null, '1', '1', null, 'ccbd41c8c75d44bab2f8f60fcf51a794', null);
INSERT INTO `sys_permission` VALUES ('1', '系统设置', '1', '123123', 'sys', '0', null, '1', '1', 'fa fa-user', '1', null);
INSERT INTO `sys_permission` VALUES ('100', '系统权限管理', '2', '/privilege/permission/index', 'pri:view', '1', null, '1', '1', 'xuanzecj.png', '1', null);
INSERT INTO `sys_permission` VALUES ('102', '角色管理', '2', '/privilege/role/index', 'role:view', '1', null, '2', '1', 'xuanzecj.png', '1', null);
INSERT INTO `sys_permission` VALUES ('12', '1', '2', '', '1', '0', null, '1', '1', null, '302fa461746442c5ae5254aec8de67ad', null);
INSERT INTO `sys_permission` VALUES ('123', '123123', '2', '12', '123', '0', null, '5', '1', null, '136563c1b9134633ac861924e34f3d5d', null);
INSERT INTO `sys_permission` VALUES ('12312', 'dddd', '1', '/', '33:view', '0', null, '1', '1', null, '6ecac655c9054a5191d21318dc228c24', null);
INSERT INTO `sys_permission` VALUES ('13', '1', '2', '1', '1', '0', null, '1', '0', null, '302fa461746442c5ae5254aec8de67ad', null);
INSERT INTO `sys_permission` VALUES ('13222', '123', '2', '123', '13', 'z-1', null, '1', '1', null, '2', null);
INSERT INTO `sys_permission` VALUES ('1你啊啊啊aaa...', '1你啊啊啊aaa...', '2', '', '1你啊啊啊aaa...', '0', null, '10', '1', null, '302fa461746442c5ae5254aec8de67ad', null);
INSERT INTO `sys_permission` VALUES ('5', '用户管理', '2', '/manager/user/manager', 'user:view', '1', null, '1', '1', 'xuanzecj.png', '1', null);
INSERT INTO `sys_permission` VALUES ('6ecac655c9054a5191d21318dc228c_menu_auto', '自动生成菜单', '1', '/', 'menu_auto:view', '0', null, '100', '1', null, '6ecac655c9054a5191d21318dc228c24', null);
INSERT INTO `sys_permission` VALUES ('6ecac655c9054a5191d21318dc228c_menu_test', '测试222管理', '2', '/manager/test/manager', 'test:view', '6ecac655c9054a5191d21318dc228c_menu_auto', null, '1', '1', null, '6ecac655c9054a5191d21318dc228c24', null);
INSERT INTO `sys_permission` VALUES ('a19dc024e757417697cb3d5117427d_menu_auto', '自动生成菜单', '1', '/', 'menu_auto:view', '0', null, '100', '1', null, 'a19dc024e757417697cb3d5117427dd3', null);
INSERT INTO `sys_permission` VALUES ('a19dc024e757417697cb3d5117427d_menu_iotsysrolecommunity', '角色小区权限表管理', '2', '/manager/iotsysrolecommunity/manager', 'iotsysrolecommunity:view', 'a19dc024e757417697cb3d5117427d_menu_auto', null, '1', '1', null, 'a19dc024e757417697cb3d5117427dd3', null);
INSERT INTO `sys_permission` VALUES ('a19dc024e757417697cb3d5117427d_menu_iotsysrolelock', '角色锁权限表管理', '2', '/manager/iotsysrolelock/manager', 'iotsysrolelock:view', 'a19dc024e757417697cb3d5117427d_menu_auto', null, '1', '1', null, 'a19dc024e757417697cb3d5117427dd3', null);
INSERT INTO `sys_permission` VALUES ('d&#39;f&#39;s&#39;d&#39;s&#39;d&#39;f', 'd&#39;f&#39;s', '2', '手动分', '123', '0', null, '1', '1', null, '2', null);
INSERT INTO `sys_permission` VALUES ('iot1', '系统管理员菜单', '1', '/', null, 'iot0', null, '1', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:12:34');
INSERT INTO `sys_permission` VALUES ('iot10', '区域管理', '2', 'manager/region/findRegionDevice', 'qygl-1.png', 'iot2', null, '4', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:12:34');
INSERT INTO `sys_permission` VALUES ('iot11', '统计报表', '2', 'statistics/view.action', 'statistics:view', 'iot3', null, '1', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:12:34');
INSERT INTO `sys_permission` VALUES ('iot12', '审计查询', '2', 'audit/view.action', 'audit:view', 'iot3', null, '2', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:12:34');
INSERT INTO `sys_permission` VALUES ('iot13', '预警管理', '2', 'warning/view.action', 'warning/view', 'iot4', null, '3', '0', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:12:34');
INSERT INTO `sys_permission` VALUES ('iot14', '消息管理', '2', 'message/view.action', 'message:view', 'iot4', null, '2', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:12:34');
INSERT INTO `sys_permission` VALUES ('iot15', '用户中心', '2', 'manager/admin/index', 'yhzx-1.png', 'iot4', null, '1', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:12:34');
INSERT INTO `sys_permission` VALUES ('iot16', '权限管理', '2', 'manager/group/etrGroupUserList', 'qxgl.png', 'iot4', null, '3', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:12:34');
INSERT INTO `sys_permission` VALUES ('iot18', '网关管理', '2', 'manager/gateWay/view.action', 'wangguan-1.png', 'iot2', null, '1', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:12:34');
INSERT INTO `sys_permission` VALUES ('iot2', '实施人员菜单', '1', '/', null, 'iot0', null, '2', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:12:34');
INSERT INTO `sys_permission` VALUES ('iot20', 'APP升级管理', '2', 'manager/etrSysconf', 'app-1.png', 'iot1', null, '2', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:12:34');
INSERT INTO `sys_permission` VALUES ('iot21', '供应商管理', '2', 'manager/manufacturer/etrMain', 'gys-1.png', 'iot1', null, '3', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:12:34');
INSERT INTO `sys_permission` VALUES ('iot22', '项目管理', '2', 'manager/project/etrMain', 'xmgl-1.png', 'iot1', null, '4', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:12:34');
INSERT INTO `sys_permission` VALUES ('iot23', '设备管理', '2', 'manager/device/etrMain', 'sbgl-1.png', 'iot1', null, '6', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:13:30');
INSERT INTO `sys_permission` VALUES ('iot24', '数据图表展现', '2', 'manager/dchart/etrDataChart', 'sjtbzx-1.png', 'iot1', null, '7', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:13:30');
INSERT INTO `sys_permission` VALUES ('iot26', '配置管理', '2', 'manager/config/configList', 'pzgl-1.png', 'iot1', null, '9', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:13:30');
INSERT INTO `sys_permission` VALUES ('iot27', '日志管理', '2', 'manager/log/LogList', 'rzgl-1.png', 'iot1', null, '10', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:13:30');
INSERT INTO `sys_permission` VALUES ('iot28', '网关信息', '2', 'manager/gateWay/adminEnter', 'wangguan-1.png', 'iot1', null, '5', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:13:30');
INSERT INTO `sys_permission` VALUES ('iot3', '设备供应商菜单', '1', '/', null, 'iot0', null, '3', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:13:30');
INSERT INTO `sys_permission` VALUES ('iot30', '网关白名单', '2', 'manager/gateWayWhite/index', 'znms.png', 'iot2', null, '10', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:13:30');
INSERT INTO `sys_permission` VALUES ('iot31', '消息推送', '2', 'manager/sendMessage/index', 'sbgl-1.png', 'iot1', null, '1', '1', null, '3a008915d48c485faf4e5e9497270be4', null);
INSERT INTO `sys_permission` VALUES ('iot4', '普通用户菜单', '1', '/', null, 'iot0', null, '4', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:13:30');
INSERT INTO `sys_permission` VALUES ('iot5', '用户管理与行为分析', '2', 'manager/user/manager.action', 'yhgl-1.png', 'iot1', null, '1', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:13:30');
INSERT INTO `sys_permission` VALUES ('iot7', '场景管理', '2', 'manager/scene/findSceneDevice', 'cjgl-1.png', 'iot2', null, '5', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:13:30');
INSERT INTO `sys_permission` VALUES ('iot8', '管理权限转移', '2', 'manager/user/etrpermissionTransfer', 'zhuanyi-1.png', 'iot2', null, '2', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:13:30');
INSERT INTO `sys_permission` VALUES ('iot9', '设备管理', '2', 'manager/device/deviceList', 'sbgl-1.png', 'iot2', null, '3', '1', '2', '3a008915d48c485faf4e5e9497270be4', '2019-03-15 23:13:30');
INSERT INTO `sys_permission` VALUES ('lock_1', '系统设置', '1', '/', 'sys', '0', null, '1', '1', 'fa fa-cogs', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_100', '系统权限管理', '2', '/privilege/permission/index', 'pri:view', 'lock_1', null, '1', '1', 'xuanzecj.png', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_102', '角色管理', '2', '/privilege/role/index', 'role:view', 'lock_1', null, '2', '1', 'xuanzecj.png', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_5', '用户管理', '2', '/manager/user/manager', 'user:view', 'lock_1', null, '1', '1', 'xuanzecj.png', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_company', '企业审核', '1', '/', 'compnay:view', '0', null, '3', '1', 'fa fa-group', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_menu_orderCompleted', '已完结工单', '2', '/manager/orderCompleted/manager', 'orderCompleted:view', 'lock_order_manager', null, '6', '1', '', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_menu_project', '工程表管理', '2', '/manager/tproject/manager', 'tproject:view', 'lock_project_m', null, '1', '1', '', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_menu_tcompany', '待审核企业管理', '2', '/manager/tcompany/manager', 'tcompany:view', 'lock_company', null, '1', '1', 'f', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_menu_tuserinfo', '待审核锁匠', '2', '/manager/tuserinfo/manager', 'tuserinfo:view', 'lock_m_user_manager', null, '1', '1', '', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_menu_tuseropinion', '用户意见管理', '2', '/manager/tuseropinion/manager', 'tuseropinion:view', 'lock_suggest_manager', null, '1', '1', 'fa fa-commets', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_m_user_manager', '锁匠管理', '1', '/', 'm_user_manager:view', '0', null, '2', '1', 'fa fa-user', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_npass_company', '审核不通过企业', '2', '/manager/tcompany/managerNoPass', 'npass_compnay:view', 'lock_company', null, '3', '1', null, 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_order_area_stastic', '工单区域统计', '2', '/manager/torderAreaStatistics/manager', 'order_area_stastic:view', 'lock_stastic_manager', null, '2', '1', '', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_order_doing', '待作业工单', '2', '//manager/torderinfo_doing/manager', 'order_doing:view', 'lock_order_manager', null, '3', '1', '', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_order_huifang', '待回访工单', '2', '/manager/torderinfo_huif/manager', 'order_huifang:view', 'lock_order_manager', null, '4', '1', '', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_order_log', '工单历史', '2', '/manager/torderinfooperatelog/manager', 'order_log:view', 'lock_order_manager', null, '7', '1', '', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_order_manager', '工单管理', '1', '/', 'order_manager:view', '0', null, '3', '1', 'fa fa-clone', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_order_status_stastic', '工单状态统计', '2', '/manager/stastic/statuslist', 'order_status_stastic:view', 'lock_stastic_manager', null, '1', '1', '', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_order_to_assgin', '待接单工单', '2', '/manager/torderinfo_todo/manager', 'order_to_assgin:view', 'lock_order_manager', null, '2', '1', '', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_order_to_pay', '待结账工单', '2', '/manager/torderinfo_pay/manager', 'order_to_pay：view', 'lock_order_manager', null, '5', '1', '', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_order_type_stastic', '工单类型统计', '2', '/manager/torderTypeStatistics/manager', 'order_type_stastic:view', 'lock_stastic_manager', null, '3', '1', '', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_pass_company', '审核通过企业', '2', '/manager/tcompany/managerPass', 'pass_compnay:view', 'lock_company', null, '2', '1', null, 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_project_m', '工程管理', '1', '/', 'project_m:view', '0', null, '3', '1', null, 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_score_manager', '评分管理', '2', '/manager/tuserinfoPoint/manager', 'score_manager:view', 'lock_m_user_manager', null, '5', '1', 'fa fa-coffee', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_stastic_manager', '统计管理', '1', '/', 'stastic_manager:view', '0', null, '6', '1', 'fa fa-bar-chart', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_suggest_manager', '意见反馈', '1', '/', 'suggest_manager:view', '0', null, '7', '1', 'fa fa-comments', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_sys_log', '系统日志', '2', '/manager/sysOperLog/manager', 'sys_log', 'lock_1', null, '9', '1', null, 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_user_money', '锁匠费用', '2', '/manager/tuserinfoMoney/manager', 'user_money:view', 'lock_m_user_manager', null, '4', '1', '', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_user_nopass', '审核不通过锁匠', '2', '/manager/tuserinfo/managerNoPass', 'managerNoPass:view', 'lock_m_user_manager', null, '3', '1', 'fa', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('lock_user_to_auidt', '已审核锁匠', '2', '/manager/tuserinfo/managerPass', 'managerPass:view', 'lock_m_user_manager', null, '2', '1', 'fa', 'e43f2ca00867435d8bd079f5213f2b31', null);
INSERT INTO `sys_permission` VALUES ('menu_auto', '自动生成菜单', '1', '/', 'menu_auto:view', '0', null, '100', '1', null, '1', null);
INSERT INTO `sys_permission` VALUES ('menu_lockresidents', '租户信息表管理', '2', '/manager/lockresidents/manager', 'lockresidents:view', 'menu_auto', null, '1', '1', null, '1', null);
INSERT INTO `sys_permission` VALUES ('menu_malldeliveryaddress', '收货地址管理', '2', '/manager/malldeliveryaddress/manager', 'malldeliveryaddress:view', 'menu_auto', null, '1', '1', null, '1', null);
INSERT INTO `sys_permission` VALUES ('menu_sysplat', '平台信息表管理', '2', '/manager/sysplat/manager?1=1', '123123:view', '12312', null, '1', '1', null, '6ecac655c9054a5191d21318dc228c24', null);
INSERT INTO `sys_permission` VALUES ('menu_test', '测试表管理', '2', '/manager/test/manager', 'test:view', 'menu_auto', null, '1', '1', null, '6ecac655c9054a5191d21318dc228c24', null);
INSERT INTO `sys_permission` VALUES ('p2_first_menu', '我是一级菜单', '1', '/', 'p2_first_menu:view', '0', null, '1', '1', null, '6ecac655c9054a5191d21318dc228c24', null);
INSERT INTO `sys_permission` VALUES ('p2_test_2', '平台管理', '2', '/manager/sysplat/manager', 'p2_test_2:view', '1', null, '1', '1', null, '1', null);
INSERT INTO `sys_permission` VALUES ('test1', '1', '2', '1', '1', '0', null, '91', '1', null, 'ccbd41c8c75d44bab2f8f60fcf51a794', null);
INSERT INTO `sys_permission` VALUES ('test2', '1', '2', '1', '1', '0', null, '91', '1', null, 'ccbd41c8c75d44bab2f8f60fcf51a794', null);
INSERT INTO `sys_permission` VALUES ('tk_menu_auto', '自动生成菜单', '1', '/', 'menu_auto:view', '0', null, '100', '1', null, null, null);
INSERT INTO `sys_permission` VALUES ('tk_menu_mqttnode', 'mqtt节点信息表管理', '2', '/manager/mqttnode/manager', 'mqttnode:view', 'tk_menu_auto', null, '1', '1', null, null, null);
INSERT INTO `sys_permission` VALUES ('tk_menu_mqttrule', 'mqtt规则表管理', '2', '/manager/mqttrule/manager', 'mqttrule:view', 'tk_menu_auto', null, '1', '1', null, null, null);
INSERT INTO `sys_permission` VALUES ('tk_menu_procompany', '公司表管理', '2', '/manager/procompany/manager', 'procompany:view', 'tk_menu_auto', null, '1', '1', null, null, null);
INSERT INTO `sys_permission` VALUES ('tk_menu_rule', '规则管理', '2', '/manager/rule/manager', 'rule:view', 'tk_menu_auto', null, '1', '1', null, null, null);
INSERT INTO `sys_permission` VALUES ('tk_menu_ruleaction', '规则动作管理', '2', '/manager/ruleaction/manager', 'ruleaction:view', 'tk_menu_auto', null, '1', '1', null, null, null);
INSERT INTO `sys_permission` VALUES ('tt', '平台', '2', '/manager/sysplat/manager/', 'tt:view', 'p2_first_menu', null, '1', '1', null, '6ecac655c9054a5191d21318dc228c24', null);
INSERT INTO `sys_permission` VALUES ('z-1', '系统设置', '1', '123123', 'sys', '0', null, '1', '1', 'fa fa-user', '2', null);
INSERT INTO `sys_permission` VALUES ('z-100', '系统权限管理', '2', '/privilege/permission/index', 'pri:view', 'z-1', null, '1', '1', 'xuanzecj.png', '2', null);
INSERT INTO `sys_permission` VALUES ('z-102', '角色管理', '2', '/privilege/role/index', 'role:view', 'z-1', null, '2', '1', 'xuanzecj.png', '2', null);
INSERT INTO `sys_permission` VALUES ('z-20', '自动生成管理', '2', '/generator/index', 'generator：view', 'z-1', null, '5', '1', '123457', '2', null);
INSERT INTO `sys_permission` VALUES ('z-5', '用户管理', '2', '/manager/user/manager', 'user:view', 'z-1', null, '1', '1', 'xuanzecj.png', '2', null);
INSERT INTO `sys_permission` VALUES ('z-p2_test_2', '平台管理', '2', '/manager/sysplat/manager', 'p2_test_2:view', 'z-1', null, '1', '1', null, '2', null);
INSERT INTO `sys_permission` VALUES ('zj-iot-local-chart', '数据统计报表', '2', 'manager/dchart/etrDataChart', 'zj-iot-local-chart', 'zj-local-iot-m1', null, '1', '1', null, '3a008915d48c485faf4e5e9497270be4', null);
INSERT INTO `sys_permission` VALUES ('zj-local-iot-m1', '测试一级', '1', '/', 'zj-local-iot-mi1', '0', null, '1', '1', null, '3a008915d48c485faf4e5e9497270be4', null);
INSERT INTO `sys_permission` VALUES ('zj_local_one', '测试一级', '1', '/', 'zj_local', '0', null, '1', '1', null, 'a19dc024e757417697cb3d5117427dd3', null);

-- ----------------------------
-- Table structure for sys_plat
-- ----------------------------
DROP TABLE IF EXISTS `sys_plat`;
CREATE TABLE `sys_plat` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '平台名称',
  `root_path` varchar(255) DEFAULT NULL COMMENT '平台根url',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `icons` varchar(255) DEFAULT '' COMMENT 'fa 图标 class',
  `enable` varchar(1) DEFAULT '1' COMMENT '上下线 1：上线，0 :离线',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台信息表';

-- ----------------------------
-- Records of sys_plat
-- ----------------------------
INSERT INTO `sys_plat` VALUES ('1', '权限平台-开发', 'http://ztgmwl.com:7501/privilege/', '2019-01-11 22:50:21', 'fa fa-cog', '1');
INSERT INTO `sys_plat` VALUES ('2', '权限平台-zj-local', 'http://ztgmwl.com:9002/d1/', '2019-01-11 22:50:21', 'fa fa-cog', '0');
INSERT INTO `sys_plat` VALUES ('3a008915d48c485faf4e5e9497270be4', 'IOT管理平台-开发', 'http://ztgmwl.com:7501/iot/', '2019-03-18 23:55:42', 'fa fa-user', '1');
INSERT INTO `sys_plat` VALUES ('50e58314fa054d61b508f35c9c2f1c8f', '李飞iot开发环境', 'http://192.168.100.136:7442/iot', '2019-06-28 10:36:28', 'fa fa-cog', '1');
INSERT INTO `sys_plat` VALUES ('8d12d95b2e3e4bd0abd07474c53aba57', '&<>\"\'@', '&<>\"\'@', '2019-04-17 09:40:26', '&<>\"\'', '0');
INSERT INTO `sys_plat` VALUES ('a19dc024e757417697cb3d5117427dd3', '本地测试平台-zj', 'http://192.168.100.41:7773/local/', '2019-03-13 19:55:16', 'fa fa-cog', '0');
INSERT INTO `sys_plat` VALUES ('b1ac5a9703374bba81540f56378b5990', '为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛', '为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛为我爱的吉欧我到家哦啊四季豆赛1', '2019-04-24 12:50:14', 'fa fa-stumbleup-bleup3daos-circle-stumbleupon3on-cfa fa-stumbleupon-circle-stumbleupon-circle-stumbleupon3ircle-stfa fa-stumbleupon-circle-stumbleupon-circle-stumbleupon3umbleupon-circle-stumbleupon3fa fa-stumbleupon-circle-stumbleupon-circle-stumbleupon3', '0');
INSERT INTO `sys_plat` VALUES ('b59ad2aa233e4368828695057b5e5c2a', '13123', '2222', '2019-04-17 09:31:13', 'ff', '0');
INSERT INTO `sys_plat` VALUES ('c0894843d7654b5489b7b16fc2b38123', '李飞iot开发环境', 'http://192.168.100.136:7442/iot', '2019-06-28 10:36:29', 'fa fa-cog', '1');
INSERT INTO `sys_plat` VALUES ('ccbd41c8c75d44bab2f8f60fcf51a794', 'faicons', 'http://www.fontawesome.com.cn/faicons/', '2019-04-24 14:55:24', 'fa fa-braille', '1');
INSERT INTO `sys_plat` VALUES ('e43f2ca00867435d8bd079f5213f2b31', '锁匠平台（测试示例）', 'http://ztgmwl.com:9002/ttfp2/', '2019-02-25 22:50:36', 'fa fa-user', '0');
INSERT INTO `sys_plat` VALUES ('e8ec2872f3a54d1eaf37e25e27bcdb26', '支付平台', '/pay', '2019-01-10 23:55:57', 'fa fa-paypal', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(36) NOT NULL,
  `name` varchar(128) NOT NULL,
  `available` char(1) DEFAULT NULL COMMENT '是否可用,1：可用，0不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('&amp;', 'admin121', '1');
INSERT INTO `sys_role` VALUES ('&amp;&lt;&gt;&quot;&#39;', '&amp;&lt;&gt;&quot;&#39;', '1');
INSERT INTO `sys_role` VALUES ('&lt;&lt;&lt;', '1', '1');
INSERT INTO `sys_role` VALUES ('1', 'admin', '1');
INSERT INTO `sys_role` VALUES ('2', '2', '1');
INSERT INTO `sys_role` VALUES ('3', '工程实施人员', '1');
INSERT INTO `sys_role` VALUES ('33', 'admin-real', '1');
INSERT INTO `sys_role` VALUES ('34', '3', '1');
INSERT INTO `sys_role` VALUES ('4', '4', '1');
INSERT INTO `sys_role` VALUES ('9', '9', '0');
INSERT INTO `sys_role` VALUES ('normal_user', '普通用户', '1');
INSERT INTO `sys_role` VALUES ('zj-test', '本地测试-zj', '1');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_role_id` varchar(32) NOT NULL COMMENT '角色id',
  `sys_permission_id` varchar(64) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_53` (`sys_permission_id`),
  KEY `FK_Reference_54` (`sys_role_id`),
  CONSTRAINT `FK_Reference_53` FOREIGN KEY (`sys_permission_id`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `FK_Reference_54` FOREIGN KEY (`sys_role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5100 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('4409', 'zj-test', '1');
INSERT INTO `sys_role_permission` VALUES ('4410', 'zj-test', '5');
INSERT INTO `sys_role_permission` VALUES ('4411', 'zj-test', '100');
INSERT INTO `sys_role_permission` VALUES ('4412', 'zj-test', 'p2_test_2');
INSERT INTO `sys_role_permission` VALUES ('4413', 'zj-test', '102');
INSERT INTO `sys_role_permission` VALUES ('4415', 'zj-test', 'menu_auto');
INSERT INTO `sys_role_permission` VALUES ('4416', 'zj-test', 'menu_lockresidents');
INSERT INTO `sys_role_permission` VALUES ('4417', 'zj-test', 'menu_malldeliveryaddress');
INSERT INTO `sys_role_permission` VALUES ('4419', 'zj-test', 'z-1');
INSERT INTO `sys_role_permission` VALUES ('4420', 'zj-test', 'z-5');
INSERT INTO `sys_role_permission` VALUES ('4421', 'zj-test', 'z-100');
INSERT INTO `sys_role_permission` VALUES ('4422', 'zj-test', 'z-p2_test_2');
INSERT INTO `sys_role_permission` VALUES ('4423', 'zj-test', 'z-102');
INSERT INTO `sys_role_permission` VALUES ('4424', 'zj-test', 'z-20');
INSERT INTO `sys_role_permission` VALUES ('4426', 'zj-test', 'iot1');
INSERT INTO `sys_role_permission` VALUES ('4427', 'zj-test', 'iot5');
INSERT INTO `sys_role_permission` VALUES ('4428', 'zj-test', 'iot31');
INSERT INTO `sys_role_permission` VALUES ('4429', 'zj-test', 'iot27');
INSERT INTO `sys_role_permission` VALUES ('4430', 'zj-test', 'iot20');
INSERT INTO `sys_role_permission` VALUES ('4431', 'zj-test', 'iot21');
INSERT INTO `sys_role_permission` VALUES ('4432', 'zj-test', 'iot22');
INSERT INTO `sys_role_permission` VALUES ('4433', 'zj-test', 'iot28');
INSERT INTO `sys_role_permission` VALUES ('4434', 'zj-test', 'iot23');
INSERT INTO `sys_role_permission` VALUES ('4435', 'zj-test', 'iot24');
INSERT INTO `sys_role_permission` VALUES ('4436', 'zj-test', 'iot26');
INSERT INTO `sys_role_permission` VALUES ('4437', 'zj-test', 'zj-local-iot-m1');
INSERT INTO `sys_role_permission` VALUES ('4438', 'zj-test', 'zj-iot-local-chart');
INSERT INTO `sys_role_permission` VALUES ('4439', 'zj-test', 'iot2');
INSERT INTO `sys_role_permission` VALUES ('4440', 'zj-test', 'iot18');
INSERT INTO `sys_role_permission` VALUES ('4441', 'zj-test', 'iot30');
INSERT INTO `sys_role_permission` VALUES ('4442', 'zj-test', 'iot8');
INSERT INTO `sys_role_permission` VALUES ('4443', 'zj-test', 'iot9');
INSERT INTO `sys_role_permission` VALUES ('4444', 'zj-test', 'iot10');
INSERT INTO `sys_role_permission` VALUES ('4445', 'zj-test', 'iot7');
INSERT INTO `sys_role_permission` VALUES ('4446', 'zj-test', 'iot3');
INSERT INTO `sys_role_permission` VALUES ('4447', 'zj-test', 'iot11');
INSERT INTO `sys_role_permission` VALUES ('4448', 'zj-test', 'iot12');
INSERT INTO `sys_role_permission` VALUES ('4449', 'zj-test', 'iot4');
INSERT INTO `sys_role_permission` VALUES ('4450', 'zj-test', 'iot15');
INSERT INTO `sys_role_permission` VALUES ('4451', 'zj-test', 'iot14');
INSERT INTO `sys_role_permission` VALUES ('4452', 'zj-test', 'iot16');
INSERT INTO `sys_role_permission` VALUES ('4453', '3', '1');
INSERT INTO `sys_role_permission` VALUES ('4454', '3', '5');
INSERT INTO `sys_role_permission` VALUES ('4455', '3', '100');
INSERT INTO `sys_role_permission` VALUES ('4456', '3', 'p2_test_2');
INSERT INTO `sys_role_permission` VALUES ('4457', '3', '102');
INSERT INTO `sys_role_permission` VALUES ('4460', '3', 'iot1');
INSERT INTO `sys_role_permission` VALUES ('4461', '3', 'iot5');
INSERT INTO `sys_role_permission` VALUES ('4462', '3', 'iot31');
INSERT INTO `sys_role_permission` VALUES ('4463', '3', 'iot27');
INSERT INTO `sys_role_permission` VALUES ('4464', '3', 'iot20');
INSERT INTO `sys_role_permission` VALUES ('4465', '3', 'iot21');
INSERT INTO `sys_role_permission` VALUES ('4466', '3', 'iot22');
INSERT INTO `sys_role_permission` VALUES ('4467', '3', 'iot28');
INSERT INTO `sys_role_permission` VALUES ('4468', '3', 'iot23');
INSERT INTO `sys_role_permission` VALUES ('4469', '3', 'iot24');
INSERT INTO `sys_role_permission` VALUES ('4470', '3', 'iot26');
INSERT INTO `sys_role_permission` VALUES ('4471', '3', 'iot2');
INSERT INTO `sys_role_permission` VALUES ('4472', '3', 'iot18');
INSERT INTO `sys_role_permission` VALUES ('4473', '3', 'iot30');
INSERT INTO `sys_role_permission` VALUES ('4474', '3', 'iot8');
INSERT INTO `sys_role_permission` VALUES ('4475', '3', 'iot9');
INSERT INTO `sys_role_permission` VALUES ('4476', '3', 'iot10');
INSERT INTO `sys_role_permission` VALUES ('4477', '3', 'iot7');
INSERT INTO `sys_role_permission` VALUES ('4478', '3', 'iot4');
INSERT INTO `sys_role_permission` VALUES ('4479', '3', 'iot15');
INSERT INTO `sys_role_permission` VALUES ('4480', '3', 'iot14');
INSERT INTO `sys_role_permission` VALUES ('4481', '3', 'iot16');
INSERT INTO `sys_role_permission` VALUES ('4483', 'normal_user', 'iot4');
INSERT INTO `sys_role_permission` VALUES ('4484', 'normal_user', 'iot15');
INSERT INTO `sys_role_permission` VALUES ('4485', 'normal_user', 'iot14');
INSERT INTO `sys_role_permission` VALUES ('4497', '2', '1');
INSERT INTO `sys_role_permission` VALUES ('4498', '2', '5');
INSERT INTO `sys_role_permission` VALUES ('4499', '2', '100');
INSERT INTO `sys_role_permission` VALUES ('4500', '2', 'p2_test_2');
INSERT INTO `sys_role_permission` VALUES ('4501', '2', '102');
INSERT INTO `sys_role_permission` VALUES ('4808', '&amp;', '1');
INSERT INTO `sys_role_permission` VALUES ('4809', '&amp;', '5');
INSERT INTO `sys_role_permission` VALUES ('4810', '&amp;', '100');
INSERT INTO `sys_role_permission` VALUES ('4811', '&amp;', 'p2_test_2');
INSERT INTO `sys_role_permission` VALUES ('4812', '&amp;', '102');
INSERT INTO `sys_role_permission` VALUES ('4814', '&amp;', 'menu_auto');
INSERT INTO `sys_role_permission` VALUES ('4815', '&amp;', 'menu_lockresidents');
INSERT INTO `sys_role_permission` VALUES ('4816', '&amp;', 'menu_malldeliveryaddress');
INSERT INTO `sys_role_permission` VALUES ('4818', '&amp;', 'iot1');
INSERT INTO `sys_role_permission` VALUES ('4819', '&amp;', 'iot5');
INSERT INTO `sys_role_permission` VALUES ('4820', '&amp;', 'iot31');
INSERT INTO `sys_role_permission` VALUES ('4821', '&amp;', 'iot27');
INSERT INTO `sys_role_permission` VALUES ('4822', '&amp;', 'iot20');
INSERT INTO `sys_role_permission` VALUES ('4823', '&amp;', 'iot21');
INSERT INTO `sys_role_permission` VALUES ('4824', '&amp;', 'iot22');
INSERT INTO `sys_role_permission` VALUES ('4825', '&amp;', 'iot28');
INSERT INTO `sys_role_permission` VALUES ('4826', '&amp;', 'iot23');
INSERT INTO `sys_role_permission` VALUES ('4827', '&amp;', 'iot24');
INSERT INTO `sys_role_permission` VALUES ('4828', '&amp;', 'iot26');
INSERT INTO `sys_role_permission` VALUES ('4829', '&amp;', 'zj-local-iot-m1');
INSERT INTO `sys_role_permission` VALUES ('4830', '&amp;', 'zj-iot-local-chart');
INSERT INTO `sys_role_permission` VALUES ('4831', '&amp;', 'iot2');
INSERT INTO `sys_role_permission` VALUES ('4832', '&amp;', 'iot18');
INSERT INTO `sys_role_permission` VALUES ('4833', '&amp;', 'iot30');
INSERT INTO `sys_role_permission` VALUES ('4834', '&amp;', 'iot8');
INSERT INTO `sys_role_permission` VALUES ('4835', '&amp;', 'iot9');
INSERT INTO `sys_role_permission` VALUES ('4836', '&amp;', 'iot10');
INSERT INTO `sys_role_permission` VALUES ('4837', '&amp;', 'iot7');
INSERT INTO `sys_role_permission` VALUES ('4838', '&amp;', 'iot3');
INSERT INTO `sys_role_permission` VALUES ('4839', '&amp;', 'iot11');
INSERT INTO `sys_role_permission` VALUES ('4840', '&amp;', 'iot12');
INSERT INTO `sys_role_permission` VALUES ('4841', '&amp;', 'iot4');
INSERT INTO `sys_role_permission` VALUES ('4842', '&amp;', 'iot15');
INSERT INTO `sys_role_permission` VALUES ('4843', '&amp;', 'iot14');
INSERT INTO `sys_role_permission` VALUES ('4844', '&amp;', 'iot16');
INSERT INTO `sys_role_permission` VALUES ('4846', '33', '1');
INSERT INTO `sys_role_permission` VALUES ('4847', '33', '5');
INSERT INTO `sys_role_permission` VALUES ('4848', '33', '100');
INSERT INTO `sys_role_permission` VALUES ('4849', '33', 'p2_test_2');
INSERT INTO `sys_role_permission` VALUES ('4850', '33', '102');
INSERT INTO `sys_role_permission` VALUES ('4852', '33', 'menu_auto');
INSERT INTO `sys_role_permission` VALUES ('4853', '33', 'menu_lockresidents');
INSERT INTO `sys_role_permission` VALUES ('4854', '33', 'menu_malldeliveryaddress');
INSERT INTO `sys_role_permission` VALUES ('4856', '33', 'iot1');
INSERT INTO `sys_role_permission` VALUES ('4857', '33', 'iot5');
INSERT INTO `sys_role_permission` VALUES ('4858', '33', 'iot31');
INSERT INTO `sys_role_permission` VALUES ('4859', '33', 'iot27');
INSERT INTO `sys_role_permission` VALUES ('4860', '33', 'iot20');
INSERT INTO `sys_role_permission` VALUES ('4861', '33', 'iot21');
INSERT INTO `sys_role_permission` VALUES ('4862', '33', 'iot22');
INSERT INTO `sys_role_permission` VALUES ('4863', '33', 'iot28');
INSERT INTO `sys_role_permission` VALUES ('4864', '33', 'iot23');
INSERT INTO `sys_role_permission` VALUES ('4865', '33', 'iot24');
INSERT INTO `sys_role_permission` VALUES ('4866', '33', 'iot26');
INSERT INTO `sys_role_permission` VALUES ('4867', '33', 'zj-local-iot-m1');
INSERT INTO `sys_role_permission` VALUES ('4868', '33', 'zj-iot-local-chart');
INSERT INTO `sys_role_permission` VALUES ('4869', '33', 'iot2');
INSERT INTO `sys_role_permission` VALUES ('4870', '33', 'iot18');
INSERT INTO `sys_role_permission` VALUES ('4871', '33', 'iot30');
INSERT INTO `sys_role_permission` VALUES ('4872', '33', 'iot8');
INSERT INTO `sys_role_permission` VALUES ('4873', '33', 'iot9');
INSERT INTO `sys_role_permission` VALUES ('4874', '33', 'iot10');
INSERT INTO `sys_role_permission` VALUES ('4875', '33', 'iot7');
INSERT INTO `sys_role_permission` VALUES ('4876', '33', 'iot3');
INSERT INTO `sys_role_permission` VALUES ('4877', '33', 'iot11');
INSERT INTO `sys_role_permission` VALUES ('4878', '33', 'iot12');
INSERT INTO `sys_role_permission` VALUES ('4879', '33', 'iot4');
INSERT INTO `sys_role_permission` VALUES ('4880', '33', 'iot15');
INSERT INTO `sys_role_permission` VALUES ('4881', '33', 'iot14');
INSERT INTO `sys_role_permission` VALUES ('4882', '33', 'iot16');
INSERT INTO `sys_role_permission` VALUES ('5087', '1', 'z-p2_test_2');
INSERT INTO `sys_role_permission` VALUES ('5088', '1', 'tk_menu_mqttnode');
INSERT INTO `sys_role_permission` VALUES ('5089', '1', 'tk_menu_mqttrule');
INSERT INTO `sys_role_permission` VALUES ('5090', '1', 'tk_menu_rule');
INSERT INTO `sys_role_permission` VALUES ('5091', '1', 'tk_menu_ruleaction');
INSERT INTO `sys_role_permission` VALUES ('5092', '1', 'z-1');
INSERT INTO `sys_role_permission` VALUES ('5093', '1', 'z-5');
INSERT INTO `sys_role_permission` VALUES ('5094', '1', '13222');
INSERT INTO `sys_role_permission` VALUES ('5095', '1', 'z-100');
INSERT INTO `sys_role_permission` VALUES ('5096', '1', 'tk_menu_auto');
INSERT INTO `sys_role_permission` VALUES ('5097', '1', 'z-102');
INSERT INTO `sys_role_permission` VALUES ('5098', '1', 'z-20');
INSERT INTO `sys_role_permission` VALUES ('5099', '1', 'tk_menu_procompany');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(64) NOT NULL,
  `sys_role_id` varchar(64) DEFAULT NULL,
  `sys_user_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_55` (`sys_role_id`),
  KEY `FK_Reference_56` (`sys_user_id`),
  CONSTRAINT `FK_Reference_55` FOREIGN KEY (`sys_role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK_Reference_56` FOREIGN KEY (`sys_user_id`) REFERENCES `manager` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('0f7b0d791a73488694a41250234a719a', 'normal_user', 'edd5a822b6744d1d8ec8688e4f9769e8');
INSERT INTO `sys_user_role` VALUES ('1212d7a72ee5453fa6a945a0959ce4c7', '3', 'ab02cf6b107a4901a72f7cb7bf985b81');
INSERT INTO `sys_user_role` VALUES ('18fdc75df77d4f9485b046b2c30e6202', 'normal_user', '94335f848a03464aa2c5199f7fba835d');
INSERT INTO `sys_user_role` VALUES ('2987bcc5064b4062ab82c16d1c580e68', 'normal_user', '999554a5634048fbaaf2608b8362cdde');
INSERT INTO `sys_user_role` VALUES ('2c17ebf6fb8e432c814d57ccff5c45aa', 'normal_user', 'ac4d2f026e51499b9ccef471025047d3');
INSERT INTO `sys_user_role` VALUES ('2d3323fc5681421f916697d9d46c0f79', '3', 'a6c6b0a3c0c64b959f974091c3d6e412');
INSERT INTO `sys_user_role` VALUES ('2fbe8acfa8cb4ccfa158690a807d1ef1', 'normal_user', '5340d6457145449d868bf4909bbe8b0c');
INSERT INTO `sys_user_role` VALUES ('308347e12093431f998effd45491e9e1', '3', '817c34b2fa524034b713deb28a2e554c');
INSERT INTO `sys_user_role` VALUES ('328ef409557e4a0ba7bae9df175266aa', 'normal_user', 'd241476016e94f108a4dd73967587c18');
INSERT INTO `sys_user_role` VALUES ('466c60a7d76b4c61b759edd8a76ea226', 'normal_user', '37831e31048e48c495a461d094588bba');
INSERT INTO `sys_user_role` VALUES ('4c3209c20fd24800a38b6041282dbf33', 'normal_user', '1dc35a5a837d4574ab290e53181cb224');
INSERT INTO `sys_user_role` VALUES ('4fe7bfbef18646ffba1ea2309129826d', 'normal_user', '5aa695b170b14c399a6fc7413884f452');
INSERT INTO `sys_user_role` VALUES ('55332e0aad58480097a31f377289717a', 'normal_user', 'fe7a72df634549918554a6f2c47e356b');
INSERT INTO `sys_user_role` VALUES ('663daae2e950447685e31e566be32360', 'normal_user', '0f3b5b1db2364a46af996ddb78a06dc4');
INSERT INTO `sys_user_role` VALUES ('6792962ffd954d3cae48a0dd021bca14', '3', '7751d24f117b4cf3ba9f4158475fea79');
INSERT INTO `sys_user_role` VALUES ('779c720714c846b8aa9b60d3a8dbdbd5', 'normal_user', '5b8a8399993349a1a9deeda4b41b26db');
INSERT INTO `sys_user_role` VALUES ('89ec7174dc9148a2b8f0fb5e678f183b', 'normal_user', '5b994c9add924e6b9d2aa863b5b7a897');
INSERT INTO `sys_user_role` VALUES ('8b3c2fba1569400cab70a168dfb362e5', '1', 'fe7a72df634549918554a6f2c47e356b');
INSERT INTO `sys_user_role` VALUES ('8df601fc262046e1b2c4882176167a1a', 'normal_user', '26c8a6208f5e4151822704e3c7fad5a6');
INSERT INTO `sys_user_role` VALUES ('938ee2d0c9884a4c98bd911712e4b71a', 'normal_user', 'bb6aac433e574501ac8824cb6655e19b');
INSERT INTO `sys_user_role` VALUES ('95f953570db2485ba1f32cfeb9a55721', 'zj-test', 'c96e8a6ad6664fbc94fe870ec9ec153d');
INSERT INTO `sys_user_role` VALUES ('966d7aed58bf461c98529fe7c8e4bf13', 'normal_user', '460388c9449c4e5087fa662b8be6b73d');
INSERT INTO `sys_user_role` VALUES ('981893d08fcb4239b4f22e1613856b1f', 'normal_user', '2f58ed37dcaa4dbabcf75110353c4c84');
INSERT INTO `sys_user_role` VALUES ('9a013a722c4c489c8063ad0d03ecd2c8', 'normal_user', '50c060d828474da589b020fa5c4a0f48');
INSERT INTO `sys_user_role` VALUES ('9ac1c928766f47219151f541f73cae2d', 'zj-test', 'e5f077d44edf4cababc30890393edf45');
INSERT INTO `sys_user_role` VALUES ('9de636ddbc654f29ac15c36ad51c697d', 'normal_user', '72a7d271c35f4bdd965b36f9db1d5ba5');
INSERT INTO `sys_user_role` VALUES ('9f262ce4e4ea410799411614cc5b4471', 'normal_user', '6aaa4f31a17c49c5b36719be9bae0791');
INSERT INTO `sys_user_role` VALUES ('a567c0679b9d445a968472f8aad28e2a', 'normal_user', '69b37e4531444b69bfd6954072175128');
INSERT INTO `sys_user_role` VALUES ('b200c459f0d2451082bc19cc139a324b', '1', '1');
INSERT INTO `sys_user_role` VALUES ('b5e2cf2e9a4b403585b7ff8402387e97', 'normal_user', '2d11942c1c1c435ea8370430682f80f2');
INSERT INTO `sys_user_role` VALUES ('b78b21d9c2254de18e3f6ddc3d860b8f', 'normal_user', '51a2662fdc7f48d1b5c1ec4ca6f6a386');
INSERT INTO `sys_user_role` VALUES ('b99a817d6e5d4b6d8b40e719b4545122', 'normal_user', '560bf3666d8d49fd864568af3787940e');
INSERT INTO `sys_user_role` VALUES ('ce0b2825ff074673976a302bf742f661', 'normal_user', 'aba023eb2cdc41de9f4adf9b13584153');
INSERT INTO `sys_user_role` VALUES ('deea9b04bb1941e38966ca5208305e4d', '3', '22673aa2f7944c7f8512536dfe369e46');
INSERT INTO `sys_user_role` VALUES ('df20f6063e8e4dc980ab85cb369ce9d7', '1', 'e5f077d44edf4cababc30890393edf45');
INSERT INTO `sys_user_role` VALUES ('e1587d1280464538b6fa544e0723ccd5', '33', '0c58bd35ce8f477d8ff3904078d6c384');
INSERT INTO `sys_user_role` VALUES ('e70fe670b8004dabb894eab6ece15217', 'normal_user', '68e3d96c92cf42b684b758f59aefd82b');
INSERT INTO `sys_user_role` VALUES ('ebe6fe1fba764fe1be3a9a7b5734e708', 'normal_user', '36c4764f9ef04e8c8e68ebe58c8d298b');
INSERT INTO `sys_user_role` VALUES ('f93bf9b848c3433e8145cf41cd1f5635', 'normal_user', '06f39f5a0f3a488593508ac0e375fb27');
