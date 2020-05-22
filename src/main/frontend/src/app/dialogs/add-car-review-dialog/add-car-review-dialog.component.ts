import {Component, Inject, OnInit} from "@angular/core";

import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Review} from "./review.model";
import {ReviewImpl} from "./review-impl.model";
import {ReviewService} from "./review.service";

@Component({
  selector: 'app-add-car-review-dialog',
  templateUrl: './add-car-review-dialog.component.html'
})
export class AddCarReviewDialogComponent implements OnInit {

  review: Review;
  submitted = false;

  constructor(public dialogRef: MatDialogRef<AddCarReviewDialogComponent, Review>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private reviewService: ReviewService) { }


  ngOnInit() {
    this.review = new ReviewImpl();
  }
  onSubmit() {
    console.log('this.review details to add are:' + this.review.toString());
    console.log('data carId is: ' + this.data.carId);
    this.review.carId = this.data.carId;
    console.log('this.review details to add are:' + this.review.toString() + this.review.carId);
    console.log('review object passed up is ' + this.review.id + ' coment ' + this.review.comment);

    this.reviewService.saveNewReview(this.review).subscribe(
      data => {
        this.dialogRef.close(data);
        console.log('after the saveNewReview ');
        console.log(data);
      }
    );

  }
}
