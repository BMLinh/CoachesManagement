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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-29  8:57:16
