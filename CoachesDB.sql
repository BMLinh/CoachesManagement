CREATE DATABASE  IF NOT EXISTS `coachesmanagementdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `coachesmanagementdb`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: coachesmanagementdb
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1',
  `seat` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Giường nằm 45 chỗ',1,45),(2,'Giường nằm 42 chỗ',1,42),(3,'Giường nằm 30 chỗ',1,30),(4,'Ghế ngồi 45 chỗ',1,45),(5,'Ghế ngồi 40 chỗ',1,40),(6,'Ghế ngồi 15 chỗ',1,15),(7,'Ghế ngồi 25 chỗ',1,25);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coach`
--

DROP TABLE IF EXISTS `coach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coach` (
  `id` int NOT NULL AUTO_INCREMENT,
  `license_plates` varchar(20) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `coach_garage_id` int NOT NULL,
  `category_id` int NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `coach_station_idx` (`coach_garage_id`),
  KEY `coach_cagory_idx` (`category_id`),
  CONSTRAINT `coach_cagory` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `coach_coachGarage` FOREIGN KEY (`coach_garage_id`) REFERENCES `coach_garage` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coach`
--

LOCK TABLES `coach` WRITE;
/*!40000 ALTER TABLE `coach` DISABLE KEYS */;
INSERT INTO `coach` VALUES (1,'58HFGAF65B','Xe 45 chỗ nằm',1,1,1),(2,'18AH56GHT7','Xe 45 chỗ nằm',2,4,1),(3,'56AKH89TH6','Xe có điều hòa, tivi',1,6,1),(4,'56AKOP5TH6','Xe có điều hòa, tivi, chăn màn ngăn cách từng buồng',2,3,1),(5,'49F00056A','Tài xế có kinh ngiệm lâu năm',1,2,1);
/*!40000 ALTER TABLE `coach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coach_garage`
--

DROP TABLE IF EXISTS `coach_garage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coach_garage` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `owner` varchar(45) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  `district_id` int NOT NULL,
  `user_id` int NOT NULL,
  `contract` varchar(150) NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `coachGarage_user_idx` (`user_id`),
  KEY `coachGarage_district_idx` (`district_id`),
  CONSTRAINT `fk_coachgarage_district` FOREIGN KEY (`district_id`) REFERENCES `district` (`id`),
  CONSTRAINT `fk_coachgarage_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coach_garage`
--

LOCK TABLES `coach_garage` WRITE;
/*!40000 ALTER TABLE `coach_garage` DISABLE KEYS */;
INSERT INTO `coach_garage` VALUES (1,'Hải Triều','Minh Hiếu','0123456789','1951052099linh@gmail.com','12/8 Đường 6',11,2,'https://res.cloudinary.com/dd3tfkb7f/image/upload/v1666939936/bnspyw9nkpphlvhfoamh.png',1),(2,'Hoàng Minh','Kim Tuyết','0123435689','195105205hieu@gmail.com','350 phường 8',13,3,'https://res.cloudinary.com/dd3tfkb7f/image/upload/v1666939936/bnspyw9nkpphlvhfoamh.png',1),(3,'Kim Hằng','Kim Tuyết','0123435689','195105205hieu@gmail.com','75, Phường 8',29,3,'https://res.cloudinary.com/dd3tfkb7f/image/upload/v1666939936/bnspyw9nkpphlvhfoamh.png',1),(4,'Đinh Hoàng','Thành Long','0123456777','1951052099hieu@gmail.com','128/8 Đường 2',11,8,'',2),(5,'Hồng Ân','Hằng Nga','0123456777','1951052099hieu@gmail.com','110/ đường 8',23,9,'',2);
/*!40000 ALTER TABLE `coach_garage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coaches`
--

DROP TABLE IF EXISTS `coaches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coaches` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `description` varchar(150) DEFAULT NULL,
  `price` decimal(10,0) NOT NULL,
  `empty_seat` int NOT NULL,
  `is_shipping` bit(1) NOT NULL DEFAULT b'0',
  `create_date` datetime DEFAULT NULL,
  `coach_id` int NOT NULL,
  `start_point` int NOT NULL,
  `end_point` int NOT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_coaches_country_idx` (`start_point`),
  KEY `fk_coaches_country_idx1` (`end_point`),
  KEY `fk_coaches_coach_idx` (`coach_id`),
  CONSTRAINT `fk_coaches_coach` FOREIGN KEY (`coach_id`) REFERENCES `coach` (`id`),
  CONSTRAINT `fk_coaches_end_point` FOREIGN KEY (`end_point`) REFERENCES `country` (`id`),
  CONSTRAINT `fk_coaches_start_point` FOREIGN KEY (`start_point`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coaches`
--

LOCK TABLES `coaches` WRITE;
/*!40000 ALTER TABLE `coaches` DISABLE KEYS */;
INSERT INTO `coaches` VALUES (1,'2022-10-30 06:30:00','2022-11-01 07:45:00','Xe có phát nước và khăn',650000,45,_binary '','2022-10-29 11:22:55',2,2,1,0),(2,'2022-10-30 06:30:00','2022-11-01 19:45:00','Xe chạy tuyến quốc lộ 1A',350000,45,_binary '','2022-10-29 11:28:53',2,63,61,0);
/*!40000 ALTER TABLE `coaches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coaches_stop_by`
--

DROP TABLE IF EXISTS `coaches_stop_by`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coaches_stop_by` (
  `coaches_id` int NOT NULL,
  `stop_by_id` int NOT NULL,
  `time` time NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`coaches_id`,`stop_by_id`),
  KEY `fk_stop_by_idx` (`stop_by_id`),
  CONSTRAINT `fk_coaches` FOREIGN KEY (`coaches_id`) REFERENCES `coaches` (`id`),
  CONSTRAINT `fk_stop_by` FOREIGN KEY (`stop_by_id`) REFERENCES `stop_by` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coaches_stop_by`
--

LOCK TABLES `coaches_stop_by` WRITE;
/*!40000 ALTER TABLE `coaches_stop_by` DISABLE KEYS */;
INSERT INTO `coaches_stop_by` VALUES (1,2,'07:20:00',3),(1,3,'08:10:00',3),(1,5,'03:45:00',4),(1,6,'03:55:00',4),(2,2,'07:00:00',3),(2,3,'07:20:00',3),(2,4,'08:20:00',3),(2,22,'18:45:00',4),(2,23,'18:55:00',4);
/*!40000 ALTER TABLE `coaches_stop_by` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(200) DEFAULT NULL,
  `rating` int NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `coach_id` int NOT NULL,
  `user_id` int NOT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `comment_user_idx` (`user_id`),
  KEY `fk_comment_coach_idx` (`coach_id`),
  CONSTRAINT `fk_comment_coach` FOREIGN KEY (`coach_id`) REFERENCES `coach` (`id`),
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Lai Châu',1),(2,'Lào Cai',1),(3,'Hà Giang',1),(4,'Cao Bằng',1),(5,'Sơn La',1),(6,'Yên Bái',1),(7,'Tuyên Quang',1),(8,'Lạng Sơn',1),(9,'Quảng Ninh',1),(10,'Hòa Bình',1),(11,'Ninh Bình',1),(12,'Thái Bình',1),(13,'Thanh Hóa',1),(14,'Nghệ An',1),(15,'Hà Tĩnh',1),(16,'Quảng Bình',1),(17,'Quảng Trị',1),(18,'Thừa Thiên-Huế',1),(19,'Quảng Nam',1),(20,'Kon Tum',1),(21,'Quảng Ngãi',1),(22,'Gia Lai',1),(23,'Bình Định',1),(24,'Phú Yên',1),(25,'Đắk Lắk',1),(26,'Khánh Hòa',1),(27,'Lâm Đồng',1),(28,'Ninh Thuận',1),(29,'Tây Ninh',1),(30,'Đồng Nai',1),(31,'Bình Thuận',1),(32,'Long An',1),(33,'Bà Rịa - Vũng Tàu	',1),(34,'An Giang',1),(35,'Đồng Tháp',1),(36,'Tiền Giang',1),(37,'Kiến Giang',1),(38,'Vĩnh Long',1),(39,'Bến Tre',1),(40,'Trà Vinh',1),(41,'Sóc Trăng',1),(42,'Bắc Kạn',1),(43,'Bắc Giang',1),(44,'Bạc Liêu',1),(45,'Bắc Ninh',1),(46,'Bình Dương',1),(47,'Bình Phước',1),(48,'Cà Mau',1),(49,'Hải Dương',1),(50,'Hà Nam',1),(51,'Hưng Yên',1),(52,'Nam Định',1),(53,'Phú Thọ',1),(54,'Thái Nguyên',1),(55,'Vĩnh Phúc',1),(56,'Điện Biên',1),(57,'Đắk Nông',1),(58,'Hậu Giang',1),(59,'Cần Thơ',1),(60,'Đà Nẵng',1),(61,'Hà Nội',1),(62,'Hải Phòng',1),(63,'Hồ Chí Minh',1);
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `district`
--

DROP TABLE IF EXISTS `district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `district` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `country_id` int NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `district_country_idx` (`country_id`),
  CONSTRAINT `district_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
INSERT INTO `district` VALUES (1,'Thủ Đức',63,1),(2,'Quận 1',63,1),(3,'Quận 3',63,1),(4,'Quận 4',63,1),(5,'Quận 5',63,1),(6,'Quận 6',63,1),(7,'Quận 7',63,1),(8,'Quận 8',63,1),(9,'Quận 10',63,1),(10,'Quận 11',63,1),(11,'Quận 12',63,1),(12,'Bình Tân',63,1),(13,'Bình Thạnh',63,1),(14,'Gò Vấp',63,1),(15,'Phú Nhận',63,1),(16,'Tân Bình',63,1),(17,'Tân Phú',63,1),(18,'Bình Chánh',63,1),(19,'Cần Giờ',63,1),(20,'Củ Chi',63,1),(21,'Hóc Môn',63,1),(22,'Nhà Bè',63,1),(23,'Đà Lạt',27,1),(24,'Bảo Lâm',27,1),(25,'Bảo Lộc',27,1),(26,'Ba Đình',61,1),(27,'Bắc Từ Liêm',61,1),(28,'Cầu Giấy',61,1),(29,'Đống Đa',61,1),(30,'Hà Đông',61,1),(31,'Hai Bà Trưng',61,1),(32,'Hoàn Kiếm',61,1),(33,'Mường Tè',1,1),(34,'Nậm Nhùn',1,1),(35,'Phong Thổ',1,1),(36,'Sìn Hồ',1,1),(37,'Tam Đường',1,1),(38,'Tân Uyên',1,1),(39,'Than Uyên',1,1),(40,'Bắc Hà',2,1),(41,'Bảo Thắng',2,1),(42,'Bảo Thắng',2,1),(43,'Bát Xát',2,1),(44,'Mường Khương',2,1),(45,'Sa Pa town',2,1),(46,'Si Ma Cai',2,1),(47,'Văn Bàn',2,1),(48,'Bắc Mê',3,1),(49,'Bắc Quang',3,1),(50,'Đồng Văn',3,1),(51,'Bảo Lạc',4,1),(52,'Trà Lĩnh',4,1),(53,'Thông Nông',4,1),(54,'Bắc Yên',5,1),(55,'Mộc Châu',5,1),(56,'Mường La',5,1),(57,'Đồ Sơn',62,1),(58,'Dương Kinh',62,1),(59,'Hải An',62,1),(60,'Hồng Bàng',62,1),(61,'Kiến An',62,1),(62,'Lê Chân',62,1),(63,'Ngô Quyền',62,1),(64,'Cẩm Lệ',60,1),(65,'Hải Châu',60,1),(66,'Liên Chiểu',60,1),(67,'Ngũ Hành Sơn',60,1),(68,'Sơn Trà',60,1),(69,'Thanh Khê',60,1),(71,'Châu Đức',33,1),(72,'Côn Đảo',33,1),(73,'Đất Đỏ',33,1),(74,'Long Điền',33,1),(75,'Phú Mỹ',33,1),(76,'Gia Viễn',11,1),(77,'Hoa Lư',11,1),(78,'Kim Sơn',11,1),(79,'Nho Quan',11,1),(80,'Yên Khánh',11,1),(81,'Ninh Bình city',11,1),(82,'Tam Điệp city',11,1),(83,'Yên Mô',11,1),(84,'Long Biên',61,1),(85,'Hoàng Mai',61,1),(86,'Vũng Tàu city',33,1),(87,'Phường 11',33,1),(88,'Tân Thành',33,1);
/*!40000 ALTER TABLE `district` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `picture`
--

DROP TABLE IF EXISTS `picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `picture` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(150) DEFAULT NULL,
  `coach_id` int NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_picture_coach_idx` (`coach_id`),
  CONSTRAINT `fk_picture_coach` FOREIGN KEY (`coach_id`) REFERENCES `coach` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

LOCK TABLES `picture` WRITE;
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
INSERT INTO `picture` VALUES (1,'https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667016563/ujxtrxk2ceigpbocglbp.jpg',1,1),(2,'https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667016572/ly8y45hbh1m5z83dfm7z.jpg',1,1),(3,'https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667016575/ao8d7jfss8yngdsqcnkt.jpg',1,1),(4,'https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667016581/owjha7ezjderwoe5fbur.jpg',1,1),(5,'https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667016684/g5dpljflpqxwmdhvaohx.jpg',2,1),(6,'https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667016687/lehu2cikqv2dijuvhntq.jpg',2,1),(7,'https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667016690/lohog7yvrsdpt4tnyqte.jpg',2,1),(8,'https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667016693/ovag8klwqdaxxbtvkmqi.jpg',2,1),(9,'https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667016761/uj6dnkkavtiqchtujnam.png',3,1),(10,'https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667017086/mooophh2lpdoenvkymjf.jpg',4,1),(11,'https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667017100/zbs3et9tvl5biuexbj3y.jpg',4,1),(12,'https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667017106/sk71oosxqpsv6hk8mnno.jpg',4,1),(13,'https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667017110/stpx2pyerxqiailufqgz.jpg',4,1),(14,'https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667017137/yjdgp2kzhasxsb8b4lgi.jpg',4,1),(15,'https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667017285/sqpazryrjpl4f1ddcpny.jpg',5,1),(16,'https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667017291/msdee0d907qqeqyvqdpn.jpg',5,1);
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'user',1),(2,'admin',1),(3,'coachGarage',1),(4,'employee',1);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping`
--

DROP TABLE IF EXISTS `shipping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipping` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `sender_name` varchar(45) DEFAULT NULL,
  `sender_phone` varchar(11) NOT NULL,
  `sender_email` varchar(45) NOT NULL,
  `receiver_name` varchar(45) DEFAULT NULL,
  `receiver_phone` varchar(11) NOT NULL,
  `receiver_email` varchar(45) NOT NULL,
  `send_time` datetime DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `user_id` int NOT NULL,
  `coaches_id` int NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_shipping_coaches_idx` (`coaches_id`),
  KEY `fk_shipping__user_idx` (`user_id`),
  CONSTRAINT `fk_shipping__user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_shipping_coaches` FOREIGN KEY (`coaches_id`) REFERENCES `coaches` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping`
--

LOCK TABLES `shipping` WRITE;
/*!40000 ALTER TABLE `shipping` DISABLE KEYS */;
/*!40000 ALTER TABLE `shipping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stop_by`
--

DROP TABLE IF EXISTS `stop_by`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stop_by` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `district_id` int NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_stop_by_district_idx` (`district_id`),
  CONSTRAINT `fk_stop_by_district` FOREIGN KEY (`district_id`) REFERENCES `district` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stop_by`
--

LOCK TABLES `stop_by` WRITE;
/*!40000 ALTER TABLE `stop_by` DISABLE KEYS */;
INSERT INTO `stop_by` VALUES (1,'Bến xe Nguyễn Ảnh Thủ','98 Nguyễn Ảnh Thủ',11,1),(2,'Bến xe miền Đông','292 Đinh Bộ Lĩnh, phường 26',13,1),(3,'Bến xe miền Tây','95 Kinh Đ. Vương, An Lạc',12,1),(4,'Bến xe Tô Châu','Lê Hồng Phong, P.1',9,1),(5,'Hợp tác xã vận tải 19/5','Tân Thới Nhì',21,1),(6,'Bến xe buýt Chợ Lớn','Phường 14',5,1),(7,'Bến xe khách Mỹ Đình','20 Phạm Hùng, Mỹ Đình',27,1),(8,'Ga Gia Lâm','Phường Ngọc Thụy',84,1),(9,'Bến xe Giáp Bát','Km6 đường Giải phóng',85,1),(10,'Bến xe Nước Ngầm','Km8 Giải Phóng',85,1),(11,'Bến xe Gia Lâm','Số 9, đường Ngô Gia Khảm',84,1),(12,'Bến xe Yên Nghĩa','Đường Trần Phú',30,1),(13,'Ga Hà Nội','120 Lê Duẩn',32,1),(14,'Cổng 3 Bến Xe Miền Đông','95 Kinh Đ. Vương, An Lạc',13,1),(15,'Ngã 4 Bình Phước','Quốc lộ 1A',1,1),(16,'Suối Tiên','120 XL Hà Nội, Thành Phố',1,1),(17,'Ẹo Ông Từ','47 Đường Ven Biển, Phường 12',86,1),(18,'Cảng Cát Lở','C47G+2X3, Đ. 30 Tháng 4',87,1),(19,'Cây xăng Láng Cát','Tân Hải',88,1),(20,'Ngã 4 Túy Loan','sông Cầu Đỏ',66,1),(21,'Đà Nẵng (dọc QL1A)','QL1A',65,1),(22,'Bến xe Di Linh','681 Hùng Vương',23,1),(23,'Bến xe Đà Lạt','Đường Tô Hiến Thành, Phường 3',23,1);
/*!40000 ALTER TABLE `stop_by` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `id` int NOT NULL AUTO_INCREMENT,
  `price` decimal(10,0) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `amount` int NOT NULL DEFAULT '1',
  `coaches_id` int NOT NULL,
  `user_id` int NOT NULL,
  `pick_up_id` int NOT NULL,
  `drop_off_id` int NOT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_ticket_coaches_idx` (`coaches_id`),
  KEY `fk_tocket_user_idx` (`user_id`),
  KEY `fk_ticket_pick_up_idx` (`pick_up_id`),
  KEY `fk_ticket_drop_off_idx` (`drop_off_id`),
  CONSTRAINT `fk_ticket_coaches` FOREIGN KEY (`coaches_id`) REFERENCES `coaches` (`id`),
  CONSTRAINT `fk_ticket_drop_off` FOREIGN KEY (`drop_off_id`) REFERENCES `stop_by` (`id`),
  CONSTRAINT `fk_ticket_pick_up` FOREIGN KEY (`pick_up_id`) REFERENCES `stop_by` (`id`),
  CONSTRAINT `fk_ticket_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(60) NOT NULL,
  `fullname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `gender` bit(1) DEFAULT b'1',
  `avatar` varchar(100) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `role_id` int NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_UNIQUE` (`phone`),
  KEY `user_role_idx` (`role_id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'$2a$10$k2w4Jhw/Nx8Q0YGCJBRVTeMmjo2Y1uVbOoTyci6dBE3GNfpX4lGRu','Mạnh Linh','1951052055hieu@ou.edu.vn','0123456789',_binary '','https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667014450/uzcrhd98qemmvcv2lexp.jpg','2022-10-29 10:33:51',2,1),(2,'$2a$10$kuTIR3e65ejaa26yf9kBsu76.SISD955E8u88PWCpbMGuhOeeqSiG','Nguyễn Minh Hiếu','1951052055hieu@ou.edu.vn','0123456788',_binary '','https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667014514/aqzsixn7uc8f7fjbuh8w.jpg','2022-10-29 10:34:55',3,1),(3,'$2a$10$l1CHZKOUp2F9j8GWqK0Y5OVO/1Giua8juvX.jaRV8Nz1s0GCqzXH.','Nguyễn Kim Tuyết','1951052055hieu@ou.edu.vn','0123456787',_binary '\0','https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667014596/xhiep08wfhyusmdvrhcj.jpg','2022-10-29 10:36:17',3,1),(4,'$2a$10$Kh.ZayttOir10hzLr8l.nOipzW.c932MjTlCLx9gDjx/DLSCQx4WS','Hồng Thanh Đoan','1951052055hieu@ou.edu.vn','0123456786',_binary '\0','https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667014722/rako8bdewqqqg19phi98.jpg','2022-10-29 10:38:23',4,1),(5,'$2a$10$/zgA0ceonEoGtudPNGqTqOxgd.jcGk5Pd5NmvXR46.Y1fnGKJsGHq','Nguyễn Thanh Tuấn','1951052055hieu@ou.edu.vn','0123456785',_binary '','https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667014776/ikkbak7s2cwgdqmf65ka.jpg','2022-10-29 10:39:17',4,1),(6,'$2a$10$vUeRJSBgA96OWQRlQLPzkeXPDzjINmNuVvVfWEoMPUWPeBngtNSTS','Hoàng Kiều Linh','1951052055hieu@ou.edu.vn','0123456784',_binary '\0','https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667014984/jpwbexepngtu1i7vd1nh.jpg','2022-10-29 10:42:45',1,0),(7,'$2a$10$5pDwttIQX3Esdvc6IHo5dOzPcBwdjKYEOEONG9IB/4.ll.9xVBoqy','Thanh Hằng','1951052055hieu@ou.edu.vn','0123456783',_binary '\0','https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667015047/uqqmu2umwr6whh1cgq1p.png','2022-10-29 10:43:47',1,1),(8,'$2a$10$n6H.cLl1caPrFcH8OPVoqOI3vpigqmy8y4HeEZvtczQo5QxrwLg4S','Bùi Mạnh Linh','1951052055hieu@ou.edu.vn','0123456782',_binary '','https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667015118/em2b7duj16rzrc6gc4jy.jpg','2022-10-29 10:44:59',3,1),(9,'$2a$10$A2nmzxbACyuutmuVk2C1Gu9CrYYumUewxecrXuogwL57iBMtMPGX.','Tuyền Mỹ Kiều','1951052055hieu@ou.edu.vn','0123456781',_binary '\0','https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667015187/gbhmheaymm1fktvcyeqd.jpg','2022-10-29 10:46:08',3,1),(10,'$2a$10$ZxYWs9dvYVUI07jgKw/OEuyuaOJb72JDnWIqb6DN19Do0k/rDY39S','Hoàng Phạm Kim Linh','1951052055hieu@ou.edu.vn','0123456780',_binary '\0','https://res.cloudinary.com/dd3tfkb7f/image/upload/v1667015251/zzeaieenefe7ratfz3dw.jpg','2022-10-29 10:47:11',1,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-29 11:30:20
