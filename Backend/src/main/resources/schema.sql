CREATE DATABASE BLONDYS;
USE BLONDYS;

CREATE TABLE producto
(
    Id_Producto              INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Nombre                   VARCHAR(30) NOT NULL,
    Precio_Venta             DOUBLE      NOT NULL,
    foto                     LONGTEXT,
    Id_Categoria             INT         NOT NULL,
    Id_Uni_Med               INT         NOT NULL,
    Id_Marca                 INT         NOT NULL,
    Estado                   INT         NOT NULL, -- 1 -> vigente | 2 -> descontinuado
    Usuario_Registro         VARCHAR(30) NOT NULL,
    Fecha_Registro           DATETIME    NOT NULL,
    Usuario_Ult_Modificacion VARCHAR(30),
    Fecha_Ult_Modificacion   DATETIME
);

CREATE TABLE marca
(
    Id_Marca INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Nombre   VARCHAR(50) NOT NULL,
    Logo     LONGTEXT,
    Estado   INT         NOT NULL
);-- 1 -> vigente | 2 -> descontinuado

CREATE TABLE huesped
(
    Id_Huesped               INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Nombre                   VARCHAR(30),
    Apellido                 VARCHAR(30),
    Telefono                 VARCHAR(9),
    Correo                   VARCHAR(150),
    Estado                   INT         NOT NULL, -- 1 -> activo | 2 -> inactivo
    Usuario_Registro         VARCHAR(30) NOT NULL,
    Fecha_Registro           DATETIME    NOT NULL,
    Usuario_Ult_Modificacion VARCHAR(30),
    Fecha_Ult_Modificacion   DATETIME,
    Observaciones            VARCHAR(400)
);

CREATE TABLE documento
(
    Id_Documento     INT AUTO_INCREMENT PRIMARY KEY,
    Id_Tipo          INT         NOT NULL,
    Numero_Documento VARCHAR(12) NOT NULL,
    Id_Huesped       INT         NOT NULL
);

CREATE TABLE tipo_documento
(
    Id_Tipo INT AUTO_INCREMENT PRIMARY KEY,
    Nombre  VARCHAR(21) NOT NULL
);

CREATE TABLE detalle_venta
(
    Id_Producto INT      NOT NULL,
    Id_Venta    INT      NOT NULL,
    Descuento   DOUBLE,
    Fecha       DATETIME NOT NULL,
    Estado      INT      NOT NULL, -- 1 -> Pendiente | 2 -> Pagada | 3 -> Anulada
    PRIMARY KEY (Id_Producto, Id_Venta)
);

CREATE TABLE perdida
(
    Id_Perdida               INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Precio_Compra            DOUBLE      NOT NULL,
    Cantidad                 INT         NOT NULL,
    Fecha                    DATETIME    NOT NULL,
    Id_Producto              INT         NOT NULL,
    Usuario_Registro         VARCHAR(30) NOT NULL,
    Fecha_Registro           DATETIME    NOT NULL,
    Usuario_Ult_Modificacion VARCHAR(30),
    Fecha_Ult_Modificacion   DATETIME
);

CREATE TABLE usuario
( -- Tabla para el login
    Id_Usuario     INT AUTO_INCREMENT PRIMARY KEY,
    Nombres        VARCHAR(30),
    Apellidos      VARCHAR(50),
-- Telefono VARCHAR(9),
-- Foto VARCHAR(400),
    Username       VARCHAR(30)  NOT NULL UNIQUE,
    Password       VARCHAR(300) NOT NULL,
    Correo         VARCHAR(255),
    Fecha_Creacion DATETIME     NOT NULL,
    Estado         INT          NOT NULL
); -- 1 -> Activo | 2 - Inactivo | 3 - Eliminado

CREATE TABLE rol
(
    Id_Rol INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Nombre VARCHAR(30) NOT NULL
);

CREATE TABLE venta
(
    Id_Venta                 INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Descuento                DOUBLE      NOT NULL,
    Fecha                    DATETIME    NOT NULL,
    Id_Huesped               INT         NOT NULL,
    Usuario_Registro         VARCHAR(30) NOT NULL,
    Fecha_Registro           DATETIME    NOT NULL,
    Usuario_Ult_Modificacion VARCHAR(30),
    Fecha_Ult_Modificacion   DATETIME
);

