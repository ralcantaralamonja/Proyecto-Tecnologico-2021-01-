import { Component, OnInit } from '@angular/core';
import { LoginUsuario } from 'src/app/entity/login-usuario';
import { Reserva } from 'src/app/entity/reserva';
import { ReservaService } from 'src/app/service/reserva.service';
import { TokenService } from 'src/app/service/token.service';
import { ToastrService } from 'ngx-toastr';
import { Cuarto } from 'src/app/entity/habitacion';
import { Huesped } from 'src/app/entity/huesped';
import { CuartosService } from 'src/app/service/cuartos.service';
import { HuespedService } from 'src/app/service/huesped.service';

@Component({
  selector: 'app-reservas',
  templateUrl: './reservas.component.html',
  styleUrls: ['./reservas.component.css']
})
export class ReservasComponent implements OnInit {
  errMsj: string;
  isLogged = false;
  isLoginFail = false;
  reservas: Reserva[];
  
  habitacion: Cuarto;
  habitaciones: Cuarto[] = [];

  huesped: Huesped;
  huespedes: Huesped[] = [];

  roles: string[];
  permiso = false;
  reservaFilter: any = { estado : '' };
  usuarioLogeado: string;

  constructor(
    private tokenService: TokenService,
    private reservaService: ReservaService,
    private toastr: ToastrService,
    private cuartoService: CuartosService,
    private huespedService: HuespedService,

  ) { }

  ngOnInit(): void {
    this.cargarReservas();
    this.cargarHuespedes();
    this.cargarHabitaciones();
    this.roles = this.tokenService.getAuthorities();
    this.usuarioLogeado = this.tokenService.getUserName();
    this.roles.forEach(rol => {
      if (rol === 'MANAGER') {
        this.permiso = true;
      }
    });
  }

  cargarReservas(){
    this.reservaService.lista().subscribe(
      data => {
        this.reservas = data;
        this.reservas.forEach(
          r => r.estado = this.validarEstado(r.estado)
        )
      },
      err => {
        console.log(err);
      }
    );
  }

  cargarHabitaciones(){
    this.cuartoService.lista().subscribe(
      data => {
        this.habitaciones = data;
      },
      err => {
        console.log(err);
      }
    )
  }

  cargarHuespedes(){
    this.huespedService.lista().subscribe(
      data => {
        this.huespedes = data;
        console.log(this.huespedes);        
      },
      err => {
        console.log(err);
      }
    )
  }

  cancelar(id: number) {
    const user = new LoginUsuario(this.usuarioLogeado, '');

    this.reservaService.cancelarReserva(id, user).subscribe(
      data => {
        this.toastr.success('Ha cancelado la reserva correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.cargarReservas();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }



  validarEstado(estado: number): string {
    switch (estado) {
      case 0:
        return "Pendiente";
        break;
        case 1:
          return "Activa";
          break;
      case 2:
        return "Finalizada";
        break;
      case 3:
        return "Cancelada";
        break;
      default:
        return "Grillos";
        break;
    }
  }

}
