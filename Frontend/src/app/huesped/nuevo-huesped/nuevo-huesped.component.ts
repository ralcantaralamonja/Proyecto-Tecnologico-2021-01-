import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { NuevoHuesped } from 'src/app/entity/nuevoHuesped';
import { Persona } from 'src/app/entity/persona';
import { HuespedService } from 'src/app/service/huesped.service';
import { TokenService } from 'src/app/service/token.service';

declare const validate_text: any;
declare const select_area: any;

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
  persona: Persona;

  constructor(
    private httpClient: HttpClient,
    private huespedService: HuespedService,
    private toastr: ToastrService,
    private router: Router,
    private tokenService: TokenService
  ) { }

  onClick(boton: string) {
    if (boton === "crear") {
      this.crearHuesped()
    } else {
      this.consultarDni(boton)
    }
  }

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
        this.persona = data;
        this.nombre = this.persona.nombres;
        this.apellido = this.persona.apellido_paterno + ' ' + this.persona.apellido_materno;
      },
      err => {
        console.log('errooooor que menso ' + err);
      }
    )
  }
}
