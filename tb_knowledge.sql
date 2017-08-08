-- MySQL dump 10.13  Distrib 5.7.19, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: knowledgeRepo
-- ------------------------------------------------------
-- Server version	5.7.19-0ubuntu0.16.04.1

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
INSERT INTO `tb_knowledge` VALUES ('0d4e4bf7-2913-42cb-9082-091ec2c94bf1','234','asdfdsafasdfasd',4,'2017-09-07 11:08:16','?????','asfdas','2017-09-07 10:56:38','asdasdas','23432432','2017-09-07 10:55:05'),('12324234','isdfdsfsd','hello world',45,'2017-09-07 10:44:59','待审核','sdf','2017-08-26 15:42:01','dsfsd','sdfds','2017-08-26 15:42:16'),('abfgdsf','Hello world','23热风服务商撒旦法',14,'2017-09-07 10:53:39','?????','','2017-09-07 10:53:51','','Hello world','2017-09-07 10:20:17'),('dsf','dsfsdf','if you miss the train',14,'2017-09-07 20:35:32','待审核','dsf','2017-08-26 15:43:31','sf','sd','2017-08-26 15:43:41'),('hsdf','java修改错误','java抛出异常',91,'2017-09-07 20:29:21','录入待审批','sdf','2017-08-28 16:01:50','sdf','sdf','2017-08-28 16:01:57'),('s','git push有异常','git 进行push操作时候出现异常',31,'2017-09-07 14:44:11','录入待审批','sf','2017-08-28 16:02:43','sdf','sdfd','2017-08-28 16:02:53'),('sdf','你好','世界,中央,站在',9,'2017-09-07 10:19:45','待审核','sdf','2017-08-26 19:18:42','dsf','sdfsd','2017-08-26 19:18:53'),('vdfds','干什么','我会忍受所有的寂寞',4,'2017-09-07 09:55:28','待审核','sdf','2017-08-26 19:19:27','dsf','idfs','2017-08-26 19:19:38');
/*!40000 ALTER TABLE `tb_knowledge` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-08 12:42:25
