import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
// import {Owner} from "./owner.model";
import {Review} from  './review.model';
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";

@Injectable()
export class ReviewService {

  constructor(private http: HttpClient) {}

  // public getOwners(): Observable<Owner[]> {
  //   return this.http.get<Owner[]>(environment.userUrl + '/api/owner/list');
  // }
  //
  // public getOwnerByCarId(carId: number): Observable<Owner[]> {
  //   return this.http.get<Owner[]>(environment.userUrl + '/api/owner/listOwnerByCarId/ ' + carId);
  // }
// /add/{carId}/review")
  public saveNewReview(newReview: Review): Observable<Review> {
    return this.http.post<Review>(environment.userUrl + '/api/review/add/' + newReview.carId + '/review', newReview);
  }
}
