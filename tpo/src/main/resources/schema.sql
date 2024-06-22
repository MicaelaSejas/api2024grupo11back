CREATE DATABASE IF NOT EXISTS `tpo` DEFAULT CHARACTER SET utf8mb4;

USE `tpo`;

CREATE TABLE `rol` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `categoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `descuento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `porcentaje` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL UNIQUE,
  `usuario` varchar(45) NOT NULL UNIQUE,
  `password` varchar(45) NOT NULL,
  `idRol` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_rol` FOREIGN KEY (`idRol`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `producto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `imagen_1_url` varchar(255),
  `imagen_2_url` varchar(255),
  `precio` float NOT NULL DEFAULT 0,
  `cantidad` int NOT NULL DEFAULT 0,
  `idCategoria` int NOT NULL,
  `idDescuento` int,
  PRIMARY KEY (`id`),
  KEY `fk_categoria` (`idCategoria`),
  KEY `fk_descuento` (`idDescuento`),
  CONSTRAINT `fk_categoria` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`id`),
  CONSTRAINT `fk_descuento` FOREIGN KEY (`idDescuento`) REFERENCES `descuento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



CREATE TABLE `carrito` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idUsuario` int NOT NULL,
  `total` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usuario` (`idUsuario`),
  CONSTRAINT `fk_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `carritoProducto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idProducto` int NOT NULL,
  `idCarrito` int NOT NULL,
  `cantidad` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY fk_producto (`idProducto`),
  KEY fk_carrito (`idCarrito`),
  CONSTRAINT `fk_producto` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`id`),
  CONSTRAINT `fk_carrito` FOREIGN KEY (`idCarrito`) REFERENCES `carrito` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `compra` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idUsuario` int NOT NULL,
  `precioTotal` float NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_compraUsuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `compraProducto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idCompra` int NOT NULL,
  `idProducto` int NOT NULL,
  `cantidad` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY fk_compra (`idCompra`),
  KEY fk_producto (`idProducto`),
  CONSTRAINT `fk_compra` FOREIGN KEY (`idCompra`) REFERENCES `compra` (`id`),
  CONSTRAINT `fk_compraProducto` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO rol(descripcion) VALUES ('admin');
INSERT INTO rol(descripcion) VALUES ('user');

INSERT INTO usuario(nombre, apellido, mail, usuario, password, idRol) VALUES ('Kleyver', 'Bocanegra', 'kbocanegracisneros@uade.edu.ar', 'kbocanegracisneros', '1234567', 1);
INSERT INTO usuario(nombre, apellido, mail, usuario, password, idRol) VALUES ('Agustin', 'Grigaliunas', 'agus@gmail.com', 'agriga', '12345678', 2);
