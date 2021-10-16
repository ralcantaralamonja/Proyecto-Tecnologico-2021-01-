CREATE DATABASE BLONDYS;
USE BLONDYS;

CREATE TABLE PRODUCTO
(
    Id_Producto              INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Nombre                   VARCHAR(30)        NOT NULL,
    Precio_Venta             DOUBLE             NOT NULL,
    foto                     LONGTEXT,
    Id_Categoria             INT                NOT NULL,
    Id_Uni_Med               INT                NOT NULL,
    Id_Marca                 INT                NOT NULL,
    Estado                   INT                NOT NULL, -- 1 -> vigente | 2 -> descontinuado
    Usuario_Registro         VARCHAR(30)        NOT NULL,
    Fecha_Registro           DATETIME           NOT NULL,
    Usuario_Ult_Modificacion VARCHAR(30),
    Fecha_Ult_Modificacion   DATETIME
);

CREATE TABLE MARCA
(
    Id_Marca INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Nombre   VARCHAR(50)        NOT NULL,
    Logo     LONGTEXT,
    Estado   INT                NOT NULL
);-- 1 -> vigente | 2 -> descontinuado

CREATE TABLE HUESPED
(
    Id_Huesped               INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Nombre                   VARCHAR(30),
    Apellido                 VARCHAR(30),
    Telefono                 VARCHAR(9),
    Correo                   VARCHAR(150),
    Estado                   INT                NOT NULL, -- 1 -> activo | 2 -> inactivo
    Usuario_Registro         VARCHAR(30)        NOT NULL,
    Fecha_Registro           DATETIME           NOT NULL,
    Usuario_Ult_Modificacion VARCHAR(30),
    Fecha_Ult_Modificacion   DATETIME,
    Observaciones            VARCHAR(400)
);

CREATE TABLE DOCUMENTO
(
    Id_Documento     INT AUTO_INCREMENT PRIMARY KEY,
    Id_Tipo          INT         NOT NULL,
    Numero_Documento VARCHAR(12) NOT NULL,
    Id_Huesped       INT         NOT NULL
);

CREATE TABLE TIPO_DOCUMENTO
(
    Id_Tipo INT AUTO_INCREMENT PRIMARY KEY,
    Nombre  VARCHAR(21) NOT NULL
);

CREATE TABLE DETALLE_VENTA
(
    Id_Producto INT      NOT NULL,
    Id_Venta    INT      NOT NULL,
    Descuento   DOUBLE,
    Fecha       DATETIME NOT NULL,
    Estado      INT      NOT NULL, -- 1 -> Pendiente | 2 -> Pagada | 3 -> Anulada
    PRIMARY KEY (Id_Producto, Id_Venta)
);

CREATE TABLE PERDIDA
(
    Id_Perdida               INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Precio_Compra            DOUBLE             NOT NULL,
    Cantidad                 INT                NOT NULL,
    Fecha                    DATETIME           NOT NULL,
    Id_Producto              INT                NOT NULL,
    Usuario_Registro         VARCHAR(30)        NOT NULL,
    Fecha_Registro           DATETIME           NOT NULL,
    Usuario_Ult_Modificacion VARCHAR(30),
    Fecha_Ult_Modificacion   DATETIME
);

CREATE TABLE USUARIO
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
); -- 1 -> Activo | 2 - Inactivo

CREATE TABLE ROL
(
    Id_Rol INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Nombre VARCHAR(30)        NOT NULL
);

CREATE TABLE VENTA
(
    Id_Venta                 INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Descuento                DOUBLE             NOT NULL,
    Fecha                    DATETIME           NOT NULL,
    Id_Huesped               INT                NOT NULL,
    Usuario_Registro         VARCHAR(30)        NOT NULL,
    Fecha_Registro           DATETIME           NOT NULL,
    Usuario_Ult_Modificacion VARCHAR(30),
    Fecha_Ult_Modificacion   DATETIME
);

CREATE TABLE COMPRA
(
    Id_Compra                INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Fecha                    DATETIME           NOT NULL,
    Usuario_Registro         VARCHAR(30)        NOT NULL,
    Fecha_Registro           DATETIME           NOT NULL,
    Usuario_Ult_Modificacion VARCHAR(30),
    Fecha_Ult_Modificacion   DATETIME
);

CREATE TABLE DETALLE_COMPRA
(
    Id_Producto INT    NOT NULL,
    Id_Compra   INT    NOT NULL,
    Precio      DOUBLE NOT NULL,
    Cantidad    INT    NOT NULL,
    Descuento   DOUBLE NOT NULL,
    PRIMARY KEY (Id_Producto, Id_Compra)
);

CREATE TABLE UNIDAD_MEDIDA
(
    Id_Uni_Med INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Nombre     VARCHAR(100)       NOT NULL
);

CREATE TABLE CATEGORIA
(
    Id_Categoria INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Nombre       VARCHAR(150)       NOT NULL
);

CREATE TABLE HABITACION
(
    Id_Hab                   INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Numero                   VARCHAR(4)         NOT NULL,
    Id_Tipo                  INT                NOT NULL,
    Usuario_Registro         VARCHAR(30)        NOT NULL,
    Fecha_Registro           DATETIME           NOT NULL,
    Usuario_Ult_Modificacion VARCHAR(30),
    Fecha_Ult_Modificacion   DATETIME,
    Estado                   INT                NOT NULL
); -- 1 -> disponible | 2 -> ocupada | 3 -> mantenimiento

