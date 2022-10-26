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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Giường nằm 45 chỗ',1,45),(2,'Giường nằm 25 chỗ',1,25),(3,'Giường nằm 49 chỗ',0,49),(5,'Ghế ngồi 10 chỗ',0,10),(6,'Ngồi 15 chỗ',1,15);
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coach`
--

LOCK TABLES `coach` WRITE;
/*!40000 ALTER TABLE `coach` DISABLE KEYS */;
INSERT INTO `coach` VALUES (1,'59AFG015H',NULL,1,1,1),(2,'58HFG015B',NULL,2,2,1),(3,'49HF321B','Xe màu hồng',2,3,0),(4,'58HFG015B',NULL,2,2,1),(5,'58HFG015B',NULL,2,2,1),(6,'58HFG015B',NULL,2,2,1),(7,'58HFG015B',NULL,2,2,1),(8,'58HFG015B',NULL,2,2,1),(9,'58HFG015B',NULL,2,2,1),(10,'58HFG015B',NULL,2,2,1),(11,'98HF321B','Cập nhật',1,1,1),(12,'58HFGAF65B','Nhìu thứ mới',1,2,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coach_garage`
--

LOCK TABLES `coach_garage` WRITE;
/*!40000 ALTER TABLE `coach_garage` DISABLE KEYS */;
INSERT INTO `coach_garage` VALUES (1,'Hải Triều','Linh','0123456789','a',NULL,3,2,'a',0),(2,'Đông Dương','Hiếu','1234567890','b',NULL,2,3,'a',1),(3,'Đà Lạt','Hải','2345678901','c',NULL,1,3,'a',1),(4,'Hải Triều','Nam','0123456789','d',NULL,3,2,'a',1),(6,'Hải Triều','Linh','0123456789','e','12/8 Đường 6',3,3,'a',2),(7,'Linh test api','Linh','0123456789','1951052099linh@gmail.com','12/8 Đường 6',3,3,'null',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coaches`
--

LOCK TABLES `coaches` WRITE;
/*!40000 ALTER TABLE `coaches` DISABLE KEYS */;
INSERT INTO `coaches` VALUES (1,'2022-10-05 07:30:30','2022-10-05 10:30:30','',250000,38,_binary '',1,1,1,1),(2,'2022-10-14 06:30:00','2022-10-15 05:45:00','Xe hư dời 1 ngày',350000,0,_binary '\0',2,2,1,0),(3,'2022-10-13 06:30:00','2022-10-15 05:45:00','',550000,49,_binary '',1,1,1,1),(4,'2022-10-13 06:30:00','2022-10-15 05:45:00','',550000,25,_binary '',2,2,1,1),(6,'2022-10-26 06:30:00','2022-10-27 05:45:00','',650000,0,_binary '',2,2,1,1),(7,'2022-10-26 06:30:00','2022-10-27 05:45:00','',650000,0,_binary '',2,2,1,1);
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
INSERT INTO `coaches_stop_by` VALUES (1,1,'00:00:00',1),(1,2,'00:00:15',2),(1,3,'00:00:00',3),(3,1,'00:00:30',2),(3,2,'00:00:15',3),(3,3,'00:00:00',1),(4,2,'00:00:15',3),(4,3,'00:00:00',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (5,'aaa',5,NULL,1,1,1),(6,'Tesing',4,'2022-10-13 08:10:05',1,1,1),(7,'Xe chạy bát nháo lắm',5,'2022-10-13 08:10:43',1,1,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Hà Nội',1),(2,'Hồ Chí Minh',1),(3,'Đà Lạt',1),(5,'Hồ Chí Minh',1),(6,'Hồ Chí Minh',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
INSERT INTO `district` VALUES (1,'Đống Đa',1,1),(2,'Quận 1',2,1),(3,'Quận 12',2,1);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

LOCK TABLES `picture` WRITE;
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stop_by`
--

LOCK TABLES `stop_by` WRITE;
/*!40000 ALTER TABLE `stop_by` DISABLE KEYS */;
INSERT INTO `stop_by` VALUES (1,'Bến xe An Sương','111 đường 8',3,1),(2,'Bến xe Quận 1','456 đường 7',2,1),(3,'Bến xe Quận 2',NULL,3,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,15000,'1951052055Hieu@ou.edu.vn',NULL,'0123456789',1,1,1,1,1,1),(2,15000,'1951052099linh@ou.edu.vn','2022-10-12 04:29:48','0123456789',1,1,2,2,1,1),(3,15000,'1951052099linh@ou.edu.vn','2022-10-12 04:29:50','0123456789',1,1,2,2,1,1),(4,15000,'1951052055hieu@ou.edu.vn','2022-10-12 04:31:02','0123456789',1,1,2,2,1,1),(5,15000,'1951052055hieu@ou.edu.vn','2022-10-12 04:31:03','0123456789',1,1,2,2,1,1),(6,15000,'1951052055hieu@ou.edu.vn','2022-10-12 04:37:12','0123456789',1,1,2,2,1,1),(7,15000,'1951052099linh@ou.edu.vn','2022-10-12 04:43:19','0123456789',1,1,2,2,1,1),(8,15000,'1951052099linh@ou.edu.vn','2022-10-12 04:43:19','0123456789',1,1,2,2,1,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'$2a$10$Kh28jCwLGkH13mfW669lIOdwUxyn8/AWrBsmSVgWeuFytwCjPcQNG','Nguyễn Minh Hiếu','1951052055hieu@ou.edu.vn','0123456789',_binary '',NULL,NULL,1,1),(2,'$2a$10$Kh28jCwLGkH13mfW669lIOdwUxyn8/AWrBsmSVgWeuFytwCjPcQNG','Bùi Mạnh Linh','1951052099linh@ou.edu.vn','1234567890',_binary '',NULL,NULL,3,1),(3,'$2a$10$Kh28jCwLGkH13mfW669lIOdwUxyn8/AWrBsmSVgWeuFytwCjPcQNG','Nguyễn Mạnh','1951052099linh@ou.edu.vn','2345678901',_binary '',NULL,NULL,3,0),(4,'$2a$10$Kh28jCwLGkH13mfW669lIOdwUxyn8/AWrBsmSVgWeuFytwCjPcQNG','Lương Hoàng Nam','1951052099linh@ou.edu.vn','3456789012',_binary '',NULL,NULL,1,0),(7,'$2a$10$Kh28jCwLGkH13mfW669lIOdwUxyn8/AWrBsmSVgWeuFytwCjPcQNG','Nguyễn Minh Hiếu','1951052055hieu@ou.edu.vn','4567890123',_binary '',NULL,'2022-10-05 01:38:30',1,1),(8,'$2a$10$Kh28jCwLGkH13mfW669lIOdwUxyn8/AWrBsmSVgWeuFytwCjPcQNG','Nguyễn Minh Hiếu','1951052055hieu@ou.edu.vn','5678901234',_binary '',NULL,'2022-10-05 01:38:56',1,1),(10,'$2a$10$Kh28jCwLGkH13mfW669lIOdwUxyn8/AWrBsmSVgWeuFytwCjPcQNG','Nguyễn Minh Hiếu','1951052055hieu@ou.edu.vn','0123456711',_binary '',NULL,'2022-10-05 01:46:18',1,1),(11,'$2a$10$Kh28jCwLGkH13mfW669lIOdwUxyn8/AWrBsmSVgWeuFytwCjPcQNG','Linh test update2','1951052099linh@ou.edu.vn','1234555555',_binary '',NULL,NULL,1,0),(12,'$2a$10$Kh28jCwLGkH13mfW669lIOdwUxyn8/AWrBsmSVgWeuFytwCjPcQNG','Nguyễn Minh Hiếu','1951052055hieu@ou.edu.vn','12345456488',_binary '',NULL,'2022-10-05 05:46:56',1,1),(13,'$2a$10$Kh28jCwLGkH13mfW669lIOdwUxyn8/AWrBsmSVgWeuFytwCjPcQNG','Nguyễn Minh Hiếu','1951052055hieu@ou.edu.vn','    ',_binary '',NULL,'2022-10-05 06:09:11',1,1),(16,'$2a$10$Kh28jCwLGkH13mfW669lIOdwUxyn8/AWrBsmSVgWeuFytwCjPcQNG','Linh','1951052055hieu@ou.edu.vn','01234567777',_binary '',NULL,'2022-10-09 16:40:00',1,1),(17,'$2a$10$Kh28jCwLGkH13mfW669lIOdwUxyn8/AWrBsmSVgWeuFytwCjPcQNG','Linh','1951052055hieu@ou.edu.vn','01234567771',_binary '',NULL,'2022-10-09 16:43:11',1,1),(19,'$2a$10$Kh28jCwLGkH13mfW669lIOdwUxyn8/AWrBsmSVgWeuFytwCjPcQNG','Linh','1951052055hieu@ou.edu.vn','0123456781',_binary '',NULL,'2022-10-24 01:01:29',1,1),(20,'$2a$10$Ow40iRavGZhB4JtHPXGXdu8iIodS3iWHVFgQwe2H/KtAc7A0KN.Fm','Linh','1951052055hieu@ou.edu.vn','0123456782',_binary '',NULL,'2022-10-24 18:03:43',1,1),(22,'$2a$10$OwgvA0i9IYDSjMxBc.781e0PL6XFFHYHp5vrQySXIurnrZGsU4YJu','Tesing 1','1951052099linh@ou.edu.vn','12345678901',_binary '',NULL,'2022-10-25 23:05:16',1,0);
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

-- Dump completed on 2022-10-26  1:33:22
