/*export class NuevoHuesped {

    huespedId?: number;
    nombre: string;
    apellido:string;
    telefono: string;
    correo: string;
    usuarioRegistro:string;
    observaciones:string;

    constructor(nombres: string, apellidos: string, telefonos: string, correos: string, usuarioRegistros: string,observacion:string  ){       
        this.nombre = nombres;
        this.apellido = apellidos;
        this.telefono = telefonos;
        this.correo = correos;
        this.usuarioRegistro = usuarioRegistros;
        this.observaciones = observacion        
    }
}*/

export class NuevoHuesped {
   
    data: Data;
    
}

class Data {
    numero: number;
    nombre_completo: string;
   

    constructor( numeros: number, nombres_completos: string ){       
        
        this.numero = numeros;
        this.nombre_completo = nombres_completos;
    
    }

}