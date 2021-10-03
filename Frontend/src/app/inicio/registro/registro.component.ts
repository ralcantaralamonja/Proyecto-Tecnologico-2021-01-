import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { NuevoUsuario } from 'src/app/entity/nuevo-usuario';
import { AuthService } from 'src/app/service/auth.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {

 
  nuevoUsuario: NuevoUsuario; 
  nombres: string;
  apellidos: string;
  correo: string;
  username: string;
  password: string;
  roles: string[] = [];
  errMsj: string;
  isLogged = false;

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {

    if(this.tokenService.getToken()){
      this.isLogged = true;
      this.roles = this.tokenService.getAuthorities();

    }
    
  }

  onRegister(): void{
    this.nuevoUsuario = new NuevoUsuario(this.nombres, this.apellidos,  this.username, this.correo,  this.password);
    this.authService.nuevo(this.nuevoUsuario).subscribe(
      data =>{
        this.toastr.success('Usuario creado', 'OK', {
          timeOut: 3000, positionClass:'toast-top-center'
        });

       
        this.router.navigate(['/login']);
      },
      err => {
       
        this.errMsj = err.error.mensaje;
        this.toastr.error(this.errMsj, 'Fail', {
          timeOut: 3000, positionClass:'toast-top-center'
        })
        console.log("error -> " + err.error.mensaje);
      }
    )
  }
}
