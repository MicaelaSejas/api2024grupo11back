DROP DATABASE IF EXISTS `tpo`;
CREATE DATABASE IF NOT EXISTS `tpo` DEFAULT CHARACTER SET utf8mb4;
USE `tpo`;

DROP TABLE IF EXISTS `categoria`;
DROP TABLE IF EXISTS `descuento`;
DROP TABLE IF EXISTS `producto`;

CREATE TABLE `categoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `descuento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `porcentaje` DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `producto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `precio` DECIMAL(10,2) NOT NULL DEFAULT 1.00,
  `cantidad` int NOT NULL DEFAULT 1,
  `imagen_1_URL` varchar(255),
  `imagen_2_URL` varchar(255),
  `idCategoria` int NOT NULL,
  `idDescuento` int,
  PRIMARY KEY (`id`),
  KEY `fk_categoria` (`idCategoria`),
  KEY `fk_descuento` (`idDescuento`),
  CONSTRAINT `fk_categoria` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`id`),
  CONSTRAINT `fk_descuento` FOREIGN KEY (`idDescuento`) REFERENCES `descuento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


use tpo;
INSERT INTO categoria(descripcion) 
VALUES ('Silla Gamer');
INSERT INTO categoria(descripcion) 
VALUES ('Silla Jardin');


use tpo;
INSERT INTO descuento(porcentaje) 
VALUES (0);
INSERT INTO descuento(porcentaje) 
VALUES (10);

use tpo;
INSERT INTO producto(titulo, descripcion, precio, cantidad,imagen_1_URL, imagen_2_URL,idCategoria, idDescuento)
VALUES ('Silla RGB','Silla RGB Excelente estado', 100000,10, 'https://i.blogs.es/a9b4fe/dx/1366_2000.jpg','https://i.blogs.es/a9b4fe/dx/1366_2000.jpg',1, 1);

