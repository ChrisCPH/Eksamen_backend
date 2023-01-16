-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema eksamen24test
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `eksamen24test` ;

-- -----------------------------------------------------
-- Schema eksamen24test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `eksamen24test` DEFAULT CHARACTER SET utf8 ;
USE `eksamen24test` ;

-- -----------------------------------------------------
-- Table `eksamen24test`.`developer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eksamen24test`.`developer` ;

CREATE TABLE IF NOT EXISTS `eksamen24test`.`developer` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `billing_pr_hour` INT NOT NULL,
  `user_pass` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eksamen24test`.`project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eksamen24test`.`project` ;

CREATE TABLE IF NOT EXISTS `eksamen24test`.`project` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eksamen24test`.`project_hours`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eksamen24test`.`project_hours` ;

CREATE TABLE IF NOT EXISTS `eksamen24test`.`project_hours` (
  `id` INT NOT NULL,
  `hours_spent` INT NOT NULL,
  `user_story` INT NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `developer_id` INT NOT NULL,
  `project_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_project_hours_project1_idx` (`project_id` ASC) VISIBLE,
  INDEX `fk_project_hours_developer1_idx` (`developer_id` ASC) VISIBLE,
  CONSTRAINT `fk_project_hours_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `eksamen24test`.`project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_project_hours_developer1`
    FOREIGN KEY (`developer_id`)
    REFERENCES `eksamen24test`.`developer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
