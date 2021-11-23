import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DetalleReserva } from '../entity/detalleReserva';
import { HabitacionConsulta } from '../entity/habitacionConsulta';

@Injectable({
  providedIn: 'root'
})
export class DetalleReservaService {

  ENDPOINT = 'http://127.0.0.1:8080/api/consultas/habitacion/';

  constructor(private httpClient: HttpClient) { }

  public detalleReservaActivaPorHabitacion(id: number): Observable<DetalleReserva> {
    return this.httpClient.get<DetalleReserva>(this.ENDPOINT + `reserva-activa/${id}`);
  }

  public historicoHuespedesPorHabitacion(id: number, habitacionConsulta: HabitacionConsulta): Observable<DetalleReserva> {
    return this.httpClient.get<DetalleReserva>(this.ENDPOINT + `huespedes-fechas/${id}`);
  }

  
  

}
