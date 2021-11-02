create database BLONDYS;
use BLONDYS;

create table producto
(
    id_producto              int auto_increment not null primary key,
    nombre                   varchar(30)        not null,
    precio_venta             double             not null,
    foto                     longtext,
    id_categoria             int                not null,
    id_uni_med               int                not null,
    id_marca                 int                not null,
    estado                   int                not null, -- 1 -> vigente | 2 -> descontinuado
    usuario_registro         varchar(30)        not null,
    fecha_registro           datetime           not null,
    usuario_ult_modificacion varchar(30),
    fecha_ult_modificacion   datetime
);

create table marca
(
    id_marca int auto_increment not null primary key,
    nombre   varchar(50)        not null,
    logo     longtext,
    estado   int                not null
);-- 1 -> vigente | 2 -> descontinuado

create table huesped
(
    id_huesped               int auto_increment not null primary key,
    nombre                   varchar(30),
    apellido                 varchar(30),
    telefono                 varchar(9),
    correo                   varchar(150),
    estado                   int                not null, -- 1 -> activo | 2 -> inactivo
    usuario_registro         varchar(30)        not null,
    fecha_registro           datetime           not null,
    usuario_ult_modificacion varchar(30),
    fecha_ult_modificacion   datetime,
    observaciones            varchar(400)
);

create table documento
(
    id_documento     int auto_increment primary key,
    id_tipo          int         not null,
    numero_documento varchar(12) not null,
    id_huesped       int         not null
);

create table tipo_documento
(
    id_tipo int auto_increment primary key,
    nombre  varchar(21) not null
);

create table detalle_venta
(
    id_producto int      not null,
    id_venta    int      not null,
    descuento   double,
    fecha       datetime not null,
    estado      int      not null, -- 1 -> pendiente | 2 -> pagada | 3 -> anulada
    primary key (id_producto, id_venta)
);

create table perdida
(
    id_perdida               int auto_increment not null primary key,
    precio_compra            double             not null,
    cantidad                 int                not null,
    fecha                    datetime           not null,
    id_producto              int                not null,
    usuario_registro         varchar(30)        not null,
    fecha_registro           datetime           not null,
    usuario_ult_modificacion varchar(30),
    fecha_ult_modificacion   datetime
);

create table usuario
( -- tabla para el login
    id_usuario     int auto_increment primary key,
    nombres        varchar(30),
    apellidos      varchar(50),
-- telefono varchar(9),
-- foto varchar(400),
    username       varchar(30)  not null unique,
    password       varchar(300) not null,
    correo         varchar(255),
    fecha_creacion datetime     not null,
    estado         int          not null
); -- 1 -> activo | 2 - inactivo | 3 - eliminado

create table rol
(
    id_rol int auto_increment not null primary key,
    nombre varchar(30)        not null
);

create table venta
(
    id_venta                 int auto_increment not null primary key,
    descuento                double             not null,
    fecha                    datetime           not null,
    id_huesped               int                not null,
    usuario_registro         varchar(30)        not null,
    fecha_registro           datetime           not null,
    usuario_ult_modificacion varchar(30),
    fecha_ult_modificacion   datetime
);

create table compra
(
    id_compra                int auto_increment not null primary key,
    fecha                    datetime           not null,
    usuario_registro         varchar(30)        not null,
    fecha_registro           datetime           not null,
    usuario_ult_modificacion varchar(30),
    fecha_ult_modificacion   datetime
);

create table detalle_compra
(
    id_producto int    not null,
    id_compra   int    not null,
    precio      double not null,
    cantidad    int    not null,
    descuento   double not null,
    primary key (id_producto, id_compra)
);

create table unidad_medida
(
    id_uni_med int auto_increment not null primary key,
    nombre     varchar(100)       not null
);

create table categoria
(
    id_categoria int auto_increment not null primary key,
    nombre       varchar(150)       not null
);

