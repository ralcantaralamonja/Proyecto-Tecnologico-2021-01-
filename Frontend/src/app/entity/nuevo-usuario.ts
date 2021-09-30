export class NuevoUsuario{
    nombres: string;
    apellidos: string;
    username:string;
    password: string;
    correo: string;
    constructor(nombre: string, apellido: string, nombreUsuario: string, email: string, password: string){

        this.nombres = nombre;
        this.apellidos = apellido;
        this.username = nombreUsuario;
        this.correo = email;
        this.password = password;
        
    }
}