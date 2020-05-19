import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable()
export class AuthService {

  constructor(private http: HttpClient) {
  }

  attemptAuth(username: string, password: string): Observable<any> {
    const credentials = {username: username, password: password};
    console.log('attempAuth username: ' + username + ' password: ' + password);


    const responseVar = this.http.post<any>(
      'http://localhost:8080/token/generate-token', credentials
    );

    responseVar.subscribe(jsonResp => {
        console.log(jsonResp.toString());
      },
      err => {
        console.log('ERROR IS ' + err.url + ' >>> ' + err.statusText +  '[' + err.toString() + ']');
      });

    return responseVar;

  }
}
