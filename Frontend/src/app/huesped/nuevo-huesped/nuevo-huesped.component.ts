import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Huesped } from 'src/app/entity/huesped';
import { HuespedService } from 'src/app/service/huesped.service';

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
  usuarioRegistro = '';
  usuarioUltModificacion = '';
  observaciones = ''


  constructor(

    private huespedService: HuespedService,
    private toastr: ToastrService,
    private router: Router

  ) { }

  ngOnInit(): void {
  }

  onCreate(): void {
    const huesped = new Huesped(this.nombre, this.apellido, this.telefono, this.correo, this.usuarioRegistro, this.usuarioUltModificacion, this.observaciones);
    this.huespedService.save(huesped).subscribe(
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
  }


}
