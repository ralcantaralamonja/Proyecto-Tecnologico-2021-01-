import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginUsuario } from '../entity/login-usuario';
import { Reserva } from '../entity/reserva';

@Injectable({
  providedIn: 'root'
})
export class ReservaService {

  reservaURL = 'http://127.0.0.1:8080/api/reservas';


  constructor(private httpClient: HttpClient) { }

  public lista(): Observable<Reserva[]> {
    return this.httpClient.get<Reserva[]>(this.reservaURL);
  }

  public detalle(id: number): Observable<Reserva> {
    return this.httpClient.get<Reserva>(this.reservaURL + `/${id}`);
  }

  public save(reserva: Reserva): Observable<any> {
    return this.httpClient.post<any>(this.reservaURL, reserva);
  }

  public finalizarReserva(id: number, usuario:LoginUsuario): Observable<any> {
    return this.httpClient.put<any>(this.reservaURL+`/final/${id}`, usuario);
  }

  public cancelarReserva(id: number, usuario:LoginUsuario): Observable<any> {
    return this.httpClient.put<any>(this.reservaURL+`/cancelar/${id}`, usuario);
  }

}
