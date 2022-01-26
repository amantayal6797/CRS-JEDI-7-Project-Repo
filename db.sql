CREATE DATABASE  IF NOT EXISTS `crs_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `crs_db`;
-- MySQL dump 10.13  Distrib 8.0.28, for macos11 (x86_64)
--
-- Host: localhost    Database: crs_db
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
-- Table structure for table `coursecatalog`
--

DROP TABLE IF EXISTS `coursecatalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coursecatalog` (
  `courseId` int NOT NULL,
  `courseName` varchar(45) NOT NULL,
  `professorAlloted` int NOT NULL DEFAULT '0',
  `Credits` int NOT NULL,
  `Prerequisites` varchar(45) NOT NULL,
  PRIMARY KEY (`courseId`),
  KEY `coursecatalogFk_idx` (`professorAlloted`),
  CONSTRAINT `coursecatalogFk` FOREIGN KEY (`professorAlloted`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursecatalog`
--

LOCK TABLES `coursecatalog` WRITE;
/*!40000 ALTER TABLE `coursecatalog` DISABLE KEYS */;
INSERT INTO `coursecatalog` VALUES (401,'DS',201,5,'NA'),(402,'CN',202,5,'NA'),(403,'Algo',201,5,'NA'),(404,'DM',202,5,'NA'),(405,'TOC',203,5,'NA'),(406,'Compiler',204,5,'NA'),(407,'OS',203,5,'NA'),(408,'Automata',202,5,'NA');
/*!40000 ALTER TABLE `coursecatalog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professor` (
  `Department` varchar(45) NOT NULL,
  `userId` int NOT NULL,
  PRIMARY KEY (`userId`),
  CONSTRAINT `professorfk` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES ('CS',201),('CS',202),('CS',203),('CS',204);
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registeredcourses`
--

DROP TABLE IF EXISTS `registeredcourses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registeredcourses` (
  `courseId` int NOT NULL,
  `userId` int NOT NULL,
  `Grade` varchar(45) NOT NULL,
  PRIMARY KEY (`courseId`,`userId`),
  KEY `registeredcourseFk2_idx` (`userId`),
  CONSTRAINT `registeredcourseFk1` FOREIGN KEY (`courseId`) REFERENCES `coursecatalog` (`courseId`),
  CONSTRAINT `registeredcourseFk2` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registeredcourses`
--

LOCK TABLES `registeredcourses` WRITE;
/*!40000 ALTER TABLE `registeredcourses` DISABLE KEYS */;
/*!40000 ALTER TABLE `registeredcourses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `userId` int NOT NULL,
  `isRegistered` int NOT NULL,
  `Branch` varchar(45) NOT NULL,
  `paymentStatus` int NOT NULL,
  PRIMARY KEY (`userId`),
  CONSTRAINT `studentFk` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (101,0,'CS',0),(102,0,'CS',0);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userId` int NOT NULL,
  `userName` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Role` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `isApproved` int NOT NULL,
  `Address` varchar(45) NOT NULL,
  `Age` int NOT NULL,
  `Gender` varchar(45) NOT NULL,
  `Contact` varchar(45) NOT NULL,
  `Nationality` varchar(45) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (0,'dummy','password','Professor','email@dummy.com',1,'Address',23,'Male','2222222222','Indian'),(101,'userName','password','Student','Email',1,'Address',23,'Male','7983105686','Indian'),(102,'userName2','password','Student','Email2',0,'Address2',23,'Male','7983105686','Indian2'),(201,'userName3','password','Professor','Email3',1,'Address3',23,'Male','7983105686','Indian3'),(202,'userName4','password','Professor','Email4',1,'Address4',23,'Male','7983105686','Indian4'),(203,'userName4','password','Professor','Email4',1,'Address4',23,'Female','7983105686','Indian4'),(204,'userName4','password','Professor','Email4',1,'Address4',23,'Female','7983105686','Indian4'),(301,'userName5','password','Admin','Email5',1,'Address',23,'Male','7983105686','Indian5');
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

-- Dump completed on 2022-01-26 20:20:56
