import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Cuarto } from 'src/app/entity/habitacion';
import { CuartosService } from 'src/app/service/cuartos.service';
import { DetalleReservaService } from 'src/app/service/detalle-reserva.service';
import { ReservaService } from 'src/app/service/reserva.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-cuartos',
  templateUrl: './cuartos.component.html',
  styleUrls: ['./cuartos.component.css']
})
export class CuartosComponent implements OnInit {

  isLogged = false;
  isLoginFail = false;
  cuarto: Cuarto[] = [];
  roles: string[];
  isUser = false;
  isAdmin = true;
  errMsj: string;
  clases = [
    'btn', 'btn btn-success', 'btn btn-danger', 'btn btn-warning'
  ]

  constructor(
    private cuartoService: CuartosService,
    private detalleReservaService: DetalleReservaService,
    private reservaService: ReservaService,
    private toastr: ToastrService,
    private tokenService: TokenService,
    private router: Router,
  ) { }

  ngOnInit(): void {

    this.cargarCuartos();
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ADMIN') {
        this.isAdmin = false;
      }
    });
  }

  cargarCuartos(): void {
    this.cuartoService.lista().subscribe(
      data => {
        this.cuarto = data;
        this.cuarto.forEach(
          c => {
            c.color = c.estado;
            c.estado = this.validarEstado(c.estado);
            c.banio = this.validarBanio(c.banio);
            this.reservaService.ultimaReservaPendiente(c.habitacionId).subscribe(
              data => {
                (data != null) ? c.pendientes = 'Reservada para el ' + data.fecIngreso.toString().substring(0, 10) + ' a las ' + data.fecIngreso.toString().substring(11, 16)
                  : c.pendientes = '';
              }
            )
          }
        )
      },
      err => {
        console.log('error al cargar: ' + err);
      }
    );
  }

  setDisponible(id: number, estado: string) {
    if (estado != 'Disponible') {
      this.cuartoService.setDisponible(id).subscribe(
        data => {
          this.toastr.success(data.mensaje, 'OK', {
            timeOut: 3000, positionClass: 'toast-top-center'
          });
          this.cargarCuartos();
        },
        err => {
          this.toastr.error(err.error.mensaje, 'Fail', {
            timeOut: 3000, positionClass: 'toast-top-center',
          });
        }
      )
    }
  }

  verDetalleReservaActiva(id: number) {
    this.detalleReservaService.detalleReservaActivaPorHabitacion(id).subscribe(
      data => {
        this.router.navigate(['/editarcuarto/' + id]);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        })
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

  validarBanio(estado: number): string {
    switch (estado) {
      case 1:
        return "Con baño";
        break;
      case 2:
        return "Baño compartido";
        break;
      default:
        return "Vaya a alquilar al chifa";
        break;
    }
  }

}
