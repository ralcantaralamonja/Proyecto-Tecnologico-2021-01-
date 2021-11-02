import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cuarto } from '../entity/habitacion';

@Injectable({
  providedIn: 'root'
})
export class CuartosService {

  cuartoURL = 'http://127.0.0.1:8080/api/habitaciones';


  constructor(private httpClient: HttpClient) { }


  
  public lista(): Observable<Cuarto[]> {
    return this.httpClient.get<Cuarto[]>(this.cuartoURL);
  }

  //public detalle(id: number): Observable<Cuarto> {
   // return this.httpClient.get<Cuarto>(this.cuartoURL + `/${id}`);
  //}

 // public save(huesped: NuevoHuesped): Observable<any> {
   // return this.httpClient.post<any>(this.huespedURL, huesped);
  //}

  //public update(id: number, cuarto: Cuarto): Observable<any> {
   // return this.httpClient.put<any>(this.huespedURL + `/${id}`, huesped);
 // }

  //public delete(id: number): Observable<any> {
   // return this.httpClient.delete<any>(this.huespedURL + `/${id}`);
 // }
}
