import { Component, OnInit } from '@angular/core';
import { CarDetailService } from './car-detail.service';
import {Router} from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import {TokenStorage} from '../core/token.storage';
import {DialogsService} from "../dialogs/dialogs.service";
import {Car} from "../car/car.model";
import {Owner} from "../dialogs/add-car-owner-dialog/owner.model";
import {OwnerService} from "../dialogs/add-car-owner-dialog/owner.service";
import {MatDialog} from "@angular/material/dialog";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-car-detail',
  templateUrl: './car-detail.component.html',
  styleUrls: ['./car-detail.component.css', '../car/car.component.css']
})
export class CarDetailComponent implements OnInit {

  id: number;
  car: Car;

  currentStep: string;
  username: string;

  dataSourceOwner = new MatTableDataSource<Owner>();
  displayedColumnsOwner = ['id', 'firstName', 'lastName',  'username'];

  constructor(private router: Router, private route: ActivatedRoute, private dialogService: DialogsService,
              private carDetailService: CarDetailService, private token: TokenStorage,
              private ownerService: OwnerService,
              public dialog: MatDialog) { }

  ngOnInit(): void {

    this.id = this.route.snapshot.params.id;
    this.username = this.token.getUserToken();

    this.carDetailService.getCarDetail(this.id).subscribe(
      data => {
        console.log(data);
        this.car = data;
      }
    )

    this.ownerService.getOwnerByCarId(this.id).subscribe(
      data => {
        console.log('data for getOwnerByCarId is: ' + data);
        this.dataSourceOwner.data = data;
      }
    )

  }

  viewCarReview(): void {
    console.log('viewCarReview');
  }

  addCarReview(): void {
    console.log('addCarReview for carId: ' + this.id);

    this.dialogService.showAddReviewDialog(this.id).subscribe(
      res => {
        // if (res != null) {
        //   console.log('res is: ' + res);
        //   let newOwners: Owner[] = this.dataSourceOwner.data;
        //   newOwners.push(res);
        //   this.dataSourceOwner.data = newOwners;
        // }
      });

  }


  addCarOwner(): void {
    console.log('addCarOwner for carId: ' + this.id);

    //TODO pass the carId here and refresh the UI
    this.dialogService.showAddOwnerDialog(this.id).subscribe(
      res => {
        if (res != null) {
          console.log('res is: ' + res);
          let newOwners: Owner[] = this.dataSourceOwner.data;
          newOwners.push(res);
          this.dataSourceOwner.data = newOwners;
        }
      });

  }
}
