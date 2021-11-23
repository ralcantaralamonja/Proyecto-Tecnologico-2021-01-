import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { interceptorProvider } from './interceptor/interceptors.service';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FilterPipeModule } from 'ngx-filter-pipe';



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
import { DesignComponent } from './contenido/design/design.component';
import { NuevoUsuarioComponent } from './usuario/nuevo-usuario/nuevo-usuario.component';
import { DetalleUsuarioComponent } from './usuario/detalle-usuario/detalle-usuario.component';
import { EditarUsuarioComponent } from './usuario/editar-usuario/editar-usuario.component';
import { ListaUsuarioComponent } from './usuario/lista-usuario/lista-usuario.component';
import { CuartosComponent } from './cuarto/cuartos/cuartos.component';
import { NgxCaptchaModule } from 'ngx-captcha';
import { ReactiveFormsModule } from '@angular/forms';
import { CuartosEditarComponent } from './cuarto/cuartos-editar/cuartos-editar.component';
import { ReservasComponent } from './reserva/reservas/reservas.component';
import { ReservasAgregarComponent } from './reserva/reservas-agregar/reservas-agregar.component';
import { ListadoReservasMvcComponent } from './mvcReservas/listado-reservas-mvc/listado-reservas-mvc.component';


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
    NuevoHuespedComponent,
    DesignComponent,
    NuevoUsuarioComponent,
    DetalleUsuarioComponent,
    EditarUsuarioComponent,
    ListaUsuarioComponent,
    CuartosComponent,
    CuartosEditarComponent,
    ReservasComponent,
    ReservasAgregarComponent,
    ListadoReservasMvcComponent,
    
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgxCaptchaModule,
    FilterPipeModule,
    ToastrModule.forRoot(
      {
        timeOut: 3000, positionClass:'toast-top-center',
      }
    ),
    BrowserAnimationsModule
  ],
  providers: [interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
