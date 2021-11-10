import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Reserva } from 'src/app/entity/reserva';
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


  constructor(

    private reservaService: ReservaService,
   /* private toastr: ToastrServicece,*/
    private router: Router

  ) { }

  ngOnInit(): void {
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

}
