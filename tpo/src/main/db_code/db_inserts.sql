INSERT INTO roles(descripcion) VALUES ('admin');
INSERT INTO roles(descripcion) VALUES ('normal');

INSERT INTO usuarios(nombre, apellido, mail, usuario, password, idRoles) VALUES ('Agustin', 'Grigaliunas', 'agus@gmail.com', 'agriga', '1234', 1);
INSERT INTO usuarios(nombre, apellido, mail, usuario, password, idRoles) VALUES ('Kleyver', 'Bocanegra', 'kb@gmail.com', 'kb', '1234', 2);

INSERT INTO categorias(descripcion) VALUES ('Silla Comedor');
INSERT INTO categorias(descripcion) VALUES ('Silla Gamer');
INSERT INTO categorias(descripcion) VALUES ('Silla Jardin');

INSERT INTO descuentos(Porcentaje) VALUES (5);
INSERT INTO descuentos(Porcentaje) VALUES (10);
INSERT INTO descuentos(Porcentaje) VALUES (15);

INSERT INTO productos(titulo, descripcion, precio, cantidad, idCategoria, idDescuento)
VALUES ('Silla RGB','Silla RGB max max.', 1000, 3, 2, 2);
INSERT INTO productos(titulo, descripcion, precio, cantidad, idCategoria, idDescuento)
VALUES ('Silla Plaza Plaza','Silla amplia para tu jardin unica.', 500, 5, 3,3);

INSERT INTO carritoproductos(idProducto, idCarrito, cantidad) VALUES (1, 1, 1);
INSERT INTO carrito(idUsuario, idCarritoProductos, total) VALUES (2, 1, 1000);

select * from roles;
select * from usuarios;
select * from categorias;
select * from descuentos;
select * from productos;
select * from carrito;
select * from carritoproductos;