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


/*Data for the table `t_class` */

insert IGNORE into `t_class`(`id`,`c_class_name`) values 
(11000,'一年级一班'),
(11001,'一年级二班');

/*Data for the table `t_student` */

insert IGNORE  into `t_student`(`id`,`c_name`,`c_class_id`) values 
(11002,'学生二',11000),
(21000,'学生一',11000);

/*Data for the table `t_student_extra` */

insert  IGNORE into `t_student_extra`(`id`,`c_student_id`,`c_father_name`,`c_mother_name`) values 
(11000,21000,'父亲一','母亲一'),
(11001,11002,'父亲二','母亲二');

/*Data for the table `t_user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
