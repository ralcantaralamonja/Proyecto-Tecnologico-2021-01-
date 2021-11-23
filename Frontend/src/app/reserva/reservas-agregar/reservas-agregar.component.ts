import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Cuarto } from 'src/app/entity/habitacion';
import { Huesped } from 'src/app/entity/huesped';
import { Reserva } from 'src/app/entity/reserva';
import { CuartosService } from 'src/app/service/cuartos.service';
import { HuespedService } from 'src/app/service/huesped.service';
import { ReservaService } from 'src/app/service/reserva.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-reservas-agregar',
  templateUrl: './reservas-agregar.component.html',
  styleUrls: ['./reservas-agregar.component.css']
})
export class ReservasAgregarComponent implements OnInit {

  fecIngreso:Date;
  fecSalida:Date;
  huespedId:number;
  habitacionId:number;
  usuarioRegistro= '';

  habitacion: Cuarto;
  habitaciones: Cuarto[] = [];

  huesped: Huesped;
  huespedes: Huesped[] = [];

  constructor(
    private reservaService: ReservaService,
    private cuartoService: CuartosService,
    private huespedService: HuespedService,
    private tokenService: TokenService,
    private toastr: ToastrService,
    private router: Router

  ) { }

  ngOnInit(): void {
    this.cargarHuespedes();
    this.cargarHabitaciones();
    this.usuarioRegistro=this.tokenService.getUserName();
  }

  onCreate(): void {
    console.log("huespedId: "+this.huespedId);
    console.log("habId: "+this.habitacionId);
    
    const reserva = new Reserva(this.fecIngreso, this.fecSalida, this.huespedId, this.habitacionId, this.usuarioRegistro);
    this.reservaService.save(reserva).subscribe(
      data => {
        this.toastr.success(data.mensaje, 'OK', {
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

}
