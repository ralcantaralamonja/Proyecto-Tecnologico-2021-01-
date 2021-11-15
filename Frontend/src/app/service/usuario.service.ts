import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { LoginUsuario } from '../entity/login-usuario';
import { NuevoUsuario } from '../entity/nuevo-usuario';
import { Usuario } from '../entity/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  usuarioURL = 'http://127.0.0.1:8080/admin/';

  constructor(private httpClient: HttpClient) { }

  public lista(): Observable<Usuario[]> {
    return this.httpClient.get<Usuario[]>(this.usuarioURL + 'users');
  }

  public bloquear(loginUsuario: LoginUsuario): Observable<any> {
    return this.httpClient.put<any>(this.usuarioURL + `users/bloqueo`, loginUsuario);
  }

  public eliminar(id: string, loginUsuario: LoginUsuario): Observable<any> {
    return this.httpClient.put<any>(this.usuarioURL + `users/delete/` + `${id}`, loginUsuario);
  }

  public nuevoUsuario(username: string, nuevoUsuario: NuevoUsuario): Observable<any> {
    return this.httpClient.post<any>(this.usuarioURL + 'users/registro-user/' + `${username}`, nuevoUsuario);
  }

  public nuevoManager(username: string, nuevoUsuario: NuevoUsuario): Observable<any> {
    return this.httpClient.post<any>(this.usuarioURL + 'users/registro-manager/' + `${username}`, nuevoUsuario);
  }
}