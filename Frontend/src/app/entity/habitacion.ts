export class Cuarto {

    habitacionId?: number;
    numero: string;
    foto: string;
    tipoId: number;
    usuarioRegistro: string;
    fecha_Registro: Date;
    usuarioUltModificacion: string;
    fechaUltModificacion: Date;
    estado: any;
    tipo: string;
    aforo: number;
    banio: number;
    precio: number;


    constructor(numeros: string, foto: string, tipoId: number, usuarioRegistro: string, fecha_Registro: Date, usuarioUltModificacion: string,
        fechaUltModificacion: Date, estado: any, tipo: string, aforo:number, banio: number, precio:number) {
        this.numero = numeros;
        this.foto=foto;
        this.tipoId = tipoId;
        this.usuarioRegistro = usuarioRegistro;
        this.fecha_Registro = fecha_Registro;
        this.usuarioUltModificacion = usuarioUltModificacion;
        this.fechaUltModificacion = fechaUltModificacion;
        this.estado = estado;
        this.tipo = tipo;
        this.aforo = aforo;
        this.banio = banio;
        this.precio = precio;

    }
}