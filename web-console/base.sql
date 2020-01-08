/*
Navicat MySQL Data Transfer

Source Server         : 192.168.100.134
Source Server Version : 50626
Source Host           : 192.168.100.134:3306
Source Database       : base

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2019-01-21 11:44:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `ID` varchar(36) NOT NULL,
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
  `icon` varchar(64) DEFAULT NULL COMMENT '头像md5',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员';

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('1', 'admin', 'efd9d1b8bfb00e8e3647990f7d74d1d8', '13915925326', null, '管理员1', null, null, null, null, '2018-03-29 11:06:45', '0', 'd25d7ad7fd88446f3cab3bd7bf502d0d');
INSERT INTO `manager` VALUES ('ef90bbb019484a69a71efe837972a122', '13913834420', '0a4a536cfa0f0e49adf641b38addae18', '13913834420', null, '333', null, null, null, null, '2019-01-21 11:42:06', '0', 'b3dbfcac09575e3f8f3129458e4c1f33');

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
INSERT INTO `sys_file` VALUES ('839b4ab54f474fa38455454c8c80043b', '2019-01-21 11:41:41', 'faviconqq.png', 'b95ab097-6c62-4e25-901e-3d3f99ad6777.png', 'F:\\IMG\\upload\\apk/201901/b95ab097-6c62-4e25-901e-3d3f99ad6777.png', 'apk/201901/b95ab097-6c62-4e25-901e-3d3f99ad6777.png', '/FileSvr/downFile.action?m5=dc8fd2fbf4190207937db3767a9cdeb8', '2691', '2019-01-21 11:41:41', 'dc8fd2fbf4190207937db3767a9cdeb8', '1');
INSERT INTO `sys_file` VALUES ('8e229878495544949192b63f81ebb590', '2019-01-21 11:23:51', 'cropped-p-logo-100x100.png', 'd53bb21b-f163-4c98-8028-2f67a2b58cd1.png', 'F:\\IMG\\upload\\apk/201901/d53bb21b-f163-4c98-8028-2f67a2b58cd1.png', 'apk/201901/d53bb21b-f163-4c98-8028-2f67a2b58cd1.png', '/FileSvr/downFile.action?m5=d25d7ad7fd88446f3cab3bd7bf502d0d', '5737', '2019-01-21 11:23:51', 'd25d7ad7fd88446f3cab3bd7bf502d0d', '2');
INSERT INTO `sys_file` VALUES ('a37d441dc2b84a2a81c05375dc7086af', '2019-01-21 11:31:04', 'ff.png', 'c6dedf8b-e42a-45d3-85ff-9502dfc1707a.png', 'F:\\IMG\\upload\\apk/201901/c6dedf8b-e42a-45d3-85ff-9502dfc1707a.png', 'apk/201901/c6dedf8b-e42a-45d3-85ff-9502dfc1707a.png', '/FileSvr/downFile.action?m5=b3dbfcac09575e3f8f3129458e4c1f33', '39158', '2019-01-21 11:31:05', 'b3dbfcac09575e3f8f3129458e4c1f33', '2');
INSERT INTO `sys_file` VALUES ('a709431563384e4c83fe6f16963fadc7', '2019-01-21 11:39:29', 'head (3).png', 'd0043ef2-15b0-46ea-867d-eadd5429f377.png', 'F:\\IMG\\upload\\apk/201901/d0043ef2-15b0-46ea-867d-eadd5429f377.png', 'apk/201901/d0043ef2-15b0-46ea-867d-eadd5429f377.png', '/FileSvr/downFile.action?m5=1afece67766f660e7a500a02a573065b', '2738', '2019-01-21 11:39:30', '1afece67766f660e7a500a02a573065b', '1');
INSERT INTO `sys_file` VALUES ('f1613754f8f04348ae163a8ab5538e1d', '2019-01-21 11:23:46', 'cropped-尚磊头像-1-32x32.png', 'a1e69df6-b5c9-4ca9-af8f-f2f87abcb632.png', 'F:\\IMG\\upload\\apk/201901/a1e69df6-b5c9-4ca9-af8f-f2f87abcb632.png', 'apk/201901/a1e69df6-b5c9-4ca9-af8f-f2f87abcb632.png', '/FileSvr/downFile.action?m5=340f54b20b7c40770359173cb809bff6', '2247', '2019-01-21 11:23:46', '340f54b20b7c40770359173cb809bff6', '1');
INSERT INTO `sys_file` VALUES ('f7887ffd90b04ebc90d7b396203f4c36', '2019-01-21 10:49:18', 'd304382c-7aa6-4168-8731-ddadebbfc500.jpg', 'e6832b25-4c33-46c8-80a1-4e3e61e4b6d3.jpg', 'F:\\IMG\\upload\\apk/201901/e6832b25-4c33-46c8-80a1-4e3e61e4b6d3.jpg', 'apk/201901/e6832b25-4c33-46c8-80a1-4e3e61e4b6d3.jpg', '/FileSvr/downFile.action?m5=fff1e3459ae591983138ead774b7f0cb', '17667', '2019-01-21 10:49:18', 'fff1e3459ae591983138ead774b7f0cb', '4');

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
  `parentid` varchar(20) DEFAULT NULL COMMENT '父结点id',
  `parentids` varchar(128) DEFAULT NULL COMMENT '父结点id列表串',
  `sortstring` varchar(128) DEFAULT NULL COMMENT '排序号',
  `available` char(1) DEFAULT '1' COMMENT '是否可用,1：可用，0不可用',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标或者icon class',
  `plat_id` varchar(64) DEFAULT NULL COMMENT '所属平台id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限-菜单-按钮';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '系统设置', '1', '123123', 'sys', '0', null, '1', '1', 'fa fa-user', '1');
INSERT INTO `sys_permission` VALUES ('100', '系统权限管理', '2', '/privilege/permission/index', 'pri:view', '1', null, '1', '1', 'xuanzecj.png', '1');
INSERT INTO `sys_permission` VALUES ('102', '角色管理', '2', '/privilege/role/index', 'role:view', '1', null, '2', '1', 'xuanzecj.png', '1');
INSERT INTO `sys_permission` VALUES ('20', '自动生成管理', '2', '/generator/index', 'generator：view', '1', null, '5', '1', '123457', '1');
INSERT INTO `sys_permission` VALUES ('5', '用户管理', '2', '/manager/user/manager', 'user:view', '1', null, '1', '1', 'xuanzecj.png', '1');
INSERT INTO `sys_permission` VALUES ('menu_auto', '自动生成菜单', '1', '/', 'menu_auto:view', '0', null, '100', '1', null, '1');
INSERT INTO `sys_permission` VALUES ('menu_lockresidents', '租户信息表管理', '2', '/manager/lockresidents/manager', 'lockresidents:view', 'menu_auto', null, '1', '1', null, '1');
INSERT INTO `sys_permission` VALUES ('menu_sysplat', '平台信息表管理', '2', '/manager/sysplat/manager', 'sysplat:view', 'menu_auto', null, '1', '1', null, '1');
INSERT INTO `sys_permission` (`id`, `name`, `type`, `url`, `percode`, `parentid`, `parentids`, `sortstring`, `available`, `icon`, `plat_id`) VALUES ('sys_log', '系统日志', '2', '/manager/sysOperLog/manager', 'sys_log', '1', NULL, '9', '1', NULL, NULL);

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台信息表';

-- ----------------------------
-- Records of sys_plat
-- ----------------------------
INSERT INTO `sys_plat` VALUES ('1', '权限管理平台', 'http://127.0.0.1:8882/d1', '2019-01-11 14:50:21', 'fa fa-cog');
INSERT INTO `sys_plat` VALUES ('6ecac655c9054a5191d21318dc228c24', '测试平台2', 'http://127.0.0.1:8883/d2', '2019-01-10 15:55:57', 'fa fa-user');
INSERT INTO `sys_plat` VALUES ('e8ec2872f3a54d1eaf37e25e27bcdb26', '支付平台', '/pay', '2019-01-10 15:55:57', 'fa fa-paypal');

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
INSERT INTO `sys_role` VALUES ('1', 'admin', '1');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_role_id` varchar(32) NOT NULL COMMENT '角色id',
  `sys_permission_id` varchar(32) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_53` (`sys_permission_id`),
  KEY `FK_Reference_54` (`sys_role_id`),
  CONSTRAINT `FK_Reference_53` FOREIGN KEY (`sys_permission_id`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `FK_Reference_54` FOREIGN KEY (`sys_role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=505 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('497', '1', '1');
INSERT INTO `sys_role_permission` VALUES ('498', '1', '5');
INSERT INTO `sys_role_permission` VALUES ('499', '1', '100');
INSERT INTO `sys_role_permission` VALUES ('500', '1', '102');
INSERT INTO `sys_role_permission` VALUES ('501', '1', '20');
INSERT INTO `sys_role_permission` VALUES ('502', '1', 'menu_auto');
INSERT INTO `sys_role_permission` VALUES ('503', '1', 'menu_sysplat');
INSERT INTO `sys_role_permission` VALUES ('504', '1', 'menu_lockresidents');
INSERT INTO `sys_role_permission` (`id`, `sys_role_id`, `sys_permission_id`) VALUES ('528', '1', 'sys_log');

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
INSERT INTO `sys_user_role` VALUES ('78432fc1049a4f2b888ffe45ea7ecac7', '1', '1');

alter table manager add column 
`oauthUserId` varchar(128) DEFAULT NULL COMMENT '统一认证系统的用户id';


alter table sys_permission modify COLUMN
`parentid` varchar(64) DEFAULT NULL COMMENT '父结点id';