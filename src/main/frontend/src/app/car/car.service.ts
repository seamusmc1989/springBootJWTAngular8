import {Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {environment} from "../../environments/environment";
import {Car} from "./car.model";

@Injectable()
export class CarService {

  constructor(private http: HttpClient) {}

  public getCars(): Observable<Car[]> {
    return this.http.get<Car[]>(environment.userUrl + '/api/car/list');
  }

  //getCarsByOwnerId
  public getCarsByOwnerId(): Observable<Car[]> {
    return this.http.get<Car[]>(environment.userUrl + '/api/car/findByOwnerId');
  }

  public saveCar(newCar: Car): Observable<Car> {
    return this.http.post<Car>(environment.userUrl + '/api/car/save', newCar);
  }

}
