import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { TokenService } from '../service/token.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuardService implements CanActivate{

  realRole: string;



  constructor(

    private tokenService: TokenService,
    private router: Router,

  ) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const expectedRol = route.data.expectRol;
    const roles = this.tokenService.getAuthorities();
    this.realRole = 'ADMIN';
    roles.forEach(rol =>{
      if (rol === 'MANAGER') {
        this.realRole = 'MANAGER';
      }
      if (rol === 'USER') {
        this.realRole = 'USER';
      }
    });
    console.log(this.tokenService.getToken());
    
    if (!this.tokenService.getToken()  || expectedRol.indexOf(this.realRole) === -1) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
