import { Component, OnInit } from '@angular/core';
import { Reserva } from 'src/app/entity/reserva';
import { ReservaService } from 'src/app/service/reserva.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-reservas',
  templateUrl: './reservas.component.html',
  styleUrls: ['./reservas.component.css']
})
export class ReservasComponent implements OnInit {
  reservas: Reserva[];
  roles: string[];
  permiso = false;
  
  constructor(
    private tokenService: TokenService,
    private reservaService: ReservaService
  ) { }

  ngOnInit(): void {
    this.cargarReservas();
    this.roles = this.tokenService.getAuthorities();
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
