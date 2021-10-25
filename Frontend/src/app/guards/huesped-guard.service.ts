import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { TokenService } from '../service/token.service';

@Injectable({
  providedIn: 'root'
})
export class HuespedGuardService implements CanActivate{
  
  realRole: string;

  constructor(
    private tokenService: TokenService,
    private router: Router

  ) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {    
    const expectedRol = route.data.expectRol;
    const roles = this.tokenService.getAuthorities();
    this.realRole = 'USER';
    roles.forEach(rol =>{
      if (rol === 'MANAGER') {
        this.realRole = 'MANAGER';
      }
      if (rol === 'ADMIN') {
        this.realRole = 'ADMIN';
      }
    });
    console.log(this.tokenService.getToken());
    
    if (!this.tokenService.getToken()  || expectedRol.indexOf(this.realRole) === -1) {
      this.router.navigate(['inicio']);
      return false;
    }
    return true;
  }
}
