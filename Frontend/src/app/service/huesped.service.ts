import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Huesped } from '../entity/huesped';
import { NuevoHuesped } from '../entity/nuevoHuesped';
import { map } from 'rxjs/operators'


@Injectable({
  providedIn: 'root'
})
export class HuespedService {

  //huespedURL = 'http://192.168.1.254:8080/api/huespedes';

//  huespedURL = 'http://192.168.18.75:8080/api/huespedes';
//huespedURL = 'http://localhost:8080/api/huespedes';




  //huespedURL = 'http://192.168.18.75:8080/api/huespedes';
  huespedURL = 'http://127.0.0.1:8080/api/huespedes';

 

  constructor(private httpClient: HttpClient) { }
 
  /*huespedDetalle(numero: string){
    const ruta = "https://apiperu.dev/api/dni/" + numero;

    return this.httpClient.get(ruta);
  }*/

  categoriasInsert(nombre:string, descripcion:string){
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
}

