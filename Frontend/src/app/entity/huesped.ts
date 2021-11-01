/*export class Huesped {

    huespedId?: number;
    nombre: string;
    apellido:string;
    telefono: string;
    correo: string;
    usuarioRegistro:string;
    usuarioUltModificacion: string;
    observaciones:string;




    constructor(nombres: string, apellidos: string, telefonos: string, correos: string, usuarioRegistros: string,usuariosUltModificacion:string,observacion:string  ){       
        this.nombre = nombres;
        this.apellido = apellidos;
        this.telefono = telefonos;
        this.correo = correos;
        this.usuarioRegistro = usuarioRegistros;
        this.usuarioUltModificacion = usuariosUltModificacion;
        this.observaciones = observacion        
    }
}*/

export class Huesped {

    data: Data;

   /* constructor(nombres: string, apellidos: string, telefonos: string, correos: string, usuarioRegistros: string,usuariosUltModificacion:string,observacion:string  ){       
        this.nombre = nombres;
        this.apellido = apellidos;
        this.telefono = telefonos;
        this.correo = correos;
        this.usuarioRegistro = usuarioRegistros;
        this.usuarioUltModificacion = usuariosUltModificacion;
        this.observaciones = observacion        
    }*/
}

class Data {
    numero?: number;
    nombre_completo: string;
    nombres: string;
    apellido_paterno: string;
    apellido_materno: string;
    domicilio_direccion: string;

    constructor( nombres_completos: string, nombre: string, apellidos_paterno: string, apellidos_materno: string,domicilios_direccion:string ){       
        
        this.nombre_completo = nombres_completos;
        this.nombres = nombre;
        this.apellido_paterno = apellidos_paterno;
        this.apellido_materno = apellidos_materno;
        this.domicilio_direccion = domicilios_direccion;
    }

}