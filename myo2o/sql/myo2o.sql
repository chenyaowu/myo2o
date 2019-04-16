CREATE DATABASE
IF NOT EXISTS `myo2o` DEFAULT CHARACTER
SET utf8 COLLATE utf8_general_ci;

USE `myo2o`;

#创建区域表
CREATE TABLE `tb_area` (
	`area_id` INT (2) NOT NULL,
	`area_name` VARCHAR (200) NOT NULL,
	`priority` INT (2) NOT NULL DEFAULT `0`,
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
	`birthday` datetime DEFAULT NULL,
	`email` VARCHAR (1024) DEFAULT NULL,
	`gender` VARCHAR (2) DEFAULT NULL,
	`enable_status` INT (2) NOT NULL DEFAULT `0` COMMENT `0:禁止使用本商城，1：允许使用本商城`,
	`user_type` INT (2) NOT NULL DEFAULT 1 COMMENT `1:顾客，2：店家，3：超级管理员`,
	`create_time` datetime DEFAULT NULL,
	`last_edit_time` datetime DEFAULT NULL,
	PRIMARY KEY (`user_id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = UTF8;

#创建微信账号表
CREATE TABLE `tb_wechat_auth`(
	`wechat_auth_id` int(10) NOT NULL AUTO_INCREMENT,
	`user_id` int(10) NOT NULL,
	`open_id` varchar(1024) NOT NULL,
	`create_time` datetime DEFAULT NULL,
	PRIMARY KEY(`wechat_auth_id`),
	UNIQUE INDEX `tb_wechat_auth`(`open_id`),
	CONSTRAINT fk_wechat_profile FOREIGN KEY(`user_id`) REFERENCES tb_person_info(`user_id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET = utf8;

#创建本地账号表
CREATE TABLE `tb_local_auth`(
	`local_auth_id` int(10) NOT NULL AUTO_INCREMENT,
	`user_id` int(10) NOT NULL,
	`username` varchar(128) NOT NULL,
	`password` varchar(128) NOT NULL,
	`create_time` datetime DEFAULT NULL,
	`last_edit_time` datetime DEFAULT NULL,
	PRIMARY KEY (`local_auth_id`),
	UNIQUE KEY uk_local_profile (`username`),
	CONSTRAINT fk_wechatauth_profile FOREIGN key(`user_id`) REFERENCES tb_person_info(`user_id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET = utf8;

#创建头条表
CREATE TABLE tb_head_line(
  line_id int(100) NOT NULL AUTO_INCREMENT,
  line_name varchar(1000) DEFAULT NULL,
  line_link varchar(2000) NOT NULL,
  line_img varchar(2000) NOT NULL,
  priority int(2) DEFAULT NULL,
  enable_status int(2) NOT NULL DEFAULT 0,
  create_time datetime DEFAULT NULL,
  last_edit_time datetime DEFAULT NULL,
  PRIMARY KEY (line_id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET = utf8;

#创建店铺类别
create TABLE tb_shop_category(
	shop_category_id  int(11) NOT NULL AUTO_INCREMENT,
	shop_category_name varchar(100) NOT NULL DEFAULT ``,
	shop_category_desc varchar(1000) NOT NULL DEFAULT ``,
	shop_category_img varchar(2000) DEFAULT NULL,
	priority int(2) NOT NULL DEFAULT 0,
	create_time datetime DEFAULT NULL,
	last_edit_time datetime DEFAULT NULL,
	parent_id int(11) DEFAULT NULL,
	PRIMARY KEY (shop_category_id),
	CONSTRAINT fk_shop_category_self FOREIGN KEY (parent_id) REFERENCES tb_shop_category(shop_category_id)
)ENGINE=INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

#创建店铺表
CREATE TABLE tb_shop(
	shop_id int(10) NOT NULL AUTO_INCREMENT,
	owner_id int(10) NOT NULL COMMENT `店铺创建人`,
	area_id int(5) DEFAULT NULL,
	shop_category_id int(11) DEFAULT NULL,
	shop_name varchar(256) NOT NULL,
	shop_desc varchar(1024) DEFAULT NULL,
	shop_addr varchar(200) DEFAULT NULL,
	phone varchar(128) DEFAULT NULL,
	shop_img varchar(1024) DEFAULT NULL,
	priority int(3) DEFAULT 0,
	create_time datetime DEFAULT NULL,
	last_edit_time datetime DEFAULT NULL,
	enable_status int(2) NOT NULL DEFAULT 0,
	advice varchar(255) DEFAULT NULL,
	PRIMARY KEY (shop_id),
	CONSTRAINT fk_shop_area FOREIGN KEY (area_id) REFERENCES tb_area (area_id),
	CONSTRAINT fk_shop_profile FOREIGN KEY (owner_id) REFERENCES tb_person_info (user_id),
	CONSTRAINT fk_shop_shopcate FOREIGN KEY (shop_category_id) REFERENCES tb_shop_category(shop_category_id)
)ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

#创建商品类别表
create TABLE tb_product_category(
	product_category_id int(11) NOT NULL AUTO_INCREMENT,
	product_category_name varchar(100) NOT NULL,
	priority int(2) DEFAULT 0,
	create_time datetime DEFAULT NULL,
	shop_id int(20) NOT NULL DEFAULT 0,
	PRIMARY KEY (product_category_id),
	CONSTRAINT fk_procate_shop FOREIGN KEY (shop_id) REFERENCES tb_shop (shop_id)
)ENGINE = INNODB AUTO_INCREMENT =1 DEFAULT CHARSET = utf8;

#创建商品表
CREATE TABLE tb_product(
	product_id int(100) NOT NULL AUTO_INCREMENT,
	product_name varchar(100) NOT NULL,
	product_desc varchar(2000) DEFAULT NULL,
	img_addr varchar(2000) DEFAULT ``,
	normal_price varchar(100) DEFAULT NULL,
	promotion_price varchar(100) DEFAULT NULL,
	priority int(2) NOT NULL DEFAULT 0,
	point int(10) NOT NULL DEFAULT 0,
	create_time datetime DEFAULT NULL,
	last_edit_time datetime DEFAULT NULL,
	enable_status int(2) NOT NULL DEFAULT 0,
	product_category_id int(11) DEFAULT NULL,
	shop_id int(20) NOT NULL DEFAULT 0,
	PRIMARY KEY (product_id),
	CONSTRAINT fk_product_procate FOREIGN KEY (product_category_id) REFERENCES tb_product_category (product_category_id),
	CONSTRAINT fk_product_shop FOREIGN KEY (shop_id) REFERENCES tb_shop (shop_id)
)ENGINE=INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

#创建商品详情图片表
create TABLE tb_product_img(
	product_img_id int(20) NOT NULL AUTO_INCREMENT,
	img_addr varchar(2000) NOT NULL,
	img_desc varchar(2000) DEFAULT NULL,
	priority int(2) DEFAULT 0 ,
	create_time datetime DEFAULT NULL,
	product_id int(20) DEFAULT NULL,
	PRIMARY KEY (product_img_id),
	CONSTRAINT fk_proimg_product FOREIGN KEY (product_id) REFERENCES tb_product(product_id)
)ENGINE=InnoDB AUTO_INCREMENT =1 DEFAULT CHARSET = utf8;

#创建奖品表
CREATE TABLE tb_award(
	award_id int(10) NOT NULL AUTO_INCREMENT,
	award_name varchar(256) NOT NULL,
	award_desc varchar(1024) DEFAULT NULL,
	award_img varchar(1024) DEFAULT '',
	point int(10) NOT NULL DEFAULT 0,
	priority int(2) NOT NULL DEFAULT 0,
	create_time datetime DEFAULT NULL,
	last_edit_time datetime DEFAULT NULL,
	enable_status int(2) NOT NULL DEFAULT 0,
	shop_id int(20) NOT NULL DEFAULT 0,
	PRIMARY KEY (award_id),
	KEY `fk_award_shop_id` (`shop_id`),
	CONSTRAINT fk_award_shop FOREIGN KEY (shop_id) REFERENCES tb_shop (shop_id)
)ENGINE=INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

#创建顾客奖品关系表
CREATE TABLE tb_user_award_map(
	user_award_id int(10) NOT NULL AUTO_INCREMENT,
	user_id int(10) NOT NULL,
	award_id int(10) NOT NULL,
	shop_id int(10) NOT NULL ,
	operator_id int(10) NOT NULL ,
	create_time datetime DEFAULT NULL,
	userd_status int(2) NOT NULL DEFAULT 0,
	point int(10) NOT NULL DEFAULT 0,
	PRIMARY KEY (user_award_id),
	KEY `fk_user_award_map_profile` (`user_id`),
	KEY `fk_user_award_map_award` (`award_id`),
	KEY `fk_user_award_map_shop` (`shop_id`),
	CONSTRAINT fk_user_award_map_award FOREIGN KEY (award_id) REFERENCES tb_award (award_id),
	CONSTRAINT fk_user_award_map_profile FOREIGN KEY (user_id) REFERENCES tb_person_info (user_id),
  CONSTRAINT fk_user_award_map_shop FOREIGN KEY (shop_id) REFERENCES tb_shop (shop_id)
)ENGINE=INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

#顾客消费商品关系表
CREATE TABLE tb_user_product_map(
	user_product_id int(10) NOT NULL AUTO_INCREMENT,
	user_id int(10) DEFAULT NULL,
	product_id int(100) DEFAULT NULL,
	shop_id int(10) DEFAULT NULL ,
	operator_id int(10) DEFAULT NULL ,
	create_time datetime DEFAULT NULL,
	point int(10) NOT NULL DEFAULT 0,
	PRIMARY KEY (user_product_id),
	KEY `fk_user_product_map_profile` (`user_id`),
	KEY `fk_user_product_map_product` (`product_id`),
	KEY `fk_user_product_map_shop` (`shop_id`),
	CONSTRAINT fk_user_product_map_product FOREIGN KEY (product_id) REFERENCES tb_product (product_id),
	CONSTRAINT fk_user_product_map_profile FOREIGN KEY (user_id) REFERENCES tb_person_info (user_id),
  CONSTRAINT fk_user_product_map_shop FOREIGN KEY (shop_id) REFERENCES tb_shop (shop_id)
)ENGINE=INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

#创建顾客店铺积分关系表
CREATE TABLE tb_user_shop_map(
	user_shop_id int(10) NOT NULL AUTO_INCREMENT,
	user_id int(10) NOT NULL,
	shop_id int(10) NOT NULL ,
	create_time datetime DEFAULT NULL,
	point int(10) NOT NULL DEFAULT 0,
	PRIMARY KEY (user_shop_id),
	UNIQUE KEY `uq_user_shop` (`user_id`,`shop_id`),
	KEY `fk_user_shop_map_shop` (`shop_id`),
  CONSTRAINT fk_user_shop_map_shop FOREIGN KEY (shop_id) REFERENCES tb_shop (shop_id),
  CONSTRAINT fk_user_shop_map_user FOREIGN KEY (user_id) REFERENCES tb_person_info (user_id)
)ENGINE=INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

#创建顾客单日消费商品表
CREATE TABLE tb_product_sell_daily(
	`product_sell_daily_id` int(100) NOT NULL AUTO_INCREMENT,
	`product_id` int(100) NOT NULL,
	`shop_id` int(10) NOT NULL ,
	`create_time` datetime DEFAULT NULL,
	`total` int(10) DEFAULT 0,
	PRIMARY KEY (`product_sell_daily_id`),
	KEY `fk_product_sell_daily_product` (`product_id`),
	KEY `fk_product_sell_daily_shop` (`shop_id`),
  CONSTRAINT fk_product_sell_daily_product FOREIGN KEY (product_id) REFERENCES tb_product (product_id),
  CONSTRAINT fk_product_sell_daily_shop FOREIGN KEY (shop_id) REFERENCES tb_shop (shop_id)
)ENGINE=INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

#创建店员权限表
CREATE TABLE `tb_shop_auth_map`(
	`shop_auth_id` int(10) NOT NULL AUTO_INCREMENT,
	`employee_id` int(10) NOT NULL ,
	`shop_id` int(10) NOT NULL ,
	`title` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL ,
	`title_flag` int(2) DEFAULT NULL ,
	`create_time` datetime DEFAULT NULL,
	`last_edit_time` datetime DEFAULT NULL,
	`enable_status` int(2) NOT NULL DEFAULT 0,
	PRIMARY KEY (shop_auth_id),
	KEY `fk_shop_auth_map_shop` (`shop_id`),
	UNIQUE KEY `uk_shop_auth_map` (`employee_id`,`shop_id`),
  CONSTRAINT `fk_shop_auth_map_employee` FOREIGN KEY (`employee_id`) REFERENCES `tb_person_info` (`user_id`),
  CONSTRAINT `fk_shop_auth_map_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
)ENGINE=INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;
