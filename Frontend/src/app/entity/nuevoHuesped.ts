export class NuevoHuesped {

    huespedId?: number;
    nombre: string;
    apellido: string;
    telefono: string;
    correo: string;
    usuarioRegistro: string;
    observaciones: string;
    tipoDocumento: string;
    numeroDocumento: string;

    constructor(nombres: string, apellidos: string, telefonos: string, correo: string, usuarioRegistro: string, observaciones: string, tipoDocumento: string, numeroDocumento: string) {
        this.nombre = nombres;
        this.apellido = apellidos;
        this.telefono = telefonos;
        this.correo = correo;
        this.usuarioRegistro = usuarioRegistro;
        this.observaciones = observaciones;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
    }
}