create table habitacion
(
    id_hab                   int auto_increment not null primary key,
    numero                   varchar(4)         not null,
    id_tipo                  int                not null,
    usuario_registro         varchar(30)        not null,
    fecha_registro           datetime           not null,
    usuario_ult_modificacion varchar(30),
    fecha_ult_modificacion   datetime,
    estado                   int                not null
); -- 1 -> disponible | 2 -> ocupada | 3 -> mantenimiento

create table reserva
(
    id_reserva               int         not null,
    fec_ingreso              datetime    not null,
    fec_salida               datetime    not null,
    id_huesped               int         not null,
    id_hab                   int         not null,
    usuario_registro         varchar(30) not null,
    fecha_registro           datetime    not null,
    usuario_ult_modificacion varchar(30),
    fecha_ult_modificacion   datetime,
    primary key (id_reserva, id_huesped)
);

create table rol_usuario
(
    id_usuario int not null,
    id_rol     int not null,
    primary key (id_usuario, id_rol)
);

create table tipo_habitacion
(
    id_tipo int auto_increment not null primary key,
    nombre  varchar(50)        not null,
    precio  double             not null
);


alter table producto
    add constraint producto_id_categoria_categoria_id_categoria foreign key (id_categoria) references categoria (id_categoria);
alter table producto
    add constraint producto_id_uni_med_unidad_medida_id_uni_med foreign key (id_uni_med) references unidad_medida (id_uni_med);
alter table producto
    add constraint producto_id_marca_marca_id_marca foreign key (id_marca) references marca (id_marca);
alter table detalle_venta
    add constraint detalle_venta_id_producto_producto_id_producto foreign key (id_producto) references producto (id_producto);
alter table detalle_venta
    add constraint detalle_venta_id_venta_venta_id_venta foreign key (id_venta) references venta (id_venta);
alter table perdida
    add constraint perdida_id_producto_producto_id_producto foreign key (id_producto) references producto (id_producto);
alter table venta
    add constraint venta_id_huesped_huesped_id_huesped foreign key (id_huesped) references huesped (id_huesped);
alter table detalle_compra
    add constraint detalle_compra_id_producto_producto_id_producto foreign key (id_producto) references producto (id_producto);
alter table detalle_compra
    add constraint detalle_compra_id_compra_compra_id_compra foreign key (id_compra) references compra (id_compra);
alter table habitacion
    add constraint habitacion_id_tipo_tipo_habitacion_id_tipo foreign key (id_tipo) references tipo_habitacion (id_tipo);
alter table reserva
    add constraint reserva_id_huesped_huesped_id_huesped foreign key (id_huesped) references huesped (id_huesped);
alter table reserva
    add constraint reserva_id_hab_habitacion_id_hab foreign key (id_hab) references habitacion (id_hab);
alter table rol_usuario
    add constraint rol_usuario_id_usuario_usuario_id_usuario foreign key (id_usuario) references usuario (id_usuario);
alter table rol_usuario
    add constraint rol_usuario_id_rol_rol_id_rol foreign key (id_rol) references rol (id_rol);
alter table documento
    add constraint documento_usuario foreign key (id_huesped) references huesped (id_huesped);
alter table documento
    add constraint documento_tipo foreign key (id_tipo) references tipo_documento (id_tipo);

insert into rol
values (1, 'ADMIN'),
       (2, 'MANAGER'),
       (3, 'USER');

insert into usuario (id_usuario, username, password, estado, fecha_creacion)
values (1, 'admin', '$2a$10$Uv3FW83F97Y5sc3iUziZ4Ov72SkPiwg12g5GsBqBjQ2IZjWcQB44K', 1, sysdate());

insert into rol_usuario (id_usuario, id_rol)
values (1, 1),
       (1, 2),
       (1, 3);

insert into tipo_documento (nombre)
values ('DNI'),
       ('PASAPORTE'),
       ('CARNET DE EXTRANJERIA')
