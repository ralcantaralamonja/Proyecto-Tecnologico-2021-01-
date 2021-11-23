import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Cuarto } from 'src/app/entity/habitacion';
import { CuartosService } from 'src/app/service/cuartos.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-cuartos-editar',
  templateUrl: './cuartos-editar.component.html',
  styleUrls: ['./cuartos-editar.component.css']
})
export class CuartosEditarComponent implements OnInit {

  cuartos: Cuarto = null;

  constructor(

    private cuartoService: CuartosService,
    private actiavedRouter: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router,


  ) { }

  ngOnInit(): void {

    const id = this.actiavedRouter.snapshot.params.habitacionId;
    this.cuartoService.detalle(id).subscribe(
      data => {
        this.cuartos = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/']);
      }
    );

  }

  onUpdate(): void{
    
    const id = this.actiavedRouter.snapshot.params.habitacionId;
    this.cuartoService.update(id, this.cuartos).subscribe(
      data => {
        this.toastr.success('Disponibilidad Actualizada', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/cuartos']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        }
      
    );
  }

}
