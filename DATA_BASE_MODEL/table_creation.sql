-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema pri_database
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pri_database
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pri_database` DEFAULT CHARACTER SET utf8 ;
USE `pri_database` ;

-- -----------------------------------------------------
-- Table `pri_database`.`posts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pri_database`.`posts` ;

CREATE TABLE IF NOT EXISTS `pri_database`.`posts` (
  `POST_ID` BIGINT(20) NOT NULL,
  `DATE` DATE NOT NULL,
  `TITLE` VARCHAR(512) NOT NULL,
  `ADDRESS` TEXT(512) NULL,
  `URL` VARCHAR(512) NOT NULL,
  `BOOK_TITLE` VARCHAR(255) NULL,
  PRIMARY KEY (`POST_ID`),
  UNIQUE INDEX `URL_UNIQUE` (`URL` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pri_database`.`categories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pri_database`.`categories` ;

CREATE TABLE IF NOT EXISTS `pri_database`.`categories` (
  `CATEGORY_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME` VARCHAR(45) NOT NULL,
  `SUBCATEGORY_ID` BIGINT(20) NULL,
  PRIMARY KEY (`CATEGORY_ID`),
  INDEX `SUB8CATEGORY_idx` (`SUBCATEGORY_ID` ASC) VISIBLE,
  UNIQUE INDEX `CATEGORY_NAME_UNIQUE` (`CATEGORY_NAME` ASC) VISIBLE,
  CONSTRAINT `SUB_CATEGORY`
    FOREIGN KEY (`SUBCATEGORY_ID`)
    REFERENCES `pri_database`.`categories` (`CATEGORY_ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pri_database`.`authors`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pri_database`.`authors` ;

CREATE TABLE IF NOT EXISTS `pri_database`.`authors` (
  `AUTHOR_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `AUTHOR_NAME` VARCHAR(512) NOT NULL,
  PRIMARY KEY (`AUTHOR_ID`),
  UNIQUE INDEX `AUTHOR_NAME_UNIQUE` (`AUTHOR_NAME` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pri_database`.`keywords`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pri_database`.`keywords` ;

CREATE TABLE IF NOT EXISTS `pri_database`.`keywords` (
  `KEYWORD_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `KEYWORD_NAME` VARCHAR(512) NOT NULL,
  PRIMARY KEY (`KEYWORD_ID`),
  UNIQUE INDEX `KEYWORD_NAME_UNIQUE` (`KEYWORD_NAME` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pri_database`.`posts_authors`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pri_database`.`posts_authors` ;

CREATE TABLE IF NOT EXISTS `pri_database`.`posts_authors` (
  `AUTHOR_ID` BIGINT(20) NOT NULL,
  `POST_ID` BIGINT(20) NOT NULL,
  PRIMARY KEY (`AUTHOR_ID`, `POST_ID`),
  INDEX `POST_FK_idx` (`POST_ID` ASC) VISIBLE,
  CONSTRAINT `POST_FK`
    FOREIGN KEY (`POST_ID`)
    REFERENCES `pri_database`.`posts` (`POST_ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `AUTHOR`
    FOREIGN KEY (`AUTHOR_ID`)
    REFERENCES `pri_database`.`authors` (`AUTHOR_ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pri_database`.`posts_catergories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pri_database`.`posts_catergories` ;

CREATE TABLE IF NOT EXISTS `pri_database`.`posts_catergories` (
  `POST_ID` BIGINT(20) NOT NULL,
  `CATEGORY_ID` BIGINT(20) NOT NULL,
  PRIMARY KEY (`POST_ID`, `CATEGORY_ID`),
  INDEX `CATEGORY_idx` (`CATEGORY_ID` ASC) VISIBLE,
  CONSTRAINT `POST`
    FOREIGN KEY (`POST_ID`)
    REFERENCES `pri_database`.`posts` (`POST_ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `CATEGORY`
    FOREIGN KEY (`CATEGORY_ID`)
    REFERENCES `pri_database`.`categories` (`CATEGORY_ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pri_database`.`posts_keywords`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pri_database`.`posts_keywords` ;

CREATE TABLE IF NOT EXISTS `pri_database`.`posts_keywords` (
  `POST_ID` BIGINT(20) NOT NULL,
  `KEYWORD_ID` BIGINT(20) NOT NULL,
  PRIMARY KEY (`POST_ID`, `KEYWORD_ID`),
  INDEX `fk_posts_keywords_keywords1_idx` (`KEYWORD_ID` ASC) VISIBLE,
  CONSTRAINT `fk_posts_keywords_posts1`
    FOREIGN KEY (`POST_ID`)
    REFERENCES `pri_database`.`posts` (`POST_ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_posts_keywords_keywords1`
    FOREIGN KEY (`KEYWORD_ID`)
    REFERENCES `pri_database`.`keywords` (`KEYWORD_ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pri_database`.`known_urls`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pri_database`.`known_urls` ;

CREATE TABLE IF NOT EXISTS `pri_database`.`known_urls` (
  `URL_NAME` VARCHAR(512) NOT NULL,
  PRIMARY KEY (`URL_NAME`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
