DROP TABLE usuario;

CREATE TABLE usuario (
nombre_usuario VARCHAR2(100) PRIMARY KEY,
contrasena VARCHAR2(100),
direccion VARCHAR2(100)
);

INSERT INTO usuario(nombre_usuario, contrasena, direccion) VALUES('admin', 'admin', 'direccion_original_bdchain');

INSERT INTO usuario(nombre_usuario, contrasena, direccion) VALUES('holi', 'password', 'jhdlhjashjldkhafhdsajshd');

SELECT nombre_usuario FROM usuario WHERE nombre_usuario = 'Algun nombre';
SELECT * FROM usuario WHERE nombre_usuario = 'Algun nombre' AND contrasena = 'Alguna contraseña';