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
  permiso = false;
  usuarioLogeado: string;
  isAdmin = false;

  constructor(
    private usuarioService: UsuarioService,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { }

  ngOnInit(): void {
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'MANAGER') {
        this.permiso = true;
      }
      if (rol === 'ADMIN') {
        this.isAdmin = true;
      }
      this.usuarioLogeado = this.tokenService.getUserName();
      console.log('oninit ' + this.usuarioLogeado);
      console.log(this.roles);
      
    });
    this.cargarUsuarios();
  }

  cargarUsuarios(): void {
    if (this.isAdmin) {
      this.usuarioService.listaParaElAdmin().subscribe(
        data => {
          this.usuarios = data;
          this.usuarios.forEach(
            u => u.estado = this.validarEstado(u.estado)
          )
          console.log(this.usuarios);
        },
        err => {
          console.log(err);
        }
      );
    }else{
      this.usuarioService.listaParaLaPlebe().subscribe(
        data => {
          this.usuarios = data;
          this.usuarios.forEach(
            u => u.estado = this.validarEstado(u.estado)
          )
          console.log(this.usuarios);
        },
        err => {
          console.log(err);
        }
      );
    }
  }

  borrar(username: string) {
    console.log('borrador ' + username);
    console.log('borrado ' + this.usuarioLogeado);
    const user = new LoginUsuario(this.usuarioLogeado, '');

    this.usuarioService.eliminar(username, user).subscribe(
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

  activar(username: string) {
    const user = new LoginUsuario(this.usuarioLogeado, '');

    this.usuarioService.activar(username, user).subscribe(
      data => {
        this.toastr.success('Usuario activado correctamente', 'OK', {
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

  validarEstado(estado): string {
    switch (estado) {
      case 1:
        return "Activo";
        break;
      case 2:
        return "Inactivo";
        break;
      case 3:
        return "Eliminado";
        break;
      default:
        return "Desconocido";
        break;
    }
  }

}
