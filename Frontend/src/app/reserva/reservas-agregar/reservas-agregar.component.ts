import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Cuarto } from 'src/app/entity/habitacion';
import { Huesped } from 'src/app/entity/huesped';
import { Reserva } from 'src/app/entity/reserva';
import { CuartosService } from 'src/app/service/cuartos.service';
import { HuespedService } from 'src/app/service/huesped.service';
import { ReservaService } from 'src/app/service/reserva.service';

@Component({
  selector: 'app-reservas-agregar',
  templateUrl: './reservas-agregar.component.html',
  styleUrls: ['./reservas-agregar.component.css']
})
export class ReservasAgregarComponent implements OnInit {


  fecSalida: '';
  huespedId: '';
  habitacionId: '';
  usuarioRegistro: '';

  habitacion: Cuarto;
  habitaciones: Cuarto[] = [];

  huesped: Huesped;
  huespedes: Huesped[] = [];

  constructor(
    private reservaService: ReservaService,
    private cuartoService: CuartosService,
    private huespedService: HuespedService,
    private toastr: ToastrService,
    private router: Router

  ) { }

  ngOnInit(): void {
    this.cargarHuespedes();
    this.cargarHabitaciones();
  }

  /*onCreate(): void {
    const reserva = new Reserva(this.nombre, this.precio);
    this.reservaService.save(producto).subscribe(
      data => {
        this.toastr.success('Producto Creado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/lista']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/']);
      }
    );
  }*/

  cargarHabitaciones(){
    this.cuartoService.listarDisponibles().subscribe(
      data => {
        this.habitaciones = data;
      },
      err => {
        console.log(err);
      }
    )
  }

  //TODO
  // cambiar por metodo listarDisponibles() !!
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
