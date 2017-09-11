-- MySQL dump 10.13  Distrib 5.7.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: knowledgerepo
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_knowledge`
--

DROP TABLE IF EXISTS `tb_knowledge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_knowledge` (
  `id` varchar(36) NOT NULL COMMENT '知识id',
  `kTitle` varchar(255) NOT NULL COMMENT '知识标题',
  `kAnswer` blob NOT NULL COMMENT '知识解答',
  `kUseCount` int(11) NOT NULL DEFAULT '0' COMMENT '使用次数',
  `kUserTimeLast` datetime DEFAULT NULL COMMENT '最后一次使用时间',
  `kApprStatus` varchar(10) NOT NULL DEFAULT '录入待审批' COMMENT '审批状态',
  `kApprUserId` varchar(36) DEFAULT NULL COMMENT '审批人',
  `kApprTime` datetime DEFAULT NULL COMMENT '审批时间',
  `kApprMemo` varchar(2000) DEFAULT NULL COMMENT '审批意见',
  `createUserId` varchar(36) NOT NULL COMMENT '创建人',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='知识表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_knowledge`
--

LOCK TABLES `tb_knowledge` WRITE;
/*!40000 ALTER TABLE `tb_knowledge` DISABLE KEYS */;
INSERT INTO `tb_knowledge` VALUES ('068ea715-8d3f-4492-bea4-7c360024f1b7','烟草','有毒',0,'2017-09-07 13:59:07','删除待审批','bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-08 22:36:30','不错','123','2017-09-07 13:59:07'),('1b471a65-ece0-4bd7-a33d-55e94b7e9c38','微笑','傻瓜',1,'2017-09-07 13:56:36','录入待审批',NULL,NULL,NULL,'12364','2017-09-07 13:40:26'),('275a63c4-0400-45c9-a32d-c60b1fe0f2b6','早上好','晚上好',1,'2017-09-07 13:34:03','删除待审批','123456','2017-09-07 11:34:11','常用','11111','2017-09-07 10:09:19'),('2972d181-8743-4e4e-9bc2-5ad3d33be3f1','你是谁','神',1,'2017-09-07 17:22:19','删除待审批','3333','2017-09-07 11:53:59','有创意','12345','2017-09-07 10:23:22'),('2e7fd8fe-e828-4101-9f55-a36668a48bab','王晨','很好',2,'2017-09-07 17:21:54','删除待审批','1111','2017-09-07 13:10:56','不错','26333','2017-09-07 12:37:48'),('3','慌慌张张','稳稳当当',5,'2017-09-07 13:35:03','删除待审批','121','2017-09-07 12:12:56','还行','12345','2017-08-24 17:25:35'),('30aa9f2d-7493-4899-b5cc-26ef9d49b32a','头发长','理发',0,'2017-09-07 14:39:36','录入待审批',NULL,NULL,NULL,'3333','2017-09-07 14:39:36'),('3b1abcaf-947a-4db0-9ecb-2b4ca58995b6','梨花','棉花',0,'2017-09-07 13:51:12','编辑待审批',NULL,NULL,NULL,'123456','2017-09-07 13:51:12'),('402abae3-74a4-4530-8fa9-287704298ef6','你','我',7,'2017-09-07 09:59:58','通过','123456','2017-09-07 01:08:22','常用','10086','2017-09-06 19:21:24'),('6228c000-2434-47c8-8b70-050a76665bd0','aaaaaa','<p>aaaaaaaa</p>',0,'2017-09-11 10:43:08','录入待审批',NULL,NULL,NULL,'','2017-09-11 10:43:08'),('7','它好','你也好',3,'2017-08-24 17:25:07','删除待审批','22222','2017-09-07 12:05:50','不错','456321','2017-08-24 17:25:35'),('750e9571-42e9-46ec-9d58-fa877df0b25b','6666','666',0,'2017-09-07 12:52:43','删除待审批','666666','2017-09-07 13:39:15','6666','6666','2017-09-07 12:52:43'),('7536a554-8863-4410-b21a-699a2f290515','天气情况','下雨',0,'2017-09-07 09:24:58','不通过','','2017-09-07 13:25:50','不好','45642','2017-09-05 11:50:16'),('8','不错','很好',6,'2017-09-07 09:57:26','录入待审批','','2017-05-30 17:25:24','','122','2017-08-24 17:25:35'),('80770516-d941-4512-baaf-3ddc3afa1a4d','qwe','​qwe​e​qwe',0,'2017-09-08 22:17:20','删除待审批',NULL,NULL,NULL,'qwe','2017-09-08 22:17:20'),('81587444-be5b-4fe5-b488-aff63bc6b339','起床','睡懒觉',0,'2017-09-07 09:24:58','不通过','123456','2017-09-07 09:41:54','不常用','66666','2017-09-05 14:46:55'),('82948a47-c031-41b1-ab03-8e3a9fcd6441','阿阿萨','<p id=\"aa\"><p><b><font color=\"#c24f4a\">​啊啊啊啊</font></b></p></p><p><br></p>',2,'2017-09-08 23:07:57','删除待审批','admin','2017-09-08 22:33:48','不错','aasa','2017-09-08 22:27:03'),('841dee74-df5c-498b-b826-e7133e16e1f5','aaaabbbccc','<p><font><font>一个aaa</font></font></p>',0,'2017-09-11 11:42:07','录入待审批',NULL,NULL,NULL,'','2017-09-11 11:42:07'),('9','它好','你也好',3,'2017-08-24 17:25:07','删除待审批','2222','2017-09-07 12:06:30','不常用','44235','2017-08-24 17:25:35'),('93722ec2-80a5-445a-8ab5-ba0b3cc7521f','中国','<p id=\"aa\"><h2><font color=\"#c24f4a\">中国</font>时光网</h2></p><p><br></p>',2,'2017-09-08 23:28:10','编辑待审批',NULL,NULL,NULL,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-08 22:47:25'),('a00b83f9-da0e-48bb-b71e-2e1c682f6559','asdasasd','<p id=\"aa\"><h2><b style=\"\">​qweqqweqw</b></h2></p><p><br></p>',0,'2017-09-08 22:24:28','删除待审批','bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-08 22:49:37','不错','qwqe ','2017-09-08 22:24:28'),('a24a88b8-0f84-47ef-bc36-80952e3319be','夜晚','有星星',0,NULL,'录入待审批',NULL,NULL,NULL,'11111','2017-09-07 09:30:44'),('a6c68694-66ef-4fbb-9de6-042ddcf34cf4','白天','有太阳',0,'2017-09-07 09:24:58','删除待审批','','2017-09-06 23:42:32','不错','121','2017-09-05 10:39:34'),('b90af209-8145-44b4-a387-a12fd4409d88','李刚','<blockquote>请千万千瓦<b>​请问企鹅恰恰为请问</b></blockquote>',0,'2017-09-10 11:52:57','录入待审批',NULL,NULL,NULL,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-10 11:52:57'),('c61193ff-9f4c-4696-a20d-44529fa57be4','手机','电脑',0,'2017-09-07 13:41:44','录入待审批',NULL,NULL,NULL,'2344','2017-09-07 13:41:44'),('cacbd6ae-eebc-49c0-93b7-304fd4252089','你是谁','机器人',3,'2017-09-07 17:51:43','录入待审批',NULL,NULL,NULL,'54232','2017-09-07 13:47:39');
/*!40000 ALTER TABLE `tb_knowledge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_resource`
--

DROP TABLE IF EXISTS `tb_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_resource` (
  `id` varchar(36) NOT NULL COMMENT '资源id',
  `sParentId` varchar(36) DEFAULT NULL COMMENT '资源父id',
  `sName` varchar(100) NOT NULL COMMENT '资源名称',
  `sType` int(11) NOT NULL COMMENT '资源类型',
  `sUrl` varchar(500) DEFAULT NULL COMMENT '资源url',
  `sIcon` varchar(100) DEFAULT NULL COMMENT '图标',
  `sIndex` int(11) DEFAULT NULL COMMENT '资源排序',
  `deleteStatus` int(11) NOT NULL DEFAULT '1' COMMENT '逻辑删除状态',
  `createUserId` varchar(36) NOT NULL COMMENT '创建人id',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_resource`
--

LOCK TABLES `tb_resource` WRITE;
/*!40000 ALTER TABLE `tb_resource` DISABLE KEYS */;
INSERT INTO `tb_resource` VALUES ('1aa1e266-1736-4f3f-ad82-847ea98a898e','s01','测试资源',1,'','haha',NULL,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-08 17:28:02'),('3e1b89dd-f205-41db-90d2-2b8a0187e5da','s02','知识添加',1,'/kno/addKnowledge.form','',NULL,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-08 17:37:55'),('a1307c01-19d6-4d1c-a48b-0a5be9c90ab9','s02','知识审批批',1,'/kno/knowledgeApprova.form','',NULL,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-08 17:38:30'),('b9a07306-af09-4b56-abab-c5a056254fb3','s02','知识编辑',1,'/kno/knowledgeEdit.form','',NULL,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-08 17:38:12'),('c659b0c7-74ad-437c-85fc-01a5c3055be1','s02','知识管理',0,'/kno/selectPage.form','',NULL,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-08 17:36:53'),('ca50a43e-1751-4892-8d9c-49523afb4297','s02','知识审批',0,'/kno/selectPage2.form','',NULL,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-08 17:37:29'),('ca50a43e-3551-4862-ad9c-49523afb4297','s02','知识删除',1,'/kno/knowledgeDelete.form',NULL,NULL,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-16 10:55:27'),('ca50a43e-3751-4862-8d9c-49523afb4297','s02','知识查询',1,'/kno/queryKnowledgeById.form',NULL,NULL,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-16 10:55:27'),('s01',NULL,'系统管理',0,NULL,'icon-dashboard',1,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-06 17:57:04'),('s02',NULL,'知识库',0,NULL,'icon-dashboard',2,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-06 17:57:04'),('s03','s01','用户管理',0,'/user/query.form',NULL,1,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-06 17:57:04'),('s04','s01','角色管理',0,'/role/queryList.form',NULL,2,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-06 17:57:04'),('s05','s01','资源管理',0,'/resource/query.form',NULL,3,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-06 17:57:04'),('s06','s02','知识搜索',0,'/repo/',NULL,1,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-06 17:57:04'),('s07','s03','新建用户',1,'/user/add.form',NULL,1,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-06 17:57:04'),('s08','s03','修改用户',1,'/user/userUpdate/',NULL,1,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-06 17:57:04'),('s09','s03','删除用户',1,'/user/delete.form',NULL,1,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-06 17:57:04'),('s10','s05','新建资源',1,'/resource/resourceAdd/',NULL,1,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-06 17:57:04'),('s11','s05','修改资源',1,'/resource/resourceUpdate/',NULL,1,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-06 17:57:04'),('s12','s05','删除资源',1,'/resource/delete.form',NULL,1,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-06 17:57:04'),('s13','s04','新建角色',1,'/role/add.form',NULL,1,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-06 17:57:04'),('s14','s04','修改角色',1,'/role/roleUpdate/',NULL,1,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-06 17:57:04'),('s15','s04','删除角色',1,'/role/delete.form',NULL,1,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-06 17:57:04'),('s16','s03','设置用户角色',1,'/user/userRole/',NULL,1,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-10 20:34:03'),('s17','s04','角色授权',1,'/role/roleAuth/',NULL,1,1,'bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-11 01:55:27');
/*!40000 ALTER TABLE `tb_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_resources_role`
--

DROP TABLE IF EXISTS `tb_resources_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_resources_role` (
  `sId` varchar(36) NOT NULL COMMENT '资源id',
  `rId` varchar(36) NOT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限映射表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_resources_role`
--

LOCK TABLES `tb_resources_role` WRITE;
/*!40000 ALTER TABLE `tb_resources_role` DISABLE KEYS */;
INSERT INTO `tb_resources_role` VALUES ('s01','r2'),('s03','r2'),('s07','r2'),('s08','r2'),('s09','r2'),('s04','1ee05a23-af17-45b2-a6e6-b1ee9115122b'),('s14','1ee05a23-af17-45b2-a6e6-b1ee9115122b'),('s15','1ee05a23-af17-45b2-a6e6-b1ee9115122b'),('s05','1ee05a23-af17-45b2-a6e6-b1ee9115122b'),('s10','1ee05a23-af17-45b2-a6e6-b1ee9115122b'),('s11','1ee05a23-af17-45b2-a6e6-b1ee9115122b'),('s12','1ee05a23-af17-45b2-a6e6-b1ee9115122b'),('s01','r2'),('s03','r2'),('s07','r2'),('s08','r2'),('s09','r2'),('s04','1ee05a23-af17-45b2-a6e6-b1ee9115122b'),('s14','1ee05a23-af17-45b2-a6e6-b1ee9115122b'),('s15','1ee05a23-af17-45b2-a6e6-b1ee9115122b'),('s05','1ee05a23-af17-45b2-a6e6-b1ee9115122b'),('s10','1ee05a23-af17-45b2-a6e6-b1ee9115122b'),('s11','1ee05a23-af17-45b2-a6e6-b1ee9115122b'),('s12','1ee05a23-af17-45b2-a6e6-b1ee9115122b'),('s01','r1'),('1aa1e266-1736-4f3f-ad82-847ea98a898e','r1'),('s03','r1'),('ca50a43e-3551-4862-ad9c-49523afb4297','r1'),('ca50a43e-3751-4862-8d9c-49523afb4297','r1'),('s07','r1'),('s08','r1'),('s09','r1'),('s16','r1'),('s04','r1'),('s13','r1'),('s14','r1'),('s15','r1'),('s17','r1'),('s05','r1'),('s10','r1'),('s11','r1'),('s12','r1'),('s02','r1'),('3e1b89dd-f205-41db-90d2-2b8a0187e5da','r1'),('a1307c01-19d6-4d1c-a48b-0a5be9c90ab9','r1'),('b9a07306-af09-4b56-abab-c5a056254fb3','r1'),('c659b0c7-74ad-437c-85fc-01a5c3055be1','r1'),('ca50a43e-1751-4892-8d9c-49523afb4297','r1'),('s06','r1');
/*!40000 ALTER TABLE `tb_resources_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role`
--

DROP TABLE IF EXISTS `tb_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_role` (
  `id` varchar(36) NOT NULL COMMENT '角色id',
  `rName` varchar(50) NOT NULL COMMENT '角色名称',
  `deleteStatus` int(11) NOT NULL DEFAULT '1' COMMENT '角色状态',
  `rDescription` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `createUserId` varchar(36) NOT NULL COMMENT '创建人id',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role`
--

LOCK TABLES `tb_role` WRITE;
/*!40000 ALTER TABLE `tb_role` DISABLE KEYS */;
INSERT INTO `tb_role` VALUES ('1ee05a23-af17-45b2-a6e6-b1ee9115122b','测试角色',1,'测试','bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-11 03:05:02'),('r1','admin',1,'rouro','bdccb13c-db1e-432c-83eb-6731dd05759d','2017-09-07 11:40:16'),('r2','asd2',1,'rouroururou','sdf','2017-09-07 11:40:16');
/*!40000 ALTER TABLE `tb_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role_user`
--

DROP TABLE IF EXISTS `tb_role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_role_user` (
  `rId` varchar(36) NOT NULL COMMENT '角色id',
  `uId` varchar(36) NOT NULL COMMENT '用户id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色映射表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role_user`
--

LOCK TABLES `tb_role_user` WRITE;
/*!40000 ALTER TABLE `tb_role_user` DISABLE KEYS */;
INSERT INTO `tb_role_user` VALUES ('f28165f3-3f8f-4e46-bf0d-0d53167a47d6','e64db4d0-d85d-4020-b390-942bf0a8a45b'),('r1','e64db4d0-d85d-4020-b390-942bf0a8a45b'),('r2','e64db4d0-d85d-4020-b390-942bf0a8a45b'),('f28165f3-3f8f-4e46-bf0d-0d53167a47d6','bdccb13c-db1e-432c-83eb-6731dd05759d'),('r1','bdccb13c-db1e-432c-83eb-6731dd05759d'),('1ee05a23-af17-45b2-a6e6-b1ee9115122b','e5af514d-7342-42ed-8081-cec6c85c5564');
/*!40000 ALTER TABLE `tb_role_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `id` varchar(36) NOT NULL COMMENT '用户id',
  `uName` varchar(100) NOT NULL COMMENT '账户名称',
  `uPassword` varchar(100) NOT NULL COMMENT '用户密码',
  `deleteStatus` int(11) NOT NULL DEFAULT '1' COMMENT '逻辑删除状态',
  `uDescription` varchar(200) DEFAULT NULL COMMENT '用户描述',
  `createUserId` varchar(36) DEFAULT NULL COMMENT '创建人id',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `uLastOnLine` datetime DEFAULT NULL COMMENT '最后上线时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tb_user_uName_uindex` (`uName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES ('1','aa','aa',1,'1','1','2017-09-08 21:43:07',NULL),('2329074a-6b44-4ca0-8bee-10d819722d7f','admin222','123123',1,'',NULL,'2017-09-08 00:48:12',NULL),('a5104e64-bfea-4b08-b4c9-1fcd482c516d','zzzzzz','123123',1,'ww',NULL,'2017-09-08 00:42:07',NULL),('bdccb13c-db1e-432c-83eb-6731dd05759d','admin','123123',1,'',NULL,'2017-09-07 11:13:05',NULL),('e64db4d0-d85d-4020-b390-942bf0a8a45b','admin333','123123',1,'',NULL,'2017-09-08 00:51:59',NULL);
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-11 11:57:35
