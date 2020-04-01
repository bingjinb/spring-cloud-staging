/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : bugod

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2020-04-01 16:59:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '发布者',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `sort` smallint(6) DEFAULT NULL COMMENT '顺序',
  `is_public` tinyint(4) DEFAULT '1' COMMENT '是否公开:1|是, -1|否',
  `recommend` tinyint(4) DEFAULT '-1' COMMENT '是否推荐:1|是, -1|否',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '删除标识（0|未删除  1|已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='文章';

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '1', '1', '1', null, '1', '-1', '2020-03-12 14:17:16', null, null, null, '-1');
INSERT INTO `article` VALUES ('2', '2', '2', '2', null, '1', '-1', '2020-03-12 14:17:25', null, null, null, '-1');
INSERT INTO `article` VALUES ('3', '3', '3', '3', null, '1', '-1', '2020-03-12 14:17:30', null, null, null, '-1');
INSERT INTO `article` VALUES ('4', '4', '4', '4', null, '1', '-1', '2020-03-16 12:26:20', 'root', null, null, '0');
INSERT INTO `article` VALUES ('5', '7', '7', '7', null, '1', '-1', '2020-03-19 15:16:14', 'root', null, null, '0');
INSERT INTO `article` VALUES ('6', '8', '8', '8', null, '1', '-1', '2020-03-21 16:55:44', 'root', null, null, '0');
INSERT INTO `article` VALUES ('7', '9', '9', '9', null, '1', '-1', '2020-03-21 17:26:28', 'root', null, null, '0');
INSERT INTO `article` VALUES ('8', '9', '9', '9', null, '1', '-1', '2020-03-21 17:27:15', 'root', null, null, '0');
INSERT INTO `article` VALUES ('9', '11', '11', '11', null, '1', '-1', '2020-03-31 16:52:36', 'root', null, null, '0');
INSERT INTO `article` VALUES ('10', '11', '11', '11', null, '1', '-1', '2020-04-01 11:19:21', 'root', null, null, '0');
INSERT INTO `article` VALUES ('11', '12', '12', '12', null, '1', '-1', '2020-04-01 11:33:19', 'root', null, null, '0');
INSERT INTO `article` VALUES ('12', '13', '13', '13', null, '1', '-1', '2020-04-01 11:35:36', 'root', null, null, '0');
INSERT INTO `article` VALUES ('13', '14', '14', '14', null, '1', '-1', '2020-04-01 11:41:06', 'root', null, null, '0');
INSERT INTO `article` VALUES ('14', '15', '15', '15', null, '1', '-1', '2020-04-01 11:41:41', 'root', null, null, '0');
INSERT INTO `article` VALUES ('15', '21', '21', '21', null, '1', '-1', '2020-04-01 14:45:46', 'root', null, null, '0');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` int(11) DEFAULT NULL COMMENT '父部门id',
  `ancestors` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) COLLATE utf8mb4_bin DEFAULT '' COMMENT '部门名称',
  `order_num` smallint(6) DEFAULT NULL COMMENT '显示顺序',
  `leader` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '负责人',
  `phone` varchar(15) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(4) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新者',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '删除标识（0|未删除  1|已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('100', '0', '0', 'crown科技', '0', 'crown', '15888888888', 'crown@qq.com', '0', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_dept` VALUES ('101', '100', '0,100', '深圳总公司', '1', 'crown', '15888888888', 'crown@qq.com', '0', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_dept` VALUES ('102', '100', '0,100', '长沙分公司', '2', 'crown', '15888888888', 'crown@qq.com', '0', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_dept` VALUES ('103', '101', '0,100,101', '研发部门', '1', 'crown', '15888888888', 'crown@qq.com', '0', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_dept` VALUES ('104', '101', '0,100,101', '市场部门', '2', 'crown', '15888888888', 'crown@qq.com', '0', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_dept` VALUES ('105', '101', '0,100,101', '测试部门', '3', 'crown', '15888888888', 'crown@qq.com', '0', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_dept` VALUES ('106', '101', '0,100,101', '财务部门', '4', 'crown', '15888888888', 'crown@qq.com', '0', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_dept` VALUES ('107', '101', '0,100,101', '运维部门', '5', 'crown', '15888888888', 'crown@qq.com', '0', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_dept` VALUES ('108', '102', '0,100,102', '市场部门', '1', 'crown', '15888888888', 'crown@qq.com', '0', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_dept` VALUES ('109', '102', '0,100,102', '财务部门', '2', 'crown', '15888888888', 'crown@qq.com', '0', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级数据字典',
  `name` varchar(100) DEFAULT NULL COMMENT '字典名称',
  `code` varchar(200) DEFAULT NULL COMMENT '字典编码',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述',
  `sort` int(5) DEFAULT NULL COMMENT 'sort',
  `enable` tinyint(4) DEFAULT '0' COMMENT '是否启用(0否 1:是)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人名称',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '删除标识（0|未删除  1|已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='数据字典';

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '菜单名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) COLLATE utf8mb4_bin DEFAULT '#' COMMENT '请求地址',
  `target` varchar(20) COLLATE utf8mb4_bin DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `menu_type` tinyint(4) DEFAULT NULL COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` tinyint(4) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) COLLATE utf8mb4_bin DEFAULT '#' COMMENT '菜单图标',
  `remark` varchar(500) COLLATE utf8mb4_bin DEFAULT '' COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新者',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '删除标识（0|未删除  1|已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1063 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', '1', '#', '', '0', '0', '', 'fa fa-gear', '系统管理目录', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('2', '系统监控', '0', '2', '#', '', '0', '0', '', 'fa fa-video-camera', '系统监控目录', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('3', '系统工具', '0', '3', '#', '', '0', '0', '', 'fa fa-bars', '系统工具目录', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('100', '用户管理', '1', '1', '/system/user', '', '0', '0', 'system:user:view', '#', '用户管理菜单', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('101', '角色管理', '1', '2', '/system/role', '', '0', '0', 'system:role:view', '#', '角色管理菜单', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('102', '菜单管理', '1', '3', '/system/menu', '', '0', '0', 'system:menu:view', '#', '菜单管理菜单', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('103', '部门管理', '1', '4', '/system/dept', '', '0', '0', 'system:dept:view', '#', '部门管理菜单', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('104', '岗位管理', '1', '5', '/system/post', '', '0', '0', 'system:post:view', '#', '岗位管理菜单', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('105', '字典管理', '1', '6', '/system/dict', '', '0', '0', 'system:dict:view', '#', '字典管理菜单', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('106', '参数设置', '1', '7', '/system/config', '', '0', '0', 'system:config:view', '#', '参数设置菜单', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('107', '通知公告', '1', '8', '/system/notice', '', '0', '0', 'system:notice:view', '#', '通知公告菜单', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('108', '日志管理', '1', '9', '#', '', '0', '0', '', '#', '日志管理菜单', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('109', '在线用户', '2', '1', '/monitor/online', '', '0', '0', 'monitor:online:view', '#', '在线用户菜单', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('110', '定时任务', '2', '2', '/monitor/job', '', '0', '0', 'monitor:job:view', '#', '定时任务菜单', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('112', '服务监控', '2', '3', '/monitor/server', '', '0', '0', 'monitor:server:view', '#', '服务监控菜单', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('113', '表单构建', '3', '1', '/tool/build', '', '0', '0', 'tool:build:view', '#', '表单构建菜单', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('114', '代码生成', '3', '2', '/tool/gen', '', '0', '0', 'tool:gen:view', '#', '代码生成菜单', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('115', '系统接口', '3', '3', '/tool/swagger', '', '0', '0', 'tool:swagger:view', '#', '系统接口菜单', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('500', '操作日志', '108', '1', '/monitor/operlog', '', '0', '0', 'monitor:operlog:view', '#', '操作日志菜单', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('501', '登录日志', '108', '2', '/monitor/logininfor', '', '0', '0', 'monitor:logininfor:view', '#', '登录日志菜单', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1000', '用户查询', '100', '1', '#', '', '0', '0', 'system:user:list', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1001', '用户新增', '100', '2', '#', '', '0', '0', 'system:user:add', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1002', '用户修改', '100', '3', '#', '', '0', '0', 'system:user:edit', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1003', '用户删除', '100', '4', '#', '', '0', '0', 'system:user:remove', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1004', '用户导出', '100', '5', '#', '', '0', '0', 'system:user:export', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1005', '用户导入', '100', '6', '#', '', '0', '0', 'system:user:import', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1006', '重置密码', '100', '7', '#', '', '0', '0', 'system:user:resetPwd', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1007', '角色查询', '101', '1', '#', '', '0', '0', 'system:role:list', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1008', '角色新增', '101', '2', '#', '', '0', '0', 'system:role:add', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1009', '角色修改', '101', '3', '#', '', '0', '0', 'system:role:edit', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1010', '角色删除', '101', '4', '#', '', '0', '0', 'system:role:remove', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1011', '角色导出', '101', '5', '#', '', '0', '0', 'system:role:export', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1012', '菜单查询', '102', '1', '#', '', '0', '0', 'system:menu:list', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1013', '菜单新增', '102', '2', '#', '', '0', '0', 'system:menu:add', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1014', '菜单修改', '102', '3', '#', '', '0', '0', 'system:menu:edit', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1015', '菜单删除', '102', '4', '#', '', '0', '0', 'system:menu:remove', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1016', '部门查询', '103', '1', '#', '', '0', '0', 'system:dept:list', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1017', '部门新增', '103', '2', '#', '', '0', '0', 'system:dept:add', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1018', '部门修改', '103', '3', '#', '', '0', '0', 'system:dept:edit', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1019', '部门删除', '103', '4', '#', '', '0', '0', 'system:dept:remove', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1020', '岗位查询', '104', '1', '#', '', '0', '0', 'system:post:list', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1021', '岗位新增', '104', '2', '#', '', '0', '0', 'system:post:add', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1022', '岗位修改', '104', '3', '#', '', '0', '0', 'system:post:edit', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1023', '岗位删除', '104', '4', '#', '', '0', '0', 'system:post:remove', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1024', '岗位导出', '104', '5', '#', '', '0', '0', 'system:post:export', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1025', '字典查询', '105', '1', '#', '', '0', '0', 'system:dict:list', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1026', '字典新增', '105', '2', '#', '', '0', '0', 'system:dict:add', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1027', '字典修改', '105', '3', '#', '', '0', '0', 'system:dict:edit', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1028', '字典删除', '105', '4', '#', '', '0', '0', 'system:dict:remove', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1029', '字典导出', '105', '5', '#', '', '0', '0', 'system:dict:export', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1030', '参数查询', '106', '1', '#', '', '0', '0', 'system:config:list', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1031', '参数新增', '106', '2', '#', '', '0', '0', 'system:config:add', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1032', '参数修改', '106', '3', '#', '', '0', '0', 'system:config:edit', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1033', '参数删除', '106', '4', '#', '', '0', '0', 'system:config:remove', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1034', '参数导出', '106', '5', '#', '', '0', '0', 'system:config:export', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1035', '公告查询', '107', '1', '#', '', '0', '0', 'system:notice:list', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1036', '公告新增', '107', '2', '#', '', '0', '0', 'system:notice:add', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1037', '公告修改', '107', '3', '#', '', '0', '0', 'system:notice:edit', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1038', '公告删除', '107', '4', '#', '', '0', '0', 'system:notice:remove', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1039', '操作查询', '500', '1', '#', '', '0', '0', 'monitor:operlog:list', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1040', '操作删除', '500', '2', '#', '', '0', '0', 'monitor:operlog:remove', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1041', '详细信息', '500', '3', '#', '', '0', '0', 'monitor:operlog:detail', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1042', '日志导出', '500', '4', '#', '', '0', '0', 'monitor:operlog:export', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1043', '登录查询', '501', '1', '#', '', '0', '0', 'monitor:logininfor:list', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1044', '登录删除', '501', '2', '#', '', '0', '0', 'monitor:logininfor:remove', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1045', '日志导出', '501', '3', '#', '', '0', '0', 'monitor:logininfor:export', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1046', '在线查询', '109', '1', '#', '', '0', '0', 'monitor:online:list', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1047', '批量强退', '109', '2', '#', '', '0', '0', 'monitor:online:batchForceLogout', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1048', '单条强退', '109', '3', '#', '', '0', '0', 'monitor:online:forceLogout', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1049', '任务查询', '110', '1', '#', '', '0', '0', 'monitor:job:list', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1050', '任务新增', '110', '2', '#', '', '0', '0', 'monitor:job:add', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1051', '任务修改', '110', '3', '#', '', '0', '0', 'monitor:job:edit', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1052', '任务删除', '110', '4', '#', '', '0', '0', 'monitor:job:remove', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1053', '状态修改', '110', '5', '#', '', '0', '0', 'monitor:job:changeStatus', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1054', '任务详细', '110', '6', '#', '', '0', '0', 'monitor:job:detail', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1055', '任务导出', '110', '7', '#', '', '0', '0', 'monitor:job:export', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1056', '生成查询', '114', '1', '#', '', '0', '0', 'tool:gen:list', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1057', '生成代码', '114', '2', '#', '', '0', '0', 'tool:gen:code', '#', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_menu` VALUES ('1058', '即时日志', '2', '4', '/monitor/consolelog', 'menuItem', '0', '0', 'monitor:consolelog:view', '', '', '2019-07-25 01:59:15', 'crown', '2019-07-25 09:52:52', null, '0');
INSERT INTO `sys_menu` VALUES ('1059', '异常日志', '2', '1', '/monitor/exceLog', 'menuItem', '0', '0', 'monitor:exceLog:view', '#', '异常日志菜单', '2018-06-28 00:00:00', 'crown', '2019-07-27 14:48:54', null, '0');
INSERT INTO `sys_menu` VALUES ('1060', '异常日志查询', '1059', '1', '#', 'menuItem', '0', '0', 'monitor:exceLog:list', '#', '', '2018-06-28 00:00:00', 'crown', '2019-07-27 14:49:10', null, '0');
INSERT INTO `sys_menu` VALUES ('1061', '异常日志删除', '1059', '4', '#', 'menuItem', '0', '0', 'monitor:exceLog:remove', '#', '', '2018-06-28 00:00:00', 'crown', '2019-07-27 14:49:22', null, '0');
INSERT INTO `sys_menu` VALUES ('1062', '异常日志详情', '1059', '3', '', 'menuItem', '0', '0', 'monitor:exceLog:detail', '', '', '2019-07-27 16:48:20', 'crown', '2019-07-27 16:48:48', null, '0');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `remark` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新者',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '删除标识（0|未删除  1|已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='岗位信息表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('1', 'ceo', '董事长', '1', '0', '', '2018-03-16 11:33:00', 'crown', '2019-08-28 19:19:29', 'crown', '0');
INSERT INTO `sys_post` VALUES ('2', 'se', '项目经理', '2', '0', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_post` VALUES ('3', 'hr', '人力crown2资源', '3', '0', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');
INSERT INTO `sys_post` VALUES ('4', 'user', '普通员工', '4', '0', '', '2018-03-16 11:33:00', 'crown', '2018-03-16 11:33:00', 'crown', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` tinyint(4) DEFAULT NULL COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新者',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '删除标识（0|未删除  1|已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'admin', '1', '1', '0', '2018-03-16 11:33:00', 'crown', '2019-07-23 17:15:23', null, '0');
INSERT INTO `sys_role` VALUES ('2', '普通角色', 'common', '2', '2', '0', '2018-03-16 11:33:00', 'crown', '2019-08-28 19:17:04', null, '0');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色ID',
  `dept_id` int(11) NOT NULL DEFAULT '0' COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色和部门关联表';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('2', '100');
INSERT INTO `sys_role_dept` VALUES ('2', '101');
INSERT INTO `sys_role_dept` VALUES ('2', '105');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色ID',
  `menu_id` int(11) NOT NULL DEFAULT '0' COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '100');
INSERT INTO `sys_role_menu` VALUES ('1', '102');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) COLLATE utf8mb4_bin NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) COLLATE utf8mb4_bin NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) COLLATE utf8mb4_bin DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '用户邮箱',
  `phone` varchar(11) COLLATE utf8mb4_bin DEFAULT '' COMMENT '手机号码',
  `sex` tinyint(4) DEFAULT NULL COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '密码',
  `salt` varchar(20) COLLATE utf8mb4_bin DEFAULT '' COMMENT '盐加密',
  `status` tinyint(4) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `login_ip` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `remark` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新者',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '删除标识（0|未删除  1|已删除）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', null, 'root', 'root', '00', '', '', null, '', '04522abf42bb8ad979fe66948402bc0d', 'hello', '0', '', null, null, '2020-03-27 15:22:15', null, null, null, '0');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `post_id` int(11) NOT NULL DEFAULT '0' COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户与岗位关联表';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');

-- ----------------------------
-- Table structure for user_operation_record
-- ----------------------------
DROP TABLE IF EXISTS `user_operation_record`;
CREATE TABLE `user_operation_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` varchar(36) DEFAULT NULL COMMENT '用户uuid',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名称',
  `user_type` smallint(6) DEFAULT NULL COMMENT '用户类型',
  `action_url` varchar(500) DEFAULT NULL COMMENT '操作路径',
  `parameter` varchar(2000) DEFAULT NULL COMMENT '请求参数',
  `description` varchar(500) DEFAULT NULL COMMENT '功能描述',
  `ip` varchar(50) DEFAULT NULL COMMENT '请求IP地址',
  `detail` varchar(500) DEFAULT NULL COMMENT '备注',
  `start_time` datetime DEFAULT NULL COMMENT '操作接口开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '操作接口结束时间',
  `business_type` smallint(6) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `operator_type` smallint(6) DEFAULT '1' COMMENT '操作人类别（0其它 1后台用户 2手机端用户）',
  `status` smallint(6) DEFAULT '1' COMMENT '操作状态（1正常 0异常）',
  `error_message` varchar(2000) DEFAULT NULL COMMENT '错误消息(status=0，才有效果)',
  `operating_time` bigint(20) DEFAULT NULL COMMENT '操作时长',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '删除标识（0|未删除  1|已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户操作轨迹记录表';

-- ----------------------------
-- Records of user_operation_record
-- ----------------------------
INSERT INTO `user_operation_record` VALUES ('74', null, null, null, '/api/article/list', '{\"title\":[\"4\"]}', '文章列表', '0:0:0:0:0:0:0:1', '/ by zero', '2020-03-30 09:16:00', '2020-03-30 09:16:01', '0', '0', '0', '系统异常', '384', '2020-03-30 09:16:04', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('75', null, null, null, '/api/article/list', '{\"title\":[\"4\"]}', '文章列表', '0:0:0:0:0:0:0:1', '/ by zero', '2020-03-30 09:25:39', '2020-03-30 09:25:40', '0', '0', '0', '系统异常', '369', '2020-03-30 09:25:43', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('76', null, null, null, '/api/article/list', '{\"title\":[\"4\"]}', '文章列表', '0:0:0:0:0:0:0:1', '/ by zero', '2020-03-30 09:29:08', '2020-03-30 09:29:08', '0', '0', '0', '系统异常', '8', '2020-03-30 09:29:25', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('77', null, null, null, '/api/article/list', '{\"title\":[\"4\"]}', '文章列表', '0:0:0:0:0:0:0:1', '/ by zero', '2020-03-30 09:32:49', '2020-03-30 09:32:50', '0', '0', '0', '系统异常', '386', '2020-03-30 09:32:51', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('78', null, null, null, '/api/article/list', '{\"title\":[\"4\"]}', '文章列表', '0:0:0:0:0:0:0:1', '/ by zero', '2020-03-30 09:33:12', '2020-03-30 09:34:12', '0', '0', '0', '系统异常', '59736', '2020-03-30 09:34:14', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('79', null, null, null, '/api/article/list', '{\"title\":[\"4\"]}', '文章列表', '0:0:0:0:0:0:0:1', '/ by zero', '2020-03-30 09:52:20', '2020-03-30 09:52:21', '0', '0', '0', '系统异常', '381', '2020-03-30 09:52:25', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('80', null, null, null, '/api/article/list', '{\"title\":[\"4\"]}', '文章列表', '0:0:0:0:0:0:0:1', '/ by zero', '2020-03-30 10:28:22', '2020-03-30 10:28:22', '0', '0', '0', '系统异常', '376', '2020-03-30 10:28:37', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('81', null, null, null, '/api/article/list', '{\"title\":[\"4\"]}', '文章列表', '0:0:0:0:0:0:0:1', '/ by zero', '2020-03-30 10:29:35', '2020-03-30 10:29:35', '0', '0', '0', '系统异常', '388', '2020-03-30 10:29:49', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('82', null, null, null, '/api/article/list', '{\"title\":[\"4\"]}', '文章列表', '0:0:0:0:0:0:0:1', '/ by zero', '2020-03-30 10:36:25', '2020-03-30 10:36:25', '0', '0', '0', '系统异常', '387', '2020-03-30 10:36:27', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('83', null, null, null, '/api/article/list', '{\"title\":[\"4\"]}', '文章列表', '0:0:0:0:0:0:0:1', '/ by zero', '2020-03-30 10:37:12', '2020-03-30 10:37:12', '0', '0', '0', '系统异常', '8', '2020-03-30 10:37:14', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('84', null, null, null, '/api/article/list', '{\"title\":[\"4\"]}', '文章列表', '0:0:0:0:0:0:0:1', '/ by zero', '2020-03-30 10:39:14', '2020-03-30 10:39:15', '0', '0', '0', '系统异常', '371', '2020-03-30 10:39:16', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('85', null, null, null, '/api/article/list', '{\"title\":[\"5\"]}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-03-30 16:49:33', '2020-03-30 16:49:33', '0', '0', '1', null, '474', '2020-03-30 16:49:35', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('86', null, null, null, '/api/article/list', '{\"title\":[\"2\"]}', '文章列表', '0:0:0:0:0:0:0:1', '/ by zero', '2020-03-31 08:44:52', '2020-03-31 08:44:52', '0', '0', '0', '系统异常', '429', '2020-03-31 08:44:54', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('87', null, null, null, '/api/article/list', '{\"title\":[\"2\"]}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-03-31 08:45:43', '2020-03-31 08:45:43', '0', '0', '1', null, '318', '2020-03-31 08:45:45', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('88', null, null, null, '/api/article/list', '{\"title\":[\"7\"]}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-03-31 08:45:58', '2020-03-31 08:45:58', '0', '0', '1', null, '6', '2020-03-31 08:45:59', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('89', null, null, null, '/api/article/list', '{\"title\":[\"8\"]}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-03-31 08:46:05', '2020-03-31 08:46:05', '0', '0', '1', null, '5', '2020-03-31 08:46:06', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('90', null, null, null, '/api/article/list', '{\"title\":[\"5\"]}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-03-31 08:46:09', '2020-03-31 08:46:09', '0', '0', '1', null, '4', '2020-03-31 08:46:11', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('91', null, null, null, '/api/article/list', '{\"title\":[\"5\"]}', '文章列表', '0:0:0:0:0:0:0:1', '/ by zero', '2020-03-31 08:50:12', '2020-03-31 08:50:12', '0', '0', '0', '系统异常', '271', '2020-03-31 08:50:14', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('92', null, null, null, '/api/article/list', '{\"title\":[\"5\"]}', '文章列表', '0:0:0:0:0:0:0:1', '/ by zero', '2020-03-31 08:51:09', '2020-03-31 08:51:09', '0', '0', '0', '系统异常', '6', '2020-03-31 08:51:10', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('93', null, null, null, '/api/article/list', '{\"title\":[\"1\"]}', '文章列表', '0:0:0:0:0:0:0:1', '/ by zero', '2020-03-31 08:57:41', '2020-03-31 08:57:41', '0', '0', '0', '系统异常', '25', '2020-03-31 08:57:42', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('94', null, null, null, '/api/article/list', '{\"title\":[\"1\"]}', '文章列表', '0:0:0:0:0:0:0:1', '/ by zero', '2020-03-31 09:09:43', '2020-03-31 09:09:43', '0', '0', '0', '系统异常', '303', '2020-03-31 09:09:45', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('95', null, null, null, '/api/article/list', '{\"title\":[\"1\"]}', '文章列表', '0:0:0:0:0:0:0:1', '/ by zero', '2020-03-31 09:14:11', '2020-03-31 09:14:12', '0', '0', '0', '系统异常', '322', '2020-03-31 09:14:13', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('96', null, null, null, '/api/article/list', '{\"title\":[\"5\"]}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-03-31 14:31:51', '2020-03-31 14:31:51', '0', '0', '1', null, '378', '2020-03-31 14:31:53', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('97', null, null, null, '/api/article/list', '{\"title\":[\"5\"]}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-03-31 14:41:23', '2020-03-31 14:41:23', '0', '0', '1', null, '310', '2020-03-31 14:41:25', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('98', null, null, null, '/api/article/list', '{\"title\":[\"5\"]}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-03-31 14:42:19', '2020-03-31 14:42:19', '0', '0', '1', null, '310', '2020-03-31 14:42:21', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('99', null, null, null, '/api/article/list', '{\"title\":[\"5\"]}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-03-31 14:49:28', '2020-03-31 14:49:29', '0', '0', '1', null, '314', '2020-03-31 14:49:30', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('100', null, null, null, '/api/article/list', '{\"title\":[\"5\"]}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-03-31 14:58:54', '2020-03-31 14:58:54', '0', '0', '1', null, '310', '2020-03-31 14:58:56', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('101', null, null, null, '/api/article/list', '{\"title\":[\"5\"]}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-03-31 14:59:04', '2020-03-31 14:59:04', '0', '0', '1', null, '6', '2020-03-31 14:59:05', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('102', null, null, null, '/api/article/list', '{\"title\":[\"5\"]}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-03-31 15:02:49', '2020-03-31 15:02:49', '0', '0', '1', null, '314', '2020-03-31 15:02:51', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('103', null, null, null, '/api/article/list', '{\"title\":[\"5\"]}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-03-31 15:09:46', '2020-03-31 15:09:47', '0', '0', '1', null, '319', '2020-03-31 15:09:48', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('104', null, null, null, '/api/article/list', '{\"title\":[\"5\"]}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-03-31 15:11:52', '2020-03-31 15:11:52', '0', '0', '1', null, '320', '2020-03-31 15:11:54', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('105', null, null, null, '/api/article/list', '{\"title\":[\"5\"]}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-03-31 15:42:53', '2020-03-31 15:42:53', '0', '0', '1', null, '6', '2020-03-31 15:42:54', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('106', null, null, null, '/api/article/list', '{\"title\":[\"5\"]}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-03-31 15:43:07', '2020-03-31 15:43:07', '0', '0', '1', null, '4', '2020-03-31 15:43:08', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('107', null, null, null, '/api/article/list', '{\"title\":[\"5\"]}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-03-31 15:43:17', '2020-03-31 15:43:17', '0', '0', '1', null, '20', '2020-03-31 15:43:19', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('108', null, null, null, '/api/article/list', '{\"title\":[\"5\"]}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-03-31 15:51:34', '2020-03-31 15:51:35', '0', '0', '1', null, '321', '2020-03-31 15:51:36', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('109', null, null, null, '/api/article/list', '{\"title\":[\"5\"]}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-03-31 15:51:57', '2020-03-31 15:51:57', '0', '0', '1', null, '5', '2020-03-31 15:51:59', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('110', null, null, null, '/api/article/list', '{\"title\":[\"5\"]}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-03-31 15:52:57', '2020-03-31 15:53:11', '0', '0', '1', null, '14003', '2020-03-31 15:53:13', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('111', null, null, null, '/api/article/list', '{}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-04-01 14:36:09', '2020-04-01 14:36:09', '0', '0', '1', null, '304', '2020-04-01 14:36:11', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('112', null, null, null, '/api/article/list', '{}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-04-01 14:40:08', '2020-04-01 14:40:08', '0', '0', '1', null, '9', '2020-04-01 14:40:10', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('113', null, null, null, '/api/article/list', '{}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-04-01 14:41:45', '2020-04-01 14:41:56', '0', '0', '1', null, '11433', '2020-04-01 14:42:03', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('114', null, null, null, '/api/article/list', '{}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-04-01 14:43:56', '2020-04-01 14:43:56', '0', '0', '1', null, '249', '2020-04-01 14:43:58', null, null, null, '0');
INSERT INTO `user_operation_record` VALUES ('115', null, null, null, '/api/article/list', '{}', '文章列表', '0:0:0:0:0:0:0:1', '', '2020-04-01 14:44:05', '2020-04-01 14:44:06', '0', '0', '1', null, '811', '2020-04-01 14:45:11', null, null, null, '0');
