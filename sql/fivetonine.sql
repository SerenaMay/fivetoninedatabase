CREATE DATABASE  IF NOT EXISTS `fivetonine` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fivetonine`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: fivetonine
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `addressID` int NOT NULL AUTO_INCREMENT,
  `Street` varchar(45) DEFAULT NULL,
  `geoID` int DEFAULT NULL,
  PRIMARY KEY (`addressID`),
  UNIQUE KEY `addressID_UNIQUE` (`addressID`),
  KEY `geoID_idx` (`geoID`),
  CONSTRAINT `geoID` FOREIGN KEY (`geoID`) REFERENCES `geoid` (`geoID`)
) ENGINE=InnoDB AUTO_INCREMENT=5374 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (2144,'148 Old Will Hunter Rd',5760),(2939,'Street Address',4371),(5373,'Street Address',2165);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cartID` int NOT NULL,
  `uid` int DEFAULT NULL,
  `pid` int DEFAULT NULL,
  PRIMARY KEY (`cartID`),
  UNIQUE KEY `cartID_UNIQUE` (`cartID`),
  KEY `pid_idx` (`pid`),
  KEY `uid_idx` (`uid`),
  CONSTRAINT `pid` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`),
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `user` (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `geoid`
--

DROP TABLE IF EXISTS `geoid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `geoid` (
  `geoID` int NOT NULL AUTO_INCREMENT,
  `city` varchar(45) DEFAULT ' ',
  `state` varchar(2) DEFAULT ' ',
  `zip` int DEFAULT '0',
  PRIMARY KEY (`geoID`)
) ENGINE=InnoDB AUTO_INCREMENT=9975 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `geoid`
--

