import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { NuevoUsuario } from '../entity/nuevo-usuario';
import { Usuario } from '../entity/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {


  usuarioURL = 'http://127.0.0.1:8080/api/usuarios';


  constructor(private httpClient: HttpClient) { }


  public lista(): Observable<Usuario[]> {
    return this.httpClient.get<Usuario[]>(this.usuarioURL);
  }

  public detalle(id: string): Observable<Usuario> {
    return this.httpClient.get<Usuario>(this.usuarioURL + `/${id}`);
  }

  public save(usuario: Usuario): Observable<any> {
    return this.httpClient.post<any>(this.usuarioURL, usuario);
  }

  public update(id: string, usuario: Usuario): Observable<any> {
    return this.httpClient.put<any>(this.usuarioURL + `/${id}`, usuario);
  }

  public delete(id: string): Observable<any> {
    return this.httpClient.delete<any>(this.usuarioURL + `/${id}`);
  }





}
