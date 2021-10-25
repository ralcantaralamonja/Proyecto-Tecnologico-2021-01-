export class Usuario{
    nombres: string;
    apellidos: string;
    username:string;
    password: string;
    correo: string;
    estado: string;
    rol: string[];
    constructor(nombres: string, apellidos: string, username: string, correo: string, password: string, estado:string, rol: string){

        this.nombres = nombres;
        this.apellidos = apellidos;
        this.username = username;
        this.correo = correo;
        this.password = password;
        this.estado = estado;
        this.rol[0] = rol;
        
    }
}