import {Review} from "./review.model";

export class ReviewImpl implements Review{

  id: number;
  comment: string;
  rating: number;
  carId: number;

}
