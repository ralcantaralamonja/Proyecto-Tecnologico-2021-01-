import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { LoginUsuario } from 'src/app/entity/login-usuario';
import { NuevoUsuario } from 'src/app/entity/nuevo-usuario';
import { Usuario } from 'src/app/entity/usuario';
import { TokenService } from 'src/app/service/token.service';
import { UsuarioService } from 'src/app/service/usuario.service';

@Component({
  selector: 'app-lista-usuario',
  templateUrl: './lista-usuario.component.html',
  styleUrls: ['./lista-usuario.component.css']
})
export class ListaUsuarioComponent implements OnInit {

  usuario: Usuario;
  usuarios: Usuario[] = [];
  roles: string[];
  isAdmin = false;
  loginUsuario: LoginUsuario;

  constructor(
    private usuarioService: UsuarioService,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { }

  ngOnInit(): void {
    this.cargarUsuarios();
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ADMIN') {
        this.isAdmin = true;
      }
      this.loginUsuario.username = this.tokenService.getUserName();
      this.loginUsuario.password = 'ricardoinfiel';
      console.log(this.loginUsuario.username);
      console.log(this.tokenService.getUserName());
    });

  }

  cargarUsuarios(): void {
    this.usuarioService.lista().subscribe(
      data => {
        this.usuarios = data;
      },
      err => {
        console.log(err);
      }
    );
  }

  borrar(id: string) {
    console.log(this.loginUsuario.username);
    this.usuarioService.eliminar(id, this.loginUsuario).subscribe(
      data => {
        this.toastr.success('Usuario ha sido Eliminado correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.cargarUsuarios();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }

  activar(username:string){

  }
}
