import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  roles: string[];
  isAdmin = false;
  isAdminPermiso = true;
  isUserPermiso = true;

  constructor(
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { 
  }

  ngOnInit(): void {
    this.PermisioAdmin();
    this.PermisoUser();
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ADMIN') {
        this.isAdmin = true;
      }
    });
  }

  PermisioAdmin(){
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ADMIN') {
        this.isAdminPermiso = false;
      }
    });
  }

  PermisoUser(){
    
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'MANAGER') {
        this.isUserPermiso = true;
      }
      else if(rol === 'USER'){
        this.isUserPermiso = false;
      }
    });
  }

}
