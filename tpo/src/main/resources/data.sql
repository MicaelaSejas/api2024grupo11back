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
INSERT INTO categoria(descripcion) 
VALUES ('Silla Comedor');


use tpo;
INSERT INTO descuento(porcentaje) 
VALUES (0);
INSERT INTO descuento(porcentaje) 
VALUES (10);
INSERT INTO descuento(porcentaje) 
VALUES (15);

use tpo;
INSERT INTO producto(titulo, descripcion, precio, cantidad,imagen_1_URL, imagen_2_URL,idCategoria, idDescuento)
VALUES ('Silla Gamer RGB','Silla RGB perfecta para tu comodidad', 100000, 15,'https://acdn.mitiendanube.com/stores/001/474/949/products/hesx00291-1936e738ce36b71af916350018512377-640-0.png','https://tpelectronica.com.ar/images/product_image/407/0?dpr=2.625&fit=contain&h=400&q=80&version=3e443&w=400',1, 1);
INSERT INTO producto(titulo, descripcion, precio, cantidad,imagen_1_URL, imagen_2_URL,idCategoria, idDescuento)
VALUES ('Silla Gamer Black','Silla Black like a puma', 90000, 20, 'https://static.nb.com.ar/i/nb_SILLA-GAMER-TRUST-GXT708-RESTO-BLACK_ver_248cdcf620a72d7a48bee7459a116ae4.png','https://greendeco.com.ar/wp-content/uploads/2023/09/Virke_GamerZGX_Negra_DSC08053-600x600.webp',1, 2);
INSERT INTO producto(titulo, descripcion, precio, cantidad,imagen_1_URL, imagen_2_URL,idCategoria, idDescuento)
VALUES ('Silla Gamer Pink','Silla Pink claro', 95000, 10,'https://talius.tech/gaming/wp-content/uploads/sites/2/2021/04/silla-gaming-dragonfly-v2-3-500x500-1.png','https://http2.mlstatic.com/D_NQ_NP_845826-MLA71720311621_092023-O.webp',1, 3);
INSERT INTO producto(titulo, descripcion, precio, cantidad,imagen_1_URL, imagen_2_URL,idCategoria, idDescuento)
VALUES ('Silla Jardin Plastica','Silla Rustica elegante', 29999.99, 30, 'https://http2.mlstatic.com/D_NQ_NP_768404-MLU73982723152_012024-O.webp','https://imagedelivery.net/4fYuQyy-r8_rpBpcY7lH_A/sodimacPE/1191640_01/w=800,h=800,fit=pad',2, 1);
INSERT INTO producto(titulo, descripcion, precio, cantidad,imagen_1_URL, imagen_2_URL,idCategoria, idDescuento)
VALUES ('Silla Jardin Madera','Silla Madera para jardin', 24999.99,20, 'https://http2.mlstatic.com/D_NQ_NP_983116-MLA51276305043_082022-O.webp','https://http2.mlstatic.com/D_NQ_NP_793336-MLA75753546715_042024-O.webp',2, 1);
INSERT INTO producto(titulo, descripcion, precio, cantidad,imagen_1_URL, imagen_2_URL,idCategoria, idDescuento)
VALUES ('Silla Jardin Metalica','Silla metalica, dura como la realidad', 35000, 20, 'https://acdn.mitiendanube.com/stores/531/821/products/871-adf43aeff3cae2735916300827717477-640-0.png','https://i.ebayimg.com/thumbs/images/g/re0AAOSw4hlmYhyL/s-l1200.jpg',2, 2);
INSERT INTO producto(titulo, descripcion, precio, cantidad,imagen_1_URL, imagen_2_URL,idCategoria, idDescuento)
VALUES ('Silla Comedor Blue','Silla comedor blues sky', 55499.99, 15,'https://ae01.alicdn.com/kf/H328846ee86e1481aade0b3992e410724O/Juego-de-sillas-de-comedor-con-terciopelo-suave-y-pies-de-madera-para-decoraci-n-del.jpg','https://m.media-amazon.com/images/I/61xl37Awz6L._AC_UF1000,1000_QL80_.jpg',3, 3);
INSERT INTO producto(titulo, descripcion, precio, cantidad,imagen_1_URL, imagen_2_URL,idCategoria, idDescuento)
VALUES ('Silla Comedor Madera','Silla comedor de madera', 45999.99, 29,'https://static1.muebles-lara.es/17099-large_default/silla-232.jpg','https://www.mobles-sedavi.com/15220-large_default/silla-de-comedor-clasica-diseno-194-640.jpg',3, 2);
INSERT INTO producto(titulo, descripcion, precio, cantidad,imagen_1_URL, imagen_2_URL,idCategoria, idDescuento)
VALUES ('Silla Comedor Black','Silla comedor asiento black', 50000, 21,'https://ballsellings.com/105550-large_default/silla-new-paris-ecocuero.jpg','https://http2.mlstatic.com/D_676189-MLA49552131277_042022-C.jpg',3, 1);
