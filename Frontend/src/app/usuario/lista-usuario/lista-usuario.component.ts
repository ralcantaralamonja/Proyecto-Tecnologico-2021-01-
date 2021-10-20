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

  nuevoUsuario : NuevoUsuario[] = [];
  roles:string[];
  isAdmin = false;
  loginUsuario: LoginUsuario;

  constructor(
    private usuarioService: UsuarioService,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { }

  ngOnInit(): void {

    //this.loginUsuario.username = this.tokenService.getUserName();
    //this.loginUsuario.password = 'ricardoinfiel';
    this.cargarUsuarios();
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ADMIN') {
        this.isAdmin = true;
      }
    });

  }

  cargarUsuarios(): void {
    this.usuarioService.lista().subscribe(
      data => {
        this.nuevoUsuario = data;
      },
      err => {
        console.log(err);
      }
    );
  }

  borrar(id: string) {
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
}
