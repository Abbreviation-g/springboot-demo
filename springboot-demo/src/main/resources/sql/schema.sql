

CREATE TABLE IF NOT EXISTS `t_map` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `gender` int(1) NOT NULL COMMENT '0:male,1:female',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


