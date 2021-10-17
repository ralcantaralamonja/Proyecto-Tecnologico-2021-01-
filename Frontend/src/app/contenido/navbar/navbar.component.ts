import { Component, OnInit } from '@angular/core';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLogged = false;
  nombreUsuario = '';



  constructor(private tokenService: TokenService) { }

  ngOnInit(): void {

    if(this.tokenService.getToken()){
      this.isLogged = true
      this.nombreUsuario = this.tokenService.getUserName();

    }
    else{
      this.isLogged = false;
      this.nombreUsuario = '';

    }
  }

  onLogOut(): void{
    this.tokenService.logOut();
    window.location.reload();
  }

}
