import { Injectable } from '@angular/core';
import {Car} from "../car/car.model";
import {AddCarDialogComponent} from "./add-car-dialog/add-car-dialog.component";
import {Owner} from "./add-car-owner-dialog/owner.model";
import {AddCarOwnerDialogComponent} from "./add-car-owner-dialog/add-car-owner-dialog.component";
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {Observable} from "rxjs";


@Injectable()
export class DialogsService {

  constructor(private dialog: MatDialog) { }

  public showAddCarDialog(): Observable<Car>{
    let dialogRef: MatDialogRef<AddCarDialogComponent>;
    dialogRef = this.dialog.open(AddCarDialogComponent);
    return dialogRef.afterClosed();
  }

  public showEditCarDialog(car: Car): Observable<Car>{
    let dialogRef: MatDialogRef<AddCarDialogComponent>;

    dialogRef = this.dialog.open(AddCarDialogComponent, {
      data: {
        car: car
      },
      height: '70%',
      width: '30%'
    });


    return dialogRef.afterClosed();
  }

  public showAddOwnerDialog(carId :number): Observable<Owner>{
    console.log('carId for showAddOwnerDialog in dialogs.service is: ' + carId);
    let dialogRef: MatDialogRef<AddCarOwnerDialogComponent>;

    dialogRef = this.dialog.open(AddCarOwnerDialogComponent, {
      data: {
        carId: carId
      },
      height: '70%',
      width: '30%'
    });

    return dialogRef.afterClosed();
  }

}
