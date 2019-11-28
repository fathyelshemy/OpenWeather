CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL ,
  `mobile_number` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


INSERT INTO `weatherforcast`.`employee` (`id`, `email`, `mobile_number`, `password`, `username`, `role`) VALUES ('1', 'admin@orangelabs.com', '01227343239', '12345678', 'admin','ROLE_ADMIN');

