import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioComponent } from './common/inicio/inicio.component';
import { HomeComponent } from './contenido/home/home.component';
import { LoginComponent } from './inicio/login/login.component';
import { RegistroComponent } from './inicio/registro/registro.component';

const routes: Routes = [

  {path: '', component: HomeComponent},
  {path: 'registro', component: RegistroComponent},
  {path: 'inicio', component:InicioComponent},
  {path: 'login', component:LoginComponent}
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
