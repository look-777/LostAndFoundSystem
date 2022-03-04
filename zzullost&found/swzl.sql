/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : swzl

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2021-06-20 19:57:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for claim
-- ----------------------------
DROP TABLE IF EXISTS `claim`;
CREATE TABLE `claim` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `post_id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `post_title` varchar(40) DEFAULT NULL,
  `user_name` varchar(40) DEFAULT NULL,
  `claim_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cliam_record_ibfk_2` (`user_id`),
  KEY `cliam_record_ibfk_1` (`post_id`),
  CONSTRAINT `claim_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `claim_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of claim
-- ----------------------------
INSERT INTO `claim` VALUES ('1', '45', '29', '丢书了', '张浩堃', '2021-06-05 14:16:42');
INSERT INTO `claim` VALUES ('2', '55', '34', '寻找U盘', 'zhangxinyan', '2021-06-05 14:16:56');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `post_id` bigint(20) NOT NULL COMMENT '贴子ID',
  `uname` varchar(20) DEFAULT NULL COMMENT '评论人',
  `content` varchar(50) NOT NULL COMMENT '内容',
  `ctime` datetime DEFAULT NULL COMMENT '评论时间',
  `parent` bigint(20) DEFAULT NULL COMMENT '是否有父评论',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `comment_ibfk_1` (`post_id`) USING BTREE,
  KEY `comment_ibfk_2` (`uname`) USING BTREE,
  KEY `parent_comment` (`parent`) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('68', '45', '张浩堃', '这个黑大头是什么呢', '2021-01-16 07:44:25', null);
INSERT INTO `comment` VALUES ('69', '45', '詹伟杰', '我也不知道，看着很厉害的样子', '2021-01-16 07:44:42', null);
INSERT INTO `comment` VALUES ('70', '45', '张鑫焱', '华为手机吗', '2021-01-16 07:44:56', '68');
INSERT INTO `comment` VALUES ('71', '45', '张浩堃', '对 哈哈哈 这个是张笑天的手机吧 华为P40', '2021-04-22 10:39:29', '70');
INSERT INTO `comment` VALUES ('72', '53', '张浩堃', '呜呜呜，我新买的书本啊。大家帮我找找看。', '2021-04-22 15:06:57', null);
INSERT INTO `comment` VALUES ('73', '53', '张浩堃', '哈哈，我已经找到了，还是谢谢大家奥。', '2021-04-22 15:09:55', null);
INSERT INTO `comment` VALUES ('74', '53', '匿名用户', '你好，请问找到了吗，我今天好像看到了哎。', '2021-04-22 15:10:30', null);
INSERT INTO `comment` VALUES ('75', '53', '匿名用户', '哈哈哈，那就好', '2021-04-22 15:12:05', null);
INSERT INTO `comment` VALUES ('76', '50', '张浩堃', '什么鬼？你咋哈哈哈', '2021-04-23 09:35:45', null);
INSERT INTO `comment` VALUES ('77', '50', '匿名用户', '因为我是后台管理员，这个帖子是测试用的啊', '2021-04-23 09:36:17', null);
INSERT INTO `comment` VALUES ('78', '50', '张浩堃', '奥奥，明白了。', '2021-04-23 09:36:33', null);
INSERT INTO `comment` VALUES ('79', '45', 'zhangxiaotian', '同志们我找到了啊，谢谢各位hxdm', '2021-04-23 09:37:47', null);
INSERT INTO `comment` VALUES ('80', '45', 'zhangxiaotian', '嗯嗯 ，谢谢各位，这个对我来说特别珍贵。', '2021-04-23 09:38:35', null);
INSERT INTO `comment` VALUES ('81', '47', '张浩堃', 'hahha ', '2021-05-08 09:42:41', null);
INSERT INTO `comment` VALUES ('82', '53', '匿名用户', '厉害啊', '2021-05-24 13:15:13', null);
INSERT INTO `comment` VALUES ('83', '50', '张浩堃', '好的', '2021-05-25 08:50:08', null);
INSERT INTO `comment` VALUES ('84', '56', '张浩堃', '请问找到了吗？', '2021-05-28 09:21:54', null);
INSERT INTO `comment` VALUES ('97', '50', '张浩堃', 'a', '2021-06-02 10:40:51', null);
INSERT INTO `comment` VALUES ('98', '50', '匿名用户', 'b', '2021-06-02 10:40:57', null);
INSERT INTO `comment` VALUES ('99', '50', '匿名用户', 'hahah', '2021-06-02 10:45:12', null);
INSERT INTO `comment` VALUES ('100', '50', '张浩堃', 'hello', '2021-06-02 10:45:20', null);
INSERT INTO `comment` VALUES ('101', '56', '匿名用户', '还没呢', '2021-06-06 10:49:09', null);

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) NOT NULL COMMENT '反馈用户',
  `title` varchar(20) NOT NULL COMMENT '反馈标题',
  `content` varchar(1000) NOT NULL COMMENT '反馈内容',
  `ctime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `uname` (`uname`) USING BTREE,
  CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`uname`) REFERENCES `tb_user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES ('5', 'kappy', '系统功能', '功能很完善，再接再厉', '2021-03-23 11:15:32');
INSERT INTO `feedback` VALUES ('6', '张浩堃', '测试', '这是一个测试公告。', '2021-04-14 10:08:26');
INSERT INTO `feedback` VALUES ('7', '张浩堃', '测试', '这是一个测试公告', '2021-04-14 10:08:55');
INSERT INTO `feedback` VALUES ('8', '张浩堃', '我要反馈', '管理员同志你好，我发现了你们系统的bug。我给你看看', '2021-04-14 10:11:47');
INSERT INTO `feedback` VALUES ('9', '张浩堃', '一条小小的建议', '希望系统可以增加，邮箱提示功能', '2021-04-17 10:52:31');
INSERT INTO `feedback` VALUES ('10', 'zhangxinyan', '系统问题', '希望可以增加消息强提醒功能', '2021-05-12 10:32:05');
INSERT INTO `feedback` VALUES ('11', 'zhangxinyan', '反馈', '希望可以系统的功能可以更加完善一些，比如可以增加地图标注功能', '2021-05-12 10:35:06');
INSERT INTO `feedback` VALUES ('12', '张浩堃', '测试', '测试返库', '2021-06-01 20:20:15');
INSERT INTO `feedback` VALUES ('13', '张浩堃', '测试', '反馈测试', '2021-06-02 10:13:28');
INSERT INTO `feedback` VALUES ('14', '张浩堃', '测试', 'zzz', '2021-06-02 10:20:00');

-- ----------------------------
-- Table structure for mail
-- ----------------------------
DROP TABLE IF EXISTS `mail`;
CREATE TABLE `mail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '邮件编号，主键',
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮件内容',
  `ctime` datetime DEFAULT NULL COMMENT '邮件创建时间',
  `rev_id` bigint(20) DEFAULT NULL COMMENT '邮件接收方',
  `rev_name` varchar(20) DEFAULT NULL COMMENT '收件人姓名',
  PRIMARY KEY (`id`),
  KEY `rev_id` (`rev_id`),
  KEY `rev_name` (`rev_name`),
  CONSTRAINT `mail_ibfk_1` FOREIGN KEY (`rev_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `mail_ibfk_2` FOREIGN KEY (`rev_name`) REFERENCES `tb_user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mail
-- ----------------------------
INSERT INTO `mail` VALUES ('4', '系统发送', '张浩堃同学，你丢失的《数据分析之图算法》，找到了，请到系统查看详情', '2021-04-16 19:46:14', '45', '张浩堃');
INSERT INTO `mail` VALUES ('10', 'ZZU寻物小帮手', '同学你好，你的物品已经找到了，快到系统中查看吧~', '2021-05-21 05:12:39', '45', '张浩堃');
INSERT INTO `mail` VALUES ('11', 'ZZU寻物小帮手', '同学你好，你的物品已经找到了，快到系统中查看吧~', '2021-05-21 05:14:58', '52', 'zhangxinyan');
INSERT INTO `mail` VALUES ('12', 'ZZU寻物小帮手', '同学你好，你的物品已经找到了，快到系统中查看吧~', '2021-05-21 05:18:48', '49', 'zhanweijie');
INSERT INTO `mail` VALUES ('13', 'ZZU寻物小帮手', '同学你好，你发布的标题为：<1号楼前捡到一本书>的启示已经有人认领或找回了，快到系统中查看吧~', '2021-05-21 05:32:04', '45', '张浩堃');
INSERT INTO `mail` VALUES ('14', 'ZZU寻物小帮手', '张浩堃同学你好，你发布的标题为：<1号楼前捡到一本书>的启示已经有人认领或找回了，快到系统中查看吧~', '2021-05-21 05:32:51', '45', '张浩堃');
INSERT INTO `mail` VALUES ('15', 'ZZU寻物小帮手', 'zhanweijie同学你好，你发布的标题为：《捡到u盘》的启示已经有人认领或找回了，快到系统中查看吧~', '2021-05-24 05:14:26', '49', 'zhanweijie');
INSERT INTO `mail` VALUES ('16', 'ZZU寻物小帮手', '张浩堃同学你好，你发布的标题为：《找u盘哈》的启示已经有人认领或找回了，快到系统中查看吧~', '2021-05-25 00:49:44', '45', '张浩堃');
INSERT INTO `mail` VALUES ('17', 'ZZU寻物小帮手', '张浩堃同学你好，你发布的标题为：《1号楼前捡到一本书》的启示已经有人认领或找回了，快到系统中查看吧~', '2021-06-01 09:25:19', '45', '张浩堃');
INSERT INTO `mail` VALUES ('23', 'ZZU寻物小帮手', '张浩堃同学你好，你发布的标题为：《找u盘哈》的启示已经有人认领或找回了，快到系统中查看吧~', '2021-06-02 02:39:07', '45', '张浩堃');
INSERT INTO `mail` VALUES ('24', 'ZZU寻物小帮手', '张浩堃同学你好，你发布的标题为：《找u盘哈》的启示已经有人认领或找回了，快到系统中查看吧~', '2021-06-02 02:41:05', '45', '张浩堃');
INSERT INTO `mail` VALUES ('25', 'ZZU寻物小帮手', '张浩堃同学你好，你发布的标题为：《找u盘哈》的启示已经有人认领或找回了，快到系统中查看吧~', '2021-06-02 02:45:30', '45', '张浩堃');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cuser` varchar(20) DEFAULT NULL COMMENT '创建人',
  `content` varchar(1000) NOT NULL COMMENT '内容',
  `ctime` datetime NOT NULL COMMENT '创建时间',
  `title` varchar(40) NOT NULL COMMENT '通知标题',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `admin_fk` (`cuser`) USING BTREE,
  CONSTRAINT `admin_fk` FOREIGN KEY (`cuser`) REFERENCES `tb_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('11', 'kappy', '这是测试公告', '2021-03-23 14:54:03', '测试');
INSERT INTO `notice` VALUES ('15', 'kappy', 'hahha ', '2021-03-23 20:52:57', '考研预报名，影响推免信息填写吗');
INSERT INTO `notice` VALUES ('16', 'admin', '5月1号-3号我们的系统将更新，期间暂停服务，由此造成的不便，请谅解。', '2021-04-07 20:53:39', '系统升级通知');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) DEFAULT NULL COMMENT '发帖人',
  `type_name` varchar(20) DEFAULT NULL COMMENT '分类',
  `title` varchar(40) DEFAULT NULL COMMENT '标题',
  `content` varchar(1000) DEFAULT NULL COMMENT '内容',
  `picture` varchar(255) DEFAULT NULL COMMENT '图片',
  `address` varchar(40) DEFAULT NULL COMMENT '地址',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(11) DEFAULT NULL COMMENT '状态：1为进行中，0为结束',
  `flag` int(11) DEFAULT NULL COMMENT '标记：1为失物招领，0为寻物启事',
  `view_count` int(11) DEFAULT NULL COMMENT '查看次数',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `post_ibfk_1` (`uname`) USING BTREE,
  KEY `psot_fk` (`type_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('45', 'zhangxiaotian', '手机', '我的手机在餐厅不见了', '今天中午吃饭后，手机忘了，黑色的，谁见了给我下', '/images/4ad0fd9075c9422084cfffb26881b536_shouji.png', '北2', '2021-01-16 07:26:38', '1', '0', '71');
INSERT INTO `post` VALUES ('47', 'zhangxinyan', '书本', '捡到一本书', '我今天下午在操场主席台捡到一本《数据分析之图算法》', '/images/shuben.jpg', '操场', '2021-04-07 13:33:25', '1', '1', '39');
INSERT INTO `post` VALUES ('50', '张浩堃', '小物品', '找u盘哈', '金士顿丢了啊啊啊啊', '/images/upan.jpg', '图书馆', '2021-05-15 01:23:09', '0', '0', '109');
INSERT INTO `post` VALUES ('51', '张浩堃', '书本', '我捡到一本书', '我在操场东边捡到一本书《斯坦福数据挖掘课程》', '/images/3b1aaf99e44941f78aa2a72fd05e757e_1.png', '操场东大门', '2021-05-14 08:35:40', '0', '1', '63');
INSERT INTO `post` VALUES ('53', '张浩堃', '书本', '1号楼前捡到一本书', '今天晚饭后，在操场主席台东侧捡到一本书《数据分析之图算法--基于Spark计算引擎实现》，谁丢失的可以联系我哟。我的QQ号：3250515249', '/images/5d7885dbb0084e3bb79dbd39bad58e1d_1.png', '软件学院门口', '2021-04-08 08:14:39', '1', '0', '104');
INSERT INTO `post` VALUES ('54', null, '书本', '测试', '书本丢了', '/images/16ba59e509a4433aa70131cde60f3203_数据挖掘导论.jpg', '足球场', '2021-05-12 02:06:59', '1', '1', '5');
INSERT INTO `post` VALUES ('55', 'zhangxinyan', '校园卡', '卡丢了', '我的卡离家出走了', '/images/e6a3819184b9415c9dc5165f19816fbc_ka.jpg', '不晓得', '2021-05-12 02:14:46', '0', '0', '4');
INSERT INTO `post` VALUES ('56', 'zhangxinyan', '手机', '手机', '呜呜呜，俺的小米10丢了', '/images/e181bebc9021426b89514d6be077f698_xiaomi.jpg', '我也不知道', '2021-05-12 02:29:32', '1', '0', '34');
INSERT INTO `post` VALUES ('57', 'zhangxinyan', '小物品', '找文件--三方协议表', '我的三方协议表丢了。马上要签约了，希望大家可以帮助我', '/images/ffdb736108184655bb52e5d14f96dbd1_xieyi.jpg', '国旗台', '2021-05-17 00:49:47', '1', '1', '12');
INSERT INTO `post` VALUES ('59', 'zhangxiaotian', '小物品', '找文件--调档函', '找我的调档函', '/images/0e4080c6064147418fdb6087c0a29a03_han.jpg', '不晓得', '2021-05-17 01:00:02', '0', '1', '14');
INSERT INTO `post` VALUES ('60', 'zhanweijie', '小物品', '捡到u盘', '捡到金士顿优盘一枚。', '/images/36a2256ceff3421f981fc5419e0c3e8d_jinshidun.jpg', '足球场', '2021-05-21 05:18:13', '1', '1', '16');
INSERT INTO `post` VALUES ('61', '张浩堃', '小物品', '测试111', '这事测试111', '/images/2d2624a34bb34a99b1908ffd6195b6f9_学信网学历.png', '不晓得', '2021-06-01 12:19:23', '0', '0', '2');
INSERT INTO `post` VALUES ('66', '张浩堃', '书本', '书本丢了', '丢书本了', '/images/cec2a0e834e5438c839c677b7620c371_t016eeac8392dcdaa69.jpg', '足球场', '2021-06-02 02:46:59', '0', '0', '1');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin` varchar(5) DEFAULT NULL,
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` char(32) DEFAULT NULL COMMENT '密码',
  `email` varchar(50) DEFAULT NULL COMMENT 'email',
  `sex` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `photo` varchar(100) DEFAULT NULL COMMENT '头像',
  `reward_code` varchar(100) DEFAULT NULL COMMENT '打赏码',
  `personal_say` varchar(50) DEFAULT NULL COMMENT '个性签名',
  `last_time` datetime DEFAULT NULL COMMENT '上一次登录',
  `type` int(11) NOT NULL COMMENT '类型 0 普通 1 管理员',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('22', 'true', 'kappy', 'c4ca4238a0b923820dcc509a6f75849b', '490151517@qq.com', '女', '21', '/images/08fc640518cd417390054f1917a004ef_3.png', '/images/320573054bda4c42b618d0f838efa4c6_erwei.png', '在哪里', '2021-01-07 01:46:34', '1');
INSERT INTO `tb_user` VALUES ('27', 'true', 'admin', 'c4ca4238a0b923820dcc509a6f75849b', 'admin@126.com', '女', '25', '/images/08fc640518cd417390054f1917a004ef_3.png', '/images/320573054bda4c42b618d0f838efa4c6_erwei.png', '谁是谁的谁', '2021-02-18 03:54:05', '0');
INSERT INTO `tb_user` VALUES ('29', 'true', 'root', '63a9f0ea7bb98050796b649e85481845', '3250514239@qq.com', '男', '22', null, null, '1111', '2021-03-17 17:43:54', '1');
INSERT INTO `tb_user` VALUES ('30', null, '张三', '202cb962ac59075b964b07152d234b70', '1', '男', '21', '12', '11', 'hahah', '2021-03-17 19:07:32', '1');
INSERT INTO `tb_user` VALUES ('31', null, '李四', '202cb962ac59075b964b07152d234b70', 'qq.com', '男', '23', '111', '2222', '哈哈哈', '2021-03-17 19:26:13', '0');
INSERT INTO `tb_user` VALUES ('34', null, '张晓丹', '698d51a19d8a121ce581499d7b701668', 'qq.com', '男', '23', '111', '111', '111', '2021-03-17 19:49:42', '1');
INSERT INTO `tb_user` VALUES ('35', null, '马晒行', '698d51a19d8a121ce581499d7b701668', 'qq.com', '男', '23', '111', '111', '111', '2021-03-17 19:59:54', '1');
INSERT INTO `tb_user` VALUES ('43', null, '张先生', '698d51a19d8a121ce581499d7b701668', '3250514239@qq.com', '男', '11', null, null, '哈哈哈', '2021-03-17 20:28:25', '1');
INSERT INTO `tb_user` VALUES ('44', null, 'lisa', '202cb962ac59075b964b07152d234b70', '3250514239@qq.com', '男', '23', null, null, '大家好，我是丽萨', '2021-03-20 09:21:21', '0');
INSERT INTO `tb_user` VALUES ('45', 'true', '张浩堃', '16aa4cd48601731af22391816eabf9b1', '3250514239@qq.com', '男', '21', null, null, '我爱这艰难又拼劲全力的每一天~', '2021-04-09 11:30:09', '1');
INSERT INTO `tb_user` VALUES ('46', null, 'zhanghaokun', '16aa4cd48601731af22391816eabf9b1', null, null, null, null, null, null, '2021-04-19 14:42:30', '0');
INSERT INTO `tb_user` VALUES ('48', null, 'zhangxiaotian', '28a7969050468a4cd4928f5abd03964e', '759895692@qq.com', '男', '22', null, null, '不努力，怎能赶上父母老去的步伐', '2021-04-19 15:44:35', '0');
INSERT INTO `tb_user` VALUES ('49', null, 'zhanweijie', 'b27b75defa5bdefb5447aa6255771184', '1424653119@qq.com', '男', '23', null, null, '大家好，我是伟大杰出', '2021-04-19 16:02:40', '0');
INSERT INTO `tb_user` VALUES ('52', null, 'zhangxinyan', 'e36cb309aef2f4cb703c06f5df8aba33', '1147151592@qq.com', '男', '22', null, null, '我酒精过敏', '2021-04-19 16:12:07', '0');
INSERT INTO `tb_user` VALUES ('54', null, 'zhanghk', '16aa4cd48601731af22391816eabf9b1', null, null, null, null, null, '关关难过关关过，前路漫漫亦灿灿。', '2021-04-20 11:12:42', '0');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `type_name` (`type_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('5', '书本');
INSERT INTO `type` VALUES ('7', '小物品');
INSERT INTO `type` VALUES ('3', '手机');
INSERT INTO `type` VALUES ('21', '文件');
INSERT INTO `type` VALUES ('1', '校园卡');
INSERT INTO `type` VALUES ('2', '衣服');
INSERT INTO `type` VALUES ('8', '钱包');
