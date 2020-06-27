import { Injectable } from '@angular/core';
import {
  HttpInterceptor, HttpRequest, HttpHandler, HttpSentEvent, HttpHeaderResponse, HttpProgressEvent,
  HttpResponse, HttpUserEvent, HttpErrorResponse, HttpEvent
} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import {TokenStorage} from './token.storage';
import 'rxjs/add/operator/do';
import {catchError, tap} from "rxjs/operators";
import {throwError} from "rxjs";

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class Inteceptor implements HttpInterceptor {

  constructor(private token: TokenStorage, private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if (this.token.getToken() != null) {
      console.debug('token is not empty so set it here');
      req = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this.token.getToken())});
    }

    return next.handle(req).pipe(catchError(err => {

      if ([401, 403].includes(err.status)) {
        console.log('redirect to the login page');
        this.router.navigate(['login']);
      }
      const error = (err && err.error && err.error.message) || err.statusText;
      return throwError(error);
    }))
  }

}
