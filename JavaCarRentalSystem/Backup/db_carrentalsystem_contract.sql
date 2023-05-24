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
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contract` (
  `conditions` varchar(400) NOT NULL,
  `number` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` VALUES ('1. With this agreement, the lessor has leased the vehicle described (to be specified as a vehicle in this agreement) under the terms of this agreement, and the lessee has rented the vehicle by accepting the conditions.',1),('2. The tenant must comply with the highway regulations and applicable laws. The tenant is responsible for the costs (fines, tying a vehicle, towing a vehicle etc.)',2),('3. The lessee is responsible for the penal actions to be applied as a result of the accidents caused by violating the traffic rules of the highways and their accessories (Traffic fine, expenses incurred as a result of tying and pulling the rental vehicle and other expenses incurred as a result of the related accident).',3),('4. The tenant will return and deliver the vehicle on the agreed day.',4),('5. At the beginning of the rental, vehicle users are required to present their driver\'s licenses and available credit cards. Payments are made with a valid credit card at the start of the rental.',5),('6. In case of damage or malfunction in the rented vehicle, the lessee is responsible for delivering the vehicle to the authorized service in a safe manner that does not increase the damage.',6),('7. In case of transporting goods or passengers by vehicle, the lessee is solely responsible for any damage or damage that may occur to the goods or passengers.',7),('8. In the event of an accident resulting in material, fatal or bodily damage, the tenant must immediately notify the nearest police officers or relevant units, and deliver the minutes and reports to the relevant office within 24 hours at the latest.',8),('9. In case of damage to the vehicle that will be reflected to the insurance company, the lessee is obliged to complete the documents and procedures and deliver them. The rental agreement will continue to operate at the daily rental price until the documents and procedures are completed.',9),('10. Upon the expiry of the rental period',10);
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
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
