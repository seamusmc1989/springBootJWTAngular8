import {MatDialogRef} from "@angular/material";
import {Component, EventEmitter, Inject, OnInit, Output} from "@angular/core";
import {Car} from "../../car/car.model";
import {CarService} from "../../car/car.service";
import {CarImpl} from "../../car/car-impl.model";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
  selector: 'app-add-car-dialog',
  templateUrl: './add-car-dialog.component.html'
})
export class AddCarDialogComponent implements OnInit {

  car: Car;
  submitted = false;
  isEdit: boolean;

  constructor(public dialogRef: MatDialogRef<AddCarDialogComponent, Car>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private carService: CarService) {}

  ngOnInit() {
    console.log(this.data)

    if (this.data == null) {
      console.log('it is an add as no data was passed ');
      this.isEdit = false;
      this.car = new CarImpl();
    }
    else {
      console.log('it is an edit so assign the form');
      this.isEdit = true;
      console.table(this.data.car);
      this.car = this.data.car;
    }

  }
  onSubmit() {
      this.carService.saveCar(this.car).subscribe(
        data => {
          this.dialogRef.close(data);
        }
      );

  }
}
