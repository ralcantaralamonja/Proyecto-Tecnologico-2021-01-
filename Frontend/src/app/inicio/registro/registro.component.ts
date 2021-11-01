import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { NuevoUsuario } from 'src/app/entity/nuevo-usuario';
import { AuthService } from 'src/app/service/auth.service';
import { TokenService } from 'src/app/service/token.service';
import { UsuarioService } from 'src/app/service/usuario.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {

  usernameLogeado: string='';
  nuevoUsuario: NuevoUsuario; 
  nombres: string;
  apellidos: string;
  correo: string;
  username: string;
  password: string;
  rol: string;
  estado: string;

  roles: string[] = [];
  errMsj: string;
  isLogged = false;

  constructor(
    private tokenService: TokenService,
    private usuarioService: UsuarioService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {

    if(this.tokenService.getToken()){
      this.isLogged = true;
      this.roles = this.tokenService.getAuthorities();
      this.usernameLogeado = this.tokenService.getUserName();
    }    
  }


  onRegister(): void{
    this.nuevoUsuario = new NuevoUsuario(this.nombres, this.apellidos,  this.username, this.correo,  this.password, this.rol, this.estado);
    this.usuarioService.nuevoUsuario(this.usernameLogeado, this.nuevoUsuario).subscribe(
      data =>{
        this.toastr.success('Usuario creado', 'OK', {
          timeOut: 3000, positionClass:'toast-top-center'
        });       
        this.router.navigate(['/inicio']);
      },
      err => {       
        this.errMsj = err.error.mensaje;
        this.toastr.error(this.errMsj, 'Fail');
        console.log("error -> " + err.error.mensaje);
      }
    )
  }
}
