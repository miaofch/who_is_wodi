/*
MySQL Data Transfer
Source Host: localhost
Source Database: wisw
Target Host: localhost
Target Database: wisw
Date: 2013-07-25 13:37:53
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for comment_history
-- ----------------------------
CREATE TABLE `comment_history` (
  `id` int(11) NOT NULL auto_increment,
  `room_id` int(11) default NULL,
  `user_id` int(11) default NULL,
  `comment` varchar(3000) default NULL,
  `comment_type` int(2) default NULL,
  PRIMARY KEY  (`id`),
  KEY `room_id` (`room_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `comment_history_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `room_history` (`id`),
  CONSTRAINT `comment_history_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for room_history
-- ----------------------------
CREATE TABLE `room_history` (
  `id` int(11) NOT NULL auto_increment,
  `room_name` varchar(100) default NULL,
  `member_count` int(5) default '0',
  `start_time` datetime default NULL,
  `end_time` datetime default NULL,
  `complete_count` int(5) default '0',
  `game_mode` int(2) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(50) default NULL,
  `password` varchar(100) default NULL,
  `score` int(11) default '0',
  `nickname` varchar(50) default NULL,
  `email` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_word_rel
-- ----------------------------
CREATE TABLE `user_word_rel` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) default NULL,
  `word_set_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `user_id` (`user_id`),
  KEY `word_set_id` (`word_set_id`),
  CONSTRAINT `user_word_rel_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_word_rel_ibfk_2` FOREIGN KEY (`word_set_id`) REFERENCES `word_set` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for word_set
-- ----------------------------
CREATE TABLE `word_set` (
  `id` int(11) NOT NULL auto_increment,
  `specia_word` varchar(100) default NULL,
  `normal_word` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `user` VALUES ('test', '098f6bcd4621d373cade4e832627b4f6', '0', 'test', '');
INSERT INTO `user` VALUES ('test2', '05a671c66aefea124cc08b76ea6d30bb', '0', 'test2', '');
INSERT INTO `user` VALUES ('test3', '05a671c66aefea124cc08b76ea6d30bb', '0', 'test3', '');
INSERT INTO `user` VALUES ('test4', '05a671c66aefea124cc08b76ea6d30bb', '0', 'test4', '');
