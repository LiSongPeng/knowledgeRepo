-- MySQL dump 10.13  Distrib 5.7.19, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: knowledgeRepo
-- ------------------------------------------------------
-- Server version	5.7.19-0ubuntu0.16.04.1
CREATE SCHEMA `knowledgeRepo` CHARACTER SET 'utf8';
USE `knowledgeRepo`;
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
  `kUserTimeLast` datetime NOT NULL COMMENT '最后一次使用时间',
  `kApprStatus` varchar(10) NOT NULL COMMENT '审批状态',
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

-- Dump completed on 2017-08-26 11:34:28
