SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

DROP DATABASE IF EXISTS `freeway`;

-- -----------------------------------------------------
-- Schema freeway
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `freeway` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `freeway` ;

-- -----------------------------------------------------
-- Table `freeway`.`Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freeway`.`Usuarios` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `contrasena` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `freeway`.`Viajes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freeway`.`Viajes` (
  `idViaje` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(500) NULL,
  `fechaInicio` DATE NOT NULL,
  `fechaFin` DATE NOT NULL,
  `Usuarios_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idViaje`),
  INDEX `fk_Viajes_Usuario_idx` (`Usuarios_idUsuario` ASC),
  CONSTRAINT `fk_Viajes_Usuarios`
    FOREIGN KEY (`Usuarios_idUsuario`)
    REFERENCES `freeway`.`Usuarios` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `freeway`.`itemsViaje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freeway`.`ItemsViaje` (
  `idItemsViaje` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(100) NULL,
  `hora` TIME NULL,
  `fecha` DATE NULL,
  `precio` DOUBLE NULL,
  `ubicacionLatitud` DOUBLE NOT NULL,
  `ubicacionLongitud` DOUBLE NOT NULL,
  `Viajes_idViaje` INT NOT NULL,
  PRIMARY KEY (`idItemsViaje`),
  INDEX `fk_ItemsViaje_Viajes_idx` (`Viajes_idViaje` ASC) ,
  CONSTRAINT `fk_ItemsViaje_Viajes`
    FOREIGN KEY (`Viajes_idViaje`)
    REFERENCES `freeway`.`Viajes` (`idViaje`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;