export class Reserva {

    reservaId?: number;
   // fecIngreso: Date;
    fecSalida: Date;
    huespedId: number;
    habitacionId: number;
    usuarioRegistro: String;
    //fecha_Registro: Date;
    //usuarioUltModificacion: String;
    //fechaUltModificacion: Date;
    //estado: any;
    


    constructor(fecSalida: Date, huespedId:number, habitacionId:number, usuarioRegistro: string) {
       
      //  this.fecIngreso = fecIngreso;
        this.fecSalida = fecSalida;
        this.huespedId = huespedId;
        this.habitacionId = habitacionId;
        this.usuarioRegistro = usuarioRegistro;
        //this.fecha_Registro = fecha_Registro;
        //this.usuarioUltModificacion = usuarioUltModificacion;
        //this.fechaUltModificacion = fechaUltModificacion;
        //this.estado = estado;
      

    }
}

//constructor(fecIngreso: Date, fecSalida: Date, huespedId:number, habitacionId:number, usuarioRegistro: string, fecha_Registro: Date, usuarioUltModificacion: string,
//fechaUltModificacion: Date, estado: any)