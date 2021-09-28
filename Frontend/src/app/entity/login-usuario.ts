export class LoginUsuario{
    username: string;
    password: string;
    constructor(nombreUsuario: string, password: string){
        this.username = nombreUsuario;
        this.password = password;
    }
}