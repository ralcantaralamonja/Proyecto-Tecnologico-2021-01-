import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { interceptorProvider } from './interceptor/interceptors.service';
import { ToastrModule } from 'ngx-toastr';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './contenido/home/home.component';
import { LoginComponent } from './inicio/login/login.component';
import { InicioComponent } from './common/inicio/inicio.component';
import { RegistroComponent } from './inicio/registro/registro.component';
import { NavbarComponent } from './contenido/navbar/navbar.component';
import { ListaHuespedComponent } from './huesped/lista-huesped/lista-huesped.component';
import { DetalleHuespedComponent } from './huesped/detalle-huesped/detalle-huesped.component';
import { EditarHuespedComponent } from './huesped/editar-huesped/editar-huesped.component';
import { NuevoHuespedComponent } from './huesped/nuevo-huesped/nuevo-huesped.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    InicioComponent,
    RegistroComponent,
    NavbarComponent,
    ListaHuespedComponent,
    DetalleHuespedComponent,
    EditarHuespedComponent,
    NuevoHuespedComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ToastrModule.forRoot()
  ],
  providers: [interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
