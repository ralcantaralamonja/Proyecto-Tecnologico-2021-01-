import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { LoginUsuario } from 'src/app/entity/login-usuario';
import { AuthService } from 'src/app/service/auth.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  protected aFormGroup: FormGroup;
  siteKey: string;

  isLogged = false;
  isLoginFail = false;
  loginUsuario: LoginUsuario; 
  nombreUsuario: string;
  password: string;
  roles: string[] = [];
  errMsj: string;
  intentos: any = 0;

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private toastr: ToastrService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {this.siteKey = '6Lc_7h0dAAAAAAMXXoQqgeeD7uPkwAanOno7Tm68' }

  ngOnInit(): void {
    if(this.tokenService.getToken()){
      this.isLogged = true;
      this.isLoginFail = false;
      this.roles = this.tokenService.getAuthorities();
    }
    this.aFormGroup = this.formBuilder.group({
      recaptcha: ['', Validators.required]
    });
  }


  test() {
    this.toastr.success("I'm a toast!", "Success!");
  }

  onLogin(): void{
    this.loginUsuario = new LoginUsuario(this.nombreUsuario, this.password);
    
    this.authService.login(this.loginUsuario).subscribe(
      data =>{
        this.isLogged=true;
        this.isLoginFail=false;

        this.tokenService.setToken(data.token);
        this.tokenService.setUserName(data.username);
        this.tokenService.setAuthorities(data.authorities);
        this.siteKey = '6Lc_7h0dAAAAAAMXXoQqgeeD7uPkwAanOno7Tm68';
        
        this.roles = data.authorities;
        this.router.navigate(['/inicio']);
      },
      err => {
        this.isLogged = false;
        this.isLoginFail = true;
        this.errMsj = err.error.mensaje;
        this.toastr.error(this.errMsj, 'Fail');

        this.authService.bloquear(this.loginUsuario).subscribe(
          data =>{
         
     
            this.tokenService.setToken(data.token);
            this.tokenService.setUserName(data.username);
            this.tokenService.setAuthorities(data.authorities);
           
            this.roles = data.authorities;
        
          },
          err => {
            this.isLogged = false;
            this.isLoginFail = true;
            this.errMsj = err.error.mensaje;
            this.toastr.error(this.errMsj, 'Fail');
     
            this.intentos = 0;
     
            while (true) {
              this.intentos = this.intentos +1;
             if(this.intentos === 3){
              console.log("error -> " + err.error.mensaje);
              break;
             }
             
            }
     
            console.log("error -> " + err.error.mensaje);
          }
       )

        console.log("error -> " + err.error.mensaje);
      }
    )
  
    
  }

  // bloquear(){
  //   this.authService.bloquear(this.loginUsuario).subscribe(
  //     data =>{
      
  
  //       this.tokenService.setToken(data.token);
  //       this.tokenService.setUserName(data.username);
  //       this.tokenService.setAuthorities(data.authorities);
        
  //       this.roles = data.authorities;
     
  //     },
  //     err => {
  //       this.isLogged = false;
  //       this.isLoginFail = true;
  //       this.errMsj = err.error.mensaje;
  //       this.toastr.error(this.errMsj, 'Fail');
  
  //       this.intentos = 0;
  
  //       while (true) {
  //         this.intentos = this.intentos +1;
  //        if(this.intentos === 3){
  //         console.log("error -> " + err.error.mensaje);
  //         break;
  //        }
          
  //       }
  
  //       console.log("error -> " + err.error.mensaje);
  //     }
  //   )
  // }
}
