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
import { AdminGuardService  as adminGuard } from './guards/admin-guard.service';

import { ListaUsuarioComponent } from './usuario/lista-usuario/lista-usuario.component';
import { NuevoUsuarioComponent } from './usuario/nuevo-usuario/nuevo-usuario.component';

const routes: Routes = [

  {path: '', component: HomeComponent},
  {path: 'registro', component: RegistroComponent,canActivate: [guard], data: {expectRol:['ADMIN', 'USER']}},
  {path: 'inicio', component:InicioComponent,canActivate: [inicioGuard], data: {expectRol:['ADMIN', 'USER']}},
  {path: 'usuario', component:ListaUsuarioComponent,canActivate: [inicioGuard], data: {expectRol:['ADMIN', 'USER']}},

  {path: 'login', component:LoginComponent},
  
  {path: 'lista-huesped', component:ListaHuespedComponent,canActivate: [guard], data: {expectRol:['ADMIN', 'USER']}},
  {path: 'nuevo', component:NuevoHuespedComponent/*, canActivate: [adminGuard], data: {expectRol:['MANAGER', 'ADMIN']}*/},
  {path: 'editar/:huespedId',component:EditarHuespedComponent, canActivate: [guard], data: {expectRol:['ADMIN', 'USER']} },
  {path: 'lista-usuario', component:ListaUsuarioComponent, canActivate: [guard], data: {expectRol:['ADMIN', 'USER']}},

  {path: 'detalle/:huespedId', component:DetalleHuespedComponent, canActivate: [guard], data: {expectRol:['ADMIN', 'USER']}},
  

  {path: 'nuevo-usuario', component:NuevoUsuarioComponent,canActivate: [guard], data: {expectRol:['ADMIN', 'USER']}},


];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
