import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { DetalleReserva } from 'src/app/entity/detalleReserva';
import { Cuarto } from 'src/app/entity/habitacion';
import { LoginUsuario } from 'src/app/entity/login-usuario';
import { CuartosService } from 'src/app/service/cuartos.service';
import { DetalleReservaService } from 'src/app/service/detalle-reserva.service';
import { ReservaService } from 'src/app/service/reserva.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-cuartos-editar',
  templateUrl: './cuartos-editar.component.html',
  styleUrls: ['./cuartos-editar.component.css']
})
export class CuartosEditarComponent implements OnInit {

  cuartos: Cuarto = null;
  detallereserva: DetalleReserva = null;
  usuarioLogeado: string = '';

  cuartoActual: Cuarto;

  constructor(
    private cuartoService: CuartosService,
    private actiavedRouter: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router,
    private detalleReservaService: DetalleReservaService,
    private reservaService: ReservaService,
    private tokenService: TokenService
  ) { }

  ngOnInit(): void {

    const id = this.actiavedRouter.snapshot.params.habitacionId;
    this.usuarioLogeado = this.tokenService.getUserName();

    this.detalleReservaService.detalleReservaActivaPorHabitacion(id).subscribe(
      data => {
        this.detallereserva = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
        this.router.navigate(['/cuartos']);
      }
    );
  }

  finalizarReserva(id: number): void {

    const loginUsuario = new LoginUsuario(this.usuarioLogeado, '')

    this.reservaService.finalizarReserva(id, loginUsuario).subscribe(
      data => {
        this.calcularTotalAPagar(this.detallereserva);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }

  calcularTotalAPagar(detalle) {
    var tiempo: number;
    this.cuartoService.detalle(detalle.habitacionId).subscribe(
      data => {
        this.cuartoActual = data;
        //calcular el total (precio * tiempo)
        var entro: Date = new Date(detalle.fechaIngreso);
        var sale: Date = new Date();
        var diferencia:number = (sale.getTime() - entro.getTime()) / (1000 * 60 * 60);
        var precioHora = this.cuartoActual.precio / 24;
        var totalACobrar = precioHora * diferencia
        this.toastr.success('Cobrar S/.' + totalACobrar.toFixed(2), 'OK', {
          timeOut: 13000, positionClass: 'toast-top-center',
        });
        this.router.navigate(['/cuartos']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }
}