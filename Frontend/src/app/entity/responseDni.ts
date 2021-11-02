export class ResponseDni{
    success:boolean;
    data:Persona;
    source:string;
}

export class Persona{
    numero: string;
    nombres: string;
    apellido_paterno: string;
    apellido_materno: string;
    domicilio_direccion: string;
}