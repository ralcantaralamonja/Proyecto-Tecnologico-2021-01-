export class Huesped {

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
}