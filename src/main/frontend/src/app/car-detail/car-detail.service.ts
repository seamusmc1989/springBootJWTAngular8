import {Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {TokenStorage} from "../core/token.storage";
import {Car} from "../car/car.model";


@Injectable()
export class CarDetailService {

  private userId: string;

  constructor(private http: HttpClient, private token: TokenStorage) {
    this.userId = token.getUserId();
  }

  private userUrl = 'http://localhost:8080/';
  id: number;

  public getCarDetail(id: number): Observable<Car> {
    console.log('getCarDetail');
    return this.http.get<Car>(this.userUrl + 'api/car/findById/' + id);
  }

}
