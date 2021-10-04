import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Huesped } from 'src/app/entity/huesped';
import { HuespedService } from 'src/app/service/huesped.service';

@Component({
  selector: 'app-editar-huesped',
  templateUrl: './editar-huesped.component.html',
  styleUrls: ['./editar-huesped.component.css']
})
export class EditarHuespedComponent implements OnInit {

  huesped: Huesped = null;

  constructor(

    private huespedService: HuespedService,
    private actiavedRouter: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router

  ) { }

  ngOnInit(): void {

    const id = this.actiavedRouter.snapshot.params.huespedId;
    this.huespedService.detalle(id).subscribe(
      data => {
        this.huesped = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass:'toast-top-center',
        });
        this.router.navigate(['/']);
      }
    );
  }

  onUpdate(): void{
    const id = this.actiavedRouter.snapshot.params.huespedId;
    this.huespedService.update(id, this.huesped).subscribe(
      data => {
        this.toastr.success('Huesped Actualizado Correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/lista-huesped']);
      },
        err =>{
          this.toastr.error(err.error.mensaje, 'Fail', {
            timeOut: 3000,  positionClass: 'toast-top-center',
          });
        }
      
    );
  }

}
