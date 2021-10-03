import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Huesped } from '../entity/huesped';

@Injectable({
  providedIn: 'root'
})
export class HuespedService {

  huespedURL = 'http://localhost:8080/api/huespedes';

  constructor(private httpClient: HttpClient) { }

  public lista(): Observable<Huesped[]> {
    return this.httpClient.get<Huesped[]>(this.huespedURL + 'lista');
  }

  public detalle(id: number): Observable<Huesped> {
    return this.httpClient.get<Huesped>(this.huespedURL + `detalle/${id}`);
  }

  public detalleName(nombre: string): Observable<Huesped> {
    return this.httpClient.get<Huesped>(this.huespedURL + `detalleName/${nombre}`);
  }

  public save(huesped: Huesped): Observable<any> {
    return this.httpClient.post<any>(this.huespedURL + 'create', huesped);
  }

  public update(id: number, huesped: Huesped): Observable<any> {
    return this.httpClient.put<any>(this.huespedURL + `update/${id}`, huesped);
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.huespedURL + `delete/${id}`);
  }
}