CREATE TABLE compra
(
    Id_Compra                INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Fecha                    DATETIME    NOT NULL,
    Usuario_Registro         VARCHAR(30) NOT NULL,
    Fecha_Registro           DATETIME    NOT NULL,
    Usuario_Ult_Modificacion VARCHAR(30),
    Fecha_Ult_Modificacion   DATETIME
);

CREATE TABLE detalle_compra
(
    Id_Producto INT    NOT NULL,
    Id_Compra   INT    NOT NULL,
    Precio      DOUBLE NOT NULL,
    Cantidad    INT    NOT NULL,
    Descuento   DOUBLE NOT NULL,
    PRIMARY KEY (Id_Producto, Id_Compra)
);

CREATE TABLE unidad_medida
(
    Id_Uni_Med INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Nombre     VARCHAR(100) NOT NULL
);

CREATE TABLE categoria
(
    Id_Categoria INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Nombre       VARCHAR(150) NOT NULL
);

CREATE TABLE habitacion
(
    Id_Hab                   INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Numero                   VARCHAR(4),
    Foto                     VARCHAR(700),
    Id_Tipo                  INT         NOT NULL,
    Usuario_Registro         VARCHAR(30) NOT NULL,
    Fecha_Registro           DATETIME    NOT NULL,
    Usuario_Ult_Modificacion VARCHAR(30),
    Fecha_Ult_Modificacion   DATETIME,
    Estado                   INT         NOT NULL -- 1 -> disponible | 2 -> ocupada | 3 -> mantenimiento
);

CREATE TABLE reserva
(
    Id_Reserva               INT         NOT NULL,
    Fec_Ingreso              DATETIME    NOT NULL,
    Fec_Salida               DATETIME    NOT NULL,
    Id_Huesped               INT         NOT NULL,
    Id_Hab                   INT         NOT NULL,
    Usuario_Registro         VARCHAR(30) NOT NULL,
    Fecha_Registro           DATETIME    NOT NULL,
    Usuario_Ult_Modificacion VARCHAR(30),
    Fecha_Ult_Modificacion   DATETIME,
    PRIMARY KEY (Id_Reserva, Id_Huesped)
);

CREATE TABLE rol_usuario
(
    Id_Usuario INT NOT NULL,
    Id_Rol     INT NOT NULL,
    PRIMARY KEY (Id_Usuario, Id_Rol)
);

CREATE TABLE tipo_habitacion
(
    Id_Tipo INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Nombre  VARCHAR(50) NOT NULL,
    Aforo   INT         NOT NULL,
    Banio   INT         NOT NULL, -- propio -> 1 | compartido -> 2
    Precio  DOUBLE      NOT NULL
);


ALTER TABLE producto
    ADD CONSTRAINT PRODUCTO_Id_Categoria_CATEGORIA_Id_Categoria FOREIGN KEY (Id_Categoria) REFERENCES categoria (Id_Categoria);
ALTER TABLE producto
    ADD CONSTRAINT PRODUCTO_Id_Uni_Med_UNIDAD_MEDIDA_Id_Uni_Med FOREIGN KEY (Id_Uni_Med) REFERENCES unidad_medida (Id_Uni_Med);
ALTER TABLE producto
    ADD CONSTRAINT PRODUCTO_Id_Marca_MARCA_Id_Marca FOREIGN KEY (Id_Marca) REFERENCES marca (Id_Marca);
ALTER TABLE detalle_venta
    ADD CONSTRAINT DETALLE_VENTA_Id_Producto_PRODUCTO_Id_Producto FOREIGN KEY (Id_Producto) REFERENCES producto (Id_Producto);
ALTER TABLE detalle_venta
    ADD CONSTRAINT DETALLE_VENTA_Id_Venta_VENTA_Id_Venta FOREIGN KEY (Id_Venta) REFERENCES venta (Id_Venta);
