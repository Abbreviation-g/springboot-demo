/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.16-log : Database - db_spring_demo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `t_class` */

CREATE TABLE IF NOT EXISTS `t_class` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `c_class_name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11002 DEFAULT CHARSET=utf8;

/*Table structure for table `t_student` */

CREATE TABLE IF NOT EXISTS `t_student` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `c_name` varchar(12) DEFAULT NULL,
  `c_class_id` int(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `c_class_id` (`c_class_id`),
  CONSTRAINT `t_student_ibfk_1` FOREIGN KEY (`c_class_id`) REFERENCES `t_class` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21001 DEFAULT CHARSET=utf8;

/*Table structure for table `t_student_extra` */

CREATE TABLE IF NOT EXISTS `t_student_extra` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `c_student_id` int(12) DEFAULT NULL,
  `c_father_name` varchar(12) DEFAULT NULL,
  `c_mother_name` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `t_student_extra_ibfk_1` (`c_student_id`),
  CONSTRAINT `t_student_extra_ibfk_1` FOREIGN KEY (`c_student_id`) REFERENCES `t_student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11002 DEFAULT CHARSET=utf8;

/*Table structure for table `t_user` */

CREATE TABLE IF NOT EXISTS `t_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `gender` int(1) NOT NULL COMMENT '0:male,1:female',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
