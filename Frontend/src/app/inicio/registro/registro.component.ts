import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NuevoUsuario } from 'src/app/entity/nuevo-usuario';
import { AuthService } from 'src/app/service/auth.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {

  isRegister = false;
  isRegisterFail = false;
  nuevoUsuario: NuevoUsuario; 
  nombre: string;
  apellido: string;
  email: string;
  nombreUsuario: string;
  password: string;
  roles: string[] = [];
  errMsj: string;
  isLogged = false;

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {

    if(this.tokenService.getToken()){
      this.isLogged = true;
      this.roles = this.tokenService.getAuthorities();

    }
    
  }

  onRegister(): void{
    this.nuevoUsuario = new NuevoUsuario(this.nombre, this.apellido,  this.nombreUsuario, this.email,  this.password);
    this.authService.nuevo(this.nuevoUsuario).subscribe(
      data =>{
        this.isRegister = true; 
        this.isRegisterFail = false;

       
        this.router.navigate(['/login']);
      },
      err => {
        this.isRegister = false;
        this.isRegisterFail = true;
        this.errMsj = err.error.mensaje;
        console.log("error -> " + err.error.mensaje);
      }
    )
  }
}
