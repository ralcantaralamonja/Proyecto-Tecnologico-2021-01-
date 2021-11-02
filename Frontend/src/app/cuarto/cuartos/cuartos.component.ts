import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Cuarto } from 'src/app/entity/habitacion';
import { CuartosService } from 'src/app/service/cuartos.service';
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

  constructor(

    private cuartoService: CuartosService,
    private toastr: ToastrService,
    private tokenService: TokenService


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
      },
      err => {
        console.log(err);
      }
    );
  }

}
