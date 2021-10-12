import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioComponent } from './common/inicio/inicio.component';
import { HomeComponent } from './contenido/home/home.component';
import { DetalleHuespedComponent } from './huesped/detalle-huesped/detalle-huesped.component';
import { EditarHuespedComponent } from './huesped/editar-huesped/editar-huesped.component';
import { ListaHuespedComponent } from './huesped/lista-huesped/lista-huesped.component';
import { NuevoHuespedComponent } from './huesped/nuevo-huesped/nuevo-huesped.component';
import { LoginComponent } from './inicio/login/login.component';
import { RegistroComponent } from './inicio/registro/registro.component';

import { HuespedGuardService as guard} from './guards/huesped-guard.service';
import { InicioGuardService  as inicioGuard } from './guards/inicio-guard.service';

const routes: Routes = [

  {path: '', component: HomeComponent},
  {path: 'registro', component: RegistroComponent},
  {path: 'inicio', component:InicioComponent,canActivate: [inicioGuard], data: {expectRol:['ADMIN', 'USER']}},
  {path: 'login', component:LoginComponent},
  
  {path: 'lista-huesped', component:ListaHuespedComponent,canActivate: [guard], data: {expectRol:['ADMIN', 'USER']}},
  {path: 'nuevo', component:NuevoHuespedComponent, canActivate: [guard], data: {expectRol:['ADMIN', 'USER']}},
  {path: 'editar/:huespedId',component:EditarHuespedComponent, canActivate: [guard], data: {expectRol:['ADMIN', 'USER']} },
  {path: 'detalle/:huespedId', component:DetalleHuespedComponent, canActivate: [guard], data: {expectRol:['ADMIN', 'USER']}}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
