/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `address` varchar(100) NOT NULL,
                          `first_name` varchar(80) NOT NULL,
                          `gender` varchar(6) NOT NULL,
                          `last_name` varchar(80) NOT NULL,
                          PRIMARY KEY (`id`)
);