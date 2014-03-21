SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `cuitcc` DEFAULT CHARACTER SET utf8 ;
USE `cuitcc` ;

-- -----------------------------------------------------
-- Table `cuitcc`.`administrator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cuitcc`.`administrator` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '¹ÜÀíÔ±',
  `name` VARCHAR(32) NULL DEFAULT NULL COMMENT 'Ãû×Ö',
  `passwrod` CHAR(40) NULL DEFAULT NULL COMMENT 'ÃÜÂë(sha1)',
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '´´½¨Ê±¼ä',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cuitcc`.`config`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cuitcc`.`config` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NULL DEFAULT NULL,
  `vaule` TINYBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cuitcc`.`global_msg`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cuitcc`.`global_msg` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ÏµÍ³ÏûÏ¢',
  `administrator_id` BIGINT(20) UNSIGNED NOT NULL,
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '´´½¨Ê±¼ä',
  `content` TINYBLOB NULL DEFAULT NULL COMMENT 'ÄÚÈÝ',
  `status` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '×´Ì¬',
  PRIMARY KEY (`id`),
  INDEX `fk_global_msg_administrator1_idx` (`administrator_id` ASC),
  CONSTRAINT `fk_global_msg_administrator1`
    FOREIGN KEY (`administrator_id`)
    REFERENCES `cuitcc`.`administrator` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cuitcc`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cuitcc`.`user` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ÓÃ»§ID',
  `name` VARCHAR(32) NOT NULL COMMENT 'ÓÃ»§Ãû',
  `email` VARCHAR(64) NOT NULL COMMENT 'ÓÃ»§ÓÊÏä',
  `register_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '×¢²áÊ±¼ä',
  `password` CHAR(40) NOT NULL COMMENT 'ÃÜÂë£¨sha1£©',
  `introduction` VARCHAR(128) NULL DEFAULT NULL COMMENT '¸öÈË¼ò½é',
  `avator` VARCHAR(64) NULL DEFAULT NULL COMMENT 'Í·Ïñ(url)',
  `phone` VARCHAR(32) NULL DEFAULT NULL COMMENT 'µç»°ºÅÂë',
  `profile` VARCHAR(64) NULL DEFAULT NULL COMMENT '¸öÈËÖ÷Ò³',
  `favorite` VARCHAR(128) NULL DEFAULT NULL COMMENT '¸öÈËÏ²ºÃ',
  `location` VARCHAR(128) NULL DEFAULT NULL COMMENT '·½Î»£¨º½¿Õ¸Û|ÁúÈª£©',
  `station` VARCHAR(16) NULL DEFAULT NULL COMMENT 'Éí·Ý£¨½ÌÖ°¹¤|Ñ§Éú£©',
  `level` TINYINT(3) UNSIGNED NULL DEFAULT NULL COMMENT 'ÓÃ»§µÈ¼¶',
  `masks` SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0' COMMENT 'ÓÃ»§»ý·Ö',
  `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '´æ»î×´Ì¬',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC),
  INDEX `users_id_IDX` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cuitcc`.`login_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cuitcc`.`login_log` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  `login_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ÓÃ»§µÇÂ¼ÈÕÖ¾',
  `login_ip` VARCHAR(32) NULL DEFAULT NULL,
  `status` TINYINT(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `fk_login_log_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_login_log_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `cuitcc`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cuitcc`.`node`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cuitcc`.`node` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '½ÚµãID',
  `name` VARCHAR(32) NOT NULL COMMENT '½ÚµãÃû³Æ',
  `alias` VARCHAR(16) NOT NULL COMMENT '½Úµã±ð³Æ(×÷ÓÃÓÚurl)',
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '´´½¨Ê±¼ä',
  `introduction` VARCHAR(128) NULL DEFAULT NULL COMMENT '½Úµã¼ò½é',
  `type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '½ÚµãÀàÐÍ',
  `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '½Úµã×´Ì¬',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cuitcc`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cuitcc`.`post` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Ö÷ÌâID',
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  `node_id` BIGINT(20) UNSIGNED NOT NULL,
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '´´½¨Ê±¼ä',
  `status` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '×´Ì¬',
  `content` LONGBLOB NULL DEFAULT NULL COMMENT '·¢ÌûÄÚÈÝ',
  `type` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT 'Ö÷ÌâÀàÐÍ',
  `view_times` MEDIUMINT(8) UNSIGNED NULL DEFAULT NULL COMMENT 'µã»÷ÊýÁ¿',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_post_user1_idx` (`user_id` ASC),
  INDEX `fk_post_node1_idx` (`node_id` ASC),
  CONSTRAINT `fk_post_node1`
    FOREIGN KEY (`node_id`)
    REFERENCES `cuitcc`.`node` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `cuitcc`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cuitcc`.`mention`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cuitcc`.`mention` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ÌáÐÑ',
  `post_id` BIGINT(20) UNSIGNED NOT NULL,
  `user_id_host` BIGINT(20) UNSIGNED NOT NULL,
  `user_id_guest` BIGINT(20) UNSIGNED NOT NULL,
  `type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT 'ÀàÐÍ£¨@Ìáµ½|ÄÚÈÝ±»ÆÀÂÛ£©',
  `content` TINYBLOB NULL DEFAULT NULL COMMENT 'ÄÚÈÝ',
  `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '×´Ì¬',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_mention_user1_idx` (`user_id_host` ASC),
  INDEX `fk_mention_user2_idx` (`user_id_guest` ASC),
  INDEX `fk_mention_post1_idx` (`post_id` ASC),
  CONSTRAINT `fk_mention_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `cuitcc`.`post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mention_user1`
    FOREIGN KEY (`user_id_host`)
    REFERENCES `cuitcc`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mention_user2`
    FOREIGN KEY (`user_id_guest`)
    REFERENCES `cuitcc`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cuitcc`.`msg`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cuitcc`.`msg` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ÓÃ»§Í¨Öª',
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  `content` TINYBLOB NULL DEFAULT NULL COMMENT 'Í¨ÖªÄÚÈÝ',
  `status` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '×´Ì¬',
  PRIMARY KEY (`id`),
  INDEX `fk_msg_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_msg_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `cuitcc`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cuitcc`.`nodes_for_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cuitcc`.`nodes_for_users` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ÓÃ»§ÊÕ²Ø½Úµã',
  `node_id` BIGINT(20) UNSIGNED NOT NULL,
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_nodes_for_users_node1_idx` (`node_id` ASC),
  INDEX `fk_nodes_for_users_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_nodes_for_users_node1`
    FOREIGN KEY (`node_id`)
    REFERENCES `cuitcc`.`node` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nodes_for_users_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `cuitcc`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cuitcc`.`tab`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cuitcc`.`tab` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '·ÖÀà±íID',
  `name` VARCHAR(32) NOT NULL COMMENT '·ÖÀàÃû³Æ',
  `alias` VARCHAR(32) NOT NULL COMMENT '±ð³Æ£¨×÷ÓÃÓÚurl£©',
  `introdution` VARCHAR(128) NULL DEFAULT NULL COMMENT '·ÖÀà¼ò½é',
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '´´½¨Ê±¼ä',
  `postion` TINYINT(4) NULL DEFAULT NULL COMMENT 'Î»ÖÃ',
  `in_main_menu` TINYINT(4) NOT NULL DEFAULT '1' COMMENT 'Õ¹Ê¾ÔÚÖ÷²Ëµ¥ÖÐ',
  `status` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '×´Ì¬',
  `type` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT 'ÀàÐÍ',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cuitcc`.`nodes_in_tabs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cuitcc`.`nodes_in_tabs` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '·ÖÀà-½Úµã¶ÔÓ¦¹ØÏµ±í£¨¶à¶Ô¶à£©',
  `tab_id` BIGINT(20) UNSIGNED NOT NULL,
  `node_id` BIGINT(20) UNSIGNED NOT NULL,
  `postion` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '½ÚµãÔÚ·ÖÀàÖÐÕ¹Ê¾µÄÎ»ÖÃ',
  PRIMARY KEY (`id`),
  INDEX `fk_nodes_in_tabs_node1_idx` (`node_id` ASC),
  INDEX `fk_nodes_in_tabs_tab1_idx` (`tab_id` ASC),
  CONSTRAINT `fk_nodes_in_tabs_node1`
    FOREIGN KEY (`node_id`)
    REFERENCES `cuitcc`.`node` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nodes_in_tabs_tab1`
    FOREIGN KEY (`tab_id`)
    REFERENCES `cuitcc`.`tab` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cuitcc`.`post_tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cuitcc`.`post_tags` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `post_id` BIGINT(20) UNSIGNED NOT NULL,
  `tag` VARCHAR(32) NULL DEFAULT NULL COMMENT '±êÇ©',
  PRIMARY KEY (`id`),
  INDEX `fk_post_tags_post1_idx` (`post_id` ASC),
  CONSTRAINT `fk_post_tags_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `cuitcc`.`post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cuitcc`.`posts_for_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cuitcc`.`posts_for_users` (
  `id` BIGINT(20) NOT NULL COMMENT 'ÓÃ»§ÊÕ²ØÖ÷Ìâ',
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  `post_id` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_posts_for_users_post1_idx` (`post_id` ASC),
  INDEX `fk_posts_for_users_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_posts_for_users_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `cuitcc`.`post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_posts_for_users_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `cuitcc`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cuitcc`.`record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cuitcc`.`record` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'É¾Ìù¼ÇÂ¼',
  `post_id` BIGINT(20) UNSIGNED NULL DEFAULT NULL COMMENT 'ËùÔÚÖ÷Ìâ',
  `review_id` BIGINT(20) NULL DEFAULT NULL COMMENT 'É¾³ý»Ø¸´',
  `type` TINYINT(3) UNSIGNED NULL DEFAULT NULL COMMENT 'ËùÉ¾ÀàÐÍ',
  `administrator_id` BIGINT(20) UNSIGNED NOT NULL,
  `create_date` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_record_administrator_idx` (`administrator_id` ASC),
  CONSTRAINT `fk_record_administrator`
    FOREIGN KEY (`administrator_id`)
    REFERENCES `cuitcc`.`administrator` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cuitcc`.`report`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cuitcc`.`report` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Í¶Ëß',
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  `type` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT 'ÀàÐÍ',
  `content` TINYBLOB NULL DEFAULT NULL COMMENT 'Í¶ËßÄÚÈÝ',
  `status` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '×´Ì¬',
  PRIMARY KEY (`id`),
  INDEX `fk_report_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_report_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `cuitcc`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cuitcc`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cuitcc`.`review` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '»Ø¸´',
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  `post_id` BIGINT(20) UNSIGNED NOT NULL,
  `content` MEDIUMBLOB NULL DEFAULT NULL COMMENT '»Ø¸´ÄÚÈÝ',
  `floor` SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0' COMMENT '»Ø¸´Â¥²ã',
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '´´½¨Ê±¼ä',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_review_user1_idx` (`user_id` ASC),
  INDEX `fk_review_post1_idx` (`post_id` ASC),
  CONSTRAINT `fk_review_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `cuitcc`.`post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `cuitcc`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cuitcc`.`stars`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cuitcc`.`stars` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '¹Ø×¢',
  `user_id_host` BIGINT(20) UNSIGNED NOT NULL,
  `user_id_guest` BIGINT(20) UNSIGNED NOT NULL,
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '¹Ø×¢Ê±¼ä',
  PRIMARY KEY (`id`),
  INDEX `fk_stars_user1_idx` (`user_id_host` ASC),
  INDEX `fk_stars_user2_idx` (`user_id_guest` ASC),
  CONSTRAINT `fk_stars_user1`
    FOREIGN KEY (`user_id_host`)
    REFERENCES `cuitcc`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_stars_user2`
    FOREIGN KEY (`user_id_guest`)
    REFERENCES `cuitcc`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cuitcc`.`vote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cuitcc`.`vote` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ÓÃ»§Í¶Æ±',
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  `review_id` BIGINT(20) UNSIGNED NOT NULL,
  `type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT 'Ö§³Ö|·´¶Ô',
  `create_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_vote_review1_idx` (`review_id` ASC),
  INDEX `fk_vote_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_vote_review1`
    FOREIGN KEY (`review_id`)
    REFERENCES `cuitcc`.`review` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vote_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `cuitcc`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cuitcc`.`grade_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cuitcc`.`grade_log` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '积分',
  `content` TINYTEXT NULL,
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_grade_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_grade_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `cuitcc`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
