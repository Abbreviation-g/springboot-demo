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

INSERT INTO db_spring_demo.qa_log_detail (request_id, session_id, user_id, question, answer, service_cards,
                                          `references`, `options`, suggestions, request_time, deleted, deleted_by_user)
VALUES ('request_id_1', 'session_id_1', 'user_id_1', '1', 'a1', NULL, NULL, NULL, NULL, '2026-02-25 00:00:01', 0, 0),
       ('request_id_10', 'session_id_1', 'user_id_1', '10', 'a10', NULL, NULL, NULL, NULL, '2026-02-25 00:00:10', 0, 0),
       ('request_id_11', 'session_id_1', 'user_id_1', '11', 'a11', NULL, NULL, NULL, NULL, '2026-02-25 00:00:11', 0, 0),
       ('request_id_12', 'session_id_1', 'user_id_1', '12', 'a12', NULL, NULL, NULL, NULL, '2026-02-25 00:00:12', 0, 0),
       ('request_id_13', 'session_id_1', 'user_id_1', '13', 'a13', NULL, NULL, NULL, NULL, '2026-02-25 00:00:13', 0, 0),
       ('request_id_14', 'session_id_1', 'user_id_1', '14', 'a14', NULL, NULL, NULL, NULL, '2026-02-25 00:00:14', 0, 0),
       ('request_id_15', 'session_id_1', 'user_id_1', '15', 'a15', NULL, NULL, NULL, NULL, '2026-02-25 00:00:15', 0, 0),
       ('request_id_16', 'session_id_1', 'user_id_1', '16', 'a16', NULL, NULL, NULL, NULL, '2026-02-25 00:00:16', 0, 0),
       ('request_id_17', 'session_id_1', 'user_id_1', '17', 'a17', NULL, NULL, NULL, NULL, '2026-02-25 00:00:17', 0, 0),
       ('request_id_18', 'session_id_1', 'user_id_1', '18', 'a18', NULL, NULL, NULL, NULL, '2026-02-25 00:00:18', 0, 0);
INSERT INTO db_spring_demo.qa_log_detail (request_id, session_id, user_id, question, answer, service_cards,
                                          `references`, `options`, suggestions, request_time, deleted, deleted_by_user)
VALUES ('request_id_19', 'session_id_1', 'user_id_1', '19', 'a19', NULL, NULL, NULL, NULL, '2026-02-25 00:00:19', 0, 0),
       ('request_id_2', 'session_id_1', 'user_id_1', '2', 'a2', NULL, NULL, NULL, NULL, '2026-02-25 00:00:02', 0, 0),
       ('request_id_20', 'session_id_1', 'user_id_1', '20', 'a20', NULL, NULL, NULL, NULL, '2026-02-25 00:00:20', 0, 0),
       ('request_id_21', 'session_id_1', 'user_id_1', '21', 'a21', NULL, NULL, NULL, NULL, '2026-02-25 00:00:21', 0, 0),
       ('request_id_22', 'session_id_1', 'user_id_1', '22', 'a22', NULL, NULL, NULL, NULL, '2026-02-25 00:00:22', 0, 0),
       ('request_id_23', 'session_id_1', 'user_id_1', '23', 'a23', NULL, NULL, NULL, NULL, '2026-02-25 00:00:23', 0, 0),
       ('request_id_24', 'session_id_1', 'user_id_1', '24', 'a24', NULL, NULL, NULL, NULL, '2026-02-25 00:00:24', 0, 0),
       ('request_id_3', 'session_id_1', 'user_id_1', '3', 'a3', NULL, NULL, NULL, NULL, '2026-02-25 00:00:03', 0, 0),
       ('request_id_4', 'session_id_1', 'user_id_1', '4', 'a4', NULL, NULL, NULL, NULL, '2026-02-25 00:00:04', 0, 0),
       ('request_id_5', 'session_id_1', 'user_id_1', '5', 'a5', NULL, NULL, NULL, NULL, '2026-02-25 00:00:05', 0, 0);
INSERT INTO db_spring_demo.qa_log_detail (request_id, session_id, user_id, question, answer, service_cards,
                                          `references`, `options`, suggestions, request_time, deleted, deleted_by_user)
VALUES ('request_id_6', 'session_id_1', 'user_id_1', '6', 'a6', NULL, NULL, NULL, NULL, '2026-02-25 00:00:06', 0, 0),
       ('request_id_7', 'session_id_1', 'user_id_1', '7', 'a7', NULL, NULL, NULL, NULL, '2026-02-25 00:00:07', 0, 0),
       ('request_id_8', 'session_id_1', 'user_id_1', '8', 'a8', NULL, NULL, NULL, NULL, '2026-02-25 00:00:08', 0, 0),
       ('request_id_9', 'session_id_1', 'user_id_1', '9', 'a9', NULL, NULL, NULL, NULL, '2026-02-25 00:00:09', 0, 0);