LOCK TABLES `geoid` WRITE;
/*!40000 ALTER TABLE `geoid` DISABLE KEYS */;
INSERT INTO `geoid` VALUES (2165,'City Name','St',2),(4371,'City Name','St',30606),(5760,'Athens','GA',30606);
/*!40000 ALTER TABLE `geoid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `orderID` int NOT NULL AUTO_INCREMENT,
  `uid` int DEFAULT NULL,
  `pid` int DEFAULT NULL,
  PRIMARY KEY (`orderID`),
  KEY `pid_idx` (`pid`),
  KEY `uid_idx` (`uid`),
  CONSTRAINT `pid_order` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`),
  CONSTRAINT `uid_order` FOREIGN KEY (`uid`) REFERENCES `user` (`UID`)
) ENGINE=InnoDB AUTO_INCREMENT=98594 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (71330,3664,9409);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `pid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `price` decimal(3,2) NOT NULL,
  `description` varchar(255) DEFAULT ' ',
  `quantity` int DEFAULT '0',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=9467 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1213,'In Stock Youpin NewBring Mini ',6.93,'-The NewBring card protector. Reddot Award winning design credit card holder. -With a click all cards slide out gradually. The cards will not fall out',25),(1448,'EAMBRITE Fairy Lights 50LT Str',6.99,'17FT silver copper wire hosts 50 LEDs starry chips that intensely bright, with 2 hanging hocks, lead in 0.98ft, spacing 0.33ft. With steady 360 degree viewing angle and 8 modes to changing the display form ',54),(1919,'Hudson Baby Unisex Baby Cotton',8.50,'     100% Woven Terry Cotton     Adorably detailed animal face on hood     Made with 100% woven terry cotton / hooded towel 33x33 inches     Soft and gentle on babys skin     Optimal for everyday use     Affordable, high quality hooded towel ',50),(2102,'Amazon Basics Portable Digital',9.93,'Compact digital luggage scale helps avoid excess-bag-weight costs when traveling ',51),(3246,'Thermometer for Adults, Oral T',6.99,'Fast and Accurate Reading: Fast measurement, normally is 0.5-1 minute. This thermometer is clinically accurate with high precision ',15),(4811,'LuckyStone Motion Activated To',9.99,'Motion-Activated, activates when you enter and deactivates when you leave, decoration the dark bathroom and lighting your way, protecting kids from bumped in the dark. ',55),(4976,'Burts Bees Eye Makeup Remover ',4.19,'MOITURIZING PADS: Formulated with kiwi extract, these eye makeup remover pads cleanse and moisturize the delicate skin around the eyes without causing irritation ',100),(5443,'OXO Good Grips 3-in-1 Avocado ',9.99,'All in one tool splits, pits and slices avocados ',8),(6185,'Amazon Basics Silicone Hot Ski',4.78,'Insulating kitchen accessory protects hands from hot pan handles',23),(6957,'Phone Stand for Desk,BRIGHT ST',8.99,'Suitable for all smartphones and tablets, compatible with iPhone, Samsung Galaxy and all Android phone up to 12.9 inch. ',1),(7057,'Kitchiny Silicone Bottle Brush',6.99,'Better hygiene for you: Our silicone water bottle brush repels unpleasant odors, grease and stains, preventing nasty gunk build-up which is more hygienic and healthier for your family. It’s finally time to toss away that smelly sponge! ',23),(7690,'[Upgraded Version] Maxracy 2 S',5.59,' 1.Premium Material: food grade silicone rubber, mold proof, no fade, comfortable feeling. 2.Easy to use: Simply place several pieces of garlic cloves in tube and roll back and forth, peel cloves in seconds. ',5),(7938,'Tongue Scraper (2 Pack), Reduc',6.99,'Eliminate Bad Breath - A proper tongue scraper is the best remedy for bad breath available. Clean your tongue in seconds with these high quality, stainless steel tongue scrapers. Your new tounge scrapers wont just clean your mouth. These tongue scrapers w',21),(8038,'4 Boxs(200 Sheets) Paper Soap,',8.99,'1: MATERIAL: Made of natural premium quality biodegradable environment material, pure natural plant extract. Safe, without side effects. ',4),(8336,'6-in-1 Can Opener Bag Opener J',2.99,'6 in 1 Bottle opener flexible head can be adjusted to different angles, it can holds most of existing jars or bottle lids and open multiple sizes of bottles, jar caps, sealed bags, beer bottles. A heavy multifunctional opener is the best kitchenware. ',5),(8748,'Multifunctional Automatic Door',5.49,'Wide range of applicable types: 800G tension (80N), suitable for doors or doors with stronger closing force. For example: screen doors, sliding double doors, invisible doors, security doors, aluminum alloy glass doors, iron doors, office doors, interior d',5),(9294,'KitchenAid All Purpose Shears ',8.99,' Plastic guard included: These KitchenAid Soft Grip Handle Shears comes with a protective plastic blade guard Stainless steel blades: The blades are crafted from stainless steel that resists rusting ',4),(9409,'Plastic Shaker Toy ',1.28,' Plastic Shaker Child Fun Toy Noise Maker',45),(9465,'KitchenIQ 0009, Black 50009 Ed',7.15,' Imported Coarse for dull and damaged knives Fine for polishing the knife and for quick touch-ups for an already sharp knife Patented Edge Grip feature allows sharpening on the edge of the table or counter top- prevents the tip of larger knives from dragg',25),(9466,'Premium Dishwasher Magnet, Cle',7.97,'SUPERIOR QUALITY DISHWASHER MAGNET - Our Dishwasher Magnet is made of materials that are second-to-none and has an attractive silver finish. It measures 7 x 2 x 0.5 inches and weighs just 2.2 ounces.',5);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `UID` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `uname` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `addressID` int DEFAULT NULL,
  PRIMARY KEY (`UID`),
  UNIQUE KEY `UID_UNIQUE` (`UID`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `addressID_idx` (`addressID`),
  CONSTRAINT `addressID` FOREIGN KEY (`addressID`) REFERENCES `address` (`addressID`)
) ENGINE=InnoDB AUTO_INCREMENT=9410 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3664,'a@b.c','Username','Password',5373);
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

-- Dump completed on 2021-08-03 21:28:21
