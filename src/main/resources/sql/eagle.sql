-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: niu-n6920.mysql.rds.aliyuncs.com    Database: eagle_eye
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '19246ed5-81ef-11ea-bdc6-00163e165220:1-550013';

--
-- Table structure for table `abtesting`
--

DROP TABLE IF EXISTS `abtesting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `abtesting` (
  `service_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '服务名称',
  `active` tinyint(4) NOT NULL COMMENT '状态 1: 禁用 2: 启用',
  `endpoint` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '转发地址',
  `weight` int(11) NOT NULL COMMENT '权重',
  `target_service_name` varchar(100) NOT NULL COMMENT '目标服务',
  `current_version` varchar(10) NOT NULL COMMENT '当前版本',
  `target_version` varchar(10) DEFAULT NULL COMMENT '目标版本',
  PRIMARY KEY (`service_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abtesting`
--

LOCK TABLES `abtesting` WRITE;
/*!40000 ALTER TABLE `abtesting` DISABLE KEYS */;
INSERT INTO `abtesting` VALUES ('organizationservice',2,'http://127.0.0.1:7023',5,'organizationservicev2','v1','v2');
/*!40000 ALTER TABLE `abtesting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `license`
--

DROP TABLE IF EXISTS `license`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `license` (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '许可证ID',
  `organization_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '机构ID',
  `product_name` varchar(50) DEFAULT NULL COMMENT '产品名称',
  `license_type` varchar(10) DEFAULT NULL COMMENT '许可证类型',
  `license_max` int(11) DEFAULT NULL COMMENT '最大许可证数量',
  `license_allocated` int(11) DEFAULT NULL COMMENT '已分配许可证数量',
  `comment` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `license`
--

LOCK TABLES `license` WRITE;
/*!40000 ALTER TABLE `license` DISABLE KEYS */;
INSERT INTO `license` VALUES ('225510f35e884fefbd28bfbccdda8c72','cd3d65167be749b08a9fe36f9079a1c1','产品17','org',117,27,'默认许可证服务配置'),('2cca943dcddd49f49810101f629f1f01','9d02646467fd40d280610bc8649026c6','产品15','org',115,25,'默认许可证服务配置'),('31afbb0a9d5d4c1cb600a4d81098268b','241d89bf19224ea3a20d0aa5be57eedb','产品3','official',103,13,'默认许可证服务配置'),('39f19e14d44845a4933fa5531e06901d','252af013132942e7be399fd8ae9ae17e','产品4','official',104,14,'默认许可证服务配置'),('3b461a338bdc4993971832dae05f30dd','a9b30ca6ae4b4ad3a7413e9d9348ba23','产品16','org',116,26,'默认许可证服务配置'),('3eb858a472a740ae97f793d87b220895','1aacd2b81dbd449784fc19189f9e90ad','产品2','org',102,12,'默认许可证服务配置'),('4379c8701b5c4b5cb4097742f469baaa','29d2e5347c9b459cbe455caa7cee9f51','产品5','official',105,15,'默认许可证服务配置'),('4ad6ee1807264659b1ab76a22a91d340','935d48d42ba54b568975089a6fa6ac54','产品13','official',113,23,'默认许可证服务配置'),('4e57f0a947274dc288a087b9656cd905','99156721e380457eacf811245a68abb3','产品14','org',114,24,'默认许可证服务配置'),('6eb410b0b33c408693e0b9801e5aa979','381d9fbccbb04510b953ff42286d747b','产品7','personal',107,17,'默认许可证服务配置'),('870eac3728464a61a5c5fb88f94aff8c','5a152ca384524d7eb29016715f08bd75','产品10','org',110,20,'默认许可证服务配置'),('8e3bff1516754fdf96350bcc1aa03f56','75a7e0a0153c44e2a37ca37c480dd563','产品12','org',112,22,'默认许可证服务配置'),('91476509e80b46abb3cfa3f09220988c','2e7f9377c0294a85b40468204885f8de','产品6','org',106,16,'默认许可证服务配置'),('b9a18b9ee440477fb876c84946a026e5','73a22dcb7ffb4f63b1c09b0ad23e4d4f','产品11','personal',111,21,'默认许可证服务配置'),('bc35a97542154d5cb3668da672ae5773','0a891b2e6f5d4be59b6ecc2bd7b52378','产品1','personal',101,11,'默认许可证服务配置'),('d4fa3209e1804d888c55cb410b52c909','e450cea664bf4f03a81eb93394522cfc','产品19','org',119,29,'默认许可证服务配置'),('d638cac5bf2c404ebdc3e1d18c7ca81e','582063acb3cf4a7cb1486696bbfe07e5','产品8','personal',108,18,'默认许可证服务配置'),('e25b2abf27ce4c74a4948b0dbee16cef','057a597b70954b00878344507e16caec','产品0','org',100,10,'默认许可证服务配置'),('ec1810c917164d6bb89a73f2d3742864','e39bbaa74b25401fbf046453161274f2','产品18','org',118,28,'默认许可证服务配置'),('f38f4b5efb934aa09dc3887090585a5a','59963fc902a749be8ac33eba03e17170','产品9','org',109,19,'默认许可证服务配置');
/*!40000 ALTER TABLE `license` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `organization` (
  `id` char(32) NOT NULL COMMENT '机构ID',
  `name` varchar(50) NOT NULL COMMENT '机构名称',
  `contact_name` varchar(50) DEFAULT NULL COMMENT '联系人',
  `contact_email` varchar(50) DEFAULT NULL COMMENT '联系邮箱',
  `contact_phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization`
--

LOCK TABLES `organization` WRITE;
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
INSERT INTO `organization` VALUES ('057a597b70954b00878344507e16caec','机构11','联系人11','51868@google.com','16314749557'),('0a891b2e6f5d4be59b6ecc2bd7b52378','机构8','联系人8','97181@google.com','59813814669'),('0b4f9a8ea114460d839aacf88c9b54bb','小西牛乳业24','李先生24','1223@xiaoxiniu.com','19988778229'),('0b872b9fb44345e2b7a822fb4962af2c','小西牛乳业4','李先生4','1223@xiaoxiniu.com','19988778229'),('1aacd2b81dbd449784fc19189f9e90ad','机构19','联系人19','97305@google.com','00783319070'),('22eec72c1f7d4b7bb9383cd3a6c5101c','小西牛乳业4','李先生4','1223@xiaoxiniu.com','19988778229'),('241d89bf19224ea3a20d0aa5be57eedb','机构2','联系人2','83624@google.com','79382786382'),('252af013132942e7be399fd8ae9ae17e','机构9','联系人9','24916@google.com','70253963681'),('29d2e5347c9b459cbe455caa7cee9f51','机构18','联系人18','30138@google.com','62606710008'),('2a2b81659c1e4a55b85cf60a24dd0a07','小西牛乳业1','李先生1','123@xiaoxiniu.com','19988778899'),('2bea97cb15c746f2a6145141b744ef04','小西牛乳业3','李先生3','1223@xiaoxiniu.com','19988778229'),('2e7f9377c0294a85b40468204885f8de','机构5','联系人5','92982@google.com','02898905936'),('381d9fbccbb04510b953ff42286d747b','机构17','联系人17','41648@google.com','67207534544'),('582063acb3cf4a7cb1486696bbfe07e5','机构13','联系人13','16115@google.com','50990568096'),('59963fc902a749be8ac33eba03e17170','机构6','联系人6','08241@google.com','81649576013'),('5a152ca384524d7eb29016715f08bd75','机构20','联系人20','42665@google.com','27110286896'),('614920e68624488f99621474db8c525f','小西牛乳业2','李先生2','1223@xiaoxiniu.com','19988778229'),('6237abafd8a14a3abb7758b84e71d1fd','小西牛乳业4','李先生4','1223@xiaoxiniu.com','19988778229'),('661a6436df9d44e5ac13ec4a2b11674f','小西牛乳业24','李先生24','1223@xiaoxiniu.com','19988778229'),('6bc75a5325b2470289d8167b77458dcd','小西牛乳业24','李先生24','1223@xiaoxiniu.com','19988778229'),('73a22dcb7ffb4f63b1c09b0ad23e4d4f','机构12','联系人12','96160@google.com','50114827530'),('75a7e0a0153c44e2a37ca37c480dd563','机构16','联系人16','08388@google.com','23381900413'),('7c21faa90ea84e42a17fe921dff3b283','小西牛乳业24','李先生24','1223@xiaoxiniu.com','19988778229'),('816e06a7c6ba417e82bf67ddb7a611f4','小西牛乳业24','李先生24','1223@xiaoxiniu.com','19988778229'),('8e376df1ce994174a47fd46a90412bc5','小西牛乳业','李先生','123@xiaoxiniu.com','19988778899'),('909df1fee73b4dba9d8e6067d6761349','小西牛乳业2','李先生2','1223@xiaoxiniu.com','19988778229'),('935d48d42ba54b568975089a6fa6ac54','机构7','联系人7','94945@google.com','70230960958'),('99156721e380457eacf811245a68abb3','机构15','联系人15','59720@google.com','61322901251'),('9d02646467fd40d280610bc8649026c6','机构4','联系人4','59124@google.com','70321721966'),('a9b30ca6ae4b4ad3a7413e9d9348ba23','机构3','联系人3','17141@google.com','87293671694'),('c28721d953224c31ba6b5f516fe8d409','小西牛乳业24','李先生24','1223@xiaoxiniu.com','19988778229'),('cc341a5b952e4414a5f9d4373544e14f','小西牛乳业2','李先生2','1223@xiaoxiniu.com','19988778229'),('cd3d65167be749b08a9fe36f9079a1c1','机构1','联系人1','96377@google.com','41938422435'),('e2322fe522d8408d9eb826aab469c8a8','小西牛乳业2','李先生2','1223@xiaoxiniu.com','19988778229'),('e39bbaa74b25401fbf046453161274f2','机构10','联系人10','22479@google.com','83728073979'),('e450cea664bf4f03a81eb93394522cfc','机构14','联系人14','62370@google.com','41981246162'),('ed843034e1f04524b4a0c5293b8c3677','小西牛乳业4','李先生4','1223@xiaoxiniu.com','19988778229'),('fb847c191f66486f96c8fef9b9aae1d8','小西牛乳业2','李先生2','1223@xiaoxiniu.com','19988778229');
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-21 22:04:39
-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: niu-n6920.mysql.rds.aliyuncs.com    Database: eagle_eye_prod
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '19246ed5-81ef-11ea-bdc6-00163e165220:1-550013';

--
-- Table structure for table `abtesting`
--

DROP TABLE IF EXISTS `abtesting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `abtesting` (
  `service_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '服务名称',
  `active` tinyint(4) NOT NULL COMMENT '状态 1: 禁用 2: 启用',
  `endpoint` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '转发地址',
  `weight` int(11) NOT NULL COMMENT '权重',
  PRIMARY KEY (`service_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abtesting`
--

LOCK TABLES `abtesting` WRITE;
/*!40000 ALTER TABLE `abtesting` DISABLE KEYS */;
/*!40000 ALTER TABLE `abtesting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `license`
--

DROP TABLE IF EXISTS `license`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `license` (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '许可证ID',
  `organization_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '机构ID',
  `product_name` varchar(50) DEFAULT NULL COMMENT '产品名称',
  `license_type` varchar(10) DEFAULT NULL COMMENT '许可证类型',
  `license_max` int(11) DEFAULT NULL COMMENT '最大许可证数量',
  `license_allocated` int(11) DEFAULT NULL COMMENT '已分配许可证数量',
  `comment` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `license`
--

LOCK TABLES `license` WRITE;
/*!40000 ALTER TABLE `license` DISABLE KEYS */;
/*!40000 ALTER TABLE `license` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `organization` (
  `id` char(32) NOT NULL COMMENT '机构ID',
  `name` varchar(50) NOT NULL COMMENT '机构名称',
  `contact_name` varchar(50) DEFAULT NULL COMMENT '联系人',
  `contact_email` varchar(50) DEFAULT NULL COMMENT '联系邮箱',
  `contact_phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization`
--

LOCK TABLES `organization` WRITE;
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-21 22:04:41
-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: niu-n6920.mysql.rds.aliyuncs.com    Database: eagle_eye_dev
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '19246ed5-81ef-11ea-bdc6-00163e165220:1-550013';

--
-- Table structure for table `abtesting`
--

DROP TABLE IF EXISTS `abtesting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `abtesting` (
  `service_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '服务名称',
  `active` tinyint(4) NOT NULL COMMENT '状态 1: 禁用 2: 启用',
  `endpoint` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '转发地址',
  `weight` int(11) NOT NULL COMMENT '权重',
  PRIMARY KEY (`service_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abtesting`
--

LOCK TABLES `abtesting` WRITE;
/*!40000 ALTER TABLE `abtesting` DISABLE KEYS */;
/*!40000 ALTER TABLE `abtesting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `license`
--

DROP TABLE IF EXISTS `license`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `license` (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '许可证ID',
  `organization_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '机构ID',
  `product_name` varchar(50) DEFAULT NULL COMMENT '产品名称',
  `license_type` varchar(10) DEFAULT NULL COMMENT '许可证类型',
  `license_max` int(11) DEFAULT NULL COMMENT '最大许可证数量',
  `license_allocated` int(11) DEFAULT NULL COMMENT '已分配许可证数量',
  `comment` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `license`
--

LOCK TABLES `license` WRITE;
/*!40000 ALTER TABLE `license` DISABLE KEYS */;
/*!40000 ALTER TABLE `license` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `organization` (
  `id` char(32) NOT NULL COMMENT '机构ID',
  `name` varchar(50) NOT NULL COMMENT '机构名称',
  `contact_name` varchar(50) DEFAULT NULL COMMENT '联系人',
  `contact_email` varchar(50) DEFAULT NULL COMMENT '联系邮箱',
  `contact_phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization`
--

LOCK TABLES `organization` WRITE;
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-21 22:04:42
