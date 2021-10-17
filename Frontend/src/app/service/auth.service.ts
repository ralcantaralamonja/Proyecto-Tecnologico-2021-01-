import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtDto } from '../entity/jwl-dto';
import { LoginUsuario } from '../entity/login-usuario';
import { NuevoUsuario } from '../entity/nuevo-usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  //authURL = 'http://192.168.1.254:8080/admin/';
  authURL = 'http://192.168.18.75:8080/admin/';

  constructor(private httpClient: HttpClient) { }

  public nuevo(nuevoUsuario: NuevoUsuario): Observable<any>{
    return this.httpClient.post<any>(this.authURL + 'registro', nuevoUsuario);
  }
  public login(loginUsuario: LoginUsuario): Observable<JwtDto>{
    return this.httpClient.post<JwtDto>(this.authURL + 'login', loginUsuario);    
  }
}
