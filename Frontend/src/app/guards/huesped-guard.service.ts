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
    const expectedRol = route.data.expectedRol;
    const roles = this.tokenService.getAuthorities();
    this.realRole = 'USER';
    roles.forEach(rol =>{
      if (rol === 'ROLE_ADMIN') {
        this.realRole = 'ADMIN';
      }
    });
    if (!this.tokenService.getToken() ) {
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }
}
