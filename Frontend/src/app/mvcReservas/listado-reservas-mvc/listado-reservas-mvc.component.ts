import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { HabitacionConsulta } from 'src/app/entity/habitacionConsulta';
import { DetalleReservaService } from 'src/app/service/detalle-reserva.service';
import { TokenService } from 'src/app/service/token.service';
import { DetalleReserva } from 'src/app/entity/detalleReserva';
import { Cuarto } from 'src/app/entity/habitacion';
import { CuartosService } from 'src/app/service/cuartos.service';

@Component({
  selector: 'app-listado-reservas-mvc',
  templateUrl: './listado-reservas-mvc.component.html',
  styleUrls: ['./listado-reservas-mvc.component.css']
})
export class ListadoReservasMvcComponent implements OnInit {

  habitacionConsulta: HabitacionConsulta;
  detalleReserva: DetalleReserva[] = [];

  habitacion: Cuarto;
  habitaciones: Cuarto[] = [];
  habitacionId: number;

  cuarto: Cuarto[] = [];
  fecIni: Date;
  fecFin: Date;

  constructor(
    private tokenService: TokenService,
    private router: Router,
    private toastr: ToastrService,
    private detalleReservaService: DetalleReservaService,
    private cuartoService: CuartosService
  ) { }

  ngOnInit(): void {
    this.cargarHabitaciones();
    this.validarEstado(1);
  }

  onRegister(): void {
    console.log("hab: " + this.habitacionId);
    console.log("fecini: " + this.fecIni);
    console.log("fecfin: " + this.fecFin);

    this.listarhistoricoHuespedesPorHabitacion(this.habitacionId, this.fecIni, this.fecFin);
  }

  listarhistoricoHuespedesPorHabitacion(idHab: number, fecIni: Date, fecFin: Date) {
    const habitacionConsulta = new HabitacionConsulta(idHab, fecIni, fecFin);

    this.detalleReservaService.historicoHuespedesPorHabitacion(idHab, habitacionConsulta).subscribe(
      data => {
        this.detalleReserva = data;
        this.detalleReserva.forEach(
          dr => dr.estadoReserva = this.validarEstado(dr.estadoReserva)
        )
      },
      err => {
        console.log("Ricardo gordito chipi");
      }
    )
  }

  cargarHabitaciones() {
    this.cuartoService.lista().subscribe(
      data => {
        this.habitaciones = data;
      },
      err => {
        console.log(err);
      }
    )
  }

  validarEstado(estado: number): string {
    switch (estado) {
      case 1:
        return "Disponible";
        break;
      case 2:
        return "Ocupado";
        break;
      case 3:
        return "Mantenimiento";
        break;
      default:
        return "Quien sabe";
        break;
    }
  }
}
