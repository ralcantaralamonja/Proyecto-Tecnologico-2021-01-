import { Component, OnInit } from '@angular/core';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLogged = true;
  isAdmin = true;
  nombreUsuario = '';
  roles: string[];
  isUserPermiso = true;


  constructor(
    
    private tokenService: TokenService,
    
    
    ) { }

  ngOnInit(): void {


    this.PermisosAdmin();
    this.PermisoUser();

    if(this.tokenService.getToken()){
      this.isLogged = true
      this.nombreUsuario = this.tokenService.getUserName();
      
    }
    
    else{
      this.isLogged = false;
      this.nombreUsuario = '';

    }
  }

  PermisosAdmin(){
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ADMIN') {
        this.isAdmin = false;
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
  

  onLogOut(): void{
    this.tokenService.logOut();
    window.location.reload();
  }

}
