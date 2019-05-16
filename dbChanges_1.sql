-- MySQL Script generated by MySQL Workbench
-- Wed May 15 15:31:12 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema InvoiceManagment
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema InvoiceManagment
-- -----------------------------------------------------
USE InvoiceManagment;
DROP TRIGGER if exists `InvoiceManagment`.`Company_BEFORE_UPDATE`;
drop trigger if exists `InvoiceManagment`.`InternalArticle_BEFORE_UPDATE`;
drop trigger if exists `InvoiceManagment`.`Invoice_BEFORE_UPDATE`;
drop trigger if exists `InvoiceManagment`.`Article_BEFORE_UPDATE`;
drop table if exists schemaversion;


CREATE SCHEMA IF NOT EXISTS `InvoiceManagment` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
USE `InvoiceManagment` ;

-- -----------------------------------------------------
-- Table `InvoiceManagment`.`Company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `InvoiceManagment`.`Company` (
  `company_id` BINARY(16) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `tax_number` VARCHAR(45) NOT NULL,
  `registration_number` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(30) NOT NULL,
  `taxpayer` TINYINT(1) NOT NULL,
  `address` VARCHAR(150) NOT NULL,
  `deleted` TINYINT(1) NOT NULL,
  `created` TIMESTAMP NOT NULL,
  `modified` TIMESTAMP NULL,
  PRIMARY KEY (`company_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InvoiceManagment`.`InternalArticle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `InvoiceManagment`.`InternalArticle` (
  `internal_article_id` BINARY(16) NOT NULL,
  `internal_id` VARCHAR(4) NOT NULL,
  `name` VARCHAR(1000) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `vat` DECIMAL(5,2) NOT NULL,
  `stock` INT NOT NULL,
  `deleted` TINYINT(1) NOT NULL,
  `created` TIMESTAMP NOT NULL,
  `modified` TIMESTAMP NULL,
  PRIMARY KEY (`internal_article_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InvoiceManagment`.`Invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `InvoiceManagment`.`Invoice` (
  `invoice_id` BINARY(16) NOT NULL,
  `total` DECIMAL(10,2) NOT NULL,
  `total_vat` DECIMAL(10,2) NOT NULL,
  `deleted` TINYINT(1) NOT NULL,
  `created` TIMESTAMP NOT NULL,
  `modified` TIMESTAMP NULL,
  `issuer_id` BINARY(16) NOT NULL,
  `customer_id` BINARY(16) NULL,
  PRIMARY KEY (`invoice_id`),
  INDEX `fk_Invoice_Company1_idx` (`issuer_id` ASC) VISIBLE,
  INDEX `fk_Invoice_Company2_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_Invoice_Company1`
    FOREIGN KEY (`issuer_id`)
    REFERENCES `InvoiceManagment`.`Company` (`company_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_Invoice_Company2`
    FOREIGN KEY (`customer_id`)
    REFERENCES `InvoiceManagment`.`Company` (`company_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InvoiceManagment`.`Article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `InvoiceManagment`.`Article` (
  `article_id` BINARY(16) NOT NULL,
  `barcode` VARCHAR(14) NOT NULL,
  `name` VARCHAR(1000) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `vat` DECIMAL(5,2) NOT NULL,
  `stock` INT NOT NULL,
  `deleted` TINYINT(1) NOT NULL,
  `created` TIMESTAMP NOT NULL,
  `modified` TIMESTAMP NULL,
  PRIMARY KEY (`article_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InvoiceManagment`.`SchemaVersion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `InvoiceManagment`.`SchemaVersion` (
  `schema_version_id` BINARY(16) NOT NULL,
  `change_number` INT NOT NULL,
  `version` VARCHAR(30) NOT NULL,
  `applied` DATETIME NOT NULL,
  `applied_by` VARCHAR(45) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `file` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`schema_version_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InvoiceManagment`.`Invoice_has_InternalArticle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `InvoiceManagment`.`Invoice_has_InternalArticle` (
  `invoice_id` BINARY(16) NOT NULL,
  `internal_article_id` BINARY(16) NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`invoice_id`, `internal_article_id`),
  INDEX `fk_Invoice_has_InternalArticle_InternalArticle1_idx` (`internal_article_id` ASC) VISIBLE,
  INDEX `fk_Invoice_has_InternalArticle_Invoice_idx` (`invoice_id` ASC) VISIBLE,
  CONSTRAINT `fk_Invoice_has_InternalArticle_Invoice`
    FOREIGN KEY (`invoice_id`)
    REFERENCES `InvoiceManagment`.`Invoice` (`invoice_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_Invoice_has_InternalArticle_InternalArticle1`
    FOREIGN KEY (`internal_article_id`)
    REFERENCES `InvoiceManagment`.`InternalArticle` (`internal_article_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InvoiceManagment`.`Invoice_has_Article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `InvoiceManagment`.`Invoice_has_Article` (
  `invoice_id` BINARY(16) NOT NULL,
  `article_id` BINARY(16) NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`invoice_id`, `article_id`),
  INDEX `fk_Invoice_has_Article_Article1_idx` (`article_id` ASC) VISIBLE,
  INDEX `fk_Invoice_has_Article_Invoice1_idx` (`invoice_id` ASC) VISIBLE,
  CONSTRAINT `fk_Invoice_has_Article_Invoice1`
    FOREIGN KEY (`invoice_id`)
    REFERENCES `InvoiceManagment`.`Invoice` (`invoice_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_Invoice_has_Article_Article1`
    FOREIGN KEY (`article_id`)
    REFERENCES `InvoiceManagment`.`Article` (`article_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;

ALTER TABLE Article ALTER deleted SET DEFAULT false;
ALTER TABLE Company ALTER deleted SET DEFAULT false;
ALTER TABLE InternalArticle ALTER deleted SET DEFAULT false;
ALTER TABLE Invoice ALTER deleted SET DEFAULT false;


INSERT INTO SchemaVersion (schema_version_id,change_number,version, applied,applied_by,description,file)
   VALUES
   ( UUID_TO_BIN(UUID()),1,'1', NOW(),'igor','schemaversion','aaa');
   
USE `InvoiceManagment`;

DELIMITER $$
USE `InvoiceManagment`$$
CREATE DEFINER = CURRENT_USER TRIGGER `InvoiceManagment`.`Company_BEFORE_UPDATE` BEFORE UPDATE ON `Company` FOR EACH ROW
BEGIN
	INSERT INTO `InvoiceManagment`.`Company`
    SET action = 'update',
		modified =  NOW(); 
END
$$

USE `InvoiceManagment`$$
CREATE DEFINER = CURRENT_USER TRIGGER `InvoiceManagment`.`InternalArticle_BEFORE_UPDATE` BEFORE UPDATE ON `InternalArticle` FOR EACH ROW
BEGIN
	INSERT INTO `InvoiceManagment`.`InternalArticle`
    SET action = 'update',
		modified =  NOW(); 
END
$$

USE `InvoiceManagment`$$
CREATE DEFINER = CURRENT_USER TRIGGER `InvoiceManagment`.`Invoice_BEFORE_UPDATE` BEFORE UPDATE ON `Invoice` FOR EACH ROW
BEGIN
	INSERT INTO `InvoiceManagment`.`Invoice`
    SET action = 'update',
		modified =  NOW(); 
END
$$

USE `InvoiceManagment`$$
CREATE DEFINER = CURRENT_USER TRIGGER `InvoiceManagment`.`Article_BEFORE_UPDATE` BEFORE UPDATE ON `Article` FOR EACH ROW
BEGIN
	INSERT INTO `InvoiceManagment`.`Article`
    SET action = 'update',
		modified =  NOW(); 
END
$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

select * from schemaversion;