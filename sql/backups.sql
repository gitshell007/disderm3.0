-- MySQL dump 10.13  Distrib 8.0.21, for Linux (x86_64)
--
-- Host: localhost    Database: dosaolivar
-- ------------------------------------------------------
-- Server version	8.0.21-0ubuntu0.20.04.4

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `blocked_ip`
--

DROP TABLE IF EXISTS `blocked_ip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blocked_ip` (
  `_id` int NOT NULL AUTO_INCREMENT,
  `ip` varchar(200) NOT NULL,
  `agent` varchar(500) DEFAULT NULL,
  `attempts` int DEFAULT NULL,
  `enabled` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `boquillas`
--

DROP TABLE IF EXISTS `boquillas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `boquillas` (
  `boquillas_id` int unsigned NOT NULL AUTO_INCREMENT,
  `index` varchar(20) DEFAULT NULL,
  `marca` varchar(100) DEFAULT NULL,
  `modelo` varchar(100) DEFAULT NULL,
  `submodelo` varchar(100) DEFAULT NULL,
  `tipo` varchar(100) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `codref` varchar(20) DEFAULT NULL,
  `caudal` varchar(20) DEFAULT NULL,
  `pmax` varchar(20) DEFAULT NULL,
  `pmin` varchar(20) DEFAULT NULL,
  `pmaxrecomendada` varchar(20) DEFAULT NULL,
  `pminrecomendada` varchar(20) DEFAULT NULL,
  `angulochorro` varchar(20) DEFAULT NULL,
  `codfoto` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`boquillas_id`)
) ENGINE=InnoDB AUTO_INCREMENT=473 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `densidad_hojas`
--

DROP TABLE IF EXISTS `densidad_hojas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `densidad_hojas` (
  `densidad_hojas_id` int unsigned NOT NULL AUTO_INCREMENT,
  `densidad_hojas_des` varchar(100) DEFAULT NULL,
  `densidad_hojas_code` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`densidad_hojas_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dispositivos`
--

DROP TABLE IF EXISTS `dispositivos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dispositivos` (
  `DispositivoID` int unsigned NOT NULL AUTO_INCREMENT,
  `JabberID` varchar(255) NOT NULL DEFAULT '',
  `Token` varchar(512) NOT NULL,
  `SO` varchar(45) NOT NULL DEFAULT '',
  `SO_VERSION` varchar(40) NOT NULL DEFAULT '',
  `model` varchar(30) NOT NULL DEFAULT '',
  `name` varchar(100) NOT NULL DEFAULT '',
  `app_version` varchar(45) DEFAULT '1.0',
  `PubKey` varchar(10000) DEFAULT NULL,
  `active` int DEFAULT '0',
  `PrivKey` varchar(32) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `last_login_at` datetime DEFAULT NULL,
  `created_at_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_login_at_ts` timestamp NULL DEFAULT NULL,
  `badge` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`DispositivoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dosaolivar_users`
--

DROP TABLE IF EXISTS `dosaolivar_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dosaolivar_users` (
  `dosaolivar_users_id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(150) DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `visible_name` varchar(100) DEFAULT NULL,
  `user_type_id` int DEFAULT NULL,
  `profile_image` mediumtext,
  `enabled` int DEFAULT NULL,
  `phone_mobile` varchar(50) DEFAULT NULL,
  `localidad` varchar(50) DEFAULT NULL,
  `provincia` varchar(50) DEFAULT NULL,
  `cp` varchar(6) DEFAULT NULL,
  `app_platform` varchar(250) DEFAULT NULL,
  `app_device_model` varchar(250) DEFAULT NULL,
  `app_device_token` varchar(250) DEFAULT NULL,
  `fecha_alta_sistema` datetime DEFAULT NULL,
  `fecha_ultimo_acceso` datetime DEFAULT NULL,
  `message` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`dosaolivar_users_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `equipos`
--

DROP TABLE IF EXISTS `equipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipos` (
  `equipos_id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `fabricante` varchar(100) DEFAULT NULL,
  `nbastidor` varchar(100) DEFAULT NULL,
  `capacidad` varchar(100) DEFAULT NULL,
  `nroma` varchar(100) DEFAULT NULL,
  `nropo` varchar(100) DEFAULT NULL,
  `nboquillas` varchar(20) DEFAULT NULL,
  `boquilla1id` varchar(20) DEFAULT NULL,
  `boquilla1cantidad` varchar(20) DEFAULT NULL,
  `boquilla1url` varchar(200) DEFAULT NULL,
  `boquilla1des` varchar(200) DEFAULT NULL,
  `boquilla2id` varchar(20) DEFAULT NULL,
  `boquilla2url` varchar(200) DEFAULT NULL,
  `boquilla2cantidad` varchar(20) DEFAULT NULL,
  `boquilla2des` varchar(200) DEFAULT NULL,
  `boquilla3id` varchar(20) DEFAULT NULL,
  `boquilla3url` varchar(200) DEFAULT NULL,
  `boquilla3des` varchar(200) DEFAULT NULL,
  `boquilla3cantidad` varchar(20) DEFAULT NULL,
  `boquilla4id` varchar(20) DEFAULT NULL,
  `boquilla4cantidad` varchar(20) DEFAULT NULL,
  `foto` longtext,
  PRIMARY KEY (`equipos_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `log_sessions`
--

DROP TABLE IF EXISTS `log_sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log_sessions` (
  `_id` int NOT NULL AUTO_INCREMENT,
  `session_id` varchar(45) DEFAULT NULL,
  `host` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `success` tinyint(1) DEFAULT '0',
  `ts_in` datetime DEFAULT NULL,
  `ts_out` datetime DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logs` (
  `logs_id` int unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `user` varchar(100) DEFAULT NULL,
  `des` mediumtext,
  PRIMARY KEY (`logs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `logs2`
--

DROP TABLE IF EXISTS `logs2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logs2` (
  `logs2_id` int unsigned NOT NULL,
  `type` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `user` varchar(100) DEFAULT NULL,
  `des` mediumtext CHARACTER SET utf8mb4 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `marco_plantacion`
--

DROP TABLE IF EXISTS `marco_plantacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marco_plantacion` (
  `marco_plantacion_id` int unsigned NOT NULL AUTO_INCREMENT,
  `marco_plantacion_des` varchar(100) DEFAULT NULL,
  `marco_plantacion_code` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`marco_plantacion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `municipios`
--

DROP TABLE IF EXISTS `municipios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `municipios` (
  `municipios_id` int unsigned NOT NULL AUTO_INCREMENT,
  `localidad` varchar(250) DEFAULT NULL,
  `provincia` varchar(50) DEFAULT NULL,
  `cp` varchar(10) DEFAULT NULL,
  `localidad_provincia` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`municipios_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14666 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `parcelas`
--

DROP TABLE IF EXISTS `parcelas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parcelas` (
  `parcelas_id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `area` varchar(20) DEFAULT NULL,
  `area_metros` varchar(20) DEFAULT NULL,
  `area_decimales` varchar(20) DEFAULT NULL,
  `provincia` varchar(150) DEFAULT NULL,
  `localidad` varchar(150) DEFAULT NULL,
  `agregado` varchar(20) DEFAULT NULL,
  `zona` varchar(50) DEFAULT NULL,
  `sigpac` varchar(50) DEFAULT NULL,
  `poligono` varchar(50) DEFAULT NULL,
  `parcela` varchar(20) DEFAULT NULL,
  `recinto` varchar(20) DEFAULT NULL,
  `a_metros` varchar(20) DEFAULT NULL,
  `a_decimales` varchar(20) DEFAULT NULL,
  `s_metros` varchar(20) DEFAULT NULL,
  `s_decimales` varchar(20) DEFAULT NULL,
  `densidad_hojas_id` varchar(20) DEFAULT NULL,
  `sistema_cultivo_id` varchar(20) DEFAULT NULL,
  `marco_cultivo_id` varchar(20) DEFAULT NULL,
  `altura_media_copa` varchar(20) DEFAULT NULL,
  `diametro_media_copa` varchar(20) DEFAULT NULL,
  `copa_h` varchar(20) DEFAULT NULL,
  `copa_d1` varchar(20) DEFAULT NULL,
  `copa_d2` varchar(20) DEFAULT NULL,
  `copa_vca` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`parcelas_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `productos_id` int unsigned NOT NULL AUTO_INCREMENT,
  `nombre_plaga` varchar(100) DEFAULT NULL,
  `nombre_formulado` varchar(300) CHARACTER SET utf8mb4  DEFAULT NULL,
  `nombre_comercial` varchar(100) DEFAULT NULL,
  `url_pdf` varchar(300) DEFAULT NULL,
  `empresa` varchar(300) DEFAULT NULL,
  `domicilio` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`productos_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1655 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sistema_cultivo`
--

DROP TABLE IF EXISTS `sistema_cultivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sistema_cultivo` (
  `sistema_cultivo_id` int unsigned NOT NULL AUTO_INCREMENT,
  `sistema_cultivo_code` varchar(2) DEFAULT NULL,
  `sistema_cultivo_des` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sistema_cultivo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tratamientos`
--

DROP TABLE IF EXISTS `tratamientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tratamientos` (
  `tratamientos_id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `velocidad` varchar(20) DEFAULT NULL,
  `fecha` varchar(100) DEFAULT NULL,
  `hora` varchar(100) DEFAULT NULL,
  `fecha_fin` varchar(100) DEFAULT NULL,
  `hora_fin` varchar(100) DEFAULT NULL,
  `presion` varchar(20) DEFAULT NULL,
  `parcela_id` varchar(20) DEFAULT NULL,
  `equipo_id` varchar(20) DEFAULT NULL,
  `parcela_nombre` varchar(100) DEFAULT NULL,
  `equipo_nombre` varchar(100) DEFAULT NULL,
  `const_k` varchar(20) DEFAULT NULL,
  `const_qb` varchar(20) DEFAULT NULL,
  `nboquillas` varchar(20) DEFAULT NULL,
  `volumen_caldo` varchar(20) DEFAULT NULL,
  `producto1id` varchar(20) DEFAULT NULL,
  `producto1des` varchar(100) DEFAULT NULL,
  `producto1cantidad` varchar(20) DEFAULT NULL,
  `producto2id` varchar(20) DEFAULT NULL,
  `producto2des` varchar(100) DEFAULT NULL,
  `producto2cantidad` varchar(20) DEFAULT NULL,
  `producto3id` varchar(20) DEFAULT NULL,
  `producto3des` varchar(100) DEFAULT NULL,
  `producto3cantidad` varchar(20) DEFAULT NULL,
  `materia1id` varchar(20) DEFAULT NULL,
  `materia1des` varchar(100) DEFAULT NULL,
  `materia2id` varchar(20) DEFAULT NULL,
  `materia2des` varchar(100) DEFAULT NULL,
  `materia3id` varchar(20) DEFAULT NULL,
  `materia3des` varchar(100) DEFAULT NULL,
  `funcion1id` varchar(20) DEFAULT NULL,
  `funcion1des` varchar(100) DEFAULT NULL,
  `funcion2id` varchar(20) DEFAULT NULL,
  `funcion2des` varchar(100) DEFAULT NULL,
  `funcion3id` varchar(20) DEFAULT NULL,
  `funcion3des` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`tratamientos_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tratamientos_gps`
--

DROP TABLE IF EXISTS `tratamientos_gps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tratamientos_gps` (
  `tratamientos_gps_id` int unsigned NOT NULL AUTO_INCREMENT,
  `tratamientos_id` int DEFAULT NULL,
  `latitud` varchar(20) DEFAULT NULL,
  `longitud` varchar(20) DEFAULT NULL,
  `fecha` varchar(20) DEFAULT NULL,
  `hora` varchar(20) DEFAULT NULL,
  `timeline` datetime DEFAULT NULL,
  PRIMARY KEY (`tratamientos_gps_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60730 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tratamientos_sensores`
--

DROP TABLE IF EXISTS `tratamientos_sensores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tratamientos_sensores` (
  `tratamientos_sensores_id` int unsigned NOT NULL AUTO_INCREMENT,
  `tratamientos_id` int DEFAULT NULL,
  `caudal` varchar(20) DEFAULT NULL,
  `presion` varchar(20) DEFAULT NULL,
  `fecha` varchar(20) DEFAULT NULL,
  `hora` varchar(20) DEFAULT NULL,
  `ev` varchar(20) DEFAULT NULL,
  `timeline` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`tratamientos_sensores_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2125 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `upload`
--

DROP TABLE IF EXISTS `upload`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `upload` (
  `upload_id` int unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `upload_date` date DEFAULT NULL,
  PRIMARY KEY (`upload_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_type` (
  `user_type_id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_type_des` varchar(20) DEFAULT NULL,
  `user_type_code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-04 19:05:58
