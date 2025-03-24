CREATE DATABASE  IF NOT EXISTS `cake` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cake`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cake
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `cake_categories`
--

DROP TABLE IF EXISTS `cake_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cake_categories` (
  `cakeid` varchar(255) NOT NULL,
  `categoryid` varchar(255) NOT NULL,
  PRIMARY KEY (`cakeid`,`categoryid`),
  KEY `FKj3rragv7hvrgn6uyntt953tij` (`categoryid`),
  CONSTRAINT `FK6gpvcq0wkiqj7bofjmqxmbgju` FOREIGN KEY (`cakeid`) REFERENCES `cakeproduct` (`id`),
  CONSTRAINT `FKj3rragv7hvrgn6uyntt953tij` FOREIGN KEY (`categoryid`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cake_categories`
--

LOCK TABLES `cake_categories` WRITE;
/*!40000 ALTER TABLE `cake_categories` DISABLE KEYS */;
INSERT INTO `cake_categories` VALUES ('BPK001','BBTG'),('BPK002','BBTG'),('BPK003','BBTG'),('BPK004','BBTG'),('BPK007','BBTG'),('BV001','BBTG'),('BV002','BBTG'),('BV004','BBTG'),('BK002','BK'),('BM001','BM'),('BM002','BM'),('BM003','BM'),('BM004','BM'),('BM005','BM'),('BM006','BM'),('BM007','BM'),('BPK001','BPK'),('BPK002','BPK'),('BPK003','BPK'),('BPK004','BPK'),('BPK005','BPK'),('BPK006','BPK'),('BPK007','BPK'),('BPK008','BPK'),('BPK009','BPK'),('BSK001','BSK'),('BSK002','BSK'),('BSK003','BSK'),('BSK004','BSK'),('BSK005','BSK'),('BSK006','BSK'),('BSK007','BSK'),('BSK008','BSK'),('BSK009','BSK'),('BSK010','BSK'),('BV001','BV'),('BV002','BV'),('BV003','BV'),('BV004','BV'),('BV005','BV'),('BV006','BV'),('BV007','BV'),('BV008','BV'),('BV009','BV'),('BV010','BV'),('GT001','GT'),('GT002','GT'),('GT003','GT'),('GT004','GT'),('GT005','GT'),('GT006','GT'),('GT007','GT'),('GT008','GT'),('GT009','GT'),('GT010','GT'),('GT011','GT'),('GT012','GT'),('GT013','GT'),('GT014','GT'),('GT015','GT'),('GT016','GT'),('GT017','GT'),('GT018','GT'),('GT019','GT'),('GT020','GT'),('GT021','GT'),('GT022','GT'),('GT023','GT'),('GT024','GT'),('GT025','GT'),('GT026','GT'),('GT027','GT'),('MK001','MK'),('MK002','MK'),('MK003','MK'),('MK004','MK'),('MK005','MK'),('MK006','MK'),('MK007','MK'),('MK008','MK'),('MS001','MS'),('MS002','MS'),('MS003','MS'),('MS004','MS'),('VLT001','VLT'),('VLT002','VLT'),('VLT003','VLT'),('VLT004','VLT'),('VLT005','VLT'),('VLT006','VLT'),('VLT007','VLT'),('VLT008','VLT'),('VLT009','VLT');
/*!40000 ALTER TABLE `cake_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cakeproduct`
--

DROP TABLE IF EXISTS `cakeproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cakeproduct` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `imageURL` varchar(255) DEFAULT NULL,
  `has_size` bit(1) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cakeproduct`
--

LOCK TABLES `cakeproduct` WRITE;
/*!40000 ALTER TABLE `cakeproduct` DISABLE KEYS */;
INSERT INTO `cakeproduct` VALUES ('BK002','Caremen',17000,'bk2.png',_binary '\0','2025-03-19 02:34:55.000000'),('BM001','B√°nh M√¨ Nh√¢n Kem Ng·∫≠y',12000,'bm1.png',_binary '\0','2025-03-19 02:34:55.000000'),('BM002','B√°nh M√¨ B∆° Ru·ªëc',12000,'bm2.png',_binary '\0','2025-03-19 02:34:55.000000'),('BM003','Pizza X√∫c X√≠ch',28000,'bm3.png',_binary '\0','2025-03-19 02:34:55.000000'),('BM004','B√¥ng Lan Tr·ª©ng Mu·ªëi',20000,'bm4.png',_binary '\0','2025-03-19 02:34:55.000000'),('BM005','Hamburger B√≤',20000,'bm5.png',_binary '\0','2025-03-19 02:34:55.000000'),('BM006','B√°nh M√¨ Kem Ru·ªëc',20000,'bm6.png',_binary '\0','2025-03-19 02:34:55.000000'),('BM007','B√°nh Da B√°o Nh√¢n Kem Ng·∫≠y',20000,'bm7.png',_binary '\0','2025-03-19 02:34:55.000000'),('BPK001','Chubby Chocolate Cake',280000,'bpk1.png',_binary '','2025-03-19 02:34:55.000000'),('BPK002','Spider Man Creamy Cake',280000,'bpk2.png',_binary '','2025-03-19 02:34:55.000000'),('BPK003','Princess Strawberry Cake',280000,'bpk3.png',_binary '','2025-03-19 02:34:55.000000'),('BPK004','Chubby Blueberry Cake',280000,'bpk4.png',_binary '','2025-03-19 02:34:55.000000'),('BPK005','Money Chocolate Cake 1',280000,'bpk5.png',_binary '','2025-03-19 02:34:55.000000'),('BPK006','B√°nh T√†i L·ªôc',160000,'bpk6.png',_binary '','2025-03-19 02:34:55.000000'),('BPK007','Spider Man Matcha Cake',280000,'bpk7.png',_binary '','2025-03-19 02:34:55.000000'),('BPK008','Money Blue Cake',280000,'bpk8.png',_binary '','2025-03-19 02:34:55.000000'),('BPK009','Money Chocolate Cake 2',250000,'bpk9.png',_binary '','2025-03-19 02:34:55.000000'),('BSK001','Elderly Birthday Cake 1',280000,'bsk1.png',_binary '','2025-03-19 02:34:55.000000'),('BSK002','Wedding Anniversary 1',280000,'bsk2.png',_binary '','2025-03-19 02:34:55.000000'),('BSK003','Corporate Anniversary',280000,'bsk3.png',_binary '','2025-03-19 02:34:55.000000'),('BSK004','B√°nh T·ªïng K·∫øt NƒÉm H·ªçc',280000,'bsk4.png',_binary '','2025-03-19 02:34:55.000000'),('BSK005','Elderly Birthday Cake 2',280000,'bsk5.png',_binary '','2025-03-19 02:34:55.000000'),('BSK006','Wedding Anniversary 2',280000,'bsk6.png',_binary '','2025-03-19 02:34:55.000000'),('BSK007','Elderly Birthday Cake 3',280000,'bsk7.png',_binary '','2025-03-19 02:34:55.000000'),('BSK008','Elderly Birthday Cake 4',280000,'bsk8.png',_binary '','2025-03-19 02:34:55.000000'),('BSK009','Elderly Birthday Cake 5',280000,'bsk9.png',_binary '','2025-03-19 02:34:55.000000'),('BSK010','B√°nh Khai Tr∆∞∆°ng C·ª≠a H√†ng',280000,'bsk10.png',_binary '','2025-03-19 02:34:55.000000'),('BV001','Football Cake',250000,'bv1.png',_binary '','2025-03-19 02:34:55.000000'),('BV002','Brown Bear Creamy Cake',250000,'bv2.png',_binary '','2025-03-19 02:34:55.000000'),('BV003','Meme Strawberry Cake',250000,'bv3.png',_binary '','2025-03-19 02:34:55.000000'),('BV004','Tiger Creamy Cake',250000,'bv4.png',_binary '','2025-03-19 02:34:55.000000'),('BV005','Badminton Creamy Cake',250000,'bv5.png',_binary '','2025-03-19 02:34:55.000000'),('BV006','BI-A Cake ',250000,'bv6.png',_binary '','2025-03-19 02:34:55.000000'),('BV007','Beer Cake 1',250000,'bv7.png',_binary '','2025-03-19 02:34:55.000000'),('BV008','Simple Chocolate Cake',250000,'bv8.png',_binary '','2025-03-19 02:34:55.000000'),('BV009','Beer Cake 2',250000,'bv9.png',_binary '','2025-03-19 02:34:55.000000'),('BV010','Beer Cake 3',250000,'bv10.png',_binary '','2025-03-19 02:34:55.000000'),('GT001','Chocolate Christmast Cake',220000,'gt01.png',_binary '','2025-03-19 02:34:55.000000'),('GT002','Strawberry Cake 1',220000,'gt02.png',_binary '','2025-03-19 02:34:55.000000'),('GT003','Strawberry Cake 2',220000,'gt03.png',_binary '','2025-03-19 02:34:55.000000'),('GT004','Macaron Cake',280000,'gt04.png',_binary '','2025-03-19 02:34:55.000000'),('GT005','Lemon Cake Cake',250000,'gt05.png',_binary '','2025-03-19 02:34:55.000000'),('GT006','Queen Cake 1',250000,'gt06.png',_binary '','2025-03-19 02:34:55.000000'),('GT007','Fruit Cake 1',250000,'gt07.png',_binary '','2025-03-19 02:34:55.000000'),('GT008','Fruit Cake 2',250000,'gt08.png',_binary '','2025-03-19 02:34:55.000000'),('GT009','Rose Cake Vu√¥ng',280000,'gt09.png',_binary '','2025-03-19 02:34:55.000000'),('GT010','Chocolate Fruit Cake 1',220000,'gt10.png',_binary '','2025-03-19 02:34:55.000000'),('GT011','Cherry Cake ',220000,'gt11.png',_binary '','2025-03-19 02:34:55.000000'),('GT012','B√¥ng Lan Tr·ª©ng Mu·ªëi Gateux 1',250000,'gt12.png',_binary '','2025-03-19 02:34:55.000000'),('GT013','Queen Cake 2',250000,'gt13.png',_binary '','2025-03-19 02:34:55.000000'),('GT014','Strawberry Cake 3',220000,'gt14.png',_binary '','2025-03-19 02:34:55.000000'),('GT015','Fruit Cake Vu√¥ng 1',250000,'gt15.png',_binary '','2025-03-19 02:34:55.000000'),('GT016','Fruit Cake Vu√¥ng 2',250000,'gt16.png',_binary '','2025-03-19 02:34:55.000000'),('GT017','Chocolate Money Cake Vu√¥ng',250000,'gt17.png',_binary '','2025-03-19 02:34:55.000000'),('GT018','Money Cake',220000,'gt18.png',_binary '','2025-03-19 02:34:55.000000'),('GT019','B√¥ng Lan Tr·ª©ng Mu·ªëi Gateux 2',220000,'gt19.png',_binary '','2025-03-19 02:34:55.000000'),('GT020','Matcha Cake ',260000,'gt20.png',_binary '','2025-03-19 02:34:55.000000'),('GT021','Chocolate Fruit Cake 2',220000,'gt21.png',_binary '','2025-03-19 02:34:55.000000'),('GT022','Macaron Matcha Cake',280000,'gt22.png',_binary '','2025-03-19 02:34:55.000000'),('GT023','Mix Cake Vu√¥ng',220000,'gt23.png',_binary '','2025-03-19 02:34:55.000000'),('GT024','Matcha Fruit Cake',220000,'gt24.png',_binary '','2025-03-19 02:34:55.000000'),('GT025','Chocolate Strawberry Cake',240000,'gt25.png',_binary '','2025-03-19 02:34:55.000000'),('GT026','Macaron Chocolate Cake',240000,'gt26.png',_binary '','2025-03-19 02:34:55.000000'),('GT027','Oreo Cake ',240000,'gt27.png',_binary '','2025-03-19 02:34:55.000000'),('MK001','Orange Mini Cake',15000,'mk1.png',_binary '\0','2025-03-19 02:34:55.000000'),('MK002','Strawberry Mini Cake 1',15000,'mk2.png',_binary '\0','2025-03-19 02:34:55.000000'),('MK003','Strawberry Mini Cake 2',15000,'mk3.png',_binary '\0','2025-03-19 02:34:55.000000'),('MK004','Blueberry Mini Cake',15000,'mk4.png',_binary '\0','2025-03-19 02:34:55.000000'),('MK005','B√°nh Xu Kem',15000,'mk5.png',_binary '\0','2025-03-19 02:34:55.000000'),('MK006','B√°nh Kem Cu·ªôn Matcha',20000,'mk6.png',_binary '\0','2025-03-19 02:34:55.000000'),('MK007','B√°nh B∆° Chocolate (Theo mi·∫øng)',15000,'mk7.png',_binary '\0','2025-03-19 02:34:55.000000'),('MK008','B√°nh Donut',20000,'mk8.png',_binary '\0','2025-03-19 02:34:55.000000'),('MS001','Matcha Mousse Cake',250000,'ms1.png',_binary '','2025-03-19 02:34:55.000000'),('MS002','Passion Fruit Mousse Cake Vu√¥ng',280000,'ms2.png',_binary '','2025-03-19 02:34:55.000000'),('MS003','Passion Fruit Mouse Cake',250000,'ms3.png',_binary '','2025-03-19 02:34:55.000000'),('MS004','Orange Mousse Cake',250000,'ms4.png',_binary '','2025-03-19 02:34:55.000000'),('VLT001','Strawberry Heart Cake',260000,'vlt1.png',_binary '','2025-03-19 02:34:55.000000'),('VLT002','Creamy Strawberry Heart Cake 1',260000,'vlt2.png',_binary '','2025-03-19 02:34:55.000000'),('VLT003','Orange Heart Cake',260000,'vlt3.png',_binary '','2025-03-19 02:34:55.000000'),('VLT004','Rose Heart Cake',250000,'vlt4.png',_binary '','2025-03-19 02:34:55.000000'),('VLT005','Chocolate Rose Heart Cake',260000,'vlt5.png',_binary '','2025-03-19 02:34:55.000000'),('VLT006','Creamy Strawberry Heart Cake 2',260000,'vlt6.png',_binary '','2025-03-19 02:34:55.000000'),('VLT007','Creamy Strawberry Heart Cake 2',260000,'vlt7.png',_binary '','2025-03-19 02:34:55.000000'),('VLT008','Creamy Strawberry Heart Cake 3',250000,'vlt8.png',_binary '','2025-03-19 02:34:55.000000'),('VLT009','Chocolate Heart Cake',250000,'vlt9.png',_binary '','2025-03-19 02:34:55.000000');
/*!40000 ALTER TABLE `cakeproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` binary(16) NOT NULL,
  `cake_id` varchar(255) DEFAULT NULL,
  `imageurl` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `size` varchar(255) DEFAULT NULL,
  `user_id` binary(16) NOT NULL,
  `cakeid` varchar(255) DEFAULT NULL,
  `unitprice` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg5uhi8vpsuy0lgloxk2h4w5o6` (`user_id`),
  CONSTRAINT `FKg5uhi8vpsuy0lgloxk2h4w5o6` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `main_category_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1o9opwme2q425qr8iwr3jqqml` (`main_category_id`),
  CONSTRAINT `FK1o9opwme2q425qr8iwr3jqqml` FOREIGN KEY (`main_category_id`) REFERENCES `main_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('bbtg','B√°nh Sinh Nh·∫≠t B√© Trai - B√© G√°i','1'),('bia','B√°nh In ·∫¢nh','1'),('bk','Kh√°c','3'),('bm','B√°nh M·∫∑n','2'),('bpk','B√°nh Ph·ª• Ki·ªán','1'),('bsk','B√°nh S·ª± Ki·ªán','1'),('bv','B√°nh V·∫Ω','1'),('gt','Gateaux Kem T∆∞∆°i','1'),('mk','Mini Cake','2'),('ms','B√°nh Mousse','1'),('vlt','B√°nh Valentine - Tr√°i Tim','1');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `main_category`
--

DROP TABLE IF EXISTS `main_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `main_category` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_category`
--

LOCK TABLES `main_category` WRITE;
/*!40000 ALTER TABLE `main_category` DISABLE KEYS */;
INSERT INTO `main_category` VALUES ('1','B√°nh Sinh Nh·∫≠t'),('2','B√°nh M·∫∑n & MiniCake'),('3','B√°nh Kh√°c');
/*!40000 ALTER TABLE `main_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `online_users`
--

DROP TABLE IF EXISTS `online_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `online_users` (
  `id` binary(16) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `order_content` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `online_users`
--

LOCK TABLES `online_users` WRITE;
/*!40000 ALTER TABLE `online_users` DISABLE KEYS */;
INSERT INTO `online_users` VALUES (_binary ')\ÓgÒè¢NvèqQ+p†U','seb@gmail.com','Seb','mua nhieu don\r\n','0453678544'),(_binary '[íπ°$hO¢©F®ú\Ô\À\√','nguyenthanhvinh07052005@gmail.com','Ha Do','hi dfdldsJGF\"S','0915363866'),(_binary '\Âﬂç\'	]I÷¥kal∂qß','vinhdzai@gmail.com','vinhcris','mua b√°nh','03435339555');
/*!40000 ALTER TABLE `online_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetails` (
  `id` binary(16) NOT NULL,
  `price` double DEFAULT NULL,
  `quantity` int NOT NULL,
  `size` varchar(255) DEFAULT NULL,
  `cake_id` varchar(255) DEFAULT NULL,
  `order_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo76jdm790l8g8nuhkmrckg7g1` (`cake_id`),
  KEY `FKhnsosbuy7bhpqpnt3bjr7sh8x` (`order_id`),
  CONSTRAINT `FKhnsosbuy7bhpqpnt3bjr7sh8x` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKo76jdm790l8g8nuhkmrckg7g1` FOREIGN KEY (`cake_id`) REFERENCES `cakeproduct` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
INSERT INTO `orderdetails` VALUES (_binary 'ußàÑFd±É\∆K8\Á@+',10500000,30,'20 cm','BV003',_binary 'èß\–\‚*ÆGÑóûˆ1ÄN9k'),(_binary '\roÒ[áM.∞¢{W\n',220000,1,'16 cm','GT002',_binary 'í§îznEKvñ\’\«\”\Âﬂ∞∏'),(_binary ';\‹\‡]kIÒ´ä71\ÔZ',12000,1,'','BM001',_binary 'èß\–\‚*ÆGÑóûˆ1ÄN9k'),(_binary 'F\‡”ääEë∫\‘ZÖç%7+',350000,1,'18 cm',NULL,_binary ',Ùiï∑\–Irâx\Ê9lâ?D'),(_binary '#V¢*\0oN\ÈÉ\ƒ#G',370000,1,'22 cm','GT003',_binary ']à\"\À&Mª†\n#E}^◊©'),(_binary '&\ÔıID\—FïóL+ºµˆÆ',800000,2,'20 cm',NULL,_binary 'BSP´ZD|é/ßP§ÛÑX'),(_binary '-É©))|@	ç\0∂\0ˇz',51000,3,'','BK002',_binary ']à\"\À&Mª†\n#E}^◊©'),(_binary '2v1±öùLJé<hV|§*ª',250000,1,'16 cm','GT005',_binary 'GK_E†@|ª£pVì˝\È'),(_binary '4ALJAπ©“æ¶\Œ\ﬂ',250000,1,'16 cm','GT005',_binary 'ü\»Òºù¡MZÜbO∫Ωn^'),(_binary '7\Ô?¯\›.H€•h\’,S\ÌG≠',220000,1,'16 cm','GT002',_binary 'ü\»Òºù¡MZÜbO∫Ωn^'),(_binary 'IJ≈ÇˇYO)å&\‚7:',810000,3,'18 cm','GT024',_binary '¥˘\"≤\'\ÏL˝ìa\«F\«9'),(_binary 'L®u\ËçN\‰î\Z∑\—V3\ﬂ\◊',2000000,5,'22 cm','BV006',_binary 'V\»ˇ\ÌB∑M\rûÄ·ûª\Z'),(_binary 'fhSO\›Oyº‚ì∫å.4}',30000,2,'','BK002',_binary '˜\Ë<%#HDÉïß\0oÇ*'),(_binary 'nÆ∑∏n\"@êê;\Ô:\‡\Á',350000,1,'20 cm','BV001',_binary 'í§îznEKvñ\’\«\”\Âﬂ∞∏'),(_binary 'p(+\ÿ;K˚å\≈3á\‰^]',350000,1,'20 cm','BV001',_binary 'îm\È¸ D,¶ÄÇ#	X¸'),(_binary 'è0dãJÀ≥œë\È˘Då',700000,2,'20 cm','GT005',_binary 'ü\»Òºù¡MZÜbO∫Ωn^'),(_binary 'ò-1\  \ Dã!Iavm\Â',10810000,23,'24 cm','GT002',_binary 'Lª\—~kBeï.Iˇ¯π∂'),(_binary '¢\Ô	ãÜ\ÿOµΩ\ÔshY\‡∑M',250000,1,'16 cm','BV001',_binary 'îm\È¸ D,¶ÄÇ#	X¸'),(_binary 'æ#hô•F‚áíò`˜˘',60000,3,'','BM004',_binary '\∆~Øs8\›@◊èå.èì\ '),(_binary '\ƒ=\÷ræ}M⁄ìeÙ\\Q?)',300000,1,'16 cm',NULL,_binary 'u≈ö\Ã#L∑-\Õ¸\’N#≥'),(_binary '∆à5¡∑F∆¢K\ﬂL“ãX',300000,1,'16 cm',NULL,_binary 'µññ\‚Iìµ(˛5ˆ*a='),(_binary '\»>j GA\Ì∏ì¸~<›Ñ',40000,2,'','BM004',_binary 'XSM\›\Z\ÓGÅµî\"\Z\≈ZÖ'),(_binary '\œ˜keïˇJ7ãv\ﬁ º\Î\—\ƒ',370000,1,'22 cm','GT001',_binary 'îm\È¸ D,¶ÄÇ#	X¸'),(_binary 'ÿ¶RìùØCñ§4ŒΩ~\›\ƒ\‘',12000,1,'','BM002',_binary 'ü\»Òºù¡MZÜbO∫Ωn^'),(_binary '\Á|º$	:ED¢\ﬂT?0®ﬂï',440000,2,'16 cm','GT003',_binary 'Lª\—~kBeï.Iˇ¯π∂'),(_binary '\È\Í\‚OáGD∫Ç.±\’ÚU-7',12000,1,'','BM001',_binary 'îm\È¸ D,¶ÄÇ#	X¸'),(_binary '\ÎS|\¬\–<A∫Üø\¬¯\Î\…a',448000,16,'','BM003',_binary 'V\»ˇ\ÌB∑M\rûÄ·ûª\Z'),(_binary '\Ó°a˙±O\"øÉÚØ\Èµ[r',17000,1,'','BK002',_binary 'èß\–\‚*ÆGÑóûˆ1ÄN9k'),(_binary 'ˇÇHÑîCRØZa\·\·≥	',740000,2,'22 cm','GT024',_binary 'XSM\›\Z\ÓGÅµî\"\Z\≈ZÖ');
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` binary(16) NOT NULL,
  `order_date` datetime(6) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `user_id` binary(16) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `note` text,
  `phone` varchar(255) DEFAULT NULL,
  `status` enum('CHO_XAC_NHAN','XAC_NHAN','DANG_GIAO','DA_GIAO','DA_HUY') NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (_binary 'µññ\‚Iìµ(˛5ˆ*a=','2025-03-24 13:43:36.709790',300000,_binary 'Pˇ&ˇq`F»ç∏&\Î,KK\€','test','H√† N·ªôi','dung david','','0353963866','DA_GIAO'),(_binary 'îm\È¸ D,¶ÄÇ#	X¸','2025-04-19 13:11:16.966150',982000,_binary '	d\…Bôπàå\\à\‚','ngon','H√† N·ªôi','Nguyen Thanh Vinh','','0353963865','DA_GIAO'),(_binary ',Ùiï∑\–Irâx\Ê9lâ?D','2025-03-23 23:20:23.497065',350000,_binary 'Pˇ&ˇq`F»ç∏&\Î,KK\€','successful','H√† N·ªôi','dung david','','0353963866','DA_GIAO'),(_binary 'BSP´ZD|é/ßP§ÛÑX','2025-03-23 23:38:34.151435',800000,_binary 'Pˇ&ˇq`F»ç∏&\Î,KK\€','jennie','H√† N·ªôi','dung david','','0353963866','DA_GIAO'),(_binary 'GK_E†@|ª£pVì˝\È','2025-05-19 00:27:11.696951',250000,_binary 'Pˇ&ˇq`F»ç∏&\Î,KK\€','ddc','H√† N·ªôi','dung david','','0353963866','XAC_NHAN'),(_binary 'Lª\—~kBeï.Iˇ¯π∂','2025-03-19 01:13:28.318096',11250000,_binary 'Pˇ&ˇq`F»ç∏&\Î,KK\€','mua cho doanh nghiep','H√† N·ªôi','dung david','','0353963866','XAC_NHAN'),(_binary 'U3põd¨Fˇ≤c§S;•\œ','2025-03-23 20:07:25.109080',600,_binary 'Pˇ&ˇq`F»ç∏&\Î,KK\€','ko','H√† N·ªôi','dung david','ko','0353963866','DA_HUY'),(_binary 'V\»ˇ\ÌB∑M\rûÄ·ûª\Z','2025-03-23 23:44:35.562184',2448000,_binary '	d\…Bôπàå\\à\‚','678 La Th√†nh','H√† N·ªôi','Nguyen Thanh Vinh','pizza th√™m nhi·ªÅu s·ªët\r\n','0353963865','DANG_GIAO'),(_binary 'XSM\›\Z\ÓGÅµî\"\Z\≈ZÖ','2025-03-19 11:35:58.711116',780000,_binary '	d\…Bôπàå\\à\‚','Hoa Lac','H√† N·ªôi','Nguyen Thanh Vinh','','0353963865','DA_HUY'),(_binary ']à\"\À&Mª†\n#E}^◊©','2025-03-19 12:42:36.758304',421000,_binary 'Pˇ&ˇq`F»ç∏&\Î,KK\€','admin','H√† N·ªôi','dung david','','0353963866','CHO_XAC_NHAN'),(_binary 'u≈ö\Ã#L∑-\Õ¸\’N#≥','2025-03-24 13:45:50.541967',300000,_binary 'Pˇ&ˇq`F»ç∏&\Î,KK\€','test','H√† N·ªôi','dung david','','0353963866','DA_GIAO'),(_binary 'èß\–\‚*ÆGÑóûˆ1ÄN9k','2025-03-19 12:33:55.290440',10529000,_binary '	d\…Bôπàå\\à\‚','mua nhieu','H√† N·ªôi','Nguyen Thanh Vinh','','0353963865','DA_GIAO'),(_binary 'í§îznEKvñ\’\«\”\Âﬂ∞∏','2025-03-19 17:25:45.113201',570000,_binary 'Pˇ&ˇq`F»ç∏&\Î,KK\€','ok','H√† N·ªôi','dung david','','0353963866','CHO_XAC_NHAN'),(_binary 'ü\»Òºù¡MZÜbO∫Ωn^','2025-03-19 00:14:09.081518',1182000,_binary 'Pˇ&ˇq`F»ç∏&\Î,KK\€','707, Yen Hoa Sunshine residential building, Vu Pham Ham Street, Cau Giay District, Ha Noi, Vietnam','H√† N·ªôi','Ha Do','','0915363866','XAC_NHAN'),(_binary '¥˘\"≤\'\ÏL˝ìa\«F\«9','2025-03-24 13:35:36.279838',810000,_binary 'Pˇ&ˇq`F»ç∏&\Î,KK\€','707, Yen Hoa Sunshine residential building, Vu Pham Ham Street, Cau Giay District, Ha Noi, Vietnam','H√† N·ªôi','Ha Do','','0915363866','CHO_XAC_NHAN'),(_binary '\∆~Øs8\›@◊èå.èì\ ','2025-04-19 01:21:50.578216',60000,_binary 'Pˇ&ˇq`F»ç∏&\Î,KK\€','ha noi','H√† N·ªôi','dung david','it trung muoi','0353963866','DA_GIAO'),(_binary '˜\Ë<%#HDÉïß\0oÇ*','2025-03-19 00:35:32.204787',30000,_binary 'Pˇ&ˇq`F»ç∏&\Î,KK\€','ok','H√† N·ªôi','dung david','','0353963866','XAC_NHAN');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` binary(16) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (_binary '}µ˛\»EE¡∑˜z+r¸Ò','ROLE_USER'),(_binary '}µ˛\»EE¡∑˜z+r¸Ú','ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` binary(16) NOT NULL,
  `role_id` binary(16) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (_binary 'å4\∆\ÁçH:ÇgÀ≤l\›˜ ',_binary '}µ˛\»EE¡∑˜z+r¸Ò'),(_binary '	d\…Bôπàå\\à\‚',_binary '}µ˛\»EE¡∑˜z+r¸Ò'),(_binary '1`\Ã\◊GJdÄK\‘\‰Ãø—ë',_binary '}µ˛\»EE¡∑˜z+r¸Ò'),(_binary 'Pˇ&ˇq`F»ç∏&\Î,KK\€',_binary '}µ˛\»EE¡∑˜z+r¸Ò'),(_binary 'Ñ∂q¸˝πCªcNzÑ\Ã\Ë\Ï',_binary '}µ˛\»EE¡∑˜z+r¸Ò'),(_binary 'ì]~?\'\0O±¨R; Ç˙ú',_binary '}µ˛\»EE¡∑˜z+r¸Ò'),(_binary '\Ê\Ì\'\ÌñF ü\ÌB\\ië+\‹',_binary '}µ˛\»EE¡∑˜z+r¸Ò'),(_binary 'Pˇ&ˇq`F»ç∏&\Î,KK\€',_binary '}µ˛\»EE¡∑˜z+r¸Ú'),(_binary 'Ñ∂q¸˝πCªcNzÑ\Ã\Ë\Ï',_binary '}µ˛\»EE¡∑˜z+r¸Ú');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` binary(16) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `reset_token` varchar(255) DEFAULT NULL,
  `token_expiry` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (_binary 'å4\∆\ÁçH:ÇgÀ≤l\›˜ ','vic','$2a$10$rBM4sMZ7FEaswy/7jnCN5OqbTYkY0Y.0E.DYkCJaSjBLSWQx01aqC','vic@gmail.com','Sebastian','Vic',NULL,NULL,NULL),(_binary '	d\…Bôπàå\\à\‚','crisvinh','$2a$10$ZCFp5VjM.wM4aDCTt4fAdO21ja6fUbeJYToiq7T9AmD6uO6zAbrz2','nguyenthanhvinh07052005@gmail.com','Vinh','Nguyen Thanh','0353963865','a7f23643-bbd7-4343-94e3-c0e21c68f7c7',1742800016438),(_binary '1`\Ã\◊GJdÄK\‘\‰Ãø—ë','ducthuannguyen','$2a$10$fFxADpoNQTPVGLuTylCkf.4SLn2XBA/Qln8/n0Pty7JtiLeY.e7Ny','nguyenthanhvinh07052004@gmail.com','nguyen','thuan','0353963867',NULL,NULL),(_binary 'Pˇ&ˇq`F»ç∏&\Î,KK\€','david','$2a$10$KFXHHZ8PIKBEFqiVid63kulkqxSjTIHUEjvZXabbzQ2LJMHRX6faW','nguyenvietdung0805@gmail.com','david','dung','0353963866',NULL,NULL),(_binary 'Ñ∂q¸˝πCªcNzÑ\Ã\Ë\Ï','admin','$2a$10$n3vkMCUJQOOLBomsBBlha.krrEhy/R.wjKQ1fOj08KOG7dWX5h0nK','admin@gmail.com','admin','admin',NULL,NULL,NULL),(_binary 'ì]~?\'\0O±¨R; Ç˙ú','captain','$2a$10$x4OzVIca.nxNAxMoyIb0VeY/nTo5OSu5tZtxC8nZ4NbpgPNQI/1oG','captain@gmail.com','captain','vi',NULL,NULL,NULL),(_binary '\Ê\Ì\'\ÌñF ü\ÌB\\ië+\‹','gay','$2a$10$VpVf2S.zhJpvpedJypfaBOcJYCEYAyXx9jWcX/cIBQcmRfmYlG0d.','gay@gmail.com','Gay','So',NULL,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-24 23:07:54
