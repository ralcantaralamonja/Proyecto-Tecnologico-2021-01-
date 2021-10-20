import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginUsuario } from '../entity/login-usuario';
import { NuevoUsuario } from '../entity/nuevo-usuario';
import { Usuario } from '../entity/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  usuarioURL = 'http://127.0.0.1:8080/admin';
  apiReniec = 'https://apiperu.dev/api/dni/';

  contentType: string = 'application/json';
  token: string = 'Bearer 2ad4c7f85cc0c5b02da0b2084d7953401bd4c0ce2342e3e9451de55b7e540af8';

  constructor(private httpClient: HttpClient) { }

  public lista(): Observable<Usuario[]> {
    return this.httpClient.get<Usuario[]>(this.usuarioURL + 'users');
  }
  /*
    public detalle(id: string): Observable<Usuario> {
      return this.httpClient.get<Usuario>(this.usuarioURL + `/${id}`);
    }
  
    public save(usuario: Usuario): Observable<any> {
      return this.httpClient.post<any>(this.usuarioURL, usuario);
    }
  
    public update(id: string, usuario: Usuario): Observable<any> {
      return this.httpClient.put<any>(this.usuarioURL + `/${id}`, usuario);
    }
  */

  public bloquear(loginUsuario: LoginUsuario): Observable<any> {
    return this.httpClient.put<any>(this.usuarioURL + `/users/bloqueo`, loginUsuario);
  }

  public eliminar(id: string, loginUsuario: LoginUsuario): Observable<any> {
    return this.httpClient.put<any>(this.usuarioURL + `/users/delete/` + `${id}`, loginUsuario);
  }

  public nuevoUsuario(username: string, nuevoUsuario: Usuario): Observable<any> {
    return this.httpClient.post<any>(this.usuarioURL + '/users/registro-user/' + `${username}`, nuevoUsuario);
  }

  public nuevoManager(username: string, nuevoUsuario: Usuario): Observable<any> {
    return this.httpClient.post<any>(this.usuarioURL + '/users/registro-manager/' + `${username}`, nuevoUsuario);
  }

}
