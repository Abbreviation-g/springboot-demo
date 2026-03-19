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

-- db_spring_demo.qa_log_detail definition

CREATE TABLE `qa_log_detail` (
     `request_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
     `session_id` varchar(64) DEFAULT NULL,
     `user_id` varchar(64) DEFAULT NULL,
     `question` text,
     `answer` text,
     `service_cards` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '核心业务卡片列表 (用于前端突出展示事项入口)',
     `references` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '文本引用来源列表 (对应文中的 [1][2]...)',
     `options` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '澄清选项列表 (当意图不明确时提供)',
     `suggestions` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '推荐问题列表 (回答后自动生成)',
     `request_time` datetime DEFAULT NULL,
     `deleted` tinyint(1) DEFAULT '0' COMMENT '是否被删除，默认false',
     `deleted_by_user` tinyint(1) DEFAULT '0' COMMENT '是否被问答前端用户删除',
     PRIMARY KEY (`request_id`),
     UNIQUE KEY `request_id` (`request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='对话问答详情，保存chat接口的完整返回值';

-- db_spring_demo.t_parent_children definition

CREATE TABLE `t_parent_children` (
                                     `id` int NOT NULL AUTO_INCREMENT,
                                     `p_id` int NOT NULL,
                                     `name` varchar(100) NOT NULL,
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10202 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
