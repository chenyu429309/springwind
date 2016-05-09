/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50615
Source Host           : localhost:3306
Source Database       : springwind

Target Server Type    : MYSQL
Target Server Version : 50615
File Encoding         : 65001

Date: 2016-05-09 18:05:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '上级ID',
  `title` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '标题',
  `type` smallint(6) NOT NULL DEFAULT '0' COMMENT '类型 0、菜单 1、功能',
  `state` smallint(6) NOT NULL DEFAULT '0' COMMENT '状态 0、正常 1、禁用',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `url` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `permCode` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '权限编码',
  `icon` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '图标',
  `description` varchar(80) COLLATE utf8_bin NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('2', '0', '权限管理', '0', '0', '2', '/perm/', '2000', 'fa fa-eye', '权限管理');
INSERT INTO `permission` VALUES ('1', '0', '功能管理', '0', '0', '1', '/sys/', '1000', 'fa fa-magic', '功能管理');
INSERT INTO `permission` VALUES ('3', '0', '系统监控', '0', '0', '3', '/monitor/', '3000', 'fa fa-bar-chart-o', '系统监控');
INSERT INTO `permission` VALUES ('4', '0', '操作日志', '0', '0', '4', '/log/', '4000', 'fa fa-bug', '操作日志');
INSERT INTO `permission` VALUES ('11', '1', '发送邮件测试', '0', '0', '1', '/sys/mail/send.html', '1001', null, '发送邮件测试');
INSERT INTO `permission` VALUES ('12', '1', '暂无2', '0', '0', '2', 'none2.html', '1002', null, '暂无2');
INSERT INTO `permission` VALUES ('21', '2', '用户管理', '0', '0', '1', '/perm/user/list.html', '2001', null, '用户管理');
INSERT INTO `permission` VALUES ('22', '2', '角色管理', '0', '0', '2', '/perm/role/list.html', '2002', null, '角色管理');
INSERT INTO `permission` VALUES ('23', '2', '权限管理', '0', '0', '3', '/perm/permission/list.html', '2003', null, '权限管理');
INSERT INTO `permission` VALUES ('31', '3', '警告列表', '0', '0', '1', 'abc.html', '3001', null, '警告列表');
INSERT INTO `permission` VALUES ('32', '3', '实时监控', '0', '0', '2', '/monitor/realTime.html', '3002', null, '实时监控');
INSERT INTO `permission` VALUES ('41', '4', '日志列表', '0', '0', '1', 'sad.htm', '4001', null, '日志列表');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(30) NOT NULL COMMENT '角色',
  `sort` smallint(6) NOT NULL DEFAULT '0' COMMENT '排序',
  `description` varchar(60) NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', '1', '系统管理员');
INSERT INTO `role` VALUES ('2', '普通会员', '2', '普通会员');

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `rid` bigint(20) NOT NULL COMMENT '角色ID',
  `pid` bigint(20) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色权限表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1', '1');
INSERT INTO `role_permission` VALUES ('2', '1', '2');
INSERT INTO `role_permission` VALUES ('3', '1', '3');
INSERT INTO `role_permission` VALUES ('4', '1', '4');
INSERT INTO `role_permission` VALUES ('5', '1', '11');
INSERT INTO `role_permission` VALUES ('6', '1', '12');
INSERT INTO `role_permission` VALUES ('7', '1', '21');
INSERT INTO `role_permission` VALUES ('8', '1', '22');
INSERT INTO `role_permission` VALUES ('9', '1', '23');
INSERT INTO `role_permission` VALUES ('10', '1', '31');
INSERT INTO `role_permission` VALUES ('11', '1', '32');
INSERT INTO `role_permission` VALUES ('12', '1', '41');
INSERT INTO `role_permission` VALUES ('13', '2', '2');
INSERT INTO `role_permission` VALUES ('14', '2', '21');
INSERT INTO `role_permission` VALUES ('15', '2', '3');
INSERT INTO `role_permission` VALUES ('16', '2', '31');
INSERT INTO `role_permission` VALUES ('17', '2', '32');

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` bigint(20) unsigned DEFAULT NULL COMMENT '用户ID',
  `content` varchar(600) NOT NULL DEFAULT '' COMMENT '日志内容',
  `operation` varchar(250) DEFAULT NULL COMMENT '用户操作',
  `crtime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=423769215225298945 DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `loginName` varchar(30) NOT NULL COMMENT '登录名称',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `email` varchar(60) DEFAULT NULL COMMENT '邮箱',
  `type` smallint(6) NOT NULL DEFAULT '0' COMMENT '0、普通用户 1、管理员',
  `status` smallint(6) NOT NULL DEFAULT '1' COMMENT '0、禁用 1、正常',
  `crTime` datetime NOT NULL COMMENT '创建时间',
  `lastTime` datetime NOT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'b9140469ac5d2a70d86a583e9095ad6f', null, '0', '1', '2016-04-17 18:52:26', '2016-04-17 18:52:26');
INSERT INTO `user` VALUES ('2', 'test', 'd227f9edb541169068b11221869a014a', null, '0', '1', '2016-04-14 18:22:58', '2016-04-14 18:22:58');
INSERT INTO `user` VALUES ('415112286823251968', 'abc', 'c4c99e1a5bd140d138ad0863bf59acb3', null, '0', '1', '2016-04-15 20:43:43', '2016-04-15 20:43:43');
INSERT INTO `user` VALUES ('415112325381488640', 'def', '94e5398201cd9452b1e667044debcd10', null, '0', '1', '2016-04-15 20:43:53', '2016-04-15 20:43:53');
INSERT INTO `user` VALUES ('423730216519598080', 'asddsa', '539228ca62df02143c88137ca0dc5c13', null, '0', '1', '2016-05-09 15:28:18', '2016-05-09 15:28:18');
INSERT INTO `user` VALUES ('423734169042944000', 'sdddd', '9245c4eafd3aa6884fca10d8b60c057c', null, '0', '1', '2016-05-09 15:44:00', '2016-05-09 15:44:00');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `rid` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '2');
