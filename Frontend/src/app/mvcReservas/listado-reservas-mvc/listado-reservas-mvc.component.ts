import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
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



  constructor(
    private tokenService: TokenService,
    private router: Router,
    private toastr: ToastrService
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
}
