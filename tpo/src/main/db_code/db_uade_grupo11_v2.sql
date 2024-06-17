CREATE DATABASE IF NOT EXISTS `tpo` DEFAULT CHARACTER SET utf8mb3;

USE `tpo`;

CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `categorias` (
  `idCategorias` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`idCategorias`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `descuentos` (
  `idDescuentos` int NOT NULL AUTO_INCREMENT,
  `Porcentaje` int NOT NULL,
  PRIMARY KEY (`idDescuentos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL UNIQUE,
  `usuario` varchar(45) NOT NULL UNIQUE,
  `password` varchar(45) NOT NULL,
  `idRoles` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_roles` FOREIGN KEY (`idRoles`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `productos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) NOT NULL,
  `imagen_1` blob,
  `imagen_2` blob,
  `precio` float NOT NULL DEFAULT 0,
  `cantidad` int NOT NULL DEFAULT 0,
  `idCategoria` int NOT NULL,
  `idDescuento` int,
  PRIMARY KEY (`id`),
  KEY `fk_idCategoria` (`idCategoria`),
  KEY `fk_idDescuento` (`idDescuento`),
  CONSTRAINT `fk_idCategoria` FOREIGN KEY (`idCategoria`) REFERENCES `categorias` (`idCategorias`),
  CONSTRAINT `fk_idDescuento` FOREIGN KEY (`idDescuento`) REFERENCES `descuentos` (`idDescuentos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `CarritoProductos` (
  `idCarritoProducto` int NOT NULL AUTO_INCREMENT,
  `idProducto` int NOT NULL,
  `idCarrito` int NOT NULL,
  `cantidad` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`idCarritoProducto`),
  KEY fk_idProductos (idProducto),
  KEY fk_idCarrito (idCarrito)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `carrito` (
  `idCARRITO` int NOT NULL AUTO_INCREMENT,
  `idUsuario` int NOT NULL,
  `total` float NOT NULL,
  PRIMARY KEY (`idCARRITO`),
  KEY `fk_idUsuario` (`idUsuario`),
  CONSTRAINT `fk_idUsuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Nueva enteidad*/
CREATE TABLE `CompraProductos` (
  `idCompraProductos` int NOT NULL AUTO_INCREMENT,
  `idCompra` int NOT NULL,
  `idProducto` int NOT NULL,
  `cantidad` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`idCompraProductos`),
  KEY fk_idCompra (idCompra),
  KEY fk_idProducto (idProducto),
  CONSTRAINT `fk_idCompra` FOREIGN KEY (`idCompra`) REFERENCES `compra` (`idCompra`),
  CONSTRAINT `fk_idProducto` FOREIGN KEY (`idProducto`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Nueva enteidad*/
CREATE TABLE `compra` (
  `idCompra` int NOT NULL AUTO_INCREMENT,
  `idCompraProductos` int NOT NULL,
  `idUsuario` int NOT NULL,
  `precioTotal` float NOT NULL,
  PRIMARY KEY (`idCompra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

