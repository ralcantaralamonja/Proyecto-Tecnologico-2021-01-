export class NuevoUsuario{
    nombres: string;
    apellidos: string;
    username:string;
    password: string;
    correo: string;
    constructor(nombres: string, apellidos: string, username: string, correo: string, password: string){

        this.nombres = nombres;
        this.apellidos = apellidos;
        this.username = username;
        this.correo = correo;
        this.password = password;
        
    }
}