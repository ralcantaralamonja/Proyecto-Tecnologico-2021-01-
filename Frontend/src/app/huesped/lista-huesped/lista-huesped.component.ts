import { Component, OnInit } from '@angular/core';
import { Huesped } from 'src/app/entity/huesped';
import { HuespedService } from 'src/app/service/huesped.service';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-lista-huesped',
  templateUrl: './lista-huesped.component.html',
  styleUrls: ['./lista-huesped.component.css']
})
export class ListaHuespedComponent implements OnInit {

  huesped: Huesped[] = [];
  roles: string[];
  isAdmin = false;

  constructor( 

    private huespedService: HuespedService,
    private toastr: ToastrService,
    private tokenService: TokenService

  ) { }

  ngOnInit(): void {

    this.cargarHuespedes();
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ROLE_ADMIN') {
        this.isAdmin = true;
      }
    });

  }

  cargarHuespedes(): void {
    this.huespedService.lista().subscribe(
      data => {
        this.huesped = data;
      },
      err => {
        console.log(err);
      }
    );
  }

  borrar(id: number) {
    this.huespedService.delete(id).subscribe(
      data => {
        this.toastr.success('Huesped ha sido Eliminado correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.cargarHuespedes();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }








}
