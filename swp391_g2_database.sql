-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema swp391_g2_project
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema swp391_project
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `swp391_g2_project` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `swp391_g2_project` ;

-- -----------------------------------------------------
-- Table `swp391_g2_project`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `swp391_g2_project`.`person` (
  `person_id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(255) CHARACTER SET 'utf8' NOT NULL,
  `lastname` VARCHAR(255) CHARACTER SET 'utf8' NOT NULL,
  `birthday` DATETIME NOT NULL,
  `gender` BIT(1) NOT NULL DEFAULT b'0',
  `email` VARCHAR(80) NOT NULL,
  `phone_number` VARCHAR(13) NOT NULL,
  `address` VARCHAR(255) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `created_date` DATETIME NOT NULL,
  `updated_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`person_id`),
  UNIQUE INDEX `Email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `PhoneNumber_UNIQUE` (`phone_number` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `swp391_g2_project`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `swp391_g2_project`.`role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NOT NULL,
  `created_date` DATETIME NOT NULL,
  `updated_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `swp391_g2_project`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `swp391_g2_project`.`account` (
  `account_id` INT NOT NULL AUTO_INCREMENT,
  `person_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  `phone_number` VARCHAR(13) NOT NULL,
  `email` VARCHAR(80) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `status` VARCHAR(25) NULL DEFAULT 'inactive',
  `created_date` DATETIME NOT NULL,
  `updated_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE INDEX `PhoneNumber` (`phone_number` ASC) VISIBLE,
  UNIQUE INDEX `Email` (`email` ASC) VISIBLE,
  FOREIGN KEY (`person_id`) REFERENCES `swp391_g2_project`.`person` (`person_id`),
  FOREIGN KEY (`role_id`) REFERENCES `swp391_g2_project`.`role` (`role_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `swp391_g2_project`.`shop_manager`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `swp391_g2_project`.`shop` (
  `shop_id` INT NOT NULL AUTO_INCREMENT,
  `person_id` INT NOT NULL,
  `shop_name` INT NOT NULL,
  `status` VARCHAR(25) NULL DEFAULT 'inactive',
  `description` TEXT CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `created_date` DATETIME NOT NULL,
  `updated_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`shop_id`),
  FOREIGN KEY (`person_id`) REFERENCES `swp391_g2_project`.`person` (`person_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `swp391_g2_project`.`shop_manager`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `swp391_g2_project`.`food` (
  `food_id` INT NOT NULL AUTO_INCREMENT,
  `shop_id` INT NOT NULL,
  `food_name` INT NOT NULL,
  `status` VARCHAR(25) NULL DEFAULT 'inactive',
  `description` TEXT CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `created_date` DATETIME NOT NULL,
  `updated_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`food_id`),
  FOREIGN KEY (`shop_id`) REFERENCES `swp391_g2_project`.`shop` (`shop_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;