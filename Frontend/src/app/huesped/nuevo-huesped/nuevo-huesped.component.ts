import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { NuevoHuesped } from 'src/app/entity/nuevoHuesped';
import { HuespedService } from 'src/app/service/huesped.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-nuevo-huesped',
  templateUrl: './nuevo-huesped.component.html',
  styleUrls: ['./nuevo-huesped.component.css']
})
export class NuevoHuespedComponent implements OnInit {

 /* nombre = '';
  apellido = '';
  telefono = '';
  correo = '';
  usuarioRegistro = this.tokenService.getUserName();
  observaciones = ''
*/
    numero   = '';
    nombre_completo = this.tokenService.getUserName();
    
  

  constructor(
    private httpClient: HttpClient,
    private huespedService: HuespedService,
    private toastr: ToastrService,
    private router: Router,
    private tokenService: TokenService

  ) { }

  
  

  ngOnInit(): void {
  }

  onCreate(): void {
    const huesped = new NuevoHuesped(/*this.numero, this.data.nombre_completo*/ );
    this.huespedService.save(huesped).subscribe(
      data => {
        this.toastr.success('Huesped Creado correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/lista-huesped']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
      }
    );
  }


}
