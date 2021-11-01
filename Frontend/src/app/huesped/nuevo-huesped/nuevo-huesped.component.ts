import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { NuevoHuesped } from 'src/app/entity/nuevoHuesped';
import { Persona, ResponseDni } from 'src/app/entity/responseDni';
import { HuespedService } from 'src/app/service/huesped.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-nuevo-huesped',
  templateUrl: './nuevo-huesped.component.html',
  styleUrls: ['./nuevo-huesped.component.css']
})
export class NuevoHuespedComponent implements OnInit {

  nombre = '';
  apellido = '';
  telefono = '';
  correo = '';
  usuarioRegistro = this.tokenService.getUserName();
  observaciones = '';
  tipoDocumento = 'DNI';
  numeroDocumento = '';
  
  nombre_completo = '';
  responseDni: ResponseDni;
  persona: Persona;

  constructor(
    private httpClient: HttpClient,
    private huespedService: HuespedService,
    private toastr: ToastrService,
    private router: Router,
    private tokenService: TokenService
  ) { }

  ngOnInit(): void {
  }

  crearHuesped() {
    const huesped = new NuevoHuesped(this.nombre, this.apellido, this.telefono, this.correo, this.usuarioRegistro, this.observaciones, this.tipoDocumento, this.numeroDocumento);
    this.huespedService.save(huesped).subscribe(
      data => {
        this.toastr.success('Huesped Creado correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/lista-huesped']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }

  consultarDni(dni: string) {
    this.huespedService.consultarDni(dni).subscribe(
      data => {
        this.responseDni = data;
        this.persona = this.responseDni.data;
        this.nombre = this.persona.nombres;
        this.apellido = this.persona.apellido_paterno + ' ' + this.persona.apellido_materno;
      },
      err => {
        console.log('errooooor que menso ' + err);
      }
    )
  }
}