CREATE TABLE RESERVA
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

CREATE TABLE ROL_USUARIO
(
    Id_Usuario INT NOT NULL,
    Id_Rol     INT NOT NULL,
    PRIMARY KEY (Id_Usuario, Id_Rol)
);

CREATE TABLE TIPO_HABITACION
(
    Id_Tipo INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Nombre  VARCHAR(50)        NOT NULL,
    Precio  DOUBLE             NOT NULL
);


ALTER TABLE PRODUCTO
    ADD CONSTRAINT PRODUCTO_Id_Categoria_CATEGORIA_Id_Categoria FOREIGN KEY (Id_Categoria) REFERENCES CATEGORIA (Id_Categoria);
ALTER TABLE PRODUCTO
    ADD CONSTRAINT PRODUCTO_Id_Uni_Med_UNIDAD_MEDIDA_Id_Uni_Med FOREIGN KEY (Id_Uni_Med) REFERENCES UNIDAD_MEDIDA (Id_Uni_Med);
ALTER TABLE PRODUCTO
    ADD CONSTRAINT PRODUCTO_Id_Marca_MARCA_Id_Marca FOREIGN KEY (Id_Marca) REFERENCES MARCA (Id_Marca);
ALTER TABLE DETALLE_VENTA
    ADD CONSTRAINT DETALLE_VENTA_Id_Producto_PRODUCTO_Id_Producto FOREIGN KEY (Id_Producto) REFERENCES PRODUCTO (Id_Producto);
ALTER TABLE DETALLE_VENTA
    ADD CONSTRAINT DETALLE_VENTA_Id_Venta_VENTA_Id_Venta FOREIGN KEY (Id_Venta) REFERENCES VENTA (Id_Venta);
ALTER TABLE PERDIDA
    ADD CONSTRAINT PERDIDA_Id_Producto_PRODUCTO_Id_Producto FOREIGN KEY (Id_Producto) REFERENCES PRODUCTO (Id_Producto);
ALTER TABLE VENTA
    ADD CONSTRAINT VENTA_Id_Huesped_HUESPED_Id_Huesped FOREIGN KEY (Id_Huesped) REFERENCES HUESPED (Id_Huesped);
ALTER TABLE DETALLE_COMPRA
    ADD CONSTRAINT DETALLE_COMPRA_Id_Producto_PRODUCTO_Id_Producto FOREIGN KEY (Id_Producto) REFERENCES PRODUCTO (Id_Producto);
ALTER TABLE DETALLE_COMPRA
    ADD CONSTRAINT DETALLE_COMPRA_Id_Compra_COMPRA_Id_Compra FOREIGN KEY (Id_Compra) REFERENCES COMPRA (Id_Compra);
ALTER TABLE HABITACION
    ADD CONSTRAINT HABITACION_Id_Tipo_TIPO_HABITACION_Id_Tipo FOREIGN KEY (Id_Tipo) REFERENCES TIPO_HABITACION (Id_Tipo);
ALTER TABLE RESERVA
    ADD CONSTRAINT RESERVA_Id_Huesped_HUESPED_Id_Huesped FOREIGN KEY (Id_Huesped) REFERENCES HUESPED (Id_Huesped);
ALTER TABLE RESERVA
    ADD CONSTRAINT RESERVA_Id_Hab_HABITACION_Id_Hab FOREIGN KEY (Id_Hab) REFERENCES HABITACION (Id_Hab);
ALTER TABLE ROL_USUARIO
    ADD CONSTRAINT ROL_USUARIO_Id_Usuario_USUARIO_Id_Usuario FOREIGN KEY (Id_Usuario) REFERENCES USUARIO (Id_Usuario);
ALTER TABLE ROL_USUARIO
    ADD CONSTRAINT ROL_USUARIO_Id_Rol_ROL_Id_Rol FOREIGN KEY (Id_Rol) REFERENCES ROL (Id_Rol);
ALTER TABLE DOCUMENTO
    ADD CONSTRAINT DOCUMENTO_USUARIO FOREIGN KEY (Id_Huesped) REFERENCES HUESPED (Id_Huesped);
ALTER TABLE DOCUMENTO
    ADD CONSTRAINT DOCUMENTO_TIPO FOREIGN KEY (Id_Tipo) REFERENCES TIPO_DOCUMENTO (Id_Tipo);

INSERT INTO ROL
VALUES (1, 'ADMIN'),
       (2, 'USER');

INSERT INTO USUARIO (Id_Usuario, Username, Password, Estado, Fecha_Creacion)
VALUES (1, 'admin', '$2a$10$Uv3FW83F97Y5sc3iUziZ4Ov72SkPiwg12g5GsBqBjQ2IZjWcQB44K', 1, sysdate());

INSERT INTO ROL_USUARIO (Id_Usuario, Id_Rol)
VALUES (1, 1),
       (1, 2);

INSERT INTO TIPO_DOCUMENTO (Nombre)
VALUES ('DNI'),
       ('PASAPORTE'),
       ('PASAPORTE'),
       ('CARNET DE EXTRANJERIA')
