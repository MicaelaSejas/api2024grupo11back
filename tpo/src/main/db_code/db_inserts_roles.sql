USE tpo;

insert into roles(descripcion) values ("Vendedor");
insert into roles(descripcion) values ("Comprador");

select * from roles;


insert into usuarios(apellido, email, nombre, password, username, roles_id)
values("ape", "nombreape@gmail.com", "nom", "1234", "nombreape", 1);