import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material';
import {AuthService} from '../core/auth.service';
import {TokenStorage} from '../core/token.storage';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private router: Router, public dialog: MatDialog, private authService: AuthService, private token: TokenStorage) {
  }

  username: string;
  password: string;

  // added 05/04/18
  errorMessage: string;

  login(): void {

    console.log('login entered');
    this.authService.attemptAuth(this.username, this.password).subscribe(
      data => {
        this.token.saveToken(data.token);
        this.token.saveUserId(data.userId);
        this.token.saveUserToken(this.username);
        this.token.saveUserRoles(data.roles);

        this.router.navigate(['car']);
      },
      err => {
        console.log('error on the login.component screen');
        this.errorMessage = 'Invalid Login';
      }
    );
  }

}
