import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Owner} from "./owner.model";
import {environment} from "../../../environments/environment";

@Injectable()
export class OwnerService {

  constructor(private http: HttpClient) {}

  public getOwners(): Observable<Owner[]> {
    return this.http.get<Owner[]>(environment.userUrl + '/api/owner/list');
  }

  public getOwnerByCarId(carId: number): Observable<Owner[]> {
    return this.http.get<Owner[]>(environment.userUrl + '/api/owner/listOwnerByCarId/ ' + carId);
  }

  public saveNewOwner(newOwner: Owner): Observable<Owner> {
    return this.http.post<Owner>(environment.userUrl + '/api/owner/cars/' + newOwner.carId + '/owners', newOwner);
  }
}
