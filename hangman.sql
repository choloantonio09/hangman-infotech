CREATE DATABASE  IF NOT EXISTS `hangman` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hangman`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: hangman
-- ------------------------------------------------------
-- Server version	5.7.13-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `easy_bank`
--

DROP TABLE IF EXISTS `easy_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `easy_bank` (
  `question_id` int(11) NOT NULL,
  `question` varchar(2000) DEFAULT NULL,
  `answer` varchar(1000) DEFAULT NULL,
  `question_points` int(11) DEFAULT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `easy_bank`
--

LOCK TABLES `easy_bank` WRITE;
/*!40000 ALTER TABLE `easy_bank` DISABLE KEYS */;
INSERT INTO `easy_bank` VALUES (1,'Process and store all forms of data in binary format','COMPUTERS',6000),(2,'In ASCII, what does “C” stands for?','CODE',6000),(3,'7-bit code: 128 characters ','ASCII',6000),(4,'Example of this data are Photographs, figures, icons, drawings, charts and graphs ','IMAGE DATA',6000),(5,'A small area with associated coordinate location on bitmap images','PIXELS',6000),(6,'black, white or 254 shades of gray. 1 byte per pixel','GRAY SCALE',6000),(7,'Amount of detail affecting clarity and sharpness of an image','RESOLUTION',6000),(8,'color translation table that uses a code for each pixel rather than actual color value','PALETTE',6000),(9,'Transmission and processing requirements less demanding than those for video ','AUDIO DATA',6000),(10,'Continuous data such as sound or images','ANALOG',6000),(11,'Using WAV, AVI, MP3, MIDI, and WMA as standards','SOUND',6000),(12,'Using Quicktime, MPEG-2, RealVideo, and WMV as standards','VIDEO',6000),(13,'data that describes or interprets the meaning of data','METADATA',6000);
/*!40000 ALTER TABLE `easy_bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hard_bank`
--

DROP TABLE IF EXISTS `hard_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hard_bank` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(2000) DEFAULT NULL,
  `answer` varchar(1000) DEFAULT NULL,
  `question_points` int(11) DEFAULT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hard_bank`
--

LOCK TABLES `hard_bank` WRITE;
/*!40000 ALTER TABLE `hard_bank` DISABLE KEYS */;
INSERT INTO `hard_bank` VALUES (1,'Alphanumeric data from credit cards','Magnetic Stripe Reader',18000),(2,'In TIFF, what does “T” stands for?','Tagged',18000),(3,'An Object Graphics Software used in vector illustration, layout, bitmap creation, image-editing, painting and animation software','CorelDraw',18000),(4,'What does MIDI stands for? ','Musical Instrument Digital Interface',18000),(5,'Number of times per second that sound is measured during the recording process','Sampling Rate',18000),(6,'An example of this is large blocks of the same color','Repetition',18000),(7,'A binary code that detects digital errors during transmission ','Error Detection Code',18000),(8,'The most common error detection code used is what? ','Parity Bit',18000),(9,'Renormalizing we retain the larger exponent and _____the low-order bit.','Truncate',18000),(10,'A type of checksum used primarily in data communications that determines whether an error has occurred within a large block or stream of information bytes. ','Cyclic redundancy check',18000),(11,'A code that can be thought of as a CRC that operates over entire characters instead of only a few bits','Reed Soloman',18000);
/*!40000 ALTER TABLE `hard_bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medium_bank`
--

DROP TABLE IF EXISTS `medium_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medium_bank` (
  `question_id` int(11) NOT NULL,
  `question` varchar(2000) DEFAULT NULL,
  `answer` varchar(1000) DEFAULT NULL,
  `question_points` int(11) DEFAULT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medium_bank`
--

LOCK TABLES `medium_bank` WRITE;
/*!40000 ALTER TABLE `medium_bank` DISABLE KEYS */;
INSERT INTO `medium_bank` VALUES (1,'Used in applications that require fast, accurate and repetitive input with minimal employee training ','Bar Code Readers',12000),(2,'Preferred when image contains large amount of detail and processing requirements are fairly simple ','Bitmap Images',12000),(3,'black or white bitmap display. 1 bit per pixel','Monochrome',12000),(4,'16 colors, 256 colors, or 24-bit true color (16.7 million colors). 4, 8, and 24 bits respectively ','Color Graphics',12000),(5,'Designed to replace GIF and JPEG for Internet applications ','PNG',12000),(6,'Macintosh coding scheme that includes both identification and presentation requirement for characters','Glyphs',12000),(7,'General-purpose format for storing and reproducing small snippets of sound ','WAV',12000),(8,'A ______form is used for storing a floating-point number in memory','normalized',12000),(9,'Alphabetic sorting if software handles mixed upper- and lowercase codes','Collating Sequence',12000);
/*!40000 ALTER TABLE `medium_bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT '0',
  `user_level` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (32,'Hannah Pigao',20000,'EASY'),(56,'Cholo',12000,'EASY'),(72,'Reina',26000,'MEDIUM'),(73,'Hazel',36000,'MEDIUM'),(74,'Aylmera',36000,'MEDIUM'),(75,'Maryrose',33000,'HARD'),(76,'Cholo',87000,'HARD'),(77,'Famu',12000,'MEDIUM'),(138,'Banana',1000,'EASY'),(139,'Banana Pigao',6000,'EASY'),(140,'Rose',40000,'MEDIUM'),(141,'JEFFY',0,'EASY');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'hangman'
--

--
-- Dumping routines for database 'hangman'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-01  7:57:48
