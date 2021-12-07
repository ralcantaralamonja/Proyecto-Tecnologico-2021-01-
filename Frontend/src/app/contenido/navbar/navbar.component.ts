import { Component, OnInit } from '@angular/core';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLogged = true;
  isAdmin = false;
  nombreUsuario = '';
  roles: string[];

  constructor(
    
    private tokenService: TokenService,
    
    
    ) { }

  ngOnInit(): void {
    this.PermisosAdmin();
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

  onLogOut(): void{
    this.tokenService.logOut();
    window.location.reload();
  }

}
