import {Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {environment} from "../../environments/environment";

@Injectable()
export class HeaderService {

  constructor(private http: HttpClient) {}


  // public getSample(): Observable<Car[]> {
  //   return this.http.get<Car[]>(environment.userUrl + '/api/car/find');
  // }

}
