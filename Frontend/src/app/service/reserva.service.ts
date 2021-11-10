import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reserva } from '../entity/reserva';

@Injectable({
  providedIn: 'root'
})
export class ReservaService {

  huespedURL = 'http://127.0.0.1:8080/api/reservas';


  constructor(private httpClient: HttpClient) { }

  public lista(): Observable<Reserva[]> {
    return this.httpClient.get<Reserva[]>(this.huespedURL);
  }

  public detalle(id: number): Observable<Reserva> {
    return this.httpClient.get<Reserva>(this.huespedURL + `/${id}`);
  }

  public save(reserva: Reserva): Observable<any> {
    return this.httpClient.post<any>(this.huespedURL, reserva);
  }

  public update(id: number, reserva: Reserva): Observable<any> {
   return this.httpClient.put<any>(this.huespedURL + `/${id}`, reserva);
  }
}
