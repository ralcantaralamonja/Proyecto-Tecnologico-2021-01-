export class Huesped {

huespedId: number;
nombre: string;
apellido: string;
telefono: string;
correo: string;
usuarioRegistro: string;
fechaRegistro: Date;
usuarioUltModificacion: string;
fechaUltModificacion: Date;
observaciones: string;
tipoDocumento: string;
numeroDocumento: string;
estado: any;

    constructor(huespedId: number,nombre: string,apellido: string,telefono: string,correo: string,
        usuarioRegistro: string,fechaRegistro: Date,usuarioUltModificacion: string,
        fechaUltModificacion: Date,observaciones: string,tipoDocumento: string,numeroDocumento: string,
        estado: any){
        
            this.huespedId = huespedId;
            this.nombre = nombre;
            this.apellido = apellido;
            this.telefono = telefono;
            this.correo = correo;
            this.usuarioRegistro = usuarioRegistro;
            this.fechaRegistro = fechaRegistro;
            this.usuarioUltModificacion = usuarioUltModificacion;
            this.fechaUltModificacion = fechaUltModificacion;
            this.observaciones = observaciones;
            this.tipoDocumento = tipoDocumento;
            this.numeroDocumento = numeroDocumento;
            this.estado = estado;
    }

}