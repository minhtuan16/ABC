-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: shop_shoes
-- ------------------------------------------------------
-- Server version	8.0.28

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

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `id` int NOT NULL,
  `buy_date` datetime(6) DEFAULT NULL,
  `coupon_code` varchar(255) DEFAULT NULL,
  `discount` int NOT NULL,
  `total_payment` double NOT NULL,
  `total_price` double NOT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqhq5aolak9ku5x5mx11cpjad9` (`user_id`),
  CONSTRAINT `FKqhq5aolak9ku5x5mx11cpjad9` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,'2022-09-10 00:00:00.000000','giam50',50,4720,9440,7),(2,'2022-10-01 00:00:00.000000','giam50',50,50,100,10);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billitems`
--

DROP TABLE IF EXISTS `billitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billitems` (
  `id` int NOT NULL,
  `buy_price` double NOT NULL,
  `quantity` int NOT NULL,
  `bill_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfgejd55ihyxg01thtb3grc2tn` (`bill_id`),
  KEY `FKje2we04wvb4i5un4cjqpjauwb` (`product_id`),
  CONSTRAINT `FKfgejd55ihyxg01thtb3grc2tn` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`),
  CONSTRAINT `FKje2we04wvb4i5un4cjqpjauwb` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billitems`
--

LOCK TABLES `billitems` WRITE;
/*!40000 ALTER TABLE `billitems` DISABLE KEYS */;
INSERT INTO `billitems` VALUES (1,0,20,1,6),(2,0,10,2,6);
/*!40000 ALTER TABLE `billitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartitem`
--

DROP TABLE IF EXISTS `cartitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartitem` (
  `product_id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `qty` int NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartitem`
--

LOCK TABLES `cartitem` WRITE;
/*!40000 ALTER TABLE `cartitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `cartitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_46ccwnsi9409t36lurvtyljak` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (18,'ÁO KHOÁC NAM'),(35,'ÁO KHOÁC NỮ'),(15,'ÁO POLO NAM'),(17,'ÁO SƠ MI NAM'),(34,'ÁO SƠ MI NỮ'),(16,'ÁO THUN NAM'),(33,'ÁO THUN NỮ'),(19,'ÁO VEST NAM'),(26,'BĂNG ĐÔ'),(28,'CÀ VẠT'),(40,'CHÂN VÁY'),(41,'ĐẦM NỮ'),(30,'ĐỒ LÓT'),(32,'GIÀY DÉP'),(27,'KHẨU TRANG'),(25,'MŨ / NÓN'),(20,'QUẦN JEAN NAM'),(36,'QUẦN JEAN NỮ'),(21,'QUẦN KAKI NAM'),(37,'QUẦN KAKI NỮ'),(24,'QUẦN SHORT NAM'),(39,'QUẦN SHORT NỮ'),(22,'QUẦN VẢI NAM'),(38,'QUẦN VẢI NỮ'),(29,'TÚI / BALO'),(31,'VỚ');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon` (
  `id` int NOT NULL,
  `coupon_code` varchar(255) DEFAULT NULL,
  `discount_amount` int NOT NULL,
  `expired_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sre2vcap4vs6qucaksomk3c7s` (`coupon_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
INSERT INTO `coupon` VALUES (1,'giam50',50,'2022-12-12 00:00:00.000000'),(2,'giam30',30,'2022-12-12 00:00:00.000000');
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `imageurl` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `category_id` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `ten_sp` varchar(255) DEFAULT NULL,
  `ma_sp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hbfd76ihif4i7s6156a6h1rh2` (`ten_sp`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (6,'10s21pol008---bright-white-2_1.jpg',472,15,'Kiểu dáng/Form : Fitted ;  Họa tiết : Trơn','ÁO POLO NAM NGẮN TAY, CÓ TÚI. FORM OVERSIZE - 10S21POL008','10S21POL008'),(7,'10f21pol002---medieval-blue-3_1.jpg',413,15,'Kiểu dáng/Form : Fitted ; Chất liệu : Modal ; Họa tiết : Trơn','ÁO POLO NGẮN TAY. FITTED - 10F21POL002','10F21POL002'),(8,'10s22pol046_-_mint_2__1_1.jpg',472,15,'Kiểu dáng/Form : Fitted ; Chất liệu : Cotton ; Họa tiết : Kẻ sọc ; Kiểu tay : Tay ngắn ; Kiểu cổ : Cổ bẻ','ÁO POLO KẺ SỌC CỔ BẺ COTTON FORM FITTED - 10S22POL046','10S22POL046'),(9,'10s22pol045_-_mushroom_1__2.jpg',472,15,'Kiểu dáng/Form : Fitted ; Chất liệu : Cotton ; Họa tiết : Trơn ; Kiểu tay : Tay ngắn ; Kiểu cổ: Cổ V','ÁO POLO CỔ V COTTON FORM SLIM - 10S22POL045','10S22POL045'),(11,'10s22pol041-_black_3__1_1.jpg',472,15,'Kiểu dáng/Form : Fitted ; Chất liệu : Cotton ; Họa tiết : Phối màu ; Kiểu tay : Tay ngắn ','ÁO POLO TAY BO COTTON FORM SLIM - 10S22POL041','10S22POL041'),(12,'10s22pol038_-_flint_stone_2__1_1.jpg',472,15,'Kiểu dáng/Form : Fitted ; Chất liệu : Cotton ; Họa tiết : Phối màu ; Kiểu tay : Tay ngắn ','ÁO POLO PHỐI MÀU COTTON FORM FITTED - 10S22POL038','10S22POL038'),(13,'10s21pol001cr1_-_sky_captain_2__1_1_1.jpg',344,15,'Kiểu dáng/Form : Fitted ; Họa tiết : Trơn ; Kiểu tay : Tay ngắn ','ÁO POLO FORM FITTED - 10S21POL001CR1','10S21POL001CR1'),(14,'10f21pol037_-_fitted_yellow_4__1_1.jpg',249,15,'Kiểu dáng/Form : Fitted ; Chất liệu : Wool ; Họa tiết : Trơn ; Kiểu tay : Tay ngắn  ','ÁO POLO COTTON FORM SLIM - 10F21POL037','10F21POL037'),(15,'10f21pol010-brown_white-1_1.jpg',249,15,'Kiểu dáng/Form : Fitted ; Chất liệu : Wool ; Họa tiết : Trơn ; Kiểu tay : Tay ngắn ','ÁO POLO COTTON FORM SLIM - 10F21POL010','10F21POL010'),(16,'10f21pol027c-v_ng.1_1.jpg',442,15,'Kiểu dáng/Form : Fitted ; Chất liệu : Cotton ; Họa tiết : Thêu ; Kiểu tay : Tay ngắn ','ÁO POLO THÊU COTTON FORM FITTED - 10F21POL027C','10F21POL027C'),(17,'10f20pol021r-chipmunk.1_1.jpg',374,15,'Kiểu dáng/Form : Fitted ; Họa tiết : Trơn ; Kiểu tay : Tay ngắn ','ÁO POLO CỔ V FORM FITTED - 10F20POL021R','10F20POL021R'),(18,'mausac_black_10s21pol001c_2__1.jpg',344,15,'Kiểu dáng/Form : Fitted ; Chất liệu : Cotton ; Họa tiết : Trơn ; Kiểu tay : Tay ngắn','ÁO POLO COTTON FORM FITTED - 10S21POL001C','10S21POL001C'),(19,'10s21pol005-tag_ko_co_ten_mau.x1_1.jpg',249,15,'Kiểu dáng/Form : Fitted ; Họa tiết : Phối màu ; Kiểu tay : Tay ngắn','ÁO POLO PHỐI MÀU FORM FITTED - 10S21POL005','10S21POL005'),(20,'10s21pol031-white.1_1.jpg',199,15,'Kiểu dáng/Form : Fitted ; Chất liệu : Cotton ; Họa tiết : Phối màu ; Kiểu tay : Tay ngắn ','ÁO POLO PHỐI CỔ COTTON FORM FITTED - 10S21POL031','10S21POL031'),(21,'10s21pol024-black.1_1.jpg',442,15,'Kiểu dáng/Form : Fitted ; Họa tiết : Trơn ; Kiểu tay : Tay ngắn','ÁO POLO COTTON FORM FITTED - 10S21POL024','10S21POL024'),(22,'10s21pol012-yellow.1_1_1.jpg',249,15,'Kiểu dáng/Form : Fitted ; Chất liệu : Cotton ; Họa tiết : Kẻ sọc ; Kiểu tay : Tay ngắn','ÁO POLO KẺ SỌC COTTON FORM FITTED - 10S21POL012','10S21POL012'),(23,'10f20pol008.2_1.jpg',413,15,'Kiểu dáng/Form : Fitted ; Chất liệu : Cotton ; Họa tiết : Phối màu ; Kiểu tay : Tay ngắn ','ÁO POLO TRƠN COTTON FORM FITTED - 10F21POL022','10F21POL022');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_size`
--

DROP TABLE IF EXISTS `product_size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_size` (
  `id` int NOT NULL AUTO_INCREMENT,
  `so_luong` bigint DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `size_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8i3jm2ctt0lsyeik2wt76yvv0` (`product_id`),
  KEY `FKnlkh5xcjuopsnoimdvmumioia` (`size_id`),
  CONSTRAINT `FK8i3jm2ctt0lsyeik2wt76yvv0` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKnlkh5xcjuopsnoimdvmumioia` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_size`
--

LOCK TABLES `product_size` WRITE;
/*!40000 ALTER TABLE `product_size` DISABLE KEYS */;
INSERT INTO `product_size` VALUES (1,10,6,1),(2,10,6,2),(3,10,6,3),(4,10,6,4),(5,10,6,5),(6,10,6,6),(7,10,7,1),(8,10,7,2),(9,10,7,3),(10,10,7,4),(11,10,7,5),(12,10,7,6),(13,10,8,1),(14,10,8,2),(15,10,8,3),(16,10,8,4),(17,10,8,5),(18,10,8,6),(19,10,9,1),(20,10,9,2),(21,10,9,3),(22,10,9,4),(23,10,9,5),(24,10,9,6),(25,10,11,1),(26,10,11,2),(27,10,11,3),(28,10,11,4),(29,10,11,5),(30,10,11,6),(31,10,12,1),(32,10,12,2),(33,10,12,3),(34,10,12,4),(35,10,12,5),(36,10,12,6),(37,10,13,1),(38,10,13,2),(39,10,13,3),(40,10,13,4),(41,10,13,5),(42,10,13,6),(43,10,14,1),(44,10,14,2),(45,10,14,3),(46,10,14,4),(47,10,14,5),(48,10,14,6),(49,10,15,1),(50,10,15,2),(51,10,15,3),(52,10,15,4),(53,10,15,5),(54,10,15,6),(55,10,16,1),(56,10,16,2),(57,10,16,3),(58,10,16,4),(59,10,16,5),(60,10,16,6),(61,10,17,1),(62,10,17,2),(63,10,17,3),(64,10,17,4),(65,10,17,5),(66,10,17,6),(67,10,18,1),(68,10,18,2),(69,10,18,3),(70,10,18,4),(71,10,18,5),(72,10,18,6),(73,10,19,1),(74,10,19,2),(75,10,19,3),(76,10,19,4),(77,10,19,5),(78,10,19,6),(79,10,20,1),(80,10,20,2),(81,10,20,3),(82,10,20,4),(83,10,20,5),(84,10,20,6),(85,10,21,1),(86,10,21,2),(87,10,21,3),(88,10,21,4),(89,10,21,5),(90,10,21,6),(91,10,22,1),(92,10,22,2),(93,10,22,3),(94,10,22,4),(95,10,22,5),(96,10,22,6),(97,10,23,1),(98,10,23,2),(99,10,23,3),(100,10,23,4),(101,10,23,5),(102,10,23,6);
/*!40000 ALTER TABLE `product_size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `size`
--

DROP TABLE IF EXISTS `size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `size` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name_size` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size`
--

LOCK TABLES `size` WRITE;
/*!40000 ALTER TABLE `size` DISABLE KEYS */;
INSERT INTO `size` VALUES (1,'XS'),(2,'S'),(3,'M'),(4,'L'),(5,'XL'),(6,'XXL');
/*!40000 ALTER TABLE `size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL,
  `mail_user` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'minhtuantran1621@gmail.com','$2a$10$UbV2RlCoGYdpyuiExNE5DuuM/bksaJjRArxfKpxGjBsYALt1Kiiue','u33'),(4,'minhtuantran1621@gmail.com','$2a$10$QCDGQfYFPCF2WFhuVMaxa.wEtNW7VnOU3Wvp/axGNZbptV9B2mjJq','u44'),(6,'minhtuantran1621@gmail.com','$2a$10$iTQYMt1cH0LAJErrOXpA2umuq8zFl16tvBu.LefCmUlddWk767Z3y','admin17'),(7,'minhtuantran1621@gmail.com','$2a$10$79Wz9CZeyeVtHHpSK4miD.grRmkKb9GbdQeS/reWw1ua3QN605DrS','admin(admin)'),(8,'minhtuantran1621@gmail.com','$2a$10$39puXTcMyRR4zs90jZKxQOi4oSQrSbSOniUVVlkgPp/jzM6l.qrE6','Minh'),(9,'minhtuantran162k1@gmail.com','$2a$10$g1FhYUA00BsSuDuU7a3x4.6SG6BCIuDLZI2IirV9n3d6ZBXURuSPy','Tuan(123)'),(10,'minhtuantran1621@gmail.com','$2a$10$csW1eGQ8Q4Qj46ZiXZODrOObuk4CiZzsZ4YkE9FnZ5VZ01PoTahJO','a123(a123)'),(13,'minhtuantran1621@gmail.com','$2a$10$i67rwvAuK1RPHS3kOK3uJOl58TrELxh7HfrQi9mBKuIFaNZA/eeYG','a33(a33)'),(14,'minhtuantran162k1@gmail.com','$2a$10$DKKbW2VPLB5o273ZgAri5.sj13wpCD2LBE4QM/9cLAl723TaQkJzu','a44(a44)');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` int NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (3,'ROLE_Member'),(4,'ROLE_Member'),(6,'ROLE_Admin'),(8,'ROLE_Khach_hang'),(9,'ROLE_Khach_hang'),(7,'ROLE_Admin'),(10,'ROLE_Admin'),(13,'ROLE_Khach_hang'),(14,'ROLE_Khach_hang');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-20  6:51:19
