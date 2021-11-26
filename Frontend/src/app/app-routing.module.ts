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

import { HuespedGuardService as guard } from './guards/huesped-guard.service';
import { InicioGuardService as inicioGuard } from './guards/inicio-guard.service';
import { AdminGuardService as adminGuard } from './guards/admin-guard.service';

import { ListaUsuarioComponent } from './usuario/lista-usuario/lista-usuario.component';
import { NuevoUsuarioComponent } from './usuario/nuevo-usuario/nuevo-usuario.component';


import { CuartosComponent } from './cuarto/cuartos/cuartos.component';
import { CuartosEditarComponent } from './cuarto/cuartos-editar/cuartos-editar.component';

import { ReservasComponent } from './reserva/reservas/reservas.component';
import { ReservasAgregarComponent } from './reserva/reservas-agregar/reservas-agregar.component';

import { ListadoReservasMvcComponent } from './mvcReservas/listado-reservas-mvc/listado-reservas-mvc.component';

const routes: Routes = [

  { path: '', component: HomeComponent },
  { path: 'registro', component: RegistroComponent, canActivate: [guard], data: { expectRol: ['ADMIN', 'MANAGER'] } },
  { path: 'inicio', component: InicioComponent,/*canActivate: [inicioGuard], data: {expectRol:['ADMIN', 'USER']}*/ },
  { path: 'usuario', component: ListaUsuarioComponent, canActivate: [inicioGuard], data: { expectRol: ['ADMIN', 'USER'] } },

  { path: 'login', component: LoginComponent },

  { path: 'lista-huesped', component: ListaHuespedComponent, canActivate: [guard], data: { expectRol: ['MANAGER', 'USER'] } },
  { path: 'nuevo', component: NuevoHuespedComponent, canActivate: [guard], data: { expectRol: ['MANAGER', 'USER'] }  },
  { path: 'editar/:huespedId', component: EditarHuespedComponent, canActivate: [guard], data: { expectRol: ['MANAGER'] } },
  { path: 'lista-usuario', component: ListaUsuarioComponent, canActivate: [guard], data: { expectRol: ['ADMIN', 'MANAGER'] } },

  { path: 'detalle/:huespedId', component: DetalleHuespedComponent, canActivate: [guard], data: { expectRol: ['ADMIN', 'MANAGER'] } },

  { path: 'nuevo-usuario', component: NuevoUsuarioComponent, canActivate: [guard], data: { expectRol: ['ADMIN', 'MANAGER', 'USER'] } },

  { path: 'cuartos', component: CuartosComponent },
  { path: 'editarcuarto/:habitacionId', component: CuartosEditarComponent },

  { path: 'reservas', component: ReservasComponent },
  { path: 'reservasagregar', component: ReservasAgregarComponent },

  {path: 'mvcreserva', component: ListadoReservasMvcComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
