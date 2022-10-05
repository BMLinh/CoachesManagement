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
  `type` varchar(45) DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coach`
--

DROP TABLE IF EXISTS `coach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coach` (
  `License_plates` varchar(20) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `seat` int DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `coachGarage_id` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`License_plates`),
  UNIQUE KEY `phone_UNIQUE` (`phone`),
  KEY `coach_station_idx` (`coachGarage_id`),
  KEY `coach_cagory_idx` (`category_id`),
  CONSTRAINT `coach_cagory` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `coach_coachGarage` FOREIGN KEY (`coachGarage_id`) REFERENCES `coach_garage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coach`
--

LOCK TABLES `coach` WRITE;
/*!40000 ALTER TABLE `coach` DISABLE KEYS */;
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
  `name` varchar(45) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `distric_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `contract` varchar(150) DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `coachGarage_user_idx` (`user_id`),
  KEY `coachGarage_district_idx` (`distric_id`),
  CONSTRAINT `coachGarage_district` FOREIGN KEY (`distric_id`) REFERENCES `district` (`id`),
  CONSTRAINT `coachGarage_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coach_garage`
--

LOCK TABLES `coach_garage` WRITE;
/*!40000 ALTER TABLE `coach_garage` DISABLE KEYS */;
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
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `description` varchar(150) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `emptySeat` int DEFAULT NULL,
  `isShipping` bit(1) DEFAULT b'0',
  `driver_id` int DEFAULT NULL,
  `coach_id` varchar(20) DEFAULT NULL,
  `startPoint` int DEFAULT NULL,
  `endPoint` int DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_coaches_driver_idx` (`driver_id`),
  KEY `fk_coaches_country_idx` (`startPoint`),
  KEY `fk_coaches_country_idx1` (`endPoint`),
  KEY `fk_coaches_coach_idx` (`coach_id`),
  CONSTRAINT `fk_coaches_coach` FOREIGN KEY (`coach_id`) REFERENCES `coach` (`License_plates`),
  CONSTRAINT `fk_coaches_driver` FOREIGN KEY (`driver_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_coaches_end_point` FOREIGN KEY (`endPoint`) REFERENCES `country` (`id`),
  CONSTRAINT `fk_coaches_start_point` FOREIGN KEY (`startPoint`) REFERENCES `country` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coaches`
--

LOCK TABLES `coaches` WRITE;
/*!40000 ALTER TABLE `coaches` DISABLE KEYS */;
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
  `stopBy_id` int NOT NULL,
  `time` int DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`coaches_id`,`stopBy_id`),
  KEY `fk_stop_by_idx` (`stopBy_id`),
  CONSTRAINT `fk_coaches` FOREIGN KEY (`coaches_id`) REFERENCES `coaches` (`id`),
  CONSTRAINT `fk_stop_by` FOREIGN KEY (`stopBy_id`) REFERENCES `stop_by` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coaches_stop_by`
--

LOCK TABLES `coaches_stop_by` WRITE;
/*!40000 ALTER TABLE `coaches_stop_by` DISABLE KEYS */;
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
  `rating` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `coach_id` varchar(20) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `comment_user_idx` (`user_id`),
  KEY `fk_comment_coach_idx` (`coach_id`),
  CONSTRAINT `fk_comment_coach` FOREIGN KEY (`coach_id`) REFERENCES `coach` (`License_plates`),
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
  `status` int DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
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
  `country_id` int DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `district_country_idx` (`country_id`),
  CONSTRAINT `district_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
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
  `name` varchar(45) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL,
  `coach_id` varchar(20) DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_picture_coach_idx` (`coach_id`),
  CONSTRAINT `fk_picture_coach` FOREIGN KEY (`coach_id`) REFERENCES `coach` (`License_plates`)
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
  `name` varchar(20) DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin',1),(2,'user',1),(3,'default',1);
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
  `senderName` varchar(45) DEFAULT NULL,
  `senderPhone` varchar(11) DEFAULT NULL,
  `senderEmail` varchar(45) DEFAULT NULL,
  `receiverName` varchar(45) DEFAULT NULL,
  `receiverPhone` varchar(11) DEFAULT NULL,
  `receiverEmail` varchar(45) DEFAULT NULL,
  `sendTime` datetime DEFAULT NULL,
  `receiveTime` datetime DEFAULT NULL,
  `coaches_id` int DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_shipping_coaches_idx` (`coaches_id`),
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
  `district_id` int DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_stop_by_district_idx` (`district_id`),
  CONSTRAINT `fk_stop_by_district` FOREIGN KEY (`district_id`) REFERENCES `district` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stop_by`
--

LOCK TABLES `stop_by` WRITE;
/*!40000 ALTER TABLE `stop_by` DISABLE KEYS */;
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
  `price` float DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `coaches_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `status` int DEFAULT '1',
  `phone` varchar(11) DEFAULT NULL,
  `pick_up_id` int DEFAULT NULL,
  `drop_off_id` int DEFAULT NULL,
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
  `dob` date DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `avatar` varchar(100) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_UNIQUE` (`phone`),
  KEY `user_role_idx` (`role_id`),
  CONSTRAINT `user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'1','Nguyễn Minh Hiếu','1951052055hieu@ou.edu.vn','0123456789',NULL,_binary '',NULL,NULL,1,1),(2,'1','Bùi Mạnh Linh','1951052099linh@ou.edu.vn','1234567890',NULL,_binary '',NULL,NULL,2,1),(3,'1','Nguyễn Mạnh','1951052099linh@ou.edu.vn','2345678901',NULL,_binary '',NULL,NULL,2,1),(4,'1','Lương Hoàng Nam','1951052099linh@ou.edu.vn','3456789012',NULL,_binary '',NULL,NULL,1,0),(7,'1','Nguyễn Minh Hiếu','1951052055hieu@ou.edu.vn','4567890123',NULL,_binary '',NULL,'2022-10-05 01:38:30',1,NULL),(8,'1','Nguyễn Minh Hiếu','1951052055hieu@ou.edu.vn','5678901234',NULL,_binary '',NULL,'2022-10-05 01:38:56',1,NULL),(10,'1','Nguyễn Minh Hiếu','1951052055hieu@ou.edu.vn','0123456711',NULL,_binary '',NULL,'2022-10-05 01:46:18',1,NULL),(11,'1','Linh test update2','1951052099linh@ou.edu.vn','123465790',NULL,_binary '',NULL,'2022-10-05 03:56:00',NULL,0),(12,'1','Nguyễn Minh Hiếu','1951052055hieu@ou.edu.vn','12345456488',NULL,_binary '',NULL,'2022-10-05 05:46:56',NULL,NULL),(13,'1','Nguyễn Minh Hiếu','1951052055hieu@ou.edu.vn','    ',NULL,_binary '',NULL,'2022-10-05 06:09:11',NULL,NULL);
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

-- Dump completed on 2022-10-05  7:06:24
