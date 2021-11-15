import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Huesped } from 'src/app/entity/huesped';
import { HuespedService } from 'src/app/service/huesped.service';

@Component({
  selector: 'app-detalle-huesped',
  templateUrl: './detalle-huesped.component.html',
  styleUrls: ['./detalle-huesped.component.css']
})
export class DetalleHuespedComponent implements OnInit {

  huesped: Huesped = null;

  constructor(

    private huespedService: HuespedService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router

  ) { }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.params.huespedId;
    this.huespedService.detalle(id).subscribe(
      data =>{
        this.huesped = data;
        this.huesped.estado = this.validarEstado(this.huesped.estado);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail',{
          timeOut: 3000, positionClass: 'toast-top-center',
        });
        this.volver();
      }
    );
  }

  volver(): void{
    this.router.navigate(['/lista-huesped'])
  }

  validarEstado(estado: number): string {
    switch (estado) {
      case 1:
        return "Activo";
        break;
      case 2:
        return "Inactivo";
        break;
      default:
        return "No definido";
        break;
    }
  }


}
