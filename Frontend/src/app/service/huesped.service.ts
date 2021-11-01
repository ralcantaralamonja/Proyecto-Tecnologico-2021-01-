import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Huesped } from '../entity/huesped';
import { NuevoHuesped } from '../entity/nuevoHuesped';
import { map } from 'rxjs/operators'
import { Persona, ResponseDni } from '../entity/responseDni';

@Injectable({
  providedIn: 'root'
})
export class HuespedService {

  huespedURL = 'http://127.0.0.1:8080/api/huespedes';
  apiReniec = 'https://apiperu.dev/api/dni/';

  constructor(private httpClient: HttpClient) { }

  public lista(): Observable<Huesped[]> {
    return this.httpClient.get<Huesped[]>(this.huespedURL);
  }

  public detalle(id: number): Observable<Huesped> {
    return this.httpClient.get<Huesped>(this.huespedURL + `/${id}`);
  }

  public save(huesped: NuevoHuesped): Observable<any> {
    return this.httpClient.post<any>(this.huespedURL, huesped);
  }

  public update(id: number, huesped: Huesped): Observable<any> {
    return this.httpClient.put<any>(this.huespedURL + `/${id}`, huesped);
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.huespedURL + `/${id}`);
  }

  public consultarDni(dni: string): Observable<any> {       
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer 2ad4c7f85cc0c5b02da0b2084d7953401bd4c0ce2342e3e9451de55b7e540af8'
    })
    return this.httpClient.get<ResponseDni>(this.apiReniec + `${dni}`,{headers});
  }
}