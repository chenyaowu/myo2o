CREATE DATABASE
IF NOT EXISTS `myo2o` DEFAULT CHARACTER
SET utf8 COLLATE utf8_general_ci;

USE `myo2o`;

#创建区域表
CREATE TABLE `tb_area` (
	`area_id` INT (2) NOT NULL,
	`area_name` VARCHAR (200) NOT NULL,
	`priority` INT (2) NOT NULL DEFAULT '0',
	`create_time` datetime DEFAULT NULL,
	`last_edit_time` datetime DEFAULT NULL,
	PRIMARY KEY (`area_id`),
	UNIQUE KEY UK_AREA (`area_name`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;
#创建用户表
CREATE TABLE `tb_person_info` (
	`user_id` INT (10) NOT NULL AUTO_INCREMENT,
	`NAME` VARCHAR (32) DEFAULT NULL,
	`profile_img` VARCHAR (1024) DEFAULT NULL,
	`email` VARCHAR (1024) DEFAULT NULL,
	`gender` VARCHAR (2) DEFAULT NULL,
	`enable_status` INT (2) NOT NULL DEFAULT '0' COMMENT '0:禁止使用本商城，1：允许使用本商城',
	`user_type` INT (2) NOT NULL DEFAULT 1 COMMENT '1:顾客，2：店家，3：超级管理员',
	`create_time` datetime DEFAULT NULL,
	`last_edit_time` datetime DEFAULT NULL,
	PRIMARY KEY (`user_id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = UTF8
