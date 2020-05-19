import {Component, OnInit} from '@angular/core';
import {CarService} from "./car.service";
import {Car} from "./car.model";

import {OwnerService} from "../dialogs/add-car-owner-dialog/owner.service";
import {Owner} from "../dialogs/add-car-owner-dialog/owner.model";
import {TokenStorage} from '../core/token.storage';
import {Router} from '@angular/router';
import {DialogsService} from "../dialogs/dialogs.service";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {
  displayedColumns = ['id', 'make', 'model', 'model', 'transmission' , 'action'];
  dataSource = new MatTableDataSource<Car>();
  username: string;
  public carOwners: Owner[];

  newCarEventHander($event: any) {
    console.table($event);
    console.log('after printing the event details passed up from the child');
    this.fetchDb();
  }

  constructor(private router: Router, private carService: CarService, private token: TokenStorage,
              private ownerService: OwnerService, private dialogsService: DialogsService) {
  }

  ngOnInit(): void {

    this.username = this.token.getUserToken();
    this.carService.getCars().subscribe(
      data => {
        this.dataSource.data = data;
        console.log(data);
      }
    );




    //TODO need to decide if I want to finish this logic
    this.ownerService.getOwners().subscribe(
      data => {
        console.log('data is: ' + data);
        this.carOwners = data;
      }
    )

    //TODO need to decide if i want to remove this or not...
    this.carService.getCarsByOwnerId().subscribe(
      data => {
        console.log('data for getCarsByOnwerId is: ' + data);
        //this.carOwners = data;
      }
    )

  }

  onRowClicked(row): void {
     console.log('example of having the onRowClicked for it: ' + row.id);

   //this.router.navigate(['/car/detail', row.id]);
  }

  viewCar(carId): void {
    console.log('carId for viewCar is: ' + carId);
    this.router.navigate(['/car/detail', carId]);
  }

  editCar(car): void {
    console.log('carId for editCar is: ' + car);

    this.dialogsService.showEditCarDialog(car).subscribe(
      res => {
        if (res != null) {
          // let newCars: Car[] = this.dataSource.data;
          // newCars.push(res);
          // this.dataSource.data = newCars;
        }
      })

    //this.router.navigate(['/car/detail', carId]);
  }

  deleteCar(carId): void {
    console.log('carId for deleteCar is: ' + carId);
    //this.router.navigate(['/car/detail', carId]);
  }

  fetchDb(): void {
    this.carService.getCars().subscribe(
      data => {
        this.dataSource.data = data;
        console.log(data);
      }
    );
  }

  logout(): void {
    //TODO need to add /logout invalidate token url to rest endpoints
    this.router.navigate(['/login']);
  }

  //TODO this was for the select dropdown options.
  findCarsByOwner(): void{
    console.log('findCarsByOwner');

    this.carService.getCarsByOwnerId().subscribe(
      data => {
        console.log('data is: ' + data);
      }
    )
  }

  public openAddNewCarDialog() {
    this.dialogsService.showAddCarDialog().subscribe(
      res => {
        if (res != null) {
          let newCars: Car[] = this.dataSource.data;
          newCars.push(res);
          this.dataSource.data = newCars;
        }
      });
  }
}
