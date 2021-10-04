export class Huesped {

    huespedId?: number;
    nombre: string;
    apellido:string;
    telefono: string;
    correo: string;

     constructor(nombres: string, apellidos: string, telefonos: string, correos: string){

       
        this.nombre = nombres;
        this.apellido = apellidos;
        this.telefono = telefonos;
        this.correo = correos;
        
    }
}