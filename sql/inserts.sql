INSERT INTO roles(descripcion) VALUES ('admin');
INSERT INTO roles(descripcion) VALUES ('normal');

INSERT INTO usuarios(nombre, apellido, mail, usuario, password, idRoles) VALUES ('Agustin', 'Grigaliunas', 'agus@gmail.com', 'agriga', '1234', 1);
INSERT INTO usuarios(nombre, apellido, mail, usuario, password, idRoles) VALUES ('Kleyver', 'Bocanegra', 'kv@gmail.com', 'kb', '1234', 2);

INSERT INTO categorias(descripcion) VALUES ('Silla Gamer');
INSERT INTO categorias(descripcion) VALUES ('Silla De Comedor');
INSERT INTO categorias(descripcion) VALUES ('Silla De Jardin');

INSERT INTO descuentos(Porcentaje) VALUES (10);
INSERT INTO descuentos(Porcentaje) VALUES (5);
INSERT INTO descuentos(Porcentaje) VALUES (35);

INSERT INTO productos(titulo, categoria, descripcion, precio, cantidad, idCategoria, idDescuento)
VALUES ('Silla RGB', 'Silla Gamer', 'Silla RGB max max', 1000, 3, 1, 2);
INSERT INTO productos(titulo, categoria, descripcion, precio, cantidad, idCategoria)
VALUES ('Silla plaza plaza', 'Silla Jardin', 'Silla amplia para tu jardin unica', 500, 5, 3);

INSERT INTO carrito(idUsuario, total) VALUES (2, 1000);
INSERT INTO carritoproductos(idProducto, idCarrito, cantidad) VALUES (1, 1, 1);

select * from usuarios;
select * from roles;
select * from categorias;
select * from descuentos;
select * from productos;
select * from carrito;
select * from carritoproductos;