ALTER TABLE perdida
    ADD CONSTRAINT PERDIDA_Id_Producto_PRODUCTO_Id_Producto FOREIGN KEY (Id_Producto) REFERENCES producto (Id_Producto);
ALTER TABLE venta
    ADD CONSTRAINT VENTA_Id_Huesped_HUESPED_Id_Huesped FOREIGN KEY (Id_Huesped) REFERENCES huesped (Id_Huesped);
ALTER TABLE detalle_compra
    ADD CONSTRAINT DETALLE_COMPRA_Id_Producto_PRODUCTO_Id_Producto FOREIGN KEY (Id_Producto) REFERENCES producto (Id_Producto);
ALTER TABLE detalle_compra
    ADD CONSTRAINT DETALLE_COMPRA_Id_Compra_COMPRA_Id_Compra FOREIGN KEY (Id_Compra) REFERENCES compra (Id_Compra);
ALTER TABLE habitacion
    ADD CONSTRAINT HABITACION_Id_Tipo_TIPO_HABITACION_Id_Tipo FOREIGN KEY (Id_Tipo) REFERENCES tipo_habitacion (Id_Tipo);
ALTER TABLE reserva
    ADD CONSTRAINT RESERVA_Id_Huesped_HUESPED_Id_Huesped FOREIGN KEY (Id_Huesped) REFERENCES huesped (Id_Huesped);
ALTER TABLE reserva
    ADD CONSTRAINT RESERVA_Id_Hab_HABITACION_Id_Hab FOREIGN KEY (Id_Hab) REFERENCES habitacion (Id_Hab);
ALTER TABLE rol_usuario
    ADD CONSTRAINT ROL_USUARIO_Id_Usuario_USUARIO_Id_Usuario FOREIGN KEY (Id_Usuario) REFERENCES usuario (Id_Usuario);
ALTER TABLE rol_usuario
    ADD CONSTRAINT ROL_USUARIO_Id_Rol_ROL_Id_Rol FOREIGN KEY (Id_Rol) REFERENCES rol (Id_Rol);
ALTER TABLE documento
    ADD CONSTRAINT DOCUMENTO_USUARIO FOREIGN KEY (Id_Huesped) REFERENCES huesped (Id_Huesped);
ALTER TABLE documento
    ADD CONSTRAINT DOCUMENTO_TIPO FOREIGN KEY (Id_Tipo) REFERENCES tipo_documento (Id_Tipo);

INSERT INTO rol
VALUES (1, 'ADMIN'),
       (2, 'MANAGER'),
       (3, 'USER');

INSERT INTO usuario (Id_Usuario, Username, Password, Estado, Fecha_Creacion)
VALUES (1, 'admin', '$2a$10$Uv3FW83F97Y5sc3iUziZ4Ov72SkPiwg12g5GsBqBjQ2IZjWcQB44K', 1, sysdate());

INSERT INTO rol_usuario (Id_Usuario, Id_Rol)
VALUES (1, 1),
       (1, 2),
       (1, 3);

INSERT INTO tipo_documento (Nombre)
VALUES ('DNI'),
       ('PASAPORTE'),
       ('CARNET DE EXTRANJERIA');

INSERT INTO tipo_habitacion (Nombre, Aforo, Banio, Precio)
VALUES ('Ashland', 4, 1, 80.0),
       ('Nico', 4, 2, 60.0),
       ('Bella', 3, 1, 60.0),
       ('Guille', 3, 2, 45.0),
       ('Dolly', 1, 2, 15.0);

INSERT INTO habitacion (Id_Tipo, Foto, Usuario_Registro, Fecha_Registro, Estado)
VALUES (1, 'assets/imagenes/h1.jpg', 'admin', sysdate(), 1),
       (2, 'assets/imagenes/h2.jpg', 'admin', sysdate(), 1),
       (3, 'assets/imagenes/h3.jpg', 'admin', sysdate(), 1),
       (4, 'assets/imagenes/h4.jpg', 'admin', sysdate(), 1),
       (5, 'assets/imagenes/h5.jpg', 'admin', sysdate(), 1);