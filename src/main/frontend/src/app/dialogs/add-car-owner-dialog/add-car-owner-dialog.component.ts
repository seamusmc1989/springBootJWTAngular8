import {Component, Inject, OnInit} from "@angular/core";
import {Owner} from "./owner.model";
import {OwnerImpl} from "./owner-impl.model";
import {OwnerService} from "./owner.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-add-car-owner-dialog',
  templateUrl: './add-car-owner-dialog.component.html'
})
export class AddCarOwnerDialogComponent implements OnInit {

  owner: Owner;
  submitted = false;

  constructor(public dialogRef: MatDialogRef<AddCarOwnerDialogComponent, Owner>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private ownerService: OwnerService) { }


    ngOnInit() {
    this.owner = new OwnerImpl();
  }
  onSubmit() {
    console.log('this.owner details to add are:' + this.owner.toString());

    console.log('data carId is: ' + this.data.carId);
    // console.log('data passed in is:', this.data.auditId);
    // this.auditId = Number(this.data.auditId);
    // console.log('auditId number is: ' + this.auditId)

    this.owner.carId = this.data.carId;
    console.log('this.owner details to add are:' + this.owner.toString() + this.owner.carId);

    this.ownerService.saveNewOwner(this.owner).subscribe(
      data => {
        this.dialogRef.close(data);
        console.log(data);
      }
    );

  }
}
