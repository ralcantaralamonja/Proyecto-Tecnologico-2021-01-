import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DetalleReserva } from '../entity/detalleReserva';
import { Cuarto } from '../entity/habitacion';

@Injectable({
  providedIn: 'root'
})
export class CuartosService {

  cuartoURL = 'http://127.0.0.1:8080/api/habitaciones';
  consultaURL = 'http://127.0.0.1:8080/api/consultas';

  constructor(private httpClient: HttpClient) { }


  
  public lista(): Observable<Cuarto[]> {
    return this.httpClient.get<Cuarto[]>(this.cuartoURL);
  }
  
  public listarDisponibles(): Observable<Cuarto[]> {
    return this.httpClient.get<Cuarto[]>(this.cuartoURL+'/disponibles');
  }

  public detalle(id: number): Observable<Cuarto> {
    return this.httpClient.get<Cuarto>(this.cuartoURL + `/${id}`);
  }

  public save(cuarto: Cuarto): Observable<any> {
    return this.httpClient.post<any>(this.cuartoURL, cuarto);
  }

  public update(id: number, cuarto: Cuarto): Observable<any> {
    return this.httpClient.put<any>(this.cuartoURL + `/${id}`, cuarto);
   }

   public setDisponible(id: number): Observable<any> {
    return this.httpClient.put<any>(this.cuartoURL + `/mantenimiento/${id}`, null);
   }
   
  public habitacionesConOcupacion(detalleReserva: DetalleReserva): Observable<any> {
    return this.httpClient.post<Cuarto>(this.cuartoURL+ `/habitaciones/ocupabilidad`, detalleReserva);
  }
}
