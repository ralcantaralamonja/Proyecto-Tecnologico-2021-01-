import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Huesped } from '../entity/huesped';
import { NuevoHuesped } from '../entity/nuevoHuesped';

@Injectable({
  providedIn: 'root'
})
export class HuespedService {

  //huespedURL = 'http://192.168.1.254:8080/api/huespedes';
  huespedURL = 'http://localhost:8080/api/huespedes';

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
}

