import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Huesped } from 'src/app/entity/huesped';
import { Persona } from 'src/app/entity/persona';
import { HuespedService } from 'src/app/service/huesped.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-editar-huesped',
  templateUrl: './editar-huesped.component.html',
  styleUrls: ['./editar-huesped.component.css']
})
export class EditarHuespedComponent implements OnInit {

  huespedId: number;
  nombre = '';
  apellido = '';
  telefono = '';
  correo = '';
  observaciones = '';
  tipoDocumento = 'DNI';
  numeroDocumento = '';

  nombre_completo = '';
  persona: Persona;

  huesped: Huesped = null;
  usuarioUltModificacion: string = '';

  constructor(
    private huespedService: HuespedService,
    private actiavedRouter: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router,
    private tokenService: TokenService

  ) { }

  ngOnInit(): void {
    const id = this.actiavedRouter.snapshot.params.huespedId;
    this.huespedService.detalle(id).subscribe(
      data => {
        this.huesped = data;
        this.huesped.huespedId = id
        console.log(data);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
        this.router.navigate(['/']);
      }
    );
  }
  //    this.huesped.usuarioUltModificacion = this.tokenService.getUserName();

  clickButtonForm(boton: string) {
    if (boton === "editar") {
      this.onUpdate()
    } else {
      this.consultarDni(boton)
    }
  }

  onUpdate(): void {
    const id = this.actiavedRouter.snapshot.params.huespedId;
    this.huesped.usuarioUltModificacion = this.tokenService.getUserName();
    this.huespedService.update(id, this.huesped).subscribe(
      data => {
        this.toastr.success('Huesped Actualizado Correctamente', 'OK', {
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
        this.huesped.nombre = this.persona.nombres;
        this.huesped.apellido = this.persona.apellido_paterno + ' ' + this.persona.apellido_materno;
        console.log(data);        
      },
      err => {
        console.log('errooooor que menso ' + err);
      }
    )
  }
}
