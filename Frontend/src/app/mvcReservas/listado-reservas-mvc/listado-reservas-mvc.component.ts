import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { HabitacionConsulta } from 'src/app/entity/habitacionConsulta';
import { DetalleReservaService } from 'src/app/service/detalle-reserva.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-listado-reservas-mvc',
  templateUrl: './listado-reservas-mvc.component.html',
  styleUrls: ['./listado-reservas-mvc.component.css']
})
export class ListadoReservasMvcComponent implements OnInit {

  habitacion: string;
  habitaciones: string[] = [
    "Ashland", "Nico" , "Bella" , "Guille" , "Dolly"
  ];

  detalleReserva
  
  constructor(
    private tokenService: TokenService,
    private router: Router,
    private toastr: ToastrService,
    private detalleReservaService: DetalleReservaService
  ) { }

  ngOnInit(): void {
  }

  onRegister(): void {
   
  }
  Ashland() {
   
  }
  Nico() {
   
  }
  Bella() {
   
  }
  Guille() {
   
  }
  Dolly() {
   
  }

  ListarhistoricoHuespedesPorHabitacion(idHab:number, fecIni:Date, fecFin:Date){
    const habitacionConsulta = new HabitacionConsulta(idHab, fecIni, fecFin);

    this.detalleReservaService.historicoHuespedesPorHabitacion(idHab, habitacionConsulta).subscribe(
      data => {

      },
      err => {
        
      }
    )
  }
  
}
