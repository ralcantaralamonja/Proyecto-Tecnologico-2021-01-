import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Huesped } from '../entity/huesped';
import { NuevoHuesped } from '../entity/nuevoHuesped';
import { map } from 'rxjs/operators'
import { Persona } from '../entity/persona';

const CONTENT_TYPE_KEY = 'Content-Type';
const AUTHORIZATION_KEY = 'Authorization';

@Injectable({
  providedIn: 'root'
})
export class HuespedService {

  huespedURL = 'http://127.0.0.1:8080/api/huespedes';
  apiReniec = 'https://apiperu.dev/api/dni/';

  contentType: string = 'application/json';
  token: string = 'Bearer 2ad4c7f85cc0c5b02da0b2084d7953401bd4c0ce2342e3e9451de55b7e540af8';

  persona: Persona;

  constructor(private httpClient: HttpClient) { }

  /*huespedDetalle(numero: string){
    const ruta = "https://apiperu.dev/api/dni/" + numero;

    return this.httpClient.get(ruta);
  }*/

  categoriasInsert(nombre: string, descripcion: string) {
    const ruta = "https://apiperu.dev/api/dni/";

    const formData: FormData = new FormData();
    formData.append("nombre", nombre);
    formData.append("descripcion", descripcion);

    return this.httpClient.post(ruta, formData).pipe(
      map((res) => {
        return res;
      })
    )
  }


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
    window.sessionStorage.setItem(CONTENT_TYPE_KEY, this.contentType);
    window.sessionStorage.setItem(AUTHORIZATION_KEY, this.token);
    return this.httpClient.get<any>(this.apiReniec + `${dni}`).pipe(
      map((res) => {
        this.persona = res.data;
        return this.persona;
      })
    );
  }
}

