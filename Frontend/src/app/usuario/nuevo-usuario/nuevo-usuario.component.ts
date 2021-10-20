import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { NuevoUsuario } from 'src/app/entity/nuevo-usuario';
import { Usuario } from 'src/app/entity/usuario';
import { HuespedService } from 'src/app/service/huesped.service';
import { TokenService } from 'src/app/service/token.service';
import { UsuarioService } from 'src/app/service/usuario.service';

@Component({
  selector: 'app-nuevo-usuario',
  templateUrl: './nuevo-usuario.component.html',
  styleUrls: ['./nuevo-usuario.component.css']
})
export class NuevoUsuarioComponent implements OnInit {


  
  nombre = '';
  apellido = '';
  username = '';
  password = '';
  correo = '';
  estado = '';
  rol = '';
  usuarioRegistro = this.tokenService.getUserName();
  


  constructor(

    private httpClient: HttpClient,
    private usuarioService: UsuarioService,
    private toastr: ToastrService,
    private router: Router,
    private tokenService: TokenService

  ) { }

  ngOnInit(): void {
  }
  /*onCreate(): void {
    const username = new Usuario(this.nombre, this.apellido, this.username, this.password, this.correo, this.estado, this.rol );
    this.usuarioService.nuevoUsuario(username, nuevoUsuario).subscribe(
      data => {
        this.toastr.success('Huesped Creado correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/lista-huesped']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
      }
    );
  }*/
}
