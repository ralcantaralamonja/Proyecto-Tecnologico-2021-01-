import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Huesped } from '../entity/huesped';
import { NuevoHuesped } from '../entity/nuevoHuesped';
import { map } from 'rxjs/operators'
import { Persona} from '../entity/persona';

@Injectable({
  providedIn: 'root'
})
export class HuespedService {

  huespedURL = 'http://127.0.0.1:8080/api/huespedes';
  apiReniec = 'http://127.0.0.1:8080/api/dni/';

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
    return this.httpClient.get<Persona>(this.apiReniec + `${dni}`);
  }
}