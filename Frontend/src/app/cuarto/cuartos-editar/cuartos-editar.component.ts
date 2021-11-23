import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { DetalleReserva } from 'src/app/entity/detalleReserva';
import { Cuarto } from 'src/app/entity/habitacion';
import { CuartosService } from 'src/app/service/cuartos.service';
import { DetalleReservaService } from 'src/app/service/detalle-reserva.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-cuartos-editar',
  templateUrl: './cuartos-editar.component.html',
  styleUrls: ['./cuartos-editar.component.css']
})
export class CuartosEditarComponent implements OnInit {

  cuartos: Cuarto = null;
  detallereserva: DetalleReserva = null;
  constructor(

    private cuartoService: CuartosService,
    private actiavedRouter: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router,
    private detalleReservaService: DetalleReservaService,


  ) { }

  ngOnInit(): void {

    const id = this.actiavedRouter.snapshot.params.habitacionId;
    this.detalleReservaService.detalleReservaActivaPorHabitacion(id).subscribe(
      data => {
        this.detallereserva = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/cuartos']);
      }
    );

  }

  onUpdate(): void{
    
    const id = this.actiavedRouter.snapshot.params.habitacionId;
    this.cuartoService.update(id, this.cuartos).subscribe(
      data => {
        this.toastr.success('Disponibilidad Actualizada', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/cuartos']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        }
      
    );
  }

}
