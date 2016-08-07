/*
 Navicat MySQL Data Transfer

 Source Server         : local
 Source Server Version : 50616
 Source Host           : localhost
 Source Database       : springwind

 Target Server Version : 50616
 File Encoding         : utf-8

 Date: 08/07/2016 22:14:20 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `role`
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
--  Records of `role`
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES ('1', '管理员', '1', '系统管理员'), ('2', '普通会员', '2', '普通会员'), ('424917074352013312', '测试1', '1', '测试1');
COMMIT;

-- ----------------------------
--  Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `rid` bigint(20) NOT NULL COMMENT '角色ID',
  `pid` bigint(20) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色权限表';

-- ----------------------------
--  Records of `role_permission`
-- ----------------------------
BEGIN;
INSERT INTO `role_permission` VALUES ('100', '1', '100'), ('110', '1', '110'), ('111', '1', '111'), ('112', '1', '112'), ('113', '1', '113'), ('120', '1', '120'), ('121', '1', '121'), ('122', '1', '122'), ('123', '1', '123'), ('200', '1', '200'), ('210', '1', '210'), ('211', '1', '211'), ('212', '1', '212'), ('213', '1', '213'), ('220', '1', '220'), ('221', '1', '221'), ('300', '1', '300'), ('310', '1', '310'), ('311', '1', '311'), ('312', '1', '312'), ('313', '1', '313');
COMMIT;

-- ----------------------------
--  Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` bigint(20) unsigned DEFAULT NULL COMMENT '用户ID',
  `content` varchar(600) NOT NULL DEFAULT '' COMMENT '日志内容',
  `operation` varchar(250) DEFAULT NULL COMMENT '用户操作',
  `crTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5743385924710965249 DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
--  Records of `sys_log`
-- ----------------------------
BEGIN;
INSERT INTO `sys_log` VALUES ('450926100940324864', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:ctoken=de1cd3d4e010438d81bbb13aabb90450&password=123&captcha=5keg&loginName=admin&,[IP]:192.168.11.102', '登录', '2016-07-23 16:35:02'), ('451013944245485568', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:ctoken=9a7228b599e04b4c9f10b3424e198753&password=123&captcha=km93&loginName=admin&,[IP]:10.10.10.3', '登录', '2016-07-23 22:24:06'), ('451345489556668416', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:ctoken=8d84f30e79b04e4b9f752db816a07ab1&password=123&captcha=8d5m&loginName=admin&,[IP]:10.10.10.3', '登录', '2016-07-24 20:21:32'), ('451345990666944512', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:ctoken=5581c5ff31d143e282241ea419dbf471&password=123&captcha=72mb&loginName=admin&,[IP]:10.10.10.3', '登录', '2016-07-24 20:23:32'), ('451392937389981696', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:ctoken=b451ed6b35e94ae8b3ea9a099ee6e18f&password=123&captcha=xoae&loginName=admin&,[IP]:10.10.10.3', '登录', '2016-07-24 23:30:05'), ('451393115413020672', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:ctoken=aa87004e63394897afb2deb7040571a4&password=123&captcha=s6gw&loginName=admin&,[IP]:10.10.10.3', '登录', '2016-07-24 23:30:47'), ('453535770586644480', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:ctoken=5a1b64c23b8b472298ebbcc84c0de402&password=123&captcha=npg9&loginName=admin&,[IP]:10.10.10.4', '登录', '2016-07-30 21:24:56'), ('453537253906448384', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:ctoken=bdaaecf5b3e84108845c7750d8dfced9&password=123&captcha=ae7p&loginName=admin&,[IP]:10.10.10.4', '登录', '2016-07-30 21:30:49'), ('453815909144854528', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:ctoken=82fe5df284c74e459f3a8cdc9bdb30de&password=123&captcha=7g8b&loginName=admin&,[IP]:10.10.10.6', '登录', '2016-07-31 15:58:06'), ('5462040896532488192', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:ctoken=53d3bc921c9a4c5f8316fb7263f3696c&password=123&captcha=m7ab&loginName=admin&,[IP]:10.10.10.4', '登录', '2016-08-06 22:55:32'), ('5719266230190227456', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:ctoken=7482c17b97d04ae0a04689a8ce5a1de3&password=123&captcha=n6am&loginName=admin&,[IP]:192.168.11.102', '登录', '2016-08-07 15:33:42'), ('5719668007200899072', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:ctoken=352e3aafaa9448dbafe4eb3059b5dff3&password=123&captcha=gpe8&loginName=admin&,[IP]:192.168.11.102', '登录', '2016-08-07 15:35:15'), ('5734443429138550784', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:ctoken=0e6f3c3b8b62427cac6037fc50c85ec9&password=123&captcha=23k8&loginName=admin&,[IP]:192.168.11.102', '登录', '2016-08-07 16:32:35'), ('5743385924710965248', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:ctoken=659e2855ce8c4315978ce96b69bee7d7&password=123&captcha=46pk&loginName=admin&,[IP]:192.168.11.102', '登录', '2016-08-07 17:07:17');
COMMIT;

-- ----------------------------
--  Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '上级ID',
  `text` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '标题',
  `type` smallint(6) NOT NULL DEFAULT '0' COMMENT '类型 0、菜单 1、功能',
  `state` smallint(6) NOT NULL DEFAULT '0' COMMENT '状态 0、正常 1、禁用',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `mid` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单ID',
  `closeable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '菜单是否可关闭 0、否 1、是',
  `href` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `code` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '权限编码',
  `description` varchar(80) COLLATE utf8_bin NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='权限表';

-- ----------------------------
--  Records of `sys_permission`
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES ('200', '0', '系统管理', '0', '0', '1', 'sys', '1', 'user-list', '2000', '系统管理'), ('210', '200', '功能权限', '0', '0', '0', 'sys', '1', '#', '2000', '功能权限'), ('220', '200', '监控日志', '0', '0', '1', 'sys', '1', '#', '2000', '系统监控'), ('221', '220', '操作日志', '0', '0', '0', 'log-list', '1', '/log/list.html', '2000', '操作日志'), ('113', '110', '发送邮件测试', '0', '0', '2', 'mail-send', '1', '/mail/send.html', '1001', '发送邮件测试'), ('313', '310', '雷达图', '0', '0', '0', 'echarts-radar', '1', '/analysis/echarts/radar.html', '3000', '雷达图'), ('211', '210', '用户管理', '0', '0', '0', 'user-list', '1', '/user/list.html', '2001', '用户管理'), ('212', '210', '权限管理', '0', '0', '1', 'permission-list', '1', '/permission/list.html', '2002', '角色管理'), ('213', '210', '菜单管理', '0', '0', '2', 'permission-menu', '1', '/permission/menu.html', '2003', '菜单管理'), ('310', '300', 'echarts 图表', '0', '0', '0', 'echarts', '1', '#', '3000', 'echarts 图表'), ('311', '310', '折线图', '0', '0', '0', 'echarts-line', '1', '/analysis/echarts/line.html', '3000', '折线图'), ('312', '310', '散列图', '0', '0', '0', 'echarts-scatter', '1', '/analysis/echarts/scatter.html', '3000', '散列图'), ('110', '100', '支持', '0', '0', '0', 'support', '1', '#', '1000', '支持'), ('300', '0', '统计分析', '0', '0', '2', 'analysis', '1', 'echarts-radar', '3000', '统计分析'), ('100', '0', '功能管理', '0', '0', '0', 'func', '1', 'donate', '1000', '功能管理'), ('111', '110', '捐赠 - 作者', '0', '0', '0', 'donate', '0', '/donate.html', '1000', '捐赠作者'), ('112', '110', '捐赠 - 列表', '0', '0', '1', 'donate-list', '1', '/donate/list.html', '1000', '捐赠 - 列表'), ('222', '220', '监控 Druid', '0', '0', '1', 'druid-index', '1', '/druid/index.html', '2000', '监控 Druid');
COMMIT;

-- ----------------------------
--  Table structure for `user`
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
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'admin', 'b9140469ac5d2a70d86a583e9095ad6f', 'abc@a.com', '1', '1', '2016-04-17 18:52:26', '2016-04-17 18:52:26'), ('2', 'test', 'd227f9edb541169068b11221869a014a', 'abc1@a.com', '1', '1', '2016-04-14 18:22:58', '2016-04-14 18:22:58'), ('415112286823251968', 'abc', 'c4c99e1a5bd140d138ad0863bf59acb3', 'abc2@a.com', '1', '0', '2016-04-15 20:43:43', '2016-04-15 20:43:43'), ('415112325381488640', 'def', '94e5398201cd9452b1e667044debcd10', 'abc3@a.com', '0', '1', '2016-04-15 20:43:53', '2016-04-15 20:43:53');
COMMIT;

-- ----------------------------
--  Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `rid` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
--  Records of `user_role`
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES ('1', '1', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
