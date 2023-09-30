CREATE DATABASE IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `mydb`;
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
-- Dumping structure for table mydb.account
CREATE TABLE IF NOT EXISTS `account` (
  `account_id` varchar(50) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping structure for table mydb.grant_access
CREATE TABLE IF NOT EXISTS `grant_access` (
  `role_id` varchar(50) NOT NULL,
  `account_id` varchar(50) NOT NULL,
  `is_grant` bit(1) NOT NULL DEFAULT b'1',
  `note` varchar(250) DEFAULT '',
  PRIMARY KEY (`role_id`,`account_id`),
  KEY `account_grant` (`account_id`),
  CONSTRAINT `account_grant` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_grant` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping structure for table mydb.log
CREATE TABLE IF NOT EXISTS `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(50) NOT NULL,
  `login_time` datetime NOT NULL,
  `logout_time` datetime NOT NULL,
  `notes` varchar(250) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci COMMENT='ghi logs';

-- Dumping structure for table mydb.role
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` varchar(50) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Insert data into the account table
INSERT INTO `account` (`account_id`, `full_name`, `password`, `email`, `phone`, `status`) VALUES
('Phu', 'Nguyen Van Phu', '123', 'phuvan@gmail.com', '0904567890', 1),
('teo', 'Nguyen Van Teo', '123', 'teo@gmail.com', '0903123456', 1),
('ti', 'Nguyen Van Ti', '123', 'ti@gmail.com', '090312321', 1);

-- Insert data into the role table
INSERT INTO `role` (`role_id`, `role_name`, `description`, `status`) VALUES
('admin', 'administrator', 'admin role', 1),
('user', 'user', 'user role', 1);

-- Insert data into the grant_access table
INSERT INTO `grant_access` (`role_id`, `account_id`, `is_grant`, `note`) VALUES
('admin', 'Phu', 1, ''),
('user', 'teo', 1, '');

SELECT role.role_name FROM role INNER JOIN grant_access ON role.role_id = grant_access.role_id WHERE grant_access.account_id = 'Phu'
SELECT * FROM ACCOUNT
SELECT * FROM grant_access
SELECT * FROM log
SELECT * FROM role

ALTER TABLE log MODIFY logout_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
-- ALTER TABLE log MODIFY login_time TIMESTAMP NULL;

