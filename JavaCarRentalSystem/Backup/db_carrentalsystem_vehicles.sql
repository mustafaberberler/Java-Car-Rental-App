-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: db_carrentalsystem
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
-- Table structure for table `vehicles`
--

DROP TABLE IF EXISTS `vehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicles` (
  `uniqueID` varchar(100) NOT NULL,
  `ownerid` varchar(11) DEFAULT NULL,
  `advertNumber` varchar(11) DEFAULT NULL,
  `brand` varchar(20) DEFAULT NULL,
  `series` varchar(20) DEFAULT NULL,
  `model` varchar(20) DEFAULT NULL,
  `year` varchar(20) DEFAULT NULL,
  `fueltype` varchar(15) DEFAULT NULL,
  `transmission` varchar(15) DEFAULT NULL,
  `km` varchar(10) DEFAULT NULL,
  `chassis` varchar(10) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `fuelConsumption` double DEFAULT NULL,
  `fuelTank` int DEFAULT NULL,
  `rentCost` double DEFAULT NULL,
  `advertDate` date DEFAULT NULL,
  `traction` varchar(3) DEFAULT NULL,
  `offroad` varchar(3) DEFAULT NULL,
  `carryload` varchar(3) DEFAULT NULL,
  `rented` tinyint DEFAULT NULL,
  PRIMARY KEY (`uniqueID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicles`
--

LOCK TABLES `vehicles` WRITE;
/*!40000 ALTER TABLE `vehicles` DISABLE KEYS */;
INSERT INTO `vehicles` VALUES ('0716498f9cdf438f904b8f09c405e3b2',NULL,'55555555555','Toyota','Corolla','Life','2020','Gasoline','Manual','55000','Sedan','White',4.5,45,5000,'2022-01-01','\"\"','\"\"','\"\"',1),('49ac3e5cb35b4898b1de2f930bd86382','44444444444','66666666666','xx','yy','zz','2020','Hybrid','Automatic','45000','SUV','White',15,80,550,'2022-01-05','4x4','Yes','Yes',0),('904de64550ee4a32bf7ae5d69a22de86','77777777777','99999999999','aa','bb','c','2020','Hybrid','Automatic','10000','SUV','Black',6.5,65,400,'2022-01-05','4x4','Yes','',1);
/*!40000 ALTER TABLE `vehicles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-05 20:50:13
